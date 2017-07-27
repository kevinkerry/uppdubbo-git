/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou
 * 发往fundprocess的支付交易响应类 
 *
 */
public class ReqUnifiedPayment extends ReqFundProHead {
	private String smsCode;
	private String smsInnerFundTransNbr;
	private String sendUnionPayTime;
	private String checkNumber;   //对账分类编号
	private List<Map<String,String>> payeeAcctList;   //收款账户列表
	private String note; //备用字段
	private String frontCallBackUrl;
	private String payTypCd;
	private String deductAmt;//本次消费积分
	private String realAmt;//商户实际支付的金额
	private String branchNo;//行社号
	private String clientNo;//客户内码 
	private String interalFlag;//积分标识
	private String pointReacctdeptNbr;
	private String scanCodeMode;//扫码模式
	private String codeTypCd;//二维码类型
	private String qrcodeordernbr;//原二维码订单号
	private String discountableamt;//可打折金额
	private String isCredit;//是否支持信用卡
	private String timeStart;//订单开始时间
	private String timeExpire;//订单截止时间
	private String customerIp;//微信终端号
	private String productInfoDetail;//商品信息列表
	private String productId;//商品号
	private String proBody;//商品描述
	private String subject;//订单标题
	private String operatorId;//商户操作员号
	private String storeId;//商户门店号
	private String termId;//终端号
	private String timeoutExpress;//订单允许支付时间
	private String alipayStoreId;//支付宝门店号
	private String scene;//支付场景
	private String authCode;//支付授权码
	private String goodsDetail;//商品详情
	private String cifNo;
	private String term;
	private String payerAcctOrgDeptNbr;
	
	
	public String getPayerAcctOrgDeptNbr() {
		return payerAcctOrgDeptNbr;
	}
	public void setPayerAcctOrgDeptNbr(String payerAcctOrgDeptNbr) {
		this.payerAcctOrgDeptNbr = payerAcctOrgDeptNbr;
	}
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
	public ReqUnifiedPayment(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.UnifiedPayment);
		this.setPayeeAcctList(data.getPayeeAcctList());
		this.setSmsCode(data.getSmsCode());
		this.setSmsInnerFundTransNbr(data.getSmsInnerFundTransNbr());;
		this.setSendUnionPayTime(data.getSendUnionPayTime());
		this.setFrontCallBackUrl(data.getFrontCallBackUrl());
		this.setPayTypCd(data.getPaytypcd());
		this.setDeductAmt(StringUtil.parseObjectToString(data.getDeductamt()));
		this.setBranchNo(data.getBranchno());
		this.setClientNo(data.getClientno());
		this.setInteralFlag(data.getInteralflag());
		this.setRealAmt(StringUtil.parseObjectToString(data.getRealamt()));
		this.setPointReacctdeptNbr(data.getPointreacctdeptnbr());
		this.setCodeTypCd(data.getCodetypcd());
		this.setQrcodeordernbr(data.getQrcodeordernbr());
		this.setScanCodeMode(data.getScancodemode());
		this.setDiscountableamt(StringUtil.parseObjectToString(data.getDiscountableamt()));
		this.setIsCredit(data.getIsCredit());
		this.setTimeStart(data.getTimeStart());
		this.setTimeExpire(data.getTimeExpire());
		this.setCustomerIp(data.getCustomerIp());
		this.setProductInfoDetail(data.getProductInfoDetail());
		this.setProductId(data.getProductId());
		this.setProBody(data.getProPody());
		this.setSubject(data.getSubject());
		this.setOperatorId(data.getOperatorId());
		this.setTermId(data.getTermId());
		this.setTimeoutExpress(data.getTimeoutExpress());
		this.setStoreId(data.getStoreId());
		this.setAlipayStoreId(data.getAlipayStoreId());
		this.setScene(data.getScene());
		this.setAuthCode(data.getAuthCode());
		this.setGoodsDetail(data.getGoodsDetail());
		this.setCifNo(data.getCifNo());
		this.setTerm(data.getTerm());
		this.setPayerAcctOrgDeptNbr(data.getPayerAcctOrgDeptNbr());
	}
	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}

	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}
	
	public String getSmsInnerFundTransNbr() {
		return smsInnerFundTransNbr;
	}

	public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
		this.smsInnerFundTransNbr = smsInnerFundTransNbr;
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
	
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}

	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public String getPayTypCd() {
		return payTypCd;
	}
	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getClientNo() {
		return clientNo;
	}
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	
	public String getDeductAmt() {
		return deductAmt;
	}
	public void setDeductAmt(String deductAmt) {
		this.deductAmt = deductAmt;
	}
	public String getInteralFlag() {
		return interalFlag;
	}
	public void setInteralFlag(String interalFlag) {
		this.interalFlag = interalFlag;
	}
	public String getRealAmt() {
		return realAmt;
	}
	public void setRealAmt(String realAmt) {
		this.realAmt = realAmt;
	}
	public String getPointReacctdeptNbr() {
		return pointReacctdeptNbr;
	}
	public void setPointReacctdeptNbr(String pointReacctdeptNbr) {
		this.pointReacctdeptNbr = pointReacctdeptNbr;
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
	public String getQrcodeordernbr() {
		return qrcodeordernbr;
	}
	public void setQrcodeordernbr(String qrcodeordernbr) {
		this.qrcodeordernbr = qrcodeordernbr;
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
