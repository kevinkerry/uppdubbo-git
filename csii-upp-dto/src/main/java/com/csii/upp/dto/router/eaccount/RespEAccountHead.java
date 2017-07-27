package com.csii.upp.dto.router.eaccount;

import com.csii.upp.constant.ConstEAccount;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

/**
 * 直销银行账务系统服务调用返回参数
 * 
 * @author 徐锦
 * 
 */
public class RespEAccountHead extends RespAppHead {
	BaseResponse baseResponse;
public BaseResponse getBaseResponse() {
		return baseResponse;
	}
	public void setBaseResponse(BaseResponse baseResponse) {
		this.baseResponse = baseResponse;
	}
@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.EACCOUNT);
		if (resultStatus != null)
			setRtxnstate(ConstEAccount.RESPONSECODE_SUCCESS
					.equals(resultStatus) ? TransStatus.SUCCESS
					: TransStatus.FAILURE);
	}

}
