package com.csii.upp.dto;

import java.util.Date;

import com.csii.upp.annotation.AttributeProperties;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.idto.IDto;

public class IssueFile implements IDto{
	/**
	 * 账务流水号
	 */
	private String rtxnnbr;
	/**
	 * 账务日期
	 */
	@AttributeProperties(dateFormat=DateFormatCode.DATE_FORMATTER3)
	private Date rtxndate;
	/**
	 * 交易代码
	 */
	private String rtxntypcd;
	/**
	 * 渠道流水号
	 */
	private String upperrtxnnbr;
	/**
	 * 渠道日期
	 */
	@AttributeProperties(dateFormat=DateFormatCode.DATE_FORMATTER3)
	private Date upperrtxndate;
	/**
	 * 转出账号
	 */
	private String payeracctnbr;
	/**
	 * 转入账号
	 */
	private String payeeacctnbr;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 金额/积分
	 */
	private String transamt;
	/**
	 * 手续费
	 */
	private String feeamt;
	/**
	 * 备注1
	 */
	private String note1;
	/**
	 * 备注2
	 */
	private String note2;
	/**
	 * 交易状态
	 */
	private String transstatus;
	
	public String getTransstatus() {
		return transstatus;
	}
	public void setTransstatus(String transstatus) {
		this.transstatus = transstatus;
	}
	public Date getRtxndate() {
		return rtxndate;
	}
	public String getRtxntypcd() {
		return rtxntypcd;
	}
	public void setRtxnnbr(String rtxnnbr) {
		this.rtxnnbr = rtxnnbr;
	}
	public String getRtxnnbr() {
		return rtxnnbr;
	}
	public void setTransNbr(String rtxnnbr) {
		this.rtxnnbr = rtxnnbr;
	}
	public Date getTransdate() {
		return rtxndate;
	}
	public void setRtxndate(Date rtxndate) {
		this.rtxndate = rtxndate;
	}
	public String getOveralltranstypcd() {
		return rtxntypcd;
	}
	public void setRtxntypcd(String rtxntypcd) {
		this.rtxntypcd = rtxntypcd;
	}
	public String getUpperrtxnnbr() {
		return upperrtxnnbr;
	}
	public void setUpperrtxnnbr(String upperrtxnnbr) {
		this.upperrtxnnbr = upperrtxnnbr;
	}
	public Date getUpperrtxndate() {
		return upperrtxndate;
	}
	public void setUpperrtxndate(Date upperrtxndate) {
		this.upperrtxndate = upperrtxndate;
	}
	public String getPayeracctnbr() {
		return payeracctnbr;
	}
	public void setPayeracctnbr(String payeracctnbr) {
		this.payeracctnbr = payeracctnbr;
	}
	public String getPayeeacctnbr() {
		return payeeacctnbr;
	}
	public void setPayeeacctnbr(String payeeacctnbr) {
		this.payeeacctnbr = payeeacctnbr;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTransamt() {
		return transamt;
	}
	public void setTransamt(String transamt) {
		this.transamt = transamt;
	}
	public String getFeeamt() {
		return feeamt;
	}
	public void setFeeamt(String feeamt) {
		this.feeamt = feeamt;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
}
