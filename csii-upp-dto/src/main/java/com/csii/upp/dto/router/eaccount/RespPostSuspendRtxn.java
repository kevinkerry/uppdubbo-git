package com.csii.upp.dto.router.eaccount;

public class RespPostSuspendRtxn extends RespEAccountHead {
	String txnSeqId;

	public String getTxnSeqId() {
		return txnSeqId;
	}

	public void setTxnSeqId(String txnSeqId) {
		this.txnSeqId = txnSeqId;
	}
	
}
