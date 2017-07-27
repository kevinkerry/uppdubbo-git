package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCancelOrder;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 取消订单
 * @author qgs
 *
 */

public class CancelOrderAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		
		context.setData(Dict.ORIG_MER_SEQ_NBR, context.getString(Dict.ORG_MER_SEQ_NO));
		context.setData(Dict.ORIG_MER_DATE_TIME, context.getString(Dict.ORG_MER_DATE_TIME));
		context.setData(Dict.ORIG_TRANS_AMT, context.getString(Dict.ORG_TRANS_AMT));
		
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		@SuppressWarnings({ "rawtypes", "unused" })
		Map resultMap = this.sendPaymenTransport(new ReqCancelOrder(inputData));
		context.setDataMap(resultMap);
	
	}

}
	