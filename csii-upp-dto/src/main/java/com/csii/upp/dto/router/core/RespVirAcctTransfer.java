/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年1月21日 上午9:58:10
 * vc: $Id: $
 */
package com.csii.upp.dto.router.core;

/**
 * 虚账户转账响应类
 * @author qgs
 *
 */
public class RespVirAcctTransfer extends RespCoreHead {
	private String transDate;

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	
}
