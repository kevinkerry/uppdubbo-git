package com.csii.upp.dto.router.alipacode;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;
/**
 * 支付宝下单支付请求类
 * @author wy
 *
 */
public class ReqAlipayCodeActivePay extends ReqAlipayCodePreHead {
	private String orderAmount;
	private String orderTime;
	private String orderNumber;
	private String transType;
	private String merId;
	private String merName;
	private String discountableAmount;
	private String undiscountableAmount;
	private String payType;
	private String subject;
	private String body;
	private String goodsDetail;
	private String operatorId;
	private String storeId;
	private String termId;
	private String timeoutExpress;
	private String alipayStoreId;
	private String alipayMerchantId;
	public ReqAlipayCodeActivePay(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.AlipayCodeActivePay);
		this.setTransType("01");//01:消费
		if(!StringUtil.isStringEmpty(data.getQrcodeordernbr())){
			this.setOrderNumber(data.getQrcodeordernbr());
			this.setOrderAmount(formatAmt(data.getTransamt()));
		}else{
			this.setOrderNumber(data.getInnerfundtransnbr());
			this.setOrderAmount(formatAmt(data.getTransamt().add(data.getDiscountableamt())));
		}
		this.setOrderTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setDiscountableAmount(formatAmt(data.getDiscountableamt()));
		this.setPayType(ScanCodeMode.ACTIVE);
		this.setSubject(data.getSubject());
		this.setBody(data.getProBody());
		this.setGoodsDetail(data.getProductInfoDetail());
		this.setOperatorId(data.getOperatorId());
		this.setStoreId(data.getStoreId());
		this.setTermId(data.getTermId());
		this.setTimeoutExpress(data.getTimeoutExpress());
		this.setAlipayStoreId(data.getAlipayStoreId());
		List<Map<String, String>> payeeAcctList = data.getPayeeAcctList();
		this.setMerId(payeeAcctList.get(0).get(Dict.PROXY_MER_NBR));
		this.setAlipayMerchantId(payeeAcctList.get(0).get(Dict.THIRD_MER_NBR));
		this.setMerName((payeeAcctList.get(0).get(Dict.MER_NAME)));
		data.setPayeeacctnbr(this.getMerId());
	
		
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getDiscountableAmount() {
		return discountableAmount;
	}
	public void setDiscountableAmount(String discountableAmount) {
		this.discountableAmount = discountableAmount;
	}
	public String getUndiscountableAmount() {
		return undiscountableAmount;
	}
	public void setUndiscountableAmount(String undiscountableAmount) {
		this.undiscountableAmount = undiscountableAmount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getGoodsDetail() {
		return goodsDetail;
	}
	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
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
	public String getAlipayMerchantId() {
		return alipayMerchantId;
	}
	public void setAlipayMerchantId(String alipayMerchantId) {
		this.alipayMerchantId = alipayMerchantId;
	}
}
