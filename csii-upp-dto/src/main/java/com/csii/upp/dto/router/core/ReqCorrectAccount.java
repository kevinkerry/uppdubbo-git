package com.csii.upp.dto.router.core;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.dto.extend.InputFundData;

/**
 * 冲正
 * @author LICHAO
 *
 */
public class ReqCorrectAccount extends ReqCoreHead{

	public ReqCorrectAccount(InputFundData data) {
		super(data);
		this.setTransCode(CoreTransCode.CorrectAccount);
	}
	
}
