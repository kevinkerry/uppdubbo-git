package com.csii.upp.dto.router.beps;

public class RespBepsReTraveRtxn extends RespBepsHead {
	private String syslog;  //支付系统流水号
	private String msgid;  //报文标识号
	private String bussts; //业务状态
	public String getSyslog() {
		return syslog;
	}
	public void setSyslog(String syslog) {
		this.syslog = syslog;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getBussts() {
		return bussts;
	}
	public void setBussts(String bussts) {
		this.bussts = bussts;
	}

}
