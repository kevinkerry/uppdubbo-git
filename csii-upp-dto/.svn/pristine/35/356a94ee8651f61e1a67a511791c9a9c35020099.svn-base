package com.csii.upp.dto.router.pbc;

import com.csii.upp.constant.PbcTransCode;
import com.csii.upp.dto.extend.InputFundData;

public class ReqQueryCollectionAgreement extends ReqPbcHead{
	private String productcode;
	private String contractno;
	private String contractacct;
	public ReqQueryCollectionAgreement(InputFundData data) {
		super(data);
		this.setTransCode(PbcTransCode.QueryAgreement);
		this.setProductcode(null);
		this.setContractacct(data.getPayeracctnbr());
		this.setContractno(null);//生成协议号？
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public String getContractacct() {
		return contractacct;
	}
	public void setContractacct(String contractacct) {
		this.contractacct = contractacct;
	}
	
	
}
