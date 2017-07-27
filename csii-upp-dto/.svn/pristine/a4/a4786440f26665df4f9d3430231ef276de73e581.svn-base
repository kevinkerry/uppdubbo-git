package com.csii.upp.dto.router.dpc;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespAppHead;

public class RespDpcHead extends RespAppHead {
	private List<Map<String, Object>> resultList;

	public List<Map<String, Object>> getResultList() {
		return resultList;
	}
	
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.DPC);
		if(resultStatus!=null){
			setRtxnstate(ResponseCode.SUCCESS.equals(resultStatus) ? TransStatus.SUCCESS
					: TransStatus.FAILURE);
		}
	}
	
	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
		if (resultList != null && !resultList.isEmpty()
				&& resultList.size() > 0) {
			this.setReturncode((String) ((Map<String, Object>) resultList
					.get(0)).get("RET_CODE"));
			this.setReturnmsg((String) ((Map<String, Object>) resultList.get(0))
					.get("RET_MSG"));
			
		}
	}
}
