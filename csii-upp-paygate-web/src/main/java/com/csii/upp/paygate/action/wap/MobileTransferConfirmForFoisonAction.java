package com.csii.upp.paygate.action.wap;


import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCancelSignInfo;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 手机丰收E支付数据请求
 * @author wangyong
 *
 */
public class MobileTransferConfirmForFoisonAction extends PayGateAction{

	
	@Override
	public void execute(Context context) throws PeException {
		if(!validateTimeStampTokenMobile(context)){
			return;
		}
		
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		context.setData(Dict.PAYER_PHONE_NO, context.getString(Dict.PAYER_PHONE_NO));
		context.setData(Dict.PAYER_ACCT_NBR,context.getString(Dict.PAYER_ACCT_NBR).substring(0,context.getString(Dict.PAYER_ACCT_NBR).length()-6));
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		//短信验证
		Map smsInfo=this.validateSms(context);
		//验证卡状态信息
		this.validateCardStatusInfo(smsInfo);
		if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE)))
		{
            context.setState(99999);
            context.setData("state", 0);
            context.setDataMap(smsInfo);
    		context.setData(Dict.FS_PAYER_PHONE_NO, context.getData(Dict.PAYER_PHONE_NO));
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
		inputData.setPayercardtypcd((String)smsInfo.get(Dict.PAYER_CARD_TYP_CD));
		inputData.setCustcifnbr((String)smsInfo.get(Dict.CUST_CIF_NBR));
		inputData.setPayeracctdeptnbr((String)smsInfo.get(Dict.PAYER_ACCT_DEPT_NBR));
		Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
		
		//支付结果判断
		if(ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE)))
		{
			context.setState(0);
		}
		else
		{
			context.setDataMap(resultMap);
			context.setState(1);
		}
		//商户异步通知没加
	}
	

}
