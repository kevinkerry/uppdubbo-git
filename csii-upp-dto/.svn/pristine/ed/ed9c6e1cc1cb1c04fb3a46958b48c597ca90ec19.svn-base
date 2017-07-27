package com.csii.upp.dto.extend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Innerpreclearfundtrans;
import com.csii.upp.dto.generate.Overallrequestreg;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.Preprocessfundtrans;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 资金服务调用入口参数
 * 
 * @author 徐锦
 * 
 */
public class InputFundData extends Innerfundtrans {
	private String payeracctdeptnbr;// 付款人开户机构
	private String payercardtypcd;//付款人卡类型
	private String payeraccttypcd;// 付款人账号类型
	private String payeracctkind;  //付款人账户性质
	private String payeeacctdeptnbr;// 收款人开户机构
	private String payeeacctkind;//收款人账户性质
	private String payeecardtypcd;//收款人卡类型
	private String payeeaccttypcd;// 收款人账号类型
	private String transid;// 交易代码
	private String origoverralltransnbr;// 原总交易流水号
	private String origdowntransnbr;// 原下游交易流水号
	private Date origtransdate;// 原交易日期
	private Date origtranstime;// 原交易时间
	private String contractno;// 签约号
	private String contractacctnbr;// 签约账号
	private String mernbr; // 商户代码
	private String checkdataflag; // 对账数据标识
	private String msgid;// 报文id
	private String note;// 备注
	private List<Map<String, String>> payeeAcctList;// 同一机构子订单汇总
	private String origUpperSubTransNbr;
	private Date hostcleardate;
	private String processMode;
	private String cyberTypCd;
	private String smsCode;// 短信码
	private String checkpwdflag;// 检查密码标志
	private String smsInnerFundTransNbr;
	private String sendUnionPayTime;
	private String settleseqnbr;
	private String merId;//银联商户ID
	private String bizType;
	private String channelNbr;
	private String frontCallBackUrl;
	private String filename;
	private String IP;
	private String Port;
	private String UserName;
	private String Password;
	private String RemotePath;
	private String fileaccepter;
	private String LocalPath;
	private String paytypcd;
	private BigDecimal deductAmt;//本次积分抵用金额
	private BigDecimal realAmt;//实际支付金额 
	private String branchNo;//行社号
	private String clientNo;//客户内码
	private String interalFlag;//积分标识
	private String pointReacctdeptNbr;
	private String transerDeptNbr;
	private String codetypcd;//二维码类型
	private String scancodemode;//扫码模式
	private String qrcodeordernbr;//原二维码生成订单号
	private BigDecimal discountableamt;//可打折金额
	private String isCredit;//是否支持信用卡
	private String timeStart;//订单开始时间
	private String timeExpire;//订单截止时间
	private String customerIp;//微信终端号
	private String productInfoDetail;//商品信息列表
	private String productId;//商品号
	private String proBody;//商品描述
	private String subject;//订单标题
	private String operatorId;//商户操作员号
	private String storeId;//商户门店号
	private String termId;//终端号
	private String timeoutExpress;//订单允许支付时间
	private String alipayStoreId;//支付宝门店号
	private String scene;//支付场景
	private String authCode;//支付授权码
	private String goodsDetail;//商品详情
	private String refundReason;//退款原因 
	private String initTxnSeqId; //原二维码前置消费流水号
	private String initTxnTime; //原二维码前置交易时间
	private String refundAmount;//退款金额
	private String outRequestNo;//标识一次退款请求,同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	private String subMerchantId;//二级商户号
	private String mertName;	
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
	private String receiptAmount;//实收金额
	private boolean timeout; //是否超时
	private String transtypcd;//交易类型
	private String branchNbr;
	private String bankOrgNbr;
	private String cifNo;
	private String customerId;
	private BigDecimal tranAmt;
	private String payMethod;
	private String term;
	private String orderNbr;
	private String rtxnseq;
	
	
	
