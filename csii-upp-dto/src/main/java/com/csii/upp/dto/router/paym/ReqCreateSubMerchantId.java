package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqCreateSubMerchantId  extends ReqPaymHead{
	private String outSubMerchantId;
	public String getOutSubMerchantId() {
		return outSubMerchantId;
	}


	public void setOutSubMerchantId(String outSubMerchantId) {
		this.outSubMerchantId = outSubMerchantId;
	}


	public ReqCreateSubMerchantId(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.CTSM);
		this.setOutSubMerchantId(data.getOutsubmerchantid());
	}

}
