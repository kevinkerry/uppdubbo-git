package com.csii.upp.dto.router.ibps;

import java.math.BigDecimal;

import com.csii.upp.constant.IBPSTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 网银互联往账贷记输入参数
 * 
 * @author 姜星
 * 
 */
public class ReqRealTimeCreditRtxn extends ReqIbpsHead {
	private String payeeacct; // 收款人账号
	private String remark; // 附言
	private String ccy; // 交易币种
	private String payeracct; // 付款人账号
	private String payeeaddr; // 收款人地址
	private BigDecimal tranamt; // 交易金额
	private String busitype; // 业务类型
	private String payeracctcode; // 付款人账户类型
	private String rcvbankcode; // 收款人所属网银系统行号
	private String trancategory; // 业务种类
	private String payeename; // 收款人名称
	private String payerbankname; // 付款人开户行名称
	private String payername; // 付款人名称
	private String payeraddr; // 付款人地址
	private String tranclass;
	private String branchflag;
	private String direction;
	private String format;
	private String sourcemodule;

	public ReqRealTimeCreditRtxn(InputFundData data) {
		// 公共报文头
		super(data);
		this.setTransCode(IBPSTransCode.HubeiRealTimeCreditRtxn);
		// 业务报文体
		this.setPayeeacct(data.getPayeeacctnbr()); // 收款人账号
		this.setRemark(data.getNote()); // 附言
		this.setCcy(data.getCurrencycd()); // 交易币种
		this.setPayeracct(data.getPayeracctnbr()); // 付款人账号
		// this.setPayeeaddr(""); //收款人地址
		this.setTranamt(data.getTransamt()); // 交易金额
		this.setBusitype("C200"); // 业务类型：C200-汇兑
		this.setPayeracctcode(data.getPayeraccttypcd()); // 付款人账户类型（行内账户类型取值如100，400）
		this.setRcvbankcode(data.getPayeracctdeptnbr()); // 收款人所属网银系统行号
		this.setTrancategory("02001"); // 业务种类：02001-汇兑
		this.setPayeename(data.getPayeename()); // 收款人名称
		this.setPayername(data.getPayername()); // 付款人名称
		// this.setPayeraddr(""); //付款人地址
		this.setTranclass("RTC");
		this.setBranchflag("C");
		this.setDirection("O");
		this.setFormat("IBPS");
		this.setSourcemodule("DP");
	}

	public String getPayeeacct() {
		return payeeacct;
	}

	public void setPayeeacct(String payeeacct) {
		this.payeeacct = payeeacct;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getPayeracct() {
		return payeracct;
	}

	public void setPayeracct(String payeracct) {
		this.payeracct = payeracct;
	}

	public String getPayeeaddr() {
		return payeeaddr;
	}

	public void setPayeeaddr(String payeeaddr) {
		this.payeeaddr = payeeaddr;
	}

	public BigDecimal getTranamt() {
		return tranamt;
	}

	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getPayeracctcode() {
		return payeracctcode;
	}

	public void setPayeracctcode(String payeracctcode) {
		this.payeracctcode = payeracctcode;
	}

	public String getRcvbankcode() {
		return rcvbankcode;
	}

	public void setRcvbankcode(String rcvbankcode) {
		this.rcvbankcode = rcvbankcode;
	}

	public String getTrancategory() {
		return trancategory;
	}

	public void setTrancategory(String trancategory) {
		this.trancategory = trancategory;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

	public String getPayerbankname() {
		return payerbankname;
	}

	public void setPayerbankname(String payerbankname) {
		this.payerbankname = payerbankname;
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

	public String getTranclass() {
		return tranclass;
	}

	public void setTranclass(String tranclass) {
		this.tranclass = tranclass;
	}

	public String getBranchflag() {
		return branchflag;
	}

	public void setBranchflag(String branchflag) {
		this.branchflag = branchflag;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSourcemodule() {
		return sourcemodule;
	}

	public void setSourcemodule(String sourcemodule) {
		this.sourcemodule = sourcemodule;
	}
}
