/**
 * 
 */
package com.csii.upp.dto.router.core;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 用于支付和退货掉核心请求类
 * @author gaoqi
 *
 */
public class ReqCoreCreditRefound extends ReqCoreHead {
	
	private String payerAcctNbr;  //付款人账号
	private String currencyCd;  //币种
	private String note; //备用字段
	private String payerCardExpiredDate; //信用卡有效期
	private String payerCardCvv2; //信用卡cvv
	private BigDecimal transAmt;     //交易金额
	private String payeeAcctNbr;  //收款账号
	private String payerCardTypCd;
	private String payerAcctTypCd;
	private String payerAcctKind;
	private String payerOrgNbr;
	private String payeeCardTypCd;
	private String payeeAcctTypCd;
	private String payeeAcctKind;
	private String payeePhoneNo;
	private String payTypCd;
	public ReqCoreCreditRefound(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.CoreCreditRefound);
		this.setReceiver(ConstCore.RECEIVER_CREDIT);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setPayerCardExpiredDate(data.getPayercardexpireddate());
		this.setNote(data.getNote());
		this.setPayerCardCvv2(data.getPayercardcvv2());
		if(InteralFlag.YES.equals(data.getInteralFlag())){
			this.setTransAmt(data.getRealAmt());
			data.setTransamt(data.getRealAmt());
		}else{
			this.setTransAmt(data.getTransamt());
		}
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setPayerCardTypCd(data.getPayercardtypcd());
		this.setPayerAcctTypCd(data.getPayeraccttypcd());
		this.setPayerAcctKind(data.getPayeracctkind());
		this.setPayerOrgNbr(data.getPayeracctdeptnbr());
		this.setPayeeCardTypCd(data.getPayeecardtypcd());
		this.setPayeeAcctTypCd(data.getPayeeaccttypcd());
		this.setPayeeAcctKind(data.getPayeeacctkind());
		this.setPayeePhoneNo(data.getPayeephoneno());
		if(PayTypCd.FOSION.equals(data.getPaytypcd())){
			setPayTypCd("丰收e支付退货");
		}else if(PayTypCd.CARDPWD.equals(data.getPaytypcd())){
			setPayTypCd("银行卡支付退货");
		}else if(PayTypCd.OURCYBER.equals(data.getPaytypcd())){
			setPayTypCd("网银支付退货");
		}
		this.setNote(data.getOriginnertransnbr().substring(data.getOriginnertransnbr().length() - 6) + DateUtil
				.Date_To_DateTimeFormat(data.getOrigtranstime(), DateFormatCode.DATETIME_FORMATTER3).substring(4)+"     "+payTypCd);
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getPayTypCd() {
		return payTypCd;
	}
	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

}
