package com.csii.upp.dao.extend;

import java.util.Date;

import com.csii.upp.dao.BasePayDAO;

public class FundchannelcleartranshistExtendDAO extends BasePayDAO {
	
	public static void insertFundchannelcleartranshistToAll(Date checkDate) {
		getSqlMap().insert("FUNDCHANNELCLEARTRANSHISTEXTEND.insertFundchannelcleartranshistToAll",checkDate);
	}
}
