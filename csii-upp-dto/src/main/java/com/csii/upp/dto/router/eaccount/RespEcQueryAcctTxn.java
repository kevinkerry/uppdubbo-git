package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;
import java.util.Date;

public class RespEcQueryAcctTxn extends RespEAccountHead {
	private String txnseqid;
	private Date postdate;
	private String exttxnid;
	private Date exttxndate;
	private String rtxnsourcecd;
	private String txnstat;
	public String getTxnseqid() {
		return txnseqid;
	}
	public void setTxnseqid(String txnseqid) {
		this.txnseqid = txnseqid;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getExttxnid() {
		return exttxnid;
	}
	public void setExttxnid(String exttxnid) {
		this.exttxnid = exttxnid;
	}
	public Date getExttxndate() {
		return exttxndate;
	}
	public void setExttxndate(Date exttxndate) {
		this.exttxndate = exttxndate;
	}
	public String getRtxnsourcecd() {
		return rtxnsourcecd;
	}
	public void setRtxnsourcecd(String rtxnsourcecd) {
		this.rtxnsourcecd = rtxnsourcecd;
	}
	public String getTxnstat() {
		return txnstat;
	}
	public void setTxnstat(String txnstat) {
		this.txnstat = txnstat;
	}
	public String getMediid() {
		return mediid;
	}
	public void setMediid(String mediid) {
		this.mediid = mediid;
	}
	public BigDecimal getTranamt() {
		return tranamt;
	}
	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}
	private String mediid;
	private BigDecimal tranamt;
}
