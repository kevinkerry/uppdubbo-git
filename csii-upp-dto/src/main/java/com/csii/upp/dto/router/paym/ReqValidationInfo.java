package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 验证信息
 * @author zgb   
 *
 */
public class ReqValidationInfo extends ReqPaymHead{

	public ReqValidationInfo(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.Validationinfo);
	}
	
}
