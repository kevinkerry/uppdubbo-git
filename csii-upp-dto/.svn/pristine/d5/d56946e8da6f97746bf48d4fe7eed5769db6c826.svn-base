package com.csii.upp.dto.router.wechatcode;

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
 * 微信被扫支付请求类
 * @author wy
 *
 */
public class ReqWeChatCodPassivePay extends ReqWeChatCodeHead {
	private String orderAmount;
	private String orderTime;
	private String orderNumber;
	private String transType;
	private String merId;
	private String merName;
	private String isCredit;
	private String payType;
	private String timeStart;
	private String timeExpire;
	private String body;
	private String productInfoDetail;
	private String productId;
	private String customerIP;
	private String subWxMerId;
	private String authCode;
	public ReqWeChatCodPassivePay(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.WeChatCodePassivePay);
		this.setOrderNumber(data.getInnerfundtransnbr());
		this.setOrderTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setOrderAmount(formatAmt(data.getTransamt()));
		this.setTransType("01");//01:消费
		this.setIsCredit(StringUtil.isStringEmpty(data.getIsCredit())? "1":"0");
		this.setPayType(ScanCodeMode.ACTIVE);
		this.setTimeStart(data.getTimeStart());
		this.setTimeExpire(data.getTimeExpire());
		this.setBody(data.getProBody());
		this.setProductInfoDetail(data.getGoodsDetail());//被扫用GoodsDetail()
		this.setProductId(data.getProductId());
		this.setCustomerIP(data.getCustomerIp());
		this.setAuthCode(data.getAuthCode());
		List<Map<String, String>> payeeAcctList = data.getPayeeAcctList();
		this.setMerId(payeeAcctList.get(0).get(Dict.PROXY_MER_NBR));
		this.setSubWxMerId(payeeAcctList.get(0).get(Dict.THIRD_MER_NBR));
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

	public String getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public String getCustomerIP() {
		return customerIP;
	}

	public void setCustomerIP(String customerIP) {
		this.customerIP = customerIP;
	}

	public String getSubWxMerId() {
		return subWxMerId;
	}

	public void setSubWxMerId(String subWxMerId) {
		this.subWxMerId = subWxMerId;
	}
	

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
