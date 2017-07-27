package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.DownsysfundtransDAO;
import com.csii.upp.dto.generate.Downsysfundtrans;
import com.csii.upp.dto.generate.DownsysfundtransExample;

public class DownsysfundtransExtendDAO extends BasePayDAO {
	/**
	 * 通过资金通道码和对账状态获得下游交易对账流水列表
	 * 
	 * @param fundChannelCd
	 * @return
	 */
	public static List<Downsysfundtrans> getDownsysfundtrans(String fundChannelCd,String checkState) {
		DownsysfundtransExample example = new DownsysfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd)
				.andCheckstatusEqualTo(checkState);
		example.setOrderByClause("innerfundtransnbr asc");
		List<Downsysfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = DownsysfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Downsysfundtrans Failed.");
		}
		return Innerfundtranss;
	}
	
	/**
	 * 通过内部交易号更新下游交易对账流水的对账状态
	 * @param innerRtxnNbr
	 * @param checkState
	 */
	public static void updateDownsysfundtrans(String innerRtxnNbr,String checkState) {
		Downsysfundtrans fundRtxn=new Downsysfundtrans();
		fundRtxn.setInnerfundtransnbr(innerRtxnNbr);
		fundRtxn.setCheckstatus(checkState);
		try {
			if (DownsysfundtransDAO.updateByPrimaryKeySelective(fundRtxn) == 0) {
				throw new PeRuntimeException(
						"Update Downsysfundtrans Failed for unknown reason.");
			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Update Downsysfundtrans Table Failed.", e);
		}
	}
	
	public static Integer getDownsysfundtransCnt(String  innerRtxnNbr) {
		DownsysfundtransExample example = new DownsysfundtransExample();
		example.createCriteria().andInnerfundtransnbrEqualTo(innerRtxnNbr);
		Integer count=0;
		try {
			count = DownsysfundtransDAO.countByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("get Downsysfundtrans Table Failed.",e);
		}
		return count;
	}
	
	/**
	 * 更新rtxnDate前(包括)的待对账的下游交易对账流水为对账不平
	 * @param fundChannelCd
	 * @param innerRtxnNbr
	 * @param checkState
	 */
	public static Integer getDownRtxnCnt(String fundChannelCd,Date rtxnDate,String checkState) {
		Downsysfundtrans record=new Downsysfundtrans();
		record.setTransdate(rtxnDate);
		record.setCheckstatus(checkState);
		record.setFundchannelcode(fundChannelCd);
		Integer count = (Integer)getSqlMap().queryForObject("Downsysfundtrans.getDownRtxnCnt", record);
		return count;
	}
	



	/**
	 * 插入下游对账流水
	 * 
	 * @param record
	 */
	public static void insertDownsysfundtrans(Downsysfundtrans record) {
		try {
			DownsysfundtransDAO.insert(record);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Innerfundtrans Table Failed.", e);
		}
	}
	
	public static List<Map<String, Object>> getDownsysfundtransList(Map map, int pageNum, int pageSize) {
		return (List<Map<String, Object>>) queryForList("Downsysfundtrans.getDownsysfundtransList", map,
				pageSize * (pageNum - 1), pageSize * pageNum);
	}
	
	/**
	 * 将清算日期当天的下游交易对账流水表DownSysFundTrans移植到历史表DownSysFundTransHist，同时将对账状态更改为待对账   
	 * @author Chen Chao
	 */
	public static void InsertDownsysfundtransToHist(String fundChannelCd,String checkStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fundChannelCd", fundChannelCd);
		map.put("checkStatus", checkStatus);
		getSqlMap().insert("DOWNSYSFUNDTRANSEXTEND.InsertDownsysfundtransToHist",map);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getTotalTransAmtAndFeeAmt(Map<String, Object> map){
		return getSqlMap().queryForList("DOWNSYSFUNDTRANSEXTEND.getTotalTransAmtAndFeeAmt", map);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getMerDeptTransAmt(Map<String, Object> map){
		return getSqlMap().queryForList("DOWNSYSFUNDTRANSEXTEND.getMerDeptTransAmt", map);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getMerDeptTransmode(Map<String, Object> map){
		return getSqlMap().queryForList("DOWNSYSFUNDTRANSEXTEND.getMerDeptTransmode", map);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getCoreTodayClearAmt(Map<String, Object> map){
		return getSqlMap().queryForList("DOWNSYSFUNDTRANSEXTEND.getCoreTodayClearAmt", map);
	}
    
	
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param map
	 * @return
	 */
	public static List<Map<String, Object>> getMerDeptQrCodeAmt(Map<String, Object> map){
		return getSqlMap().queryForList("DOWNSYSFUNDTRANSEXTEND.getMerDeptQrCodeAmt", map);
	}	
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public static List<Map<String, Object>> getMerDeptQrCodeFeeAmt(Map<String, Object> map){
		return getSqlMap().queryForList("DOWNSYSFUNDTRANSEXTEND.getMerDeptQrCodeFeeAmt", map);
	}		
	
	/**
	 * 
	 * @param map
	 */
	public static void InsertDownsysfundtransFromInnerfundtrans(Map<String, Object> map){
		getSqlMap().delete("DOWNSYSFUNDTRANSEXTEND.DeleteDownsysfundtrans", map);
		getSqlMap().insert("DOWNSYSFUNDTRANSEXTEND.InsertDownsysfundtransFromInnerfundtrans",map);
	}
	
}
