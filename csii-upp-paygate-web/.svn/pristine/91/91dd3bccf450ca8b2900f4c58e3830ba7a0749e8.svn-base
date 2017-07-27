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
import com.csii.upp.dto.router.paym.ReqAddSignInfo;
import com.csii.upp.dto.router.paym.ReqQuerySignInfo;
import com.csii.upp.dto.router.paym.ReqValidationInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class RegisterconfirmAction extends PayGateAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))) {
			context.setState(1);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		context.setData(Dict.PAYER_CARD_EXPIRED_DATE, context.getString(Dict.PAYER_CARD_EXPIRED_DATE));
		context.setData(Dict.TELLER_NO, context.getString(Dict.TELLER_NO));
		//context.setData(Dict.TRANS_TYP_CD, PaymTransCode.AddSignInfo);
		// context.setData(Dict.OPERATION_TYPE,
		// FosionState.OPERATION_TYPE_EXECUTE);
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
			context.setState(1);
			context.setData("state", 0);
			 context.setDataMap(smsInfo);
			return;
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
			context.setState(1);
			context.setDataMap(hostMap);
			return;
		}
		// 验证密码通过 查询数据库中是否有记录存在
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		// 返回不成功
		if (!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			if (ResponseCode.NOTEXIT.equals(resultMap.get(Dict.RESP_CODE))) {
				// 可注册
				inputData.setPayeracctname((String) hostMap.get(Dict.PAYER_ACCT_NAME));
				Map data = this.sendPaymenTransport(new ReqAddSignInfo(inputData));
				String respCode = (String) data.get(Dict.RESP_CODE);
				if (!ResponseCode.SUCCESS.equals(respCode)) {
					context.setState(1);
					context.setDataMap(data);
					return;
				}
				context.setState(0);
			} else {
				context.setState(1);
				throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
			}
		} else {
			// 000000情况
			if (SignStatus.CANCEL.equals(resultMap.get(Dict.SIGN_STATUS))) {
				inputData.setPayeracctname((String) hostMap.get(Dict.PAYER_ACCT_NAME));
				Map data = this.sendPaymenTransport(new ReqAddSignInfo(inputData));
				String respCode = (String) data.get(Dict.RESP_CODE);
				if (!ResponseCode.SUCCESS.equals(respCode)) {
					context.setState(1);
					context.setDataMap(data);
					return;
				}
				context.setState(0);
			} else {
				context.setState(1);
				throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
			}
		}
		context.setDataMap(context.getDataMap());
		
		
	}

}
