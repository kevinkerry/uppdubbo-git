/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;


/**
 * @author lixinyou
 * 卡信息查询响应类
 */
public class RespQueryCardInfo extends RespFundProHead {
	private String acctLossStatus;
	private String acctStatusWord;
	private String acctFreezeFlag;
	private String acctNature;
	private String cardStatus;
	private String cardStatusWord;
	public String getAcctLossStatus() {
		return acctLossStatus;
	}
	public void setAcctLossStatus(String acctLossStatus) {
		this.acctLossStatus = acctLossStatus;
	}
	public String getAcctStatusWord() {
		return acctStatusWord;
	}
	public void setAcctStatusWord(String acctStatusWord) {
		this.acctStatusWord = acctStatusWord;
	}
	public String getAcctFreezeFlag() {
		return acctFreezeFlag;
	}
	public void setAcctFreezeFlag(String acctFreezeFlag) {
		this.acctFreezeFlag = acctFreezeFlag;
	}
	public String getAcctNature() {
		return acctNature;
	}
	public void setAcctNature(String acctNature) {
		this.acctNature = acctNature;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardStatusWord() {
		return cardStatusWord;
	}
	public void setCardStatusWord(String cardStatusWord) {
		this.cardStatusWord = cardStatusWord;
	}
}
