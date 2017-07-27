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
 * @author wy
 * 发往fundprocess
 *
 */
public class ReqQueryQrCodeUrl extends ReqFundProHead {
	private String merName;
	private String subMerchantId;
	private String thirdMerNbr;
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
	private List<Map<String,String>> payeeAcctList;   //收款账户列表
	public ReqQueryQrCodeUrl(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.QueryQrCodeUrl);
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
		this.setPayeeAcctList(data.getPayeeAcctList());
		this.setMerName(data.getMerName());
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
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getSubMerchantId() {
		return subMerchantId;
	}
	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}
	public String getThirdMerNbr() {
		return thirdMerNbr;
	}
	public void setThirdMerNbr(String thirdMerNbr) {
		this.thirdMerNbr = thirdMerNbr;
	}
	/**
	 * 合并相同机构的子订单用于记账
	 * 
	 * @param onlineSubTransList
	 * @return
	 */
	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}
	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}
	
	
}
