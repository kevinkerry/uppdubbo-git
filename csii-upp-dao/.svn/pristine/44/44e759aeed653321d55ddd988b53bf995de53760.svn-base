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
import com.csii.upp.dto.generate.Batchmertranshist;
public class BatchMerTransHistExtendDAO extends BasePayDAO{
	/**
	 * 取商户结算交易明细数据
	 * 
	 * @author CC
	 */
	public static List<Batchmertranshist> getDateFromBatchMerTrans(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHMERTRANSHISTEXTEND.getDateFromBatchMerTrans", map);
	}
}
