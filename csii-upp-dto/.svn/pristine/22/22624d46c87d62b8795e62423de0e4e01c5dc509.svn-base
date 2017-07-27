package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.DateUtil;
/**
 * 单笔代发
 * @author qgs   
 *
 */
public class ReqSGPMTransfer extends ReqPaymHead{
	
	private String batchNo;
	private String merTfType;
	private String merSettAcctBankNo;
	private String merSettAcctName;
	private String merSettAcctNO;
	private String merSettAcctType;
	private String merDateTime;
	private String subMerchantId;

	public ReqSGPMTransfer(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.SGPMTransfer);
		this.setMerNbr(data.getMernbr());
		this.setBatchNo(data.getMerseqnbr());
		this.setMerTfType(data.getMerTfType());
		this.setMerSettAcctBankNo(data.getPayerbanknbr());
		this.setMerSettAcctName(data.getPayeracctname());
		this.setMerSettAcctNO(data.getPayeracctnbr());
		this.setMerSettAcctType(data.getPayeraccttypcd());
		this.setMerDateTime(DateUtil.Date_To_DateTimeFormat(data.getMertransdatetime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setSubMerchantId(data.getSubMerchantId());
	}

	public String getMerDateTime() {
		return merDateTime;
	}

	public void setMerDateTime(String merDateTime) {
		this.merDateTime = merDateTime;
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

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

}
