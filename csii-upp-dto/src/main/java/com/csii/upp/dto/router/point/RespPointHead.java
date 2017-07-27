package com.csii.upp.dto.router.point;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespPointHead extends RespAppHead {
	
	private String protocolVersion;//协议版本
	private String requestId;//查询请求ID
	private String cmdId;//命令ID
	private String occuDate;
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.JFWG);
		setDownrtxnnbr(resultStatus);
		setReturncode(resultStatus);
		setRtxnstate(Integer.parseInt(resultStatus)>0? TransStatus.SUCCESS : TransStatus.FAILURE);
	}
	public String getCmdId() {
		return cmdId;
	}
	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getOccuDate() {
		return occuDate;
	}
	public void setOccuDate(String occuDate) {
		this.occuDate = occuDate;
	}
	
	

}
