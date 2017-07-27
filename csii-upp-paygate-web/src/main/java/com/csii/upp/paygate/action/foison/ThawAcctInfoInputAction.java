package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqValidationInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class ThawAcctInfoInputAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		if(CardTypCd.CREDIT.equals(context.getData(Dict.PAYER_CARD_TYP_CD))){
			context.setData(Dict.PAYER_CARD_EXPIRED_DATE,context.getString(Dict.CARD_VALIDY)+context.getString(Dict.CARD_VALIDM));
		}
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))) {
			context.setState(1);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		//验证密码
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
			context.setState(1);
		}
		context.setDataMap(hostMap);
		return;
	}
}