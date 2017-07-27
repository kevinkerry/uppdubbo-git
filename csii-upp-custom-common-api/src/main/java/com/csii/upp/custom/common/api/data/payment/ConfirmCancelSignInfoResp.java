package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class ConfirmCancelSignInfoResp extends PaymentRespHead {
	private static final long serialVersionUID = -5964829468673996701L;
    private String payerAcctNbr; 
    private String localMobilePhone; 
    private String payerAcctName; 
    private String payerPhoneNo; 
    private String payerCardTypCd; 
    private String custCifNbr; 
    private String payerAcctDeptNbr; 
    private String payerAcctStatus; 
    private String acctLossStatus; 
    private String acctStatusWord; 
    private String cardStatusWord; 
    private String cardStatus; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setLocalMobilePhone(String localMobilePhone) {
        this.localMobilePhone = localMobilePhone;
    }
    public String getLocalMobilePhone() {
        return localMobilePhone;
    }

    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }
    public String getPayerAcctName() {
        return payerAcctName;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }

    public void setCustCifNbr(String custCifNbr) {
        this.custCifNbr = custCifNbr;
    }
    public String getCustCifNbr() {
        return custCifNbr;
    }

    public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
        this.payerAcctDeptNbr = payerAcctDeptNbr;
    }
    public String getPayerAcctDeptNbr() {
        return payerAcctDeptNbr;
    }

    public void setPayerAcctStatus(String payerAcctStatus) {
        this.payerAcctStatus = payerAcctStatus;
    }
    public String getPayerAcctStatus() {
        return payerAcctStatus;
    }

    public void setAcctLossStatus(String acctLossStatus) {
        this.acctLossStatus = acctLossStatus;
    }
    public String getAcctLossStatus() {
        return acctLossStatus;
    }

    public void setAcctStatusWord(String acctStatusWord) {
        this.acctStatusWord = acctStatusWord;
    }
    public String getAcctStatusWord() {
        return acctStatusWord;
    }

    public void setCardStatusWord(String cardStatusWord) {
        this.cardStatusWord = cardStatusWord;
    }
    public String getCardStatusWord() {
        return cardStatusWord;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }
    public String getCardStatus() {
        return cardStatus;
    }


}
