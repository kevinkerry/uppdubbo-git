package com.csii.upp.dto.router.paym;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.StringUtil;

/**
 * 卡密支付交易请求
 * @author fgq
 *
 */
public class ReqPayTrans extends ReqPaymHead {

	private String smsCode;
	private String smsInnerFundTransNbr;
	private String sendUnionPayTime;
	private List<Map> merTransList;
	private String payerBankNbr;
	private String frontCallBackUrl;
	private String clientNo;
	private String branchNo;
	private String deductAmt;
	private String interalFlag;
	private String actMemo;
	//二维码相关字段
	private String scanCodeMode;
	private String codeTypCd;
	private String discountableamt;
	private String isCredit;
	private String timeStart;
	private String timeExpire;
	private String customerIp;
	private String productInfoDetail;
	private String productId;
	private String proBody;
	private String subject;
	private String operatorId;
	private String storeId;
	private String termId;
	private String timeoutExpress;
	private String alipayStoreId;
	private String scene;
	private String authCode;
	private String goodsDetail;
	public String getPayerAcctOrgDeptNbr() {
		return payerAcctOrgDeptNbr;
	}

	public void setPayerAcctOrgDeptNbr(String payerAcctOrgDeptNbr) {
		this.payerAcctOrgDeptNbr = payerAcctOrgDeptNbr;
	}

	private String bizType;
	private String requestModel;
	private String tradeCode;
	private String cifNo;
	private String term;
	private String payerAcctOrgDeptNbr;

	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public ReqPayTrans(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.PayTrans);
		this.setSmsCode(data.getSmsCode());
		this.setSmsInnerFundTransNbr(data.getSmsInnerFundTransNbr());
		this.setSendUnionPayTime(data.getSendUnionPayTime());
		this.setMerTransList(data.getMersubtranslist());
		this.setPayerBankNbr(data.getPayerbanknbr());
		this.setFrontCallBackUrl(data.getFrontCallBackUrl());
		this.setDeductAmt(StringUtil.parseObjectToString(data.getDeductamt()));
		this.setBranchNo(data.getBranchno());
		this.setClientNo(data.getClientno());
		this.setInteralFlag(data.getInteralflag());
		this.setActMemo(data.getActMemo());
		this.setScanCodeMode(data.getScancodemode());
		this.setCodeTypCd(data.getCodetypcd());
		this.setDiscountableamt(StringUtil.parseObjectToString(data.getDiscountableamt()));
		this.setIsCredit(data.getIsCredit());
		this.setTimeStart(data.getTimeStart());
		this.setTimeExpire(data.getTimeExpire());
		this.setCustomerIp(data.getCustomerIp());
		this.setProductInfoDetail(data.getProductInfoDetail());
		this.setProductId(data.getProductId());
		this.setProBody(data.getProBody());
		this.setSubject(data.getSubject());
		this.setOperatorId(data.getOperatorId());
		this.setTermId(data.getTermId());
		this.setTimeoutExpress(data.getTimeoutExpress());
		this.setStoreId(data.getStoreId());
		this.setAlipayStoreId(data.getAlipayStoreId());
		this.setScene(data.getScene());
		this.setAuthCode(data.getAuthCode());
		this.setGoodsDetail(data.getGoodsDetail());
		this.setBizType(data.getBizType());
		this.setRequestModel(data.getRequestModel());
		this.setTradeCode(data.getTradeCode());
		this.setCifNo(data.getCifNo());
		this.setTerm(data.getTerm());
		this.setPayerAcctOrgDeptNbr(data.getPayerAcctOrgDeptNbr());
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getRequestModel() {
		return requestModel;
	}

	public void setRequestModel(String requestModel) {
		this.requestModel = requestModel;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	
	public String getActMemo() {
		return actMemo;
	}

	public void setActMemo(String actMemo) {
		this.actMemo = actMemo;
	}

	public String getSmsInnerFundTransNbr() {
		return smsInnerFundTransNbr;
	}

	public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
		this.smsInnerFundTransNbr = smsInnerFundTransNbr;
	}

	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}

	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}

	public String getPayerBankNbr() {
		return payerBankNbr;
	}

	public void setPayerBankNbr(String payerBankNbr) {
		this.payerBankNbr = payerBankNbr;
	}

	public String getSendUnionPayTime() {
		return sendUnionPayTime;
	}

	public void setSendUnionPayTime(String sendUnionPayTime) {
		this.sendUnionPayTime = sendUnionPayTime;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public List<Map> getMerTransList() {
		return merTransList;
	}

	public void setMerTransList(List<Map> merTransList) {
		this.merTransList = merTransList;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getInteralFlag() {
		return interalFlag;
	}

	public void setInteralFlag(String interalFlag) {
		this.interalFlag = interalFlag;
	}

	public String getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(String deductAmt) {
		this.deductAmt = deductAmt;
	}

	public String getScanCodeMode() {
		return scanCodeMode;
	}

	public void setScanCodeMode(String scanCodeMode) {
		this.scanCodeMode = scanCodeMode;
	}

	public String getCodeTypCd() {
		return codeTypCd;
	}

	public void setCodeTypCd(String codeTypCd) {
		this.codeTypCd = codeTypCd;
	}

	public String getDiscountableamt() {
		return discountableamt;
	}

	public void setDiscountableamt(String discountableamt) {
		this.discountableamt = discountableamt;
	}

	public String getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getProductInfoDetail() {
		return productInfoDetail;
	}

	public void setProductInfoDetail(String productInfoDetail) {
		this.productInfoDetail = productInfoDetail;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProBody() {
		return proBody;
	}

	public void setProBody(String proBody) {
		this.proBody = proBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}

	public String getAlipayStoreId() {
		return alipayStoreId;
	}

	public void setAlipayStoreId(String alipayStoreId) {
		this.alipayStoreId = alipayStoreId;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}


	
	
	
}
