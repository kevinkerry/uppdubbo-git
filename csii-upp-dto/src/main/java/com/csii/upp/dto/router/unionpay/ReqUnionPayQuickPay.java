package com.csii.upp.dto.router.unionpay;

import java.math.BigDecimal;

import com.csii.upp.constant.BizType;
import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.TxnType;
import com.csii.upp.dto.extend.InputFundData;

public class ReqUnionPayQuickPay extends ReqUnionPayHead {

	public ReqUnionPayQuickPay(InputFundData data) {
		super(data);
		this.setTxnType(TxnType.TXNTYPE_01);
		this.setTxnSubType("01");
		this.setBizType(BizType.BIZTYPE_000301);
		this.setChannelType("02".equals(data.getChannelNbr()) ? "08" : "07");//渠道类型（05：语音；07：互联网；08：移动）
		this.setBackUrl("UnionPayOtherBankQuickPayCallBack.do");
		this.setAccType("01");
		this.setMerId("929331053110008");
		this.setTxnTime(data.getSendUnionPayTime());
		this.setAccNo(data.getPayeracctnbr());
		this.setTxnAmt(data.getTransamt());
		this.setCurrencyCode("156");
		this.setCustomerInfo(getCustomer(this.getEncoding(), new CustomerInfoObj(data,ConstUnionPay.PAY_OUT)));// 短信验证码   
	}

	private String txnType; // 交易类型
	private String txnSubType; // 交易子类
	private String bizType; // 产品类型
	private String channelType; // 渠道类型
	private String backUrl; // 后台通知地址
	private String orderId; // 订单号
	private String accType; // 账号类型
	private String accNo; // 账号
	private BigDecimal txnAmt; // 交易金额
	private String currencyCode; // 交易币种
	private String customerInfo; // 客户信息
	private String reqReserved; // 请求方保留域
	private String reserved; // 保留域
	private String riskRateInfo; // 风险信息域

	public BigDecimal getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
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
}
