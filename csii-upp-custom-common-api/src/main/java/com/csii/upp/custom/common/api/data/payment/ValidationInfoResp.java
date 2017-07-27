package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class ValidationInfoResp extends PaymentRespHead {
	private static final long serialVersionUID = -5367146427209816193L;
    private String payerAcctName; 
    private String payerCardTypCd; 
    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }
    public String getPayerAcctName() {
        return payerAcctName;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }


}
