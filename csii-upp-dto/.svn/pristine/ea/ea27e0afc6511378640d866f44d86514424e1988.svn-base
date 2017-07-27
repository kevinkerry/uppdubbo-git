package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;

/**
 * 客户鉴权请求类(POC测试使用)
 * 
 * @author 颜祎名
 *
 */
public class ReqCustomAuthen extends ReqPaymHead {

	// 商户号
	private String merId;
	// 客户姓名
	private String customName;
	// 证件类型
	private String certTyp;
	// 证件编号
	private String certNo;
	// 卡类型
	private String cardTyp;
	// 卡号
	private String cardNo;
	// 客户类型
	private String customTyp;
	// 客户手机号
	private String phoneNo;
	// 备注
	private String remark;

	public ReqCustomAuthen(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.CustomAuthen);
		this.setCustomName(data.getCustomName());
		this.setCertTyp(data.getCertTyp());
		this.setCertNo(data.getCertNo());
		this.setCardTyp(data.getCardTyp());
		this.setCardNo(data.getCardNo());
		this.setCustomTyp(data.getCustomTyp());
		this.setPhoneNo(data.getPhoneNo());
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCertTyp() {
		return certTyp;
	}

	public void setCertTyp(String certTyp) {
		this.certTyp = certTyp;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCardTyp() {
		return cardTyp;
	}

	public void setCardTyp(String cardTyp) {
		this.cardTyp = cardTyp;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCustomTyp() {
		return customTyp;
	}

	public void setCustomTyp(String customTyp) {
		this.customTyp = customTyp;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
