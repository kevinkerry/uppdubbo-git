package com.csii.upp.paygate.action.common;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQuerySms;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;
/**
 * 商户查询短信接口
 * @author gaoqi
 *
 */

public class QuerySmsAction extends PayGateAction {
	protected static Log log = LogFactory.getLog(QuerySmsAction.class);

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAYER_PHONE_NO, StringUtil.toStringAndTrim(context.getData(Dict.PHONE_NO)));

		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQuerySms(inputData));
		String Content = (String) resultMap.get(Dict.CONTENT);
		log.info("paygate收到的加签后的报文"+Content+"***********");
		context.setDataMap(resultMap);
		return;

	}

}
