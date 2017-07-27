package com.csii.upp.custom.common.api.data.base;

public abstract class PaymentReqHead extends BaseInput{
	private static final long serialVersionUID = -7640648047382180004L;
	
	private String channelNbr;
	private String payTypCd;
	private String transId;
	private String merNbr;
	private String plain;
	private String signature;
	public String getChannelNbr() {
		return channelNbr;
	}
	public void setChannelNbr(String channelNbr) {
		this.channelNbr = channelNbr;
	}
	public String getPayTypCd() {
		return payTypCd;
	}
	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getMerNbr() {
		return merNbr;
	}
	public void setMerNbr(String merNbr) {
		this.merNbr = merNbr;
	}
	public String getPlain() {
		return plain;
	}
	public void setPlain(String plain) {
		this.plain = plain;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
