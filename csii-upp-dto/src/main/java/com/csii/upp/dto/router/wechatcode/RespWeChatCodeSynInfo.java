package com.csii.upp.dto.router.wechatcode;

/**
 * 微信信息同步接收类
 * @author shell
 *
 */
public class RespWeChatCodeSynInfo extends RespWeChatCodePreHead {
	private String respCode;
	private String respDesc;
	private String mchId;
	private String subMchId;
	
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
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getSubMchId() {
		return subMchId;
	}
	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}
	
	
	
	
}
