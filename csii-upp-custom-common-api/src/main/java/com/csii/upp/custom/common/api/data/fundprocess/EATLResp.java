package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class EATLResp extends FundProcessRespHead {
	private static final long serialVersionUID = -7791159798464683332L;
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
