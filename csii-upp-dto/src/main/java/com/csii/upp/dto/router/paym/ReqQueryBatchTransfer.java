package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 批量转账交易结果查询
 * @author zhubenle   
 *
 */
public class ReqQueryBatchTransfer extends ReqPaymHead{
	
	private String batchNo;
	private String merDateTime;

	public ReqQueryBatchTransfer(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryBatchTransfer);
		this.setBatchNo(data.getBatchNo());
		//this.setMerDateTime(DateUtil.Date_To_DateTimeFormat(data.getMertransdatetime(), DateFormatCode.DATETIME_FORMATTER3));
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
}
