package com.csii.upp.qrcodeplatform.base.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;

public class SqlMapClientFactoryBean extends
		org.springframework.orm.ibatis.SqlMapClientFactoryBean {
	private static final Log log = LogFactory
			.getLog(SqlMapClientFactoryBean.class);

	private SqlExecutor sqlExecutor;

	public SqlExecutor getSqlExecutor() {
		return sqlExecutor;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected SqlMapClient buildSqlMapClient(Resource[] configLocations,
			Resource[] mappingLocations, Properties properties)
			throws IOException {
		SqlMapClient sqlMapClient = super.buildSqlMapClient(configLocations,
				mappingLocations, properties);
		SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sqlMapClient)
				.getDelegate();

		// 将MappedStatement的baseCacheKey统一赋值为0，以满足集群环境的需要。
		// 在Ibatis的原有代码中baseCacheKey的值是动态的，这就导同样的查询语句在不同的机器上得到的key不一样，缓存无法复用。
		Iterator i = delegate.getMappedStatementNames();
		while (i.hasNext()) {
			String s = (String) i.next();
			MappedStatement ms = delegate.getMappedStatement(s);
			ms.setBaseCacheKey(0);
		}

		// 将自定义的sqlExecutor付给SqlMapExecutorDelegate,lubiao
		setFieldValue(delegate, "sqlExecutor", sqlExecutor);

		return sqlMapClient;
	}

	private void setFieldValue(Object target, String name, Object value) {
		Class targetClass = target.getClass();
		Field field;
		try {
			field = targetClass.getDeclaredField(name);
			if (!Modifier.isPublic(field.getModifiers())) {
				field.setAccessible(true);
			}
			field.set(target, value);
		} catch (Exception e) {
			log.error(e);
		}
	}
}
