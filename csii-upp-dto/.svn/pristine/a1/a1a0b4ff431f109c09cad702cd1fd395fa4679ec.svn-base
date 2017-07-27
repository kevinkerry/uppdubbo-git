/**
 * 
 */
package com.csii.upp.dto.router.core;

import java.util.List;

import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou
 * 借记卡卡信息查询响应类
 */
public class RespQueryDeditCardInfo extends RespCoreHead {
	
	private String payerAcctNbr;
	private String payerPhoneNo;
	private List<String> List;
	private List<String> payerPhoneNoList;
	private String payerIdTypCd;
	private String payerIdNbr;
	private String payerAcctStatus;
	private String payerAcctName;  //付款人姓名
	public String getCardStat() {
		return cardStat;
	}
	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
	}
	private String cardStat;
	public String getAcctLossStatus() {
		return acctLossStatus;
	}
	public void setAcctLossStatus(String acctLossStatus) {
		this.acctLossStatus = acctLossStatus;
	}
	public String getAcctStatusWord() {
		return acctStatusWord;
	}
	public void setAcctStatusWord(String acctStatusWord) {
		this.acctStatusWord = acctStatusWord;
	}
	public String getAcctFreezeFlag() {
		return acctFreezeFlag;
	}
	public void setAcctFreezeFlag(String acctFreezeFlag) {
		this.acctFreezeFlag = acctFreezeFlag;
	}
	public String getAcctNature() {
		return acctNature;
	}
	public void setAcctNature(String acctNature) {
		this.acctNature = acctNature;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardStatusWord() {
		return cardStatusWord;
	}
	public void setCardStatusWord(String cardStatusWord) {
		this.cardStatusWord = cardStatusWord;
	}
	private String custCifNbr;     //客户号
	private String payerAcctDeptNbr;  //开户机构
	
	private String acctLossStatus;
	private String acctStatusWord;
	private String acctFreezeFlag;
	private String acctNature;
	private String cardStatus;
	private String cardStatusWord;
	
	public String getPayerAcctDeptNbr() {
		return payerAcctDeptNbr;
	}
	public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
		this.payerAcctDeptNbr = payerAcctDeptNbr;
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
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
	public String getPayerAcctName() {
		return payerAcctName;
	}
	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
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
	public List<String> getList() {
		return List;
	}
	public void setList(List<String> list) {
		List = list;
	}
	public List<String> getPayerPhoneNoList() {
		payerPhoneNoList=this.getList();
		if(!StringUtil.isStringEmpty(this.payerPhoneNo)
				&&!payerPhoneNoList.contains(this.payerPhoneNo)){
			payerPhoneNoList.add(this.payerPhoneNo);
		}
		return payerPhoneNoList;
	}
	public void setPayerPhoneNoList(List<String> payerPhoneNoList) {
		this.payerPhoneNoList = payerPhoneNoList;
	}
}
