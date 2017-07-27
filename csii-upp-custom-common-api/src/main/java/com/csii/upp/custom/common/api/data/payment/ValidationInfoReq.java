package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class ValidationInfoReq extends PaymentReqHead {
	private static final long serialVersionUID = -6940794632989404503L;
    private String custCifNbr; 
    private String payerAcctNbr; 
    private String payerCardTypCd; 
    private String payerPhoneNo; 
    private String payerCardPwd; 
    private String payerIdTypCd; 
    private String payerCardCvv2; 
    private String payerCardExpiredDate; 
    private String payerIdNbr; 
    private String payerAcctDeptNbr; 
    public void setCustCifNbr(String custCifNbr) {
        this.custCifNbr = custCifNbr;
    }
    public String getCustCifNbr() {
        return custCifNbr;
    }

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

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
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

    public void setPayerCardCvv2(String payerCardCvv2) {
        this.payerCardCvv2 = payerCardCvv2;
    }
    public String getPayerCardCvv2() {
        return payerCardCvv2;
    }

    public void setPayerCardExpiredDate(String payerCardExpiredDate) {
        this.payerCardExpiredDate = payerCardExpiredDate;
    }
    public String getPayerCardExpiredDate() {
        return payerCardExpiredDate;
    }

    public void setPayerIdNbr(String payerIdNbr) {
        this.payerIdNbr = payerIdNbr;
    }
    public String getPayerIdNbr() {
        return payerIdNbr;
    }

    public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
        this.payerAcctDeptNbr = payerAcctDeptNbr;
    }
    public String getPayerAcctDeptNbr() {
        return payerAcctDeptNbr;
    }


}
