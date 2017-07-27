package com.csii.upp.fundprocess.action.callback;

import java.util.Date;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.UnionPayService;
import com.csii.upp.util.DateUtil;

/**
 * 第三方支付回调测试用
 * 
 * @author 徐锦
 *
 */
public class TestCallBackAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		log.debug(context.getDataMap());
		String orderId=context.getString("orderId");//匹配原交易用，交易ID
		Date txnTime = DateUtil.DateTimeFormat_To_Date(context.getData("txnTime"));//匹配原交易用，交易时间
		InputFundData input = new InputFundData(context.getDataMap());
		input.setFundchannelcode(FundChannelCode.UNIONPAY);
//		((UnionPayService)getService("unionpay")).collectForAnother(input);
		((UnionPayService)getService("unionpay")).download(input);
	}

}
