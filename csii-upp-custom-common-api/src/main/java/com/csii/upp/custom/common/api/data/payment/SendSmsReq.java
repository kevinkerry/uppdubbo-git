package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class SendSmsReq extends PaymentReqHead {
	private static final long serialVersionUID = -5114265272176272533L;
    private String payerPhoneNo; 
    private String payerAcctNbr; 
    private String transTypCd; 
    private String transAmt; 
    private String payerAcctDeptNbr; 
    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setTransTypCd(String transTypCd) {
        this.transTypCd = transTypCd;
    }
    public String getTransTypCd() {
        return transTypCd;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
        this.payerAcctDeptNbr = payerAcctDeptNbr;
    }
    public String getPayerAcctDeptNbr() {
        return payerAcctDeptNbr;
    }


}
