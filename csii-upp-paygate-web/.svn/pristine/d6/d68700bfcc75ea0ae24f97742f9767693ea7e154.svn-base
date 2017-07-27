package com.csii.upp.paygate.action.cardpwd;

import java.net.URLDecoder;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

/**
 * 卡密支付数据请求
 * @author fgq
 *
 */
public class CardPwdPayAction extends PayGateAction{

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void execute(Context context) throws PeException {
		validateTimeStampToken(context);
		
		context.setData(Dict.TRANS_TYP_CD, PaymTransCode.PayTrans);
		String merurl1=Dict.MER_URL1.substring(0,4)+Dict.MER_URL1.substring(4).toUpperCase();
		if(!StringUtil.isStringEmpty(context.getString(merurl1))){
			context.setData(merurl1, URLDecoder.decode(context.getString(merurl1)));
		}
		if (CardTypCd.DEBIT.equals(context.getString(Dict.PAYER_CARD_TYP_CD))) {
			if(StringUtil.isStringEmpty(context.getString(Dict.PAYER_CARD_PWD))){
				context.setState(99999);
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PASSWORD});
			}
		}
		if(!StringUtil.isStringEmpty(context.getString(Dict.CARD_VALIDY))){
			context.setData(Dict.PAYER_CARD_EXPIRED_DATE,context.getString(Dict.CARD_VALIDY)+context.getString(Dict.CARD_VALIDM));
			context.setData(Dict.PAYER_CARD_CVV2, context.getString(Dict.CV_V2));
		}
		
		Map smsInfo=this.validateSms(context);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE)))
		{
            context.setState(99999);
            context.setDataMap(smsInfo);
            return;
		}
		context.setData(Dict.PAY_TYP_CD, PayTypCd.CARDPWD);
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		
		
		context.setDataMap(resultMap);
		
		//支付结果判断
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE)))
		{
			context.setState(1);
		}
		else
		{
			context.setDataMap(resultMap);
			context.setState(99999);
		}
	}

}
