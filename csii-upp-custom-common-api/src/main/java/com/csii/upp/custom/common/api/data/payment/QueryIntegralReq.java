package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryIntegralReq extends PaymentReqHead {
	private static final long serialVersionUID = -5272955432167818270L;
    private String payerAcctNbr; 
    private String transAmt; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }


}
