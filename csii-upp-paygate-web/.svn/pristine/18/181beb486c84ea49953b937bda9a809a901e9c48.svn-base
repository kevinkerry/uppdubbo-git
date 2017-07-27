package com.csii.upp.paygate.action.common;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCustomAuthen;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 客户鉴权(POC测试使用)
 * 
 * @author 颜祎名
 *
 */
public class CustomAuthenAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {

		// 组装客户鉴权报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		@SuppressWarnings({ "rawtypes", "unused" })
		Map resultMap = this.sendPaymenTransport(new ReqCustomAuthen(inputData));
		context.setDataMap(resultMap);
	}

}
