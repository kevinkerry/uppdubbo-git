package com.csii.upp.payment.action.mgmt;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespCheckCardPwd;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 验证密码接口
 * 
 * @author zgb
 *
 */
public class ValidationInfoAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		// 调核心验密接口 抛出特定的错误码
		input.setPayercardpwd(this.getPasswordService().encrypt(input.getPayercardpwd(), input.getChannelnbr()));
		queryCardType(input);
		RespCheckCardPwd output = (RespCheckCardPwd) getFDPSService().checkCardPwd(input);

		// 返回户名
		context.setData(Dict.PAYER_ACCT_NAME, output.getPayerAcctName());
		// 卡类型需要，有页面需要卡类型跳转
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		return;

	}
}