package com.csii.upp.dto.router.beps;

import com.csii.upp.constant.ConstBeps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqBepsHead extends ReqAppHead {
	private String receiver;
	public ReqBepsHead(InputFundData data) {
		super(data);
		this.setTime(this.getReqTime().substring(8, 14));
		this.setSystime(this.getReqTime());
		this.setReceiver(ConstBeps.RECEIVER_BEPS);
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.BEPS);
		this.setSrvChnlId(FundChannelCode.BEPS);
		this.replaceTransNbr(data);
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
