package com.csii.upp.dto.router.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * @author JIANGXING
 * 
 */
public class ReqFundRedemption extends ReqGeneralCharge {

	public ReqFundRedemption(InputFundData data) {
		super(data);
		this.setTransCode("FRED");
		this.setBaseacctno(data.getPayeracctnbr());
		this.setAccttype(data.getPayeraccttypcd());
		this.setPassword(data.getPayercardpwd());
		this.setCardpbind("P");
		this.setPbflag("Y");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseacctno", data.getPayeeacctnbr());
		map.put("ccy", data.getCurrencycd());
		// map.put("accttype", data.getPayeraccttypcd()); // 帐户类型：100-个人；300-对公
		map.put("cardpbind", "P");
		map.put("trantype", "ZX05");
		map.put("tranamt", data.getTransamt()); // 交易金额
		map.put("effectdate",
				DateUtil.Date_To_DateTimeFormat(new Date(), "yyyyMMdd")); // 生效日期
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		this.setTranarray(list);
	}
}
