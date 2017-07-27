
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author sheliang 商户信息同步
 */
public class ReqSyhMerDate extends ReqFundProHead {

	public ReqSyhMerDate(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.SynMerInfo);
		this.setProxySynType(data.getProxySynType());
		this.setProxySynStatus(data.getProxySynStatus());
		this.setSubMerchantId(data.getSubMerchantId());
		this.setMertName(data.getMertName());
		this.setMerShortName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
		this.setContactName(data.getContactName());
		this.setContactMobile(data.getContactMobile());
		this.setContactPhone(data.getContactPhone());
		this.setContactEmail(data.getContactEmail());
		this.setBusiness(data.getBusiness());
		this.setMerRemark(data.getMerRemark());;
		
	}
	private String subMerchantId;
	private String proxySynType;//同步标志
	private String proxySynStatus;//同步状态
	private String mertName;//二维码商户名
	private String merShortName;
	private String servicePhone;
	private String contactName;
	private String contactMobile;
	private String contactPhone;
	private String contactEmail;
	private String business;
	private String merRemark;
	
	public String getProxySynType() {
		return proxySynType;
	}
	public void setProxySynType(String proxySynType) {
		this.proxySynType = proxySynType;
	}
	public String getSubMerchantId() {
		return subMerchantId;
	}
	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}
	public String getMerShortName() {
		return merShortName;
	}
	public void setMerShortName(String merShortName) {
		this.merShortName = merShortName;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getMerRemark() {
		return merRemark;
	}
	public void setMerRemark(String merRemark) {
		this.merRemark = merRemark;
	}
	public String getProxySynStatus() {
		return proxySynStatus;
	}
	public void setProxySynStatus(String proxySynStatus) {
		this.proxySynStatus = proxySynStatus;
	}
	public String getMertName() {
		return mertName;
	}
	public void setMertName(String mertName) {
		this.mertName = mertName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
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
	
	
}
