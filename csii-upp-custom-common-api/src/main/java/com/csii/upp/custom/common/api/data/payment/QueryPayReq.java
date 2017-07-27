package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryPayReq extends PaymentReqHead {
	private static final long serialVersionUID = -4830655154096832413L;
    private String merTransDate; 

    public void setMerTransDate(String merTransDate) {
        this.merTransDate = merTransDate;
    }
    public String getMerTransDate() {
        return merTransDate;
    }


}
