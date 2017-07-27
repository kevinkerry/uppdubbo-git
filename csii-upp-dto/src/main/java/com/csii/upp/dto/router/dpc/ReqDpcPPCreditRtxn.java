/**
 * 
 */
package com.csii.upp.dto.router.dpc;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstDpc;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author DreamsHunter
 *
 */
public class ReqDpcPPCreditRtxn extends ReqDpcHead{
 
	public ReqDpcPPCreditRtxn(InputFundData data) {
		
		super(data);
		this.setTransCode("DpcPPCreditRtxn");
		this.setGltype(ConstDpc.GLTYPE);  //总帐类型Y  固定：R-账号
		this.setTrantype(ConstDpc.TRANSTYPE);  //交易类型Y 对私 CE04
		this.setSvcclass(ConstDpc.SVCCLASS);  //业务类型 现在只使用 2
		this.setNotetype(ConstDpc.NOTETYPE);  //票据种类Y 对私使用75
		this.setNoteno("");  //凭证号N  网银渠道不上送
		this.setBaseacctno("");  //账户号码Y  账户必输，卡不输
		this.setCurcode("0");  //钞汇类型Y  0-丙钞
		this.setAccttype(data.getPayeraccttypcd());  //账户类型Y  
		//TODO 卡折标志待确认
		this.setCardpbind("P");  //卡折标志Y  
		this.setCcysign(data.getCurrencycd());  //交易币种Y
		this.setTranamt(data.getTransamt());  //交易金额Y
		this.setSendbankcode(data.getPayerbanknbr());  //发起行行号N
		this.setPayerbankcode(data.getPayeracctdeptnbr());  //付款人开户行行号Y
		this.setPayeracct(data.getPayeracctnbr());  //付款人账号Y
		this.setPayername(data.getPayername());  //付款人名称Y
		this.setRcvbankcode(data.getPayeebanknbr());  //接收行行号Y
		this.setPayeebankcode(data.getPayeeacctdeptnbr());  //收款人开户行号Y
		this.setPayeeacct(data.getPayeeacctnbr());  //收款人账号Y
		this.setPayeename(data.getPayeename());  //收款人名称Y
		this.setRemark(data.getNote());  //附言N
	}
	 
