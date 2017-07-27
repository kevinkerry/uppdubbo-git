package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.util.StringUtil;
/**
 * 查询卡信息请求参数
 * @author gq
 *
 */
public class ReqQuerySignInfoList extends ReqPaymHead{

	private String beginDate;
	private String endDate;
	private String transTypCd;
	private String pageSize;
	private String pageNum;
	private int startNo;
	private int endNo;

	public ReqQuerySignInfoList(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.QuerySignInfoList);
		this.setBeginDate(data.getBeginDate());
		this.setEndDate(data.getEndDate());
		this.setTransTypCd(data.getTranstypcd());
		
		if(!(null == data.getPagenum() && data.getPagesize() == null)){
			this.setPageNum(data.getPagenum());
			this.setPageSize(data.getPagesize());
			startNo= (StringUtil.toInt(data.getPagenum())-1)* StringUtil.toInt(data.getPagesize())+1;
			this.setStartNo(startNo);
			
			endNo =  startNo+ StringUtil.toInt(data.getPagesize())-1;
			this.setEndNo(endNo);
		}
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTransTypCd() {
		return transTypCd;
	}

	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
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
}
