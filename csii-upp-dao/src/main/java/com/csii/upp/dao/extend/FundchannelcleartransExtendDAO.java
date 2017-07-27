package com.csii.upp.dao.extend;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;

public class FundchannelcleartransExtendDAO extends BasePayDAO {
	public static List<Map> calculateTotalByMerAcctType(String channelcode,
			Date rtxndate) {
		Map parameter = new HashMap();

		parameter.put("rtxndate", rtxndate);
		parameter.put("channelcode", channelcode);

		return getSqlMap().queryForList(
				"FUNDCHANNELCLEARTRANS.calculateTotalByMerAcctType", parameter);
	}
	
	public static void insertFundchannelcleartransToHist(Map<String, Object> map) {
		getSqlMap().insert("FUNDCHANNELCLEARTRANSEXTEND.insertFundchannelcleartransToHist", map);
	}
	
	public static void deleteFundchannelcleartransBeUsed(Map<String, Object> map){
		getSqlMap().delete("FUNDCHANNELCLEARTRANSEXTEND.deleteFundchannelcleartransBeUsed", map);
	}
	
	public static List<Map> getTotalOffsetBalance(String standbooktypcd) {
		Map map = new HashMap();
		map.put("standbooktypcd", standbooktypcd);
		return getSqlMap().queryForList("FUNDCHANNELCLEARTRANSEXTEND.getTotalOffsetBalance", map);
	}
}