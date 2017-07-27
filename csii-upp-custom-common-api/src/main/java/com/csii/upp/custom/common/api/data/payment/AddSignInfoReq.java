package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class AddSignInfoReq extends PaymentReqHead {
	private static final long serialVersionUID = -7112195377543342994L;
    private String custCifNbr; 
    private String payerAcctNbr; 
    private String payerCardTypCd; 
    private String payerPhoneNo; 
    private String payerCardPwd; 
    private String payerIdTypCd; 
    private String payerCardCvv2; 
    private String payerIdNbr; 
    private String payerAcctDeptNbr; 
    private String payerAcctName; 
    private String tellerNo; 
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

    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }
    public String getPayerAcctName() {
        return payerAcctName;
    }

    public void setTellerNo(String tellerNo) {
        this.tellerNo = tellerNo;
    }
    public String getTellerNo() {
        return tellerNo;
    }


}
