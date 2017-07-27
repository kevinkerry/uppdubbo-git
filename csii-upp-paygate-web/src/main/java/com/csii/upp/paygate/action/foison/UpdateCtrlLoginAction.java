package com.csii.upp.paygate.action.foison;

import java.util.List;
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
public class UpdateCtrlLoginAction extends PayGateAction {
	private boolean ignoreCase;

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		// 组装查询卡信息报文PayerPhoneNo
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		// 判断本行它行卡信息，确定支付方式
		Map result = this.sendPaymenTransport(new ReqQueryCardFlag(inputData));
		if(!result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(result);
			context.setState(1);
			return;
		}
		String cardFlag=(String) result.get(Dict.INNER_CARD_FLAG);
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlag)){
			context.setState(2);
			return;
		}
		// 查询本地卡 签约关系
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		String respCode = (String) resultMap.get(Dict.RESP_CODE);
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			context.setState(1);
			context.setDataMap(resultMap);
			return;
		}
		String signNbr = (String) resultMap.get(Dict.SIGN_NBR);
	    String signTypCd = (String) resultMap.get(Dict.SIGN_TYP_CD);
		context.setData(Dict.SIGN_NBR, signNbr);
		context.setData(Dict.SIGN_TYP_CD,signTypCd);
		String signStatus = (String) resultMap.get(Dict.SIGN_STATUS);
		if (!SignStatus.NORMA.equals(signStatus)){
			context.setState(1);
			throw new PeException(DictErrors.SIGN_STATUS_ERROR,new Object[]{resultMap.get(Dict.SIGN_STATUS).equals(SignStatus.CANCEL)?Dict.CANCEL:Dict.FROZEN});
		}
		if(!resultMap.get(Dict.PAYER_PHONE_NO).equals(inputData.getPayerphoneno())){
        	context.setState(1);
        	throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);	
        }
		
		// 查询核心卡 签约关系
		Map hostMap = this.sendPaymenTransport(new ReqQueryCardInfo(inputData));
		this.validateCardStatusInfo(hostMap);
		if (!ResponseCode.SUCCESS.equals((String) hostMap.get(Dict.RESP_CODE))) {
			context.setState(1);
			context.setDataMap(hostMap);
			return;
		}
		//判断核心手机号，本地数据库手机号，输入手机号三者是否匹配
        List PayerPhoneNoList = (List) hostMap.get(Dict.PAYER_PHONE_NO_LIST);
        if(!PayerPhoneNoList.contains(inputData.getPayerphoneno())){
        	context.setState(1);
        	context.setDataMap(hostMap);
        	throw new PeException(DictErrors.NOT_OPEN_FOISONE_PAY);
        }
        if(!PayerPhoneNoList.contains(resultMap.get(Dict.PAYER_PHONE_NO))){
        	context.setState(1);
        	context.setDataMap(hostMap);
        	throw new PeException(DictErrors.CARD_NOT_MATE_PHONE);
        }
        Map dataMap = context.getDataMap();
		dataMap.putAll(resultMap);
		context.setDataMap(hostMap);
		context.setData(Dict.CUST_CIF_NBR, hostMap.get(Dict.CUST_CIF_NBR));
	}

}
