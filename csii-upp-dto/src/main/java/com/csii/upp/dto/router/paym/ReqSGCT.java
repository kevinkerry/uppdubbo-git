package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqSGCT extends ReqPaymHead {
	
	

	private String payTypCd;
	private String subMerchantId;
	private String certNo;
	private String certType;
	public ReqSGCT(InputPaygateData data){
		super(data);
		this.setTransCode(PaymTransCode.SGCT);
		this.setSubMerchantId(data.getSubMerchantId());
		this.setPayTypCd(data.getPaytypcd());
		this.setCertNo(data.getCertNo());
		this.setCertType(data.getCertTyp());
		
	}
	
	
	

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}
	
	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}
	
}
