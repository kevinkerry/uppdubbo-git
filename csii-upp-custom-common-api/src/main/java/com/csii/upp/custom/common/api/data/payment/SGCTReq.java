package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class SGCTReq extends PaymentReqHead {
	private static final long serialVersionUID = -4675643746121571217L;
    private String payerAcctNbr; 
    private String certType; 
    private String certNo; 
    private String payerAcctOrgDeptNbr; 
    private String payTypeCd; 
    private String payerCardTypCd; 
    private String payerPhoneNo; 
    private String subMerchantId; 
    private String merSeqNbr; 
    private String transAmt; 
    private String merTransDateTime; 
    private String currencyCd; 
    private String payerAcctDeptNbr; 
    private String payerAcctName; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }
    public String getCertType() {
        return certType;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
    public String getCertNo() {
        return certNo;
    }

    public void setPayerAcctOrgDeptNbr(String payerAcctOrgDeptNbr) {
        this.payerAcctOrgDeptNbr = payerAcctOrgDeptNbr;
    }
    public String getPayerAcctOrgDeptNbr() {
        return payerAcctOrgDeptNbr;
    }

    public void setPayTypeCd(String payTypeCd) {
        this.payTypeCd = payTypeCd;
    }
    public String getPayTypeCd() {
        return payTypeCd;
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

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }
    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setMerSeqNbr(String merSeqNbr) {
        this.merSeqNbr = merSeqNbr;
    }
    public String getMerSeqNbr() {
        return merSeqNbr;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setMerTransDateTime(String merTransDateTime) {
        this.merTransDateTime = merTransDateTime;
    }
    public String getMerTransDateTime() {
        return merTransDateTime;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getCurrencyCd() {
        return currencyCd;
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


}
