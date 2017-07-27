package com.csii.upp.dto.router.unionpay;

import com.csii.pe.common.util.Base64;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 建立绑定关系（实名认证）request
 * 
 * @author JIANGXING
 *
 */
public class ReqAuthenticateName extends ReqUnionPayHead {
	
	private String merId; 
	private String bizType; // 业务类型
	private String frontUrl; // 前台通知地址
	private String backUrl; // 交易后台返回商户结果时使用，如上送，则发送商户后台交易结果通知
	private String merCatCode; // 商户类别
	private String merName; // 商户名称
	private String merAbbr; // 商户简称
	private String subMerId; // 二级商户代码
	private String subMerName; // 二级商户全称
	private String subMerAbbr; // 二级商户简称
	private String accType;
	private String accNo;//对于前台类交易，返回卡号后4位，后台类交易，原样返回
	private String customerInfo;
	private String reqReserved;
	private String riskRateInfo;
	private String encryptCertId; // 加密证书ID
	private String userMac; // 终端信息域
	private String bindId; // 绑定关系标识号:需做建立绑定关系交易时填写
	private String relTxnType; // 关联业务标识:用于填写账户验证交易的关联业务类型(01：消费 02：代收)
	private String payCardType; // 支付卡类型
	private String issInsCode; // 发卡机构代码
	private String vpcTransData; // VPC交易信息域

	public ReqAuthenticateName(InputFundData data) {
		super(data);
		this.setTxnType("72"); // 交易类型
		this.setTxnSubType("01");//01：实名认证	02：免验建立绑定关系
		
		this.setBizType("000501");//业务类型
		this.setAccNo(data.getPayeracctnbr());
		
		String customerinfoBase64 = initCustomerinfo(data);
		this.setCustomerInfo(customerinfoBase64);
		this.setTransCode("AUTN");//交易类型是实名认证，在发送报文时使用
	}
	

	private String initCustomerinfo(InputFundData data) {
		
		StringBuilder customerinfo = new StringBuilder();
		customerinfo.append("{");
		if(data.getPayerphoneno() != null){
			customerinfo.append("phoneNo="+data.getPayerphoneno()+"&");
		}
		//certifyTp和certifyId必须同时存在
		if(data.getPayeridnbr() != null && data.getPayeridnbr() != null){
			customerinfo.append("certifTp="+data.getPayeridtypcd()+"&certifId="+data.getPayeridtypcd()+"&");
		}
		if(data.getPayername() != null){
			customerinfo.append("customerNm="+data.getPayername()+"&");
		}
		
		customerinfo.deleteCharAt(customerinfo.length()-1);
		customerinfo.append("}");
		
		String customerinfoBase64 = Base64.byteArrayToBase64(customerinfo.toString().getBytes());
		return customerinfoBase64;
	}


	public String getMerId() {
		return merId;
	}



	public void setMerId(String merId) {
		this.merId = merId;
	}



	public String getBizType() {
		return bizType;
	}



	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	

	public String getFrontUrl() {
		return frontUrl;
	}



	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}



	public String getBackUrl() {
		return backUrl;
	}



	public void setBackUrl(String backurl) {
		this.backUrl = backurl;
	}



	public String getMerCatCode() {
		return merCatCode;
	}

	public void setMerCatCode(String merCatCode) {
		this.merCatCode = merCatCode;
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

	public String getUserMac() {
		return userMac;
	}

	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getRelTxnType() {
		return relTxnType;
	}

	public void setRelTxnType(String relTxnType) {
		this.relTxnType = relTxnType;
	}

	public String getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(String payCardType) {
		this.payCardType = payCardType;
	}

	public String getIssInsCode() {
		return issInsCode;
	}

	public void setIssInsCode(String issInsCode) {
		this.issInsCode = issInsCode;
	}

	public String getVpcTransData() {
		return vpcTransData;
	}

	public void setVpcTransData(String vpcTransData) {
		this.vpcTransData = vpcTransData;
	}
}
