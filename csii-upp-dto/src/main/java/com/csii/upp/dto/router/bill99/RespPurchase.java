package com.csii.upp.dto.router.bill99;

import java.math.BigDecimal;

public class RespPurchase extends RespBill99Head {
	BigDecimal amount;
	String cardOrg;
	String issuer;
	String storableCardNo;
	String authorizationCode;
	String miExt;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getStorableCardNo() {
		return storableCardNo;
	}

	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getMiExt() {
		return miExt;
	}

	public void setMiExt(String miExt) {
		this.miExt = miExt;
	}
}
