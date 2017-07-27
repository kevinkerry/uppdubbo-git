package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchdownsystransDAO;
import com.csii.upp.dto.generate.Batchdownsystrans;
import com.csii.upp.dto.generate.BatchdownsystransExample;

public class BatchdownsystransExtendDAO extends BasePayDAO {
	/**
	 * 通过对账状态获得后台交易对账流水列表
	 * 
	 * @param checkStatus
	 * @return
	 */
	public static List<Batchdownsystrans> getBatchDownsystrans(String checkStatus) {
		BatchdownsystransExample example = new BatchdownsystransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus);
		example.setOrderByClause("DownSysTransNbr asc");
		List<Batchdownsystrans> Innerfundtranss;
		try {
			Innerfundtranss = BatchdownsystransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Batchdownsystrans Failed.");
		}
		return Innerfundtranss;
	}
	
	/**
	 * 通过下游交易流水号更新下游交易对账流水的对账状态
	 * @param downSysTransNbr
	 * @param checkStatus
	 */
	public static void updateDownsysfundtrans(String downSysTransNbr,String checkStatus) {
		Batchdownsystrans fundRtxn=new Batchdownsystrans();
		fundRtxn.setDownsystransnbr(downSysTransNbr);
		fundRtxn.setCheckstatus(checkStatus);
		try {
			if (BatchdownsystransDAO.updateByPrimaryKeySelective(fundRtxn) == 0) {
				throw new PeRuntimeException(
						"Update Batchdownsystrans Failed for unknown reason.");
			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Update Batchdownsystrans Table Failed.", e);
		}
	}
	
	public static Integer getBatchdownsystransCnt(String  downSysTransNbr) {
		BatchdownsystransExample example = new BatchdownsystransExample();
		example.createCriteria().andDownsystransnbrEqualTo(downSysTransNbr);
		Integer count=0;
		try {
			count = BatchdownsystransDAO.countByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("get Batchdownsystrans Table Failed.", e);
		}
		return count;
	}
	
	/**
	 * 获取rtxnDate前(包括)的待对账的下游交易对账流水数目
	 * @param transDate
	 * @param checkStatus
	 */
	public static Integer getBatchdownsystransCnt(Date transDate,String checkStatus) {
		Batchdownsystrans record=new Batchdownsystrans();
		record.setTransdate(transDate);
		record.setCheckstatus(checkStatus);
		Integer count = (Integer)getSqlMap().queryForObject("Batchdownsystrans.getDownRtxnCnt", record);
		return count;
	}

	
	/**
	 * 插入行内后台交易明细流水
	 * 
	 * @param record
	 */
	public static void insertBatchDownSysTrans(Batchdownsystrans record) {
		try {
			BatchdownsystransDAO.insert(record);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Batchdownsystrans Table Failed.", e);
		}
	}
	
}
