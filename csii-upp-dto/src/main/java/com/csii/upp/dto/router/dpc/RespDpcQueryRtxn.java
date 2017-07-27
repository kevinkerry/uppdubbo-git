/**
 * 
 */
package com.csii.upp.dto.router.dpc;

/**
 * 大同城系统处理结果查询输出参数
 * 
 * @author 陈彦鹏 
 *
 */
public class RespDpcQueryRtxn extends RespDpcHead {
	private String refno         ;  //原渠道流水号
	private String channelno     ;  //原交易日期
	private String trandate      ;  //原渠道机构号
	private String branchid      ;  //清算日期
	private String settledate   ;  //交易状态
	private String status;  	   //清算状态
	private String settlestatus;   //处理码
	private String responsestatus; //拒绝原因
	private String returnreason;   //
	private String checkstatus;   //对账状态
	
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getChannelno() {
		return channelno;
	}
	public void setChannelno(String channelno) {
		this.channelno = channelno;
	}
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public String getSettledate() {
		return settledate;
	}
	public void setSettledate(String settledate) {
		this.settledate = settledate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSettlestatus() {
		return settlestatus;
	}
	public void setSettlestatus(String settlestatus) {
		this.settlestatus = settlestatus;
	}
	public String getResponsestatus() {
		return responsestatus;
	}
	public void setResponsestatus(String responsestatus) {
		this.responsestatus = responsestatus;
	}
	public String getReturnreason() {
		return returnreason;
	}
	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}


	 
}
