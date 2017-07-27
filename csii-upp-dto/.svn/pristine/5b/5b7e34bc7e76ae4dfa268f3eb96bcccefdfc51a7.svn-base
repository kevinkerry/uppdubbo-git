package com.csii.upp.dto.router.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

public class ReqInnerAcctToCard extends ReqGeneralCharge {

	public ReqInnerAcctToCard(InputFundData data) {
		super(data);
		setTransCode(CoreTransCode.GeneralCharge);
		this.setBaseacctno(data.getPayeracctnbr()); // 电子账户
//		this.setAccttype(data.getPayeecardtype());
		this.setCardpbind("P");
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("baseacctno", data.getPayeracctnbr());
		map.put("ccy", data.getCurrencycd());
//		map.put("accttype", data.getPayeecardtype()); // 帐户类型：100-个人；300-对公
		map.put("cardpbind", "C");
		map.put("cardno", data.getPayeeacctnbr());
		map.put("trantype", "ZX01");
		map.put("pbflag", "N"); // 存折携带标记
		map.put("checkoption", "0000"); // 检查标志
		map.put("tranamt", data.getTransamt()); // 交易金额
		map.put("effectdate",
				DateUtil.Date_To_DateTimeFormat(data.getTransdate(), DateFormatCode.DATE_FORMATTER3)); // 生效日期
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		this.setTranarray(list);
	}
}