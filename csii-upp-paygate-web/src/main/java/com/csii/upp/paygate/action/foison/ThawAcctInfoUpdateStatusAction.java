package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqUnHoldAcctStatus;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class ThawAcctInfoUpdateStatusAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		if(StringUtil.isStringEmpty(context.getString(Dict.PAY_TYP_CD))){
			context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		}
		context.setData(Dict.TRANS_TYP_CD,PaymTransCode.UnHoldAcctStatus);
		@SuppressWarnings("rawtypes")
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
			context.setState(1);
			context.setData("state", 0);
			context.setDataMap(smsInfo);
			return;
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		@SuppressWarnings("rawtypes")
		Map data = this.sendPaymenTransport(new ReqUnHoldAcctStatus(inputData));
		if(!ResponseCode.SUCCESS.equals(data.get(Dict.RESP_CODE))){
			context.setState(1);
		}
		context.setDataMap(data);
		return;
	}
}