package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryCardFlagReq extends PaymentReqHead {
	private static final long serialVersionUID = -8080455284296103587L;
    private String payerAcctNbr; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }


}
