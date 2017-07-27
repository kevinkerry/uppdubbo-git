/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import java.util.Date;

/**
 * @author lixinyou
 * 发往fundprocess的支付交易响应类 
 *
 */
public class RespUnifiefPayment extends RespFundProHead {
	private String returnForm;
	private Date hostClearDate;
	private String codeUrl;
	private String qrcodeordernbr;
	private String receiptAmount;
	public String getReturnForm() {
		return returnForm;
	}
	public void setReturnForm(String returnForm) {
		this.returnForm = returnForm;
	}
	public Date getHostClearDate() {
		return hostClearDate;
	}
	public void setHostClearDate(Date hostClearDate) {
		this.hostClearDate = hostClearDate;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	public String getQrcodeordernbr() {
		return qrcodeordernbr;
	}
	public void setQrcodeordernbr(String qrcodeordernbr) {
		this.qrcodeordernbr = qrcodeordernbr;
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	
}
