package com.csii.upp.paygate.action.test;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.test.ReqTest;
import com.csii.upp.paygate.action.PayGateAction;

public class QueryPayerName extends PayGateAction {
//生产测试
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.MERCHANT_ID, "310020151012160000");
		InputPaygateData input = new InputPaygateData(context.getDataMap());
	    @SuppressWarnings("rawtypes")
		Map resultMap = this.sendPaymenTransport(new ReqTest(input));
	    String PayerAcctName=(String) resultMap.get(Dict.PAYER_ACCT_NAME);
	    String TransAmt=(String) resultMap.get(Dict.TRANS_AMT);
	    if (!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setDataMap(resultMap);
			context.setState(1);
			return;
	    }
	    context.setData(Dict.PAYER_ACCT_NAME, PayerAcctName);
	    context.setData(Dict.TRANS_AMT, TransAmt);
		
		
	}

}
	