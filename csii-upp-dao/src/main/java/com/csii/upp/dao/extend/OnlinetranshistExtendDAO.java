package com.csii.upp.dao.extend;

import java.util.Map;

import com.csii.upp.dao.BasePayDAO;

public class OnlinetranshistExtendDAO extends BasePayDAO {
	/**
	 * move onlinetranshist to onlinetranshistall
	 * @author chen chao
	 */
	public static void insertOnlinetranshistToAll(Map<String, Object> map) {
		getSqlMap().insert("ONLINETRANSHISTEXTEND.insertOnlinetranshistToAll", map);
	}
}
