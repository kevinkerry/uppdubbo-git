package com.csii.upp.dto.router.unionpay;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.UnionpayTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

public class ReqDsjyCx extends ReqUnionPayHead  {

	private String queryId;
	
	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public ReqDsjyCx(InputFundData data) {
		super(data);
		this.setTransCode(UnionpayTransCode.ACP000);
		this.setMerId(ConstUnionPay.PAY_MER_ID);
		this.setQueryId(data.getOrigdowntransnbr());
		this.setOrderId(data.getOriginnertransnbr());
		this.setTxnTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
	}

}
