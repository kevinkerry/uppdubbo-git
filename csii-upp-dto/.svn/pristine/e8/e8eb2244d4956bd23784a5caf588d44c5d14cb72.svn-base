package com.csii.upp.dto.router.beps;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.HvpsTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

public class ReqBepsCheck extends ReqBepsHead {

	public ReqBepsCheck(InputFundData data) {
		super(data);
		this.setCollatingdate(DateUtil.Date_To_DateTimeFormat(
				data.getTransdate(), DateFormatCode.DATE_FORMATTER3));
		this.setStartxchgdate(DateUtil.Date_To_DateTimeFormat(
				data.getTransdate(), DateFormatCode.DATE_FORMATTER3));
		this.setEndxchgdate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(),
				DateFormatCode.DATE_FORMATTER3));
		this.format = FundChannelCode.BEPS;
		this.setTransCode(HvpsTransCode.HvpsCheckApply);
	}

	private String collatingdate;// 对账日期
	private String startxchgdate;// 扎差开始时间(大额不用)
	private String endxchgdate;// 扎差结束时间不能大于对账日期
	private String format;// 渠道

	public String getCollatingdate() {
		return collatingdate;
	}

	public void setCollatingdate(String collatingdate) {
		this.collatingdate = collatingdate;
	}

	public String getStartxchgdate() {
		return startxchgdate;
	}

	public void setStartxchgdate(String startxchgdate) {
		this.startxchgdate = startxchgdate;
	}

	public String getEndxchgdate() {
		return endxchgdate;
	}

	public void setEndxchgdate(String endxchgdate) {
		this.endxchgdate = endxchgdate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
