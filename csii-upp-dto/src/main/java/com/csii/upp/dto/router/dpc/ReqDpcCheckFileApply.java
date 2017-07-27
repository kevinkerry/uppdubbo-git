/**
 * 
 */
package com.csii.upp.dto.router.dpc;

import java.util.Date;

import com.csii.upp.dto.extend.InputFundData;

/**
 * @author DreamsHunter
 *
 */
public class ReqDpcCheckFileApply extends ReqDpcHead {

	public ReqDpcCheckFileApply(InputFundData data) {
		super(data);
		setTransCode("DpcCheckApply");
		this.setFormat("SXTC");
		this.setTransdate(data.getTransdate());
		this.setSvcclass(2);
	}

	private Date transdate; // 工作日期
	private long workround; // 工作场次
	private long svcclass; // 业务类型 现在只使用 2
	private String format; // 渠道类型

	public Date getTransdate() {
		return transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}

	public long getWorkround() {
		return workround;
	}

	public void setWorkround(long workround) {
		this.workround = workround;
	}

	public long getSvcclass() {
		return svcclass;
	}

	public void setSvcclass(long svcclass) {
		this.svcclass = svcclass;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}



}
