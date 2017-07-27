package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class GenerrateSubMerchantIdReq extends PaymentReqHead {
	private static final long serialVersionUID = -6678559990770235440L;
    private String outSubMerchantId; 

    public void setOutSubMerchantId(String outSubMerchantId) {
        this.outSubMerchantId = outSubMerchantId;
    }
    public String getOutSubMerchantId() {
        return outSubMerchantId;
    }


}
