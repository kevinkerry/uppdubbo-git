package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QueryVirtualAcctBalanceResp extends PaymentRespHead {
	private static final long serialVersionUID = -6000153802252448130L;
    private String transId; 
    private String merchantId; 
    private String subMerchantId; 
    private String userNo; 
    private String merDateTime; 
    private String totalBal; 
    private String availableBal; 
    private String blockedBal; 
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

    public void setMerDateTime(String merDateTime) {
        this.merDateTime = merDateTime;
    }
    public String getMerDateTime() {
        return merDateTime;
    }

    public void setTotalBal(String totalBal) {
        this.totalBal = totalBal;
    }
    public String getTotalBal() {
        return totalBal;
    }

    public void setAvailableBal(String availableBal) {
        this.availableBal = availableBal;
    }
    public String getAvailableBal() {
        return availableBal;
    }

    public void setBlockedBal(String blockedBal) {
        this.blockedBal = blockedBal;
    }
    public String getBlockedBal() {
        return blockedBal;
    }


}
