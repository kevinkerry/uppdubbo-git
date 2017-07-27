package com.csii.upp.dao.extend;

import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;

public class BatchmersettleExtendDAO extends BasePayDAO {

	public static List<Map> queryMerchantSettExtForDeposit(Map<String, Object> map) {
		return (List<Map>) getSqlMap().queryForList("BATCHMERSETTLEEXTEND.queryMerchantSettExtForDeposit", map);
	}
	
	public static List<Map> querySubMerchantSettDetail(Map<String, Object> map) {
		return (List<Map>) getSqlMap().queryForList("BATCHMERSETTLEEXTEND.querySubMerchantSettDetail", map);
	}
	
	public static List<Map> querySubMerchantTransDetail(Map map) {
		return (List<Map>) getSqlMap().queryForList("BATCHMERSETTLEEXTEND.querySubMerchantTransDetail", map);
	}
	
	/**
	 * 将商户结算表临时表记录转移到历史表中
	 * @param map
	 * @return
	 * @author 姜星
	 */
	public static void transferMerchantSettExtToHistory(Map map) {
		getSqlMap().insert("BATCHMERSETTLEEXTEND.transferMerchantSettExtToHistory", map);
	}
	
	/**
	 * 删除商户结算日结算表指定清算日期之前的记录
	 * @param map
	 * @return
	 * @author 姜星
	 */
	public static int deleteMerchantSettExtBeforeDate(Map map) {
		return getSqlMap().delete("BATCHMERSETTLEEXTEND.deleteMerchantSettExtBeforeDate", map);
	}
	/**
	 * 取所有一级商户
	 * 
	 * @author chenchao 
	 */
	public static List<Map<String, Object>> getMerNbrFromBatchmersettle(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHMERSETTLEEXTEND.getMerNbrFromBatchmersettle", map);
	}
}
