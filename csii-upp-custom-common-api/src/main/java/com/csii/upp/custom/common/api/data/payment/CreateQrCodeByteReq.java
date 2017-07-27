package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class CreateQrCodeByteReq extends PaymentReqHead {
	private static final long serialVersionUID = -9162834783194451706L;
    private String merSeqNbr; 
    private String transDate; 
    private String codeStreamStr; 
    private String codeUrl; 
    private String codeTypCd; 
    private String payType; 
    private String respCode; 
    private String respMessage; 

    public void setMerSeqNbr(String merSeqNbr) {
        this.merSeqNbr = merSeqNbr;
    }
    public String getMerSeqNbr() {
        return merSeqNbr;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    public String getTransDate() {
        return transDate;
    }

    public void setCodeStreamStr(String codeStreamStr) {
        this.codeStreamStr = codeStreamStr;
    }
    public String getCodeStreamStr() {
        return codeStreamStr;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeTypCd(String codeTypCd) {
        this.codeTypCd = codeTypCd;
    }
    public String getCodeTypCd() {
        return codeTypCd;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getPayType() {
        return payType;
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


}
