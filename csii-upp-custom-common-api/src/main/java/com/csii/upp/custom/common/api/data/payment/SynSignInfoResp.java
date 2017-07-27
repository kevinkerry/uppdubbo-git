package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class SynSignInfoResp extends PaymentRespHead {
	private static final long serialVersionUID = -8338405638818842881L;
    private String transId; 
    private String synType; 
    private String acctNo; 
    private String modifyDate; 
    private String modifyUser; 
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public String getTransId() {
        return transId;
    }

    public void setSynType(String synType) {
        this.synType = synType;
    }
    public String getSynType() {
        return synType;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
    public String getAcctNo() {
        return acctNo;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    public String getModifyUser() {
        return modifyUser;
    }


}
