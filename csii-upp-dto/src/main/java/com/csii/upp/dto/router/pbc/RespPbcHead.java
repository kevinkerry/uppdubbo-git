package com.csii.upp.dto.router.pbc;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespPbcHead extends RespAppHead {
	private String refno; // 行内支付平台交易参考号
	private String acceptstatuscode;// 受理状态码
	private String acceptfailsmessage; // 受理失败描述
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getAcceptstatuscode() {
		return acceptstatuscode;
	}

	public void setAcceptstatuscode(String acceptstatuscode) {
		this.acceptstatuscode = acceptstatuscode;
	}

	public String getAcceptfailsmessage() {
		return acceptfailsmessage;
	}

	public void setAcceptfailsmessage(String acceptfailsmessage) {
		this.acceptfailsmessage = acceptfailsmessage;
	}

	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.PBC);
		if(resultStatus!=null){
			setRtxnstate(ResponseCode.SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS
					: TransStatus.FAILURE);
		}
	}
}
