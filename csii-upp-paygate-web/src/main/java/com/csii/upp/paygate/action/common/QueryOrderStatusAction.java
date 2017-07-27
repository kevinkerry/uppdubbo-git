package com.csii.upp.paygate.action.common;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryOrderStatus;
import com.csii.upp.paygate.action.PayGateAction;

public class QueryOrderStatusAction extends PayGateAction {
	protected static Log log = LogFactory.getLog(QueryOrderStatusAction.class);

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQueryOrderStatus(inputData));
		if(PayStatus.PAY_STATUS_HAND.equals(resultMap.get(Dict.PAY_STATUS))){
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				throw new PeException(e);
			}
			resultMap = this.sendPaymenTransport(new ReqQueryOrderStatus(inputData));
		}
		context.setData(Dict.PAY_STATUS, resultMap.get(Dict.PAY_STATUS));
		if(PayStatus.PAY_STATUS_OK.equals(resultMap.get(Dict.PAY_STATUS))){
			context.setState(0);
		}else {
			context.setState(99999);
		}
	}

}
