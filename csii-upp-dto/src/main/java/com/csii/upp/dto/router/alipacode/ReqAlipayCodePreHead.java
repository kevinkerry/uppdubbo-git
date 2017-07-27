package com.csii.upp.dto.router.alipacode;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.ReqAppHead;
import com.csii.upp.util.StringUtil;
/**
 * 二维码支付宝请求头
 * @author wy
 *
 */
public class ReqAlipayCodePreHead extends ReqAppHead {
	private String txnCode;
	private String channel;
	private String payAccessType;
	private String currencyType;
	public ReqAlipayCodePreHead(InputFundData data) {
		super(data);
		this.setTime(this.getReqTime().substring(8, 14));
		this.setChnlId(FundChannelCode.PAY);
		//支付平台渠道 6001：POS 6002：支付平台 6003：手机银行
		this.setChannel("6002");
		//支付宝接入 01:本行 02：微信 03：支付宝
		this.setPayAccessType("03");
		//币种 156：人民币
		this.setCurrencyType("156");
		data.setFundchannelcode(FundChannelCode.ALIPAY);
		this.setSrvChnlId(FundChannelCode.ALIPAY);
		
	}
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPayAccessType() {
		return payAccessType;
	}
	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	protected String formatAmt(BigDecimal transamt){
		String formattransamt=StringUtil.BigDel2Str(transamt.setScale(2, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(100)));
		return StringUtil.formatLeftStr(formattransamt, 12);
	}
}
