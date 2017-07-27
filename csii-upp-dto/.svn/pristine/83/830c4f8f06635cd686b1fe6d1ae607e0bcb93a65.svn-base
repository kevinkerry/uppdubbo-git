package com.csii.upp.dto.router.wechatcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;
import com.csii.upp.util.StringUtil;
/**
 * 二维码前置微信请求头
 * @author wy
 *
 */
public class ReqWeChatCodeHead extends ReqAppHead {
	private String txnCode;
	private String channel;
	private String payAccessType;
	private String currencyType;
	private String deviceInfo;
	public ReqWeChatCodeHead(InputFundData data) {
		super(data);
		this.setChnlId(FundChannelCode.PAY);
		//支付平台渠道 6001：POS 6002：支付平台 6003：手机银行
		this.setChannel("6002");
		//支付宝接入 01:本行 02：微信 03：支付宝
		this.setPayAccessType("02");
		//币种 156：人民币
		this.setCurrencyType("156");
		//设备信息 WEB：线上 POS：线下
		this.setDeviceInfo("WEB");
		data.setFundchannelcode(FundChannelCode.WECHATCODE);
		this.setSrvChnlId(FundChannelCode.WECHATCODE);
	}
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPayAccessType() {
		return payAccessType;
	}
	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	protected String formatAmt(BigDecimal transamt){
		String formattransamt=StringUtil.BigDel2Str(transamt.setScale(2, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(100)));
		return StringUtil.formatLeftStr(formattransamt, 12);
	}
	
}
