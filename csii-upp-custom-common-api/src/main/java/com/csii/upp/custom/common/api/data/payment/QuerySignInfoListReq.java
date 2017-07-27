package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QuerySignInfoListReq extends PaymentReqHead {
	private static final long serialVersionUID = -5219286096654672925L;
    private String payerAcctNbr; 
    private String payerPhoneNo; 
    private String payerCardTypCd; 
    private String payerAcctDeptNbr; 
    private String beginDate; 
    private String endDate; 
    private String pageNum; 
    private String transTypCd; 
    private String pageSize; 
    private String startNo; 
    private String endNo; 
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }

    public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
        this.payerAcctDeptNbr = payerAcctDeptNbr;
    }
    public String getPayerAcctDeptNbr() {
        return payerAcctDeptNbr;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getBeginDate() {
        return beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
    public String getPageNum() {
        return pageNum;
    }

    public void setTransTypCd(String transTypCd) {
        this.transTypCd = transTypCd;
    }
    public String getTransTypCd() {
        return transTypCd;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
    public String getPageSize() {
        return pageSize;
    }

    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }
    public String getStartNo() {
        return startNo;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }
    public String getEndNo() {
        return endNo;
    }


}