	private String gltype;  //总帐类型  固定：R-账号
	private String trantype;  //交易类型  对私 CE04
	private String svcclass;  //业务类型 现在只使用 2
	private String notetype;  //票据种类 对私使用75
	private String noteno;  //凭证号   非必填 
	private String baseacctno;  //账户号码  必输
	private String curcode;  //钞汇类型  0-丙钞
	private String accttype;  //账户类型
	private String cardpbind;  //卡折标志
	private String ccysign;  //交易币种
	private BigDecimal tranamt;  //交易金额
	private String consigndate;  //生效日期/委托日期
	private String issuedate;  //签发日期
	private String sendsettlebank;  //发起清算行行号
	private String sendbankcode;  //发起行行号
	private String payerbankcode;  //付款人开户行行号
	private String payeracct;  //付款人账号
	private String payername;  //付款人名称
	private String payeraddr;  //付款人地址
	private String rcvbankcode;  //接收行行号
	private String payeebankcode;  //收款人开户行号
	private String payeeacct;  //收款人账号
	private String payeename;  //收款人名称
	private String payeeaddr;  //收款人地址
	private String remark;  //附言
//	private String servdetail;  //服务费信息数组
//	private String servtype;  //服务费类型
//	private String servccy;  //收费币种
//	private String servtrantype;  //服务费交易类型
//	private String servamt;  //服务费金额
//	private String servboind;  //日终标志
//	private String servcons;  //服务费合并标志
//	private String servcrdrflag;  //服务费借贷标志
//	private String taxincluded;  //税务包含标志
//	private String chargetype;  //收取方式
	public String getGltype() {
		return gltype;
	}
	public void setGltype(String gltype) {
		this.gltype = gltype;
	}
	public String getTrantype() {
		return trantype;
	}
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}
	public String getNotetype() {
		return notetype;
	}
	public void setNotetype(String notetype) {
		this.notetype = notetype;
	}
	public String getSvcclass() {
		return svcclass;
	}
	public void setSvcclass(String svcclass) {
		this.svcclass = svcclass;
	}
	public String getNoteno() {
		return noteno;
	}
	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}
	public String getBaseacctno() {
		return baseacctno;
	}
	public void setBaseacctno(String baseacctno) {
		this.baseacctno = baseacctno;
	}
	public String getCurcode() {
		return curcode;
	}
	public void setCurcode(String curcode) {
		this.curcode = curcode;
	}
	public String getAccttype() {
		return accttype;
	}
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public String getCardpbind() {
		return cardpbind;
	}
	public void setCardpbind(String cardpbind) {
		this.cardpbind = cardpbind;
	}
	public String getCcysign() {
		return ccysign;
	}
	public void setCcysign(String ccysign) {
		this.ccysign = ccysign;
	}
	public String getSendsettlebank() {
		return sendsettlebank;
	}
	public void setSendsettlebank(String sendsettlebank) {
		this.sendsettlebank = sendsettlebank;
	}
	public BigDecimal getTranamt() {
		return tranamt;
	}
	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}
	public String getSendbankcode() {
		return sendbankcode;
	}
	public void setSendbankcode(String sendbankcode) {
		this.sendbankcode = sendbankcode;
	}
	public String getPayerbankcode() {
		return payerbankcode;
	}
	public void setPayerbankcode(String payerbankcode) {
		this.payerbankcode = payerbankcode;
	}
	public String getConsigndate() {
		return consigndate;
	}
	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}
	public String getPayeracct() {
		return payeracct;
	}
	public void setPayeracct(String payeracct) {
		this.payeracct = payeracct;
	}
	public String getPayername() {
		return payername;
	}
	public void setPayername(String payername) {
		this.payername = payername;
	}
	public String getPayeraddr() {
		return payeraddr;
	}
	public void setPayeraddr(String payeraddr) {
		this.payeraddr = payeraddr;
	}
	public String getRcvbankcode() {
		return rcvbankcode;
	}
	public void setRcvbankcode(String rcvbankcode) {
		this.rcvbankcode = rcvbankcode;
	}
	public String getPayeebankcode() {
		return payeebankcode;
	}
	public void setPayeebankcode(String payeebankcode) {
		this.payeebankcode = payeebankcode;
	}
	public String getPayeeacct() {
		return payeeacct;
	}
	public void setPayeeacct(String payeeacct) {
		this.payeeacct = payeeacct;
	}
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
	public String getPayeeaddr() {
		return payeeaddr;
	}
	public void setPayeeaddr(String payeeaddr) {
		this.payeeaddr = payeeaddr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public String getServdetail() {
//		return servdetail;
//	}
//	public void setServdetail(String servdetail) {
//		this.servdetail = servdetail;
//	}
//	public String getServtype() {
//		return servtype;
//	}
//	public void setServtype(String servtype) {
//		this.servtype = servtype;
//	}
//	public String getServccy() {
//		return servccy;
//	}
//	public void setServccy(String servccy) {
//		this.servccy = servccy;
//	}
//	public String getServtrantype() {
//		return servtrantype;
//	}
//	public void setServtrantype(String servtrantype) {
//		this.servtrantype = servtrantype;
//	}
//	public String getServamt() {
//		return servamt;
//	}
//	public void setServamt(String servamt) {
//		this.servamt = servamt;
//	}
//	public String getServboind() {
//		return servboind;
//	}
//	public void setServboind(String servboind) {
//		this.servboind = servboind;
//	}
//	public String getServcons() {
//		return servcons;
//	}
//	public void setServcons(String servcons) {
//		this.servcons = servcons;
//	}
//	public String getTaxincluded() {
//		return taxincluded;
//	}
//	public void setTaxincluded(String taxincluded) {
//		this.taxincluded = taxincluded;
//	}
//	public String getServcrdrflag() {
//		return servcrdrflag;
//	}
//	public void setServcrdrflag(String servcrdrflag) {
//		this.servcrdrflag = servcrdrflag;
//	}
//	public String getChargetype() {
//		return chargetype;
//	}
//	public void setChargetype(String chargetype) {
//		this.chargetype = chargetype;
//	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
}
