package com.csii.upp.dto.router.eaccount;

import java.util.Date;

import com.csii.upp.idto.IDto;

public class BaseResponse implements IDto {
	private String retCode;// 返回码
	private String retMsg;// 返回信息
	private Date postDate;// 交易日期

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
