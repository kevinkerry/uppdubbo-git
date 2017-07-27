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
public class BatchMerTransExtendDAO extends BasePayDAO {
	
	public static void insertBatchMerTransToHist(Map<String, Object> map) {
		getSqlMap().insert("BATCHMERTRANSEXTEND.insertBatchMerTransToHist", map);
	}
	
	public static List<DeptSettlementMap> selectFromBatchMerTrans(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHMERTRANSEXTEND.selectFromBatchMerTrans", map);
	}
	
	/**
	 * 取商户结算交易明细数据
	 * 
	 * @author chenchao
	 */
	public static List<Batchmertrans> getDateFromBatchMerTrans(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHMERTRANSEXTEND.getDateFromBatchMerTrans", map);
	}
}
