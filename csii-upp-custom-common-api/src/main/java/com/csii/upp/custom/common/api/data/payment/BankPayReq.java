package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class BankPayReq extends PaymentReqHead {
	private static final long serialVersionUID = -5142635370856599242L;
    private String cyberTypCd; 
    private String subMerchantId; 
    private String userNbr; 
    public void setCyberTypCd(String cyberTypCd) {
        this.cyberTypCd = cyberTypCd;
    }
    public String getCyberTypCd() {
        return cyberTypCd;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }
    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setUserNbr(String userNbr) {
        this.userNbr = userNbr;
    }
    public String getUserNbr() {
        return userNbr;
    }


}
