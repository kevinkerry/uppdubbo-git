package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QueryReOtherPayStatusResp extends PaymentRespHead {
	private static final long serialVersionUID = -5142750593511346849L;
    private String openStatus; 
    private String payerCardTypCd; 
    private String cardBinName; 
    private String innerCardFlag; 
    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }
    public String getOpenStatus() {
        return openStatus;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }

    public void setCardBinName(String cardBinName) {
        this.cardBinName = cardBinName;
    }
    public String getCardBinName() {
        return cardBinName;
    }

    public void setInnerCardFlag(String innerCardFlag) {
        this.innerCardFlag = innerCardFlag;
    }
    public String getInnerCardFlag() {
        return innerCardFlag;
    }


}
