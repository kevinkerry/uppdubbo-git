package com.csii.upp.dto.router.point;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;
import com.csii.upp.supportor.IDGenerateFactory;

public class ReqPointHead extends ReqAppHead {
	
	private String requestId;
	private String suffixUrl;      //请求地址后缀
	private String protocolVersion; //协议版本
	
	public ReqPointHead(InputFundData data) {
		super(data);
		this.setTime(this.getReqTime().substring(8, 14));
		this.setChnlId(FundChannelCode.PAY);
		this.setSrvChnlId(FundChannelCode.JFWG);
		data.setFundchannelcode(FundChannelCode.JFWG);
		this.setRequestId(IDGenerateFactory.generateSeqId());
		this.setProtocolVersion("1.0");
	}
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSuffixUrl() {
		return suffixUrl;
	}
	public void setSuffixUrl(String suffixUrl) {
		this.suffixUrl = suffixUrl;
	}
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	
	
}
