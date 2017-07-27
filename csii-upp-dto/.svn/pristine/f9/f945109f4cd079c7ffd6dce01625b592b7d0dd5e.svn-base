package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

public class ReqUnifiedRegister extends ReqFundProHead {
	private String sendUnionPayTime;
	private String checkNumber;   //对账分类编号
	private String note; //备用字段
	private String frontCallBackUrl;

	public ReqUnifiedRegister(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.RegisterOtherQuickPay);
		this.setSendUnionPayTime(data.getSendUnionPayTime());
		this.setFrontCallBackUrl(data.getFrontCallBackUrl());
	}
	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}

	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}	

	public String getSendUnionPayTime() {
		return sendUnionPayTime;
	}

	public void setSendUnionPayTime(String sendUnionPayTime) {
		this.sendUnionPayTime = sendUnionPayTime;
	}
	
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
