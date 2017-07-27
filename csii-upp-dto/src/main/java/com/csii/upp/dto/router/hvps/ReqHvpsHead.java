package com.csii.upp.dto.router.hvps;

import com.csii.upp.constant.ConstHvps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqHvpsHead extends ReqAppHead {
	private String receiver;
	public ReqHvpsHead(InputFundData data) {
		super(data);
		this.setTime(this.getReqTime().substring(8, 14));
		this.setSystime(this.getReqTime());
		this.setReceiver(ConstHvps.RECEIVER_HVPS);
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.HVPS);
		this.setSrvChnlId(FundChannelCode.HVPS);
		this.replaceTransNbr(data);
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
}
