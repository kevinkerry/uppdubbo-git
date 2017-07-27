package com.csii.upp.paygate.action.foison;

import java.net.URLDecoder;
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

public class FsRegisterAndEpayConfirmAction extends PayGateAction {

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public void execute(Context context) throws PeException {
		validateTimeStampToken(context);
		
		if(StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))){
			context.setState(1);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PASSWORD});
		}
		String merurl1=Dict.MER_URL1.substring(0,4)+Dict.MER_URL1.substring(4).toUpperCase();
		if(!StringUtil.isStringEmpty(context.getString(merurl1))){
			context.setData(merurl1, URLDecoder.decode(context.getString(merurl1)));
		}
		// 手机短信验证码校验
		context.setData(Dict.TRANS_TYP_CD,"RegisterAndEpay");
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE))){
			context.setState(1);
			context.setData("state", 0);
			context.setDataMap(smsInfo);
			return;
		}
//		context.setData(Dict.TRANS_TYP_CD, PaymTransCode.AddSignInfo);
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		// context.setData(Dict.OPERATION_TYPE,
		// FosionState.OPERATION_TYPE_EXECUTE);
		// 继续验证密码
		if(CardTypCd.CREDIT.equals(context.getData(Dict.PAYER_CARD_TYP_CD))){
			String CardValidY = context.getString(Dict.CARD_VALIDY);
			String CardValidM= context.getString(Dict.CARD_VALIDM);
			context.setData(Dict.PAYER_CARD_EXPIRED_DATE,CardValidY+CardValidM);
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
		if (!ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			if (ResponseCode.NOTEXIT.equals(resultMap.get(Dict.RESP_CODE))) {
				// 可注册
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
		
		inputData.setPayercardtypcd((String) hostMap.get(Dict.PAYER_CARD_TYP_CD));
		inputData.setCustcifnbr((String) hostMap.get(Dict.CUST_CIF_NBR));
		inputData.setPayercardpwd(null);
		inputData.setPayeracctdeptnbr((String) smsInfo.get(Dict.PAYER_ACCT_DEPT_NBR));
		resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));

		// 支付结果判断
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(0);
		} else {
			context.setDataMap(resultMap);
			context.setData(Dict.RESP_CODE, "100333");
			context.setState(99999);
		}
		context.setDataMap(context.getDataMap());
		return;
	}

}
