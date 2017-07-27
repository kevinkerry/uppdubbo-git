package com.csii.upp.dto.router.unionpay;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.idto.IDto;

/**
 * 身份信息
 * @author WHD
 *
 */
public class CustomerInfoObj implements IDto{
	public CustomerInfoObj(InputFundData input,String payDirection){
		this.certifTp = input.getPayeridtypcd();
		this.certifId = input.getPayeridnbr();
		if(payDirection.equals(ConstUnionPay.PAY_IN)){
			this.customerNm = input.getPayername();
		}else{
			this.customerNm = input.getPayeename();
		}
		this.phoneNo = input.getPayerphoneno();
		this.smsCode = input.getSmsCode();
	}
	
	
	private String certifTp;
	private String certifId;
	private String customerNm;
	private String phoneNo;
	private String smsCode;
	private String pin;
	private String cvn2;
	private String expired;
	public String getCertifTp() {
		return certifTp;
	}
	public void setCertifTp(String certifTp) {
		this.certifTp = certifTp;
	}
	public String getCertifId() {
		return certifId;
	}
	public void setCertifId(String certifId) {
		this.certifId = certifId;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCvn2() {
		return cvn2;
	}
	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	
	 
	
	
}
