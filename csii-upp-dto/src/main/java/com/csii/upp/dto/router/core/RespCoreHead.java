package com.csii.upp.dto.router.core;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespCoreHead extends RespAppHead {

	private String creditReturnCode;
	
	/**
	 * 借记卡返回状态
	 */
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.CORE);
		if(resultStatus==null){
			return;
		}
		setRtxnstate(ConstCore.SUCCESS.equals(resultStatus)? TransStatus.SUCCESS : TransStatus.FAILURE);
	}

	public String getCreditReturnCode() {
		return creditReturnCode;
	}

	/**
	 * 贷记卡返回状态
	 */
	public void setCreditReturnCode(String creditReturnCode) {
		this.setFundchannelcd(FundChannelCode.CORE);
		if(creditReturnCode==null){
			return;
		}
		this.setReturncode(creditReturnCode);
		setRtxnstate(ConstCore.RETCODE_SUCCESS.equals(creditReturnCode)? TransStatus.SUCCESS : TransStatus.FAILURE);
	}
	
}
