package com.csii.upp.dto.router.eaccount;

import java.math.BigDecimal;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqAcctRecharge extends ReqEAccountHead {
	public ReqAcctRecharge(InputFundData data) {
		super(data);
		/*
		setTransCode(EAccountTransCode.AcctRecharge);
		this.setPayerCardNbr(data.getPayeracctnbr());
		this.setPayeeCardNbr(data.getPayeeacctnbr());
		this.setPayerName(data.getPayername());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
		this.setMediPwd(data.getPayercardpwd());
		this.setRtxnDesc(data.getNote());
		this.setPayeeName(data.getPayeename());*/
		//
		setTransCode(EAccountTransCode.AcctRecharge);
		this.setPayerCardNbr(data.getPayeracctnbr());
		this.setPayerName(data.getPayername());
		this.setMediId(data.getPayeeacctnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTranAmt(data.getTransamt());
		this.setMediPwd(data.getPayercardpwd());
		this.setRtxnDesc(data.getNote());
		this.setOwnerName(data.getPayeename());
		this.setMediPwdCheck("0");
	}

	String payerCardNbr;
	String mediId;
	String payerName;
	String payeeCardNbr;
	String currencyCd;
	BigDecimal tranAmt;
	String mediPwdCheck;
	String ownerName;
	private String payeeName;
	

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

	public String getPayeeCardNbr() {
		return payeeCardNbr;
	}

	public void setPayeeCardNbr(String payeeCardNbr) {
		this.payeeCardNbr = payeeCardNbr;
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

	public String getMediPwdCheck() {
		return mediPwdCheck;
	}

	public void setMediPwdCheck(String mediPwdCheck) {
		this.mediPwdCheck = mediPwdCheck;
	}

	public String getMediPwd() {
		return mediPwd;
	}

	public void setMediPwd(String mediPwd) {
		this.mediPwd = mediPwd;
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

	public String getMediId() {
		return mediId;
	}

	public void setMediId(String mediId) {
		this.mediId = mediId;
	}

	String mediPwd;
	String summary;
	String rtxnDesc;


	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
