package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryTransCtrl;
import com.csii.upp.paygate.action.PayGateAction;

public class UpdateCtrlInputAction extends PayGateAction {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Context context) throws PeException {
		//手机短信验证码校验
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
            context.setState(1);
            context.setData("state", 0);
            context.setDataMap(smsInfo);
            return;
		}
		InputPaygateData data = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqQueryTransCtrl(data));
		   String respCode = (String) resultMap.get(Dict.RESP_CODE);
		if (ResponseCode.SUCCESS.equals(respCode)) {
			Map dataMap = context.getDataMap();
	        dataMap.putAll(resultMap);
			context.setDataMap(resultMap);
			return;
		}
		context.setDataMap(resultMap);
		context.setState(1);
	}
}
