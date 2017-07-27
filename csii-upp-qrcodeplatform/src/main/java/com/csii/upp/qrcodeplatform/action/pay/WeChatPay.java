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

public class WeChatPay extends QrCodeAction {
	
	private WxUtil wxUtil;
	private String url;
	private String queryurl;
	
	
	
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



	public String getQueryurl() {
		return queryurl;
	}



	public void setQueryurl(String queryurl) {
		this.queryurl = queryurl;
	}



	@Override
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		log.debug("支付开始~");
//		Order order = (Order) ctx.getVariable();
//		Merchant merchant = order.getMerchant();
		
		Map sendMap = new HashMap();
		sendMap.put("appid", "wx11c21f4979caeb35");
		sendMap.put("mch_id", "1368662602");
		sendMap.put("sub_mch_id", "1375086202");
		// sendMap.put("device_info", ctx.getData("device_info"));
		
		sendMap.put("nonce_str", MiscUtil.getRandom());
		
//		sendMap.put("body", merchant.getMerShortName() + "-" + merchant.getMerAddr() + "-" + merchant.getManageDesc());
		sendMap.put("body", "Test" + "-" + "Test_addrr" + "-" + "科蓝金服");
		
//		sendMap.put("attach", wxUtil.getAttach(order));
		sendMap.put("attach", wxUtil.getAttach("Test","Test"));

//		sendMap.put("out_trade_no", order.getTransSeqNo());
		sendMap.put("out_trade_no", DefaultSupportor.generateSeqNbr());
		
		sendMap.put("total_fee", ctx.getData("total_fee"));
		sendMap.put("spbill_create_ip", "127.0.0.1");
		sendMap.put("auth_code", ctx.getData("auth_code"));
		String paysignkey = ctx.getString("paysignkey");
		String str1 = MiscUtil.sign1(sendMap,paysignkey);

		sendMap.put("sign", str1);

		String xmlData = wxUtil.parseXML(sendMap);
		System.out.println("发送的数据为 》》》 " + xmlData);

		byte[] bytea = wxUtil.httpSend(xmlData.getBytes(), url);

		String str = MiscUtil.getStrMsg(bytea, "UTF-8");

		Map wxResMap = XmlUtil.toMap(str, "xml");

		ctx.setDataMap(wxResMap);

		log.info("微信提交刷卡支付微信返回信息如下:" + wxResMap);

		if (Constants.QRCODE_FAIL.equals(wxResMap.get("return_code"))) {
			log.info("微信提交刷卡支付微信通信错误码[" + wxResMap.get("return_code") + "],微信通信错误信息[" + wxResMap.get("return_msg") + "]");
			throw new PeException((String) wxResMap.get("return_code"));// 交易失败
		}else{
			ctx.setData(Dict.RESP_STATUS, "R");
			ctx.setData(Dict.RESP_CODE, "0000");
		}
		
	}

}
