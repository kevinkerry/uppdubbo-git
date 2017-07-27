package com.csii.upp.dto.router.unionpay;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;

public class RespUnionPayCheck extends RespFileDownload {
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.UNIONPAY);
		if (resultStatus != null)
			setRtxnstate(ConstUnionPay.STATUS_SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS
					: TransStatus.FAILURE);
	}
}
