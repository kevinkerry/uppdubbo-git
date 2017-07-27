package com.csii.upp.custom.common.api.data.payment;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class XMLimitCXResp extends PaymentRespHead {
	private static final long serialVersionUID = -6513104054105068090L;
    private String creditLimitAmtRemain1; 
    private String creditLimitAmtRemain2; 
    public void setCreditLimitAmtRemain1(String creditLimitAmtRemain1) {
        this.creditLimitAmtRemain1 = creditLimitAmtRemain1;
    }
    public String getCreditLimitAmtRemain1() {
        return creditLimitAmtRemain1;
    }

    public void setCreditLimitAmtRemain2(String creditLimitAmtRemain2) {
        this.creditLimitAmtRemain2 = creditLimitAmtRemain2;
    }
    public String getCreditLimitAmtRemain2() {
        return creditLimitAmtRemain2;
    }


}
