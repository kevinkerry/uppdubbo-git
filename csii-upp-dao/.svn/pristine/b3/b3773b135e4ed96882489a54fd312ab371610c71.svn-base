package com.csii.upp.dao.extend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dao.BasePayDAO;


public class BatchclearsubtransExtendDAO extends BasePayDAO {

	/**
	 * 通过对账状态获得内部待清算资金流水列表
	 * @param checkStatus
	 * @return
	 */
	public static List<Map> getBatchclearsubtransByCheckStatus(String interalflag,String checkstatus,String mernbr) {
		Map map = new HashMap();
		map.put("interalflag", interalflag);
		map.put("checkstatus", checkstatus);
		map.put("mernbr", mernbr);
		return getSqlMap().queryForList("BATCHCLEARSUBTRANSEXTEND.getBatchClearSubTrans", map);		

	}
}
