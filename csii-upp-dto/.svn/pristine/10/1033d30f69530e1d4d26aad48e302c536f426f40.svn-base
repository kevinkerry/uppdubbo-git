package com.csii.upp.dto.router.core;

import java.math.BigDecimal;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dto.extend.InputFundData;

public class ReqCreditVirAcctTransfer extends ReqCoreHead {

	private String payerAcctNbr;
	private String payerCardTypCd;
	private String payerAcctTypCd;
	private String payerAcctKind;
	private String payerOrgNbr;
	private String payerCardPwd;
	private String payerIdNbr;
	private String payerIdTypCd;
	private String payerCardExpiredDate; // 信用卡有效期
	private String payerCardCvv2; // 信用卡cvv
	private String currencyCd;
	private String processMode;
	private BigDecimal transAmt;
	private String payeeAcctNbr;
	private String payeeCardTypCd;
	private String payeeAcctTypCd;
	private String payeeAcctKind;
	private String payeePhoneNo;
	private String note;
	private String payeeName;
	private String payTypCd;
	
	public ReqCreditVirAcctTransfer(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.CreditVirAcctTransfer);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerCardTypCd(data.getPayercardtypcd());
		this.setPayerAcctTypCd(data.getPayeraccttypcd());
		this.setPayerAcctKind(data.getPayeracctkind());
		this.setPayerOrgNbr(data.getPayeracctdeptnbr());
		this.setPayerCardPwd(data.getPayercardpwd());
		this.setPayerIdNbr(data.getPayeridnbr());
		this.setPayerIdTypCd(data.getPayeridtypcd());
		this.setPayerCardExpiredDate(data.getPayercardexpireddate());
		this.setPayerCardCvv2(data.getPayercardcvv2());
		this.setCurrencyCd(data.getCurrencycd());
		this.setProcessMode(data.getProcessMode());
		this.setTransAmt(data.getTransamt());
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setPayeeCardTypCd(data.getPayeecardtypcd());
		this.setPayeeAcctKind(data.getPayeeacctkind());
		this.setPayeePhoneNo(data.getPayeephoneno());
		if(PayTypCd.FOSION.equals(data.getPaytypcd())){
			payTypCd = "丰收e支付消费";
		}else if(PayTypCd.CARDPWD.equals(data.getPaytypcd())){
			payTypCd = "银行卡支付消费";
		}else if(PayTypCd.OURCYBER.equals(data.getPaytypcd())){
			payTypCd = "网银支付消费";
		}
		this.setNote(this.getReqSeqNo().substring(this.getReqSeqNo().length() - 6) + this.getReqTime().substring(4));
		this.setPayeeName(data.getPayeename());
	}

	public String getPayerCardExpiredDate() {
		return payerCardExpiredDate;
	}

	public void setPayerCardExpiredDate(String payerCardExpiredDate) {
		this.payerCardExpiredDate = payerCardExpiredDate;
	}

	public String getPayerCardCvv2() {
		return payerCardCvv2;
	}

	public void setPayerCardCvv2(String payerCardCvv2) {
		this.payerCardCvv2 = payerCardCvv2;
	}
	
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}

	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}

	public String getPayerCardTypCd() {
		return payerCardTypCd;
	}

	public void setPayerCardTypCd(String payerCardTypCd) {
		this.payerCardTypCd = payerCardTypCd;
	}

	public String getPayerAcctTypCd() {
		return payerAcctTypCd;
	}

	public void setPayerAcctTypCd(String payerAcctTypCd) {
		this.payerAcctTypCd = payerAcctTypCd;
	}

	public String getPayerAcctKind() {
		return payerAcctKind;
	}

	public void setPayerAcctKind(String payerAcctKind) {
		this.payerAcctKind = payerAcctKind;
	}

	public String getPayerOrgNbr() {
		return payerOrgNbr;
	}

	public void setPayerOrgNbr(String payerOrgNbr) {
		this.payerOrgNbr = payerOrgNbr;
	}

	public String getPayerCardPwd() {
		return payerCardPwd;
	}

	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}

	public String getPayerIdNbr() {
		return payerIdNbr;
	}

	public void setPayerIdNbr(String payerIdNbr) {
		this.payerIdNbr = payerIdNbr;
	}

	public String getPayerIdTypCd() {
		return payerIdTypCd;
	}

	public void setPayerIdTypCd(String payerIdTypCd) {
		this.payerIdTypCd = payerIdTypCd;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	
    public String getProcessMode() {
		return processMode;
	}

	public void setProcessMode(String processMode) {
		this.processMode = processMode;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}

	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}

	public String getPayeeCardTypCd() {
		return payeeCardTypCd;
	}

	public void setPayeeCardTypCd(String payeeCardTypCd) {
		this.payeeCardTypCd = payeeCardTypCd;
	}

	public String getPayeeAcctTypCd() {
		return payeeAcctTypCd;
	}

	public void setPayeeAcctTypCd(String payeeAcctTypCd) {
		this.payeeAcctTypCd = payeeAcctTypCd;
	}

	public String getPayeeAcctKind() {
		return payeeAcctKind;
	}

	public void setPayeeAcctKind(String payeeAcctKind) {
		this.payeeAcctKind = payeeAcctKind;
	}

	public String getPayeePhoneNo() {
		return payeePhoneNo;
	}

	public void setPayeePhoneNo(String payeePhoneNo) {
		this.payeePhoneNo = payeePhoneNo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}
	
}

