package com.csii.upp.payment.action.mgmt;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;

public class FoisonLoginAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		// 查询本地签约信息
		// InputPaymentData input = this.prepareTransData(context.getDataMap());
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		queryCardType(input);
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		context.setData(Dict.CUST_CIF_NBR, input.getCustcifnbr());

		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr());
		List<Userpaytypsigninfo> UerpaytypsigninfoList = null;
		try {
			UerpaytypsigninfoList = UserpaytypsigninfoDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		if (UerpaytypsigninfoList.isEmpty()) {
			throw new PeException(DictErrors.CUST_NOT_SIGN);
		}
		// 取本地签约手机号
		Userpaytypsigninfo signinfo = UerpaytypsigninfoList.get(0);
		// 核心查询卡信息
		RespQueryCardInfo output = (RespQueryCardInfo) getFDPSService().queryCardInfo(input);
		context.setData(Dict.PAYER_ACCT_NAME, output.getPayerAcctName());
		context.setData(Dict.PAYER_ACCT_TYP_CD, signinfo.getAcctkindcd());
		context.setData(Dict.CUST_CIF_NBR, output.getCustCifNbr());
		context.setData(Dict.PAYER_ACCT_DEPT_NBR, output.getPayerAcctDeptNbr());

		// 如果核心手机号list不包含用户输入手机号
		if (!output.getPayerPhoneNoList().contains(input.getPayerphoneno())) {
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		// 如果核心手机号list不包含用户签约手机号
		if (!output.getPayerPhoneNoList().contains(signinfo.getSignmobile())) {
			// setstatus为注销状态
			signinfo.setSigndate(new java.sql.Date(new java.util.Date().getTime()));
			try {
				UserpaytypsigninfoDAO.updateByExampleSelective(signinfo, example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}

			log.info(new StringBuilder().append("丰收e登录时注销卡").append("卡号：")
					.append(context.getString(signinfo.getSigncardnbr())).append("用户输入手机号：")
					.append(input.getPayerphoneno()).append("本地的手机号：").append(signinfo.getSignmobile())
					.append("核心的手机号为：").append(output.getPayerPhoneNoList()).toString());
			throw new PeException(DictErrors.NOT_OPEN_FOISONE_PAY);
		}
		// 如果本地签约手机号不等于用户输入手机号
		if (!signinfo.getSignmobile().equals(input.getPayerphoneno())) {
			throw new PeException(DictErrors.CARD_NOT_MATE_PHONE);
		}

	}
}
