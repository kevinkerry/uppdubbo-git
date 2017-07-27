package com.csii.upp.dao.dialect;

public class OracleDialect implements Dialect {

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getLimitString(String query, int offset, int limit) {
		StringBuilder pageStr = new StringBuilder();

		pageStr.append("select * from ( select row_limit.*, rownum rownum_ from (");
		pageStr.append("select count_num.*, count(1)over() totalnum_ from (");
		pageStr.append(query.trim());
		pageStr.append(" ) count_num ");
		pageStr.append(" ) row_limit where rownum <= ");
		pageStr.append(limit);
		pageStr.append(" ) where rownum_ >");
		pageStr.append(offset);

		return pageStr.toString();
	}

}
