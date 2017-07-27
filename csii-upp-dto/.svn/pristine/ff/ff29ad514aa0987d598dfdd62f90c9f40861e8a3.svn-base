package com.csii.upp.dto.router.unionpay;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 交易状态查询类交易
 * 
 * @author zhubenle
 *
 */
public class ReqQueryAcctOpenStatus extends ReqUnionPayHead {
	private String queryId; // 交易查询流水号

	public ReqQueryAcctOpenStatus(InputFundData data) {
		super(data);
		this.setBizType("000301");
		this.setTxnType("78");
		this.setTxnTime(DateUtil.Date_To_DateTimeFormat(DateUtil.getCurrentDateTime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setMerId("929331053110008");
		this.setTxnSubType("00");
		this.setAccNo(data.getPayeracctnbr());
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}	

}
