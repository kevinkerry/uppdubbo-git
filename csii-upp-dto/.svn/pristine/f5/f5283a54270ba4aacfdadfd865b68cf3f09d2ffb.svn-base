package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqQueryAcctOpenStatus  extends ReqPaymHead{
	
	private String isQueryOpenStatus;

	public ReqQueryAcctOpenStatus(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryAcctOpenStatus);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerPhoneNo(data.getPayerphoneno());
		this.setIsQueryOpenStatus(data.getIsQueryOpenStatus());
	}

	public String getIsQueryOpenStatus() {
		return isQueryOpenStatus;
	}

	public void setIsQueryOpenStatus(String isQueryOpenStatus) {
		this.isQueryOpenStatus = isQueryOpenStatus;
	}
}
