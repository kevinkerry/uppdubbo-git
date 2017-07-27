package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class SMSXResp extends FundProcessRespHead {
	private static final long serialVersionUID = -5308683335471629100L;
    private String smsInnerFundTransNbr; 
    private String sendUnionPayTime; 
    public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
        this.smsInnerFundTransNbr = smsInnerFundTransNbr;
    }
    public String getSmsInnerFundTransNbr() {
        return smsInnerFundTransNbr;
    }

    public void setSendUnionPayTime(String sendUnionPayTime) {
        this.sendUnionPayTime = sendUnionPayTime;
    }
    public String getSendUnionPayTime() {
        return sendUnionPayTime;
    }


}
