package com.csii.upp.paygate.action.foison;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.validation.ValidationException;
import com.csii.upp.constant.InnerCardFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCardFlag;
import com.csii.upp.dto.router.paym.ReqQueryCardInfo;
import com.csii.upp.dto.router.paym.ReqQuerySignInfo;
import com.csii.upp.paygate.action.PayGateAction;
/**
 * 丰收E支付  注销
 * @author qgs
 *
 */
public class CancelAccountLoginAction extends PayGateAction {
	private boolean ignoreCase;

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@Override
	public void execute(Context context) throws PeException {
		// 先验证图形验证码
		String token = (String) context.getSessionAttribute("_ImgTokenName");
		String imageTokenId = (String) context.getData("_vTokenName");

		if (token == null || imageTokenId == null) {
			context.setState(1);
			throw new ValidationException("validation.input_mistake_token");
		}

		if (this.ignoreCase) {
			if (!imageTokenId.equalsIgnoreCase(token)) {
				context.setState(1);
				throw new ValidationException("validation.input_mistake_token");
			}
		} else {
			if (!imageTokenId.equals(token)) {
				context.setState(1);
				throw new ValidationException("validation.input_mistake_token");
			}
		}
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map result = this.sendPaymenTransport(new ReqQueryCardFlag(inputData));
		if(!result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(result);
			context.setState(1);
			return;
		}
		String cardFlag=(String) result.get(Dict.INNER_CARD_FLAG);
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlag)){
			inputData.setPaytypcd(PayTypCd.INTEL);
		}else{
			inputData.setPaytypcd(PayTypCd.FOSION);
		}
		@SuppressWarnings("rawtypes")
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		if(!resultMap.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(resultMap);
			context.setState(1);
			return;
		}
		String signStatus = (String) resultMap.get(Dict.SIGN_STATUS);
		String signphone = (String) resultMap.get(Dict.PAYER_PHONE_NO);
		if (!signphone.equals(inputData.getPayerphoneno())) {
			context.setState(1);
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		
		if (SignStatus.CANCEL.equals(signStatus)) {
				context.setState(1);
				throw new PeException(DictErrors.SIGN_STATUS_ERROR,new Object[]{resultMap.get(Dict.SIGN_STATUS).equals(SignStatus.CANCEL)?Dict.CANCEL:Dict.FROZEN});
		
		}
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlag)){
			context.setData(Dict.PAY_TYP_CD, PayTypCd.INTEL);
			context.setDataMap(resultMap);
			return;
		}
		
		// 用户输入的和核心手机号判断
		Map hostMap = this.sendPaymenTransport(new ReqQueryCardInfo(inputData));
		this.validateCardStatusInfo(hostMap);
		if(!hostMap.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(hostMap);
			context.setState(1);
			return;
		}
		context.setDataMap(hostMap);
		context.setDataMap(resultMap);
		
	}

}
