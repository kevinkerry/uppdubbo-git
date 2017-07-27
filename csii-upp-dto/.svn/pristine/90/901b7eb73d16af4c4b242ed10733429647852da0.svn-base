package com.csii.upp.dto.router.fundprocess;

import java.util.List;
import java.util.Map;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author zhubenle
 * 发往fundprocess的批量转账交易请求类 
 *
 */
public class ReqBatchTransfer extends ReqFundProHead {

	private List<Map<String, String>> payeeAcctList;
	private String transerDeptNbr;

	public ReqBatchTransfer(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.BatchTransfer);
		this.setPayeeAcctList(data.getPayeeAcctList());
		this.setTranserDeptNbr(data.getTranserDeptNbr());
		
	}

	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}

	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}
	
	public String getTranserDeptNbr() {
		return transerDeptNbr;
	}

	public void setTranserDeptNbr(String transerDeptNbr) {
		this.transerDeptNbr = transerDeptNbr;
	}
	
}
