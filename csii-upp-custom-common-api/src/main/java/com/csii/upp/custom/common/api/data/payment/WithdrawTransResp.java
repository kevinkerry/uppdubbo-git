package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class WithdrawTransResp extends PaymentRespHead {
	private static final long serialVersionUID = -8736264673219902680L;
    private String transId; 
    private String merchantId; 
    private String subMerchantId; 
    private String userNo; 
    private String merSeqNo; 
    private String merDateTime; 
    private String transAmt; 
    private String respCode; 
    private String respMessage; 
    private String transSeqNo; 
    private String clearDate; 
    private String msgExt; 
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public String getTransId() {
        return transId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    public String getMerchantId() {
        return merchantId;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }
    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getUserNo() {
        return userNo;
    }

    public void setMerSeqNo(String merSeqNo) {
        this.merSeqNo = merSeqNo;
    }
    public String getMerSeqNo() {
        return merSeqNo;
    }

    public void setMerDateTime(String merDateTime) {
        this.merDateTime = merDateTime;
    }
    public String getMerDateTime() {
        return merDateTime;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
    public String getRespCode() {
        return respCode;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
    public String getRespMessage() {
        return respMessage;
    }

    public void setTransSeqNo(String transSeqNo) {
        this.transSeqNo = transSeqNo;
    }
    public String getTransSeqNo() {
        return transSeqNo;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }
    public String getClearDate() {
        return clearDate;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }
    public String getMsgExt() {
        return msgExt;
    }


}
