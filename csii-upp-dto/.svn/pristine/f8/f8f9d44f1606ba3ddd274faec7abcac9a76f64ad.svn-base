package com.csii.upp.dto.router.cnaps2;

import com.csii.upp.dto.extend.InputFundData;

/**
 * 退汇交易入参
 * 
 * @author
 *
 */
public class ReqReturnRemittance extends ReqCnaps2Head {

	public ReqReturnRemittance(InputFundData data) {
		super(data);
		setFlowNb(data.getOveralltransnbr());
		setCntt(data.getNote());
	}

	private String flowNb; // 来报交易唯一流水号
	private String cntt; // 退汇原因

	public String getFlowNb() {
		return flowNb;
	}

	public void setFlowNb(String flowNb) {
		this.flowNb = flowNb;
	}

	public String getCntt() {
		return cntt;
	}

	public void setCntt(String cntt) {
		this.cntt = cntt;
	}
}
