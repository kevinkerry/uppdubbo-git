package com.csii.upp.dto.router.bill99;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.csii.upp.constant.ConstBill99;
import com.csii.upp.dto.extend.InputFundData;

public class ReqPurchase extends ReqBill99Head {

	public ReqPurchase(InputFundData data) {
		super(data);
		setTxnType(ConstBill99.TXNTYPE_PURCHASE);
		setInteractiveStatus(ConstBill99.INTERACTIVESTATUS_TR1);
		setCardNoEn(data.getPayeracctnbr());
		setExpiredDate(data.getPayercardexpireddate());
		setCvv2En(data.getPayercardcvv2());
		setAmount(data.getTransamt());
		setCardHolderId(data.getPayeridnbr());
		setCardHolderName(data.getPayername());
		setIdType(data.getPayeridtypcd());
		setEntryTime(data.getUppertranstime());
		setSpFlag(ConstBill99.SPFLAG_CNPAY01);

		Map<String, Object> extData = new HashMap<String, Object>();
		extData.put(ConstBill99.EXTDATA_PHONE, data.getPayerphoneno());
		setExtData(extData);
	}

	private RespCheckRight checkright;

	public RespCheckRight getCheckright() {
		return checkright;
	}

	public void setCheckright(RespCheckRight checkright) {
		this.checkright = checkright;
		Map<String, Object> extData = getExtData();
		extData.put(ConstBill99.EXTDATA_VALIDCODE,
				checkright.getReturnmsg());
		extData.put(ConstBill99.EXTDATA_SAVEPCIFLAG,
				ConstBill99.EXTDATA_SAVEPCIFLAG_NO);
		extData.put(ConstBill99.EXTDATA_TOKEN, checkright.getToken());
		extData.put(ConstBill99.EXTDATA_PAYBATCH,
				ConstBill99.EXTDATA_PAYBATCH_FIRST);

	}

	String cardNoEn;
	String expiredDate;
	String cvv2En;
	BigDecimal amount;
	String cardHolderId;
	String cardHolderName;
	String idType;
	String pin;
	String issuerCountry;
	String siteType;
	String siteID;
	String dynPin;
	String serialNo;
	String spFlag;
	String ext;
	String ext1;
	Map extData;
	String storableCardNo;
	String tr3Url;
	String supCardFlag;
	String miExt;
	String bankId;
	String rifleMap;

	public String getCardNoEn() {
		return cardNoEn;
	}

	public void setCardNoEn(String cardNoEn) {
		this.cardNoEn = cardNoEn;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getCvv2En() {
		return cvv2En;
	}

	public void setCvv2En(String cvv2En) {
		this.cvv2En = cvv2En;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCardHolderId() {
		return cardHolderId;
	}

	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getIssuerCountry() {
		return issuerCountry;
	}

	public void setIssuerCountry(String issuerCountry) {
		this.issuerCountry = issuerCountry;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public String getDynPin() {
		return dynPin;
	}

	public void setDynPin(String dynPin) {
		this.dynPin = dynPin;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSpFlag() {
		return spFlag;
	}

	public void setSpFlag(String spFlag) {
		this.spFlag = spFlag;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public Map getExtData() {
		return extData;
	}

	public void setExtData(Map extData) {
		this.extData = extData;
	}

	public String getStorableCardNo() {
		return storableCardNo;
	}

	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}

	public String getTr3Url() {
		return tr3Url;
	}

	public void setTr3Url(String tr3Url) {
		this.tr3Url = tr3Url;
	}

	public String getSupCardFlag() {
		return supCardFlag;
	}

	public void setSupCardFlag(String supCardFlag) {
		this.supCardFlag = supCardFlag;
	}

	public String getMiExt() {
		return miExt;
	}

	public void setMiExt(String miExt) {
		this.miExt = miExt;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getRifleMap() {
		return rifleMap;
	}

	public void setRifleMap(String rifleMap) {
		this.rifleMap = rifleMap;
	}
}
