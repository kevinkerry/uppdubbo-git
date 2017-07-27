package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryAcctOpenStatusPaymentReq extends PaymentReqHead {
	private static final long serialVersionUID = -6417446910098814859L;
    private String payerAcctNbr; 
    private String payerPhoneNo; 
    private String isQueryOpenStatus; 
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

    public void setIsQueryOpenStatus(String isQueryOpenStatus) {
        this.isQueryOpenStatus = isQueryOpenStatus;
    }
    public String getIsQueryOpenStatus() {
        return isQueryOpenStatus;
    }


}
