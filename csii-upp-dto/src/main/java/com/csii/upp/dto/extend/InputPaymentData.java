package com.csii.upp.dto.extend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.CurrencyCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.ProcStatus;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Mercusttransctrl;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

public class InputPaymentData extends Onlinetrans {
	public InputPaymentData() {
	}
	
	public static InputPaymentData parseInputData(Transexceptionreg excepReg) {
		InputPaymentData input = BeanUtils.xmlStringToBean(excepReg.getExptransdatasnap(), InputPaymentData.class);
		input.setTransexcepseqnbr(excepReg.getExpseqnbr());
		input.setTransdate(excepReg.getTransdate());
		return input;
	}

	/**
	 * 交易前数据准备
	 * 
	 * @param inputData
	 * @return
	 * @throws PeException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InputPaymentData(Map inputData) throws PeException {
		Map<String, Object> map = inputData;
		this.setTransdate((Date) map.get(Dict.POST_DATE));
		this.setTranstime(DateUtil.getCurrentDateTime());
		this.setCleardate(this.getTransdate());
		// 交易渠道-前端传送-01个人网银02企业网银
		this.setChannelnbr(StringUtil.parseObjectToString(map.get(Dict.CHANNEL_NBR)));
		// 支付方式
		this.setPaytypcd(StringUtil.parseObjectToString(map.get(Dict.PAY_TYP_CD)));
		// 部门号，根据部门号分别对账文件
		this.setDepartmentnbr(StringUtil.parseObjectToString(map.get(Dict.DEPARTMENT_NBR)));
		this.setCustomName(StringUtil.parseObjectToString(map.get(Dict.CUSTOM_NAME)));
		this.setCustomTyp(StringUtil.parseObjectToString(map.get(Dict.CUSTOM_TYP)));
		this.setPhoneNo(StringUtil.parseObjectToString(map.get(Dict.PHONE_NO)));
		this.setCertTyp(StringUtil.parseObjectToString(map.get(Dict.CERT_TYP)));
		this.setCertNo(StringUtil.parseObjectToString(map.get(Dict.CERT_NO)));
		this.setCardTyp(StringUtil.parseObjectToString(map.get(Dict.CARD_TYP)));
		this.setCardNo(StringUtil.parseObjectToString(map.get(Dict.CARD_NO)));
		// 交易类型-支付00：支付 01：退货 02：提现
		this.setTranstypcd(StringUtil.parseObjectToString(map.get(Dict.TRANS_TYP_CD)));
		this.setMerseqnbr(StringUtil.parseObjectToString(map.get(Dict.MER_SEQ_NBR)));
		// 商户时间-前端传送-时间格式yyyyMMddhhmmss
		this.setMertransdatetime(DateUtil.DateTimeFormat_To_Date(map.get(Dict.MER_TRANS_DATE_TIME)));
		this.setMertransdate(DateUtil.DateFormat_To_Date(
				DateUtil.Date_To_DateTimeFormat(this.getMertransdatetime(), DateFormatCode.DATE_FORMATTER1)));
		// 原商户流水号-前端传送
		this.setOrigmerseqnbr(StringUtil.parseObjectToString(map.get(Dict.ORIG_MER_SEQ_NBR)));
		// 原商户交易日期-前端传送
		this.setOrigmerdate(DateUtil.DateFormat_To_Date(map.get(Dict.ORIG_MER_DATE)));
		this.setMernbr(StringUtil.parseObjectToString(map.get(Dict.MER_NBR)));
		// 客户号-前端传送
		this.setCustcifnbr(StringUtil.parseObjectToString(map.get(Dict.CUST_CIF_NBR)));
		this.setPayeracctnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NBR)));
		this.setPayeracctname(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NAME)));
		this.setPayercardtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_TYP_CD)));
		this.setPayeraccttypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_TYP_CD)));
		this.setPayeracctkind(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_KIND)));
		// 付款账户机构-前端传送
		this.setPayeracctdeptnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_DEPT_NBR)));
		// 证件号码-前端传送
		this.setPayeridnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_NBR)));
		// 证件类型-前端传送-TODO-参照网银
		this.setPayeridtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_TYP_CD)));
		this.setTransamt(StringUtil.parseBigDecimal(map.get(Dict.TRANS_AMT)));
		// 交易密码
		this.setPayercardpwd(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_PWD)));
		this.setPayercardcvv2(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_CVV2)));
		this.setPayercardexpireddate(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_EXPIRED_DATE)));
		this.setCurrencycd(CurrencyCode.CNY);
		this.setTransamt3(BigDecimal.ZERO);
		this.setTransamt4(this.getTransamt());
		// 已退金额
		this.setRefundedamt(BigDecimal.ZERO);
		// 未退货的金额
		this.setUnrefundamt(this.getTransamt());
		// 手续费金额
		this.setFeeamt(BigDecimal.ZERO);
		// 交易处理状态默认为交易处理中
		this.setProcstatus(ProcStatus.PENDING);
		// 交易手机号
		this.setPayerphoneno(StringUtil.parseObjectToString(map.get(Dict.PAYER_PHONE_NO)));
		// 交易阶段默认为未对账
		this.setProcstep(ProcStep.Init);
		// 请求系统
		this.setSystemcode(StringUtil.parseObjectToString(map.get(Dict.SYSTEM_CODE)));
		// 备用字段1：商户结果通知地址
		this.setMemo1(StringUtil.parseObjectToString(map.get(Dict.MER_URL)));
		// 商户跳转连接地址
		this.setMerurl1(StringUtil.parseObjectToString(map.get(Dict.MER_URL1)));
		if (CardTypCd.CREDIT.equals(this.getPayercardtypcd())) {
			this.setPayercardexpireddate(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_EXPIRED_DATE)));
			this.setPayercardcvv2(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_CVV2)));
		}
		//收款人账户号
		this.setPayeeacctnbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NBR)));
		this.setTransId(StringUtil.parseObjectToString(map.get(Dict.TRANS_ID)));
		this.setCybertypcd(StringUtil.parseObjectToString(map.get(Dict.CYBER_TYP_CD)));
		this.setTransseqnbr(StringUtil.parseObjectToString(map.get(Dict.TRANS_SEQ_NBR)));
		this.setTransstatus(StringUtil.parseObjectToString(map.get(Dict.TRANS_STATUS)));
		this.setSmsCode(StringUtil.parseObjectToString(map.get(Dict.SMS_CODE)));
		this.setSmsInnerFundTransNbr(StringUtil.parseObjectToString(map.get(Dict.SMS_INNER_FUND_TRANS_NBR)));
		this.setSendUnionPayTime(StringUtil.parseObjectToString(map.get(Dict.SEND_UNION_PAY_TIME)));
		this.setPerDayLimit(StringUtil.parseBigDecimal(map.get(Dict.PER_DAY_LIMIT)));
		this.setPerTransLimit(StringUtil.parseBigDecimal(map.get(Dict.PER_TRANS_LIMIT)));
		this.setPayeebanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_BANK_NBR)));
		this.setPayeeacctname(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NAME)));
		this.setNote(StringUtil.parseObjectToString(map.get(Dict.MSG_EXT)));
		this.setPayerbanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_BANK_NBR)));
		this.setFrontCallBackUrl(StringUtil.parseObjectToString(map.get(Dict.FRONT_CALL_BACK_URL)));
		//积分字段
		this.setInnerCardFlag(StringUtil.parseObjectToString(map.get(Dict.INNER_CARD_FLAG)));
		this.setDeductamt(StringUtil.parseBigDecimal(map.get(Dict.DEDUCT_AMT)));
		this.setInteralflag(StringUtil.parseObjectToString(map.get(Dict.INTERAL_FLAG)));
		this.setPointReacctdeptNbr(StringUtil.parseObjectToString(map.get(Dict.POINT_REACCTDEPT_NBR)));
		this.setBranchno(StringUtil.parseObjectToString(map.get(Dict.BRANCH_NO)));
		this.setClientno(StringUtil.parseObjectToString(map.get(Dict.CLIENT_NO)));
		this.setActMemo(StringUtil.parseObjectToString(map.get(Dict.ACT_MEMO)));
		//二维码字段
		this.setCodetypcd(StringUtil.parseObjectToString(map.get(Dict.CODE_TYP_CD)));
		this.setScancodemode(StringUtil.parseObjectToString(map.get(Dict.SCAN_CODE_MODE)));
		this.setQrcodeordernbr(StringUtil.parseObjectToString(map.get(Dict.QRCODEORDERNBR)));
		this.setIsCredit(StringUtil.parseObjectToString(map.get(Dict.IS_CREDIT)));
		this.setTimeStart(StringUtil.parseObjectToString(map.get(Dict.TIME_START)));
		this.setTimeExpire(StringUtil.parseObjectToString(map.get(Dict.TIME_EXPIRE)));
		this.setCustomerIp(StringUtil.parseObjectToString(map.get(Dict.CUSTOMER_IP)));
		this.setProductInfoDetail(StringUtil.parseObjectToString(map.get(Dict.PRODUCT_INFO_DETAIL)));
		this.setProductId(StringUtil.parseObjectToString(map.get(Dict.PRODUCT_ID)));
		this.setProPody(StringUtil.parseObjectToString(map.get(Dict.PRO_BODY)));
		this.setDiscountableamt(StringUtil.parseBigDecimal(map.get(Dict.DISCOUNTABLEAMT)));
		this.setSubject(StringUtil.parseObjectToString(map.get(Dict.SUBJECT)));
		this.setOperatorId(StringUtil.parseObjectToString(map.get(Dict.OPERATOR_ID)));
		this.setStoreId(StringUtil.parseObjectToString(map.get(Dict.STORE_ID)));
		this.setRefundReason(StringUtil.parseObjectToString(map.get(Dict.REFUND_REASON)));
		this.setTermId(StringUtil.parseObjectToString(map.get(Dict.TERM_ID)));
		this.setTimeoutExpress(StringUtil.parseObjectToString(map.get(Dict.TIMEOUT_EXPRESS)));
		this.setAlipayStoreId(StringUtil.parseObjectToString(map.get(Dict.ALIPAY_STORE_ID)));
		this.setScene(StringUtil.parseObjectToString(map.get(Dict.SCENE)));
		this.setAuthCode(StringUtil.parseObjectToString(map.get(Dict.AUTH_CODE)));
		this.setGoodsDetail(StringUtil.parseObjectToString(map.get(Dict.GOODS_DETAIL)));
		this.setReceiptamt(StringUtil.parseBigDecimal(map.get(Dict.RECEIPT_AMOUNT)));
		
		this.setTransTime(StringUtil.parseObjectToString(map.get(Dict.MER_TRANS_DATE_TIME)));
		this.setTrsferONum(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NBR)));
		this.setTrsferOName(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NAME)));
		this.setTrsferOBank(StringUtil.parseObjectToString(map.get(Dict.PAYER_BANK_NBR)));
		this.setTrsferINum(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NBR)));
		this.setTrsferIName(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NAME)));
		this.setTrsferIBank(StringUtil.parseObjectToString(map.get(Dict.PAYEE_BANK_NBR)));
		this.setTransAmount(StringUtil.parseBigDecimal(map.get(Dict.TRANS_AMT)));
		this.setCuurrency(StringUtil.parseObjectToString(map.get(Dict.CURRENCY_CD)));
		this.setTranserDeptNbr(StringUtil.parseObjectToString(map.get(Dict.TRANSER_DEPT_NBR)));

		this.setProxySynType(StringUtil.parseObjectToString(map.get(Dict.PROXY_SYN_TYPE)));
		this.setProxySynStatus(StringUtil.parseObjectToString(map.get(Dict.PROXY_SYN_STATUS)));
		this.setMertName(StringUtil.parseObjectToString(map.get("MertName")));
		this.setMerShortName(StringUtil.parseObjectToString(map.get(Dict.MER_SHORT_NAME)));
		this.setServicePhone(StringUtil.parseObjectToString(map.get(Dict.SERVICE_PHONE)));
		this.setContactName(StringUtil.parseObjectToString(map.get(Dict.CONTACT_NAME)));
		this.setContactMobile(StringUtil.parseObjectToString(map.get(Dict.CONTACT_MOBILE)));
		this.setContactPhone(StringUtil.parseObjectToString(map.get(Dict.CONTACT_PHONE)));
		this.setContactEmail(StringUtil.parseObjectToString(map.get(Dict.CONTACT_EMAIL)));
		this.setBusiness(StringUtil.parseObjectToString(map.get(Dict.BUSINESS)));
		this.setMerRemark(StringUtil.parseObjectToString(map.get(Dict.MER_REMARK)));
		this.setSubMerchantId(StringUtil.parseObjectToString(map.get(Dict.SUB_MERCHANT_ID)));
		this.setBizType(StringUtil.parseObjectToString(map.get(Dict.BIZ_TYPE)));		//财政网业务类型（01：个人（B2C）02：企业（B2B））
		this.setRequestModel(StringUtil.parseObjectToString(map.get(Dict.REQUEST_MODEL)));	//请求类型(01：支付，02：查询，03：撤消，04：退款)
		this.setTradeCode(StringUtil.parseObjectToString(map.get(Dict.TRADE_CODE)));	//代收机构编码
		this.setCifNo(StringUtil.parseObjectToString(map.get(Dict.CIF_NO)));
		this.setTerm(StringUtil.parseObjectToString(map.get(Dict.TERM)));
		this.setPayerAcctOrgDeptNbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_ORG_DEPT_NBR)));
	}

	private Custtransctrl custTransCtrl;
	private Mercusttransctrl merCustTransCtrl;
	private String transId; 
	private String merurl1;// 商户跳转连接地址
	private Onlinetrans origTrans;// 原交易明细信息用于退货交易
	private Onlinesubtrans origSubTrans;// 原子交易明细信息用于退货交易
	private List<SubTransData> onlineSubTransList; // 子订单列表
	private List<Map<String, String>> payeeAcctList; // 收款账户列表
	private boolean queryHistFlag = false;// 查询当前表还是历史表：true:历史表,false:当前表
	private Meracctinfo merAcct;
	private String smsCode;
	private String cardBinName;
	private String smsInnerFundTransNbr;
	private String sendUnionPayTime;
	private String payModeCd;
    private BigDecimal PerDayLimit;
    private BigDecimal PerTransLimit;
    private String note;
    private String frontCallBackUrl;
    private String innerCardFlag;
    private String pointReacctdeptNbr;
    private String transerDeptNbr; //转账机构号
    
    private String actMemo;
    
    private String transTime;
	private String trsferONum;
	private String trsferOName;
	private String trsferOBank;
	private String trsferINum;
	private String trsferIName;
	private String trsferIBank;
	private BigDecimal transAmount;
	private String cuurrency;
	//二维码相关字段
	private String isCredit;
	private String timeStart;
	private String timeExpire;
	private String customerIp;
	private String productInfoDetail;
	private String productId;
	private String proPody;
	private String subject;
	private String operatorId;
	private String storeId;
	private String termId;
	private String timeoutExpress;
	private String alipayStoreId;
	private String scene;
	private String authCode;
	private String goodsDetail;
	private String refundReason;//退款原因 
	
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
	private String alipayMerchantId;
	private String weChatSubMerchatId;
	private String cifNo; //客户号
	private String term;
	private String payerAcctOrgDeptNbr;
	private String merName;
	
	private String customName;// 客户姓名
	private String certTyp;// 证件类型
	private String certNo;// 证件编号
	private String cardTyp;// 卡类型
	private String cardNo;// 卡号
	private String customTyp;// 客户类型
	private String phoneNo;// 客户手机号
	
	
	
	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
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


	private String bizType;
	private String requestModel;
	private String tradeCode;
	
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
    public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getTrsferONum() {
		return trsferONum;
	}

	public void setTrsferONum(String trsferONum) {
		this.trsferONum = trsferONum;
	}

	public String getTrsferOName() {
		return trsferOName;
	}

	public void setTrsferOName(String trsferOName) {
		this.trsferOName = trsferOName;
	}

	public String getTrsferOBank() {
		return trsferOBank;
	}

	public void setTrsferOBank(String trsferOBank) {
		this.trsferOBank = trsferOBank;
	}

	public String getTrsferINum() {
		return trsferINum;
	}

	public void setTrsferINum(String trsferINum) {
		this.trsferINum = trsferINum;
	}

	public String getTrsferIName() {
		return trsferIName;
	}

	public void setTrsferIName(String trsferIName) {
		this.trsferIName = trsferIName;
	}

	public String getTrsferIBank() {
		return trsferIBank;
	}

	public void setTrsferIBank(String trsferIBank) {
		this.trsferIBank = trsferIBank;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public String getCuurrency() {
		return cuurrency;
	}

	public void setCuurrency(String cuurrency) {
		this.cuurrency = cuurrency;
	}

	public String getActMemo() {
		return actMemo;
	}

	public void setActMemo(String actMemo) {
		this.actMemo = actMemo;
	}

	public String getInnerCardFlag() {
		return innerCardFlag;
	}

	public void setInnerCardFlag(String innerCardFlag) {
		this.innerCardFlag = innerCardFlag;
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

	public String getSendUnionPayTime() {
		return sendUnionPayTime;
	}

	public void setSendUnionPayTime(String sendUnionPayTime) {
		this.sendUnionPayTime = sendUnionPayTime;
	}

	public String getCardBinName() {
		return cardBinName;
	}

	public void setCardBinName(String cardBinName) {
		this.cardBinName = cardBinName;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}


	public String getMerurl1() {
		return merurl1;
	}

	public void setMerurl1(String merurl1) {
		this.merurl1 = merurl1;
	}

	public Custtransctrl getCustTransCtrl() {
		return custTransCtrl;
	}

	public void setCustTransCtrl(Custtransctrl custTransCtrl) {
		this.custTransCtrl = custTransCtrl;
	}
	
	public Mercusttransctrl getMercusttransctrl() {
		return merCustTransCtrl;
	}

	public void setMercusttransctrl(Mercusttransctrl merCustTransCtrl) {
		this.merCustTransCtrl = merCustTransCtrl;
	}

	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;  
	}

	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}


	public List<SubTransData> getOnlineSubTransList() {
		return onlineSubTransList;
	}

	public void setOnlineSubTransList(List<SubTransData> onlineSubTransList) {
		this.onlineSubTransList = onlineSubTransList;
	}

	public boolean isQueryHistFlag() {
		return queryHistFlag;
	}

	public void setQueryHistFlag(boolean queryHistFlag) {
		this.queryHistFlag = queryHistFlag;
	}

	public Onlinetrans getOrigTrans() {
		return origTrans;
	}

	public void setOrigTrans(Onlinetrans origTrans) {
		this.origTrans = origTrans;
	}

	public Onlinesubtrans getOrigSubTrans() {
		return origSubTrans;
	}

	public void setOrigSubTrans(Onlinesubtrans origSubTrans) {
		this.origSubTrans = origSubTrans;
	}

	public Meracctinfo getMerAcct() {
		return merAcct;
	}

	public void setMerAcct(Meracctinfo merAcct) {
		this.merAcct = merAcct;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPayModeCd() {
		return payModeCd;
	}

	public void setPayModeCd(String payModeCd) {
		this.payModeCd = payModeCd;
	}

	public BigDecimal getPerDayLimit() {
		return PerDayLimit;
	}

	public void setPerDayLimit(BigDecimal perDayLimit) {
		PerDayLimit = perDayLimit;
	}

	public BigDecimal getPerTransLimit() {
		return PerTransLimit;
	}

	public void setPerTransLimit(BigDecimal perTransLimit) {
		PerTransLimit = perTransLimit;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPointReacctdeptNbr() {
		return pointReacctdeptNbr;
	}

	public void setPointReacctdeptNbr(String pointReacctdeptNbr) {
		this.pointReacctdeptNbr = pointReacctdeptNbr;
	}
	
	public String getTranserDeptNbr() {
		return transerDeptNbr;
	}

	public void setTranserDeptNbr(String transerDeptNbr) {
		this.transerDeptNbr = transerDeptNbr;
	}

	public Mercusttransctrl getMerCustTransCtrl() {
		return merCustTransCtrl;
	}

	public void setMerCustTransCtrl(Mercusttransctrl merCustTransCtrl) {
		this.merCustTransCtrl = merCustTransCtrl;
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

	public String getProPody() {
		return proPody;
	}

	public void setProPody(String proPody) {
		this.proPody = proPody;
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

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getMerShortName() {
		return merShortName;
	}

	public void setMerShortName(String merShortName) {
		this.merShortName = merShortName;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
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

	public String getProxySynType() {
		return proxySynType;
	}

	public void setProxySynType(String proxySynType) {
		this.proxySynType = proxySynType;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getMerRemark() {
		return merRemark;
	}

	public void setMerRemark(String merRemark) {
		this.merRemark = merRemark;
	}

	public String getSubMerchantId() {
		return subMerchantId;
	}

	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}

	public String getAlipayMerchantId() {
		return alipayMerchantId;
	}

	public void setAlipayMerchantId(String alipayMerchantId) {
		this.alipayMerchantId = alipayMerchantId;
	}

	public String getWeChatSubMerchatId() {
		return weChatSubMerchatId;
	}

	public void setWeChatSubMerchatId(String weChatSubMerchatId) {
		this.weChatSubMerchatId = weChatSubMerchatId;
	}

	
	
}
