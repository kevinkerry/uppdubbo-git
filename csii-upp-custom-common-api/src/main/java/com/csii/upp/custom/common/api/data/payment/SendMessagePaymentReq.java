package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class SendMessagePaymentReq extends PaymentReqHead {
	private static final long serialVersionUID = -8220121296928013719L;
    private String payerAcctNbr; 
    private String payerPhoneNo; 
    private String transAmt; 
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

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }


}
