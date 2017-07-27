package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.List;

import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dto.extend.QueapplhistAndAppl;

public class QueapplhistExtendDAO extends BasePayDAO {
	@SuppressWarnings("unchecked")
    public static List<QueapplhistAndAppl> selectByExampleWithBLOBs(QueapplhistAndAppl req)
			throws SQLException {
		List<QueapplhistAndAppl> list = getSqlMap().queryForList(
				"QUEAPPLHISTADNAPPL.selectByPrimaryKey", req);
		return list;
	}
}