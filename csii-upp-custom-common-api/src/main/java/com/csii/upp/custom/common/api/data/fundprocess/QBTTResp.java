package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class QBTTResp extends FundProcessRespHead {
	private static final long serialVersionUID = -4670291877021923195L;
    private String transStatus; 
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }
    public String getTransStatus() {
        return transStatus;
    }


}
