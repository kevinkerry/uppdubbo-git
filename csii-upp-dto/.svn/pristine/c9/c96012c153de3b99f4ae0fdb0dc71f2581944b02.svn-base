package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
/**
 * 支付宝交易状态查询请求类
 * @author wy
 *
 */
public class ReqAlipayCodePayStatus extends ReqAlipayCodePreHead {
	private String orderTime;
	private String orderNumber;
	private String transType;
	private String merId;
	
	public ReqAlipayCodePayStatus(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.QueryCodePayStatus);
		this.setOrderNumber(data.getInnerfundtransnbr());
		this.setOrderTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setTransType("35");
		this.setMerId(data.getMerId());
	
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
}
