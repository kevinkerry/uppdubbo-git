package com.csii.upp.dto.router.paym;

import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 请求上传商户证书
 *
 */
public class ReqMerCertUpload extends ReqPaymHead {

	private String merId;
	private String merCert;
	private String merCertStatus;

	public ReqMerCertUpload(InputPaygateData data, String merId, String merCert, String merCertStatus) {
		super(data);
		this.setMerId(merId);
		this.setMerCert(merCert);
		this.setMerCertStatus(merCertStatus);
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public void setMerCert(String merCert) {
		this.merCert = merCert;
	}

	public void setMerCertStatus(String merCertStatus) {
		this.merCertStatus = merCertStatus;
	}

	public String getMerId() {
		return merId;
	}

	public String getMerCert() {
		return merCert;
	}

	public String getMerCertStatus() {
		return merCertStatus;
	}

}
