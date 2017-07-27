package com.csii.upp.dto.router;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * 发送路由请求应用报文头
 * 
 * @author 徐锦
 *
 */
public class ReqAppHead extends ReqSysHead {
	public ReqAppHead(InputFundData data) {
		super(data);
		data.setTransnbr(IDGenerateFactory.generateInnerFundTransNbr());
		data.setInnerfundtransnbr(data.getTransnbr());
		this.setReqSeqNo(data.getTransnbr());
	}
	
	/**
	 * 二代需要换流水号
	 * @param data
	 */
	protected void replaceTransNbr(InputFundData data){
		if(this.getReqSeqNo().length()!=8){
			this.setReqSeqNo(this.getReqSeqNo().substring(4));
			data.setTransnbr(this.getReqSeqNo());
		}
		if(data.getInnerfundtransnbr().length()!=8){
			data.setInnerfundtransnbr(this.getReqSeqNo());
		}
	}
	
	private String time; 
	private String systime; 
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}
}
