package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryOrderInfoReq extends PaymentReqHead {
	private static final long serialVersionUID = -5682714065071802632L;
    private String transSeqNbr; 
    public void setTransSeqNbr(String transSeqNbr) {
        this.transSeqNbr = transSeqNbr;
    }
    public String getTransSeqNbr() {
        return transSeqNbr;
    }


}
