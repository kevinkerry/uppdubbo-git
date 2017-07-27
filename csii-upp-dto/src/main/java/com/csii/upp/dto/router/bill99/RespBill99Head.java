package com.csii.upp.dto.router.bill99;

import java.util.Date;

import com.csii.upp.constant.ConstBill99;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespBill99Head extends RespAppHead {

	String version;
	String txnType;
	String interactiveStatus;
	String errorCode;
	String errorMessage;
	String settleMerchantId;
	Date entryTime;
	String externalRefNumber;
	String merchantId;
	String terminalId;
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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSettleMerchantId() {
		return settleMerchantId;
	}

	public void setSettleMerchantId(String settleMerchantId) {
		this.settleMerchantId = settleMerchantId;
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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.BILL99);
		setRtxnstate(ConstBill99.RESPONSECODE_SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS
				: TransStatus.FAILURE);
	}
}
