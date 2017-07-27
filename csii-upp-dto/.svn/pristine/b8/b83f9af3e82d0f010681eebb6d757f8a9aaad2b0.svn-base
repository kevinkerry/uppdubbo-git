package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 
 * 创建日期：2017年1月8日
 * Description:支付宝撤消请求类
 * @author liru
 * @mender: (文件的修改者，文件创建者之外的人)
 * @version: 1.0
 * @Remark: (认为有必要的其它信息)
 */
public class ReqUndoToAli extends ReqAlipayCodePreHead {
	private String orderNumber;//订单号 
	private String orderTime;//订单原交易时间 
	private String merId;//商户号
	private String transType;//交易类型
	private String orderAmount;//原订单金额 
	private String initTxnSeqId;//原二维码前置消费流水号
	private String initTxnTime;//原二维码前置交易时间
	public ReqUndoToAli(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.AlipayCodeActivePayUndo);
		this.setOrderNumber(data.getInnerfundtransnbr());		
		this.setOrderTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));		
		this.setOrderAmount(formatAmt(data.getTransamt()));
		this.setTransType("31");//31:消费撤销	
	    this.setInitTxnSeqId(data.getOriginnertransnbr());//原二维码前置消费流水号
	    this.setInitTxnTime(DateUtil.Date_To_DateTimeFormat(data.getOrigtranstime(), DateFormatCode.DATETIME_FORMATTER3));//原二维码前置交易时间
		this.setMerId(data.getMerId());//商户号	
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
	
	public String getInitTxnSeqId() {
		return initTxnSeqId;
	}
	public void setInitTxnSeqId(String initTxnSeqId) {
		this.initTxnSeqId = initTxnSeqId;
	}
	public String getInitTxnTime() {
		return initTxnTime;
	}
	public void setInitTxnTime(String initTxnTime) {
		this.initTxnTime = initTxnTime;
	}

}
