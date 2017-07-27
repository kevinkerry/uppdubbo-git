package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.ConstEAccount;
import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqDepRtxnReversal extends ReqEAccountHead {

	private String extTxnId;  //外部渠道流水号
	private String reversalTypCd;  //冲正类型
	private String summary;  //交易概要
	private String rtxnDesc;  //交易描述
	public ReqDepRtxnReversal(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.DepRtxnReversal);
		this.setExtTxnId(data.getOriginnertransnbr());
		this.setReversalTypCd(ConstEAccount.BLUE);
		this.setSummary("存款交易冲正");
		this.setRtxnDesc("存款交易冲正");
	}
	
	public String getExtTxnId() {
		return extTxnId;
	}
	public void setExtTxnId(String extTxnId) {
		this.extTxnId = extTxnId;
	}
	public String getReversalTypCd() {
		return reversalTypCd;
	}
	public void setReversalTypCd(String reversalTypCd) {
		this.reversalTypCd = reversalTypCd;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRtxnDesc() {
		return rtxnDesc;
	}
	public void setRtxnDesc(String rtxnDesc) {
		this.rtxnDesc = rtxnDesc;
	}

}
