package com.csii.upp.custom.common.api.data.payment;

import java.io.Serializable;
import java.util.List;


import com.csii.upp.custom.common.api.data.base.PaymentReqHead;

public class PayTransReq extends PaymentReqHead {
	private static final long serialVersionUID = -9076835580257882901L;
    private String payerAcctNbr; 
    private String payerAcctOrgDeptNbr; 
    private String smsCode; 
    private String payerCardTypCd; 
    private String payerPhoneNo; 
    private String custCifNbr; 
    private String merSeqNbr; 
    private String actMemo; 
    private String transAmt; 
    private String merTransDateTime; 
    private String currencyCd; 
    private String custEmail; 
    private String merUrl; 
    private String merUrl1; 
    private String payIp; 
    private String msgExt; 
    private String payerCardPwd; 
    private String payerCardCvv2; 
    private String payerCardExpiredDate; 
    private String payerAcctDeptNbr; 
    private String payerAcctName; 
    private String cyberTypCd; 
    private String smsInnerFundTransNbr; 
    private String sendUnionPayTime; 
    private String payerBankNbr; 
    private String frontCallBackUrl; 
    private String deductAmt; 
    private String bizType; 
    private String requestModel; 
    private String tradeCode; 
    private String branchNo; 
    private String clientNo; 
    private String interalFlag; 
    private String scanCodeMode; 
    private String codeTypCd; 
    private String discountableamt; 
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
    private String cifNo; 
    private String term; 
    private List<MerTransDetail> merTransList; 
    

    public static class MerTransDetail implements Serializable{


		private static final long serialVersionUID = 4632728689550828705L;

