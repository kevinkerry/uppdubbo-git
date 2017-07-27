package com.csii.upp.dto.router.unionpay;

import com.csii.upp.annotation.AttributeProperties;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqSysHead;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;
import com.unionpay.acp.sdk.SecureUtil;

public class ReqUnionPayHead extends ReqSysHead {

	public ReqUnionPayHead(InputFundData data) {
		super(data);
		if(StringUtil.isStringEmpty(data.getTransnbr())){
			data.setTransnbr(IDGenerateFactory.generateInnerFundTransNbr());
		}
		if(StringUtil.isStringEmpty(data.getInnerfundtransnbr())){
			data.setInnerfundtransnbr(data.getTransnbr());
		}
		this.setReqSeqNo(data.getTransnbr());
		data.setFundchannelcode(FundChannelCode.UNIONPAY);
		this.setChnlId(FundChannelCode.PAY);
		this.setSrvChnlId(FundChannelCode.UNIONPAY);
		this.setEncoding("UTF-8"); // 编码方式
		this.setSignMethod("01"); // 签名方法：取值：01（表示采用RSA）
		this.setAccessType("0");//接入类型 0 普通商户 2 平台商户
		this.setMerId(data.getMerId());
		this.setTxnTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3)); //交易时间 
	}
 
	/**
	 * 持卡人信息域操作
	 * 
	 * @param encoding  编码方式
	 * @return base64后的持卡人信息域字段
	 */
	 public String getCustomer(String encoding,CustomerInfoObj customerInfoobj ) {
		StringBuilder sf = new StringBuilder("{");
		// 证件类型
		String sepstr = "&";
		if(!StringUtil.isObjectEmpty(customerInfoobj.getCertifTp())){
			sf.append("certifTp=" + customerInfoobj.getCertifTp() + sepstr);
		}
		if(!StringUtil.isObjectEmpty( customerInfoobj.getCertifId())){
			sf.append("certifId=" + customerInfoobj.getCertifId() + sepstr);
		}
		if(!StringUtil.isObjectEmpty(customerInfoobj.getCustomerNm())){
			sf.append("customerNm=" + customerInfoobj.getCustomerNm()+ sepstr );
		}
		if(!StringUtil.isObjectEmpty(customerInfoobj.getPhoneNo())){
			sf.append("phoneNo=" + customerInfoobj.getPhoneNo() + sepstr);
		}
		if(!StringUtil.isObjectEmpty(customerInfoobj.getSmsCode())){
			sf.append("smsCode=" + customerInfoobj.getSmsCode() + sepstr);
		}
		String strCustomerInfo = sf.toString();
		strCustomerInfo =(strCustomerInfo.length()==1)?strCustomerInfo: strCustomerInfo.substring(0,sf.length()-1).concat("}");
		try {
			return new String(SecureUtil.base64Encode(strCustomerInfo.getBytes(
					encoding)));  
		} catch (Exception e) {
		}
		return strCustomerInfo;
	}
	
	@AttributeProperties(required = true)
	private String version;
	@AttributeProperties(required = true)
	private String encoding;
	@AttributeProperties(required = true)
	private String certId;
	@AttributeProperties(required = true)
	private String signMethod;
	@AttributeProperties(required = true)
	private String accessType;
	@AttributeProperties(required = true)
	private String orderId;
	@AttributeProperties(required = true)
	private String merId; //商户代码
	@AttributeProperties(required = true)
	private String txnType; //交易类型
	@AttributeProperties(required = true)
	private String txnSubType; //交易子类
	@AttributeProperties(required = true)
	private String bizType; //产品类型
	@AttributeProperties(required = true)
	private String txnTime; //订单发送时间
	@AttributeProperties(required = true)
	private String signature; //签名
	@AttributeProperties(required = true)
	private String backUrl; // 交易后台返回商户结果时使用，如上送，则发送商户后台交易结果通知
	
	private String frontUrl; // 前台通知地址
	private String customerInfo; // 用户信息
	private String accNo;

	
	@AttributeProperties(required = true)
	private String currencyCode;
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
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
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
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String date) {
		this.txnTime = date;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
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

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
}
