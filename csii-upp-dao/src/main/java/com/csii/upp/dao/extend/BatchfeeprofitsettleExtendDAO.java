package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchfeeprofitsettleDAO;
import com.csii.upp.dto.generate.Batchfeeprofitsettle;
import com.csii.upp.dto.generate.BatchfeeprofitsettleExample;

public class BatchfeeprofitsettleExtendDAO extends BasePayDAO {

	public static List<Map> queryMerchantSettExtForDeposit(Map<String, Object> map) {
		return (List<Map>) getSqlMap().queryForList("BATCHFEEPROFITSETTLEEXTEND.queryMerchantSettExtForDeposit", map);
	}
	
	public static List<Map> querySubMerchantSettDetail(Map<String, Object> map) {
		return (List<Map>) getSqlMap().queryForList("BATCHFEEPROFITSETTLEEXTEND.querySubMerchantSettDetail", map);
	}
	
	public static List<Map> querySubMerchantTransDetail(Map map) {
		return (List<Map>) getSqlMap().queryForList("BATCHFEEPROFITSETTLEEXTEND.querySubMerchantTransDetail", map);
	}
	
	/**
	 * 将商户结算表临时表记录转移到历史表中
	 * @param map
	 * @return
	 * @author 姜星
	 */
	public static void transferMerchantSettExtToHistory(Map map) {
		Date clearDate = (Date) map.get("ClearDate");
		BatchfeeprofitsettleExample example = new BatchfeeprofitsettleExample();
		example.createCriteria().andCleardateLessThanOrEqualTo(clearDate).andSendstatusEqualTo("0");
		try {
			List<Batchfeeprofitsettle> batchfeeprofitsettleList = BatchfeeprofitsettleDAO.selectByExample(example);
			for(Batchfeeprofitsettle batchfeeprofitsettle : batchfeeprofitsettleList) {
				getSqlMap().insert("BATCHFEEPROFITSETTLEEXTEND.transferMerchantSettExtToHistory", batchfeeprofitsettle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 删除商户结算日结算表指定清算日期之前的记录
	 * @param map
	 * @return
	 * @author 姜星
	 */
	public static int deleteMerchantSettExtBeforeDate(Map map) {
		return getSqlMap().delete("BATCHFEEPROFITSETTLEEXTEND.deleteMerchantSettExtBeforeDate", map);
	}
	/**
	 * 取所有一级商户
	 * 
	 * @author chenchao 
	 */
	public static List<Map<String, Object>> getMerNbrFromBatchfeeprofitsettle(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHFEEPROFITSETTLEEXTEND.getMerNbrFromBatchmersettle", map);
	}
}
