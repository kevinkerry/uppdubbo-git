package com.csii.upp.dto.router.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 基金申购请求参数
 * 
 * @author JIANGXING
 * 
 */
public class ReqFundPurchase extends ReqGeneralCharge {

	public ReqFundPurchase(InputFundData data) {
		super(data);
		this.setTransCode("FPUR");
		this.setXtgzh(data.getInnerfundtransnbr());
		this.setCzbz("1"); // 操作标志
		this.setZydh("252"); // 摘要代号
		this.setBaseacctno(data.getPayeracctnbr()); // 电子账户
		// this.setAccttype(data.getPayeecardtype());
		this.setCardpbind("P");
		this.setPbflag("Y");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseacctno", data.getPayeeacctnbr());
		map.put("ccy", data.getCurrencycd());
		// map.put("accttype", "100"); // 帐户类型：100-个人；300-对公
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
