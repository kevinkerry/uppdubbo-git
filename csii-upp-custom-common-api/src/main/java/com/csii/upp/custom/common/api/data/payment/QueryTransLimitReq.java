package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryTransLimitReq extends PaymentReqHead {
	private static final long serialVersionUID = -8771655061233515569L;
    private String payerAcctNbr; 
    private String payerPhoneNo; 
    private String signNbr; 
    private String signTypCd; 
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

    public void setSignNbr(String signNbr) {
        this.signNbr = signNbr;
    }
    public String getSignNbr() {
        return signNbr;
    }

    public void setSignTypCd(String signTypCd) {
        this.signTypCd = signTypCd;
    }
    public String getSignTypCd() {
        return signTypCd;
    }


}
