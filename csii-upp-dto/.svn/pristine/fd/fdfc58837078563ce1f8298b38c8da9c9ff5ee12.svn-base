package com.csii.upp.dto.router.cnaps2;

public class RespReturnRemittance extends RespCnaps2Head {
	
	public RespReturnRemittance(){
		this.setErrCd(this.getReturncode());
		this.setErrDesc(this.getReturnmsg());
		this.setPrcSts(this.getStatus());
	}
	private String prcSts;//受理状态
	private String errCd;//处理码
	private String errDesc;//处理描述
	public String getPrcSts() {
		return prcSts;
	}
	public void setPrcSts(String prcSts) {
		this.prcSts = prcSts;
	}
	public String getErrCd() {
		return errCd;
	}
	public void setErrCd(String errCd) {
		this.errCd = errCd;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
	
}
