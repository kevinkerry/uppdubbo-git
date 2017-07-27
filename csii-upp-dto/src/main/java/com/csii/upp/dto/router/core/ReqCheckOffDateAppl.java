/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年1月21日 上午9:55:12
 * vc: $Id: $
 */
package com.csii.upp.dto.router.core;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * TODO 请填写注释.
 * @author chen chao 
 * @version $Revision:$
 */
public class ReqCheckOffDateAppl extends ReqCoreHead {

	/**
	 * @param data
	 */
	public ReqCheckOffDateAppl(InputFundData data) {
		super(data);
//		this.ssetTransDate(data.getTransdate());
//		this.setTransTime(new Date());
		this.setTransCode(CoreTransCode.QueryHostDate);
		this.setReceiver("3101");
	}

}
