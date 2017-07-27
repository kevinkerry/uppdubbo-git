package com.csii.upp.dao.extend;

import java.math.BigDecimal;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;

public class CardphonelimitExtendDAO extends BasePayDAO {
	
	public static BigDecimal sumErrorTimes(Map<String, Object> map) {
		return (BigDecimal) getSqlMap().queryForObject("CARDPHONELIMITEXTEND.sumErrorTimes", map);
	}
		
}
