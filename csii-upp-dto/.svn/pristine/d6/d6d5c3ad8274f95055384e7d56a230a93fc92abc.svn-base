package com.csii.upp.dto.router.ibps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.YN;
import com.csii.upp.dto.router.RespAppHead;
import com.csii.upp.util.StringUtil;

/**
 * 超级网银服务调用返回参数
 * 
 * @author 姜星
 * 
 */
public class RespIbpsHead extends RespAppHead {
	private String retstatus; // 交易状态
	private String branchid; // 分支行标识
	private String messagetype; // 报文类型
	private String messagecode; // 报文代码
	private String trantimestamp; // 交易时间
	private String status;
	private String isTimeoutStatus = YN.N;

	private List<Map<String, Object>> resultList;
	private Map<String, Object> head;

	public Map<String, Object> getHead() {
		return this.head;
	}

	public void setHead(Map<String, Object> head) {
		this.head = head;
		this.setReturncode(StringUtil.parseObjectToString(head.get("RspCod")));
		this.setReturnmsg(StringUtil.parseObjectToString(head.get("RspMsg")));
		//530015-接收对方回应超时，请稍后查询处理结果
		this.setResultStatus(("000000".equals(this.getReturncode()) || "530015"
				.equals(this.getReturncode())) ? ResponseCode.SUCCESS
				: ResponseCode.FAILURE);
		this.setRtxnstateByAttribute();
	}

	public List<Map<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
		if (resultList != null && !resultList.isEmpty()
				&& resultList.size() > 0) {
			this.setReturncode((String) ((Map<String, Object>) resultList
					.get(0)).get("RET_CODE"));
			this.setReturnmsg((String) ((Map<String, Object>) resultList.get(0))
					.get("RET_MSG"));
			List<String> timeoutStatus = Arrays.asList("999998", "310777",
					"320006", "320007"); // 999998-核心主动发起调用超时，310777-发送超时，320006-记账超时，320007-冲账超时
			if (timeoutStatus.contains(this.getReturncode())) {
				this.setIsTimeoutStatus(YN.Y);
			}
			this.setRtxnstateByAttribute();
		}
	}

	protected void setRtxnstateByAttribute() {
		setRtxnstate(ResponseCode.SUCCESS.equals(this.getStatus()) ? TransStatus.SUCCESS
				: (YN.Y.equals(this.getIsTimeoutStatus()) ? TransStatus.TIMEOUT
						: TransStatus.FAILURE));
	}

	public String getRetstatus() {
		return this.retstatus;
	}

	public void setRetstatus(String retstatus) {
		this.retstatus = retstatus;
	}

	public String getBranchid() {
		return this.branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public String getMessagecode() {
		return this.messagecode;
	}

	public void setMessagecode(String messagecode) {
		this.messagecode = messagecode;
	}

	public String getTrantimestamp() {
		return this.trantimestamp;
	}

	public void setTrantimestamp(String trantimestamp) {
		this.trantimestamp = trantimestamp;
	}

	public String getIsTimeoutStatus() {
		return isTimeoutStatus;
	}

	public void setIsTimeoutStatus(String isTimeoutStatus) {
		if (isTimeoutStatus != null) {
			this.isTimeoutStatus = isTimeoutStatus;
		}
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		if (status != null) {
			this.status = status;
		}
	}

	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.IBPS);
		if (resultStatus != null) {
			this.status = resultStatus;
		}
	}
}
