package com.csii.upp.dto.router.bill99;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.upp.constant.ConstBill99;
import com.csii.upp.dto.extend.InputFundData;

public class ReqRFD extends ReqBill99Head {
	public ReqRFD(InputFundData data) {
		super(data);
		setTxnType(ConstBill99.TXNTYPE_RFD); // 交易类型
		setInteractiveStatus(ConstBill99.INTERACTIVESTATUS_TR1); // 消息状态
		setAmount(data.getTransamt());// 金额
		setOrigRefNumber(data.getOrigdowntransnbr());
	}

	private String txnMsgContent;
	private String txnType;
	private String orignalTxnType;
	private String OrigRefNumber;
	private BigDecimal amount;
	private Date entryTime;
	private String refNumber;// 原PowerPay.DownRtxnNbr
	private String ext;
	private String extMap;
	private Date extDate;
	long key;
	private String value;
	private String tr3Url;

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

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
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

	public String getOrigRefNumber() {
		return OrigRefNumber;
	}

	public void setOrigRefNumber(String origRefNumber) {
		OrigRefNumber = origRefNumber;
	}
}
