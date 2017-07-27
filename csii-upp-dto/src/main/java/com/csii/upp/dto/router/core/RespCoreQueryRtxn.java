package com.csii.upp.dto.router.core;

/**
 * @author zgb
 *
 */
public class RespCoreQueryRtxn extends RespCoreHead {
	private String tranDate; // 交易日期
	private String origSeqNbr; // 原交易流水号
	private String transAmt; // 实际交易金额
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getOrigSeqNbr() {
		return origSeqNbr;
	}
	public void setOrigSeqNbr(String origSeqNbr) {
		this.origSeqNbr = origSeqNbr;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	
}
