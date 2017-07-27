package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqReturnTrans;
import com.csii.upp.paygate.action.PayGateAction;
/**
 * 退货
 * @author 徐锦
 *
 */
public class RefundAction extends PayGateAction {
	@SuppressWarnings("rawtypes")
	public void execute(Context context) throws PeException {
		context.setData(Dict.MER_SEQ_NBR, context.getString(Dict.MER_SEQ_NO));
		context.setData(Dict.MER_TRANS_DATE_TIME, context.getString(Dict.MER_DATE_TIME));
		context.setData(Dict.ORIG_MER_SEQ_NBR, context.getString(Dict.ORG_MER_SEQ_NO));
		context.setData(Dict.ORIG_MER_DATE, context.getString(Dict.ORG_MER_DATE));
		context.setData(Dict.ORIG_TRANS_AMT, context.getString(Dict.ORG_TRANS_AMT));
		context.setData(Dict.ORIG_SUB_MER_DATE, context.getString(Dict.ORG_SUB_MER_DATE));
		context.setData(Dict.ORIG_SUB_MER_SEQ_NO, context.getString(Dict.ORG_SUB_MER_SEQ_NO));

		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqReturnTrans(inputData));
		context.setDataMap(resultMap);
	}
}
