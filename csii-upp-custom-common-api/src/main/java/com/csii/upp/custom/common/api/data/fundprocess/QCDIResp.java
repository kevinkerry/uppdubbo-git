package com.csii.upp.custom.common.api.data.fundprocess;

import java.util.List;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class QCDIResp extends FundProcessRespHead {
	private static final long serialVersionUID = -8885963444383119039L;
    private String payerAcctNbr; 
    private String payerIdTypCd; 
    private String payerIdNbr; 
    private String payerAcctStatus; 
    private List<String> payerPhoneNoList; 
    private String payerPhoneNo; 
    private String payerCardCvv2; 
    private String payerAcctName; 
    private String custCifNbr; 
    private String payerAcctDeptNbr; 
    private String acctLossStatus; 
    private String acctStatusWord; 
    private String acctFreezeFlag; 
    private String acctNature; 
    private String cardStatus; 
    private String cardStatusWord; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
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

    public void setPayerAcctStatus(String payerAcctStatus) {
        this.payerAcctStatus = payerAcctStatus;
    }
    public String getPayerAcctStatus() {
        return payerAcctStatus;
    }

    public void setPayerPhoneNoList(List<String> payerPhoneNoList) {
        this.payerPhoneNoList = payerPhoneNoList;
    }
    public List<String> getPayerPhoneNoList() {
        return payerPhoneNoList;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setPayerCardCvv2(String payerCardCvv2) {
        this.payerCardCvv2 = payerCardCvv2;
    }
    public String getPayerCardCvv2() {
        return payerCardCvv2;
    }

    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }
    public String getPayerAcctName() {
        return payerAcctName;
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

    public void setAcctFreezeFlag(String acctFreezeFlag) {
        this.acctFreezeFlag = acctFreezeFlag;
    }
    public String getAcctFreezeFlag() {
        return acctFreezeFlag;
    }

    public void setAcctNature(String acctNature) {
        this.acctNature = acctNature;
    }
    public String getAcctNature() {
        return acctNature;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }
    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatusWord(String cardStatusWord) {
        this.cardStatusWord = cardStatusWord;
    }
    public String getCardStatusWord() {
        return cardStatusWord;
    }


}
