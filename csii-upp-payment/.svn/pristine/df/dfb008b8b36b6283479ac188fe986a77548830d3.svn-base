package com.csii.upp.payment.action.query;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.fundprocess.RespSendMessage;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 他行快捷支付开通状态查询
 * 
 * @author zhubenle
 *
 */
public class SendMessagePaymentAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		RespSendMessage respSendMessage = (RespSendMessage) this.getFDPSService().sendMessage(input);
		context.setData(Dict.SMS_INNER_FUND_TRANS_NBR, respSendMessage.getSmsInnerFundTransNbr());
		context.setData(Dict.SEND_UNION_PAY_TIME, respSendMessage.getSendUnionPayTime());
	}
}
