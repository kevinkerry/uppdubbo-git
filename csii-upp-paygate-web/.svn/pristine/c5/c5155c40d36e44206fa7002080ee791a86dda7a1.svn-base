package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCopr;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 确认支付交易
 * 
 * @author gaoqi
 * 
 */
public class ConfimPayAction extends PayGateAction {
	
	@Override
	public void execute(Context context) throws PeException {
		//接收外接电商传来的字段
		context.setData(Dict.TRANS_ID, context.getString(Dict.TRANS_ID));
		context.setData(Dict.MER_NBR, context.getString(Dict.MERCHANT_ID));
		context.setData(Dict.MER_SEQ_NBR, context.getString(Dict.MER_SEQ_NO));
		context.setData(Dict.MER_TRANS_DATE_TIME, context.getString(Dict.MER_DATE_TIME));
		context.setData(Dict.SUB_MERCHANT_ID, context.getString(Dict.SUB_MERCHANT_ID));
		context.setData(Dict.ORIG_MER_SEQ_NBR, context.getString(Dict.ORG_MER_SEQ_NO));
		context.setData(Dict.ORIG_MER_DATE_TIME, context.getString(Dict.ORG_MER_DATE_TIME));
		context.setData(Dict.ORIG_SUB_MER_SEQ_NO, context.getString(Dict.ORG_SUB_MER_SEQ_NO));
		context.setData(Dict.ORIG_SUB_MER_DATE_TIME, context.getString(Dict.ORG_SUB_MER_DATE_TIME));
		context.setData(Dict.ORIG_TRANS_AMT, context.getString(Dict.ORG_TRANS_AMT));
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqCopr(inputData));
       context.setDataMap(resultMap);
		
	}
}