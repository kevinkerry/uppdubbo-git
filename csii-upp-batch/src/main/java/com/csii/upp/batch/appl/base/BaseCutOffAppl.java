/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-batch
 * create: 2016年1月20日 下午4:06:33
 * vc: $Id: $
 */
package com.csii.upp.batch.appl.base;

import java.util.Map;

import com.csii.upp.dto.router.RespSysHead;

/**
 * TODO 请填写注释.
 * @author chen chao 
 * @version $Revision:$
 */
public abstract class BaseCutOffAppl extends BaseAppl {
	/**
	 * 取渠道号
	 */
	protected abstract String getFundchannelCode();
	/**
	 * 查询下游日期
	 */
	protected abstract RespSysHead queryDownPostDate() throws Exception;
	/**
	 * 查询下有日期后续操作
	 */
	protected abstract void stepAfterQueryDownSysDate() throws Exception;
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
	
		this.stepAfterQueryDownSysDate();
	}

}
