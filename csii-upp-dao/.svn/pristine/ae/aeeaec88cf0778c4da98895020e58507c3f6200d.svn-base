package com.csii.upp.dao.extend;

import java.util.Date;

import com.csii.upp.dao.BasePayDAO;

public class InnerfundtransHistExtendDAO extends BasePayDAO {
	public static String getOrigInnerRtxnNbrByinner(String innerRtxnnbr){
		return (String) getSqlMap().queryForObject("InnerfundtransHISTEXTEND.getOrigInnerRtxnNbr", innerRtxnnbr);
	}
	
	/**
	 * 将InnerFundTransHist （平台资金流水历史表）的360天之前的非待对账的数据移植到平台资金流水历史All表InnerFundTransHistAll
	 * @author chen chao
	 */
	public static void insertInnerfundtranshistToAll(Date transDate) {
		getSqlMap().insert("INNERFUNDTRANSHISTEXTEND.insertInnerfundtranshistToAll", transDate);
	}
}
