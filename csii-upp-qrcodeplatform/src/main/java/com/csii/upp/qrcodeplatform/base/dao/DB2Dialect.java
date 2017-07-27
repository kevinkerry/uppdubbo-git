package com.csii.upp.qrcodeplatform.base.dao;

public class DB2Dialect implements Dialect {

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getLimitString(String query, int offset, int limit) {
		StringBuilder pageStr = new StringBuilder();

		pageStr.append("select * from ( select row_limit.* from (");
		pageStr.append("select count_num.*, rownumber() over() as rownum_, sum(1) over() totalnum_ from (");
		pageStr.append(query.trim());
		pageStr.append(" ) count_num ");
		pageStr.append(" ) row_limit ) where rownum_ >");
		pageStr.append(offset);
		pageStr.append(" and rownum_ <= ");
		pageStr.append(limit);

		return pageStr.toString();
	}

}
