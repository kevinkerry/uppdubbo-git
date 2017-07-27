package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 挂入交易入参
 */
public class ReqPostSuspendRtxn extends ReqEAccountHead {
	public ReqPostSuspendRtxn(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.DoPostSuspendRtxn);
		this.setPayerCardNbr(data.getPayeracctnbr());
		this.setPayeeCardNbr(data.getPayeeacctnbr());
		this.setRecognitionId(data.getCheckdataflag());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
		this.setSummary(data.getPayeeacctnbr());
		this.setRtxnDesc("挂入");
		this.setPayeeName(data.getPayeename());
	}

	String payerCardNbr;
	String payeeCardNbr;
	String recognitionId;
	String currencyCd;
	BigDecimal tranAmt;
	String summary;
	String rtxnDesc;
	String payeeName;

	public String getPayerCardNbr() {
		return payerCardNbr;
	}

	public void setPayerCardNbr(String payerCardNbr) {
		this.payerCardNbr = payerCardNbr;
	}

	public String getPayeeCardNbr() {
		return payeeCardNbr;
	}

	public void setPayeeCardNbr(String payeeCardNbr) {
		this.payeeCardNbr = payeeCardNbr;
	}

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

	public String getRtxnDesc() {
		return rtxnDesc;
	}

	public void setRtxnDesc(String rtxnDesc) {
		this.rtxnDesc = rtxnDesc;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}	
}
