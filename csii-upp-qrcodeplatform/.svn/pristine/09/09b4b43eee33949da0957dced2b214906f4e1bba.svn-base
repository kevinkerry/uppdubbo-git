package com.csii.upp.qrcodeplatform.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author hanlin
 * @description 
 *
 */

@Repository("sqlOperation")
public class SqlOperationImpl implements SqlOperation {
	
	protected  Logger log = LoggerFactory.getLogger(this.getClass());
	
	private DefaultSqlSessionFactory sqlSessionFactory;

	public DefaultSqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(DefaultSqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Object queryForObject(String id, Object params) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sql=sqlSessionFactory.openSession();
		Object obj=null;
		try{
			obj=sql.selectOne(id, params);
		}catch(Exception e){
			log.debug("数据库操作异常");
			throw e;
		}finally {
			sql.close();
		}
		return obj;
	}

	public <T> List<T> queryForList(String id, Object params) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sql=sqlSessionFactory.openSession();
		List<T>  list=null;
		try{
			list=sql.selectList(id, params);
		}catch(Exception e){
			log.debug("数据库操作异常");
			throw e;
		}finally {
			sql.close();
		}
		
		return list;
	}

	public int delete(String id, Object params) {
		// TODO Auto-generated method stub
		SqlSession sql=sqlSessionFactory.openSession();
		int count = sql.delete(id, params);
		try{
			sql.commit();
		}catch(Exception e){
			sql.rollback();
		}finally {
			sql.close();
		}
		
		return count;
	}

	public int update(String id, Object params) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sql=sqlSessionFactory.openSession();
		int count = sql.update(id, params);
		try{
			sql.commit();
		}catch(Exception e){
			sql.rollback();
			throw e;
		}finally {
			sql.close();
		}
		return count;
	}

	public int insert(String id, Object params) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sql=sqlSessionFactory.openSession();
		int count = sql.insert(id, params);
		try{
			sql.commit();
		}catch(Exception e){
			sql.rollback();
			throw e;
		}finally {
			sql.close();
		}
		return count;
	}
	/*
	 * (non-Javadoc)
	 * @see com.csii.Dao.SqlOperation#BatchOperation(java.lang.String[], java.util.Map)
	 * 关联处理的回滚
	 */
	public void BatchOperation(String[] id, Map params) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sql=sqlSessionFactory.openSession();
		for(int i=0;i<id.length;i++){
			sql.update(id[i], params.get(id[i]));
		}
		try{
			sql.commit();
		}catch(Exception e){
			sql.rollback();
			throw e;
		}finally {
			sql.close();
		}
	}
}
