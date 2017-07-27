package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
/**
 * 支付宝信息同步请求类
 * @author shell
 *
 */
public class ReqAlipayCodeSynInfo extends ReqAlipayCodePreHead {
	
	private String merId;
	private String name;
	private String aliasName;
	private String servicePhone;
	private String contactName;
	private String contactPhone;
	private String contactMobile;
	private String contactEmail;
	private String categoryld;
	private String memo;
	
	public ReqAlipayCodeSynInfo(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.AlipayCodeSynInfo);
		this.setMerId(data.getSubMerchantId());
		this.setName(data.getMertName());
		this.setAliasName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
		this.setContactName(data.getContactName());
		this.setContactPhone(data.getContactPhone());
		this.setContactMobile(data.getContactMobile());
		this.setContactEmail(data.getContactEmail());
		this.setCategoryld(data.getBusiness());
		this.setMemo(data.getMerRemark());
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
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

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getCategoryld() {
		return categoryld;
	}

	public void setCategoryld(String categoryld) {
		this.categoryld = categoryld;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
