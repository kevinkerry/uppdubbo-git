package com.csii.upp.dto.router.fundprocess;

/**
 * 查询交易流水状态
 * @author qgs
 *
 */
public class RespQueryOverallRtxnStat extends RespFundProHead {
	private String overAllTransStatus;

	public String getOverAllTransStatus() {
		return overAllTransStatus;
	}

	public void setOverAllTransStatus(String overAllTransStatus) {
		this.overAllTransStatus = overAllTransStatus;
	}
}
