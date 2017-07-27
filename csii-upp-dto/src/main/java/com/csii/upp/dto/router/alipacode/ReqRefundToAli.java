package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
/**
 * 
 * 创建日期：2017年1月8日
 * Description:支付宝退款请求类
 * @author liru
 * @mender: (文件的修改者，文件创建者之外的人)
 * @version: 1.0
 * @Remark: (认为有必要的其它信息)
 */
public class ReqRefundToAli extends ReqAlipayCodePreHead {
	private String orderNumber;//订单号
	private String orderTime;//订单原交易时间
	private String merId;//商户号
	private String transType;//交易类型
	private String orderAmount;//原订单金额
	private String initTxnSeqId;//原二维码前置消费流水号
	private String initTxnTime;//原二维码前置交易时间
	private String refundAmount;//退款金额
	private String refundReason;//退款原因
	private String outRequestNo;//标识一次退款请求,同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	private String operatorId;//操作员编号
	private String storeId;//门店编号
	private String termId;//终端编号
	public ReqRefundToAli(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.AlipayCodeActivePayRefund);
		this.setOrderNumber(data.getInnerfundtransnbr());		
		this.setOrderTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));		
		this.setOrderAmount(formatAmt(data.getTransamt()));
		this.setTransType("34");//34:退货		
		this.setOperatorId(data.getOperatorId());
		this.setStoreId(data.getStoreId());
		this.setTermId(data.getTermId());
		this.setInitTxnSeqId(data.getOriginnertransnbr());//原二维码前置消费流水号
		this.setInitTxnTime(DateUtil.Date_To_DateTimeFormat(data.getOrigtranstime(), DateFormatCode.DATETIME_FORMATTER3));//原二维码前置交易时间
	    this.setRefundAmount(formatAmt(data.getTransamt()));//退款金额
	    this.setRefundReason(data.getRefundReason());//退款原因
	    this.setOutRequestNo(data.getOveralltransnbr());//退款唯一标识
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
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getOutRequestNo() {
		return outRequestNo;
	}
	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}


	
}
