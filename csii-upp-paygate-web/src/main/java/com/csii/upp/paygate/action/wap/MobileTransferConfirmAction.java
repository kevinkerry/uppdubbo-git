package com.csii.upp.paygate.action.wap;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

/**
 * 卡密支付数据请求
 * 
 * @author gaoqi
 *
 */
public class MobileTransferConfirmAction extends PayGateAction {
	public static final int PASSWORDLENGTH = 6;

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
			context.setData(Dict.FS_PAYER_PHONE_NO,
			context.getData(Dict.PAYER_PHONE_NO));
			context.setData("state", 0);
			return;
		}
		context.setData(Dict.PAY_TYP_CD, PayTypCd.CARDPWD);
		if (CardTypCd.DEBIT.equals(context.getString(Dict.PAYER_CARD_TYP_CD))) {
			if(StringUtil.isStringEmpty(context.getString(Dict.PASSWORD))){
				context.setState(99999);
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PASSWORD});
			}
			// 密码控件
			if(StringUtil.isObjectEmpty(context.getSessionAttribute("Number"))){
				context.setState(99999);
				throw new PeException(DictErrors.PIN_INPUT_TIMEOUT);
			}
			context.setData(Dict.PAYER_CARD_PWD, this.convertPassword(
					context.getString(Dict.PASSWORD),
				(Map<String, Character>) context.getSessionAttribute("Number")));
		}
		if (CardTypCd.CREDIT.equals(context.getString(Dict.PAYER_CARD_TYP_CD))) {
			if(!StringUtil.isStringEmpty(context.getString(Dict.CARD_VALIDY))){
				context.setData(Dict.PAYER_CARD_EXPIRED_DATE,context.getString(Dict.CARD_VALIDY)+context.getString(Dict.CARD_VALIDM));
				context.setData(Dict.PAYER_CARD_CVV2, context.getString(Dict.CV_V2));
			}
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		// 支付结果判断
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(0);
		} else {
			context.setDataMap(resultMap);
			context.setState(1);
		}

		// 商户异步通知没加
	}

}
