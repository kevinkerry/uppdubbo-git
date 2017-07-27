package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class CancelOrderReq extends PaymentReqHead {
	private static final long serialVersionUID = -8254370541415586660L;
    private String origMerSeqNbr; 
    private String origMerDateTime; 
    private String origTransAmt; 
    private String reasonDesc; 
    private String msgExt; 

    public void setOrigMerSeqNbr(String origMerSeqNbr) {
        this.origMerSeqNbr = origMerSeqNbr;
    }
    public String getOrigMerSeqNbr() {
        return origMerSeqNbr;
    }

    public void setOrigMerDateTime(String origMerDateTime) {
        this.origMerDateTime = origMerDateTime;
    }
    public String getOrigMerDateTime() {
        return origMerDateTime;
    }

    public void setOrigTransAmt(String origTransAmt) {
        this.origTransAmt = origTransAmt;
    }
    public String getOrigTransAmt() {
        return origTransAmt;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }
    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }
    public String getMsgExt() {
        return msgExt;
    }


}
