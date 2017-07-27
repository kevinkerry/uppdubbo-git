/**
 * 
 */
package com.csii.upp.dto.router.ibps;

import com.csii.upp.constant.ResponseCode;

/**
 * 超级网银处理结果查询输出参数
 * 
 * @author 姜星
 * 
 */
public class RespIbpsQueryRtxn extends RespIbpsHead {
	private String refno; // 原支付流水号
	private String channelno; // 原渠道流水号
	private String trandate; // 原交易日期
	private String branchid; // 原渠道机构号
	private String contractno; // 授权支付协议号/账户信息查询付协议号
	// private String status; // 协议状态
	private String processstatus; // 原业务返回码

	public String getRefno() {
		return this.refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getChannelno() {
		return this.channelno;
	}

	public void setChannelno(String channelno) {
		this.channelno = channelno;
	}

	public String getTrandate() {
		return this.trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	public String getBranchid() {
		return this.branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	public String getContractno() {
		return this.contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	// public String getStatus() {
	// return this.status;
	// }
	//
	// public void setStatus(String status) {
	// this.status = status;
	// }

	public String getProcessstatus() {
		return this.processstatus;
	}

	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
		this.setResultStatus(("PR04".equals(processstatus) || "PR99"
				.equals(processstatus)) ? ResponseCode.SUCCESS : ResponseCode.FAILURE);
		this.setRtxnstateByAttribute();
	}
}
