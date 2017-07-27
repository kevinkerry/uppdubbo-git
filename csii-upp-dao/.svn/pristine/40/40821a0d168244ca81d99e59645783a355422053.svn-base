package com.csii.upp.dao.extend;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.generate.Batchconfirmsubcleartrans;
import com.csii.upp.dto.generate.Onlinetranshist;

public class BatchConfirmSubClearTransExtendDAO extends BasePayDAO {

	/**
	 * 转移行内后台交易明细表中非待对账的数据到行内后台交易明细历史表中
	 * @return
	 */
	public static void insertBatchConfirmSubClearTrans(Date checkDate,String fundchanneltypcd) {
		Map map = new HashMap();
		map.put("ClearDate", checkDate);
		map.put("departmentnbr", fundchanneltypcd);
		// TODO Auto-generated method stub
		getSqlMap().insert("BATCHCONFIRMSUBCLEARTRANSEXTEND.InsertBatchConfirmSubClearTrans",map);
	}
	
	/**
	 * 通过清分方式获取对账确认后的子订单交易流水（一口清分）
	 * 
	 * @param clearClassModeCd
	 *            清分方式
	 * @return
	 * @author 姜星
	 */
	@SuppressWarnings({ "unchecked" })
	public static List<Batchconfirmsubcleartrans> queryConfirmTransList(String clearClassModeCd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("clearClassModeCd", clearClassModeCd);
		return (List<Batchconfirmsubcleartrans>) getSqlMap()
				.queryForList("BATCHCONFIRMSUBCLEARTRANSEXTEND.queryConfirmTransList", map);
	}
	
	/**
	 * 通过清分方式获取对账确认后的子订单交易流水（直接清分）
	 * 
	 * @param clearClassModeCd
	 *            清分方式
	 * @return
	 * @author 姜星
	 */
	@SuppressWarnings({ "unchecked" })
	public static List<Onlinetranshist> queryOnlineTransList(String clearClassModeCd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("clearClassModeCd", clearClassModeCd);
		return (List<Onlinetranshist>) getSqlMap()
				.queryForList("BATCHCONFIRMSUBCLEARTRANSEXTEND.queryOnlineTransList", map);
	}
	
	/**
	 * 获取商户账户信息
	 * @param merNbr 商户号
	 * @return
	 * @author 姜星
	 */
	public static Map queryAcctInfo(String merNbr) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("merNbr", merNbr);
		return (Map) getSqlMap().queryForObject("BATCHCONFIRMSUBCLEARTRANSEXTEND.queryAcctInfo", map);
	}
	
	public static Map getBatchconfirmsubcleartrans(String transSeqNbr) {
		Map map = new HashMap();
		map.put("transSeqNbr", transSeqNbr);
		return (Map) getSqlMap().queryForObject("BATCHCONFIRMSUBCLEARTRANSEXTEND.getBatchconfirmsubcleartrans", map);
	}
	public static Map getOrigOnlinesubtrans(String transSeqNbr) {
		Map map = new HashMap();
		map.put("transSeqNbr", transSeqNbr);
		return (Map) getSqlMap().queryForObject("BATCHCONFIRMSUBCLEARTRANSEXTEND.getOrigOnlinesubtrans", map);
	}
}
