/**
 * 
 */
package com.csii.upp.dto.router.hvps;

import java.math.BigDecimal;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.HvpsTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author DreamsHunter
 * 
 */
public class ReqHvpsCreditRtxn extends ReqHvpsHead {

	private String payerhbrinst; // 付款人开户机构
	private String payeracct; // 付款人账号
	private String payername; // 付款人名称
	private String payerpaperno; // 付款人证件号码
	private String payerpapertype; // 付款人证件类型
	private String ccy; // 币种
	private BigDecimal tranamt; // 交易金额
	private String payeraddr; // 付款人地址
	private String payeeacct; // 收款人账号
	private String payeename; // 收款人名称
	private String payeebankcode; // 收款人开户行行号
	private String payeercvbank; // 收款行行号
	private String payeeaddr; // 收款人地址
	private String postscript; // 附言
	private String direction; // 往来标志
	private String drawtype; // 支取方式
	private String trancashflag; // 现转标识
	private String fastflag; // 加急标识
	private String individual; // 对公对私标志
	private String chequetype; // 凭证种类
	private String tranclass; // 交易类别
	private String payeracctcode; // 付款人账户类型
	private String agentflag; // 是否代理
	private String busitype; // 业务类型编码
	private String mediano; // 介质号码
	private String busicode; // 业务类型
	private String branchflag; // 机构类别
	private String priority; // 优先级
	private String format; // 渠道类型
	private String sourcemodule; // 源模块
	private String trancategory; // 业务种类
	private String options; // 操作类型

	public ReqHvpsCreditRtxn(InputFundData data) {
		super(data);
		this.setPayerhbrinst(""); // 付款人开户机构直销银行挂载的实体机构号
		this.setPayeracct(data.getPayeracctnbr()); // 付款人账号Y
		this.setPayername(data.getPayername()); // 付款人名称Y
		// this.setPayerpaperno(inputData.getPayerpaperno); //付款人证件号码N
		// this.setPayerpapertype(inputData.getPayerpapertype); //付款人证件类型N
		this.setCcy(data.getCurrencycd()); // 币种定值CNYY
		this.setTranamt(data.getTransamt()); // 交易金额Y
		// this.setPayeraddr(inputData.getPayeraddr); //付款人地址N
		this.setPayeeacct(data.getPayeeacctnbr()); // 收款人账号Y
		this.setPayeename(data.getPayeename()); // 收款人名称Y
		this.setPayeebankcode(data.getPayeeacctdeptnbr()); // 收款人开户行行号Y
		this.setPayeercvbank(data.getPayeeacctdeptnbr()); // 收款行行号Y
		// this.setPayeeaddr(inputData.getPayeeaddr); //收款人地址N
		this.setPostscript(data.getNote()); // 附言
		this.setDirection("O"); // 往来标志定值 OY
		this.setDrawtype("2"); // 支取方式定值 2Y
		this.setTrancashflag("1"); // 现转标识定值 1Y
		this.setFastflag("0"); // 加急标识定值 0Y
		// this.setIndividual(inputData.getIndividual); //对公对私标志N
		this.setChequetype("000"); // 凭证种类定值 000Y
		this.setTranclass("CTC"); // 交易类别定值 CTCY
		this.setPayeracctcode("P"); // 付款人账户类型定值PY
		this.setAgentflag("0"); // 是否代理 定值 0Y
		this.setBusitype("A100"); // 业务类型编码定值 A100 -汇兑业务Y
		this.setMediano(data.getPayeracctnbr()); // 介质号码等于PAYERACCTY
		this.setBusicode("0001"); // 业务类型定值0001Y
		this.setBranchflag("C"); // 机构类别定值CY
		this.setFormat(FundChannelCode.HVPS); // 渠道类型 HVPS Y
		this.setTrancategory("02102"); // 业务种类HVPS—大额Y
		this.setOptions("01"); // 操作类型直销银行挂载的实体机构号Y
		this.setTransCode(HvpsTransCode.Credit);
	}

	public String getPayerhbrinst() {
		return payerhbrinst;
	}

	public void setPayerhbrinst(String payerhbrinst) {
		this.payerhbrinst = payerhbrinst;
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

	public String getPayerpaperno() {
		return payerpaperno;
	}

	public void setPayerpaperno(String payerpaperno) {
		this.payerpaperno = payerpaperno;
	}

	public String getPayerpapertype() {
		return payerpapertype;
	}

	public void setPayerpapertype(String payerpapertype) {
		this.payerpapertype = payerpapertype;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getTranamt() {
		return tranamt;
	}

	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}

	public String getPayeraddr() {
		return payeraddr;
	}

	public void setPayeraddr(String payeraddr) {
		this.payeraddr = payeraddr;
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

	public String getPayeebankcode() {
		return payeebankcode;
	}

	public void setPayeebankcode(String payeebankcode) {
		this.payeebankcode = payeebankcode;
	}

	public String getPayeercvbank() {
		return payeercvbank;
	}

	public void setPayeercvbank(String payeercvbank) {
		this.payeercvbank = payeercvbank;
	}

	public String getPayeeaddr() {
		return payeeaddr;
	}

	public void setPayeeaddr(String payeeaddr) {
		this.payeeaddr = payeeaddr;
	}

	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDrawtype() {
		return drawtype;
	}

	public void setDrawtype(String drawtype) {
		this.drawtype = drawtype;
	}

	public String getTrancashflag() {
		return trancashflag;
	}

	public void setTrancashflag(String trancashflag) {
		this.trancashflag = trancashflag;
	}

	public String getFastflag() {
		return fastflag;
	}

	public void setFastflag(String fastflag) {
		this.fastflag = fastflag;
	}

	public String getIndividual() {
		return individual;
	}

	public void setIndividual(String individual) {
		this.individual = individual;
	}

	public String getChequetype() {
		return chequetype;
	}

	public void setChequetype(String chequetype) {
		this.chequetype = chequetype;
	}

	public String getTranclass() {
		return tranclass;
	}

	public void setTranclass(String tranclass) {
		this.tranclass = tranclass;
	}

	public String getPayeracctcode() {
		return payeracctcode;
	}

	public void setPayeracctcode(String payeracctcode) {
		this.payeracctcode = payeracctcode;
	}

	public String getAgentflag() {
		return agentflag;
	}

	public void setAgentflag(String agentflag) {
		this.agentflag = agentflag;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getMediano() {
		return mediano;
	}

	public void setMediano(String mediano) {
		this.mediano = mediano;
	}

	public String getBusicode() {
		return busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	public String getBranchflag() {
		return branchflag;
	}

	public void setBranchflag(String branchflag) {
		this.branchflag = branchflag;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getTrancategory() {
		return trancategory;
	}

	public void setTrancategory(String trancategory) {
		this.trancategory = trancategory;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}
