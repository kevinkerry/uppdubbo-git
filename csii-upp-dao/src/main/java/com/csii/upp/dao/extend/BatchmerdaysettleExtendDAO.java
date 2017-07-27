package com.csii.upp.dao.extend;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchmerdaysettleDAO;
import com.csii.upp.dto.generate.Batchmerdaysettle;
import com.csii.upp.dto.generate.BatchmerdaysettleExample;

public class BatchmerdaysettleExtendDAO extends BasePayDAO {
	/**
	 * 计算支付金额
	 */
	public static BigDecimal sumPayAmount(Map<String, Object> map) {
		return (BigDecimal) getSqlMap().queryForObject("BATCHMERDAYSETTLEEXTEND.sumPayAmount", map);
	}
	/**
	 * 计算未结算退货金额
	 */
	public static BigDecimal sumwithdrawAmount(Map<String, Object> map) {
		return (BigDecimal) getSqlMap().queryForObject("BATCHMERDAYSETTLEEXTEND.sumwithdrawAmount", map);
	}
	
	/**
	 * 将商户结算表记录转移到历史表中
	 * @param map
	 * @author 姜星
	 */
	public static void transferMerchantSettExtTmpToHistory(Map<String, Object> map) {
		
		Date clearDate = (Date) map.get("ClearDate");
		BatchmerdaysettleExample example = new BatchmerdaysettleExample();
		example.createCriteria().andCleardateLessThanOrEqualTo(clearDate).andSettlestatusEqualTo("1");
		try {
			List<Batchmerdaysettle> batchmerdaysettleList = BatchmerdaysettleDAO.selectByExample(example);
			for(Batchmerdaysettle batchmerdaysettle : batchmerdaysettleList){
				getSqlMap().insert("BATCHMERDAYSETTLEEXTEND.transferMerchantSettExtTmpToHistory", batchmerdaysettle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 删除商户结算表指定清算日期之前的记录
	 * 
	 * @param map
	 * @author 姜星
	 */
	public static void deleteMerchantSettExtTmpBeforeDate(Map<String, Object> map) {
		getSqlMap().delete("BATCHMERDAYSETTLEEXTEND.deleteMerchantSettExtTmpBeforeDate", map);
	}
	
	/**
	 * 统计汇总
	 * 
	 * @param map
	 * @return
	 * @author 姜星
	 */
	public static List<Map> queryMerchantSettForExt(String merNbr, Date clearDate, String settleStatus) {
		Map map = new HashMap();
		map.put("MerNbr", merNbr);
		map.put("ClearDate", clearDate);
		map.put("SettleStatus", settleStatus);
		return (List<Map>) getSqlMap().queryForList("BATCHMERDAYSETTLEEXTEND.queryMerchantSettForExt", map);
	}
	
	/**
	 * 取结算明细数据
	 * 
	 * @author 陈超
	 */
	public static List<Batchmerdaysettle> getDateFromBatchMerDaySettle(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHMERDAYSETTLEEXTEND.getDateFromBatchMerDaySettle", map);
	}
}
