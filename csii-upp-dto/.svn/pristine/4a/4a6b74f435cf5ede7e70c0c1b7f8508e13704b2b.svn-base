package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqDeleteSignInfo  extends ReqPaymHead{
	
	public ReqDeleteSignInfo(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.DeleteSignInfo);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerPhoneNo(data.getPayerphoneno());
	}
}
