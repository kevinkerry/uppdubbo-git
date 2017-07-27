package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqReturnRemittance extends ReqEAccountHead {

	public ReqReturnRemittance(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.ReturnRemittance);
		this.setRecognitionId(data.getCheckdataflag());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
		this.setSummary("挂退");
		this.setPayeeCardNbr(data.getPayeracctnbr());
		this.setPayeeName(data.getPayername());
	}

	String recognitionId;
	String currencyCd;
	BigDecimal tranAmt;
	String summary;
	String payeeCardNbr;
	String payeeName;
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
	public String getPayeeCardNbr() {
		return payeeCardNbr;
	}
	public void setPayeeCardNbr(String payeeCardNbr) {
		this.payeeCardNbr = payeeCardNbr;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}	
}
