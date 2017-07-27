package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * 查询交易流水状态
 * @author qgs
 *
 */
public class ReqQueryOverallRtxnStat  extends ReqFundProHead{
	private String origUpperTransNbr ;
	
	public ReqQueryOverallRtxnStat(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QueryOverallRtxnStat);
		this.setOrigUpperTransNbr(data.getTransseqnbr());
	}

	public String getOrigUpperTransNbr() {
		return origUpperTransNbr;
	}
	public void setOrigUpperTransNbr(String origUpperTransNbr) {
		this.origUpperTransNbr = origUpperTransNbr;
	}
}
