package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class SendMessagePaymentResp extends PaymentRespHead {
	private static final long serialVersionUID = -5396760278143507805L;
    private String smsInnerFundTransNbr; 
    private String sendUnionPayTime; 
    public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
        this.smsInnerFundTransNbr = smsInnerFundTransNbr;
    }
    public String getSmsInnerFundTransNbr() {
        return smsInnerFundTransNbr;
    }

    public void setSendUnionPayTime(String sendUnionPayTime) {
        this.sendUnionPayTime = sendUnionPayTime;
    }
    public String getSendUnionPayTime() {
        return sendUnionPayTime;
    }


}
