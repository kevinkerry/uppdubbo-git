package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesubtrans;

/**
 * @author gaoqi
 * 发往fundprocess的支付交易响应类 
 *
 */
public class ReqConfirmedVirtualAcctPay extends ReqFundProHead {

	public ReqConfirmedVirtualAcctPay(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.ConfirmedVirtualAcctPay);
		 Onlinesubtrans origSubTrans=data.getOrigSubTrans();
		this.setOrigSeqNbr(origSubTrans.getTransseqnbr());
		this.setOrigSubTransNbr(origSubTrans.getSubtransseqnbr());
	}
	
	private String origSeqNbr;
	private String origSubTransNbr;
	

	public String getOrigSeqNbr() {
		return origSeqNbr;
	}

	public void setOrigSeqNbr(String origSeqNbr) {
		this.origSeqNbr = origSeqNbr;
	}

	public String getOrigSubTransNbr() {
		return origSubTransNbr;
	}

	public void setOrigSubTransNbr(String origSubTransNbr) {
		this.origSubTransNbr = origSubTransNbr;
	}
	
}
