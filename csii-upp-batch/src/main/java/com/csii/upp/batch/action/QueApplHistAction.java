package com.csii.upp.batch.action;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.generate.QueapplhistDAO;

public class QueApplHistAction extends BaseAction {

	@Override
	public void execute(Context context) throws PeException {
		long queNbr = context.getLong("quenbr");
		String runDate = context.getString("rundate");

		String pageSize1 = context.getString("pageSize");// 一页显示多少行*
		String pageNum1 = context.getString("pageNum");// 当前页数
		Integer pageNum = Integer.parseInt(pageNum1);
		Integer pageSize = Integer.parseInt(pageSize1);
//		Integer pageNumStart = null;
//		Integer pageNumEnd = null;
//		if (null != pageSize && null != pageNum) {
//			pageNumStart = Integer.parseInt(pageSize) * (Integer.parseInt(pageNum) - 1);
//			pageNumEnd = Integer.parseInt(pageSize) * Integer.parseInt(pageNum);
//		}
		Map<String, Object> reqMap = new HashMap<String, Object>();

		reqMap.put("pageNum", pageNum-1);
		reqMap.put("pageSize", pageSize);
		reqMap.put("queNbr", queNbr);
		reqMap.put("runDate", runDate);

		List<Map<String, Object>> queApplHistList;
		try {
			queApplHistList = QueapplhistDAO.selectByExampleWithBLOBs(reqMap);
			context.setData("queApplHistList", queApplHistList);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	private List<Map<String, Object>> convertCLOB2String(List<Map<String, Object>> listMap) {
		List<Map<String, Object>> retListMap = new LinkedList<Map<String, Object>>();
		try {
			if (listMap != null && listMap.size() > 0) {
				for (Map<String, Object> map : listMap) {
					Clob clob = (Clob) map.get("detail");
					String clobStr = clob == null ? "" : clob.getSubString(1, (int) clob.length());
					map.put("detail", clobStr);
					retListMap.add(map);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return retListMap;
	}
}
