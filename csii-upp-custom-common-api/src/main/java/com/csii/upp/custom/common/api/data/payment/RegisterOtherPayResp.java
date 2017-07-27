package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class RegisterOtherPayResp extends PaymentRespHead {
	private static final long serialVersionUID = -5639612149892142821L;
    private String returnForm; 
    private String merURL1; 
    public void setReturnForm(String returnForm) {
        this.returnForm = returnForm;
    }
    public String getReturnForm() {
        return returnForm;
    }

    public void setMerURL1(String merURL1) {
        this.merURL1 = merURL1;
    }
    public String getMerURL1() {
        return merURL1;
    }


}
