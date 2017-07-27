package com.csii.upp.custom.common.api.data.payment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QuerySignInfoListResp extends PaymentRespHead {
	private static final long serialVersionUID = -7770148992353285185L;
	private String payerAcctNbr;
	private String totalPage;
	private String pageNum;
	private String recordNumber;
	private String payerPhoneNo;
	private List<TransDetail> transList;

	public static class TransDetail implements Serializable {
		private static final long serialVersionUID = -260459119573572683L;
		private String transStatus;
		private String count;
		private String payerAcctDeptNbr;
		private String payeeAcctDeptNbr;
		private String payerAcctNbr;
		private String payerAcctName;
		private String payeeAcctNbr;
		private String feeAmt;
		private String transAmt;
		private String currenCyCd;
		private String transCode;
		private String payeeAcctTypCd;
		private String payeeAcctName;
		private Date transTime;
		private String payerCardTypCd;
		private String transDate;
		private String amtAmount;

		public String getTransStatus() {
			return transStatus;
		}

		public void setTransStatus(String transStatus) {
			this.transStatus = transStatus;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getPayerAcctDeptNbr() {
			return payerAcctDeptNbr;
		}

		public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
			this.payerAcctDeptNbr = payerAcctDeptNbr;
		}

		public String getPayeeAcctDeptNbr() {
			return payeeAcctDeptNbr;
		}

		public void setPayeeAcctDeptNbr(String payeeAcctDeptNbr) {
			this.payeeAcctDeptNbr = payeeAcctDeptNbr;
		}

		public String getPayerAcctNbr() {
			return payerAcctNbr;
		}

		public void setPayerAcctNbr(String payerAcctNbr) {
			this.payerAcctNbr = payerAcctNbr;
		}

		public String getPayerAcctName() {
			return payerAcctName;
		}

		public void setPayerAcctName(String payerAcctName) {
			this.payerAcctName = payerAcctName;
		}

		public String getPayeeAcctNbr() {
			return payeeAcctNbr;
		}

		public void setPayeeAcctNbr(String payeeAcctNbr) {
			this.payeeAcctNbr = payeeAcctNbr;
		}

		public String getFeeAmt() {
			return feeAmt;
		}

		public void setFeeAmt(String feeAmt) {
			this.feeAmt = feeAmt;
		}

		public String getTransAmt() {
			return transAmt;
		}

		public void setTransAmt(String transAmt) {
			this.transAmt = transAmt;
		}

		public String getCurrenCyCd() {
			return currenCyCd;
		}

		public void setCurrenCyCd(String currenCyCd) {
			this.currenCyCd = currenCyCd;
		}

		public String getTransCode() {
			return transCode;
		}

		public void setTransCode(String transCode) {
			this.transCode = transCode;
		}

		public String getPayeeAcctTypCd() {
			return payeeAcctTypCd;
		}

		public void setPayeeAcctTypCd(String payeeAcctTypCd) {
			this.payeeAcctTypCd = payeeAcctTypCd;
		}

		public String getPayeeAcctName() {
			return payeeAcctName;
		}

		public void setPayeeAcctName(String payeeAcctName) {
			this.payeeAcctName = payeeAcctName;
		}

		public Date getTransTime() {
			return transTime;
		}

		public void setTransTime(Date transTime) {
			this.transTime = transTime;
		}

		public String getPayerCardTypCd() {
			return payerCardTypCd;
		}

		public void setPayerCardTypCd(String payerCardTypCd) {
			this.payerCardTypCd = payerCardTypCd;
		}

		public String getTransDate() {
			return transDate;
		}

		public void setTransDate(String transDate) {
			this.transDate = transDate;
		}

		public String getAmtAmount() {
			return amtAmount;
		}

		public void setAmtAmount(String amtAmount) {
			this.amtAmount = amtAmount;
		}

	}

	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}

	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setPayerPhoneNo(String payerPhoneNo) {
		this.payerPhoneNo = payerPhoneNo;
	}

	public String getPayerPhoneNo() {
		return payerPhoneNo;
	}

	public List<TransDetail> getTransList() {
		return transList;
	}

	public void setTransList(List<TransDetail> transList) {
		this.transList = transList;
	}

}
