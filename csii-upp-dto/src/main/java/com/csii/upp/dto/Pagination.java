package com.csii.upp.dto;

/**
 * 分页用基础信息
 * 
 * @author 徐锦
 *
 */
public class Pagination {
	private Integer pageSize;
	private Integer pageNum;

	public Pagination(Integer pageSize, Integer pageNum) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	public int getSkipResults() {
		return isParamValid() ? pageSize * (pageNum - 1) : 0;
	}

	public int getMaxResults() {
		return isParamValid() ? pageSize * pageNum : 999;
	}

	private boolean isParamValid() {
		return pageSize != null && pageSize.intValue() > 0 && pageNum != null
				&& pageNum.intValue() > 0;
	}
}
