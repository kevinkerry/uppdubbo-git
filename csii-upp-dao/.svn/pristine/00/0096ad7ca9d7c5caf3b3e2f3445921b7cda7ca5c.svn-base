package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.BatchcleartransDAO;
import com.csii.upp.dto.generate.Batchcleartrans;
import com.csii.upp.dto.generate.BatchcleartransExample;

public class BatchcleartransExtendDAO extends BasePayDAO {

	/**
	 * 通过对账状态获得内部待清算资金流水列表
	 * @param checkStatus
	 * @return
	 */
	public static List<Batchcleartrans> getBatchcleartransByCheckStatus(String checkStatus,String fundchanneltypcd) {
		BatchcleartransExample example = new BatchcleartransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus).andDepartmentnbrEqualTo(fundchanneltypcd);
		example.setOrderByClause("TransSeqNbr asc");
		List<Batchcleartrans> batchcleartranss;
		try {
			batchcleartranss = BatchcleartransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Batchcleartrans Failed.");
		}
		if (batchcleartranss.isEmpty()) {
//			throw new PeRuntimeException("Get Batchcleartrans is null:");
		}
		return batchcleartranss;
	}
	

	/**
	 * 查询BatchClearTrans（清算交易明细表）中对账平且交易成功的数据
	 * @author chen chao 
	 */
	@SuppressWarnings("unchecked")
	public static List<Batchcleartrans> selectFromBatchcleartrans(String merNbr) {
		Batchcleartrans record = new Batchcleartrans();
		record.setMernbr(merNbr);
		record.setCheckstatus(CheckStatus.CHECKED);
		record.setTransstatus(TransStatus.SUCCESS);
		List<Batchcleartrans> list =  getSqlMap().queryForList("BATCHCLEARTRANS.selectByExample", record);
		return list;
	}
}
