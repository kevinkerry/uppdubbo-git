
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 * @author sheliang 商户信息同步(更新)
 */
public class ReqUpdMerDate extends ReqFundProHead {

	public ReqUpdMerDate(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.UpdMerInfo);
		this.setProxySynType(data.getProxySynType());
		this.setSubMerchantId(data.getSubMerchantId());
		this.setMertName(data.getMertName());
		this.setMerShortName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
		this.setContactName(data.getContactName());
		
	}
	private String subMerchantId;
	private String proxySynType;//同步标志
	private String mertName;//二维码商户名
	private String merShortName;
	private String servicePhone;
	private String contactName;
	
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
	
	
}
