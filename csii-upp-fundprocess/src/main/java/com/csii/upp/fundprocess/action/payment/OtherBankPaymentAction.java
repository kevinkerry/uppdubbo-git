/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author lixinyou
 * @description 用于对payment提供他行网银支付交易
 *
 */
public class OtherBankPaymentAction extends PayOnlineAction {

	/* (non-Javadoc)
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		input.setCheckdataflag(FundChannelCode.UNIONPAY);
		context.setData(Dict.CHECK_DATA_FLAG, input.getCheckdataflag());
		RespSysHead anothertb = getUnionPayService().otherbankPayment(input);
		context.setData(Dict.RETURN_FORM, anothertb.getReturnHtml());
		//返回交易已受理
		context.setData(Dict.TRANS_STATUS, TransStatus.PROCESSING);
	}
}