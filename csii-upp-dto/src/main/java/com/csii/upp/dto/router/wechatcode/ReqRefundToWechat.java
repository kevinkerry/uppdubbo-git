package com.csii.upp.dto.router.wechatcode;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 
 * 创建日期：2017年1月8日
 * Description:微信退款请求类
 * @author liru
 * @mender: (文件的修改者，文件创建者之外的人)
 * @version: 1.0
 * @Remark: (认为有必要的其它信息)
 */
public class ReqRefundToWechat extends ReqWeChatCodeHead {
	private String orderNumber;//订单号
	private String orderTime;//订单原交易时间 
	private String merId;//商户号
	private String transType;//交易类型
	private String orderAmount;//原订单金额
	private String initOrderNumber;//原二维码前置消费流水号
	private String initOrderTime;//原二维码前置交易时间
	private String refundAmount;//退款金额
	private String outRequestNo;//商户退款单号,同一退款单号多次请求只能退一笔	
	public ReqRefundToWechat(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.WeChatCodeActivePayRefund);
		this.setOrderNumber(data.getInnerfundtransnbr());		
		this.setOrderTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));		
		this.setOrderAmount(formatAmt(data.getTransamt()));
		this.setTransType("34");//34:退货	
		this.setRefundAmount(formatAmt(data.getTransamt()));
		this.setInitOrderNumber(data.getOriginnertransnbr());
		this.setInitOrderTime(DateUtil.Date_To_DateTimeFormat(data.getOrigtranstime(), DateFormatCode.DATETIME_FORMATTER3));
	    this.setOutRequestNo(data.getOveralltransnbr());//退款唯一标识	OverallTransNbr	
		this.setMerId(data.getMerId());	
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
	
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getOutRequestNo() {
		return outRequestNo;
	}
	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}
	public String getInitOrderNumber() {
		return initOrderNumber;
	}
	public void setInitOrderNumber(String initOrderNumber) {
		this.initOrderNumber = initOrderNumber;
	}
	public String getInitOrderTime() {
		return initOrderTime;
	}
	public void setInitOrderTime(String initOrderTime) {
		this.initOrderTime = initOrderTime;
	}
	
}
