package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

/**
 * @author zhubenle
 *	批量转账响应类
 */
public class RespBatchTransfer extends RespAppHead {
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.FDPS);
		if(ResponseCode.SUCCESS.equals(resultStatus)){
			setRtxnstate(TransStatus.SUCCESS);
		}else if(ResponseCode.TIMEOUT.equals(resultStatus)){
			setRtxnstate(TransStatus.TIMEOUT);
		}else{
			setRtxnstate(TransStatus.FAILURE);
		}
	}
}
