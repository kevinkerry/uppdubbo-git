package com.csii.upp.dto.router.core;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 虚账户贷记卡退货交易
 * @author fandy
 *
 */
public class ReqCoreCreditVirtualRefoundTrans extends ReqCoreHead {

	private String payerAcctNbr;
	private String payerCardTypCd;
	private String payerAcctTypCd;
	private String payerAcctKind;
	private String payerOrgNbr;
	private String payerCardPwd;
	private String payerIdNbr;
	private String payerIdTypCd;
	private String payerCardExpiredDate;
	private String payerCardCvv2;
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
	private String ACRADDH;
	private String payTypCd;

	public ReqCoreCreditVirtualRefoundTrans(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.CoreCreditVirtualRefound);
		this.setReceiver(ConstCore.RECEIVER_CREDIT);
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
		this.setPayeeCardExpiredDate(data.getPayeecardexpireddate());
		this.setPayeeCardCvv2(data.getPayeecardcvv2());
		this.setPayeePhoneNo(data.getPayeephoneno());
		this.setACRADDH(data.getOriginnertransnbr());
		if(PayTypCd.FOSION.equals(data.getPaytypcd())){
			payTypCd = "丰收e支付退货";
		}else if(PayTypCd.CARDPWD.equals(data.getPaytypcd())){
			payTypCd = "银行卡支付退货";
		}else if(PayTypCd.OURCYBER.equals(data.getPaytypcd())){
			payTypCd = "网银支付退货";
		}
		this.setNote(data.getOriginnertransnbr().substring(data.getOriginnertransnbr().length() - 6) + DateUtil
				.Date_To_DateTimeFormat(data.getOrigtranstime(), DateFormatCode.DATETIME_FORMATTER3).substring(4));
	}
	
	public String getACRADDH() {
		return ACRADDH;
	}

	public void setACRADDH(String aCRADDH) {
		ACRADDH = aCRADDH;
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

	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

}


