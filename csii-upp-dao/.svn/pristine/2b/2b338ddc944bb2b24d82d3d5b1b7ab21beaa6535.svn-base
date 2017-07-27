package com.csii.upp.dao.extend;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;

public class MeracctinfoExtendDAO extends BasePayDAO {
	
	public static List<Map<String, Object>> getMerAcctInfoExtend(Date clearDate, String settleStatus) {
		Map map = new HashMap();
		map.put("ClearDate", clearDate);
		map.put("SettleStatus", settleStatus);
		return getSqlMap().queryForList("MERACCTINFOEXTEND.getMerAcctInfoExtend",map);
	}
	
	public static List<Map<String, Object>> getMerAcctInfoExtend1(Date clearDate, String settleStatus) {
		Map map = new HashMap();
		map.put("ClearDate", clearDate);
		map.put("SettleStatus", settleStatus);
		return getSqlMap().queryForList("MERACCTINFOEXTEND.getMerAcctInfoExtend1",map);
	}	
}
