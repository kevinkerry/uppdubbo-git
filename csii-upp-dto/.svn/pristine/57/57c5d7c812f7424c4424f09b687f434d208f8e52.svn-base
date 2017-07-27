package com.csii.upp.dto.router.cnaps2;

import com.csii.upp.constant.ConstCnaps2;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

/**
 * 二代支付系统服务调用返回参数
 * 
 * @author 徐锦
 * 
 */
public class RespCnaps2Head extends RespAppHead {

	private String status;// 交易状态
	private String seqNo;// 原渠道流水号

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.CNAPS2);
		setRtxnstate(ConstCnaps2.STATUS_SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS
				: TransStatus.FAILURE);
	}

}
