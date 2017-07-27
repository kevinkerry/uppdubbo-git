package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class HoldAcctStatusReq extends PaymentReqHead {
	private static final long serialVersionUID = -4918775528583510965L;
    private String payerAcctNbr; 
    private String payerCardTypCd; 
    private String payerCardPwd; 
    private String payerIdTypCd; 
    private String payerIdNbr; 
    private String payerPhoneNo; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }

    public void setPayerCardPwd(String payerCardPwd) {
        this.payerCardPwd = payerCardPwd;
    }
    public String getPayerCardPwd() {
        return payerCardPwd;
    }

    public void setPayerIdTypCd(String payerIdTypCd) {
        this.payerIdTypCd = payerIdTypCd;
    }
    public String getPayerIdTypCd() {
        return payerIdTypCd;
    }

    public void setPayerIdNbr(String payerIdNbr) {
        this.payerIdNbr = payerIdNbr;
    }
    public String getPayerIdNbr() {
        return payerIdNbr;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }


}
