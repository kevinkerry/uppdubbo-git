package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqXMTransLimtCX extends ReqPaymHead {

	private String cifNo;
	
	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	
	
	public ReqXMTransLimtCX(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.XMLimitCX);
		this.setCifNo(data.getCifNo());
	}
	
}
