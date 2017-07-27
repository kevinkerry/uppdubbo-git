package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQuerySignInfo;
import com.csii.upp.dto.router.paym.ReqValidationInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class ThawAcctconfirmAction extends PayGateAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))) {
			context.setState(1);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		//继续验证密码
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		if(!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))){
			context.setState(1);
			return;
		}
			//验证密码通过 查询数据库中是否有记录存在
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		if(!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))){
			context.setState(1);
			return;
		}
	}

}


