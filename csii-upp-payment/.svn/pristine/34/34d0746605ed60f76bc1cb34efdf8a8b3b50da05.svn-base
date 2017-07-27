package com.csii.upp.payment.action.start;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.LimitFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.MercusttransctrlDAO;
import com.csii.upp.dao.generate.SpecialmerctrlDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Mercusttransctrl;
import com.csii.upp.dto.generate.Specialmerctrl;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 特殊商户支付方式交易限制检查
 * 
 * @author qgs
 *
 */
public class CheckSpecMerPayTypTransLimitAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData inputData = (InputPaymentData) context.getData(Dict.INPUT_PAYMENT_DATA);
		if (!PayTypCd.CARDPWD.equals(inputData.getPaytypcd())) {
			return; 
		}
		try {
			Specialmerctrl spmer = SpecialmerctrlDAO.selectByPrimaryKey(inputData.getMernbr());
			if (spmer == null || LimitFlag.OFF.equals(spmer.getLimitflag())) {
				return;
			}
			if (inputData.getTransamt().compareTo(spmer.getUserperlimit()) > 0) {
				throw new PeException(DictErrors.PER_TRANS_LIMIT_OVER);
			}
			
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andSigncardnbrEqualTo(inputData.getPayeracctnbr())
					.andPaytypcdEqualTo(inputData.getPaytypcd());
			List<Userpaytypsigninfo> signInfos = UserpaytypsigninfoDAO.selectByExample(example);
			Userpaytypsigninfo signInfo = null;
			// 验证用户支付方式签约
			if (signInfos.isEmpty()) {
				// 如果本行网银支付就进行自动签约
				if (PayTypCd.OURCYBER.equals(inputData.getPaytypcd())) {
					signInfo = this.autoSignUserPayTyp(inputData.getPayerphoneno(), inputData.getPayeracctnbr(),
							inputData.getPayeracctdeptnbr(), inputData.getPaytypcd());
				} else {
					throw new PeException(DictErrors.CUST_NOT_SIGN);
				}
			} else {
				signInfo = signInfos.get(0);
			}
			// 验证签约状态
			if (!SignStatus.NORMA.equals(signInfo.getSignstatus())) {
				throw new PeException(DictErrors.SIGN_STATUS_ERROR, new Object[] {
						signInfo.getSignstatus().equals(SignStatus.CANCEL) ? Dict.CANCEL : Dict.FROZEN });
			}

			Mercusttransctrl merCustCtrl = MercusttransctrlDAO.selectByPrimaryKey(inputData.getMernbr(),
					signInfo.getSignnbr(), signInfo.getSigntypcd());
			if (merCustCtrl == null) {
				// 客户当日未做过交易验证当日限额是否小于本次支付金额
				if (spmer.getUserdaylimit().compareTo(inputData.getTransamt()) < 0) {
					throw new PeException(DictErrors.PER_DAY_LIMIT_OVER);
				}
				merCustCtrl = new Mercusttransctrl();
				merCustCtrl.setMernbr(inputData.getMernbr());
				merCustCtrl.setSignnbr(signInfo.getSignnbr());
				merCustCtrl.setSigntypcd(signInfo.getSigntypcd());
				merCustCtrl.setPerdaylimit(spmer.getUserdaylimit());
				merCustCtrl.setPertranslimit(spmer.getUserperlimit());
			
				// 更新当日限额和交易日期
				merCustCtrl.setDayamt(inputData.getTransamt());
				merCustCtrl.setDayamtdate(inputData.getTransdate());
				
				MercusttransctrlDAO.insertSelective(merCustCtrl);
			} else {
				if(inputData.getTransdate().equals(merCustCtrl.getDayamtdate())){
					// 客户当日已经做过交易验证当日限额是否小于已支付金额+本次支付金额
					if (merCustCtrl.getPerdaylimit().compareTo(merCustCtrl.getDayamt().add(inputData.getTransamt())) < 0) {
						throw new PeException(DictErrors.PER_DAY_LIMIT_OVER);
					}
					// 更新当日限额
					merCustCtrl.setDayamt(merCustCtrl.getDayamt().add(inputData.getTransamt()));
					
				} else {
					// 客户当日未做过交易验证当日限额是否小于本次支付金额
					if (merCustCtrl.getPerdaylimit().compareTo(inputData.getTransamt()) < 0) {
						throw new PeException(DictErrors.PER_DAY_LIMIT_OVER);
					}
					// 更新当日限额和交易日期
					merCustCtrl.setDayamt(inputData.getTransamt());
					merCustCtrl.setDayamtdate(inputData.getTransdate());
				}
				// 更新客户当日交易限额
				MercusttransctrlDAO.updateByPrimaryKey(merCustCtrl);
				
			}
			// 更新每日累积金额的特殊商户客户交易控制信息
			inputData.setMercusttransctrl(merCustCtrl);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}

}
