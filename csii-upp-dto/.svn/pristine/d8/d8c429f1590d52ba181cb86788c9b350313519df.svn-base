package com.csii.upp.dto.router.pbc;

import java.math.BigDecimal;

import com.csii.upp.constant.PbcTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqPbcCollectionRtxn extends ReqPbcHead {

	public ReqPbcCollectionRtxn(InputFundData data) {
		super(data);
		this.setBusitype("2");
		this.setCorporateacct(corporateacct);
		this.setCorporatebankcode(corporatebankcode);//待赋值
		this.setPayeracct(data.getPayeracctnbr());
		this.setPayerbankcode(data.getPayeracctdeptnbr());
		//this.setPayerbankname(inputData.get);//待赋值
		this.setPayercontractno(payercontractno);//待赋值
		this.setCcy(data.getCurrencycd());
		this.setTranamt(data.getTransamt());
		this.setPostscript(data.getNote());
		this.setRemark(data.getNote());
		this.setTransCode(PbcTransCode.PbcCollectionRtxn);
	}
	
	private String busitype;
	private String corporateacct;
	private String corporatebankcode;
	private String payercontractno;
	private String payeracct;
	private String payername;
	private String payerbankcode;
	private String payerbankname;
	private String ccy;
	private BigDecimal tranamt;
	private String postscript;
	private String remark;

	public String getCcy() {
		return ccy;
	}
	public String getBusitype() {
		return busitype;
	}
	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getCorporateacct() {
		return corporateacct;
	}
	public void setCorporateacct(String corporateacct) {
		this.corporateacct = corporateacct;
	}
	public String getCorporatebankcode() {
		return corporatebankcode;
	}
	public void setCorporatebankcode(String corporatebankcode) {
		this.corporatebankcode = corporatebankcode;
	}
	public String getPayercontractno() {
		return payercontractno;
	}
	public void setPayercontractno(String payercontractno) {
		this.payercontractno = payercontractno;
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
	public String getPayerbankcode() {
		return payerbankcode;
	}
	public void setPayerbankcode(String payerbankcode) {
		this.payerbankcode = payerbankcode;
	}
	public String getPayerbankname() {
		return payerbankname;
	}
	public void setPayerbankname(String payerbankname) {
		this.payerbankname = payerbankname;
	}
	public BigDecimal getTranamt() {
		return tranamt;
	}
	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
