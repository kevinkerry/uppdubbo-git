package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

public class ReqCopr  extends ReqPaymHead{
	private String origTransAmt;
	private String origMerDateTime;
	private String origSubMerDateTime;
	private String origMerSeqNbr;
	private String subMerchantId;
	private String origSubMerSeqNo;
	private String transId;

	public ReqCopr(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.COPR);
		this.setOrigTransAmt(data.getOrigTransAmt());
		this.setOrigMerDateTime(data.getOrigMerDateTime());
		this.setOrigSubMerDateTime(data.getOrigSubMerDateTime());
		this.setOrigMerSeqNbr(data.getOrigmerseqnbr());
		this.setSubMerchantId(data.getSubMerchantId());	
		this.setOrigSubMerSeqNo(data.getOrigSubMerSeqNo());	
        this.setTransId(data.getTransId());
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getOrigTransAmt() {
		return origTransAmt;
	}

	public void setOrigTransAmt(String origTransAmt) {
		this.origTransAmt = origTransAmt;
	}

	public String getOrigMerDateTime() {
		return origMerDateTime;
	}

	public void setOrigMerDateTime(String origMerDateTime) {
		this.origMerDateTime = origMerDateTime;
	}

	public String getOrigSubMerDateTime() {
		return origSubMerDateTime;
	}

	public void setOrigSubMerDateTime(String origSubMerDateTime) {
		this.origSubMerDateTime = origSubMerDateTime;
	}

	public String getOrigMerSeqNbr() {
		return origMerSeqNbr;
	}

	public void setOrigMerSeqNbr(String origMerSeqNbr) {
		this.origMerSeqNbr = origMerSeqNbr;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

	public String getOrigSubMerSeqNo() {
		return origSubMerSeqNo;
	}

	public void setOrigSubMerSeqNo(String origSubMerSeqNo) {
		this.origSubMerSeqNo = origSubMerSeqNo;
	}
	

}
