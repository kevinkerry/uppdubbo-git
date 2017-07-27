package com.csii.upp.paygate.action.foison;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
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
import com.csii.upp.util.StringUtil;
/**
 * 丰收E支付  注销
 * @author qgs
 *
 */
public class LogoutConfrimAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		
		if(StringUtil.isStringEmpty(context.getString(Dict.PAY_TYP_CD))){
			context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		}
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map result = this.sendPaymenTransport(new ReqQueryCardFlag(inputData));
		if(!result.get(Dict.RESP_CODE).equals(ResponseCode.SUCCESS)){
			context.setDataMap(result);
			context.setState(1);
			return;
		}
		if(InnerCardFlag.InnerCardFlag_other.equals(result.get(Dict.INNER_CARD_FLAG))){
			context.setData(Dict.PAY_TYP_CD, PayTypCd.INTEL);
			context.setData(Dict.PAYER_ACCT_DEPT_NBR, DEPDBNBR.ZJRCU);
			context.setDataMap(result);
			return;
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
		String PayerCardTypCd = (String) resultMap.get(Dict.PAYER_CARD_TYP_CD);
		if (!SignStatus.NORMA.equals(signStatus)) {
			context.setState(1);
			throw new PeException(DictErrors.SIGN_STATUS_ERROR,new Object[]{resultMap.get(Dict.SIGN_STATUS).equals(SignStatus.CANCEL)?Dict.CANCEL:Dict.FROZEN});
		}
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
		if (!HPayerPhoneNoList.contains(signphone)) {
			context.setState(1);
			throw new PeException(DictErrors.CARD_NOT_MATE_PHONE);
		}	
		//本地与输入
		if (!resultMap.get(Dict.PAYER_PHONE_NO).equals(inputData.getPayerphoneno())) {
			context.setState(1);
			throw new PeException(DictErrors.ACCOUNT_PHONE_ERROR);
		}
		
		context.setDataMap(hostMap);
		context.setDataMap(resultMap);
	}

}
