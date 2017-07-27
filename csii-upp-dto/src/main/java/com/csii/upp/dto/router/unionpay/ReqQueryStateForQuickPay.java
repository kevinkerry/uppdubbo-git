package com.csii.upp.dto.router.unionpay;

import com.csii.upp.dto.extend.InputFundData;

/**
 * 交易状态查询类交易
 * 
 * @author zhubenle
 *
 */
public class ReqQueryStateForQuickPay extends ReqUnionPayHead {
	private String queryId; // 交易查询流水号

	public ReqQueryStateForQuickPay(InputFundData data) {
		super(data);
		this.setBizType("000301");
		this.setTxnType("00");
		this.setMerId("929331053110008");
		this.setTxnSubType("00");
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}	

}
