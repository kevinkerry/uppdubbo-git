package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class QueryVirtualAcctBalanceReq extends PaymentReqHead {
	private static final long serialVersionUID = -8140229495929923553L;
    private String userNbr; 
    private String subMerchantId; 
    private String merTransDateTime; 
    private String currencyCd; 
    private String payeeAcctNbr; 
    private String msgExt; 

    public void setUserNbr(String userNbr) {
        this.userNbr = userNbr;
    }
    public String getUserNbr() {
        return userNbr;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }
    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setMerTransDateTime(String merTransDateTime) {
        this.merTransDateTime = merTransDateTime;
    }
    public String getMerTransDateTime() {
        return merTransDateTime;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setPayeeAcctNbr(String payeeAcctNbr) {
        this.payeeAcctNbr = payeeAcctNbr;
    }
    public String getPayeeAcctNbr() {
        return payeeAcctNbr;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }
    public String getMsgExt() {
        return msgExt;
    }


}
