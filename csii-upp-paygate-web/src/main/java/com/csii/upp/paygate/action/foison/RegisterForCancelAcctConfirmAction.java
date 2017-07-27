package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqCancelSignInfo;
import com.csii.upp.paygate.action.PayGateAction;

public class RegisterForCancelAcctConfirmAction extends PayGateAction{

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		//组装查询卡信息报文
		InputPaygateData inputData=new InputPaygateData(context.getDataMap());
		
		//强制注销
		Map hostMap=this.sendPaymenTransport(new ReqCancelSignInfo (inputData));
		
		  String respCode = (String)hostMap.get(Dict.RESP_CODE);
          
	        if(ResponseCode.SUCCESS.equals(respCode)) {
	        	String payerCardTypCd = (String) hostMap.get(Dict.PAYER_CARD_TYP_CD);
	        	if (CardTypCd.DEBIT.equals(payerCardTypCd)) {
					context.setState(0);
				} else if (CardTypCd.CREDIT.equals(payerCardTypCd)) {
					context.setState(1);
				}
	        }else{
	        	
	        	context.setState(10);
	        }
	        context.setDataMap(hostMap);
	        return;
	}

}
