package com.csii.upp.paygate.action.common;

import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.json.JSONObject;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqSendSms;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SMSAction extends PayGateAction  {

	public void execute(Context context) throws PeException {
		try {
			executeInternel(context);
		} catch (Exception e) {
			log.error("生成短信验证码异常！", e);
			context.setState(99999);
		} 
		finally {
			// 判断是否有返回数据
			if (StringUtil.isStringEmpty(context.getString(Dict.CONTENT))) {
				JSONObject json = new JSONObject(context.getDataMap());
				try {
					json.putOpt("RespCode", context.getData(Dict.RESP_CODE));
					json.putOpt("RespMessage", context.getData(Dict.RESP_MESSAGE));
					if (!json.has(Dict.RESP_CODE)) {
						json.putOpt("RespCode", "999999");
					}
					if (!json.has(Dict.RESP_MESSAGE)) {
						json.putOpt("RespMessage", "生成短信验证码异常");
					}
				} catch (Exception ee) {
					log.error("异常！", ee);
				}
				context.setData(Dict.CONTENT, json.toString());
			}
		}
	}
	
	public void executeInternel(Context context) throws PeException {
		String operType = (String) context.getData(Dict.OPERATION_TYPE);
		if ("0".equals(operType) || StringUtil.isStringEmpty(operType)) {			
			InputPaygateData inputData=new InputPaygateData(context.getDataMap());
			Map hostMap = this.sendPaymenTransport(new ReqSendSms(inputData));
			
			Map jsonMap=new HashMap();
			jsonMap.put("RespCode", hostMap.get(Dict.RESP_CODE));
			jsonMap.put("RespMessage", hostMap.get(Dict.RESP_MESSAGE));
			jsonMap.put("SmsSqenbr", hostMap.get(Dict.SMS_SQENBR));
			JSONObject json = new JSONObject(jsonMap);
			context.setData(Dict.CONTENT, json.toString());
			String respCode = (String) hostMap.get(Dict.RESP_CODE);

			if (ResponseCode.SUCCESS.equals(respCode)) {
				context.setState(0);
			} else {
				context.setState(99999);
				Map dataMap = context.getDataMap();
				dataMap.putAll(hostMap);
				context.setDataMap(dataMap);
				return;
			}

		} 
//		else {
//
//			log.info("清除验证码!contextMap" + context.getDataMap());
//			try {
//				log.info("当前sessionId：" + context.getSessionId());
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//			if (context instanceof SessionUpdatableContext) {
//				((SessionUpdatableContext) context)
//						.removeSessionAttribute(Dict.RESP_MESSAGE);
//			}
//		}

	}
}
