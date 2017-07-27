package com.csii.upp.dto.router.beps;

import com.csii.upp.util.StringUtil;



public class RespBepsCheck extends RespBepsHead {
	private String bepssendtotal;//往账总笔数
	private String bepsrcvtotal;//来账总笔数
	private String bepstotalnum;	//总笔数
	private String msgtype;//报文类型
	private String filepath;//文件路径
	private String fileName; //文件名
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getBepssendtotal() {
		return bepssendtotal;
	}
	public void setBepssendtotal(String bepssendtotal) {
		this.bepssendtotal = bepssendtotal;
	}
	public String getBepsrcvtotal() {
		return bepsrcvtotal;
	}
	public void setBepsrcvtotal(String bepsrcvtotal) {
		this.bepsrcvtotal = bepsrcvtotal;
	}
	public String getBepstotalnum() {
		return bepstotalnum;
	}
	public void setBepstotalnum(String bepstotalnum) {
		this.bepstotalnum = bepstotalnum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		if(StringUtil.isStringEmpty(fileName))
			return;
		this.fileName = fileName;
	}
}
