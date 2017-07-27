package com.csii.upp.dto.router;

import java.util.Date;
import java.util.UUID;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.InputData;
import com.csii.upp.idto.IDto;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 发送路由请求系统报文头
 * 
 * @author 徐锦
 * 
 */
public class ReqSysHead implements IDto {
	public ReqSysHead(InputData data) {
		setPageNum(data.getPagenum());
		setPageSize(data.getPagesize());
		this.setBizTrackNo(String.valueOf(UUID.randomUUID().getLeastSignificantBits()));
		this.setReqSeqNo(data.getTransnbr());

		this.setReqDate(StringUtil.isObjectEmpty(data.getTransdate())
				? DateUtil.Date_To_DateTimeFormat(new Date(), DateFormatCode.DATE_FORMATTER3)
				: DateUtil.Date_To_DateTimeFormat(data.getTransdate(), DateFormatCode.DATE_FORMATTER3));

		this.setReqTime(StringUtil.isObjectEmpty(data.getTranstime())
				? DateUtil.Date_To_DateTimeFormat(new Date(), DateFormatCode.DATETIME_FORMATTER3)
				: DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
	}

	private String bizTrackNo;
	private String chnlId;
	private String reqSeqNo;
	private String reqDate;
	private String reqTime;

	private String transCode;// 交易码
	private String srvChnlId;//服务渠道号
	

	/**
	 * 每页记录数
	 */
	private String pageSize;
	/**
	 * 请求页号
	 */
	private String pageNum;

	public String getBizTrackNo() {
		return bizTrackNo;
	}

	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}

	public String getChnlId() {
		return chnlId;
	}

	public void setChnlId(String chnlId) {
		this.chnlId = chnlId;
	}

	public String getReqSeqNo() {
		return reqSeqNo;
	}

	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getSrvChnlId() {
		return srvChnlId;
	}

	public void setSrvChnlId(String srvChnlId) {
		this.srvChnlId = srvChnlId;
	}
}
