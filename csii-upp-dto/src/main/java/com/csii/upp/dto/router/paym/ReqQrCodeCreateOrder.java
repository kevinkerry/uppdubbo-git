package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqQrCodeCreateOrder extends ReqPaymHead{

	private String url;
	private String code;
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public ReqQrCodeCreateOrder(InputPaygateData inputData) {
		super(inputData);
		this.setTransCode(FundProcessTransCode.QrCodeCO);
		this.setUrl(inputData.getUrl());
		this.setCode(inputData.getCode());
	}

}
