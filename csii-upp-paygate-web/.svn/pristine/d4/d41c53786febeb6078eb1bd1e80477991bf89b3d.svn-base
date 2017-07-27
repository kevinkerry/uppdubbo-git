package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCancelSignStatus;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class CancelSelectAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		if(StringUtil.isStringEmpty(context.getString(Dict.PAY_TYP_CD))){
			context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		}
		context.setData(Dict.TRANS_TYP_CD, PaymTransCode.CancelSignStatus);
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE)))
		{
			context.setState(1);                                                                                                                                                                                                                                                                                                                             context.setState(1);
            context.setDataMap(smsInfo);
            context.setData("state", 0);
            return;
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqCancelSignStatus(inputData));
		if(!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))){
			context.setState(1);
		}
		context.setDataMap(hostMap);
		return;
	}
	
}
