package com.csii.upp.dto.router.unionpay;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 交易状态查询类交易
 * 
 * @author zhubenle
 *
 */
public class ReqQueryRtxnForOtherEBank extends ReqUnionPayHead {
	private String queryId; // 交易查询流水号

	public ReqQueryRtxnForOtherEBank(InputFundData data) {
		super(data);
		this.setBizType("000000");
		this.setTxnType("00");
		this.setTxnTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setOrderId(data.getInnerfundtransnbr());
		if(InnerRtxnTyp.UnionPayOtherEBankPay.equals(data.getTranscode())){
			this.setMerId("929331053110009");
		}else {
			this.setMerId("929331053110008");
		}
		this.setTxnSubType("00");
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}	

}
