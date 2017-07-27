package com.csii.upp.paygate.action.otherquickpay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryAcctOpenStatus;
import com.csii.upp.paygate.action.PayGateAction;


public class QueryAcctOpenStatusPaygateAction extends PayGateAction {

@SuppressWarnings({ "rawtypes" })
public void execute(Context context) throws PeException {
	
		context.setData(Dict.PAYER_ACCT_NBR, context.getString(Dict.PAYER_ACCT_NBR));
		context.setData(Dict.PAYER_PHONE_NO, context.getString(Dict.PAYER_PHONE_NO));
		//组装查询卡信息报文
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		inputData.setIsQueryOpenStatus(context.getString(Dict.IS_QUERY_OPEN_STATUS));
		Map resultMap=this.sendPaymenTransport(new ReqQueryAcctOpenStatus(inputData));
		context.setData("json", resultMap);
	}

}
