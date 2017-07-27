package com.csii.upp.dto.router.fundprocess;

import java.util.UUID;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.router.ReqSysHead;
import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou
 * 支付收银Payment的请求头
 *
 */
public class ReqFundProHead extends ReqSysHead {

	public ReqFundProHead(InputPaymentData data) {
		super(data);
		if(!StringUtil.isStringEmpty(data.getTransseqnbr())){
			this.setReqSeqNo(data.getTransseqnbr());
		}
		if(StringUtil.isStringEmpty(this.getReqSeqNo())){
			this.setReqSeqNo(String.valueOf(UUID.randomUUID().getLeastSignificantBits()));
		}
		this.setChnlId(FundChannelCode.PAYM);
		this.setSrvChnlId(FundChannelCode.FDPS);
		this.setChannelNbr(data.getChannelnbr());
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerAcctTypCd(data.getPayeraccttypcd());
		this.setPayerCardTypCd(data.getPayercardtypcd());
		this.setPayerCardPwd(data.getPayercardpwd());
		this.setPayerPhoneNo(data.getPayerphoneno());
		this.setPayerCardCvv2(data.getPayercardcvv2());
		this.setPayerCardExpiredDate(data.getPayercardexpireddate());
		this.setPayerAcctName(data.getPayeracctname());
		this.setPayerAcctKind(data.getPayeracctkind());
		this.setPayerIdNbr(data.getPayeridnbr());
		this.setPayerIdTypCd(data.getPayeridtypcd());
		
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setPayeeAcctTypCd(data.getPayeeaccttypcd());
		this.setPayeeCardTypCd(data.getPayeecardtypcd());
		this.setPayeePhoneNo(data.getPayeephoneno());
		this.setPayeeCardCvv2(data.getPayeecardcvv2());
		this.setPayeeCardExpiredDate(data.getPayeecardexpireddate());
		this.setPayeeAcctKind(data.getPayeeacctkind());
		this.setPayeeBankNbr(data.getPayeebanknbr());
		this.setPayeeAcctName(data.getPayeeacctname());
		this.setCustCifNo(data.getCustcifnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTransAmt(StringUtil.parseObjectToString(data.getTransamt()));
		this.setNote(data.getNote());
		this.setCyberTypCd(data.getCybertypcd());
		this.setPayerAcctDeptNbr(data.getPayeracctdeptnbr());
		}

	
	private String payerAcctNbr; // 卡账号
	private String payerAcctTypCd; // 卡账户类
	private String payerCardTypCd; // 卡号类型
	private String payerCardPwd; //付款人卡密码
	private String payerAcctName; //付款人账户姓名
	private String payerCardCvv2;
	private String payerCardExpiredDate;
	private String payerAcctKind;
	private String payerPhoneNo;  //付款人手机号
	private String payerOrgNbr;//付款机构号
	private String payerIdNbr;//付款证件号码
	private String payerIdTypCd;//付款证件类型 
	private String custCifNo;
	private String currencyCd; // 币种
	private String transAmt; //支付总金额
	private String note;
	
	private String payeeAcctNbr; // 卡账号
	private String payeeAcctTypCd; // 卡账户类型
	private String payeeCardTypCd; // 卡号类型
	private String payeeCardCvv2;
	private String payeeCardExpiredDate;
	private String payeeAcctKind;
	private String payeePhoneNo;  //收款人手机号
	private String payeeBankNbr; //收款人行号
	private String payeeAcctName;//收款人姓名
	private String cyberTypCd; //网银类型
	private String payerAcctDeptNbr;
	private String channelNbr; // 渠道类型码 PC APP WAP
	

	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}

	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}


	public String getPayerAcctTypCd() {
		return payerAcctTypCd;
	}

	public void setPayerAcctTypCd(String payerAcctTypCd) {
		this.payerAcctTypCd = payerAcctTypCd;
	}

	public String getCustCifNo() {
		return custCifNo;
	}

	public void setCustCifNo(String custCifNo) {
		this.custCifNo = custCifNo;
	}

	public String getPayerAcctKind() {
		return payerAcctKind;
	}

	public void setPayerAcctKind(String payerAcctKind) {
		this.payerAcctKind = payerAcctKind;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	
	public String getPayerCardPwd() {
		return payerCardPwd;
	}

	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
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

	public String getPayerCardTypCd() {
		return payerCardTypCd;
	}

	public void setPayerCardTypCd(String payerCardTypCd) {
		this.payerCardTypCd = payerCardTypCd;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
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

	public String getPayerOrgNbr() {
		return payerOrgNbr;
	}

	public void setPayerOrgNbr(String payerOrgNbr) {
		this.payerOrgNbr = payerOrgNbr;
	}

	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}

	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}

	public String getPayeeAcctTypCd() {
		return payeeAcctTypCd;
	}

	public void setPayeeAcctTypCd(String payeeAcctTypCd) {
		this.payeeAcctTypCd = payeeAcctTypCd;
	}

	public String getPayeeCardTypCd() {
		return payeeCardTypCd;
	}

	public void setPayeeCardTypCd(String payeeCardTypCd) {
		this.payeeCardTypCd = payeeCardTypCd;
	}

	public String getPayeeCardCvv2() {
		return payeeCardCvv2;
	}

	public void setPayeeCardCvv2(String payeeCardCvv2) {
		this.payeeCardCvv2 = payeeCardCvv2;
	}

	public String getPayeeCardExpiredDate() {
		return payeeCardExpiredDate;
	}

	public void setPayeeCardExpiredDate(String payeeCardExpiredDate) {
		this.payeeCardExpiredDate = payeeCardExpiredDate;
	}

	public String getPayeeAcctKind() {
		return payeeAcctKind;
	}

	public void setPayeeAcctKind(String payeeAcctKind) {
		this.payeeAcctKind = payeeAcctKind;
	}

	public String getPayeePhoneNo() {
		return payeePhoneNo;
	}

	public void setPayeePhoneNo(String payeePhoneNo) {
		this.payeePhoneNo = payeePhoneNo;
	}

	public String getPayerIdNbr() {
		return payerIdNbr;
	}

	public void setPayerIdNbr(String payerIdNbr) {
		this.payerIdNbr = payerIdNbr;
	}

	public String getPayerIdTypCd() {
		return payerIdTypCd;
	}

	public void setPayerIdTypCd(String payerIdTypCd) {
		this.payerIdTypCd = payerIdTypCd;
	}

	public String getCyberTypCd() {
		return cyberTypCd;
	}

	public void setCyberTypCd(String cyberTypCd) {
		this.cyberTypCd = cyberTypCd;
	}

	public String getPayerAcctDeptNbr() {
		return payerAcctDeptNbr;
	}

	public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
		this.payerAcctDeptNbr = payerAcctDeptNbr;
	}

	public String getPayeeBankNbr() {
		return payeeBankNbr;
	}

	public void setPayeeBankNbr(String payeeBankNbr) {
		this.payeeBankNbr = payeeBankNbr;
	}

	public String getPayeeAcctName() {
		return payeeAcctName;
	}

	public void setPayeeAcctName(String payeeAcctName) {
		this.payeeAcctName = payeeAcctName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getChannelNbr() {
		return channelNbr;
	}

	public void setChannelNbr(String channelNbr) {
		this.channelNbr = channelNbr;
	}
	
}
