package com.csii.upp.dao.extend;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;

public class FundchannelExtendDAO extends BasePayDAO {
	
	/**
	 * 获得当期对账日期未对账渠道
	 * @param fundchanneltype
	 * @param checkdate
	 * @param dealCode
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int getFundchannelNoCheckedCnt(String fundchanneltype,Date checkdate,String dealCode) {
		Map map = new HashMap();
		map.put("fundchanneltype", fundchanneltype);
		map.put("checkdate", checkdate);
		map.put("dealcode", dealCode);
		return (Integer)getSqlMap().queryForObject("FUNDCHANNELEXTEND.getFundchannelNoCheckedCnt", map);
	}

}
