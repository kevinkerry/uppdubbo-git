package com.csii.upp.dto.router.bill99;

import java.util.Date;

import com.csii.upp.constant.ConstBill99;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqBill99Head extends ReqAppHead {
	public ReqBill99Head(InputFundData data) {
		super(data);
		this.setChnlId(FundChannelCode.PAY);
		data.setFundchannelcode(FundChannelCode.BILL99);
		this.setSrvChnlId(FundChannelCode.BILL99);
		setVersion(ConstBill99.VERSION);
		setMerchantId(ConstBill99.MERCHANTID);
		setSettleMerchantId(ConstBill99.SETTLEMERCHANTID);
		setTerminalId(ConstBill99.TERMINALID);
		setExternalRefNumber(data.getInnerfundtransnbr() == null ? data
				.getOveralltransnbr() : data.getInnerfundtransnbr());
		setEntryTime(data.getTransdate());
	}

	String version;
	String txnType;
	String interactiveStatus;
	String merchantId;
	String settleMerchantId;
	String terminalId;
	Date entryTime;
	String externalRefNumber;
	Date transTime;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getInteractiveStatus() {
		return interactiveStatus;
	}

	public void setInteractiveStatus(String interactiveStatus) {
		this.interactiveStatus = interactiveStatus;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getSettleMerchantId() {
		return settleMerchantId;
	}

	public void setSettleMerchantId(String settleMerchantId) {
		this.settleMerchantId = settleMerchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getExternalRefNumber() {
		return externalRefNumber;
	}

	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
}
