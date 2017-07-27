/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dao
 * create: 2016年1月26日 上午10:44:47
 * vc: $Id: $
 */
package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;

/**
 * TODO 请填写注释.
 * @author chen chao 
 * @version $Revision:$
 */
public class OnlinesubtransExtendDAO extends BasePayDAO {
	/**
	 * 将待对账的OnlineSubTrans（子交易明细表）数据插入OnlineSubTransHist（子交易明细历史表）
	 * @param map
	 */
	public static void insertOnlinesubtransToHist(Map<String, Object> map) {
		getSqlMap().insert("ONLINESUBTRANSEXTEND.insertOnlinesubtransToHist", map);
	}
	
	/**
	 * 将待对账的OnlineSubTrans（子交易明细表）数据插入BatchClearSubTrans（子订单清算）
	 */
	public static void insertOlinesubtransToBatchClearSubTrans(Map<String, Object> map) {
		getSqlMap().insert("ONLINESUBTRANSEXTEND.insertOlinesubtransToBatchClearSubTrans", map);
	}
	
	
	/**
	 * 将待对账的子交易数据状态更新，根据平台流水号与交易表关联
	 * @param map
	 */
	public static void updateOnlinesubtransForCheck(Map<String, Object> map){
		String checkStatus = (String) map.get("checkStatus");
		OnlinetransExample example = new OnlinetransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus);
		try {
			List<Onlinetrans> onlinetransList = OnlinetransDAO.selectByExample(example);
			for(Onlinetrans onlinetrans : onlinetransList) {
				map.put("transSeqNbr", onlinetrans.getTransseqnbr());
				getSqlMap().update("ONLINESUBTRANSEXTEND.updateOnlineSubtransForCheck", map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