	public String getRtxnseq() {
		return rtxnseq;
	}
	public void setRtxnseq(String rtxnseq) {
		this.rtxnseq = rtxnseq;
	}
	private String customname;
	public String getCustomname() {
		return customname;
	}
	public void setCustomname(String customname) {
		this.customname = customname;
	}
	public String getCerttyp() {
		return certtyp;
	}
	public void setCerttyp(String certtyp) {
		this.certtyp = certtyp;
	}
	public String getCertno() {
		return certno;
	}
	public void setCertno(String certno) {
		this.certno = certno;
	}
	public String getCardtyp() {
		return cardtyp;
	}
	public void setCardtyp(String cardtyp) {
		this.cardtyp = cardtyp;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCustomtyp() {
		return customtyp;
	}
	public void setCustomtyp(String customtyp) {
		this.customtyp = customtyp;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	private String certtyp;
	private String certno;
	private String cardtyp;
	private String cardno;
	private String customtyp;
	private String phoneno;
	


	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public String getTranstypcd() {
		return transtypcd;
	}
	public void setTranstypcd(String transtypcd) {
		this.transtypcd = transtypcd;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getClientNo() {
		return clientNo;
	}
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	public String getLocalPath() {
		return LocalPath;
	}

	public void setLocalPath(String localPath) {
		LocalPath = localPath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRemotePath() {
		return RemotePath;
	}

	public void setRemotePath(String remotePath) {
		RemotePath = remotePath;
	}

	public String getFileaccepter() {
		return fileaccepter;
	}

	public void setFileaccepter(String fileaccepter) {
		this.fileaccepter = fileaccepter;
	}
	
	public String getFrontCallBackUrl() {
		return frontCallBackUrl;
	}

	public void setFrontCallBackUrl(String frontCallBackUrl) {
		this.frontCallBackUrl = frontCallBackUrl;
	}

	public String getChannelNbr() {
		return channelNbr;
	}

	public void setChannelNbr(String channelNbr) {
		this.channelNbr = channelNbr;
	}

	public String getSmsInnerFundTransNbr() {
		return smsInnerFundTransNbr;
	}

	public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
		this.smsInnerFundTransNbr = smsInnerFundTransNbr;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSendUnionPayTime() {
		return sendUnionPayTime;
	}

	public void setSendUnionPayTime(String sendUnionPayTime) {
		this.sendUnionPayTime = sendUnionPayTime;
	}

	public String getCheckpwdflag() {
		return checkpwdflag;
	}

	public void setCheckpwdflag(String checkpwdflag) {
		this.checkpwdflag = checkpwdflag;
	}

	public String getCyberTypCd() {
		return cyberTypCd;
	}

	public void setCyberTypCd(String cyberTypCd) {
		this.cyberTypCd = cyberTypCd;
	}

	public Date getHostcleardate() {
		return hostcleardate;
	}

	public void setHostcleardate(Date hostcleardate) {
		this.hostcleardate = hostcleardate;
	}

	public InputFundData() {
		// TODO Auto-generated constructor stub
	}

	public String getOrigUpperSubTransNbr() {
		return origUpperSubTransNbr;
	}

	public void setOrigUpperSubTransNbr(String origUpperSubTransNbr) {
		this.origUpperSubTransNbr = origUpperSubTransNbr;
	}

	public static InputFundData parseInputData(Overallrequestreg reg) {
		return BeanUtils.xmlStringToBean(reg.getUpperregsnap(), InputFundData.class);
	}

	public static InputFundData parseInputData(Transexceptionreg excepReg) {
		InputFundData input = BeanUtils.xmlStringToBean(excepReg.getExptransdatasnap(), InputFundData.class);
		input.setTransexcepseqnbr(excepReg.getExpseqnbr());
		input.setFundchannelcode(excepReg.getFundchannelcode());
		input.setInnerfundtransnbr(excepReg.getInnerfundtransnbr());
		input.setTransdate(excepReg.getTransdate());
		return input;
	}

	// private String getGetterName(Field field) {
	// StringBuilder sb = new StringBuilder();
	// String name = field.getName();
	// name = name.substring(0, 1).toUpperCase() + name.substring(1);
	// if (field.getType() == boolean.class) {
	// sb.append("is").append(name);
	// } else
	// sb.append("get").append(name);
	// return sb.toString();
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InputFundData(Map inputData) throws PeException {

		Map<String, Object> tmpMap = inputData;
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : tmpMap.entrySet()) {
			map.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		this.transid = StringUtil.parseObjectToString(map.get(Dict.TRANS_ID.toLowerCase()));
		this.setUppersysnbr(StringUtil.parseObjectToString(map.get(Dict.CHNL_ID.toLowerCase())));
		this.setUppertransdate(DateUtil.DateFormat_To_Date(map.get(Dict.REQ_DATE.toLowerCase())));
		this.setOrigUpperSubTransNbr(
				StringUtil.parseObjectToString(map.get(Dict.ORIG_UPPER_SUB_TRANS_NBR.toLowerCase())));
		this.setUppertransnbr(StringUtil.parseObjectToString(map.get(Dict.REQ_SEQ_NO.toLowerCase())));
		this.setUppertranstime(DateUtil.DateTimeFormat_To_Date(map.get(Dict.REQ_TIME.toLowerCase())));
		this.setTransdate(DateUtil.DateFormat_To_Date(map.get(Dict.POST_DATE.toLowerCase())));
		this.setTranstime(DateUtil.DateFormat_To_Date(map.get(Dict.TRAN_TIME.toLowerCase())));
		this.origoverralltransnbr = StringUtil.parseObjectToString(map.get(Dict.ORIG_OVERRALL_TRANS_NBR.toLowerCase()));
		this.setOriguppertransnbr(StringUtil.parseObjectToString(map.get(Dict.ORIG_UPPER_TRANS_NBR.toLowerCase())));
		this.setOriguppersysnbr(StringUtil.parseObjectToString(map.get(Dict.ORIG_UPPER_SYS_NBR.toLowerCase())));
		this.setOriguppertransdate(DateUtil.DateFormat_To_Date(map.get(Dict.ORIG_UPPER_TRANS_DATE.toLowerCase())));
		this.setHostcleardate(DateUtil.DateFormat_To_Date(map.get(Dict.HOST_CLEAR_DATE.toLowerCase())));
		this.setOveralltransnbr(StringUtil.parseObjectToString(map.get(Dict.OVERALL_TRANS_NBR.toLowerCase())));
		this.setInnerfundtransnbr((StringUtil.parseObjectToString(map.get(Dict.INNER_FUND_TRANS_NBR.toLowerCase()))));
		this.setTransamt(StringUtil.parseBigDecimal(map.get(Dict.TRANS_AMT.toLowerCase()))); // 交易金额
		this.setFeeamt(StringUtil.parseBigDecimal(map.get(Dict.FEE_AMT.toLowerCase())));
		this.setCurrencycd(StringUtil.parseObjectToString(map.get(Dict.CURRENCY_CD.toLowerCase())));

		this.setPayerbanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_BANK_NBR.toLowerCase())));
		this.setPayeracctdeptnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_DEPT_NBR.toLowerCase())));
		this.setPayeraccttypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_TYP_CD.toLowerCase())));
		this.setPayeracctnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NBR.toLowerCase())));
		this.setPayername(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_NAME.toLowerCase())));
		this.setPayercardpwd(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_PWD.toLowerCase())));
		this.setPayercardexpireddate(
				StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_EXPIRED_DATE.toLowerCase())));
		this.setPayercardcvv2(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_CVV2.toLowerCase())));
		this.setPayeridnbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_NBR.toLowerCase())));
		this.setPayeridtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_ID_TYP_CD.toLowerCase())));
		this.setPayerphoneno(StringUtil.parseObjectToString(map.get(Dict.PAYER_PHONE_NO.toLowerCase())));
		this.setPayercardtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYER_CARD_TYP_CD.toLowerCase())));
		this.setPayeracctkind(StringUtil.parseObjectToString(map.get(Dict.PAYER_ACCT_KIND.toLowerCase())));
		this.setSmsCode(StringUtil.parseObjectToString(map.get(Dict.SMS_CODE.toLowerCase())));
		this.setPayeebanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_BANK_NBR.toLowerCase())));
		this.setPayeeacctdeptnbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_DEPT_NBR.toLowerCase())));
		this.setPayeeaccttypcd(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_TYP_CD.toLowerCase())));
		this.setPayeeacctnbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NBR.toLowerCase())));
		this.setPayeename(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_NAME.toLowerCase())));
		this.setPayeecardpwd(StringUtil.parseObjectToString(map.get(Dict.PAYEE_CARD_PWD.toLowerCase())));
		this.setPayeecardexpireddate(
				StringUtil.parseObjectToString(map.get(Dict.PAYEE_CARD_EXPIRED_DATE.toLowerCase())));
		this.setPayeecardcvv2(StringUtil.parseObjectToString(map.get(Dict.PAYEE_CARD_CVV2.toLowerCase())));
		this.setPayeeidnbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ID_NBR.toLowerCase())));
		this.setPayeeidtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ID_TYP_CD.toLowerCase())));
		this.setPayeephoneno(StringUtil.parseObjectToString(map.get(Dict.PAYEE_PHONE_NO.toLowerCase())));
		this.setPayeecardtypcd(StringUtil.parseObjectToString(map.get(Dict.PAYEE_CARD_TYP_CD.toLowerCase())));
		this.setPayeeacctkind(StringUtil.parseObjectToString(map.get(Dict.PAYEE_ACCT_KIND.toLowerCase())));
		this.setPayeebanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYEE_BANK_NBR.toLowerCase())));
		this.msgid = StringUtil.parseObjectToString(map.get(Dict.MSG_ID.toLowerCase()));
		this.note = StringUtil.parseObjectToString(map.get(Dict.NOTE.toLowerCase()));
		this.checkdataflag = StringUtil.parseObjectToString(map.get(Dict.CHECK_DATA_FLAG.toLowerCase()));
		this.setPayeeAcctList((List) map.get(Dict.PAYEE_ACCT_LIST.toLowerCase()));
		this.setCyberTypCd(StringUtil.parseObjectToString(map.get(Dict.CYBER_TYP_CD.toLowerCase())));
		this.setProcessMode(StringUtil.parseObjectToString(map.get(Dict.PROCESS_MODE.toLowerCase())));
		this.setCheckpwdflag(StringUtil.parseObjectToString(map.get(Dict.CHECK_CARD_PWD_FLAG.toLowerCase())));
		this.setSmsInnerFundTransNbr(StringUtil.parseObjectToString(map.get(Dict.SMS_INNER_FUND_TRANS_NBR.toLowerCase())));
		this.setSendUnionPayTime(StringUtil.parseObjectToString(map.get(Dict.SEND_UNION_PAY_TIME.toLowerCase())));
		this.setPayerbanknbr(StringUtil.parseObjectToString(map.get(Dict.PAYER_BANK_NBR.toLowerCase())));
		this.setChannelNbr(StringUtil.parseObjectToString(map.get(Dict.CHANNEL_NBR.toLowerCase())));
		this.setFrontCallBackUrl(StringUtil.parseObjectToString(map.get(Dict.FRONT_CALL_BACK_URL.toLowerCase())));
		this.setPaytypcd(StringUtil.parseObjectToString(map.get(Dict.PAY_TYP_CD.toLowerCase())));
		this.setDeductAmt(StringUtil.parseBigDecimal(map.get(Dict.DEDUCT_AMT.toLowerCase())));
		this.setBranchNo(StringUtil.parseObjectToString(map.get(Dict.BRANCH_NO.toLowerCase())));
		this.setClientNo(StringUtil.parseObjectToString(map.get(Dict.CLIENT_NO.toLowerCase())));
		this.setInteralFlag(StringUtil.parseObjectToString(map.get(Dict.INTERAL_FLAG.toLowerCase())));
		this.setRealAmt(StringUtil.parseBigDecimal(map.get(Dict.REAL_AMT.toLowerCase())));
		this.setPointReacctdeptNbr(StringUtil.parseObjectToString(map.get(Dict.POINT_REACCTDEPT_NBR.toLowerCase())));
		this.setPointdataflag(StringUtil.parseObjectToString(map.get(Dict.POINT_DATA_FLAG.toLowerCase())));
		this.setTranserDeptNbr(StringUtil.parseObjectToString(map.get(Dict.TRANSER_DEPT_NBR.toLowerCase())));
		//二维码相关字段
		this.setCodetypcd(StringUtil.parseObjectToString(map.get(Dict.CODE_TYP_CD.toLowerCase())));
		this.setScancodemode(StringUtil.parseObjectToString(map.get(Dict.SCAN_CODE_MODE.toLowerCase())));
		this.setQrcodeordernbr(StringUtil.parseObjectToString(map.get(Dict.QRCODEORDERNBR.toLowerCase())));
		this.setIsCredit(StringUtil.parseObjectToString(map.get(Dict.IS_CREDIT.toLowerCase())));
		this.setTimeStart(StringUtil.parseObjectToString(map.get(Dict.TIME_START.toLowerCase())));
		this.setTimeExpire(StringUtil.parseObjectToString(map.get(Dict.TIME_EXPIRE.toLowerCase())));
		this.setCustomerIp(StringUtil.parseObjectToString(map.get(Dict.CUSTOMER_IP.toLowerCase())));
		this.setProductInfoDetail(StringUtil.parseObjectToString(map.get(Dict.PRODUCT_INFO_DETAIL.toLowerCase())));
		this.setProductId(StringUtil.parseObjectToString(map.get(Dict.PRODUCT_ID.toLowerCase())));
		this.setProBody(StringUtil.parseObjectToString(map.get(Dict.PRO_BODY.toLowerCase())));
		this.setDiscountableamt(StringUtil.parseBigDecimal(map.get(Dict.DISCOUNTABLEAMT.toLowerCase())));
		this.setSubject(StringUtil.parseObjectToString(map.get(Dict.SUBJECT.toLowerCase())));
		this.setOperatorId(StringUtil.parseObjectToString(map.get(Dict.OPERATOR_ID.toLowerCase())));
		this.setStoreId(StringUtil.parseObjectToString(map.get(Dict.STORE_ID.toLowerCase())));
		this.setTermId(StringUtil.parseObjectToString(map.get(Dict.TERM_ID.toLowerCase())));
		this.setTimeoutExpress(StringUtil.parseObjectToString(map.get(Dict.TIMEOUT_EXPRESS.toLowerCase())));
		this.setAlipayStoreId(StringUtil.parseObjectToString(map.get(Dict.ALIPAY_STORE_ID.toLowerCase())));
		this.setScene(StringUtil.parseObjectToString(map.get(Dict.SCENE.toLowerCase())));
		this.setAuthCode(StringUtil.parseObjectToString(map.get(Dict.AUTH_CODE.toLowerCase())));
		this.setGoodsDetail(StringUtil.parseObjectToString(map.get(Dict.GOODS_DETAIL.toLowerCase())));
		this.setRefundReason(StringUtil.parseObjectToString(map.get(Dict.REFUND_REASON.toLowerCase())));
		
		this.setSubMerchantId(StringUtil.parseObjectToString(map.get(Dict.SUB_MERCHANT_ID.toLowerCase())));
		this.setMertName(StringUtil.parseObjectToString(map.get("mertname")));
		this.setMerShortName(StringUtil.parseObjectToString(map.get(Dict.MER_SHORT_NAME.toLowerCase())));
		this.setServicePhone(StringUtil.parseObjectToString(map.get(Dict.SERVICE_PHONE.toLowerCase())));
		this.setContactName(StringUtil.parseObjectToString(map.get(Dict.CONTACT_NAME.toLowerCase())));
		this.setContactMobile(StringUtil.parseObjectToString(map.get(Dict.CONTACT_MOBILE.toLowerCase())));
		this.setContactPhone(StringUtil.parseObjectToString(map.get(Dict.CONTACT_PHONE.toLowerCase())));
		this.setContactEmail(StringUtil.parseObjectToString(map.get(Dict.CONTACT_EMAIL.toLowerCase())));
		this.setBusiness(StringUtil.parseObjectToString(map.get(Dict.BUSINESS.toLowerCase())));
		this.setMerRemark(StringUtil.parseObjectToString(map.get(Dict.MER_REMARK.toLowerCase())));
		this.setAlipayMerchantId(StringUtil.parseObjectToString(map.get(Dict.ALIPAY_MERCHANT_ID.toLowerCase())));
		this.setWeChatSubMerchatId(StringUtil.parseObjectToString(map.get(Dict.WE_CHAT_SUB_MERCHAT_ID)));
		this.setTranstypcd(StringUtil.parseObjectToString(map.get(Dict.TRANS_TYP_CD)));
		this.setBranchNbr(StringUtil.parseObjectToString(map.get(Dict.BRANCHNBR)));
		this.setBankOrgNbr(StringUtil.parseObjectToString(map.get(Dict.BANKORGNBR)));
		this.setCifNo(StringUtil.parseObjectToString(map.get(Dict.CIF_NO)));
		this.setCustomerId(StringUtil.parseObjectToString(map.get("customerid")));
		this.setTranAmt(StringUtil.parseBigDecimal(map.get(Dict.TRANS_AMT)));
		this.setPayMethod(StringUtil.parseObjectToString(map.get("paymethod")));
		this.setTerm(StringUtil.parseObjectToString(map.get(Dict.TERM)));
		this.setOrderNbr(StringUtil.parseObjectToString(map.get(Dict.ORDER_NBR)));
	}

	/**
	 * 根据原资金流水初始化输入参数
	 * 
	 * @param orignnerfundrtxn
	 *            原内部交易流水
	 * @param isReplaceAcct
	 *            是否置换账号信息
	 */
	public void getInputDataByOrigInnerfundtrans(Innerfundtrans orignnerfundrtxn, boolean isReplaceAcct) {
		if (isReplaceAcct) {
			this.setPayeracctnbr(orignnerfundrtxn.getPayeeacctnbr()); //
			this.setPayername(orignnerfundrtxn.getPayeename());
			this.setPayeeacctnbr(orignnerfundrtxn.getPayeracctnbr());
			this.setPayeename(orignnerfundrtxn.getPayername());
		} else {
			this.setPayeracctnbr(orignnerfundrtxn.getPayeracctnbr());
			this.setPayername(orignnerfundrtxn.getPayername());
			this.setPayeeacctnbr(orignnerfundrtxn.getPayeeacctnbr());
			this.setPayeename(orignnerfundrtxn.getPayeename());
		}
		this.setCurrencycd(orignnerfundrtxn.getCurrencycd());
		this.setTransamt(orignnerfundrtxn.getTransamt());
		this.setFeeamt(orignnerfundrtxn.getFeeamt());
		this.origdowntransnbr = orignnerfundrtxn.getDowntransnbr();
		this.setOriginnertransnbr(orignnerfundrtxn.getInnerfundtransnbr());
		this.origtransdate = orignnerfundrtxn.getTransdate();
	}

	// 交换收款方和付款方账号
	public void exchangePaperAndPayeeAcctNbr() {
		String tmpStr = this.getPayeracctnbr();
		this.setPayeracctnbr(this.getPayeeacctnbr());
		this.setPayeeacctnbr(tmpStr);
		tmpStr = this.getPayername();
		this.setPayername(this.getPayeename());
		this.setPayeename(tmpStr);
		tmpStr = this.getPayeraccttypcd();
		this.setPayeraccttypcd(this.getPayeeaccttypcd());
		this.setPayeeaccttypcd(tmpStr);
	}

	public InputFundData(Preprocessfundtrans preRtxn, Overalltrans Overalltrans) {
		this.setTransdate(Overalltrans.getTransdate());
		this.setOveralltransnbr(Overalltrans.getOveralltransnbr());
		this.setTransamt(preRtxn.getTransamt());
		this.setCurrencycd(preRtxn.getCurrencycd());
		this.setPayeracctnbr(preRtxn.getPayeracctnbr());
		this.setPayername(preRtxn.getPayername());
		this.setPayeeacctnbr(preRtxn.getPayeeacctnbr());
		this.setPayeename(preRtxn.getPayeename());
	}

	public InputFundData(Innerpreclearfundtrans innerRtxn, Overalltrans Overalltrans) {
		this.setTransdate(Overalltrans.getTransdate());
		this.setOveralltransnbr(Overalltrans.getOveralltransnbr());
		this.setTransamt(innerRtxn.getTransamt());
		this.setCurrencycd(innerRtxn.getCurrencycd());
		this.setPayeracctnbr(innerRtxn.getPayeracctnbr());
		this.setPayername(innerRtxn.getPayername());
		this.setPayeeacctnbr(innerRtxn.getPayeeacctnbr());
		this.setPayeename(innerRtxn.getPayeename());
		this.setUppersysnbr(FundChannelCode.FDPS);
		this.setUppertransdate(innerRtxn.getUppertransdate());
		this.setUppertranstime(innerRtxn.getUppertranstime());
		this.setUppertransnbr(innerRtxn.getUppertransnbr());
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}


	public String getOrigoverralltransnbr() {
		return origoverralltransnbr;
	}

	public void setOrigoverralltransnbr(String origoverralltransnbr) {
		this.origoverralltransnbr = origoverralltransnbr;
	}

	public String getOrigdowntransnbr() {
		return origdowntransnbr;
	}

	public void setOrigdowntransnbr(String origdowntransnbr) {
		this.origdowntransnbr = origdowntransnbr;
	}

	public Date getOrigtransdate() {
		return origtransdate;
	}

	public void setOrigtransdate(Date origtransdate) {
		this.origtransdate = origtransdate;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getContractacctnbr() {
		return contractacctnbr;
	}

	public void setContractacctnbr(String contractacctnbr) {
		this.contractacctnbr = contractacctnbr;
	}

	public String getMernbr() {
		return mernbr;
	}

	public void setMernbr(String mernbr) {
		this.mernbr = mernbr;
	}

	public String getCheckdataflag() {
		return checkdataflag;
	}

	public void setCheckdataflag(String checkdataflag) {
		this.checkdataflag = checkdataflag;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}

	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}

	public String getProcessMode() {
		return processMode;
	}

	public void setProcessMode(String processMode) {
		this.processMode = processMode;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public BigDecimal getTranAmt() {
		return tranAmt;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public String getTerm() {
		return term;
	}
	public String getOrderNbr() {
		return orderNbr;
	}
	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}
	public String getSettleseqnbr() {
		return settleseqnbr;
	}

	public void setSettleseqnbr(String settleseqnbr) {
		this.settleseqnbr = settleseqnbr;
	}

	public Date getOrigtranstime() {
		return origtranstime;
	}

	public void setOrigtranstime(Date origtranstime) {
		this.origtranstime = origtranstime;
	}

	public String getPayeracctdeptnbr() {
		return payeracctdeptnbr;
	}

	public void setPayeracctdeptnbr(String payeracctdeptnbr) {
		this.payeracctdeptnbr = payeracctdeptnbr;
	}

	public String getPayercardtypcd() {
		return payercardtypcd;
	}

	public void setPayercardtypcd(String payercardtypcd) {
		this.payercardtypcd = payercardtypcd;
	}

	public String getPayeraccttypcd() {
		return payeraccttypcd;
	}

	public void setPayeraccttypcd(String payeraccttypcd) {
		this.payeraccttypcd = payeraccttypcd;
	}

	public String getPayeracctkind() {
		return payeracctkind;
	}

	public void setPayeracctkind(String payeracctkind) {
		this.payeracctkind = payeracctkind;
	}

	public String getPayeeacctdeptnbr() {
		return payeeacctdeptnbr;
	}

	public void setPayeeacctdeptnbr(String payeeacctdeptnbr) {
		this.payeeacctdeptnbr = payeeacctdeptnbr;
	}

	public String getPayeeacctkind() {
		return payeeacctkind;
	}

	public void setPayeeacctkind(String payeeacctkind) {
		this.payeeacctkind = payeeacctkind;
	}

	public String getPayeecardtypcd() {
		return payeecardtypcd;
	}

	public void setPayeecardtypcd(String payeecardtypcd) {
		this.payeecardtypcd = payeecardtypcd;
	}

	public String getPayeeaccttypcd() {
		return payeeaccttypcd;
	}

	public void setPayeeaccttypcd(String payeeaccttypcd) {
		this.payeeaccttypcd = payeeaccttypcd;
	}
	
	public BigDecimal getDeductAmt() {
		return deductAmt;
	}
	public void setDeductAmt(BigDecimal deductAmt) {
		this.deductAmt = deductAmt;
	}
	public String getInteralFlag() {
		return interalFlag;
	}
	public void setInteralFlag(String interalFlag) {
		this.interalFlag = interalFlag;
	}
	public BigDecimal getRealAmt() {
		return realAmt;
	}
	public void setRealAmt(BigDecimal realAmt) {
		this.realAmt = realAmt;
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
	
	public String getPaytypcd() {
		return paytypcd;
	}

	public void setPaytypcd(String paytypcd) {
		this.paytypcd = paytypcd;
	}
	public String getCodetypcd() {
		return codetypcd;
	}
	public void setCodetypcd(String codetypcd) {
		this.codetypcd = codetypcd;
	}
	public String getScancodemode() {
		return scancodemode;
	}
	public void setScancodemode(String scancodemode) {
		this.scancodemode = scancodemode;
	}
	public String getQrcodeordernbr() {
		return qrcodeordernbr;
	}
	public void setQrcodeordernbr(String qrcodeordernbr) {
		this.qrcodeordernbr = qrcodeordernbr;
	}
	public BigDecimal getDiscountableamt() {
		return discountableamt;
	}
	public void setDiscountableamt(BigDecimal discountableamt) {
		this.discountableamt = discountableamt;
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
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
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
	public String getInitTxnSeqId() {
		return initTxnSeqId;
	}
	public void setInitTxnSeqId(String initTxnSeqId) {
		this.initTxnSeqId = initTxnSeqId;
	}
	public String getInitTxnTime() {
		return initTxnTime;
	}
	public void setInitTxnTime(String initTxnTime) {
		this.initTxnTime = initTxnTime;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getOutRequestNo() {
		return outRequestNo;
	}
	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public boolean isTimeout() {
		return timeout;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
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
	public String getBranchNbr() {
		return branchNbr;
	}
	public String getBankOrgNbr() {
		return bankOrgNbr;
	}
	public void setBranchNbr(String branchNbr) {
		this.branchNbr = branchNbr;
	}
	public void setBankOrgNbr(String bankOrgNbr) {
		this.bankOrgNbr = bankOrgNbr;
	}
}
