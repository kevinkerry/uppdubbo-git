/**
 * 
 */
package com.csii.upp.paygate.action.cyber;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransId;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou 他行企业网银支付类
 *
 */
public class OtherEnterBankAction extends PayGateAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAY_TYP_CD, PayTypCd.OTHCYBER);
		context.setData(Dict.TRANS_ID, TransId.OP01);
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		inputData.setFrontCallBackUrl("CLPA.do");
		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(0);
			context.setData(Dict.RETURN_FORM, StringUtil.parseObjectToString(resultMap.get(Dict.RETURN_FORM)));
		} else {
			context.setDataMap(resultMap);
			context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_FAIL);
			context.setState(99999);
		}
	}

}
