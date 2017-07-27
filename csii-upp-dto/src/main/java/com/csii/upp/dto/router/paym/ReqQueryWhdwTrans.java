package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.DateUtil;

/**
 * 跨行转账查询
 * 
 * @author wy
 *
 */
public class ReqQueryWhdwTrans extends ReqPaymHead {

	public ReqQueryWhdwTrans(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QueryWhdwTransfer);
		this.setOrigMerDate(DateUtil.Date_To_DateTimeFormat(data.getOrigmerdate(),DateFormatCode.DATE_FORMATTER3));
		this.setOrigTransAmt(data.getOrigTransAmt());
		this.setOrigMerseqNbr(data.getOrigmerseqnbr());
	}
	private String origMerDate;
	private String origTransAmt;
	private String origMerseqNbr;
	public String getOrigTransAmt() {
		return origTransAmt;
	}

	public void setOrigTransAmt(String origTransAmt) {
		this.origTransAmt = origTransAmt;
	}

	public String getOrigMerDate() {
		return origMerDate;
	}

	public void setOrigMerDate(String origMerDate) {
		this.origMerDate = origMerDate;
	}

	public String getOrigMerseqNbr() {
		return origMerseqNbr;
	}

	public void setOrigMerseqNbr(String origMerseqNbr) {
		this.origMerseqNbr = origMerseqNbr;
	}

	
	
	

}
