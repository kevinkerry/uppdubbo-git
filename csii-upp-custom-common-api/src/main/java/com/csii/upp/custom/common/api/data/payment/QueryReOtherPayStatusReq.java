package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryReOtherPayStatusReq extends PaymentReqHead {
	private static final long serialVersionUID = -5029265934806454518L;
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
