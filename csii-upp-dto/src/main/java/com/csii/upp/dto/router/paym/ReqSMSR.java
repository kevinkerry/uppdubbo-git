package com.csii.upp.dto.router.paym;

import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.dto.extend.InputPaygateData;
/**
 * 二级商户信息同步
 * @author qgs
 *
 */
public class ReqSMSR extends ReqPaymHead{


	public ReqSMSR(InputPaygateData data) {
		super(data);
		this.setTransCode(PaymTransCode.SMSR);
		this.setSubMerchantId(data.getSubMerchantId());
		this.setMerStatus(data.getMerStatus());
		this.setMerCifNbr(data.getMerCifNbr());
		this.setMerName(data.getMerName());
		this.setMerMngDeptNbr(data.getMerMngDeptNbr());
		this.setMerOpenDeptNbr(data.getMeropendeptnbr());
		this.setMerDevelopDeptNbr(data.getMerDevelopDeptNbr());
		this.setSettleAcctNbr(data.getSettleAcctNbr());
		this.setSettleAcctName(data.getSettleAcctName());
		this.setFeeAcctNbr(data.getFeeAcctNbr());
		this.setFeeAcctName(data.getFeeAcctName());
		this.setBailAcctNbr(data.getBailAcctNbr());
		this.setBailAmt(data.getBailAmt());
		this.setSettMode(data.getSettMode());
		this.setSettPeriod(data.getSettPeriod());
		this.setFeeMode(data.getFeeMode());
		this.setFeeReturnFlag(data.getFeeReturnFlag());
		this.setMerOpenDate(data.getMerOpenDate());
		this.setOpenUserNbr(data.getOpenUserNbr());
		this.setMerModifyDate(data.getMerModifyDate());
		this.setModifyUserNbr(data.getModifyUserNbr());
		this.setMerCloseDate(data.getMerCloseDate());
		this.setCloseUser(data.getCloseUser());
		this.setMerPlatformNbr(data.getMerPlatformNbr());
		
		this.setVirtualAcctNbr(data.getVirtualAcctNbr());
		this.setFeeSettperiod(data.getFeeSettperiod());
		this.setCalculateParam(data.getCalculateParam());
		this.setLowerLimitVal(data.getLowerLimitVal());
		this.setUpperLimitVal(data.getUpperLimitVal());
		this.setPayModeCd(data.getPayModeCd());
		this.setCardTypeCd(data.getCardTypeCd());
		
		this.setProfitSeqNbr(data.getProfitseqnbr());
		this.setProfitMode(data.getProfitMode());
		this.setProfitPayEropenAmt(data.getProfitPayEropenAmt());
		this.setProfitMerOpenAmt(data.getProfitMerOpenAmt());
		this.setProfitMerDevelopAmt(data.getProfitMerDevelopAmt());
		this.setProfitHeadBankAmt(data.getProfitHeadBankAmt());
		this.setProfitThirdPartAmt(data.getProfitThirdPartAmt());
		
		this.setPayeeAcctNbr(data.getPayeeacctnbr());
		this.setPayeeAcctName(data.getPayeeacctname());
		this.setPayeeBankNbr(data.getPayeebanknbr());
		this.setInnerAcctCfmMode(data.getInneracctcfmmode());
		this.setPayeeAcctKind(data.getPayeeacctkind());
		
		this.setProxySynType(data.getProxySynType());
		this.setProxySynStatus(data.getProxySynStatus());
		this.setMertName(data.getMertName());
		this.setMerShortName(data.getMerShortName());
		this.setServicePhone(data.getServicePhone());
		this.setContactName(data.getContactName());
		this.setContactMobile(data.getContactMobile());
		this.setContactPhone(data.getContactPhone());
		this.setContactEmail(data.getContactEmail());
		this.setBusiness(data.getBusiness());
		this.setMerRemark(data.getMerRemark());
	}
	private String subMerchantId;
	private String merStatus;
	private String merCifNbr;
	private String merName;
	private String merMngDeptNbr;
	private String merOpenDeptNbr;
	private String merDevelopDeptNbr;
	
	private String settleAcctNbr;
	private String settleAcctName;
	private String feeAcctNbr;
	private String feeAcctName;
	
	private String bailAcctNbr;
	private String bailAmt;
	private String settMode;
	private String settPeriod;
	private String feeMode;
	private String feeReturnFlag;
	
