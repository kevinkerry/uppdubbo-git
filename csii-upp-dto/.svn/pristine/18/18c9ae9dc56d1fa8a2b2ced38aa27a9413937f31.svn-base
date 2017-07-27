package com.csii.upp.dto.router.ibps;

import com.csii.upp.annotation.AttributeProperties;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 直销银行超级网银对账申请输入参数
 * 
 * @author 姜星
 *
 */
public class ReqIbpsCheckApply extends ReqIbpsHead {
	public ReqIbpsCheckApply(InputFundData data) {
		super(data);
//		setTransCode("IbpsCheckApply"); // 暂定
		setTransCode("910005"); //HBB
		this.setCollatingdate(DateUtil.Date_To_DateTimeFormat(
				data.getTransdate(), DateFormatCode.DATE_FORMATTER3)); // 对账日期
//		this.setStartxchgdate(DateUtil.Date_To_DateTimeFormat(
//				data.getTransdate(), DateFormatCode.DATE_FORMATTER3));
//		this.setEndxchgdate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(),
//				DateFormatCode.DATE_FORMATTER3));
		this.setFormat(FundChannelCode.IBPS); // 渠道类型;
	}

	@AttributeProperties(required = true)
	private String collatingdate; // 对帐日期
	@AttributeProperties(required = true)
	private String startxchgdate; // 开始时间
	@AttributeProperties(required = true)
	private String endxchgdate; // 结束时间
	@AttributeProperties(required = true)
	private String format; // 渠道类型

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
