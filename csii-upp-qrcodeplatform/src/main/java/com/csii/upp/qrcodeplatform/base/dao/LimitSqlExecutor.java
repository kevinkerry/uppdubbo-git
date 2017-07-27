package com.csii.upp.qrcodeplatform.base.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * 分页用SqlExecutor
 * @author 李鸣扬
 *
 */
public class LimitSqlExecutor extends SqlExecutor {
	private static final Log log = LogFactory.getLog(LimitSqlExecutor.class);

	private Dialect dialect;

	private boolean enableLimit = true;

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public boolean isEnableLimit() {
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.enableLimit = enableLimit;
	}

	private boolean supportsLimit() {
		return isEnableLimit() && getDialect() != null
				&& getDialect().supportsLimit();
	}

	@Override
	public void executeQuery(StatementScope statementScope, Connection conn,
			String sql, Object[] parameters, int skipResults, int maxResults,
			RowHandlerCallback callback) throws SQLException {
		if (supportsLimit()
				&& (skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)) {
			sql = getDialect().getLimitString(sql, skipResults, maxResults);
			if (log.isDebugEnabled()) {
				log.debug(sql);
			}
			skipResults = NO_SKIPPED_RESULTS;
			maxResults = NO_MAXIMUM_RESULTS;
		}

		super.executeQuery(statementScope, conn, sql, parameters, skipResults,
				maxResults, callback);
	}

}
