package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 二维码字符串生成
 * @author wy
 *
 */
public class ReqCreatQrCodeByte extends ReqPaymHead {
	private String codeStreamStr;
	private String codeTypCd;
	private String codeUrl;
	private String payType;
	private String respCode;
	private String respMessage;
	public ReqCreatQrCodeByte(InputPaygateData data){
		super(data);
		this.setTransCode(PaymTransCode.CreateCodeByte);
		this.setCodeTypCd(data.getCodetypcd());
		this.setCodeUrl(data.getCodeurl());
		this.setCodeStreamStr(data.getCodeStreamByte());
		this.setPayType(data.getScancodemode());
		this.setRespCode(data.getRespcode());
		this.setRespMessage(data.getRespMessage());
	}
	public String getCodeStreamStr() {
		return codeStreamStr;
	}
	public void setCodeStreamStr(String codeStreamStr) {
		this.codeStreamStr = codeStreamStr;
	}
	public String getCodeTypCd() {
		return codeTypCd;
	}
	public void setCodeTypCd(String codeTypCd) {
		this.codeTypCd = codeTypCd;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMessage() {
		return respMessage;
	}
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	
	
	
}
