package com.csii.upp.dto.router.pbc;

import com.csii.upp.constant.AccessFlag;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqPbcHead extends ReqAppHead{
	protected String feecode;
	protected String accessflag;
	protected String clientno;
	protected String contractid;
	public ReqPbcHead(InputFundData data) {
		super(data);
		this.setChnlId(FundChannelCode.PAY);
		this.setSrvChnlId(FundChannelCode.PBC);
		data.setFundchannelcode(FundChannelCode.PBC);
		this.setAccessflag(AccessFlag.eAccountFlag);
		//this.setAgentbankcode(AgentBank.YYFlag);
		this.setFeecode("");//费项代码
		this.setContractid(contractid);//企业代码
		this.setClientno(clientno);//签订协议客户号
	}
	
	public String getAccessflag() {
		return accessflag;
	}
	public void setAccessflag(String accessflag) {
		this.accessflag = accessflag;
	}
	public String getClientno() {
		return clientno;
	}
	public void setClientno(String clientno) {
		this.clientno = clientno;
	}

	public String getFeecode() {
		return feecode;
	}

	public void setFeecode(String feecode) {
		this.feecode = feecode;
	}

	public String getContractid() {
		return contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}
	
}
