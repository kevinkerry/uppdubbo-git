package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class XMLimitCXReq extends PaymentReqHead {
	private static final long serialVersionUID = -6764444683480887601L;
    private String cifNo; 
    private String merSeqNbr; 
    private String merTransDateTime; 
    private String orderNbr; 
    private String transAmt; 
    private String currencyCd; 
    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }
    public String getCifNo() {
        return cifNo;
    }

    public void setMerSeqNbr(String merSeqNbr) {
        this.merSeqNbr = merSeqNbr;
    }
    public String getMerSeqNbr() {
        return merSeqNbr;
    }

    public void setMerTransDateTime(String merTransDateTime) {
        this.merTransDateTime = merTransDateTime;
    }
    public String getMerTransDateTime() {
        return merTransDateTime;
    }

    public void setOrderNbr(String orderNbr) {
        this.orderNbr = orderNbr;
    }
    public String getOrderNbr() {
        return orderNbr;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getCurrencyCd() {
        return currencyCd;
    }


}
