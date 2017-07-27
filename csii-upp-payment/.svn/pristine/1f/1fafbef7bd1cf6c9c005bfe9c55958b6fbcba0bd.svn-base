package com.csii.upp.payment.action.start;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 用户支付方式交易限制检查
 * 
 * @author xujin
 * 
 */
public class CheckUserPayTypTransLimitAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputPaymentData inputData = (InputPaymentData) ctx.getData(Dict.INPUT_PAYMENT_DATA);
		// 他行网银支付不控制用户支付方式
		if(PayTypCd.OTHCYBER.equals(inputData.getPaytypcd()) || inputData.getMercusttransctrl() != null||
				PayTypCd.OTHACTSCAN.equals(inputData.getPaytypcd())||
				PayTypCd.OTHPASSCAN.equals(inputData.getPaytypcd()))
			return;
		log.info(new StringBuilder().append("支付方式[").append(inputData.getPaytypcd()).append("]").append("支付平台流水[")
				.append(inputData.getTransseqnbr()).append("]").append("商户号[").append(inputData.getMernbr()).append("]")
				.append("商户流水[").append(inputData.getMerseqnbr()).append("]").append("商户时间[")
				.append(ctx.getData(Dict.MER_TRANS_DATE_TIME)).append("]").append("交易代码[")
				.append(inputData.getTranscode()).append("] 用户支付方式交易限制检查!").toString());
		// 检查客户签约和客户交易限额并更新客户每日已累积限额
		try {
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andSigncardnbrEqualTo(inputData.getPayeracctnbr())
					.andPaytypcdEqualTo(inputData.getPaytypcd());
			List<Userpaytypsigninfo> signInfos = UserpaytypsigninfoDAO.selectByExample(example);
			Userpaytypsigninfo singInfo = null;
			// 验证用户支付方式签约
			if (signInfos.isEmpty()) {
				// 如果本行网银支付就进行自动签约
				if (PayTypCd.OURCYBER.equals(inputData.getPaytypcd())) {
					singInfo = this.autoSignUserPayTyp(inputData.getPayerphoneno(), inputData.getPayeracctnbr(),inputData.getPayeracctdeptnbr(),
							inputData.getPaytypcd());
				} else {
					throw new PeException(DictErrors.CUST_NOT_SIGN);
				}
			} else {
				singInfo = signInfos.get(0);
			}
			// 验证签约状态
			if (!SignStatus.NORMA.equals(singInfo.getSignstatus())) {
				throw new PeException(DictErrors.SIGN_STATUS_ERROR,new Object[]{singInfo.getSignstatus().equals(SignStatus.CANCEL)?Dict.CANCEL:Dict.FROZEN});
			}
			// 通过签约号和签约类型查找custtransctrl表获单笔限额和每日限额
			Custtransctrl custCtrl = CusttransctrlDAO.selectByPrimaryKey(singInfo.getSignnbr(),
					singInfo.getSigntypcd());
			// 检查客户交易控制信息是否存在
			if (custCtrl == null) {
				throw new PeException(DictErrors.CUST_TRANS_LIMIT_ERROR);
			}

			// 检查客户单笔交易限额
			if (inputData.getTransamt().compareTo(custCtrl.getPertranslimit()) > 0) {
				throw new PeException(DictErrors.PER_TRANS_LIMIT_OVER);
			}

			// 检查客户当日交易限额
			if (custCtrl.getDayamtdate() != null && inputData.getTransdate().equals(custCtrl.getDayamtdate())) {
				// 客户当日已经做过交易验证当日限额是否小于已支付金额+本次支付金额
				if (custCtrl.getPerdaylimit().compareTo(custCtrl.getDayamt().add(inputData.getTransamt())) < 0) {
					throw new PeException(DictErrors.PER_DAY_LIMIT_OVER);
				}
				// 更新当日限额
				custCtrl.setDayamt(custCtrl.getDayamt().add(inputData.getTransamt()));
			} else {
				// 客户当日未做过交易验证当日限额是否小于本次支付金额
				if (custCtrl.getPerdaylimit().compareTo(inputData.getTransamt()) < 0) {
					throw new PeException(DictErrors.PER_DAY_LIMIT_OVER);
				}
				// 更新当日限额和交易日期
				custCtrl.setDayamt(inputData.getTransamt());
				custCtrl.setDayamtdate(inputData.getTransdate());
			}

			// 更新客户当日交易限额
			CusttransctrlDAO.updateByPrimaryKey(custCtrl);
			// 设置已更新每日累积金额的客户交易控制信息
			inputData.setCustTransCtrl(custCtrl);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}
}
