package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
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

public class FreezeInputAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))) {
			context.setState(1);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());

		// 查询本地卡 签约关系
		@SuppressWarnings("rawtypes")
		Map hostMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		if(!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))){
			context.setState(1);
			context.setDataMap(hostMap);
			return;
		}
	
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(
				inputData));

		String respCode = (String) resultMap.get(Dict.RESP_CODE);
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			context.setState(1);
			context.setDataMap(resultMap);
			return;
		}
		String signStatus = (String) resultMap.get(Dict.SIGN_STATUS);
		if (!SignStatus.NORMA.equals(signStatus)){
			context.setState(1);
			throw new PeException(DictErrors.SIGN_STATUS_ERROR,new Object[]{resultMap.get(Dict.SIGN_STATUS).equals(SignStatus.CANCEL)?Dict.CANCEL:Dict.FROZEN});
		}
		context.setDataMap(resultMap);
	}

}
