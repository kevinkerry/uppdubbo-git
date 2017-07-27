package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
/**
 * 支付宝退货状态查询请求类
 * @author wy
 *
 */
public class ReqAlipayCodeRefStatus extends ReqAlipayCodePreHead {
	private String initOrderTime;
	private String initOrderNumber;
	private String initOutRequestNo;
	private String transType;
	public ReqAlipayCodeRefStatus(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.QueryAlipayCodeRefStatus);
		this.setInitOrderNumber(data.getInnerfundtransnbr());
		this.setInitOrderTime((DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3)));
		this.setTransType("37");
		this.setInitOutRequestNo(data.getOveralltransnbr());
	
	}
	
	public String getInitOrderTime() {
		return initOrderTime;
	}

	public void setInitOrderTime(String initOrderTime) {
		this.initOrderTime = initOrderTime;
	}

	public String getInitOrderNumber() {
		return initOrderNumber;
	}

	public void setInitOrderNumber(String initOrderNumber) {
		this.initOrderNumber = initOrderNumber;
	}

	public String getInitOutRequestNo() {
		return initOutRequestNo;
	}
	public void setInitOutRequestNo(String initOutRequestNo) {
		this.initOutRequestNo = initOutRequestNo;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}

	
}
