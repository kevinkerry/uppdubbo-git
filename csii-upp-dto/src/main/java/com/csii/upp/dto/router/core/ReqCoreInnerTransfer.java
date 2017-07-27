/**
 * 
 */
package com.csii.upp.dto.router.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 退货请求类
 */
public class ReqCoreInnerTransfer extends ReqCoreHead {

	public ReqCoreInnerTransfer(InputFundData data) {
		super(data);
		this.setTransTypCd(ConstCore.TRANSTYP_PAY);
		this.setTransCode(CoreTransCode.CoreDeditPayment);
		this.setPayerAcctnbr(data.getPayeracctnbr());
		this.setCurrencyCd(data.getCurrencycd());
		this.setMernbr(data.getMernbr());
		this.setPayeeAcctnbr(data.getPayeeacctnbr());
		this.setTotalAmt(data.getTransamt());
		this.setNote(data.getNote());
		this.setCheckPwdFlag(ConstCore.CHECKPWDFLAG_N);
		this.setPayeeAcctList(data.getPayeeAcctList());
	}

	private String payerAcctnbr; // 付款人账号
	private String currencyCd; // 币种
	private String note; // 备用字段
	private String mernbr; // 商户编号
	private String payeeAcctnbr; // 收款账号
	private String payeeOrgnbr; // 商户归属机构号
	private String transTypCd;// 交易类型
	private String payerCardPwd; // 卡密码
	private BigDecimal totalAmt; // 汇总交易金额
	private List<Map<String, String>> payeeAcctList; // 同一机构子订单汇总
	private String checkPwdFlag;// 验密标志

	public String getCheckPwdFlag() {
		return checkPwdFlag;
	}

	public void setCheckPwdFlag(String checkPwdFlag) {
		this.checkPwdFlag = checkPwdFlag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMernbr() {
		return mernbr;
	}

	public void setMernbr(String mernbr) {
		this.mernbr = mernbr;
	}

	public String getPayerAcctnbr() {
		return payerAcctnbr;
	}

	public void setPayerAcctnbr(String payerAcctnbr) {
		this.payerAcctnbr = payerAcctnbr;
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getPayeeAcctnbr() {
		return payeeAcctnbr;
	}

	public void setPayeeAcctnbr(String payeeAcctnbr) {
		this.payeeAcctnbr = payeeAcctnbr;
	}

	public String getPayeeOrgnbr() {
		return payeeOrgnbr;
	}

	public void setPayeeOrgnbr(String payeeOrgnbr) {
		this.payeeOrgnbr = payeeOrgnbr;
	}

	public String getTransTypCd() {
		return transTypCd;
	}

	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}

	public String getPayerCardPwd() {
		return payerCardPwd;
	}

	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}

	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}
}
