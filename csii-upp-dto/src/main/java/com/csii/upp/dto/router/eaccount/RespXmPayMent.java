package com.csii.upp.dto.router.eaccount;
/**
 * 单笔借记返回参数
 * @author 徐锦
 *
 */
public class RespXmPayMent extends RespEAccountHead{

	private String txnSeqId;

	public String getTxnSeqId() {
		return txnSeqId;
	}
	public void setTxnSeqId(String txnSeqId) {
		this.txnSeqId = txnSeqId;
	}

}
