package com.csii.upp.paygate.action.foison;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransId;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCancelSignInfo;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class FoisonEpayAction extends PayGateAction {

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public void execute(Context context) throws PeException {
		validateTimeStampToken(context);
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		context.setData(Dict.FS_PAYER_PHONE_NO, context.getData(Dict.PAYER_PHONE_NO));
		context.setData(Dict.TRANS_TYP_CD, PaymTransCode.PayTrans);
		context.setData(Dict.PAYER_ACCT_NBR,context.getString(Dict.PAYER_ACCT_NBR).substring(0,context.getString(Dict.PAYER_ACCT_NBR).length()-6));
		String merurl1=Dict.MER_URL1.substring(0,4)+Dict.MER_URL1.substring(4).toUpperCase();
		if(!StringUtil.isStringEmpty(context.getString(merurl1))){
			context.setData(merurl1, URLDecoder.decode(context.getString(merurl1)));
		}
		Map smsInfo=this.validateSms(context);
		//验证积分抵扣金额
		if(!StringUtil.isStringEmpty(context.getString(Dict.INTERAL_FLAG))&&
				InteralFlag.YES.equals(context.getString(Dict.INTERAL_FLAG))){
			BigDecimal transAmt = context.getBigDecimal(Dict.TRANS_AMT);
			BigDecimal AmtLimitThisTime = BigDecimal.valueOf(transAmt.doubleValue()*0.5).setScale(0, RoundingMode.HALF_UP);
			if(StringUtil.isObjectEmpty(AmtLimitThisTime)||
				context.getBigDecimal(Dict.AMT_THIS_TIME).compareTo(AmtLimitThisTime) > 0){
				context.setState(99999);
				throw new PeException(DictErrors.TRANSAMT_DISCORD);
			}
		}
		//验证卡状态信息
		this.validateCardStatusInfo(smsInfo);
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE)))
		{
            context.setState(99999);
            context.setDataMap(smsInfo);
			if (ResponseCode.PHONEANDCARDNOTMATCH.equals(smsInfo.get(Dict.RESP_CODE))
					||ResponseCode.CREDITCARDNOTEXIST.equals(smsInfo.get(Dict.RESP_CODE))
					||ResponseCode.DEBITCARDNOTEXIST.equals(smsInfo.get(Dict.RESP_CODE))) {
				Map data = this.sendPaymenTransport(new ReqCancelSignInfo(inputData));
				if(ResponseCode.SUCCESS.equals(data.get(Dict.RESP_CODE))){
					throw new PeException(DictErrors.PHONE_NOT_MATCH_CANCEL);
				}
			}
            return;
		}
		
		context.setData(Dict.TRANS_ID, TransId.FS11);
		inputData.setPayercardtypcd((String) smsInfo.get(Dict.PAYER_CARD_TYP_CD));
		inputData.setCustcifnbr((String) smsInfo.get(Dict.CUST_CIF_NBR));
		inputData.setPayeracctdeptnbr((String) smsInfo.get(Dict.PAYER_ACCT_DEPT_NBR));
		
		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));

		// 支付结果判断
		if (ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE))) {
			context.setState(0);
		} else {
			context.setDataMap(resultMap);
			context.setState(99999);
		}

	}

}
