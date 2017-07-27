package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqItlPayment extends ReqEAccountHead {
	private BigDecimal tranAmt;  //金额
	private String currencyCd;	// 币种
	private String summary;		//摘要
	private String rtxnDesc;	//备注
	
	public ReqItlPayment(InputFundData data) {
		super(data);
		setTransCode(EAccountTransCode.ItlPayment);
		this.setTranAmt(data.getTransamt());
		this.setCurrencyCd(data.getCurrencycd());
		this.setSummary("应付手续费");
		this.setRtxnDesc("应付手续费");
	}
	
	public BigDecimal getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
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

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
}
