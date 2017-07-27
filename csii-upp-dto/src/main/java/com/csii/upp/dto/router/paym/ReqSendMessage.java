package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqSendMessage  extends ReqPaymHead{
	
	public ReqSendMessage(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.SendMessage);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerPhoneNo(data.getPayerphoneno());
	}

}
