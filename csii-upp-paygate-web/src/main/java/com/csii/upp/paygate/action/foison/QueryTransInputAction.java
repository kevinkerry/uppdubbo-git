package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 
 */
public class QueryTransInputAction extends PayGateAction {
    public void execute(Context context) throws PeException { 
    	//手机短信验证码校验
    	context.setData(Dict.TRANS_TYP_CD, PaymTransCode.QuerySignInfoList);
    	Map smsInfo=this.validateSms(context);
    	if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
            context.setState(1);
            context.setData("state", 0);
            context.setDataMap(smsInfo);
            return;
		}
    }


}
