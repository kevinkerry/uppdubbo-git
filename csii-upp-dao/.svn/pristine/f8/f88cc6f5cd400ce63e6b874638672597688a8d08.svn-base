package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchdownsystransDAO;
import com.csii.upp.dto.generate.Batchdownsystrans;
import com.csii.upp.dto.generate.BatchdownsystransExample;

public class BatchdownsystranshistExtendDAO extends BasePayDAO {

	/**
	 * 转移行内后台交易明细表中非待对账的数据到行内后台交易明细历史表中
	 * @return
	 */
	public static void insertBatchDownSysTransHist(String checkStatus) {
		
		BatchdownsystransExample example = new BatchdownsystransExample();
		example.createCriteria().andCheckstatusNotEqualTo(checkStatus);
		try {
			List<Batchdownsystrans> batchdownsystransList = BatchdownsystransDAO.selectByExample(example);
			for(Batchdownsystrans batchdownsystrans : batchdownsystransList) {
				getSqlMap().insert("BATCHDOWNSYSTRANSHISTEXTEND.insertBatchDownSysTransHist",batchdownsystrans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
