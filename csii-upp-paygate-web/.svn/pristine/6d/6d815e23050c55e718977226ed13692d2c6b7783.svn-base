package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQuerySignInfo;
import com.csii.upp.dto.router.paym.ReqValidationInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class RegisterInputdataAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		if(CardTypCd.CREDIT.equals(context.getData(Dict.PAYER_CARD_TYP_CD))){
			String CardValidY = context.getString(Dict.CARD_VALIDY);
			String CardValidM= context.getString(Dict.CARD_VALIDM);
			if(!StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_EXPIRED_DATE))){
				context.setData(Dict.PAYER_CARD_EXPIRED_DATE,context.getString(Dict.PAYER_CARD_EXPIRED_DATE));
			}else{
				
				context.setData(Dict.PAYER_CARD_EXPIRED_DATE,CardValidY+CardValidM);
			}
		}
		
		/*context.setData(Dict.PAYER_CARD_CVV2, "1213");*/
		//验证密码
		//验证密码返回户名
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		
		if(!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))){
			context.setState(1);
			this.valRespStatus(hostMap);
		}else{
		@SuppressWarnings("rawtypes")
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		if(!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))){
			if (ResponseCode.NOTEXIT.equals(resultMap.get(Dict.RESP_CODE))) {
				context.setState(0);
			}else{
				context.setState(1);
				throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
			}
		} else {
				// 000000情况
				if (SignStatus.CANCEL.equals(resultMap.get(Dict.SIGN_STATUS))) {
					context.setState(0);
				} else {
					context.setState(1);
					throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
				}
			}
		}
		context.setDataMap(context.getDataMap());
		
	}
}