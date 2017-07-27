package com.csii.upp.dto.router.cnaps2;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 普通贷记往帐输出参数
 * @author xujin
 *
 */
public class RespUpbsSimpleCreditRtxn  extends RespCnaps2Head{

	private Date bankdate; //账务主机日期
	private String bankid;//账务主机记账流水号
	private String sendbank;//发起行
	private String entrustdate;//委托日期
	private String msgid;//交易序号
	private String feetype;//手续费类型(返回结果是["1","2"]的字符串)
	private BigDecimal feeamount;//手续费金额(返回结果是["0.00","0.00"]的字符串)

	public Date getBankdate() {
		return bankdate;
	}
	public void setBankdate(Date bankdate) {
		this.bankdate = bankdate;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getSendbank() {
		return sendbank;
	}
	public void setSendbank(String sendbank) {
		this.sendbank = sendbank;
	}
	public String getEntrustdate() {
		return entrustdate;
	}
	public void setEntrustdate(String entrustdate) {
		this.entrustdate = entrustdate;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public BigDecimal getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(BigDecimal feeamount) {
		this.feeamount = feeamount;
	}
}
