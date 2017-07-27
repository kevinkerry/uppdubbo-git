package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqQueryVirtualAcctBalance  extends ReqPaymHead{
	private String userNbr;
	private String subMerchantId;
	private String payeeAcctNbr;
	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}


	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}


	public String getSubMerchantId() {
		return subMerchantId;
	}


	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}



	public String getUserNbr() {
		return userNbr;
	}


	public void setUserNbr(String userNbr) {
		this.userNbr = userNbr;
	}


	public ReqQueryVirtualAcctBalance(InputPaygateData data) {
		super(data);
		this.setUserNbr(data.getUsernbr());
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setSubMerchantId(data.getSubMerchantId());
		this.setTransCode(PaymTransCode.QueryVirtualAcctBalance);
	}

}
