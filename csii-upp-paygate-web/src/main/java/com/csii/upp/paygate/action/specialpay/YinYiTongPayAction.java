package com.csii.upp.paygate.action.specialpay;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqAddOrderInfo;
import com.csii.upp.dto.router.paym.ReqCancelSignInfo;
import com.csii.upp.dto.router.paym.ReqPayTrans;
import com.csii.upp.paygate.action.PayGateAction;

/**
* 银医通支付数据请求
* @auther fgq email:f_xust@163.com
* @version 创建时间：2016年10月28日 上午9:04:03
* 
*/
public class YinYiTongPayAction extends PayGateAction{

	@Override
	public void execute(Context context) throws PeException {
		try{
			
			context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
			context.setData(Dict.TRANS_TYP_CD, PaymTransCode.PayTrans);
		
			Map smsInfo=this.validateSms(context);
			this.validateCardStatusInfo(smsInfo);				
			InputPaygateData inputData=new InputPaygateData(context.getDataMap());		
			if(!ResponseCode.SUCCESS.equals(smsInfo.get(Dict.RESP_CODE)))
			{
				if (ResponseCode.PHONEANDCARDNOTMATCH.equals(smsInfo.get(Dict.RESP_CODE))
						||ResponseCode.CREDITCARDNOTEXIST.equals(smsInfo.get(Dict.RESP_CODE))
						||ResponseCode.DEBITCARDNOTEXIST.equals(smsInfo.get(Dict.RESP_CODE))) {
					Map data = this.sendPaymenTransport(new ReqCancelSignInfo(inputData));
					if(ResponseCode.SUCCESS.equals(data.get(Dict.RESP_CODE))){
						context.setData(Dict.CONTENT, "<Finance><Message><RespCode>100082</RespCode><RespMessage>输入手机号和预留手机号不匹配，签约关系已注销</RespMessage></Message></Finance>");
						return ;
					}
				}
				context.setData(Dict.CONTENT, "<Finance><Message><RespCode>"+smsInfo.get(Dict.RESP_CODE) +"</RespCode><RespMessage>"+smsInfo.get(Dict.RESP_MESSAGE) + "</RespMessage></Message></Finance>");
				return ;
			}
			context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
			inputData.setPayercardtypcd((String) smsInfo.get(Dict.PAYER_CARD_TYP_CD));
			inputData.setCustcifnbr((String) smsInfo.get(Dict.CUST_CIF_NBR));
			inputData.setPayeracctdeptnbr((String) smsInfo.get(Dict.PAYER_ACCT_DEPT_NBR));
			
			@SuppressWarnings({ "unused", "rawtypes" })
			Map orderMap = this.sendPaymenTransport(new ReqAddOrderInfo(inputData));
			if(!ResponseCode.SUCCESS.equals(orderMap.get(Dict.RESP_CODE))){
				
				context.setData(Dict.CONTENT, "<Finance><Message><RespCode>"+orderMap.get(Dict.RESP_CODE) +"</RespCode><RespMessage>"+orderMap.get(Dict.RESP_MESSAGE) + "</RespMessage></Message></Finance>");
				return ;
			}
			@SuppressWarnings("rawtypes")
			Map resultMap = this.sendPaymenTransport(new ReqPayTrans(inputData));
			context.setData(Dict.CONTENT, "<Finance><Message><RespCode>"+resultMap.get(Dict.RESP_CODE) +"</RespCode><RespMessage>"+resultMap.get(Dict.RESP_MESSAGE) + "</RespMessage></Message></Finance>");

		}catch (Exception e){
			context.setData(Dict.CONTENT, "<Finance><Message><RespCode>999999</RespCode><RespMessage>交易失败</RespMessage></Message></Finance>");
		}
		
	}
	
}
