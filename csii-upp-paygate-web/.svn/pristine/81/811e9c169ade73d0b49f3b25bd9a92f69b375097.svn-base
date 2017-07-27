package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.TransId;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryReOtherPayStatus;
import com.csii.upp.paygate.action.PayGateAction;

public class QuickPayRegisterStatusAction extends PayGateAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		inputData.setTransId(TransId.OQP1);
		Map resultMap = this.sendPaymenTransport(new ReqQueryReOtherPayStatus(inputData));
		if ("1".equals(resultMap.get(Dict.OPEN_STATUS))) {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				throw new PeException(e);
			}
			resultMap = this.sendPaymenTransport(new ReqQueryReOtherPayStatus(inputData));
		}
		context.setData(Dict.OPEN_STATUS, resultMap.get(Dict.OPEN_STATUS));
		if ("2".equals(resultMap.get(Dict.OPEN_STATUS))) {
			context.setState(0);
		} else {
			resultMap.put(Dict.RESP_MESSAGE,"开通失败");
			context.setDataMap(resultMap);
			context.setState(99999);
		}
	}
}
