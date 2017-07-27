package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class SendSmsResp extends PaymentRespHead {
	private static final long serialVersionUID = -4686013630078164232L;
    private String smsSqenbr; 
    public void setSmsSqenbr(String smsSqenbr) {
        this.smsSqenbr = smsSqenbr;
    }
    public String getSmsSqenbr() {
        return smsSqenbr;
    }


}
