/**
 * 
 */
package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.BizType;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.YN;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * @author lixinyou
 * 通知payment支付结果请求类
 *
 */
public class ReqNotifyPayResult extends ReqPaymHead{
	private String upperTransNbr;
	private String upperSubTransNbr;
	private String comfirmPayYN;
	private String transAmt;
	private String merTransDateTime;
	private String transStatus;
	private String payerAcctNbr;
	private String payerCardTypCd;
	private String payTypCd;
	private String hostClearDate;
	private String overallTransNbr;
	private String receiptAmount;
	public ReqNotifyPayResult(InputFundData data) {
		super(data);
		this.setTransCode(PaymTransCode.AsyncNotification);
		if(OveralltransTyp.CVAP.equals(data.getTransid())){
			this.setUpperTransNbr(data.getOriguppertransnbr());
			this.setUpperSubTransNbr(data.getOrigUpperSubTransNbr());
			this.setComfirmPayYN(YN.Y);
			this.setMerTransDateTime(DateUtil.Date_To_DateTimeFormat(data.getUppertranstime(),DateFormatCode.DATETIME_FORMATTER3));
		}else{
			this.setUpperTransNbr(data.getUppertransnbr());
			this.setComfirmPayYN(YN.N);
		}
		this.setTransAmt(StringUtil.parseObjectToString(data.getTransamt()));
		this.setTransStatus(data.getTransstatus());
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerCardTypCd(data.getPayercardtypcd());
		this.setHostClearDate(DateUtil.Date_To_DateTimeFormat(data.getCleardate(),DateFormatCode.DATE_FORMATTER3));
		this.setOverallTransNbr(data.getOveralltransnbr());
		if(BizType.BIZTYPE_000201.equals(data.getBizType()) || BizType.BIZTYPE_000202.equals(data.getBizType())){
			this.setPayTypCd(PayTypCd.OTHCYBER);
		}else if (BizType.BIZTYPE_000301.equals(data.getBizType())) {
			this.setPayTypCd(PayTypCd.INTEL);
		}
		if(!StringUtil.isStringEmpty(data.getReceiptAmount())){
			this.setReceiptAmount(data.getReceiptAmount());
		}
	}

	public String getHostClearDate() {
		return hostClearDate;
	}

	public void setHostClearDate(String hostClearDate) {
		this.hostClearDate = hostClearDate;
	}

	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

	public String getUpperTransNbr() {
		return upperTransNbr;
	}

	public void setUpperTransNbr(String upperTransNbr) {
		this.upperTransNbr = upperTransNbr;
	}

	public String getComfirmPayYN() {
		return comfirmPayYN;
	}

	public void setComfirmPayYN(String comfirmPayYN) {
		this.comfirmPayYN = comfirmPayYN;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getMerTransDateTime() {
		return merTransDateTime;
	}

	public void setMerTransDateTime(String merTransDateTime) {
		this.merTransDateTime = merTransDateTime;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getUpperSubTransNbr() {
		return upperSubTransNbr;
	}

	public void setUpperSubTransNbr(String upperSubTransNbr) {
		this.upperSubTransNbr = upperSubTransNbr;
	}

	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}

	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}

	public String getPayerCardTypCd() {
		return payerCardTypCd;
	}

	public void setPayerCardTypCd(String payerCardTypCd) {
		this.payerCardTypCd = payerCardTypCd;
	}

	public String getOverallTransNbr() {
		return overallTransNbr;
	}

	public void setOverallTransNbr(String overallTransNbr) {
		this.overallTransNbr = overallTransNbr;
	}

	public String getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	
}
