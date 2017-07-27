package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class CancelOrderResp extends PaymentRespHead {
	private static final long serialVersionUID = -6376152096950787149L;
    private String transId; 
    private String merSeqNo; 
    private String merchantId; 
    private String transAmt; 
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public String getTransId() {
        return transId;
    }

    public void setMerSeqNo(String merSeqNo) {
        this.merSeqNo = merSeqNo;
    }
    public String getMerSeqNo() {
        return merSeqNo;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    public String getMerchantId() {
        return merchantId;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }


}
