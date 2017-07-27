package com.csii.upp.dto.router.beps;



/**
 * 跨行小额转账
 * @author wy
 *
 */
public class RespBepsVirAcctWithdrawl extends RespBepsHead {
	private String transDate;
	private String transSeqnbr;
	private String acctAmt;
	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransSeqnbr() {
		return transSeqnbr;
	}

	public void setTransSeqnbr(String transSeqnbr) {
		this.transSeqnbr = transSeqnbr;
	}

	public String getAcctAmt() {
		return acctAmt;
	}

	public void setAcctAmt(String acctAmt) {
		this.acctAmt = acctAmt;
	}
	

}
