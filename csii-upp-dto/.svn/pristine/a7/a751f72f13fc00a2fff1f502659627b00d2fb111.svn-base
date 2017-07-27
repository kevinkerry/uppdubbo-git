package com.csii.upp.dto.router.paym;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqRegistOtherPay extends ReqPaymHead {


	private String sendUnionPayTime;
	private List<Map> merTransList;
	private String payerBankNbr;
	private String frontCallBackUrl;

	public ReqRegistOtherPay(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.RegisterOtherPay);
		this.setSendUnionPayTime(data.getSendUnionPayTime());
		this.setMerTransList(data.getMersubtranslist());
		this.setPayerBankNbr(data.getPayerbanknbr());
		this.setFrontCallBackUrl(data.getFrontCallBackUrl());
	}


	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}

	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}

	public String getPayerBankNbr() {
		return payerBankNbr;
	}

	public void setPayerBankNbr(String payerBankNbr) {
		this.payerBankNbr = payerBankNbr;
	}

	public String getSendUnionPayTime() {
		return sendUnionPayTime;
	}

	public void setSendUnionPayTime(String sendUnionPayTime) {
		this.sendUnionPayTime = sendUnionPayTime;
	}

	public List<Map> getMerTransList() {
		return merTransList;
	}

	public void setMerTransList(List<Map> merTransList) {
		this.merTransList = merTransList;
	}
}
