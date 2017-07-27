package com.csii.upp.dto.router.unionpay;

/**
 * 代收类交易异步应答
 * 
 * @author JIANGXING
 *
 */
public class RespDsjyyb extends RespUnionPayHead {
	private String settleAmt; //清算金额
	private String settleCurrencyCode; //清算币种
	private String settleDate; //清算日期
	private String traceNo; //系统跟踪号
	private String traceTime; //交易传输时间
	private String exchangeDate; //兑换日期
	private String exchangeRate; //汇率
	private String origQryId;//原始消费交易的银联流水号
	public String getOrigQryId() {
		return origQryId;
	}
	public void setOrigQryId(String origQryId) {
		this.origQryId = origQryId;
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
}
