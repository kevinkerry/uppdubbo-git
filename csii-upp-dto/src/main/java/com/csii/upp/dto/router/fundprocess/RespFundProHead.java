package com.csii.upp.dto.router.fundprocess;

import java.util.Date;
import java.util.List;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dto.router.RespAppHead;

/**
 * @author lixinyou
 *	支付收银Payment请求响应头
 */
public class RespFundProHead extends RespAppHead {
	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.FDPS);
		this.setReturncode(resultStatus);
		if(ResponseCode.SUCCESS.equals(resultStatus)){
			setRtxnstate(com.csii.upp.constant.TransStatus.SUCCESS);
		}else if(ResponseCode.TIMEOUT.equals(resultStatus)){
			setRtxnstate(com.csii.upp.constant.TransStatus.TIMEOUT);
		}else{
			setRtxnstate(com.csii.upp.constant.TransStatus.FAILURE);
		}
	}
	private String transStatus;
	private String payerAcctNbr;  //卡号
	private String payerIdTypCd;  //证件类型
	private String payerIdNbr;    //证件号
	private String payerAcctStatus;  //卡账户状态
	private List<String> payerPhoneNoList;  //手机号列表
	private String payerAcctName;  //付款人姓名
	private String custCifNbr;     //客户号
	private String payerAcctDeptNbr;  //开户机构
	private Date hostClearDate;  //核心日期
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}
	public String getPayerIdTypCd() {
		return payerIdTypCd;
	}
	public void setPayerIdTypCd(String payerIdTypCd) {
		this.payerIdTypCd = payerIdTypCd;
	}
	public String getPayerIdNbr() {
		return payerIdNbr;
	}
	public void setPayerIdNbr(String payerIdNbr) {
		this.payerIdNbr = payerIdNbr;
	}
	public String getPayerAcctStatus() {
		return payerAcctStatus;
	}
	public void setPayerAcctStatus(String payerAcctStatus) {
		this.payerAcctStatus = payerAcctStatus;
	}
	public List<String> getPayerPhoneNoList() {
		return payerPhoneNoList;
	}
	public void setPayerPhoneNoList(List<String> payerPhoneNoList) {
		this.payerPhoneNoList = payerPhoneNoList;
	}
	public String getPayerAcctName() {
		return payerAcctName;
	}
	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}
	public String getCustCifNbr() {
		return custCifNbr;
	}
	public void setCustCifNbr(String custCifNbr) {
		this.custCifNbr = custCifNbr;
	}
	public String getPayerAcctDeptNbr() {
		return payerAcctDeptNbr;
	}
	public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
		this.payerAcctDeptNbr = payerAcctDeptNbr;
	}
	public Date getHostClearDate() {
		return hostClearDate;
	}
	public void setHostClearDate(Date hostClearDate) {
		this.hostClearDate = hostClearDate;
	}
	
	
}
