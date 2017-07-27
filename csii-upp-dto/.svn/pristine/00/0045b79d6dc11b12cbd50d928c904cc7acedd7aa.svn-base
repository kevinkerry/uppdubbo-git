/**
 * 
 */
package com.csii.upp.dto.router.core;

import java.util.ArrayList;
import java.util.List;

import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou
 * 贷记卡信息查询响应类
 */
public class RespQueryCreditCardInfo extends RespCoreHead {

	private String payerAcctNbr;
	private String payerPhoneNo;
	private List<String> payerPhoneNoList ;
	private String payerIdTypCd;
	private String payerIdNbr;
	private String payerAcctStatus;
	private String custCifNbr;     //客户号
	private String payerAcctDeptNbr;  //开户机构
	private String payerCardCvv2;    //付款卡有效期
	private String payerCardExpireExpireDate;
	private String payerAcctName;
	public String getPayerAcctName() {
		return payerAcctName;
	}
	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}
	public String getPayerCardExpireExpireDate() {
		return payerCardExpireExpireDate;
	}
	public void setPayerCardExpireExpireDate(String payerCardExpireExpireDate) {
		this.payerCardExpireExpireDate = payerCardExpireExpireDate;
	}
	public String getPayerCardCvv2() {
		return payerCardCvv2;
	}
	public void setPayerCardCvv2(String payerCardCvv2) {
		this.payerCardCvv2 = payerCardCvv2;
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}
	public String getPayerPhoneNo() {
		return payerPhoneNo;
	}
	public void setPayerPhoneNo(String payerPhoneNo) {
		this.payerPhoneNo = payerPhoneNo;
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
	public List<String> getPayerPhoneNoList() {
		if(!StringUtil.isStringEmpty(this.payerPhoneNo)){
			payerPhoneNoList.add(this.payerPhoneNo);
		}
		return payerPhoneNoList;
	}
	public void setPayerPhoneNoList(List<String> payerPhoneNoList) {
		this.payerPhoneNoList = payerPhoneNoList==null?new ArrayList<String>():payerPhoneNoList;
	}
}
