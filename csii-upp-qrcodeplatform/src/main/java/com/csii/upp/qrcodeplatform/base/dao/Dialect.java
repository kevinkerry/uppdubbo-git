package com.csii.upp.qrcodeplatform.base.dao;

public interface Dialect {
	boolean supportsLimit();

	String getLimitString(String query, int offset, int limit);
}
