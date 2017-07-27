package com.csii.upp.paygate.action.common;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryAuthSingleTrans1;
import com.csii.upp.paygate.action.PayGateAction;

public class QueryAuthTransAction extends PayGateAction {
	protected static Log log = LogFactory.getLog(QueryAuthTransAction.class);

	@Override
	public void execute(Context context) throws PeException {

		
		InputPaygateData inputData = new InputPaygateData();
		String transdate=context.getString(Dict.MERCHANT_ID);
	     inputData.setAcctName(transdate);
		Map resultMap = this.sendPaymenTransport(new ReqQueryAuthSingleTrans1(inputData));
		
		String Content = (String) resultMap.get(Dict.CONTENT);
		log.info("paygate收到的加签后的报文"+Content+"***********");
		context.setDataMap(resultMap);
		return;

	}

}
