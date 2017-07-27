package com.csii.upp.dto.router.point;

import java.math.BigDecimal;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

public class ReqConsumePayerIntegral extends ReqPointHead {

	private BigDecimal amtThisTime;//本次积分抵扣金额
	private String branchNo;//行社号
	private String clientNo;//客户内码
	private String billNo;//订单号
	private String retry;//消费重复标识
	public ReqConsumePayerIntegral(InputFundData data) {
		super(data);
		data.setPayeracctnbr(data.getClientNo());
		data.setPayeeacctnbr(data.getBranchNo());
		data.setTransamt(data.getDeductAmt().setScale(4));
		data.setCheckdataflag(FundChannelCode.JFWG);
		data.setPointdataflag(InteralFlag.YES);
		try {
			data.setCleardate(DateUtil.DateFormat_To_Date(this.getReqDate()));
		} catch (PeException e) {
		}
		this.setTransCode("consumePoint");
		this.setBillNo(data.getInnerfundtransnbr());
		this.setAmtThisTime(data.getDeductAmt().setScale(1));
		this.setBranchNo(data.getBranchNo());
		this.setClientNo(data.getClientNo());
		this.setRetry("0");
		String url = "/consumePoint?requestId=" +this.getRequestId()
				+ "&protocolVersion=" + this.getProtocolVersion() + "&clientNo="
				+ this.getClientNo() + "&billNo="+ this.getBillNo()+"&integral="+this.getAmtThisTime()
				+"&branchNo="+this.getBranchNo()+"&retry="+this.getRetry();
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



	public String getRetry() {
		return retry;
	}



	public void setRetry(String retry) {
		this.retry = retry;
	}
	
	
}
