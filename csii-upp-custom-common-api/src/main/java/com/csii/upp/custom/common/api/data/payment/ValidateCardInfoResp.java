package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class ValidateCardInfoResp extends PaymentRespHead {
	private static final long serialVersionUID = -6673171635373582339L;
    private String transId; 
    private String merchantId; 
    private String account; 
    private String checkStatus; 
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

    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    public String getCheckStatus() {
        return checkStatus;
    }


}
