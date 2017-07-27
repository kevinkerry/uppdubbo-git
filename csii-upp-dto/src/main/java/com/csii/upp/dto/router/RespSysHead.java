package com.csii.upp.dto.router;

import java.util.Date;
import java.util.Map;

import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.idto.IDto;

/**
 * 接收路由请求系统报文头
 * 
 * @author 徐锦
 *
 */
public abstract class RespSysHead implements IDto {
	private String fundchannelcd;
	private String returnHtml;//银联页面跳转组装成的页面
	private String codeUrl;//二维码订单地址
	
	/**
	 * 返回结果编号
	 */
	private String returncode;
	/**
	 * 返回结果值
	 */
	private String resultStatus;
	/**
	 * 返回结果信息
	 */
	private String returnmsg;
	/**
	 * 交易处理状态，0：成功；1：失败；9：待发送；2：已撤销
	 */
	private String rtxnstate;
	/**
	 * 交易应答流水号，交易的唯一标识
	 */
	private String downrtxnnbr;
	/**
	 * 交易应答日期
	 */
	private Date downrtxndate;
	/**
	 * 交易应答时间
	 */
	private Date downrtxntime;
	/**
	 * 交易应答系统编号
	 */
	private String downsysnbr;
	/**
	 * 交易处理状态
	 */
	private RouterResult result;

	private Map<String,Object> ResponseHeader;
	private Map<String,Object> ResponseBody;
	private Map<String,Object> Fault;

	public abstract void setResultStatus(String resultStatus);

	public String getResultStatus() {
		return resultStatus;
	}

	public String getFundchannelcd() {
		return fundchannelcd;
	}

	public void setFundchannelcd(String fundchannelcd) {
		if(fundchannelcd != null){
			this.fundchannelcd = fundchannelcd;
		}
	}

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		if (returncode != null) {
			this.returncode = returncode;
		}
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg) {
		if (returnmsg != null) {
			this.returnmsg = returnmsg;
		}
	}

	public String getRtxnstate() {
		return rtxnstate;
	}

	public void setRtxnstate(String rtxnstate) {
		if (rtxnstate != null){
			this.rtxnstate = rtxnstate;
		}
	}

	public String getDownrtxnnbr() {
		return downrtxnnbr;
	}

	public void setDownrtxnnbr(String downrtxnnbr) {
		if(downrtxnnbr!=null){
			this.downrtxnnbr = downrtxnnbr;
		}
	}

	public Date getDownrtxndate() {
		return downrtxndate;
	}

	public void setDownrtxndate(Date downrtxndate) {
		if(downrtxndate!=null){
			this.downrtxndate = downrtxndate;
		}
	}

	public Date getDownrtxntime() {
		return downrtxntime;
	}

	public void setDownrtxntime(Date downrtxntime) {
		if (downrtxntime != null) {
			this.downrtxntime = downrtxntime;
		}
	}

	public String getDownsysnbr() {
		return downsysnbr;
	}

	public void setDownsysnbr(String downsysnbr) {
		this.downsysnbr = downsysnbr;
	}
	
	public String getReturnHtml() {
		return returnHtml;
	}

	public void setReturnHtml(String returnHtml) {
		this.returnHtml = returnHtml;
	}

	public RouterResult getResult() {		
		if (result == null) {
			if (TransStatus.SUCCESS.equals(getRtxnstate())) {
				result = RouterResult.SUCCESS;
			} else if (TransStatus.PROCESSING.equals(getRtxnstate())) {
				result = RouterResult.RECEIVED;
			} else if (TransStatus.TIMEOUT.equals(getRtxnstate())) {
				result = RouterResult.TIMEOUT;
			} else if (TransStatus.PENDING.equals(getRtxnstate())) {
				result = RouterResult.PENDING;
			} else {
				result = RouterResult.FAILURE;
			}
		}
		return this.result;	
	}

	public void setResult(RouterResult result) {
		this.result = result;
	}

	/**
	 * 交易处理成功
	 */
	public boolean isSuccess() {
		return RouterResult.SUCCESS.equals(getResult());
	}

	/**
	 * 交易处理失败
	 */
	public boolean isFailure() {
		return RouterResult.FAILURE.equals(getResult());
	}

	/**
	 * 交易处理超时
	 */
	public boolean isTimeout() {
		return RouterResult.TIMEOUT.equals(getResult());
	}

	/**
	 * 交易已收到，异步应答
	 */
	public boolean isReceived() {
		return RouterResult.RECEIVED.equals(getResult());
	}
	/**
	 * 交易已收到，待扫码
	 */
	public boolean isPending() {
		return RouterResult.PENDING.equals(getResult());
	}

	public Map<String, Object> getResponseHeader() {
		return ResponseHeader;
	}

	public void setResponseHeader(Map<String, Object> responseHeader) {
		ResponseHeader = responseHeader;
	}

	public Map<String, Object> getResponseBody() {
		return ResponseBody;
	}

	public void setResponseBody(Map<String, Object> responseBody) {
		ResponseBody = responseBody;
	}

	public Map<String, Object> getFault() {
		return Fault;
	}

	public void setFault(Map<String, Object> fault) {
		Fault = fault;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
	private String downflag;

	public String getDownflag() {
		return downflag;
	}

	public void setDownflag(String downflag) {
		this.downflag = downflag;
	}
	
}
