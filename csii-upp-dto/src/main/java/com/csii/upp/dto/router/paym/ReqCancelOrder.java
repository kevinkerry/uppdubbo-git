package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 取消订单
 * @author qgs
 *
 */
public class ReqCancelOrder extends ReqPaymHead{
	private String origMerSeqNbr;
	private String origMerDateTime;
	private String origTransAmt;
	private String reasondesc;

	public String getReasondesc() {
		return reasondesc;
	}

	public void setReasondesc(String reasondesc) {
		this.reasondesc = reasondesc;
	}

	public String getOrigMerSeqNbr() {
		return origMerSeqNbr;
	}

	public void setOrigMerSeqNbr(String origMerSeqNbr) {
		this.origMerSeqNbr = origMerSeqNbr;
	}

	public String getOrigMerDateTime() {
		return origMerDateTime;
	}

	public void setOrigMerDateTime(String origMerDateTime) {
		this.origMerDateTime = origMerDateTime;
	}

	public String getOrigTransAmt() {
		return origTransAmt;
	}

	public void setOrigTransAmt(String origTransAmt) {
		this.origTransAmt = origTransAmt;
	}

	public ReqCancelOrder(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.CancelOrder);
		this.setOrigMerDateTime(data.getOrigMerDateTime());
		this.setOrigMerSeqNbr(data.getOrigmerseqnbr());
		this.setOrigTransAmt(data.getOrigTransAmt());
		this.setReasondesc(data.getReasondesc());
	}

}
