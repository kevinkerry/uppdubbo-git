package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqQueryReOtherPayStatus extends ReqPaymHead{
	public String isQueryOpenStatus;

	public String getIsQueryOpenStatus() {
		return isQueryOpenStatus;
	}

	public void setIsQueryOpenStatus(String isQueryOpenStatus) {
		this.isQueryOpenStatus = isQueryOpenStatus;
	}

	public ReqQueryReOtherPayStatus(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryReOtherPayStatus);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerPhoneNo(data.getPayerphoneno());
		this.setIsQueryOpenStatus(data.getIsQueryOpenStatus());
	}
}