/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年1月21日 上午9:55:12
 * vc: $Id: $
 */
package com.csii.upp.dto.router.core;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.StringUtil;

/**
 * 查询内部账户详细信息
 * @author qgs
 *
 */
public class ReqVirAcctTransfer extends ReqCoreHead {

	private String payerAcctNbr;
	private String payerCardTypCd;
	private String payerAcctTypCd;
	private String payerAcctKind;
	private String payerOrgNbr;
	private String payerCardPwd;
	private String payerIdNbr;
	private String payerIdTypCd;
	private String currencyCd;
	private String processMode;
	private BigDecimal transAmt;
	private String payeeAcctNbr;
	private String payeeCardTypCd;
	private String payeeAcctTypCd;
	private String payeeAcctKind;
	private String payeeCardExpiredDate;
	private String payeeCardCvv2;
	private String payeePhoneNo;
	private String note;
	private String checkPwdFlag;//验密标志
	private String withFlag;//支取方式
	private String payeeName;

	public ReqVirAcctTransfer(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.VirAcctTransfer);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerCardTypCd(data.getPayercardtypcd());
		this.setPayerAcctTypCd(data.getPayeraccttypcd());
		this.setPayerAcctKind(data.getPayeracctkind());
		this.setPayerOrgNbr(data.getPayeracctdeptnbr());
		this.setPayerCardPwd(data.getPayercardpwd());
		if(StringUtil.isStringEmpty(this.payerCardPwd)){
			this.setCheckPwdFlag(ConstCore.CHECKPWDFLAG_N);
			this.setWithFlag(ConstCore.WITHFLAG_A);
		}else{
			this.setCheckPwdFlag(ConstCore.CHECKPWDFLAG_Y);
			this.setWithFlag(ConstCore.WITHFLAG_B);
		}
		this.setPayerIdNbr(data.getPayeridnbr());
		this.setPayerIdTypCd(data.getPayeridtypcd());
		this.setCurrencyCd(data.getCurrencycd());
		this.setProcessMode(data.getProcessMode());
		this.setTransAmt(data.getTransamt());
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setPayeeCardTypCd(data.getPayeecardtypcd());
		this.setPayeeAcctKind(data.getPayeeacctkind());
		this.setPayeeCardExpiredDate(data.getPayeecardexpireddate());
		this.setPayeeCardCvv2(data.getPayeecardcvv2());
		this.setPayeePhoneNo(data.getPayeephoneno());
		this.setNote(data.getNote());
		this.setPayeeName(data.getPayeename());
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


	public String getPayeeCardExpiredDate() {
		return payeeCardExpiredDate;
	}


	public void setPayeeCardExpiredDate(String payeeCardExpiredDate) {
		this.payeeCardExpiredDate = payeeCardExpiredDate;
	}


	public String getPayeeCardCvv2() {
		return payeeCardCvv2;
	}


	public void setPayeeCardCvv2(String payeeCardCvv2) {
		this.payeeCardCvv2 = payeeCardCvv2;
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

	public String getCheckPwdFlag() {
		return checkPwdFlag;
	}

	public void setCheckPwdFlag(String checkPwdFlag) {
		this.checkPwdFlag = checkPwdFlag;
	}

	public String getWithFlag() {
		return withFlag;
	}

	public void setWithFlag(String withFlag) {
		this.withFlag = withFlag;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}




}
