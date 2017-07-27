package com.csii.upp.paygate.action.wap;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddSignInfo;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.dto.router.paym.ReqQuerySignInfo;
import com.csii.upp.dto.router.paym.ReqValidationInfo;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class RegisterMobileConfirmAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		if(!validateTimeStampTokenMobile(context)){
			return;
		}
		
		// 短信验证
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
			context.setState(99999);
			context.setDataMap(smsInfo);
			context.setData(Dict.FS_PAYER_PHONE_NO, context.getData(Dict.PAYER_PHONE_NO));
			return;
		}
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		if (CardTypCd.CREDIT.equals(context.getData(Dict.PAYER_CARD_TYP_CD))) {
			context.setData(Dict.PAYER_CARD_EXPIRED_DATE,
					context.getString(Dict.CARD_VALIDY)+context.getData(Dict.CARD_VALIDM));
		}
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))) {
			context.setState(99999);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		// 密码控件
		context.setData(Dict.PAYER_CARD_PWD, this.convertPassword(context.getString(Dict.PAYER_CARD_PWD),
				(Map<String, Character>) context.getSessionAttribute("Number")));
		// 继续验证密码
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map hostMap = this.sendPaymenTransport(new ReqValidationInfo(inputData));
		if (!ResponseCode.SUCCESS.equals(hostMap.get(Dict.RESP_CODE))) {
			context.setDataMap(hostMap);
			context.setState(99999);
			return;
		}
		// 验证密码通过 查询数据库中是否有记录存在
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfo(inputData));
		if (!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			if (ResponseCode.NOTEXIT.equals(resultMap.get(Dict.RESP_CODE))) {
				// 可注册
				Map data = this.sendPaymenTransport(new ReqAddSignInfo(inputData));
				String respCode = (String) data.get(Dict.RESP_CODE);
				if (!ResponseCode.SUCCESS.equals(respCode)) {
					context.setDataMap(data);
					context.setState(99999);
					return;
				}
				context.setState(0);
			} else {
				context.setState(99999);
				throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
			}
		} else {
			// 000000情况
			if (SignStatus.CANCEL.equals(resultMap.get(Dict.SIGN_STATUS))) {
				Map data = this.sendPaymenTransport(new ReqAddSignInfo(inputData));
				String respCode = (String) data.get(Dict.RESP_CODE);
				if (!ResponseCode.SUCCESS.equals(respCode)) {
					context.setDataMap(data);
					context.setState(99999);
					return;
				}
				context.setState(0);
			} else {
				context.setState(99999);
				throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
			}
		}
		inputData.setPayercardtypcd((String) hostMap.get(Dict.PAYER_CARD_TYP_CD));
		inputData.setCustcifnbr((String) hostMap.get(Dict.CUST_CIF_NBR));
		inputData.setPayeracctdeptnbr((String) smsInfo.get(Dict.PAYER_ACCT_DEPT_NBR));
		inputData.setPayercardpwd(null);
		resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));

		// 支付结果判断
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(0);
		} else {
			context.setDataMap(resultMap);
			context.setState(99999);
		}
		context.setDataMap(context.getDataMap());
		return;
	}

}
