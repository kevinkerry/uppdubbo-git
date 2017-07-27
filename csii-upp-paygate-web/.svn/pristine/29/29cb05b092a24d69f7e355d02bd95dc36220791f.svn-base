package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCardFlag;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 判断本行它行卡
 * 
 * @author WY
 *
 */
public class QueryCardFlagAction extends PayGateAction {
	
	@SuppressWarnings("rawtypes")
	public void execute(Context context) throws PeException {
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map result = this.sendPaymenTransport(new ReqQueryCardFlag(inputData));
		if(!result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(result);
			context.setState(1);
			return;
		}
		context.setData("json", result);
	}
}
