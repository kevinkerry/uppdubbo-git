package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessReqHead;

public class QRTSReq extends FundProcessReqHead {
	private static final long serialVersionUID = -5742317098504478674L;
    private String origUpperTransNbr; 
    public void setOrigUpperTransNbr(String origUpperTransNbr) {
        this.origUpperTransNbr = origUpperTransNbr;
    }
    public String getOrigUpperTransNbr() {
        return origUpperTransNbr;
    }


}
