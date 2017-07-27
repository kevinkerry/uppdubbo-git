package com.csii.upp.dto.router.hvps;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.HvpsTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqHvpsReTraveRtxn extends ReqHvpsHead{
	private String syscode;  //系统标志
	private String orimsgid; //人行报文标识
	private String orisdbnk; //原发起行行号
	private String smr; //退汇原因
	
	public ReqHvpsReTraveRtxn(InputFundData data) {
		super(data);
		this.setTransCode(HvpsTransCode.Remittance);
		this.setSyscode(FundChannelCode.HVPS);
		this.setOrimsgid(data.getMsgid());
		this.setOrisdbnk(data.getPayeracctdeptnbr());
		this.setSmr("直销银行大额退汇");
	}

	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public String getOrimsgid() {
		return orimsgid;
	}

	public void setOrimsgid(String orimsgid) {
		this.orimsgid = orimsgid;
	}

	public String getOrisdbnk() {
		return orisdbnk;
	}

	public void setOrisdbnk(String orisdbnk) {
		this.orisdbnk = orisdbnk;
	}

	public String getSmr() {
		return smr;
	}

	public void setSmr(String smr) {
		this.smr = smr;
	}
}
