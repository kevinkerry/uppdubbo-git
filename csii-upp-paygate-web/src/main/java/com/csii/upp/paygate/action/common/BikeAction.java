package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddOrderInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class BikeAction extends PayGateAction {
	public void execute(Context context) throws PeException {
		String merchantDateTime = StringUtil.parseObjectToString(context.getData(Dict.MER_DATE_TIME));
		context.setData(Dict.MER_DATE, DateUtil.Date_To_DateTimeFormat(
				DateUtil.DateTimeFormat_To_Date(merchantDateTime),
				DateFormatCode.DATE_FORMATTER1));
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqAddOrderInfo(inputData));
		context.setData(Dict.MERCHANT_NAME,resultMap.get(Dict.MERCHANT_NAME));
		context.setData(Dict.MER_PLATFORM_NBR,resultMap.get(Dict.MER_PLATFORM_NBR));
		context.setData(Dict.PAY_TYPE_CD_STR, resultMap.get(Dict.PAY_TYPE_CD_STR));
		this.valRespStatus(resultMap);
		context.setState(0);
	}
}
