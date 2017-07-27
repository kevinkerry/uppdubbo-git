package com.csii.upp.paygate.action.cardpwd;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqSGPMTransfer;
import com.csii.upp.paygate.action.PayGateAction;

public class SGPMAction extends PayGateAction {
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAYER_ACCT_NBR, context.getData(Dict.ACCT_NBR));
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqSGPMTransfer(inputData));
		context.setDataMap(resultMap);
	}
				
	
}
