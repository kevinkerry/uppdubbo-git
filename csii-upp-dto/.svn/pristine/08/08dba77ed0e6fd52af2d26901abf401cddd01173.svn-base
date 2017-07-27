package com.csii.upp.dto.router.unionpay;


import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;


public class ReqUnionPayCheck extends ReqUnionPayHead {

	public ReqUnionPayCheck(InputFundData data) {
		super(data);
		setTransCode("ACP076");
		this.setBizType("000000");
		this.setTxnType("76");
		this.setTxnSubType("01");
//		this.setTxnTime(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
		this.setFileType("00");
//		this.setSignature(signature);
		//this.setMerId("802290049000180");
		this.setSettleDate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(), "MMdd"));	
		
	}
	private String version;//版本号aa
	private String encoding;//编码方式
	private String certId;//证书ID
	private String signature;//签名
	private String signMethod;//签名方法
	private String txnType;//交易类型
	private String txnSubType;//交易子类
	private String bizType; //产品类型
	private String accessType;//接入类型
	private String merId;//商户代码
	private String acqInsCode;//收单机构代码
	private String settleDate;//清算日期
	private String txnTime;//订单发送时间
	private String fileType;//文件类型
	private String reqReserved;//请求方保留域
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
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getAcqInsCode() {
		return acqInsCode;
	}
	public void setAcqInsCode(String acqInsCode) {
		this.acqInsCode = acqInsCode;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}

	 
	
	
}
