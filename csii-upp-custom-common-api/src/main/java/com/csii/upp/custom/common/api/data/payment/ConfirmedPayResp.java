package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class ConfirmedPayResp extends PaymentRespHead {
	private static final long serialVersionUID = -4630511526726106185L;
    private String transId; 
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public String getTransId() {
        return transId;
    }


}
