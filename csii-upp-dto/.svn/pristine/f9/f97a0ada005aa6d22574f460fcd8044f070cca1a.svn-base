package com.csii.upp.dto.router.hvps;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespHvpsHead extends RespAppHead {

	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.HVPS);
		if (resultStatus != null) {
			this.setRtxnstate(ConstCore.SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS : TransStatus.FAILURE);
		}
	}
}
