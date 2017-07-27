package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;
/**
 * 退货交易
 * @author xujin   
 *
 */
public class ReqReturnTrans extends ReqPaymHead{

	public ReqReturnTrans(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.ReturnTrans);
		this.setOrigMerSeqNbr(data.getOrigmerseqnbr());
		this.setOrigMerDate(DateUtil.Date_To_DateTimeFormat(data.getOrigmerdate(), DateFormatCode.DATE_FORMATTER3));
		this.setOrigTransAmt(data.getOrigTransAmt());
		this.setOrigSubMerDate(data.getOrigSubMerDate());
		this.setOrigSubMerSeqNo(data.getOrigSubMerSeqNo());
		this.setSubMerchantId(data.getSubMerchantId());
		this.setSubMerSeqNo(data.getSubMerSeqNo());
		this.setSubMerDateTime(data.getSubMerDateTime());
		this.setSubTransAmt(StringUtil.parseObjectToString(data.getSubTransAmt()));
		this.setRefundReason(data.getRefundReason());
		this.setOperatorId(data.getOperatorId());
		this.setStoreId(data.getStoreId());
		this.setTermId(data.getTermId());
	}
	
	private String origMerSeqNbr;
	
	private String origMerDate;
	
	private String origTransAmt;
	
	private String origSubMerSeqNo;
	
	private String origSubMerDate;
	
	private String subMerchantId;
	
	private String subMerSeqNo;
	
	private String subMerDateTime;
	
	private String subTransAmt;
	
	//扫码支付相关信息
	private String refundReason;//退款原因 
	
	private String operatorId;//操作员编号
	
	private String storeId;//门店编号 
	
	private String termId;//终端编号

	public String getOrigMerSeqNbr() {
		return origMerSeqNbr;
	}

	public void setOrigMerSeqNbr(String origMerSeqNbr) {
		this.origMerSeqNbr = origMerSeqNbr;
	}

	public String getOrigMerDate() {
		return origMerDate;
	}

	public void setOrigMerDate(String origMerDate) {
		this.origMerDate = origMerDate;
	}

	public String getOrigTransAmt() {
		return origTransAmt;
	}

	public void setOrigTransAmt(String origTransAmt) {
		this.origTransAmt = origTransAmt;
	}

	public String getOrigSubMerSeqNo() {
		return origSubMerSeqNo;
	}

	public void setOrigSubMerSeqNo(String origSubMerSeqNo) {
		this.origSubMerSeqNo = origSubMerSeqNo;
	}

	public String getOrigSubMerDate() {
		return origSubMerDate;
	}

	public void setOrigSubMerDate(String origSubMerDate) {
		this.origSubMerDate = origSubMerDate;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

	public String getSubMerSeqNo() {
		return subMerSeqNo;
	}

	public void setSubMerSeqNo(String subMerSeqNo) {
		this.subMerSeqNo = subMerSeqNo;
	}

	public String getSubMerDateTime() {
		return subMerDateTime;
	}

	public void setSubMerDateTime(String subMerDateTime) {
		this.subMerDateTime = subMerDateTime;
	}

	public String getSubTransAmt() {
		return subTransAmt;
	}

	public void setSubTransAmt(String subTransAmt) {
		this.subTransAmt = subTransAmt;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
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
	
}
