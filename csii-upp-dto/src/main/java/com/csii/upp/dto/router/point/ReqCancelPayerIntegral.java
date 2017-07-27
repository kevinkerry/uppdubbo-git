package com.csii.upp.dto.router.point;

import java.math.BigDecimal;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.dto.extend.InputFundData;

public class ReqCancelPayerIntegral extends ReqPointHead {

	private BigDecimal amtThisTime;//本次积分抵扣金额
	private String branchNo;//行社号
	private String clientNo;//客户内码
	private String billNo;//订单号
	public ReqCancelPayerIntegral(InputFundData data) {
		super(data);
		data.setPayeracctnbr(data.getBranchNo());
		data.setPayeeacctnbr(data.getClientNo());
		data.setTransamt(data.getDeductAmt().setScale(4));
		data.setCheckdataflag(FundChannelCode.JFWG);
		data.setPointdataflag(InteralFlag.YES);
		data.setInnerfundtransnbr(data.getTransnbr());
		this.setTransCode("cancelConsume");
		this.setBillNo(data.getOriginnertransnbr());
		this.setAmtThisTime(data.getDeductAmt().setScale(1));
		this.setBranchNo(data.getBranchNo());
		this.setClientNo(data.getClientNo());
		String url = "/cancelConsume?requestId=" +this.getRequestId()
				+ "&protocolVersion=" + this.getProtocolVersion() 
				+ "&billNo="+this.getBillNo()
				+"&branchNo="+this.getBranchNo();
		this.setSuffixUrl(url);
	}

	

	public BigDecimal getAmtThisTime() {
		return amtThisTime;
	}



	public void setAmtThisTime(BigDecimal amtThisTime) {
		this.amtThisTime = amtThisTime;
	}



	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	
}
