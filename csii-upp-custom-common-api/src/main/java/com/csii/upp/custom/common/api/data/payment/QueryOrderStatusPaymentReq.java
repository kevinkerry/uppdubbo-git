package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryOrderStatusPaymentReq extends PaymentReqHead {
	private static final long serialVersionUID = -5819127039255169749L;
    private String merSeqNbr; 

    public void setMerSeqNbr(String merSeqNbr) {
        this.merSeqNbr = merSeqNbr;
    }
    public String getMerSeqNbr() {
        return merSeqNbr;
    }


}
