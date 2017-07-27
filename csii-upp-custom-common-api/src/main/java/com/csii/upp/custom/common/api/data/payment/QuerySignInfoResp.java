package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QuerySignInfoResp extends PaymentRespHead {
	private static final long serialVersionUID = -5868413004928410994L;
    private String payerPhoneNo; 
    private String signStatus; 
    private String payerAcctNbr; 
    private String signNbr; 
    private String signTypCd; 
    private String payerCardTypCd; 
    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }
    public String getSignStatus() {
        return signStatus;
    }

    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setSignNbr(String signNbr) {
        this.signNbr = signNbr;
    }
    public String getSignNbr() {
        return signNbr;
    }

    public void setSignTypCd(String signTypCd) {
        this.signTypCd = signTypCd;
    }
    public String getSignTypCd() {
        return signTypCd;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }


}
