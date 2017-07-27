package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

public class ReqVirtualRefound extends ReqFundProHead {

	private String origSeqNbr;
	private String checkNumber;   //对账分类编号
	private String refoundMode;   //退款方式 
	private String note; //备用字段
	private String payTypCd;	//支付方式

	public ReqVirtualRefound(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.VirtualRefound);
		this.setRefoundMode(data.getRefundmode());
		this.setCyberTypCd(data.getCybertypcd());
		this.setOrigSeqNbr(data.getOrigseqnbr());
		this.setPayTypCd(data.getPaytypcd());
	}
	
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRefoundMode() {
		return refoundMode;
	}

	public void setRefoundMode(String refoundMode) {
		this.refoundMode = refoundMode;
	}

	public String getOrigSeqNbr() {
		return origSeqNbr;
	}

	public void setOrigSeqNbr(String origSeqNbr) {
		this.origSeqNbr = origSeqNbr;
	}

	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

}

