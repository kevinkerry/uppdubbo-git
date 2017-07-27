package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryBatchTransfer;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 批量转账交易结果查询
 * 
 * @author 朱本乐
 *
 */
public class QueryBatchTransferAction extends PayGateAction {
	
	@SuppressWarnings("rawtypes")
	public void execute(Context context) throws PeException {
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQueryBatchTransfer(inputData));
		context.setDataMap(resultMap);
	}
}
