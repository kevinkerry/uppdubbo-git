package com.csii.upp.dto.router.pbc;

import java.util.Date;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;

public class ReqPbcCheckFile extends ReqAppHead{

	public ReqPbcCheckFile(InputFundData data) {
		super(data);
		this.collatingdate = data.getTransdate();
		this.format = FundChannelCode.PBC;
		setTransCode("ZXDZCOLLECT");
	}
	private Date collatingdate;//对账日期
	private String format;//渠道
	public Date getCollatingdate() {
		return collatingdate;
	}
	public void setCollatingdate(Date collatingdate) {
		this.collatingdate = collatingdate;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
}
