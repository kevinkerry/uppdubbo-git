package com.csii.upp.dto.router.alipacode;

import com.csii.upp.constant.QrCodePreTransCode;
import com.csii.upp.dto.extend.InputFundData;
/**
 * 支付宝信息同步请求类
 * @author shell
 *
 */
public class ReqAlipayCodeUpdInfo extends ReqAlipayCodePreHead {
	
	private String merId;
	private String name;
	private String aliasName;
	private String servicePhone;
	private String contactName;
	private String alipayMerchantId;
	
	public ReqAlipayCodeUpdInfo(InputFundData data) {
		super(data);
		this.setTxnCode(QrCodePreTransCode.AlipayCodeUpdInfo);
		this.setMerId(data.getSubMerchantId());
		this.setAliasName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
		this.setContactName(data.getContactName());
		this.setAlipayMerchantId(data.getAlipayMerchantId());
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

	public String getAlipayMerchantId() {
		return alipayMerchantId;
	}

	public void setAlipayMerchantId(String alipayMerchantId) {
		this.alipayMerchantId = alipayMerchantId;
	}

	
}
