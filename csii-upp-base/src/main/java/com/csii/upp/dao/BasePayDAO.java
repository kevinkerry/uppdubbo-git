/*
 * @(#)testDao.java	1.0 2015-12-26 下午5:27:39
 *
 * Copyright 2004-2010 Client Server International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.csii.upp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.transaction.support.TransactionTemplate;

import com.csii.pe.config.support.SqlMapAware;
import com.csii.pe.config.support.TransactionTemplateAware;

/**
 * {type specification, must edit}
 *
 * @author  DreamsHunter {must edit, use true name}
 * <p>
 *   Created on 2015-12-26
 *   Modification history	
 *   {add your history}
 * </p>
 * <p>
 *   IBS Product Expert Group, CSII
 *   Powered by CSII PowerEngine 6.0
 * </p>
 * @version 1.0
 * @since 1.0
 */
public class BasePayDAO  implements SqlMapAware ,TransactionTemplateAware{
	
    public void setTransactionTemplate(TransactionTemplate transactionTemplate)
    {
        this.transactionTemplate = transactionTemplate;
    }

    public static TransactionTemplate getTransactionTemplate()
    {
        return transactionTemplate;
    }
    private static TransactionTemplate transactionTemplate;
    
	/**
	 * @fields sqlmap sql配置文件调用对象
	 */
	private static SqlMapClientOperations sqlmap;

	/**
	 * @description 获取sqlmap
	 * @return sqlmap
	 */
	public static SqlMapClientOperations getSqlMap() {
		return sqlmap;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.csii.pe.config.support.SqlMapAware#setSqlMap(org.springframework.orm.ibatis.SqlMapClientOperations)
	 */
	public void setSqlMap(SqlMapClientOperations sqlmapclientoperations) {
		sqlmap = sqlmapclientoperations;
	}

	/**
	 * @description 对象查询
	 * @param s
	 *            sqlmap标识
	 * @return 查询结果对象
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static Object queryForObject(String s) throws DataAccessException {
		return getSqlMap().queryForObject(s);
	}

	/**
	 * @description 对象查询
	 * @param s
	 *            sqlmap标识
	 * @param obj
	 *            调用参数
	 * @return 查询结果对象
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static Object queryForObject(String s, Object obj)
			throws DataAccessException {
		return getSqlMap().queryForObject(s, obj);
	}

	/**
	 * @description 对象集合查询
	 * @param s
	 *            sqlmap标识
	 * @return 查询结果对象集合
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static <T> List<T> queryForList(String s) throws DataAccessException {
		return getSqlMap().queryForList(s);
	}

	/**
	 * @description 对象集合查询
	 * @param s
	 *            sqlmap标识
	 * @param obj
	 *            调用参数
	 * @return 查询结果对象集合
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static <T> List<T> queryForList(String s, Object obj)
			throws DataAccessException {
		return getSqlMap().queryForList(s, obj);
	}

	/**
	 * @description 对象集合查询
	 * @param s
	 *            sqlmap标识
	 * @param obj
	 *            调用参数
	 * @param i
	 *            页码
	 * @param j
	 *            页内记录数
	 * @return 查询结果对象集合
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static List<?> queryForList(String s, Object obj, int i, int j)
			throws DataAccessException {
		return getSqlMap().queryForList(s, obj, i, j);
	}

	/**
	 * @description 插入数据记录
	 * @param s
	 *            sqlmap标识
	 * @param obj
	 *            调用参数
	 * @return 可返回主键信息
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static Object insert(String s, Object obj)
			throws DataAccessException {
		return getSqlMap().insert(s, obj);
	}

	/**
	 * @description 更新数据记录
	 * @param s
	 *            sqlmap标识
	 * @param obj
	 *            调用参数
	 * @return 更新记录条数
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static int update(String s, Object obj) throws DataAccessException {
		return getSqlMap().update(s, obj);
	}

	/**
	 * @description 删除数据记录
	 * @param s
	 *            sqlmap标识
	 * @param obj
	 *            调用参数
	 * @return 更新记录条数
	 * @throws DataAccessException
	 *             数据库调用异常
	 */
	public static int delete(String s, Object obj) throws DataAccessException {
		return getSqlMap().delete(s, obj);
	}
}
