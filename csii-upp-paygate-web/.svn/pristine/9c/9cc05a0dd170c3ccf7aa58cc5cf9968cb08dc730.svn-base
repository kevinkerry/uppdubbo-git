package com.csii.upp.paygate.action.wap;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQrCodeCreateOrder;
import com.csii.upp.paygate.action.PayGateAction;

public class QrcoCodePayAction extends PayGateAction{

	@Override
	public void execute(Context context) throws PeException {
		
		String str = "http://ww.7har.com/paygate2/qrCode?TransName=QREM&MerchantName="+context.getString(Dict.MERCHANT_NAME)+"&TransAmt="+context.getString(Dict.TRANS_AMT);
		context.setData("url",str);
		if(null==context.getString(Dict.MERCHANT_ID)||"".equals(context.getString(Dict.MERCHANT_ID))){
			context.setData(Dict.MERCHANT_ID, "010020150521152412");	//默认使用一个微信商户
		}
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendfundTransport(new ReqQrCodeCreateOrder(inputData));
		context.setData("json", resultMap);
	}

}
