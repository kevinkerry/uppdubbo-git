package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryCardInfoReq extends PaymentReqHead {
	private static final long serialVersionUID = -4781992502070792735L;
    private String payerAcctNbr; 
    private String payerPhoneNo; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }


}
