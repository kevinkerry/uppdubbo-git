package com.csii.upp.dto.router.beps;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespBepsHead extends RespAppHead {

	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.BEPS);
		if (resultStatus != null) {
			this.setRtxnstate(ConstCore.SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS : TransStatus.FAILURE);
			}
	}
}
