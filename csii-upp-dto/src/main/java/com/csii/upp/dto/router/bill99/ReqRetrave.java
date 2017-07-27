package com.csii.upp.dto.router.bill99;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.upp.constant.ConstBill99;
import com.csii.upp.dto.extend.InputFundData;

public class ReqRetrave extends ReqBill99Head {
	public ReqRetrave(InputFundData data) {
		super(data);
		setTxnType(ConstBill99.TXNTYPE_RETRAVE);
		setInteractiveStatus(ConstBill99.INTERACTIVESTATUS_TR1);
		setOrignalTxnType(data.getTranscode());
		setAmount(data.getTransamt());
		setRefNumber(data.getOrigdowntransnbr());
	}

	String txnMsgContent;
	String txnType;
	String orignalTxnType;
	BigDecimal amount;
	String refNumber;// 原PowerPay.DownRtxnNbr
	String ext;
	String extMap;
	Date extDate;
	long key;
	String value;
	String tr3Url;

	public String getTxnMsgContent() {
		return txnMsgContent;
	}

	public void setTxnMsgContent(String txnMsgContent) {
		this.txnMsgContent = txnMsgContent;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getOrignalTxnType() {
		return orignalTxnType;
	}

	public void setOrignalTxnType(String orignalTxnType) {
		this.orignalTxnType = orignalTxnType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExtMap() {
		return extMap;
	}

	public void setExtMap(String extMap) {
		this.extMap = extMap;
	}

	public Date getExtDate() {
		return extDate;
	}

	public void setExtDate(Date extDate) {
		this.extDate = extDate;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTr3Url() {
		return tr3Url;
	}

	public void setTr3Url(String tr3Url) {
		this.tr3Url = tr3Url;
	}
}
