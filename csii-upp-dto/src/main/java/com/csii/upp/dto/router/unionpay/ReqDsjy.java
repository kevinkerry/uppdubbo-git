package com.csii.upp.dto.router.unionpay;

import java.math.BigDecimal;

import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.UnionpayTransCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.StringUtil;

/**
 * 
 * 银联代收请求信息
 *
 * @author  yanyiming
 * <p>
 *   Created on 2015年11月26日
 *   Modification history	
 *   {add your history}
 * </p>
 * <p>
 *   IBS Product Expert Group, CSII
 *   Powered by CSII PowerEngine 6.0
 * </p>
 * @version 1.0
 * @since 1.0
 */
public class ReqDsjy extends ReqUnionPayHead {

	private String subMerId;		//二级商户代码
	private String subMerName;		//二级商户全称
	private String subMerAbbr;		//二级商户简称
	private String accType;			//账号类型
	private String accNo;			//账号
	private String txnAmt;			//交易金额
	private String currencyCode;	//交易币种
	private String customerNm;		//姓名
	private String phoneNo;			//手机号码
	private String certifTp;		//证件类型
	private String certifId;		//证件号码

	/**
	 * @param data
	 */
	public ReqDsjy(InputFundData data) {
		super(data);
		this.setTransCode(UnionpayTransCode.ACP011);
		setMerId(ConstUnionPay.PAY_MER_ID);
//		setSubMerId(null);
//		setSubMerName(null);
//		setSubMerAbbr(null);
//		setAccType("02");
		setAccNo(data.getPayeracctnbr());
		setTxnAmt(StringUtil.BigDel2Str(data.getTransamt().multiply(new BigDecimal("100"))));
		setCustomerNm(data.getPayername());
		setPhoneNo(data.getPayerphoneno());
		setCertifTp(data.getPayeridtypcd());
		setCertifId(data.getPayeridnbr());
	}

	/**
	 * @return the subMerId
	 */
	public String getSubMerId() {
		return subMerId;
	}

	/**
	 * @param subMerId the subMerId to set
	 */
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	/**
	 * @return the subMerName
	 */
	public String getSubMerName() {
		return subMerName;
	}

	/**
	 * @param subMerName the subMerName to set
	 */
	public void setSubMerName(String subMerName) {
		this.subMerName = subMerName;
	}

	/**
	 * @return the subMerAbbr
	 */
	public String getSubMerAbbr() {
		return subMerAbbr;
	}

	/**
	 * @param subMerAbbr the subMerAbbr to set
	 */
	public void setSubMerAbbr(String subMerAbbr) {
		this.subMerAbbr = subMerAbbr;
	}

	/**
	 * @return the accType
	 */
	public String getAccType() {
		return accType;
	}

	/**
	 * @param accType the accType to set
	 */
	public void setAccType(String accType) {
		this.accType = accType;
	}

	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * @param accNo the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * @return the txnAmt
	 */
	public String getTxnAmt() {
		return txnAmt;
	}

	/**
	 * @param txnAmt the txnAmt to set
	 */
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the customerNm
	 */
	public String getCustomerNm() {
		return customerNm;
	}

	/**
	 * @param customerNm the customerNm to set
	 */
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the certifTp
	 */
	public String getCertifTp() {
		return certifTp;
	}

	/**
	 * @param certifTp the certifTp to set
	 */
	public void setCertifTp(String certifTp) {
		if("0".equals(certifTp))
			certifTp = "01";
		this.certifTp = certifTp;
	}

	/**
	 * @return the certifId
	 */
	public String getCertifId() {
		return certifId;
	}

	/**
	 * @param certifId the certifId to set
	 */
	public void setCertifId(String certifId) {
		this.certifId = certifId;
	}
	
}
