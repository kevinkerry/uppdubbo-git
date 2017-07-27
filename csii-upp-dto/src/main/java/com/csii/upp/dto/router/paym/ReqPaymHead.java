package com.csii.upp.dto.router.paym;

import java.util.UUID;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.ReqSysHead;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class ReqPaymHead extends ReqSysHead {

	public ReqPaymHead(InputPaygateData data) {
		super(data);
		if(StringUtil.isStringEmpty(this.getReqSeqNo())){
			this.setReqSeqNo(String.valueOf(UUID.randomUUID().getLeastSignificantBits()));
		}
		this.setChnlId(FundChannelCode.PAYG);
		this.setSrvChnlId(FundChannelCode.PAYM);
		this.setPlain(data.getPlain());
		this.setSignature(data.getSignature());
		this.setChannelNbr(data.getChannelnbr());
		this.setSystemCode(data.getSystemcode());
		this.setPayTypCd(data.getPaytypcd());
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerCardTypCd(data.getPayercardtypcd());
		this.setPayerPhoneNo(data.getPayerphoneno());
		this.setCustCifNbr(data.getCustcifnbr());
		this.setPayerAcctDeptNbr(data.getPayeracctdeptnbr());
		this.setPayerAcctName(data.getPayeracctname());
		this.setPayerCardPwd(data.getPayercardpwd());
		this.setPayerCardCvv2(data.getPayercardcvv2());
		this.setPayerCardExpiredDate(data.getPayercardexpireddate());
		this.setPayerIdNbr(data.getPayeridnbr());
		this.setPayerIdTypCd(data.getPayeridtypcd());
		this.setMerNbr(data.getMernbr());
		this.setMerSeqNbr(data.getMerseqnbr());
		this.setTransAmt(StringUtil.parseObjectToString(data.getTransamt()));
		this.setMerTransDateTime(DateUtil.Date_To_DateTimeFormat(data.getMertransdatetime(),DateFormatCode.DATETIME_FORMATTER3));
		this.setCurrencyCd(data.getCurrencycd());
		this.setCustEmail(data.getCustemail());
		this.setMerUrl(data.getMerurl());
		this.setMerUrl1(data.getMerurl1());
		this.setPayIp(data.getPayip());
		this.setMsgExt(data.getMsgext());
		this.setTransId(data.getTransId());
		this.setCyberTypCd(data.getCyberTypCd());
		this.setOrderNbr(data.getOrderNbr());
		
	}
	
	public ReqPaymHead(InputFundData data){
		super(data);
	}
	
	private String signature;
	private String plain;
	private String transCode;
	private String systemCode;
	private String payTypCd;
	private String payerAcctNbr;
	private String payerCardTypCd;
	private String payerPhoneNo;
	private String custCifNbr;
	private String payerAcctDeptNbr;
	private String payerIdNbr;
	private String payerCardPwd;
	private String payerIdTypCd;
	private String merNbr;
	private String merSeqNbr;
	private String transAmt;
	private String merTransDateTime;
	private String currencyCd;
	private String custEmail;
	private String merUrl;
	private String merUrl1;
	private String payIp;
	private String msgExt;
	private String payerCardCvv2;
	private String payerCardExpiredDate;
	private String payerAcctName;
	private String transId;
	private String cyberTypCd;
	private String orderNbr;
	private String channelNbr; // 渠道类型码 PC APP WAP

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
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

	public String getPayerCardTypCd() {
		return payerCardTypCd;
	}

	public void setPayerCardTypCd(String payerCardTypCd) {
		this.payerCardTypCd = payerCardTypCd;
	}

	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
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

	public String getPayerIdNbr() {
		return payerIdNbr;
	}

	public void setPayerIdNbr(String payerIdNbr) {
		this.payerIdNbr = payerIdNbr;
	}

	public String getPayerCardPwd() {
		return payerCardPwd;
	}

	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}

	public String getPayerIdTypCd() {
		return payerIdTypCd;
	}

	public void setPayerIdTypCd(String payerIdTypCd) {
		this.payerIdTypCd = payerIdTypCd;
	}

	public String getMerNbr() {
		return merNbr;
	}

	public void setMerNbr(String merNbr) {
		this.merNbr = merNbr;
	}

	public String getMerSeqNbr() {
		return merSeqNbr;
	}

	public void setMerSeqNbr(String merSeqNbr) {
		this.merSeqNbr = merSeqNbr;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getMerTransDateTime() {
		return merTransDateTime;
	}

	public void setMerTransDateTime(String merTransDateTime) {
		this.merTransDateTime = merTransDateTime;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getMerUrl() {
		return merUrl;
	}

	public void setMerUrl(String merUrl) {
		this.merUrl = merUrl;
	}

	public String getPayIp() {
		return payIp;
	}

	public void setPayIp(String payIp) {
		this.payIp = payIp;
	}

	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

	public String getPayerCardCvv2() {
		return payerCardCvv2;
	}

	public void setPayerCardCvv2(String payerCardCvv2) {
		this.payerCardCvv2 = payerCardCvv2;
	}

	public String getPayerCardExpiredDate() {
		return payerCardExpiredDate;
	}

	public void setPayerCardExpiredDate(String payerCardExpiredDate) {
		this.payerCardExpiredDate = payerCardExpiredDate;
	}

	public String getPayerAcctName() {
		return payerAcctName;
	}

	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}

	public String getMerUrl1() {
		return merUrl1;
	}

	public void setMerUrl1(String merUrl1) {
		this.merUrl1 = merUrl1;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getCyberTypCd() {
		return cyberTypCd;
	}

	public void setCyberTypCd(String cyberTypCd) {
		this.cyberTypCd = cyberTypCd;
	}

	public String getOrderNbr() {
		return orderNbr;
	}

	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}
	
	public String getChannelNbr() {
		return channelNbr;
	}

	public void setChannelNbr(String channelNbr) {
		this.channelNbr = channelNbr;
	}

}
