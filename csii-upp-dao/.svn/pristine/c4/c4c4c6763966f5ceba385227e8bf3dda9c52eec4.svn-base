/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dao
 * create: 2016年2月17日 下午3:21:35
 * vc: $Id: $
 */
package com.csii.upp.dao.extend;

import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.extend.DeptSettlementMap;
import com.csii.upp.dto.generate.Batchmertrans;

/**
 * TODO 请填写注释.
 * @author chen chao 
 * @version $Revision:$
 */
public class BatchFeeProfitTransExtendDAO extends BasePayDAO {
	
	public static void insertBatchFeeProfitTransToHist(Map<String, Object> map) {
		getSqlMap().insert("BATCHFEEPROFITTRANSEXTEND.insertBatchFeeProfitTransToHist", map);
	}
	
	public static List<DeptSettlementMap> selectFromBatchFeeProfitTrans(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHFEEPROFITTRANSEXTEND.selectFromBatchFeeProfitTrans", map);
	}
	
	/**
	 * 取商户结算交易明细数据
	 * 
	 * @author chenchao
	 */
	public static List<Batchmertrans> getDateFromBatchFeeProfitTrans(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHFEEPROFITTRANSEXTEND.getDateFromBatchFeeProfitTrans", map);
	}
}
