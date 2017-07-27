package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

public class ReqQueryLimitInfo extends ReqFundProHead {
	
	private String cifNo;
	
	
	
	public String getCifNo() {
		return cifNo;
	}



	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}



	public ReqQueryLimitInfo(InputPaymentData data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.setTransCode(FundProcessTransCode.QueryXiaoMiLimit);
		this.setCifNo(data.getCifNo());
	}

}
