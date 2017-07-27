package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.OverallrequestregDAO;
import com.csii.upp.dto.generate.OverallrequestregExample;

public class OverallrequestregExtendDAO extends BasePayDAO {
	public static int getOverallrequestregCnt(String upperrtxnnbr,
			Date upperrtxndate, String uppersysnbr) {
		OverallrequestregExample example = new OverallrequestregExample();
		example.createCriteria().andUppertransnbrEqualTo(upperrtxnnbr)
				.andUppertransdateEqualTo(upperrtxndate);
		int count = 0;
		try {
			count = OverallrequestregDAO.countByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("get Overallrequestreg Table Failed.",
					e);
		}
		return count;
	}
}
