package com.csii.upp.dto.router.cnaps2;

import java.math.BigDecimal;

import com.csii.upp.constant.Cnaps2TransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 自动汇路并计算手续费输入参数
 * 
 * @author xujin
 * 
 */
public class ReqUpbsAutoRout extends ReqCnaps2Head {
	public ReqUpbsAutoRout(InputFundData data) {
		super(data);
		setTransCode(Cnaps2TransCode.UpbsAutoRout);
		setAmount(data.getTransamt());
		setCurrency(data.getCurrencycd());
		setPayeeaccbank(data.getPayeeacctdeptnbr());
		setPayeeaccbankname("");
		setPayeracc(data.getPayeracctnbr());
		this.setPayeraccttypecd(data.getPayeraccttypcd());
	}

	private String busitype = "A100";// 平台业务类型(统一填写：A100-普通汇兑)
	private String busikind = "02103";// 平台业务种类(统一填写：02103-网银支付)
	private BigDecimal amount;// 交易金额
	private String currency;// 币种
	private String priority;// 加急标识(0-普通 1-加急)
	private String payeeaccbank;// 收款行行号
	private String payeeaccbankname;// 收款行行名
	private String pathpriority = "2";// 汇路优先级(1-速度优先 2-费用优先)
	private String acclevel;// 客户级别(1-白金客户2-黄金客户3-白银客户4-普通客户)
	private String payeracc;// 付款人账号
	private String payeraccttypecd;// 付款人账号类型

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getBusikind() {
		return busikind;
	}

	public void setBusikind(String busikind) {
		this.busikind = busikind;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPayeeaccbank() {
		return payeeaccbank;
	}

	public void setPayeeaccbank(String payeeaccbank) {
		this.payeeaccbank = payeeaccbank;
	}

	public String getPayeeaccbankname() {
		return payeeaccbankname;
	}

	public void setPayeeaccbankname(String payeeaccbankname) {
		this.payeeaccbankname = payeeaccbankname;
	}

	public String getPathpriority() {
		return pathpriority;
	}

	public void setPathpriority(String pathpriority) {
		this.pathpriority = pathpriority;
	}

	public String getAcclevel() {
		return acclevel;
	}

	public void setAcclevel(String acclevel) {
		this.acclevel = acclevel;
	}

	public String getPayeracc() {
		return payeracc;
	}

	public void setPayeracc(String payeracc) {
		this.payeracc = payeracc;
	}

	public String getPayeraccttypecd() {
		return payeraccttypecd;
	}

	public void setPayeraccttypecd(String payeraccttypecd) {
		this.payeraccttypecd = payeraccttypecd;
	}

}
