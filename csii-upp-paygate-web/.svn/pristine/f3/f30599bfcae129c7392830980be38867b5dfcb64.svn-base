package com.csii.upp.paygate.action.common;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqValidationCardInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class InformationVerifiAction extends PayGateAction {
	protected static Log log = LogFactory.getLog(InformationVerifiAction.class);
	
	@Override
	public void execute(Context context) throws PeException {
		
		context.setData(Dict.MER_DATE_TIME,StringUtil.toStringAndTrim(context.getData(Dict.MER_DATE_TIME)));
		context.setData(Dict.ACCT_NAME,StringUtil.toStringAndTrim(context.getData(Dict.NAME)));
		context.setData(Dict.PAYERACCTNBR,StringUtil.toStringAndTrim(context.getData(Dict.ACCOUNT)));
		context.setData(Dict.PAYER_CARD_TYP_CD,StringUtil.toStringAndTrim(context.getData(Dict.PAY_ACCT_TYPE)));
		context.setData(Dict.PAYER_ID_TYP_CD,StringUtil.toStringAndTrim(context.getData(Dict.ID_TYPE)));
		context.setData(Dict.PAYER_ID_NBR,StringUtil.toStringAndTrim(context.getData(Dict.ID_NO)));
		context.setData(Dict.PAYER_PHONE_NO,StringUtil.toStringAndTrim(context.getData(Dict.MOBILE_NO)));
		
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());

		Map resultMap = this.sendPaymenTransport(new ReqValidationCardInfo(inputData));
		context.setDataMap(resultMap);
		return;
		
		
	}

}
