package com.csii.upp.fundprocess.action;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.Pagination;

/**
 * 分页查询交易基类 分页查询交易请求使用QueryRequest报文头，应答使用QueryResponse报文头
 * 请求时通用参数为pageNum和pageSize，应答通用参数为totalNum
 * 
 * @author 徐锦
 *
 */
public abstract class PayQueryAction extends PayOnlineAction {

	@Override
	public void execute(Context paramContext) throws PeException {
		setPaginationHeader(paramContext, query(paramContext));
	}
	
	/**
	 * @param context
	 * @return 分页用基础信息
	 */
	protected Pagination getPaginationDTO(Context context) {
		return new Pagination(getPageSize(context), getPageNum(context));
	}

	/**
	 * @param context
	 * @return 每页记录条目数
	 */
	private Integer getPageSize(Context context) {
		return context.getInteger("pageSize");
	}

	/**
	 * @param context
	 * @return 请求页数（从1开始）
	 */
	private Integer getPageNum(Context context) {
		return context.getInteger("pageNum");
	}

	/**
	 * @description 支持分页查询功能
	 * @param context
	 *            上下文参数
	 * @param queryResultList
	 *            查询结果集
	 */
	public void setPaginationHeader(Context context,
			List<Map<String, Object>> queryResultList) {
		if (queryResultList != null && !queryResultList.isEmpty()) {
			Map<String, Object> dataMap = queryResultList.get(0);
			context.setData(Dict.TOTAL_NUM, dataMap.get(Dict.TOTAL_NUM));
		}
	}

	public abstract List<Map<String, Object>> query(Context context)
			throws PeException;
}
