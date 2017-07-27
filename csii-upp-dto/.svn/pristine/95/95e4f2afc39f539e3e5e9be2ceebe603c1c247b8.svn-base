package com.csii.upp.dto.router.wechatcode;

import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
/**
 * 支付宝信息同步请求类
 * @author shell
 *
 */
public class ReqWeChatCodeSynInfo extends ReqWeChatCodeHead {
	
	private String mchtName;		//商户名称
	private String mchtShortName;	//商户简称
	private String servicePhone;	//客服电话
	private String contactName;		//联系人名称
	private String contactPhone;	//联系人电话
	private String contactEmail;	//联系人邮箱
	private String business;		//经营类目
	private String mchtRemark;		//商户备注
	
	public ReqWeChatCodeSynInfo(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.WeChatCodeSynInfo);
		this.setMchtName(data.getMertName());
		this.setMchtShortName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
		this.setContactName(data.getContactName());
		this.setContactPhone(data.getContactPhone());
		this.setContactEmail(data.getContactEmail());
		this.setBusiness(data.getBusiness());
		this.setMchtRemark(data.getMerRemark());
	}

	public String getMchtName() {
		return mchtName;
	}

	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	public String getMchtShortName() {
		return mchtShortName;
	}

	public void setMchtShortName(String mchtShortName) {
		this.mchtShortName = mchtShortName;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getMchtRemark() {
		return mchtRemark;
	}

	public void setMchtRemark(String mchtRemark) {
		this.mchtRemark = mchtRemark;
	}

	
}
