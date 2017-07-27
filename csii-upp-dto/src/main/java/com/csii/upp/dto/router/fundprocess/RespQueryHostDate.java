/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import java.util.Date;

/**
 * @author gaoqi 查询核心账务日期返回
 */
public class RespQueryHostDate extends RespFundProHead {
	private Date hostClearDate;

	public Date getHostClearDate() {
		return hostClearDate;
	}

	public void setHostClearDate(Date hostClearDate) {
		this.hostClearDate = hostClearDate;
	}

}
