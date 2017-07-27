package com.csii.upp.dto.router.pbc;

import java.util.Date;

public class RespPbcCollectionRtxn extends RespPbcHead{
	
	
	private Date busidate;

	private String responsecode;
	private String responsemessage;
	private String postscript;
	private String remark;
	private Date accepttimestamp;
	public Date getBusidate() {
		return busidate;
	}
	public void setBusidate(Date busidate) {
		this.busidate = busidate;
	}
	
	public String getResponsecode() {
		return responsecode;
	}
	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
	public String getResponsemessage() {
		return responsemessage;
	}
	public void setResponsemessage(String responsemessage) {
		this.responsemessage = responsemessage;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAccepttimestamp() {
		return accepttimestamp;
	}
	public void setAccepttimestamp(Date accepttimestamp) {
		this.accepttimestamp = accepttimestamp;
	}
	
	
}
