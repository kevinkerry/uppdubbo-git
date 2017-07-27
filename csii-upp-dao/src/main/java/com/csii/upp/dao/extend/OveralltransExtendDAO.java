package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dto.IssueFile;
import com.csii.upp.dto.extend.CheckDataPrepare;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;

public class OveralltransExtendDAO extends BasePayDAO {
	/**
	 * 通过总交易号更新总交易流水的交易状态
	 * 
	 * @param innerRtxnNbr
	 * @param checkState
	 * @param rtxnState
	 */
	public static void updateOveralltrans(String OveralltransNbr,
			String OveralltransState,String overAllCheckState) {
		Overalltrans Overalltrans = new Overalltrans();
		Overalltrans.setOveralltransnbr(OveralltransNbr);
		Overalltrans.setOveralltransstatus(OveralltransState);
		Overalltrans.setCheckstatus(overAllCheckState);
		try {
			if (OveralltransDAO.updateByPrimaryKeySelective(Overalltrans) == 0) {
				throw new PeRuntimeException(
						"Update Overalltrans Failed for unknown reason.");
			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Update Overalltrans Table Failed.", e);
		}
	}
	
	
	/**
	 * 通过上游交易号得到上游交易列表
	 * @param UpperSysRtxnNbr
	 * @return
	 */
	public static List<Overalltrans> getOveralltrans(String UpperSysRtxnNbr) {
		OveralltransExample example = new OveralltransExample();
		example.createCriteria().andUppertransnbrEqualTo(UpperSysRtxnNbr);
		example.setOrderByClause("Overalltransnbr Desc");
		List<Overalltrans> Overalltrans;
		try {
			Overalltrans = OveralltransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Overalltrans.isEmpty()) {
//			throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
//					+ fundChannelCd);
		}
		return Overalltrans;
	}


	/**
	 * 获得电子账户下发文件信息
	 * 
	 * @param fundChannelCd
	 * @param checkDate
	 * @return
	 */
	public static List<IssueFile> getEAccountIssueFile(Date checkDate,
			String uppSysNbr) {
		Overalltrans record = new Overalltrans();
		record.setTransdate(checkDate);
		record.setUppersysnbr(uppSysNbr);
		return getSqlMap().queryForList("Overalltrans.getEAccountIssueFile",record);
	}

	/**
	 * 获得其它下发文件信息
	 * 
	 * @param fundChannelCd
	 * @param checkDate
	 * @return
	 */
	public static List<IssueFile> getOtherIssueFile(Date checkDate,
			String uppSysNbr) {
		Overalltrans record = new Overalltrans();
		record.setTransdate(checkDate);
		record.setUppersysnbr(uppSysNbr);
		return getSqlMap().queryForList("Overalltrans.getOtherIssueFile", record);
	}
	
	
	public static int updateOveralltranshistCheckState(String checkState) {
		CheckDataPrepare record=new CheckDataPrepare();
		record.setCheckState(checkState);
		int rows = getSqlMap().update("Overalltrans.updateOveralltranshistCheckState", record);
		return rows;
	}
	
	/**
	 * 将清算日期当天的总交易流水表OverallTrans移植到历史表OverallTransHist，同时将对账状态更改为待对账
	 * 
	 * @author Chen Chao
	 */
	public static void insertOverralltransToHist(String checkStatus,String checkdataFlag) {
		OveralltransExample example = new OveralltransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus)
			.andCheckdataflagEqualTo(checkdataFlag);
		try {
			List<Overalltrans> overalltransList = OveralltransDAO.selectByExample(example);
			for(Overalltrans overalltrans : overalltransList) {
				getSqlMap().insert("OVERALLTRANSEXTEND.insertOverralltransToHist",overalltrans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateOverrallTransForCheck(String checkDataFlag,String transStatus,String checkStatus) {
		Overalltrans record=new Overalltrans();
		record.setCheckdataflag(checkDataFlag);
		record.setOveralltransstatus(transStatus);
		record.setCheckstatus(checkStatus);
		getSqlMap().insert("OVERALLTRANSEXTEND.updateOverrallTransForCheck",record);
	}
}
