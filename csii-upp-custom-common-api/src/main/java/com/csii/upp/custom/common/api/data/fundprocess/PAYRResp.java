package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class PAYRResp extends FundProcessRespHead {
	private static final long serialVersionUID = -8407299880010410662L;
    private String hostClearDate; 
    private String transStatus; 
    public void setHostClearDate(String hostClearDate) {
        this.hostClearDate = hostClearDate;
    }
    public String getHostClearDate() {
        return hostClearDate;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }
    public String getTransStatus() {
        return transStatus;
    }


}
