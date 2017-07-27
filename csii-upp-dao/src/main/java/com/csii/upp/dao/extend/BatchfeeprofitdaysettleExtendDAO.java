package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchfeeprofitdaysettleDAO;
import com.csii.upp.dto.generate.Batchfeeprofitdaysettle;
import com.csii.upp.dto.generate.BatchfeeprofitdaysettleExample;

public class BatchfeeprofitdaysettleExtendDAO extends BasePayDAO {
	/**
	 * 将商户结算表记录转移到历史表中
	 * @param map
	 * @author 姜星
	 */
	public static void transferMerchantSettExtTmpToHistory(Map<String, Object> map) {
		
		Date clearDate = (Date) map.get("ClearDate");
		BatchfeeprofitdaysettleExample example = new BatchfeeprofitdaysettleExample();
		example.createCriteria().andCleardateLessThanOrEqualTo(clearDate).andSettlestatusEqualTo("1");
		try {
			List<Batchfeeprofitdaysettle> batchfeeprofitdaysettleList = BatchfeeprofitdaysettleDAO.selectByExample(example);
			for(Batchfeeprofitdaysettle batchfeeprofitdaysettle : batchfeeprofitdaysettleList) {
				getSqlMap().insert("BATCHFEEPROFITDAYSETTLEEXTEND.transferMerchantSettExtTmpToHistory", batchfeeprofitdaysettle);
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
		getSqlMap().delete("BATCHFEEPROFITDAYSETTLEEXTEND.deleteMerchantSettExtTmpBeforeDate", map);
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
		return (List<Map>) getSqlMap().queryForList("BATCHFEEPROFITDAYSETTLEEXTEND.queryMerchantSettForExt", map);
	}
	
	/**
	 * 取结算明细数据
	 * 
	 * @author 陈超
	 */
	public static List<Batchfeeprofitdaysettle> getDateFromBatchMerDaySettle(Map<String, Object> map) {
		return getSqlMap().queryForList("BATCHFEEPROFITDAYSETTLEEXTEND.getDateFromBatchFeeProfitDaySettle", map);
	}
}
