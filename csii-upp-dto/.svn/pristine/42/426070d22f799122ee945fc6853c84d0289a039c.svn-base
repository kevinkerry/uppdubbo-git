package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.StringUtil;

public class ReqDepositOnCreditAcct extends ReqEAccountHead {

	public ReqDepositOnCreditAcct(InputFundData data) {
		super(data);
		setTransCode("502380");
		this.setRecognitionId(data.getCheckdataflag());
		this.setTranAmt(data.getTransamt());
		this.setCurrencyCd(data.getCurrencycd());
		this.setSummary("挂账");
		if(!StringUtil.isStringEmpty(data.getNote())){
			this.setSummary(StringUtil.buildString("挂账","[",data.getNote()+"]"));
		}
		this.setPayerCardNbr(data.getPayeracctnbr());
		this.setPayerName(data.getPayername());
	}

	String recognitionId;
	String currencyCd;
	BigDecimal tranAmt;
	String summary;
	String payerCardNbr;
	String payerName;

	public String getRecognitionId() {
		return recognitionId;
	}

	public void setRecognitionId(String recognitionId) {
		this.recognitionId = recognitionId;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
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

	public String getPayerCardNbr() {
		return payerCardNbr;
	}

	public void setPayerCardNbr(String payerCardNbr) {
		this.payerCardNbr = payerCardNbr;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}	
}
