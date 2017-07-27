package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class ReturnTransReq extends PaymentReqHead {
	private static final long serialVersionUID = -6800406284867027833L;
    private String merSeqNbr; 
    private String merTransDateTime; 
    private String origMerSeqNbr; 
    private String origMerDate; 
    private String origTransAmt; 
    private String subMerchantId; 
    private String subMerSeqNo; 
    private String subMerDateTime; 
    private String subTransAmt; 
    private String origSubMerSeqNo; 
    private String origSubMerDate; 
    private String refundReason; 
    private String operatorId; 
    private String storeId; 
    private String termId; 
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

    public void setOrigMerSeqNbr(String origMerSeqNbr) {
        this.origMerSeqNbr = origMerSeqNbr;
    }
    public String getOrigMerSeqNbr() {
        return origMerSeqNbr;
    }

    public void setOrigMerDate(String origMerDate) {
        this.origMerDate = origMerDate;
    }
    public String getOrigMerDate() {
        return origMerDate;
    }

    public void setOrigTransAmt(String origTransAmt) {
        this.origTransAmt = origTransAmt;
    }
    public String getOrigTransAmt() {
        return origTransAmt;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }
    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerSeqNo(String subMerSeqNo) {
        this.subMerSeqNo = subMerSeqNo;
    }
    public String getSubMerSeqNo() {
        return subMerSeqNo;
    }

    public void setSubMerDateTime(String subMerDateTime) {
        this.subMerDateTime = subMerDateTime;
    }
    public String getSubMerDateTime() {
        return subMerDateTime;
    }

    public void setSubTransAmt(String subTransAmt) {
        this.subTransAmt = subTransAmt;
    }
    public String getSubTransAmt() {
        return subTransAmt;
    }

    public void setOrigSubMerSeqNo(String origSubMerSeqNo) {
        this.origSubMerSeqNo = origSubMerSeqNo;
    }
    public String getOrigSubMerSeqNo() {
        return origSubMerSeqNo;
    }

    public void setOrigSubMerDate(String origSubMerDate) {
        this.origSubMerDate = origSubMerDate;
    }
    public String getOrigSubMerDate() {
        return origSubMerDate;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
    public String getRefundReason() {
        return refundReason;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public String getOperatorId() {
        return operatorId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getStoreId() {
        return storeId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }
    public String getTermId() {
        return termId;
    }


}
