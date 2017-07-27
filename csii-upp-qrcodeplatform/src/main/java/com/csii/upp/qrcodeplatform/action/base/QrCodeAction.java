package com.csii.upp.qrcodeplatform.action.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.action.Action;
import com.csii.pe.action.Executable;
import com.csii.upp.qrcodeplatform.base.dao.SqlOperation;

public abstract class QrCodeAction implements Executable,Action
{
	
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private  SqlOperation sqlOperation;
	
	/**
	 * 获取手写sql引擎
	 */
	protected SqlOperation getSqlMap(){
		return sqlOperation;
	}
}
