package com.csii.upp.dto.router.alipacode;

/**
 * 支付宝信息同步接收类
 * @author shell
 *
 */
public class RespAlipayCodeSynInfo extends RespAlipayCodePreHead {
	private String respCode;
	private String respDesc;
	private String merId;
	private String alipayMerchantId;
	
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getAlipayMerchantId() {
		return alipayMerchantId;
	}
	public void setAlipayMerchantId(String alipayMerchantId) {
		this.alipayMerchantId = alipayMerchantId;
	}
	
	
	
}
