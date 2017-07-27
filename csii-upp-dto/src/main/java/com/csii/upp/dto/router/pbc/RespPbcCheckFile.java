package com.csii.upp.dto.router.pbc;

public class RespPbcCheckFile extends RespPbcHead {
	private String collatingdate;//对账日期
	private String realcollecttotal;//往账总笔数
	private String accessflag;	//总笔数
	private String filepath;//文件路径
	public String getCollatingdate() {
		return collatingdate;
	}
	public void setCollatingdate(String collatingdate) {
		this.collatingdate = collatingdate;
	}
	public String getRealcollecttotal() {
		return realcollecttotal;
	}
	public void setRealcollecttotal(String realcollecttotal) {
		this.realcollecttotal = realcollecttotal;
	}

	public String getAccessflag() {
		return accessflag;
	}
	public void setAccessflag(String accessflag) {
		this.accessflag = accessflag;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
