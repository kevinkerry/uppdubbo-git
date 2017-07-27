package com.csii.upp.dto.router.unionpay;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.dto.extend.InputFundData;

public class ReqPayForAnother extends ReqUnionPayHead {

 
	private String txnSubType; //交易子类
	private String bizType; //产品类型
	private String channelType; //渠道类型
 
	private String backUrl; //后台通知地址
	private String acqInsCode; //收单机构代码
	private String merCatCode; //商户类别
	private String merId; //商户代码
	private String merName; //商户名称
	private String merAbbr; //商户简称
	private String subMerId; //二级商户代码
	private String subMerName; //二级商户全称
	private String subMerAbbr; //二级商户简称
	private String accType; //账号类型
	private String accNo; //账号
	private BigDecimal txnAmt; //交易金额
	private String currencyCode; //交易币种
	
	private String bindId; //绑定标识号
	private String billType; //账单类型
	private String billNo; //账单号码
 
	private String billPeriod; //账单周期
	private String reqReserved; //请求方保留域
	private String reserved; //保留域
	private String riskRateInfo; //风险信息域
	private String encryptCertId; //加密证书ID
 
	private String termId; //终端号
	private String cardTransData; //有卡交易信息域

	
	public ReqPayForAnother(InputFundData data) {
		super(data);
		this.setTxnType("12");
		this.setTxnSubType("00");
		this.setBizType("000401");
		this.setFrontUrl("");//代付为何是前段url
		this.setTxnAmt(data.getTransamt());
		this.setAccNo(data.getPayeeacctnbr());
		this.setBackUrl("UnionPayDFCallBack.do");
		this.setMerId("454424060120001");
		this.setCustomerInfo(getCustomer(this.getEncoding(),new CustomerInfoObj(data,ConstUnionPay.PAY_OUT) ));//   需确定   
		this.setCurrencyCode("156");//默认人民币
		/*  非必输字段
		this.setAcqInsCode("");
		this.setMerCatCode("");
		
		this.setMerName("");
		this.setMerAbbr("");
		this.setSubMerId("");
		this.setSubMerName("");
		this.setSubMerAbbr("");
		this.setTxnTime("");
		this.setAccType("");
		
		
		this.setCurrencyCode("");
	
		this.setBindId("");
		this.setBillType("");
		this.setBillNo("");
		
		this.setBillPeriod("");
		this.setReqReserved("");
		this.setReserved("");
		this.setRiskRateInfo("");
		this.setEncryptCertId("");
		
		this.setTermId("");
		this.setCardTransData("");

		
		
		
		this.setTxnType("12");// 代付
		this.setTxnSubType("00");
		this.setBizType("000401");
		this.txntime = data.getRtxnTime();
		this.accno = data.getPayeracctnbr();
		this.acctype = data.getPayeraccttypcd();
		this.amt = data.getTransamt();
		this.customerinfo = new CustomerInfo(data);
		this.setTransCode("UPPF");
		*/
	}
	private String signature;
	private String txnType;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnSubType() {
		return txnSubType;
	}
	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getAcqInsCode() {
		return acqInsCode;
	}
	public void setAcqInsCode(String acqInsCode) {
		this.acqInsCode = acqInsCode;
	}
	public String getMerCatCode() {
		return merCatCode;
	}
	public void setMerCatCode(String merCatCode) {
		this.merCatCode = merCatCode;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerAbbr() {
		return merAbbr;
	}
	public void setMerAbbr(String merAbbr) {
		this.merAbbr = merAbbr;
	}
	public String getSubMerId() {
		return subMerId;
	}
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}
	public String getSubMerName() {
		return subMerName;
	}
	public void setSubMerName(String subMerName) {
		this.subMerName = subMerName;
	}
	public String getSubMerAbbr() {
		return subMerAbbr;
	}
	public void setSubMerAbbr(String subMerAbbr) {
		this.subMerAbbr = subMerAbbr;
	}
	 
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public BigDecimal getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
 
	public String getBindId() {
		return bindId;
	}
	public void setBindId(String bindId) {
		this.bindId = bindId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillPeriod() {
		return billPeriod;
	}
	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getRiskRateInfo() {
		return riskRateInfo;
	}
	public void setRiskRateInfo(String riskRateInfo) {
		this.riskRateInfo = riskRateInfo;
	}
	public String getEncryptCertId() {
		return encryptCertId;
	}
	public void setEncryptCertId(String encryptCertId) {
		this.encryptCertId = encryptCertId;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getCardTransData() {
		return cardTransData;
	}
	public void setCardTransData(String cardTransData) {
		this.cardTransData = cardTransData;
	}
	


	 

	
}
