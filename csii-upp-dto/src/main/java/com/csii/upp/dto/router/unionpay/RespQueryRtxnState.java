package com.csii.upp.dto.router.unionpay;

import java.math.BigDecimal;

/**
 * 交易状态查询类交易应答报文
 * 
 * @author JIANGXING
 *
 */
public class RespQueryRtxnState extends RespUnionPayHead {
	private String acqInsCode; // 收单机构代码：接入类型为收单机构接入时需上送
	private String txnTime; // 订单时间
	private String orderId; // 商户订单号
	private String reqReserved; // 请求方保留域
	private String reserved; // 保留域
	private String queryId; // 交易查询流水号
	private String traceNo; // 系统跟踪号
	private String traceTime; // 交易传输时间
	private String settleDate; // 清算日期
	private String settleCurrencyCode; // 清算币种
	private String settleAmt; // 清算金额
	private String exchangeRate; // 汇率
	private String exchangeDate; // 兑换日期
	private String currencyCode;// 默认值
	private BigDecimal txnAmt; // 交易金额
	private String origRespCode; // 原交易应答码
	private String origRespMsg; // 原交易应答信息
 
 
	private String accNo; // 账号
	private String payCardType; // 支付卡类型
	private String payType; // 支付方式
	private String payCardNo; // 支付卡标识
	private String payCardIssueName; // 支付卡名称
	private String vpcTransData; // VPC交易信息域
	private String cardTransData; // 有卡交易信息域
	private String preAuthId; // 预授权号
	private String issuerIdentifyMode; // 发卡机构识别模式
	private String bindId; // 绑定关系标识号
	private String balance; // 余额
	private String activateStatus; // 开通状态

	public String getAcqInsCode() {
		return acqInsCode;
	}

	public void setAcqInsCode(String acqInsCode) {
		this.acqInsCode = acqInsCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
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

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}


	public String getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}

	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}

	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getOrigRespCode() {
		return origRespCode;
	}

	public void setOrigRespCode(String origRespCode) {
		this.origRespCode = origRespCode;
	}

	public String getOrigRespMsg() {
		return origRespMsg;
	}

	public void setOrigRespMsg(String origRespMsg) {
		this.origRespMsg = origRespMsg;
	}

	public String getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getPayCardIssueName() {
		return payCardIssueName;
	}

	public void setPayCardIssueName(String payCardIssueName) {
		this.payCardIssueName = payCardIssueName;
	}

	public String getVpcTransData() {
		return vpcTransData;
	}

	public void setVpcTransData(String vpcTransData) {
		this.vpcTransData = vpcTransData;
	}

	public String getCardTransData() {
		return cardTransData;
	}

	public void setCardTransData(String cardTransData) {
		this.cardTransData = cardTransData;
	}

	public String getPreAuthId() {
		return preAuthId;
	}

	public void setPreAuthId(String preAuthId) {
		this.preAuthId = preAuthId;
	}

	public String getIssuerIdentifyMode() {
		return issuerIdentifyMode;
	}

	public void setIssuerIdentifyMode(String issuerIdentifyMode) {
		this.issuerIdentifyMode = issuerIdentifyMode;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getActivateStatus() {
		return activateStatus;
	}

	public void setActivateStatus(String activateStatus) {
		this.activateStatus = activateStatus;
	}
	
}