	private String merOpenDate;
	private String openUserNbr;
	private String merModifyDate;
	private String modifyUserNbr;
	private String merCloseDate;
	private String closeUser;
	private String merPlatformNbr;

	private String virtualAcctNbr;
	private String feeSettperiod;
	private String calculateParam;
	private String lowerLimitVal;
	private String upperLimitVal;
	private String payModeCd;
	private String cardTypeCd;
	
	private String profitMode;
	private String profitPayEropenAmt;
	private String profitMerOpenAmt;
	private String profitMerDevelopAmt;
	private String profitHeadBankAmt;
	private String profitThirdPartAmt;
	private String profitSeqNbr;
	
	private String payeeAcctNbr;
	private String payeeAcctName;
	private String payeeBankNbr;
	
	private String innerAcctCfmMode;
	private String payeeAcctKind;
	
	
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

	public String getProfitMode() {
		return profitMode;
	}

	public String getPayeeAcctNbr() {
		return payeeAcctNbr;
	}

	public void setPayeeAcctNbr(String payeeAcctNbr) {
		this.payeeAcctNbr = payeeAcctNbr;
	}

	public String getPayeeAcctName() {
		return payeeAcctName;
	}

	public void setPayeeAcctName(String payeeAcctName) {
		this.payeeAcctName = payeeAcctName;
	}

	public String getPayeeBankNbr() {
		return payeeBankNbr;
	}

	public void setPayeeBankNbr(String payeeBankNbr) {
		this.payeeBankNbr = payeeBankNbr;
	}

	public void setProfitMode(String profitMode) {
		this.profitMode = profitMode;
	}

	public String getProfitPayEropenAmt() {
		return profitPayEropenAmt;
	}

	public void setProfitPayEropenAmt(String profitPayEropenAmt) {
		this.profitPayEropenAmt = profitPayEropenAmt;
	}

	public String getProfitMerOpenAmt() {
		return profitMerOpenAmt;
	}

	public void setProfitMerOpenAmt(String profitMerOpenAmt) {
		this.profitMerOpenAmt = profitMerOpenAmt;
	}

	public String getProfitMerDevelopAmt() {
		return profitMerDevelopAmt;
	}

	public void setProfitMerDevelopAmt(String profitMerDevelopAmt) {
		this.profitMerDevelopAmt = profitMerDevelopAmt;
	}

	public String getProfitHeadBankAmt() {
		return profitHeadBankAmt;
	}

	public void setProfitHeadBankAmt(String profitHeadBankAmt) {
		this.profitHeadBankAmt = profitHeadBankAmt;
	}

	public String getProfitThirdPartAmt() {
		return profitThirdPartAmt;
	}

	public void setProfitThirdPartAmt(String profitThirdPartAmt) {
		this.profitThirdPartAmt = profitThirdPartAmt;
	}

	
	public String getVirtualAcctNbr() {
		return virtualAcctNbr;
	}

	public void setVirtualAcctNbr(String virtualAcctNbr) {
		this.virtualAcctNbr = virtualAcctNbr;
	}

	public String getFeeSettperiod() {
		return feeSettperiod;
	}

	public void setFeeSettperiod(String feeSettperiod) {
		this.feeSettperiod = feeSettperiod;
	}

	public String getCalculateParam() {
		return calculateParam;
	}

	public void setCalculateParam(String calculateParam) {
		this.calculateParam = calculateParam;
	}

	public String getLowerLimitVal() {
		return lowerLimitVal;
	}

	public void setLowerLimitVal(String lowerLimitVal) {
		this.lowerLimitVal = lowerLimitVal;
	}

	public String getUpperLimitVal() {
		return upperLimitVal;
	}

	public void setUpperLimitVal(String upperLimitVal) {
		this.upperLimitVal = upperLimitVal;
	}

	public String getPayModeCd() {
		return payModeCd;
	}

	public void setPayModeCd(String payModeCd) {
		this.payModeCd = payModeCd;
	}

	public String getCardTypeCd() {
		return cardTypeCd;
	}

	public void setCardTypeCd(String cardTypeCd) {
		this.cardTypeCd = cardTypeCd;
	}

	public String getMerStatus() {
		return merStatus;
	}

	public void setMerStatus(String merStatus) {
		this.merStatus = merStatus;
	}

	public String getMerCifNbr() {
		return merCifNbr;
	}

