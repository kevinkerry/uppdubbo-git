package com.csii.upp.dto.router.unionpay;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.router.RespSysHead;

public class RespUnionPayHead extends RespSysHead {

	private String version; // 版本号
	private String encoding; // 编码方式
	private String certId; // 证书ID
	private String signature; // 签名
	private String signMethod; // 签名方法
	private String txnType; // 交易类型
	private String txnSubType; // 交易子类
	private String bizType; // 产品类型
	private String accessType; // 接入类型
	private String acqInsCode; // 收单机构代码
	private String merId; // 商户代码
	private String orderId; // 商户订单号
	private String txnTime; // 订单发送时间
	private String accNo; // 账号
	private BigDecimal txnAmt; // 交易金额
	private String currencyCode; // 交易币种
	private String reqReserved; // 请求方保留域
	private String reserved; // 保留域
	private String queryId; // 交易查询流水号
	private String respCode; // 响应码
	private String respMsg; // 应答信息
	private String payType; //支付方式
	private String payCardType; //支付卡类型


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
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

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getAcqInsCode() {
		return acqInsCode;
	}

	public void setAcqInsCode(String acqInsCode) {
		this.acqInsCode = acqInsCode;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
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

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
		this.setResultStatus(respCode);
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}
	


	@Override
	public void setResultStatus(String resultStatus) {
		this.setFundchannelcd(FundChannelCode.UNIONPAY);
		if (resultStatus != null) {
			if ("03".equals(resultStatus) || "04".equals(resultStatus) || "05".equals(resultStatus)
					|| "01".equals(resultStatus) || "12".equals(resultStatus) || "06".equals(resultStatus)) {
				setRtxnstate(TransStatus.TIMEOUT);
			} else if (ConstUnionPay.STATUS_SUCCESS.equals(resultStatus) || "A6".equals(resultStatus)) {
				setRtxnstate(TransStatus.PROCESSING);
				setDownrtxnnbr(getQueryId());
			}else {
				setRtxnstate(TransStatus.FAILURE);
				setReturncode(resultStatus);
				setReturnmsg(getRespMsg());
			}
		}
	}

}
