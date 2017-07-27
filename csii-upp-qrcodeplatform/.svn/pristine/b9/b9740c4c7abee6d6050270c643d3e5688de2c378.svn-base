package com.csii.upp.qrcodeplatform.action.pay;

import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.Constants;
import com.csii.upp.qrcodeplatform.action.constant.Dict;
import com.csii.upp.qrcodeplatform.action.util.MiscUtil;
import com.csii.upp.qrcodeplatform.action.util.WxUtil;
import com.csii.upp.qrcodeplatform.action.util.XmlUtil;
import com.csii.upp.qrcodeplatform.sequence.DefaultSupportor;

public class WechatQrCodePay extends QrCodeAction {

	private WxUtil wxUtil;

	private String url;

	private String notifyUrl;
	
	private String appid;
	
	private String mch_id;
	
	private String sub_mch_id;
	
	@Override
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		
		Map sendMap = new HashMap();
		sendMap.put("appid", appid);
		sendMap.put("mch_id", mch_id);
		sendMap.put("sub_mch_id", sub_mch_id);
		// sendMap.put("device_info", ctx.getData("device_info"));
		sendMap.put("nonce_str", MiscUtil.getRandom());
		
//		sendMap.put("body", merchant.getMerShortName() + "-" + merchant.getMerAddr() + "-" + merchant.getManageDesc());
		sendMap.put("body", ctx.getString("ProBody"));
		
//		sendMap.put("attach", wxUtil.getAttach(order));		//附加字段。可以自定义使用
//		sendMap.put("attach", wxUtil.getAttach("Test","Test"));

//		sendMap.put("out_trade_no", order.getTransSeqNo());
		sendMap.put("out_trade_no", DefaultSupportor.generateSeqNbr());
		
		sendMap.put("total_fee", ctx.getData("TransAmt"));
		sendMap.put("spbill_create_ip", ctx.getData("CustomerIp"));
		sendMap.put("notify_url", notifyUrl);
		sendMap.put("trade_type", "NATIVE");
		sendMap.put("product_id", ctx.getString("ProductId"));
		
		String isCredit = ctx.getString("IsCredit");
		if(null!=isCredit||isCredit.equals("0")){
			sendMap.put("limit_pay", "no_credit");
		}else{
			sendMap.put("limit_pay", "");
		}
		
//		sendMap.put("openid", order.getOpenid());	Native模式不需要
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
			ctx.setData(Dict.RESP_STATUS, "S");
			ctx.setData(Dict.RESP_CODE, "000000");
			ctx.setData("CodeUrl", xmap.get("code_url"));
		}
	}

	
	
	public String getAppid() {
		return appid;
	}



	public void setAppid(String appid) {
		this.appid = appid;
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
