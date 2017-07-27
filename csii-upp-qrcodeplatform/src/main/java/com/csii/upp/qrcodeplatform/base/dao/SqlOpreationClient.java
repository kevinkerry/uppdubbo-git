package com.csii.upp.qrcodeplatform.base.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class SqlOpreationClient{
	
	@Autowired
	private  SqlOperation sqlOperation;
	
	public SqlOperation getSqlMap() {
		return sqlOperation;
	}
	
}
