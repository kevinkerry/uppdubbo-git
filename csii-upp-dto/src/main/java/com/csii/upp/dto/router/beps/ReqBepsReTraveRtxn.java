package com.csii.upp.dto.router.beps;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.HvpsTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqBepsReTraveRtxn extends ReqBepsHead {
	
	private String syscode;  //系统标志
	private String orimsgid; //人行报文标识
	private String orisdbnk;  //原发起行行号
	private String smr;       //退汇原因
	
	public ReqBepsReTraveRtxn(InputFundData data) {
		super(data);
		this.setTransCode((HvpsTransCode.Remittance));
		this.setSyscode(FundChannelCode.BEPS);
		this.setOrimsgid(data.getMsgid());
		this.setOrisdbnk(data.getPayeracctdeptnbr());
		this.setSmr("直销银行小额退汇");
	}

	public String getSmr() {
		return smr;
	}

	public void setSmr(String smr) {
		this.smr = smr;
	}

	public String getOrisdbnk() {
		return orisdbnk;
	}

	public void setOrisdbnk(String orisdbnk) {
		this.orisdbnk = orisdbnk;
	}

	public String getOrimsgid() {
		return orimsgid;
	}

	public void setOrimsgid(String orimsgid) {
		this.orimsgid = orimsgid;
	}

	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}
}
