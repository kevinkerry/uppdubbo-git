package com.csii.upp.dto.router.cnaps2;

import java.math.BigDecimal;
/**
 * 自动汇路并计算手续费输出参数
 * @author xujin
 *
 */
public class RespUpbsAutoRout extends RespCnaps2Head{
	private String paypathcode;//汇路代码
	private BigDecimal feeamount;//手续费金额
	private String feetype;//手续费类型
	private String accbankno;//付款人开户行
	private String accbankname;//付款人开户行名称
	private String accbrno;//付款人开户机构
	private String priority;//业务优先级(2：特急；1：紧急；0：普通)
	
	public String getPaypathcode() {
		return paypathcode;
	}
	public void setPaypathcode(String paypathcode) {
		this.paypathcode = paypathcode;
	}
	public BigDecimal getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(BigDecimal feeamount) {
		this.feeamount = feeamount;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getAccbankno() {
		return accbankno;
	}
	public void setAccbankno(String accbankno) {
		this.accbankno = accbankno;
	}
	public String getAccbankname() {
		return accbankname;
	}
	public void setAccbankname(String accbankname) {
		this.accbankname = accbankname;
	}
	public String getAccbrno() {
		return accbrno;
	}
	public void setAccbrno(String accbrno) {
		this.accbrno = accbrno;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
}
