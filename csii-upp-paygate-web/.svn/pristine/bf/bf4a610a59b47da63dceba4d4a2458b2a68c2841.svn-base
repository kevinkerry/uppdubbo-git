package com.csii.upp.paygate.action.foison;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.validation.ValidationException;
import com.csii.upp.constant.DEPDBNBR;
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
 * 判断手机号是否匹配
 * 
 * @author gaoqi
 *
 */
public class QueryDetailLoginAction extends PayGateAction {
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
		context.setData(Dict.PAY_TYP_CD, 1);
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
			inputData.setPaytypcd(PayTypCd.INTEL);
		}else{
			inputData.setPaytypcd(PayTypCd.FOSION);
		}
		//查询本地签约信息
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		if(!resultMap.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(resultMap);
			context.setState(1);
			return;
		}
		// 判断状态是否为正常状态
		
		if (resultMap.get(Dict.SIGN_STATUS).equals(SignStatus.CANCEL)) {
			context.setState(1);
			throw new PeException(DictErrors.SIGN_STATUS_ERROR,new Object[]{resultMap.get(Dict.SIGN_STATUS).equals(SignStatus.CANCEL)?Dict.CANCEL:Dict.FROZEN});
		}
		// 判断手机号
		if (!resultMap.get(Dict.PAYER_PHONE_NO).equals(inputData.getPayerphoneno())) {
			context.setState(1);
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		//它行不校验卡信息
		if(InnerCardFlag.InnerCardFlag_other.equals(cardFlag)){
			context.setData(Dict.PAY_TYP_CD, PayTypCd.INTEL);
			context.setData(Dict.PAYER_ACCT_DEPT_NBR, DEPDBNBR.ZJRCU);
			context.setDataMap(resultMap);
			return;
		}
		// 判断核心手机号，本地签约收好，用户输入手机号三者是否匹配
		Map hostMap = this.sendPaymenTransport(new ReqQueryCardInfo(inputData));
		this.validateCardStatusInfo(hostMap);
		if(!hostMap.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(hostMap);
			context.setState(1);
			return;
		}
		@SuppressWarnings("rawtypes")
		List HPayerPhoneNoList = (List) hostMap.get(Dict.PAYER_PHONE_NO_LIST);
		// 核心与输入的不匹配
		if (!HPayerPhoneNoList.contains(inputData.getPayerphoneno())) {
			context.setState(1);
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		// 核心与本地不匹配
		if (!HPayerPhoneNoList.contains(resultMap.get(Dict.PAYER_PHONE_NO))) {
			context.setState(1);
			throw new PeException(DictErrors.CARD_NOT_MATE_PHONE);
		}	
		context.setDataMap(resultMap);
		context.setDataMap(hostMap);
		context.setData(Dict.PAYER_ACCT_NBR, hostMap.get(Dict.PAYER_ACCT_NBR));
		context.setData(Dict.PAYER_CARD_TYP_CD, hostMap.get(Dict.PAYER_CARD_TYP_CD));
		context.setData(Dict.CUST_CIF_NBR, hostMap.get(Dict.CUST_CIF_NBR));
		context.setData(Dict.PAYER_PHONE_NO, resultMap.get(Dict.PAYER_PHONE_NO));
		return;
	}

}
