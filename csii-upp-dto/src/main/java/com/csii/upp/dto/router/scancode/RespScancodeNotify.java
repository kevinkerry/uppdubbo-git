package com.csii.upp.dto.router.scancode;

/**
 * 二维码支付结果通知实体类
 * 
 * @author liru
 *
 */
public class RespScancodeNotify {
	private String transId; //交易码
	private String merId; //商户号
	private String payerAcctNbr; //付款人账号
	private String payerAcctName; //付款人名
	private String payAccessType; //支付接入类型
	private String payType; //支付类型
	private String txnSeqId; //二维码前置交易流水号
	private String txnTime;//二维码前置交易时间	
	private String clearDate; //清算日期
	private String orderId; //订单号
	private String orderAmount; //交易金额
	private String feeAmount; //手续费金额
	private String receiptAmoun; //实收金额
	private String orderTime; //交易时间
	private String currencyType; //币种
	private String transType;//交易类型
	private String respCode; //响应结果
	private String respMsg; //应答描述
	private String remark; //备用字段
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}
	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}
	public String getPayerAcctName() {
		return payerAcctName;
	}
	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}
	public String getPayAccessType() {
		return payAccessType;
	}
	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getTxnSeqId() {
		return txnSeqId;
	}
	public void setTxnSeqId(String txnSeqId) {
		this.txnSeqId = txnSeqId;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getClearDate() {
		return clearDate;
	}
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getReceiptAmoun() {
		return receiptAmoun;
	}
	public void setReceiptAmoun(String receiptAmoun) {
		this.receiptAmoun = receiptAmoun;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	
	
}
