package com.csii.upp.dao.dialect;

public class MySQLDialect implements Dialect {
	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, int limit) {
		StringBuilder pageStr = new StringBuilder();

		pageStr.append("select count_num.*, (select count(1) from (");
		pageStr.append(sql.trim());
		pageStr.append(") count_table ) totalnum_ from (");
		pageStr.append(sql.trim());
		pageStr.append(" ) count_num limit ");

		if (offset > 0) {
			pageStr.append(offset).append(',');
		}

		return pageStr.append(limit).toString();
	}
}
