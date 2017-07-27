package com.csii.upp.qrcodeplatform.base.dao;

import java.util.List;
import java.util.Map;

public interface SqlOperation {
	
	public Object queryForObject(String id,Object params) throws Exception;
	
	public <T> List<T> queryForList(String id,Object params) throws Exception;
	
	public int delete(String id,Object params);
	
	public int update(String id,Object params) throws Exception;
	
	public int insert(String id,Object params) throws Exception;
	
	public void BatchOperation(String[] id,Map params) throws Exception;
	
}