	public void setMerCifNbr(String merCifNbr) {
		this.merCifNbr = merCifNbr;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerMngDeptNbr() {
		return merMngDeptNbr;
	}

	public void setMerMngDeptNbr(String merMngDeptNbr) {
		this.merMngDeptNbr = merMngDeptNbr;
	}

	public String getMerOpenDeptNbr() {
		return merOpenDeptNbr;
	}

	public void setMerOpenDeptNbr(String merOpenDeptNbr) {
		this.merOpenDeptNbr = merOpenDeptNbr;
	}

	public String getMerDevelopDeptNbr() {
		return merDevelopDeptNbr;
	}

	public void setMerDevelopDeptNbr(String merDevelopDeptNbr) {
		this.merDevelopDeptNbr = merDevelopDeptNbr;
	}

	public String getFeeAcctNbr() {
		return feeAcctNbr;
	}

	public void setFeeAcctNbr(String feeAcctNbr) {
		this.feeAcctNbr = feeAcctNbr;
	}

	public String getFeeAcctName() {
		return feeAcctName;
	}

	public void setFeeAcctName(String feeAcctName) {
		this.feeAcctName = feeAcctName;
	}

	public String getBailAmt() {
		return bailAmt;
	}

	public void setBailAmt(String bailAmt) {
		this.bailAmt = bailAmt;
	}

	public String getSettMode() {
		return settMode;
	}

	public void setSettMode(String settMode) {
		this.settMode = settMode;
	}

	public String getSettPeriod() {
		return settPeriod;
	}

	public void setSettPeriod(String settPeriod) {
		this.settPeriod = settPeriod;
	}

	public String getFeeMode() {
		return feeMode;
	}

	public void setFeeMode(String feeMode) {
		this.feeMode = feeMode;
	}

	public String getFeeReturnFlag() {
		return feeReturnFlag;
	}

	public void setFeeReturnFlag(String feeReturnFlag) {
		this.feeReturnFlag = feeReturnFlag;
	}

	public String getMerOpenDate() {
		return merOpenDate;
	}

	public void setMerOpenDate(String merOpenDate) {
		this.merOpenDate = merOpenDate;
	}

	public String getOpenUserNbr() {
		return openUserNbr;
	}

	public void setOpenUserNbr(String openUserNbr) {
		this.openUserNbr = openUserNbr;
	}

	public String getMerModifyDate() {
		return merModifyDate;
	}

	public void setMerModifyDate(String merModifyDate) {
		this.merModifyDate = merModifyDate;
	}

	public String getModifyUserNbr() {
		return modifyUserNbr;
	}

	public void setModifyUserNbr(String modifyUserNbr) {
		this.modifyUserNbr = modifyUserNbr;
	}

	public String getMerCloseDate() {
		return merCloseDate;
	}

	public void setMerCloseDate(String merCloseDate) {
		this.merCloseDate = merCloseDate;
	}

	public String getCloseUser() {
		return closeUser;
	}

	public void setCloseUser(String closeUser) {
		this.closeUser = closeUser;
	}

	public String getBailAcctNbr() {
		return bailAcctNbr;
	}

	public void setBailAcctNbr(String bailAcctNbr) {
		this.bailAcctNbr = bailAcctNbr;
	}

	public String getMerPlatformNbr() {
		return merPlatformNbr;
	}

	public void setMerPlatformNbr(String merPlatformNbr) {
		this.merPlatformNbr = merPlatformNbr;
	}

	public String getSettleAcctNbr() {
		return settleAcctNbr;
	}

	public void setSettleAcctNbr(String settleAcctNbr) {
		this.settleAcctNbr = settleAcctNbr;
	}

	public String getSettleAcctName() {
		return settleAcctName;
	}

	public void setSettleAcctName(String settleAcctName) {
		this.settleAcctName = settleAcctName;
	}

	public String getProfitSeqNbr() {
		return profitSeqNbr;
	}

	public void setProfitSeqNbr(String profitSeqNbr) {
		this.profitSeqNbr = profitSeqNbr;
	}

	public String getInnerAcctCfmMode() {
		return innerAcctCfmMode;
	}

	public void setInnerAcctCfmMode(String innerAcctCfmMode) {
		this.innerAcctCfmMode = innerAcctCfmMode;
	}

	public String getPayeeAcctKind() {
		return payeeAcctKind;
	}

	public void setPayeeAcctKind(String payeeAcctKind) {
		this.payeeAcctKind = payeeAcctKind;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

	public String getProxySynType() {
		return proxySynType;
	}

	public void setProxySynType(String proxySynType) {
		this.proxySynType = proxySynType;
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
