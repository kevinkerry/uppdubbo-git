package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqXMTransLimtCX;
import com.csii.upp.paygate.action.PayGateAction;

public class XMLimitCXAction extends PayGateAction {

	public void execute(Context context) throws PeException {
		
		
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqXMTransLimtCX(inputData));
		
		context.setDataMap(resultMap);

	}		

}
