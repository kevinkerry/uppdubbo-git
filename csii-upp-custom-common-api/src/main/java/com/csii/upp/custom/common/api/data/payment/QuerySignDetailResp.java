package com.csii.upp.custom.common.api.data.payment;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.csii.upp.custom.common.api.data.base.PaymentRespHead;

public class QuerySignDetailResp extends PaymentRespHead {
	private static final long serialVersionUID = -8913142128528186163L;
	private String payerPhoneNo;
	private String payerAcctName;
	private List<Card> cardList;

	public static class Card implements Serializable {
		private static final long serialVersionUID = 9112051690088160855L;
		private String payerAcctNbr;
		private String payerAcctDeptNbr;
		private String payerAcctNo;
		private String payTypCd;
	}

	public void setPayerPhoneNo(String payerPhoneNo) {
		this.payerPhoneNo = payerPhoneNo;
	}

	public String getPayerPhoneNo() {
		return payerPhoneNo;
	}

	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}

	public String getPayerAcctName() {
		return payerAcctName;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

}
