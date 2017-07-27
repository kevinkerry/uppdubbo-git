package com.csii.upp.dto.router.unionpay;

import com.csii.upp.constant.CyberTypCd;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 * 交易状态查询类交易
 * 
 * @author zhubenle
 *
 */
public class ReqQueryStateForRefoundTrans extends ReqUnionPayHead {
	private String queryId; // 交易查询流水号

	public ReqQueryStateForRefoundTrans(InputFundData data) {
		super(data);
		//目前如果不是他行网银退货就默认是快捷退货
		this.setBizType(CyberTypCd.OUR.equals(data.getCyberTypCd()) ? "000201"
				: (CyberTypCd.OTH.equals(data.getCyberTypCd()) ? "000202" : "000301"));
		this.setTxnType("00");
		this.setTxnTime(DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
		this.setMerId("929331053110008");
		this.setTxnSubType("00");
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}	

}
