package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.unionpay.RespSendMessage;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 
 * 
 * @author zgb
 * 
 */
public class SendMessageAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		RespSendMessage resp = (RespSendMessage) this.getUnionPayService().sendMessage(input);
		if(resp.isReceived()){
			context.setData(Dict.SMS_INNER_FUND_TRANS_NBR, input.getInnerfundtransnbr());
			context.setData(Dict.SEND_UNION_PAY_TIME, input.getSendUnionPayTime());
		}
	}
}
