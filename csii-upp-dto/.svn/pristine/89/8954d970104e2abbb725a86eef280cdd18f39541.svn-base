package com.csii.upp.dto.router.wechatcode;

import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;
/**
 * 微信退货状态查询请求类
 * @author wy
 *
 */
public class ReqWechatCodeRefStatus extends ReqWeChatCodeHead {
	private String initOrderNumber;
	private String initOrderTime;
	private String initOutRequestNo;
	private String transType;
	private String merId;
	public ReqWechatCodeRefStatus(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.QueryWechatCodeRefStatus);
		this.setInitOrderNumber(data.getInnerfundtransnbr());
		this.setInitOrderTime((DateUtil.Date_To_DateTimeFormat(data.getTranstime(), DateFormatCode.DATETIME_FORMATTER3)));
		this.setTransType("37");
		this.setInitOutRequestNo(data.getOveralltransnbr());
		this.setMerId(data.getPayeeacctnbr());
	
	}
	
	public String getInitOrderNumber() {
		return initOrderNumber;
	}

	public void setInitOrderNumber(String initOrderNumber) {
		this.initOrderNumber = initOrderNumber;
	}

	public String getInitOrderTime() {
		return initOrderTime;
	}

	public void setInitOrderTime(String initOrderTime) {
		this.initOrderTime = initOrderTime;
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
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	
}