		 private String subMerchantId;
		 private String subMerSeqNo;
		 private String productInfo;
		 private String subTransAmt;
		 private String subMerDateTime;
		 private String subMerImport;
		 
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
		public String getProductInfo() {
			return productInfo;
		}
		public void setProductInfo(String productInfo) {
			this.productInfo = productInfo;
		}
		public String getSubTransAmt() {
			return subTransAmt;
		}
		public void setSubTransAmt(String subTransAmt) {
			this.subTransAmt = subTransAmt;
		}
		public String getSubMerDateTime() {
			return subMerDateTime;
		}
		public void setSubMerDateTime(String subMerDateTime) {
			this.subMerDateTime = subMerDateTime;
		}
		public String getSubMerImport() {
			return subMerImport;
		}
		public void setSubMerImport(String subMerImport) {
			this.subMerImport = subMerImport;
		}
		
    }
    
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setPayerAcctOrgDeptNbr(String payerAcctOrgDeptNbr) {
        this.payerAcctOrgDeptNbr = payerAcctOrgDeptNbr;
    }
    public String getPayerAcctOrgDeptNbr() {
        return payerAcctOrgDeptNbr;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    public String getSmsCode() {
        return smsCode;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setCustCifNbr(String custCifNbr) {
        this.custCifNbr = custCifNbr;
    }
    public String getCustCifNbr() {
        return custCifNbr;
    }

    public void setMerSeqNbr(String merSeqNbr) {
        this.merSeqNbr = merSeqNbr;
    }
    public String getMerSeqNbr() {
        return merSeqNbr;
    }

    public void setActMemo(String actMemo) {
        this.actMemo = actMemo;
    }
    public String getActMemo() {
        return actMemo;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setMerTransDateTime(String merTransDateTime) {
        this.merTransDateTime = merTransDateTime;
    }
    public String getMerTransDateTime() {
        return merTransDateTime;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
    public String getCustEmail() {
        return custEmail;
    }

    public void setMerUrl(String merUrl) {
        this.merUrl = merUrl;
    }
    public String getMerUrl() {
        return merUrl;
    }

    public void setMerUrl1(String merUrl1) {
        this.merUrl1 = merUrl1;
    }
    public String getMerUrl1() {
        return merUrl1;
    }

    public void setPayIp(String payIp) {
        this.payIp = payIp;
    }
    public String getPayIp() {
        return payIp;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }
    public String getMsgExt() {
        return msgExt;
    }

    public void setPayerCardPwd(String payerCardPwd) {
        this.payerCardPwd = payerCardPwd;
    }
    public String getPayerCardPwd() {
        return payerCardPwd;
    }

    public void setPayerCardCvv2(String payerCardCvv2) {
        this.payerCardCvv2 = payerCardCvv2;
    }
    public String getPayerCardCvv2() {
        return payerCardCvv2;
    }

    public void setPayerCardExpiredDate(String payerCardExpiredDate) {
        this.payerCardExpiredDate = payerCardExpiredDate;
    }
    public String getPayerCardExpiredDate() {
        return payerCardExpiredDate;
    }

    public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
        this.payerAcctDeptNbr = payerAcctDeptNbr;
    }
    public String getPayerAcctDeptNbr() {
        return payerAcctDeptNbr;
    }

    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }
    public String getPayerAcctName() {
        return payerAcctName;
    }

    public void setCyberTypCd(String cyberTypCd) {
        this.cyberTypCd = cyberTypCd;
    }
    public String getCyberTypCd() {
        return cyberTypCd;
    }

    public void setSmsInnerFundTransNbr(String smsInnerFundTransNbr) {
        this.smsInnerFundTransNbr = smsInnerFundTransNbr;
    }
    public String getSmsInnerFundTransNbr() {
        return smsInnerFundTransNbr;
    }

    public void setSendUnionPayTime(String sendUnionPayTime) {
        this.sendUnionPayTime = sendUnionPayTime;
    }
    public String getSendUnionPayTime() {
        return sendUnionPayTime;
    }

    public void setPayerBankNbr(String payerBankNbr) {
        this.payerBankNbr = payerBankNbr;
    }
    public String getPayerBankNbr() {
        return payerBankNbr;
    }

    public void setFrontCallBackUrl(String frontCallBackUrl) {
        this.frontCallBackUrl = frontCallBackUrl;
    }
    public String getFrontCallBackUrl() {
        return frontCallBackUrl;
    }

    public void setDeductAmt(String deductAmt) {
        this.deductAmt = deductAmt;
    }
    public String getDeductAmt() {
        return deductAmt;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
    public String getBizType() {
        return bizType;
    }

    public void setRequestModel(String requestModel) {
        this.requestModel = requestModel;
    }
    public String getRequestModel() {
        return requestModel;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }
    public String getTradeCode() {
        return tradeCode;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }
    public String getBranchNo() {
        return branchNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }
    public String getClientNo() {
        return clientNo;
    }

    public void setInteralFlag(String interalFlag) {
        this.interalFlag = interalFlag;
    }
    public String getInteralFlag() {
        return interalFlag;
    }

    public void setScanCodeMode(String scanCodeMode) {
        this.scanCodeMode = scanCodeMode;
    }
    public String getScanCodeMode() {
        return scanCodeMode;
    }

    public void setCodeTypCd(String codeTypCd) {
        this.codeTypCd = codeTypCd;
    }
    public String getCodeTypCd() {
        return codeTypCd;
    }

    public void setDiscountableamt(String discountableamt) {
        this.discountableamt = discountableamt;
    }
    public String getDiscountableamt() {
        return discountableamt;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit;
    }
    public String getIsCredit() {
        return isCredit;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }
    public String getTimeExpire() {
        return timeExpire;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }
    public String getCustomerIp() {
        return customerIp;
    }

    public void setProductInfoDetail(String productInfoDetail) {
        this.productInfoDetail = productInfoDetail;
    }
    public String getProductInfoDetail() {
        return productInfoDetail;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductId() {
        return productId;
    }

    public void setProBody(String proBody) {
        this.proBody = proBody;
    }
    public String getProBody() {
        return proBody;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getSubject() {
        return subject;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public String getOperatorId() {
        return operatorId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getStoreId() {
        return storeId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }
    public String getTermId() {
        return termId;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }
    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setAlipayStoreId(String alipayStoreId) {
        this.alipayStoreId = alipayStoreId;
    }
    public String getAlipayStoreId() {
        return alipayStoreId;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
    public String getScene() {
        return scene;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    public String getAuthCode() {
        return authCode;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }
    public String getCifNo() {
        return cifNo;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    public String getTerm() {
        return term;
    }
	public List<MerTransDetail> getMerTransList() {
		return merTransList;
	}
	public void setMerTransList(List<MerTransDetail> merTransList) {
		this.merTransList = merTransList;
	}

}
