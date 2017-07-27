package com.csii.upp.qrcodeplatform.action.pay;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.Constants;
import com.csii.upp.qrcodeplatform.action.constant.Dict;
import com.csii.upp.qrcodeplatform.action.util.MiscUtil;
import com.csii.upp.qrcodeplatform.action.util.SHA1;
import com.csii.upp.qrcodeplatform.action.util.Signature;
import com.csii.upp.qrcodeplatform.action.util.WxUtil;
import com.csii.upp.qrcodeplatform.action.util.XmlUtil;
import com.csii.upp.qrcodeplatform.sequence.DefaultSupportor;

public class WechatJsApiPayAction extends QrCodeAction {

	private WxUtil wxUtil;

	private String url;

	private String notifyUrl;
	
	private String appid;
	
	private String appsecret;
	
	private String mch_id;
	
	private String sub_mch_id;
	
	@Override
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		
		long timestamp = System.currentTimeMillis()/1000L;
		String  QrcodeTransSeqNo = ctx.getString("UpperTransNbr");
		
		Map sendMap = new HashMap();
		sendMap.put("appid", appid);
		sendMap.put("mch_id", mch_id);
		sendMap.put("sub_mch_id", sub_mch_id);
		// sendMap.put("device_info", ctx.getData("device_info"));
		sendMap.put("nonce_str", MiscUtil.getRandom());
		
//		sendMap.put("body", merchant.getMerShortName() + "-" + merchant.getMerAddr() + "-" + merchant.getManageDesc());
		sendMap.put("body", "Test" + "-" + "Test_addrr" + "-" + "科蓝金服");
		
//		sendMap.put("attach", wxUtil.getAttach(order));
		sendMap.put("attach", wxUtil.getAttach("Test","Test"));

		sendMap.put("time_start", String.valueOf(timestamp));

//		sendMap.put("out_trade_no", order.getTransSeqNo());
		sendMap.put("out_trade_no", QrcodeTransSeqNo);
		
		BigDecimal transamt = ctx.getBigDecimal("TransAmt");
		BigDecimal multiple = new BigDecimal(100);
		BigDecimal realtransamt = transamt.multiply(multiple);
		
		sendMap.put("total_fee", String.valueOf(realtransamt.intValue()));
		sendMap.put("spbill_create_ip", "127.0.0.1");
		sendMap.put("notify_url", notifyUrl);
		sendMap.put("trade_type", "JSAPI");
		
		sendMap.put("openid", getOpenid(ctx.getString("code")));	//JSAPI支付方式 需要取得用户的Openid
        
		String paysignkey = ctx.getString("paysignkey");
        log.info(paysignkey);
		sendMap.put("sign", MiscUtil.sign1(sendMap,paysignkey));

		String xmlData = wxUtil.parseXML(sendMap);

		byte[] bytea = wxUtil.httpSend(xmlData.getBytes(), url);

		String str = MiscUtil.getStrMsg(bytea, "UTF-8");

		Map xmap = XmlUtil.toMap(str, "xml");
		
		log.info("微信返回信息如下:" + xmap);
		if (Constants.QRCODE_FAIL.equals(xmap.get("return_code"))) {
			log.info("微信统一下单微信通信错误码[" + xmap.get("return_code") + "],微信通信错误信息[" + xmap.get("return_msg"));
			throw new PeException((String) xmap.get("return_code"));
		}else{
			
			ctx.setData("prepay_id", xmap.get("prepay_id"));
			ctx.setData("nonce_str", xmap.get("nonce_str"));
			ctx.setData("timestamp", timestamp);
			ctx.setData("QrcodeTransSeqNo", QrcodeTransSeqNo);
			ctx.setData("signature", getSignature(getJsapiTicket(),(String)xmap.get("nonce_str"),timestamp, ctx.getString("url")));
			ctx.setData("paySign", getPaySign((String)xmap.get("prepay_id"),(String)xmap.get("nonce_str"),timestamp,paysignkey));
			ctx.setData(Dict.RESP_STATUS, "R");
			ctx.setData(Dict.RESP_CODE, "000000");
		}
	}
	
	public String getOpenid(String code)
	{
	
		String result = "";
		try {

			HttpGet request = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+this.appid+"&secret="+this.appsecret+"&code="+code+"&grant_type=authorization_code");
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            } 
			
			JSONObject jo = JSONObject.parseObject(result);
			String ack = jo.getString("openid");
			return ack;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public  String getAccessToken()
	{
	
		String result = "";
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("grant_type", "client_credential");
			params.put("appid", appid);
			params.put("secret", appsecret);

			HttpGet request = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appid + "&secret=" + appsecret);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            } 
			
			JSONObject jo = JSONObject.parseObject(result);
			String ack = jo.getString("access_token");
			return ack;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public  String getJsapiTicket()
	{
	
		String result = "";
		String jsapi_ticket ="";
		try {
			HttpGet request = new HttpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+ getAccessToken() + "&type=JSAPI");
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
			JSONObject jo = JSONObject.parseObject(result);
			jsapi_ticket = jo.getString("ticket");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsapi_ticket;
	}
	
	public  String getPaySign(String prepay_id, String noncestr, long timestamp,String key)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appid);
		params.put("nonceStr", noncestr);
		params.put("package", "prepay_id=" + prepay_id);
		params.put("signType", "MD5");
		params.put("timeStamp", timestamp);

		String paySign = Signature.getSign(params,key);

		return paySign;
	}
	
	public static String getSignature(String jsapi, String noncestr, long timestamp, String url)
	{
		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi, "timestamp=" + timestamp, "noncestr=" + noncestr, "url=" + url }; //this.pay.getPayAuthURL()
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String orginStr = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		//LOGGER.debug("微信支付签名字符串signature[" + orginStr + "]");
		// String orginStr = "jsapi_ticket=" + jsapiTicket + "&noncestr=" +
		// noncestr + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println("jsapi_ticket ："+jsapi);
		System.out.println("noncestr ："+noncestr);
		System.out.println("时间 ："+timestamp);
		System.out.println("url ："+url);
		String signature = new SHA1().getDigestOfString(orginStr.getBytes()).toLowerCase();
		System.out.println("signature ："+signature);
		return signature;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public WxUtil getWxUtil() {
		return wxUtil;
	}

	public void setWxUtil(WxUtil wxUtil) {
		this.wxUtil = wxUtil;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	
	

}
