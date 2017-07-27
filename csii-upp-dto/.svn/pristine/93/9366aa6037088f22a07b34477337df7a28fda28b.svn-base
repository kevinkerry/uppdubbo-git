package com.csii.upp.dto.router.paym;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.DateUtil;
/**
 * 批量转账
 * @author zhubenle   
 *
 */
public class ReqBatchTransfer extends ReqPaymHead{
	
	private String batchNo;
	private String merTfType;
	private String merSettAcctBankNo;
	private String merSettAcctName;
	private String merSettAcctNO;
	private String merSettAcctType;
	private String totalNum;
	private String merDateTime;
	private List<Map> merTransList;

	public ReqBatchTransfer(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.BatchTransfer);
		this.setMerNbr(data.getMernbr());
		this.setBatchNo(data.getBatchNo());
		this.setMerTfType(data.getMerTfType());
		this.setMerSettAcctBankNo(data.getPayerbanknbr());
		this.setMerSettAcctName(data.getPayeracctname());
		this.setMerSettAcctNO(data.getPayeracctnbr());
		this.setMerSettAcctType(data.getPayeraccttypcd());
		this.setTotalNum(data.getTotalNum());
		this.setMerDateTime(DateUtil.Date_To_DateTimeFormat(data.getMertransdatetime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setMerTransList(data.getMersubtranslist());
	}

	public String getMerDateTime() {
		return merDateTime;
	}

	public void setMerDateTime(String merDateTime) {
		this.merDateTime = merDateTime;
	}

	public List<Map> getMerTransList() {
		return merTransList;
	}

	public void setMerTransList(List<Map> merTransList) {
		this.merTransList = merTransList;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getMerTfType() {
		return merTfType;
	}

	public void setMerTfType(String merTfType) {
		this.merTfType = merTfType;
	}

	public String getMerSettAcctBankNo() {
		return merSettAcctBankNo;
	}

	public void setMerSettAcctBankNo(String merSettAcctBankNo) {
		this.merSettAcctBankNo = merSettAcctBankNo;
	}

	public String getMerSettAcctName() {
		return merSettAcctName;
	}

	public void setMerSettAcctName(String merSettAcctName) {
		this.merSettAcctName = merSettAcctName;
	}

	public String getMerSettAcctNO() {
		return merSettAcctNO;
	}

	public void setMerSettAcctNO(String merSettAcctNO) {
		this.merSettAcctNO = merSettAcctNO;
	}

	public String getMerSettAcctType() {
		return merSettAcctType;
	}

	public void setMerSettAcctType(String merSettAcctType) {
		this.merSettAcctType = merSettAcctType;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
}
