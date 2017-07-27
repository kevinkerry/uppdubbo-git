package com.csii.upp.paygate.action.cardpwd;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddOrderInfo;
import com.csii.upp.dto.router.paym.ReqSGCT;
import com.csii.upp.paygate.action.PayGateAction;

public class SGCTAction extends PayGateAction {
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		context.setData(Dict.PAYER_ACCT_NBR, context.getData(Dict.ACCT_NBR));
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqAddOrderInfo(inputData));
		@SuppressWarnings("rawtypes")
		Map respMap = this.sendPaymenTransport(new ReqSGCT(inputData));
		String Content = (String) respMap.get(Dict.CONTENT);
		log.info("paygate收到的加签后的报文"+Content+"***********");
		context.setDataMap(respMap);
	}
				
	
}
