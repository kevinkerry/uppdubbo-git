package com.csii.upp.dto.extend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class InputMerchantSyncData{
	public InputMerchantSyncData() {
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InputMerchantSyncData(Map inputData) throws PeException {
		
		Map<String, Object> map = inputData;
		this.setMerNbr(StringUtil.parseObjectToString(map
				.get(Dict.MER_NBR)));
		this.setSubMerchantId(StringUtil.parseObjectToString(map
				.get(Dict.SUB_MERCHANT_ID)));
		this.setMerStatus(StringUtil.parseObjectToString(map
				.get(Dict.MER_STATUS)));
		this.setMerCifNbr(StringUtil.parseObjectToString(map
				.get(Dict.MER_CIF_NBR)));
		this.setMerName(StringUtil.parseObjectToString(map
				.get(Dict.MER_NAME)));
		this.setMerMngDeptNbr(StringUtil.parseObjectToString(map
				.get(Dict.MER_MNG_DEPT_NBR)));
		this.setMerOpenDeptNbr(StringUtil.parseObjectToString(map
				.get(Dict.MER_OPEN_DEPT_NBR)));
		this.setMerDevelopDeptNbr(StringUtil.parseObjectToString(map
				.get(Dict.MER_DEVELOP_DEPT_NBR)));
		this.setSettleAcctName(StringUtil.parseObjectToString(map
				.get(Dict.SETTLE_ACCT_NAME)));
		this.setSettleAcctNbr(StringUtil.parseObjectToString(map
				.get(Dict.SETTLE_ACCT_NBR)));
		this.setFeeAcctName(StringUtil.parseObjectToString(map
				.get(Dict.FEE_ACCT_NAME)));
		this.setFeeAcctNbr(StringUtil.parseObjectToString(map
				.get(Dict.FEE_ACCT_NBR)));
		this.setBailAmt(StringUtil.parseBigDecimal(map
				.get(Dict.BAIL_AMT)));
		this.setSettMode(StringUtil.parseObjectToString(map
				.get(Dict.SETT_MODE)));
		this.setSettPeriod(StringUtil.parseObjectToString(map
				.get(Dict.SETT_PERIOD)));
		this.setFeeMode(StringUtil.parseObjectToString(map
				.get(Dict.FEE_MODE)));
		this.setFeeReturnFlag(StringUtil.parseObjectToString(map
				.get(Dict.FEE_RETURN_FLAG)));
		this.setMerOpenDate(DateUtil.DateFormat_To_Date(map
				.get(Dict.MER_OPEN_DATE)));
		this.setOpenUserNbr(StringUtil.parseObjectToString(map
				.get(Dict.OPEN_USER_NBR)));
		this.setMerModifyDate(DateUtil.DateFormat_To_Date(map
				.get(Dict.MER_MODIFY_DATE)));
		this.setModifyUserNbr(StringUtil.parseObjectToString(map
				.get(Dict.MODIFY_USER_NBR)));
		this.setMerCloseDate(DateUtil.DateFormat_To_Date(map
				.get(Dict.MER_CLOSE_DATE)));
		this.setCloseUser(StringUtil.parseObjectToString(map
				.get(Dict.CLOSE_USER)));
		this.setBailAcctNbr(StringUtil.parseObjectToString(map
				.get(Dict.BAIL_ACCT_NBR)));
		this.setMerPlatformNbr(StringUtil.parseObjectToString(map
				.get(Dict.MER_PLATFORM_NBR)));
		
		this.setVirtualAcctNbr(StringUtil.parseObjectToString(map.get(Dict.VIRTUAL_ACCT_NBR)));
		this.setPayeeAcctNbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NBR)));
		this.setPayeeAcctName(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NAME)));
		this.setPayeeBankNbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_BANK_NBR)));
		
		this.setFeeSettperiod(StringUtil.parseObjectToString(map.get(Dict.FEE_SETT_PERIOD)));
		this.setCalculateParam(StringUtil.parseObjectToString(map.get(Dict.CALCULATE_PARAM)));
		this.setLowerLimitVal(StringUtil.parseBigDecimal(map.get(Dict.LOWER_LIMIT_VAL)));
		this.setUpperLimitVal(StringUtil.parseBigDecimal(map.get(Dict.UPPER_LIMIT_VAL)));
		this.setPayModeCd(StringUtil.parseObjectToString(map.get(Dict.PAY_MODE_CD)));
		this.setCardTypeCd(StringUtil.parseObjectToString(map.get(Dict.CARD_TYPE_CD)));
		
		this.setProfitSeqNbr(StringUtil.parseObjectToString(map.get(Dict.PROFIT_SEQ_NBR)));
		this.setProfitMode(StringUtil.parseObjectToString(map.get(Dict.PROFIT_MODE)));
		this.setProfitPayEropenAmt(StringUtil.parseBigDecimal(map.get(Dict.PROFIT_PAY_EROPEN_AMT)));
		this.setProfitMerOpenAmt(StringUtil.parseBigDecimal(map.get(Dict.PROFIT_MER_OPEN_AMT)));
		this.setProfitMerDevelopAmt(StringUtil.parseBigDecimal(map.get(Dict.PROFIT_MER_DEVELOP_AMT)));
		this.setProfitHeadBankAmt(StringUtil.parseBigDecimal(map.get(Dict.PROFIT_HEAD_BANK_AMT)));
		this.setProfitThirdPartAmt(StringUtil.parseBigDecimal(map.get(Dict.PROFIT_THIRD_PART_AMT)));
		this.setInnerAcctCfmMode(StringUtil.parseObjectToString(map.get(Dict.INNER_ACCT_CFM_MODE)));
		this.setAcctKind(StringUtil.parseObjectToString(map.get(Dict.ACCT_KIND)));
	}

	private String merNbr;
	private String subMerchantId;
	private String merStatus;
	private String merCifNbr;
	private String merName;
	public String getMerNbr() {
		return merNbr;
	}

	public void setMerNbr(String merNbr) {
		this.merNbr = merNbr;
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

	public String getMerDevelopDeptNbr() {
		return merDevelopDeptNbr;
	}

	public void setMerDevelopDeptNbr(String merDevelopDeptNbr) {
		this.merDevelopDeptNbr = merDevelopDeptNbr;
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

	public String getBailAcctNbr() {
		return bailAcctNbr;
	}

	public void setBailAcctNbr(String bailAcctNbr) {
		this.bailAcctNbr = bailAcctNbr;
	}

	public BigDecimal getBailAmt() {
		return bailAmt;
	}

	public void setBailAmt(BigDecimal bailAmt) {
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

	public Date getMerOpenDate() {
		return merOpenDate;
	}

	public void setMerOpenDate(Date merOpenDate) {
		this.merOpenDate = merOpenDate;
	}

	public String getOpenUserNbr() {
		return openUserNbr;
	}

	public void setOpenUserNbr(String openUserNbr) {
		this.openUserNbr = openUserNbr;
	}

	public Date getMerModifyDate() {
		return merModifyDate;
	}

	public void setMerModifyDate(Date merModifyDate) {
		this.merModifyDate = merModifyDate;
	}

	public String getModifyUserNbr() {
		return modifyUserNbr;
	}

	public void setModifyUserNbr(String modifyUserNbr) {
		this.modifyUserNbr = modifyUserNbr;
	}

	public Date getMerCloseDate() {
		return merCloseDate;
	}

	public void setMerCloseDate(Date merCloseDate) {
		this.merCloseDate = merCloseDate;
	}

	public String getCloseUser() {
		return closeUser;
	}

	public void setCloseUser(String closeUser) {
		this.closeUser = closeUser;
	}

	public String getMerOpenDeptNbr() {
		return merOpenDeptNbr;
	}

	public void setMerOpenDeptNbr(String merOpenDeptNbr) {
		this.merOpenDeptNbr = merOpenDeptNbr;
	}

	public String getMerPlatformNbr() {
		return merPlatformNbr;
	}

	public void setMerPlatformNbr(String merPlatformNbr) {
		this.merPlatformNbr = merPlatformNbr;
	}

	private String merMngDeptNbr;
	private String merDevelopDeptNbr;
	
	private String settleAcctNbr;
	private String settleAcctName;
	private String feeAcctNbr;
	private String feeAcctName;
	
	private String bailAcctNbr;
	private BigDecimal bailAmt;
	private String settMode;
	private String settPeriod;
	private String feeMode;
	private String feeReturnFlag;
	
	private Date merOpenDate;
	private String openUserNbr;
	private Date merModifyDate;
	private String modifyUserNbr;
	private Date merCloseDate;
	private String closeUser;
	private String merOpenDeptNbr;
	private String merPlatformNbr;
	
	private String virtualAcctNbr;
	private String payeeAcctNbr;
	private String payeeAcctName;
	private String payeeBankNbr; 
	private String feeSettperiod;
	private String calculateParam;
	private BigDecimal lowerLimitVal;
	private BigDecimal upperLimitVal;
	private String payModeCd;
	private String cardTypeCd;
	
	private String profitMode;
	private BigDecimal profitPayEropenAmt;
	private BigDecimal profitMerOpenAmt;
	private BigDecimal profitMerDevelopAmt;
	private BigDecimal profitHeadBankAmt;
	private BigDecimal profitThirdPartAmt;
	private String profitSeqNbr;
	
	private String innerAcctCfmMode;
	private String acctKind;
	public String getProfitMode() {
		return profitMode;
	}

	public void setProfitMode(String profitMode) {
		this.profitMode = profitMode;
	}

	public BigDecimal getProfitPayEropenAmt() {
		return profitPayEropenAmt;
	}

	public void setProfitPayEropenAmt(BigDecimal profitPayEropenAmt) {
		this.profitPayEropenAmt = profitPayEropenAmt;
	}

	public BigDecimal getProfitMerOpenAmt() {
		return profitMerOpenAmt;
	}

	public void setProfitMerOpenAmt(BigDecimal profitMerOpenAmt) {
		this.profitMerOpenAmt = profitMerOpenAmt;
	}

	public BigDecimal getProfitMerDevelopAmt() {
		return profitMerDevelopAmt;
	}

	public void setProfitMerDevelopAmt(BigDecimal profitMerDevelopAmt) {
		this.profitMerDevelopAmt = profitMerDevelopAmt;
	}

	public BigDecimal getProfitHeadBankAmt() {
		return profitHeadBankAmt;
	}

	public void setProfitHeadBankAmt(BigDecimal profitHeadBankAmt) {
		this.profitHeadBankAmt = profitHeadBankAmt;
	}

	public BigDecimal getProfitThirdPartAmt() {
		return profitThirdPartAmt;
	}

	public void setProfitThirdPartAmt(BigDecimal profitThirdPartAmt) {
		this.profitThirdPartAmt = profitThirdPartAmt;
	}

	public String getProfitSeqNbr() {
		return profitSeqNbr;
	}

	public void setProfitSeqNbr(String profitSeqNbr) {
		this.profitSeqNbr = profitSeqNbr;
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

	public BigDecimal getLowerLimitVal() {
		return lowerLimitVal;
	}

	public void setLowerLimitVal(BigDecimal lowerLimitVal) {
		this.lowerLimitVal = lowerLimitVal;
	}

	public BigDecimal getUpperLimitVal() {
		return upperLimitVal;
	}

	public void setUpperLimitVal(BigDecimal upperLimitVal) {
		this.upperLimitVal = upperLimitVal;
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

	public String getInnerAcctCfmMode() {
		return innerAcctCfmMode;
	}

	public void setInnerAcctCfmMode(String innerAcctCfmMode) {
		this.innerAcctCfmMode = innerAcctCfmMode;
	}

	public String getAcctKind() {
		return acctKind;
	}

	public void setAcctKind(String acctKind) {
		this.acctKind = acctKind;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}
}
