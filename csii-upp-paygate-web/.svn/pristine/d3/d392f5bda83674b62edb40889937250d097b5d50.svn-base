package com.csii.upp.paygate.action.wap;

import java.net.URLEncoder;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.paygate.action.PayGateAction;

public class CheckAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		String url = "http://ww.7har.com/paygate2/qrCode?TransName=QREM&MerchantName="+context.getString(Dict.MERCHANT_NAME)+"&TransAmt="+context.getString(Dict.TRANS_AMT);
		String str1 = null;
		str1 = URLEncoder.encode(url);
		String url2 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx14fa9d10b56feaed&redirect_uri="+str1+"&response_type=code&scope=snsapi_base&state=2#wechat_redirect";
		System.out.println(">>>>>>>>"+url2);
		context.setData("url",url2);
		
		context.setState(0);
	}

}
