package com.csii.upp.dto.router.hvps;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstHvps;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.StringUtil;

/**
 * 大额跨行转账
 * @author wy
 *
 */
public class ReqHvpsVirAcctWithdrawl extends ReqHvpsHead{

	private String payerAcctNbr;
	private String payerAcctTypCd;
	private String payerCardPwd;
	private String payerAcctName;
	private String currencyCd;
	private BigDecimal transAmt;
	private String payeeAcctNbr;
	private String payeeAcctName;
	private String payeebanknbr;
	private String msgExt;
	private String hvbepsFlag;
	private String checkPwdFlag;
	private String transerDeptNbr;
	public ReqHvpsVirAcctWithdrawl(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.VirAcctWithdrawl);
		this.setPayerAcctNbr(data.getPayeracctnbr());
		this.setPayerAcctTypCd(data.getPayeraccttypcd());
		this.setPayerCardPwd(data.getPayercardpwd());
		this.setPayerAcctName(data.getPayername());
		this.setCurrencyCd(data.getCurrencycd());
		this.setTransAmt(data.getTransamt());
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setPayeeAcctName(data.getPayeename());
		this.setPayeebanknbr(data.getPayeebanknbr());
		this.setMsgExt(data.getNote());
		this.setTranserDeptNbr(data.getTranserDeptNbr());
		this.setHvbepsFlag(ConstHvps.HVPS_FLAG);
		if(StringUtil.isStringEmpty(data.getPayercardpwd())){
			this.setCheckPwdFlag(ConstHvps.CHECKPWDFLAG_N);
		}else{
			this.setCheckPwdFlag(ConstHvps.CHECKPWDFLAG_Y);
		}
	}
	
	public String getPayerAcctNbr() {
		return payerAcctNbr;
	}


	public void setPayerAcctNbr(String payerAcctNbr) {
		this.payerAcctNbr = payerAcctNbr;
	}


	public String getPayerAcctTypCd() {
		return payerAcctTypCd;
	}


	public void setPayerAcctTypCd(String payerAcctTypCd) {
		this.payerAcctTypCd = payerAcctTypCd;
	}


	public String getPayerCardPwd() {
		return payerCardPwd;
	}


	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}


	public String getCurrencyCd() {
		return currencyCd;
	}


	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
   
	public BigDecimal getTransAmt() {
		return transAmt;
	}


	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}


	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}


	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}


	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

	public String getHvbepsFlag() {
		return hvbepsFlag;
	}

	public void setHvbepsFlag(String hvbepsFlag) {
		this.hvbepsFlag = hvbepsFlag;
	}

	public String getPayerAcctName() {
		return payerAcctName;
	}

	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}

	public String getPayeeAcctName() {
		return payeeAcctName;
	}

	public void setPayeeAcctName(String payeeAcctName) {
		this.payeeAcctName = payeeAcctName;
	}

	public String getCheckPwdFlag() {
		return checkPwdFlag;
	}

	public void setCheckPwdFlag(String checkPwdFlag) {
		this.checkPwdFlag = checkPwdFlag;
	}

	public String getPayeebanknbr() {
		return payeebanknbr;
	}

	public void setPayeebanknbr(String payeebanknbr) {
		this.payeebanknbr = payeebanknbr;
	}

	public String getTranserDeptNbr() {
		return transerDeptNbr;
	}

	public void setTranserDeptNbr(String transerDeptNbr) {
		this.transerDeptNbr = transerDeptNbr;
	}


}
