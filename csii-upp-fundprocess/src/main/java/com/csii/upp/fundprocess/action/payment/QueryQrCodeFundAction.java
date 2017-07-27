package com.csii.upp.fundprocess.action.payment;


import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 二维码信息查询
 * @author wy
 *
 */
public class QueryQrCodeFundAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {

//		InputFundData input = new InputFundData(context.getDataMap());
//		RespSysHead output=null;
//		if(CodeTypCd.ALIPAY.equals(input.getCodetypcd())){
//			output=(RespAlipayCodePreHead) getAliPayService().queryQrCodeUrl(input);
//		}else{
//			output=(RespWeChatCodePreHead) getWechatService().queryQrCodeUrl(input);
//		}
//		if(!StringUtil.isStringEmpty(output.getCodeUrl())){
//			context.setData(Dict.CODE_URL, output.getCodeUrl());
//		}
		
		String merName = context.getString("MerName");
		//merName = URLEncoder.
		
		JSONObject json=new JSONObject();
		json.put("TransName", "QREM");
		json.put("MerchantName", context.getString("MerName"));
		json.put("TransAmt", context.getString("TransAmt"));
		
		String str = JSON.toJSONString(json, SerializerFeature.UseSingleQuotes);
		String url = "http://ww.7har.com/paygate2/qrCode?TransName=check&MerchantName="+merName+"&TransAmt="+context.getString("TransAmt");
		url = URLEncoder.encode(url);
//		String str1 = null;
//		try {
//			str1 = URLEncoder.encode(url,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} 
//		String url2 ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx14fa9d10b56feaed&redirect_uri="+str1+"&response_type=code&scope=snsapi_base&state=2#wechat_redirect";
		context.setData(Dict.CODE_URL, url);
 	}

}
