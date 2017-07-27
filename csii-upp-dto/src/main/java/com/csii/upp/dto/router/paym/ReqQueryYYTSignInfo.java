package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
* 类说明
* @auther fgq email:f_xust@163.com
* @version 创建时间：2016年10月25日 上午11:19:58
* 
*/
public class ReqQueryYYTSignInfo extends ReqPaymHead{
	
	public ReqQueryYYTSignInfo(InputPaygateData data){
		super(data);
		this.setTransTypCd(data.getTransTypCd());
		this.setTransCode(PaymTransCode.QueryYYTSigninfo);
	}
	private String transTypCd;
	public String getTransTypCd() {
		return transTypCd;
	}
	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}
}
