package com.csii.upp.dto.extend;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.constant.SystemCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class InputPaygateData extends Onlinetrans {

	public InputPaygateData() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InputPaygateData(Map map) throws PeException {
		this.setTransnbr(StringUtil.parseObjectToString(map.get("transNbr")));
		this.setCancelConfirm(StringUtil.parseObjectToString(map.get(Dict.CANCEL_CONFIRM)));
		this.setBeginDate(StringUtil.parseObjectToString(map.get(Dict.BEGIN_DATE)));
		this.setEndDate(StringUtil.parseObjectToString(map.get(Dict.END_DATE)));
		this.setPaytypcd(StringUtil.parseObjectToString(map.get(Dict.PAY_TYP_CD)));
		this.setTransId(StringUtil.parseObjectToString(map.get(Dict.TRANS_ID)));
		this.setSystemcode(SystemCode.PAYGATE);
		this.setOutsubmerchantid(StringUtil.parseObjectToString(map.get(Dict.OUT_SUB_MERCHANT_ID)));
		this.setTranstypcd(StringUtil.parseObjectToString(map.get(Dict.TRANS_TYP_CD)));
		this.setOrderNbr(StringUtil.parseObjectToString(map.get(Dict.ORDER_ID)));
		this.setSignature(StringUtil.parseObjectToString(map.get(Dict.SIGNATURE)));
		this.setPlain(StringUtil.parseObjectToString(map.get(Dict.PLAIN)));
		String channelNbr=StringUtil.parseObjectToString(map.get(Dict.CHANNEL_NBR));
		this.setChannelnbr(StringUtil.isStringEmpty(channelNbr)?ChannelNbr.PC:channelNbr);
		this.setMernbr(StringUtil.parseObjectToString(map.get(Dict.MERCHANT_ID)));
		this.setMerseqnbr(StringUtil.parseObjectToString(map.get(Dict.MER_SEQ_NO)));
		this.setSignStatus(StringUtil.parseObjectToString(map.get(Dict.SIGN_STATUS)));
		this.setOrigMerDateTime(StringUtil.parseObjectToString(map.get(Dict.ORIG_MER_DATE_TIME)));
		this.setOrigSubMerDate(StringUtil.parseObjectToString(map.get(Dict.ORIG_SUB_MER_DATE)));
		this.setOrigSubMerDateTime(StringUtil.parseObjectToString(map.get(Dict.ORIG_SUB_MER_DATE_TIME)));
		this.setOrigmerdate(DateUtil.Object_To_Date(map.get(Dict.ORIG_MER_DATE)));
		this.setOrigTransAmt(StringUtil.parseObjectToString(map.get(Dict.ORIG_TRANS_AMT)));
		this.setOrigmerseqnbr(StringUtil.parseObjectToString(map.get(Dict.ORIG_MER_SEQ_NBR)));
		this.setOrigSubMerSeqNo(StringUtil.parseObjectToString(map.get(Dict.ORIG_SUB_MER_SEQ_NO)));
		this.setSubMerchantId(StringUtil.parseObjectToString(map.get(Dict.SUB_MERCHANT_ID)));
		this.setSubMerSeqNo(StringUtil.parseObjectToString(map.get(Dict.SUB_MER_SEQ_NO)));
		this.setSubMerDateTime(StringUtil.parseObjectToString(map.get(Dict.SUB_MER_DATE_TIME)));
		this.setSubTransAmt(StringUtil.parseBigDecimal(map.get(Dict.SUB_TRANS_AMT)));
		this.setMertransdate(DateUtil.DateFormat_To_Date(map.get(Dict.MER_TRANS_DATE)));
		this.setMertransdatetime(DateUtil.DateTimeFormat_To_Date(map.get(Dict.MER_DATE_TIME)));
		this.setCurrencycd(StringUtil.parseObjectToString(map.get("currency")));
		this.setCustemail(StringUtil.parseObjectToString(map.get(Dict.CUSTO_MERE_MAIL)));
		this.setMerurl(StringUtil.parseObjectToString(map.get(Dict.MER_URL)));
		String merurl1=StringUtil.parseObjectToString(map.get(Dict.MER_URL1));
		this.setMerurl1(!StringUtil.isStringEmpty(merurl1)?URLDecoder.decode(merurl1):merurl1);
		this.setPayip(StringUtil.parseObjectToString(map.get(Dict.PAY_IP)));
		this.setTransamt(StringUtil.parseBigDecimal(map.get(Dict.TRANS_AMT)));
		this.setPagenum(StringUtil.parseObjectToString(map.get(Dict.PAGE_NUM)));
		this.setPagesize(StringUtil.parseObjectToString(map.get(Dict.PAGE_SIZE)));
		this.setPayeracctnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NBR)));
		this.setPayerphoneno(StringUtil.parseObjectToString(map.get(Dict.PAYER_PHONE_NO)));
		this.setPayercardtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_TYP_CD)));
		this.setPayercardpwd(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_PWD)));
		this.setPayercardcvv2(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_CVV2)));
		this.setPayercardexpireddate(
				StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_EXPIRED_DATE)));
		this.setPayeracctname(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NAME)));
    	this.setPayeracctdeptnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_DEPT_NBR)));
    	this.setPayeraccttypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_TYP_CD)));
    	this.setPayerbanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_BANK_NBR)));
		this.setPayeridnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_NBR)));
		this.setPayeridtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_TYP_CD)));
		this.mersubtranslist = (List<Map>) map.get(Dict.MER_TRANS_LIST);
		this.setPayeridtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_TYP_CD)));
		this.setPayeridnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_NBR)));
		this.setSignNbr(StringUtil.parseObjectToString(map.get(Dict.SIGN_NBR)));
		this.setSignTypCd(StringUtil.parseObjectToString(map.get(Dict.SIGN_TYP_CD)));
		this.setPerDayLimit(StringUtil.parseObjectToString(map.get(Dict.PER_DAY_LIMIT)));
		this.setPerTransLimit(StringUtil.parseObjectToString(map.get(Dict.PER_TRANS_LIMIT)));
		this.setTransTypCd(StringUtil.parseObjectToString(map.get(Dict.TRANS_TYP_CD)));
		this.setAcctName(StringUtil.parseObjectToString(map.get(Dict.ACCT_NAME)));
		this.setReasondesc(StringUtil.parseObjectToString(map.get(Dict.REASON_DESC)));
		this.setMsgext(StringUtil.parseObjectToString(map.get(Dict.MSG_EXT)));
		this.setMerStatus(StringUtil.parseObjectToString(map.get(Dict.MER_STATUS)));
		this.setCustcifnbr(StringUtil.parseObjectToString(map.get(Dict.CUST_CIF_NBR)));
		this.setMerCifNbr(StringUtil.parseObjectToString(map.get(Dict.MER_CIFNO)));
		this.setUsernbr(StringUtil.parseObjectToString(map.get(Dict.USER_NO)));
		this.setMerName(StringUtil.parseObjectToString(map.get(Dict.MER_NAME)));
		this.setMerMngDeptNbr(StringUtil.parseObjectToString(map.get(Dict.MER_MGMT_DEPT_ID)));
		this.setMeropendeptnbr(StringUtil.parseObjectToString(map.get(Dict.MER_OPEN_DEPT_ID)));
		this.setMerDevelopDeptNbr(StringUtil.parseObjectToString(map.get(Dict.MER_DEVELOP_DEPT_ID)));
		this.setSettleAcctName(StringUtil.parseObjectToString(map.get(Dict.MER_SETT_ACCT_NAME)));
		this.setSettleAcctNbr(StringUtil.parseObjectToString(map.get(Dict.MER_SETT_ACCTNO)));
		this.setFeeAcctName(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_ACCT_NAME)));
		this.setFeeAcctNbr(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_ACCTNO)));
		this.setBailAmt(StringUtil.parseObjectToString(map.get(Dict.MER_BAIL_AMT)));
		this.setSettMode(StringUtil.parseObjectToString(map.get(Dict.MER_SETT_MODE)));
		this.setSettPeriod(StringUtil.parseObjectToString(map.get(Dict.MER_SETT_PERIOD)));
		this.setFeeMode(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_MODE)));
		this.setFeeReturnFlag(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_RETURN_FLAG)));
		this.setMerOpenDate(StringUtil.parseObjectToString(map.get(Dict.MER_OPEN_DATE)));
		this.setOpenUserNbr(StringUtil.parseObjectToString(map.get(Dict.MER_OPEN_USER)));
		this.setMerModifyDate(StringUtil.parseObjectToString(map.get(Dict.MER_MODIFY_DATE)));
		this.setModifyUserNbr(StringUtil.parseObjectToString(map.get(Dict.MER_MODIFY_USER)));
		this.setMerCloseDate(StringUtil.parseObjectToString(map.get(Dict.MER_CLOSE_DATE)));
		this.setCloseUser(StringUtil.parseObjectToString(map.get(Dict.MER_CLOSE_USER)));
		this.setBailAcctNbr(StringUtil.parseObjectToString(map.get(Dict.MER_BAIL_ACCT_NO)));
		this.setMerPlatformNbr(StringUtil.parseObjectToString(map.get(Dict.MER_PLATFORM_NBR)));
		this.setSmsCode(StringUtil.parseObjectToString(map.get(Dict.SMS_CODE)));
		this.setSmsContent(StringUtil.parseObjectToString(map.get(Dict.SMS_CONTENT)));
		this.setUsernbr(StringUtil.parseObjectToString(map.get(Dict.USER_NO)));
		this.setPayeeacctnbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NBR)));
		this.setPayeeacctname(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NAME)));
		this.setPayeebanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_BANK_NBR)));
		this.setVirtualAcctNbr(StringUtil.parseObjectToString(map.get(Dict.MER_THIRD_PART_ACCTNO)));
		this.setFeeSettperiod(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_SETT_PERIOD)));
		this.setCalculateParam(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_AMT)));
		this.setLowerLimitVal(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_MIN_AMT)));
		this.setUpperLimitVal(StringUtil.parseObjectToString(map.get(Dict.MER_FEE_MAX_AMT)));
		this.setPayModeCd(StringUtil.parseObjectToString(map.get(Dict.MER_PAY_TYPE)));
		this.setCardTypeCd(StringUtil.parseObjectToString(map.get(Dict.PAY_CARD_TYPE)));
		this.setProfitseqnbr(StringUtil.parseObjectToString(map.get(Dict.PROFIT_SEQ_NO)));
		this.setProfitMode(StringUtil.parseObjectToString(map.get(Dict.PROFIT_MODE)));
		this.setProfitPayEropenAmt(StringUtil.parseObjectToString(map.get(Dict.PROFIT_PAYER_OPEN_AMT)));
		this.setProfitMerOpenAmt(StringUtil.parseObjectToString(map.get(Dict.PROFIT_MER_OPEN_AMT)));
		this.setProfitMerDevelopAmt(StringUtil.parseObjectToString(map.get(Dict.PROFIT_MER_DEVELOP_AMT)));
		this.setProfitHeadBankAmt(StringUtil.parseObjectToString(map.get(Dict.PROFIT_HEAD_BANK_AMT)));
		this.setProfitThirdPartAmt(StringUtil.parseObjectToString(map.get(Dict.PROFIT_THIRD_PART_AMT)));
		this.setCyberTypCd(StringUtil.parseObjectToString(map.get(Dict.CYBER_TYP_CD)));
		this.setSmsInnerFundTransNbr(StringUtil.parseObjectToString(map.get(Dict.SMS_INNER_FUND_TRANS_NBR)));
		this.setSendUnionPayTime(StringUtil.parseObjectToString(map.get(Dict.SEND_UNION_PAY_TIME)));
		this.setIsQueryOpenStatus(StringUtil.parseObjectToString(map.get(Dict.IS_QUERY_OPEN_STATUS)));
		this.setBatchNo(StringUtil.parseObjectToString(map.get(Dict.BATCH_NO)));
		this.setMerTfType(StringUtil.parseObjectToString(map.get(Dict.MER_TF_TYPE)));
		this.setTotalNum(StringUtil.parseObjectToString(map.get(Dict.TOTAL_NUM)));
		this.setSmsSqenbr(StringUtil.parseObjectToString(map.get(Dict.SMS_SQENBR)));
		this.setMerSettAcctType(StringUtil.parseObjectToString(map.get(Dict.MER_SETT_ACCT_TYPE)));
		this.setInneracctcfmmode(StringUtil.parseObjectToString(map.get(Dict.MER_SERVICE_TYPE)));
		this.setPayeeacctkind(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_KIND)));
		this.setTellerNo(StringUtil.parseObjectToString(map.get(Dict.TELLER_NO)));
		this.setClientno(StringUtil.parseObjectToString(map.get(Dict.CLIENT_NO)));
		this.setBranchno(StringUtil.parseObjectToString(map.get(Dict.BRANCH_NO)));
		this.setDeductamt(StringUtil.parseBigDecimal(map.get(Dict.AMT_THIS_TIME)));
		this.setInteralflag(StringUtil.parseObjectToString(map.get(Dict.INTERAL_FLAG)));
		this.setActMemo(StringUtil.parseObjectToString(map.get(Dict.ACT_MEMO)));

		this.setBizType(StringUtil.parseObjectToString(map.get(Dict.BIZ_TYPE)));		//财政网业务类型（01：个人（B2C）02：企业（B2B））
		this.setRequestModel(StringUtil.parseObjectToString(map.get(Dict.REQUEST_MODEL)));	//请求类型(01：支付，02：查询，03：撤消，04：退款)
		this.setTradeCode(StringUtil.parseObjectToString(map.get(Dict.TRADE_CODE)));	//代收机构编码

		//二维码相关字段
		this.setCodetypcd(StringUtil.parseObjectToString(map.get(Dict.CODE_TYP_CD)));
		this.setScancodemode(StringUtil.parseObjectToString(map.get(Dict.SCAN_CODE_MODE)));
		this.setIsCredit(StringUtil.parseObjectToString(map.get(Dict.IS_CREDIT)));
		this.setTimeStart(StringUtil.parseObjectToString(map.get(Dict.TIME_START)));
		this.setTimeExpire(StringUtil.parseObjectToString(map.get(Dict.TIME_EXPIRE)));
		this.setCustomerIp(StringUtil.parseObjectToString(map.get(Dict.CUSTOMER_IP)));
		this.setProductInfoDetail(StringUtil.parseObjectToString(map.get(Dict.PRODUCT_INFO_DETAIL)));
		this.setProductId(StringUtil.parseObjectToString(map.get(Dict.PRODUCT_ID)));
		this.setProBody(StringUtil.parseObjectToString(map.get(Dict.BODY)));
		this.setDiscountableamt(StringUtil.parseBigDecimal(map.get(Dict.DISCOUNTABLE_AMOUNT)));
		this.setUndiscountableamt(StringUtil.parseBigDecimal(map.get(Dict.UNDISCOUNTABLE_AMOUNT)));
		this.setSubject(StringUtil.parseObjectToString(map.get(Dict.SUBJECT)));
		this.setOperatorId(StringUtil.parseObjectToString(map.get(Dict.OPERATOR_ID)));
		this.setStoreId(StringUtil.parseObjectToString(map.get(Dict.STORE_ID)));
		this.setTermId(StringUtil.parseObjectToString(map.get(Dict.TERM_ID)));
		this.setTimeoutExpress(StringUtil.parseObjectToString(map.get(Dict.TIMEOUT_EXPRESS)));
		this.setAlipayStoreId(StringUtil.parseObjectToString(map.get(Dict.ALIPAY_STORE_ID)));
		this.setScene(StringUtil.parseObjectToString(map.get(Dict.SCENE)));
		this.setAuthCode(StringUtil.parseObjectToString(map.get(Dict.AUTH_CODE)));
		this.setGoodsDetail(StringUtil.parseObjectToString(map.get(Dict.GOODS_DETAIL)));
		this.setRefundReason(StringUtil.parseObjectToString(map.get(Dict.REFUND_REASON))); 
		this.setPayAccessType(StringUtil.parseObjectToString(map.get(Dict.PAY_ACCESS_TYPE)));
		this.setProxySynType(StringUtil.parseObjectToString(map.get(Dict.PROXY_SYN_TYPE)));
		this.setProxySynStatus(StringUtil.parseObjectToString(map.get(Dict.PROXY_SYN_STATUS)));
		this.setMertName(StringUtil.parseObjectToString(map.get("mertName")));
		this.setContactName(StringUtil.parseObjectToString(map.get(Dict.CONTACT_NAME)));
		this.setContactMobile(StringUtil.parseObjectToString(map.get(Dict.CONTACT_MOBILE)));
		this.setContactPhone(StringUtil.parseObjectToString(map.get(Dict.CONTACT_PHONE)));
		this.setContactEmail(StringUtil.parseObjectToString(map.get(Dict.CONTACT_EMAIL)));
		this.setMerShortName(StringUtil.parseObjectToString(map.get(Dict.MER_SHORT_NAME)));
		this.setServicePhone(StringUtil.parseObjectToString(map.get(Dict.SERVICE_PHONE)));
		this.setBusiness(StringUtil.parseObjectToString(map.get(Dict.BUSINESS)));
		this.setMerRemark(StringUtil.parseObjectToString(map.get(Dict.MER_REMARK)));
		this.setCifNo(StringUtil.parseObjectToString(map.get(Dict.CIF_NO)));
		this.setTerm(StringUtil.parseObjectToString(map.get(Dict.TERM)));
		this.setPayerAcctOrgDeptNbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_ORG_DEPT_NBR)));
		this.setUrl(StringUtil.parseObjectToString(map.get("url")));
		this.setCode(StringUtil.parseObjectToString(map.get("code")));
		this.setCustomName(StringUtil.parseObjectToString(map.get(Dict.CUSTOM_NAME)));
		this.setCustomTyp(StringUtil.parseObjectToString(map.get(Dict.CUSTOM_TYP)));
		this.setPhoneNo(StringUtil.parseObjectToString(map.get(Dict.PHONE_NO)));
		this.setCertTyp(StringUtil.parseObjectToString(map.get(Dict.CERT_TYP)));
		this.setCertNo(StringUtil.parseObjectToString(map.get(Dict.CERT_NO)));
		this.setCardTyp(StringUtil.parseObjectToString(map.get(Dict.CARD_TYP)));
		this.setCardNo(StringUtil.parseObjectToString(map.get(Dict.CARD_NO)));
		
		
	}

	private String signature;
	private String plain;
	@SuppressWarnings("rawtypes")
	private List<Map> mersubtranslist;
	private String code;
	private String url;
	private String cancelConfirm;
	private String beginDate;
	private String endDate;
	private String signStatus;
	private String origTransAmt;
	private String origMerDateTime;
	private String origMerDate;
	private String origSubMerDate;
	private String origSubMerDateTime;
	private String origSubMerSeqNo;
	private String subMerchantId;
	private String subMerSeqNo;
	private String subMerDateTime;
	private BigDecimal subTransAmt;
	private String acctName;
	private String transId;
	private String orderNbr;
	private String custemail;
	private String merurl;
	private String merurl1;
	private String payip;
	private String msgext;
	private String actMemo;
	
	private String signNbr;
	private String signTypCd;
	private String perTransLimit;
	private String perDayLimit;
	private String transTypCd;
	private String reasondesc;

	private String merStatus;
	private String merCifNbr;
	private String merName;
	private String merMngDeptNbr;
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
	private String sendUnionPayTime;
	private String smsInnerFundTransNbr;
	private String isQueryOpenStatus;
	private String frontCallBackUrl;
	
	private String batchNo;
	private String merTfType;
	private String totalNum;
	private String smsSqenbr;
	private String merSettAcctType;
	private String inneracctcfmmode;
	private String tellerNo;
	

	private String bizType;
	private String requestModel;
	private String tradeCode;

	private String isCredit;
	private String timeStart;
	private String timeExpire;
	private String customerIp;
	private String productInfoDetail;
	private String productId;
	private String proBody;
	private String subject;
	private String operatorId;
	private String storeId;
	private String termId;
	private String timeoutExpress;
	private String alipayStoreId;
	private String scene;
	private String authCode;
	private String goodsDetail;
	private String codeStreamByte;
	private String refundReason;//退款原因
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
	private String thirdMerNbr;
	private String payAccessType;
	private String respMessage;
	
	private String customName;// 客户姓名
	private String certTyp;// 证件类型
	private String certNo;// 证件编号
	private String cardTyp;// 卡类型
	private String cardNo;// 卡号
	private String customTyp;// 客户类型
	private String phoneNo;// 客户手机号
	
	private String cifNo;
	private String term;
	private String payerAcctOrgDeptNbr;
	
	private String smsCode;
	private String smsContent;
	private String outsubmerchantid;
	private String usernbr;
	private String cyberTypCd;
	private String queryFlag;
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getPayerAcctOrgDeptNbr() {
		return payerAcctOrgDeptNbr;
	}

	public void setPayerAcctOrgDeptNbr(String payerAcctOrgDeptNbr) {
		this.payerAcctOrgDeptNbr = payerAcctOrgDeptNbr;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getRequestModel() {
		return requestModel;
	}

	public void setRequestModel(String requestModel) {
		this.requestModel = requestModel;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	
	public String getSmsInnerFundTransNbr() {
		return smsInnerFundTransNbr;
	}

	public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
		this.smsInnerFundTransNbr = smsInnerFundTransNbr;
	}

	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}

	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}

	public String getSmsSqenbr() {
		return smsSqenbr;
	}

	public void setSmsSqenbr(String smsSqenbr) {
		this.smsSqenbr = smsSqenbr;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getMerTfType() {
		return merTfType;
	}

	public void setMerTfType(String merTfType) {
		this.merTfType = merTfType;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getIsQueryOpenStatus() {
		return isQueryOpenStatus;
	}

	public void setIsQueryOpenStatus(String isQueryOpenStatus) {
		this.isQueryOpenStatus = isQueryOpenStatus;
	}

	public String getSendUnionPayTime() {
		return sendUnionPayTime;
	}

	public void setSendUnionPayTime(String sendUnionPayTime) {
		this.sendUnionPayTime = sendUnionPayTime;
	}

	public String getProfitMode() {
		return profitMode;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public List<Map> getMersubtranslist() {
		return mersubtranslist;
	}

	public void setMersubtranslist(List<Map> mersubtranslist) {
		this.mersubtranslist = mersubtranslist;
	}

	public String getCancelConfirm() {
		return cancelConfirm;
	}

	public void setCancelConfirm(String cancelConfirm) {
		this.cancelConfirm = cancelConfirm;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getOrigTransAmt() {
		return origTransAmt;
	}

	public void setOrigTransAmt(String origTransAmt) {
		this.origTransAmt = origTransAmt;
	}

	public String getOrigMerDateTime() {
		return origMerDateTime;
	}

	public void setOrigMerDateTime(String origMerDateTime) {
		this.origMerDateTime = origMerDateTime;
	}

	public String getOrigMerDate() {
		return origMerDate;
	}

	public void setOrigMerDate(String origMerDate) {
		this.origMerDate = origMerDate;
	}

	public String getOrigSubMerDate() {
		return origSubMerDate;
	}

	public void setOrigSubMerDate(String origSubMerDate) {
		this.origSubMerDate = origSubMerDate;
	}

	public String getOrigSubMerDateTime() {
		return origSubMerDateTime;
	}

	public void setOrigSubMerDateTime(String origSubMerDateTime) {
		this.origSubMerDateTime = origSubMerDateTime;
	}

	public String getOrigSubMerSeqNo() {
		return origSubMerSeqNo;
	}

	public void setOrigSubMerSeqNo(String origSubMerSeqNo) {
		this.origSubMerSeqNo = origSubMerSeqNo;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

	public String getSubMerSeqNo() {
		return subMerSeqNo;
	}

	public void setSubMerSeqNo(String subMerSeqNo) {
		this.subMerSeqNo = subMerSeqNo;
	}

	public String getSubMerDateTime() {
		return subMerDateTime;
	}

	public void setSubMerDateTime(String subMerDateTime) {
		this.subMerDateTime = subMerDateTime;
	}

	public BigDecimal getSubTransAmt() {
		return subTransAmt;
	}

	public void setSubTransAmt(BigDecimal subTransAmt) {
		this.subTransAmt = subTransAmt;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getOrderNbr() {
		return orderNbr;
	}

	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}

	public String getCustemail() {
		return custemail;
	}

	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}

	public String getMerurl() {
		return merurl;
	}

	public void setMerurl(String merurl) {
		this.merurl = merurl;
	}

	public String getMerurl1() {
		return merurl1;
	}

	public void setMerurl1(String merurl1) {
		this.merurl1 = merurl1;
	}

	public String getPayip() {
		return payip;
	}

	public void setPayip(String payip) {
		this.payip = payip;
	}

	public String getMsgext() {
		return msgext;
	}

	public void setMsgext(String msgext) {
		this.msgext = msgext;
	}

	public String getActMemo() {
		return actMemo;
	}

	public void setActMemo(String actMemo) {
		this.actMemo = actMemo;
	}

	public String getSignNbr() {
		return signNbr;
	}

	public void setSignNbr(String signNbr) {
		this.signNbr = signNbr;
	}

	public String getSignTypCd() {
		return signTypCd;
	}

	public void setSignTypCd(String signTypCd) {
		this.signTypCd = signTypCd;
	}

	public String getPerTransLimit() {
		return perTransLimit;
	}

	public void setPerTransLimit(String perTransLimit) {
		this.perTransLimit = perTransLimit;
	}

	public String getPerDayLimit() {
		return perDayLimit;
	}

	public void setPerDayLimit(String perDayLimit) {
		this.perDayLimit = perDayLimit;
	}

	public String getTransTypCd() {
		return transTypCd;
	}

	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}

	public String getReasondesc() {
		return reasondesc;
	}

	public void setReasondesc(String reasondesc) {
		this.reasondesc = reasondesc;
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

	public String getMerPlatformNbr() {
		return merPlatformNbr;
	}

	public void setMerPlatformNbr(String merPlatformNbr) {
		this.merPlatformNbr = merPlatformNbr;
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

	public String getMerSettAcctType() {
		return merSettAcctType;
	}

	public void setMerSettAcctType(String merSettAcctType) {
		this.merSettAcctType = merSettAcctType;
	}

	public String getInneracctcfmmode() {
		return inneracctcfmmode;
	}

	public void setInneracctcfmmode(String inneracctcfmmode) {
		this.inneracctcfmmode = inneracctcfmmode;
	}

	public String getTellerNo() {
		return tellerNo;
	}

	public void setTellerNo(String tellerNo) {
		this.tellerNo = tellerNo;
	}

	public String getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getProductInfoDetail() {
		return productInfoDetail;
	}

	public void setProductInfoDetail(String productInfoDetail) {
		this.productInfoDetail = productInfoDetail;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProBody() {
		return proBody;
	}

	public void setProBody(String proBody) {
		this.proBody = proBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}

	public String getAlipayStoreId() {
		return alipayStoreId;
	}

	public void setAlipayStoreId(String alipayStoreId) {
		this.alipayStoreId = alipayStoreId;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public String getCodeStreamByte() {
		return codeStreamByte;
	}

	public void setCodeStreamByte(String codeStreamByte) {
		this.codeStreamByte = codeStreamByte;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getProxySynType() {
		return proxySynType;
	}

	public void setProxySynType(String proxySynType) {
		this.proxySynType = proxySynType;
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

	public String getThirdMerNbr() {
		return thirdMerNbr;
	}

	public void setThirdMerNbr(String thirdMerNbr) {
		this.thirdMerNbr = thirdMerNbr;
	}

	public String getPayAccessType() {
		return payAccessType;
	}

	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}

	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getOutsubmerchantid() {
		return outsubmerchantid;
	}

	public void setOutsubmerchantid(String outsubmerchantid) {
		this.outsubmerchantid = outsubmerchantid;
	}

	public String getUsernbr() {
		return usernbr;
	}

	public void setUsernbr(String usernbr) {
		this.usernbr = usernbr;
	}

	public String getCyberTypCd() {
		return cyberTypCd;
	}

	public void setCyberTypCd(String cyberTypCd) {
		this.cyberTypCd = cyberTypCd;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}





	

}
