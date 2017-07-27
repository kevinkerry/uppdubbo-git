package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqValidationInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class ValidationInfoAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))) {
			context.setState(99999);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());

		Map resultMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		context.setDataMap(resultMap);
		return;
	}
}