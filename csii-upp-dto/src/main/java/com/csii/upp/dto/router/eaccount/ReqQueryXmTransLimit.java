package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqQueryXmTransLimit extends ReqEAccountHead{

	public ReqQueryXmTransLimit(InputFundData data) {
		super(data);
		this.setCustomerId(data.getCifNo());
		this.setTransCode(EAccountTransCode.CreditLoanQueryLimitAmt);
	}
	
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
