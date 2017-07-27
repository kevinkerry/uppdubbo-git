package com.csii.upp.dto.router.hvps;

import java.util.Date;

import com.csii.upp.constant.ConstHvps;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 跨行大额转账查询
 * @author wy
 *
 */
public class ReqHvpsQueryAcctWithdrawl extends ReqHvpsHead {

	private String origInnerFundTransNbr; // 渠道流水号
	private Date origUpperTransDate; // 原渠道交易日期
	private String hvbepsFlag; // 大小额标识

	public ReqHvpsQueryAcctWithdrawl(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.QueryAcctWithdrawl);
		this.setOrigInnerFundTransNbr(data.getInnerfundtransnbr());
		this.setOrigUpperTransDate(data.getTranstime());
		this.setHvbepsFlag(ConstHvps.HVPS_FLAG);
	}
	
	public String getOrigInnerFundTransNbr() {
		return origInnerFundTransNbr;
	}

	public void setOrigInnerFundTransNbr(String origInnerFundTransNbr) {
		this.origInnerFundTransNbr = origInnerFundTransNbr;
	}

	public Date getOrigUpperTransDate() {
		return origUpperTransDate;
	}

	public void setOrigUpperTransDate(Date origUpperTransDate) {
		this.origUpperTransDate = origUpperTransDate;
	}

	public String getHvbepsFlag() {
		return hvbepsFlag;
	}

	public void setHvbepsFlag(String hvbepsFlag) {
		this.hvbepsFlag = hvbepsFlag;
	}

	
	
	
}
