package com.csii.upp.paygate.action.foison;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.validation.ValidationException;
import com.csii.upp.constant.CardTypCd;
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
public class ThawAcctInfoAction extends PayGateAction {
	private boolean ignoreCase;

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
// 先验证图形验证码
		String token = (String) context.getSessionAttribute("_ImgTokenName");
		String imageTokenId = (String) context.getData("_vTokenName");
		
		if (token == null || imageTokenId == null) {
			context.setState(10);
			throw new ValidationException("validation.input_mistake_token");
		}
		
		if (this.ignoreCase) {
			if (!imageTokenId.equalsIgnoreCase(token)) {
				context.setState(10);
				throw new ValidationException("validation.input_mistake_token");
			}
		}else{
			if (!imageTokenId.equals(token)) {
				context.setState(10);
				throw new ValidationException("validation.input_mistake_token");
			}
		}

		// 组装查询卡信息报文
		InputPaygateData inputDataForFlag = new InputPaygateData(context.getDataMap());
		
		//查询卡标识——它行,本行
		@SuppressWarnings("rawtypes")
		Map cardFlagMap = this.sendPaymenTransport(new ReqQueryCardFlag(
				inputDataForFlag));		
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlagMap.get(Dict.INNER_CARD_FLAG)))			
			context.setData(Dict.PAY_TYP_CD, PayTypCd.INTEL);
		else
			context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());

		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		if (!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(10);
			context.setDataMap(resultMap);
			return;
		}
		String signStatus = (String) resultMap.get(Dict.SIGN_STATUS);
		String signphone = (String) resultMap.get(Dict.PAYER_PHONE_NO);
		String PayerCardTypCd = (String) resultMap.get(Dict.PAYER_CARD_TYP_CD);
		if (!SignStatus.FROZEN.equals(signStatus)) {			
			// 状态不为冻结，不能进行解冻操作
			context.setState(10);
			throw new PeException(DictErrors.STATEISNOTFROZEN);
		}
		// 本地与输入
		if (!resultMap.get(Dict.PAYER_PHONE_NO).equals(inputData.getPayerphoneno())) {
			context.setState(10);
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlagMap.get(Dict.INNER_CARD_FLAG)))	{
        	context.setDataMap(resultMap);
        	context.setState(2);
        	return;
		}
		
		Map hostMap = this.sendPaymenTransport(new ReqQueryCardInfo(inputData));
		this.validateCardStatusInfo(hostMap);
		if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
			context.setState(10);
			context.setDataMap(hostMap);
			return;
		}
		List HPayerPhoneNoList = (List) hostMap.get(Dict.PAYER_PHONE_NO_LIST);
		// 核心与输入的不匹配
		if (!HPayerPhoneNoList.contains(inputData.getPayerphoneno())) {
			context.setState(10);
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		// 核心与本地不匹配
		if (!HPayerPhoneNoList.contains(signphone)) {
			context.setState(10);
			throw new PeException(DictErrors.CARD_NOT_MATE_PHONE);
		}
		
		if (CardTypCd.DEBIT.equals(PayerCardTypCd)) {
			context.setState(0);
		} else if (CardTypCd.CREDIT.equals(PayerCardTypCd)) {
			context.setState(1);
		} else {
			throw new PeException(DictErrors.CARD_TYPE_NOT_EXIST);
		}
		context.setDataMap(hostMap);
		context.setDataMap(resultMap);
		return;
	}

}
