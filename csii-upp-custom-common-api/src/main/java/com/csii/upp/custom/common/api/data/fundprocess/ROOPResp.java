package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class ROOPResp extends FundProcessRespHead {
	private static final long serialVersionUID = -7606687269477678037L;
    private String returnForm; 
    private String hostClearDate; 
    public void setReturnForm(String returnForm) {
        this.returnForm = returnForm;
    }
    public String getReturnForm() {
        return returnForm;
    }

    public void setHostClearDate(String hostClearDate) {
        this.hostClearDate = hostClearDate;
    }
    public String getHostClearDate() {
        return hostClearDate;
    }


}
