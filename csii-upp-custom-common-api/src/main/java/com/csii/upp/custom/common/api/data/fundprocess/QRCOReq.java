package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessReqHead;

public class QRCOReq extends FundProcessReqHead {
	private static final long serialVersionUID = -6468151048166936757L;
    private String transAmt; 
    private String code; 
    private String url; 
    private String merNbr; 
    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setMerNbr(String merNbr) {
        this.merNbr = merNbr;
    }
    public String getMerNbr() {
        return merNbr;
    }


}
