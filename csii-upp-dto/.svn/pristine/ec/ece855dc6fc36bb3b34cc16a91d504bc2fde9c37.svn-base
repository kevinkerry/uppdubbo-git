package com.csii.upp.dto.router.core;

import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqCoreHead extends ReqAppHead {
	private String receiver; //请求接收方,核心:3101  信用卡 :3901
	private String checkNumber;
	public ReqCoreHead(InputFundData data) {
		super(data);
		this.setTime(this.getReqTime());
		this.setSystime(this.getReqDate());
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.CORE);
		this.setSrvChnlId(FundChannelCode.CORE);
		StringBuilder checkNumberSb=new StringBuilder();
		checkNumberSb.append(ConstCore.CHANNELNO).append(FundChannelCode.FDPS);
		if(CardTypCd.CREDIT.equals(data.getPayercardtypcd())){
			this.setReceiver(ConstCore.RECEIVER_CREDIT);
			checkNumberSb.append("C");
		}else {
			this.setReceiver(ConstCore.RECEIVER_CORE);
			checkNumberSb.append("D");
		}
		this.setCheckNumber(checkNumberSb.append(this.getReqDate()).toString());
	}
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
    public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
}
