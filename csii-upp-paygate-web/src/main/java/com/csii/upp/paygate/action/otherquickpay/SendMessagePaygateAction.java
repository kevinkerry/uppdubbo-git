package com.csii.upp.paygate.action.otherquickpay;

import java.sql.Timestamp;
import java.util.Map;

import com.csii.pe.channel.http.LocalServletContext;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqSendMessage;
import com.csii.upp.paygate.action.PayGateAction;

public class SendMessagePaygateAction extends PayGateAction {

	private static String SMSGENTOKENTIMESTAMP = "_SMSGenTokenTimestamp";
	private long limitMillisecond = 30000;

	@SuppressWarnings("rawtypes") public void execute(Context context) throws PeException {

		LocalServletContext servletContext = (LocalServletContext) context;
		Timestamp lastTime = (Timestamp) servletContext.getSessionAttribute(SMSGENTOKENTIMESTAMP);
		if(lastTime != null){
			long period = servletContext.getTimestamp().getTime() - lastTime.getTime();
			if(period <= limitMillisecond){
				throw new PeException("两次发送短信时间间隔过短");
			}
		}
		context.setData(Dict.PAYER_ACCT_NBR, context.getString(Dict.PAYER_ACCT_NBR));
		context.setData(Dict.PAYER_PHONE_NO, context.getString(Dict.PAYER_PHONE_NO));

		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqSendMessage(inputData));
//		Map<String,String> resultMap = new HashMap<String, String>();
//		resultMap.put("SendUnionPayTime", DateUtil.DATETIME_FORMATTER1.toString());
//		resultMap.put("SmsInnerFundTransNbr", "12345678");
		context.setData("json", resultMap);
		servletContext.setSessionAttribute(SMSGENTOKENTIMESTAMP, servletContext.getTimestamp());
	}

}
