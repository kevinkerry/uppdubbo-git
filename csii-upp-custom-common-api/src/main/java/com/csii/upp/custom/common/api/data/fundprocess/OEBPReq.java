package com.csii.upp.custom.common.api.data.fundprocess;

import java.io.Serializable;
import java.util.List;

import com.csii.upp.custom.common.api.data.base.FundProcessReqHead;

public class OEBPReq extends FundProcessReqHead {
	private static final long serialVersionUID = -6205150259510127429L;
    private String payerAcctNbr; 
    private String currencyCd; 
    private String payerCardPwd; 
    private String checkNumber; 
    private String transAmt; 
    private String note; 
    private String payerPhoneNo; 
    private String payerCardTypCd; 
    private String payerAcctTypCd; 
    private String payerAcctKind; 
    private String custCifNo; 
    private String payerCardCvv2; 
    private String payerCardExpiredDate; 
    private String payerAcctName; 
    private String payerAcctDeptNbr; 
    private String cyberTypCd; 
    private String payerBankNbr; 
    private String frontCallBackUrl; 
    private List<PayeeAcct> payeeAcctList; 
    private String subTransAmt; 
    private String payeeOrgNbr; 
    private String payeeAcctNbr; 
    private String payeeCardTypCd; 
    private String payeeAcctTypCd; 
    private String payeeAcctKind; 
    
    public static class PayeeAcct implements Serializable{

		private static final long serialVersionUID = 3688157279471418861L;
		private String subTransAmt;
    	private String payeeOrgNbr;
    	private String payeeAcctNbr;
    	private String payeeCardTypCd;
    	private String payeeAcctTypCd;
    	private String payeeAcctKind;
		public String getSubTransAmt() {
			return subTransAmt;
		}
		public void setSubTransAmt(String subTransAmt) {
			this.subTransAmt = subTransAmt;
		}
		public String getPayeeOrgNbr() {
			return payeeOrgNbr;
		}
		public void setPayeeOrgNbr(String payeeOrgNbr) {
			this.payeeOrgNbr = payeeOrgNbr;
		}
		public String getPayeeAcctNbr() {
			return payeeAcctNbr;
		}
		public void setPayeeAcctNbr(String payeeAcctNbr) {
			this.payeeAcctNbr = payeeAcctNbr;
		}
		public String getPayeeCardTypCd() {
			return payeeCardTypCd;
		}
		public void setPayeeCardTypCd(String payeeCardTypCd) {
			this.payeeCardTypCd = payeeCardTypCd;
		}
		public String getPayeeAcctTypCd() {
			return payeeAcctTypCd;
		}
		public void setPayeeAcctTypCd(String payeeAcctTypCd) {
			this.payeeAcctTypCd = payeeAcctTypCd;
		}
		public String getPayeeAcctKind() {
			return payeeAcctKind;
		}
		public void setPayeeAcctKind(String payeeAcctKind) {
			this.payeeAcctKind = payeeAcctKind;
		}
    	
    }
    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setPayerCardPwd(String payerCardPwd) {
        this.payerCardPwd = payerCardPwd;
    }
    public String getPayerCardPwd() {
        return payerCardPwd;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }
    public String getCheckNumber() {
        return checkNumber;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    public String getTransAmt() {
        return transAmt;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getNote() {
        return note;
    }

    public void setPayerPhoneNo(String payerPhoneNo) {
        this.payerPhoneNo = payerPhoneNo;
    }
    public String getPayerPhoneNo() {
        return payerPhoneNo;
    }

    public void setPayerCardTypCd(String payerCardTypCd) {
        this.payerCardTypCd = payerCardTypCd;
    }
    public String getPayerCardTypCd() {
        return payerCardTypCd;
    }

    public void setPayerAcctTypCd(String payerAcctTypCd) {
        this.payerAcctTypCd = payerAcctTypCd;
    }
    public String getPayerAcctTypCd() {
        return payerAcctTypCd;
    }

    public void setPayerAcctKind(String payerAcctKind) {
        this.payerAcctKind = payerAcctKind;
    }
    public String getPayerAcctKind() {
        return payerAcctKind;
    }

    public void setCustCifNo(String custCifNo) {
        this.custCifNo = custCifNo;
    }
    public String getCustCifNo() {
        return custCifNo;
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

    public void setPayerAcctName(String payerAcctName) {
        this.payerAcctName = payerAcctName;
    }
    public String getPayerAcctName() {
        return payerAcctName;
    }

    public void setPayerAcctDeptNbr(String payerAcctDeptNbr) {
        this.payerAcctDeptNbr = payerAcctDeptNbr;
    }
    public String getPayerAcctDeptNbr() {
        return payerAcctDeptNbr;
    }

    public void setCyberTypCd(String cyberTypCd) {
        this.cyberTypCd = cyberTypCd;
    }
    public String getCyberTypCd() {
        return cyberTypCd;
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

    public void setPayeeAcctList(List<PayeeAcct> payeeAcctList) {
        this.payeeAcctList = payeeAcctList;
    }
    public List<PayeeAcct> getPayeeAcctList() {
        return payeeAcctList;
    }

    public void setSubTransAmt(String subTransAmt) {
        this.subTransAmt = subTransAmt;
    }
    public String getSubTransAmt() {
        return subTransAmt;
    }

    public void setPayeeOrgNbr(String payeeOrgNbr) {
        this.payeeOrgNbr = payeeOrgNbr;
    }
    public String getPayeeOrgNbr() {
        return payeeOrgNbr;
    }

    public void setPayeeAcctNbr(String payeeAcctNbr) {
        this.payeeAcctNbr = payeeAcctNbr;
    }
    public String getPayeeAcctNbr() {
        return payeeAcctNbr;
    }

    public void setPayeeCardTypCd(String payeeCardTypCd) {
        this.payeeCardTypCd = payeeCardTypCd;
    }
    public String getPayeeCardTypCd() {
        return payeeCardTypCd;
    }

    public void setPayeeAcctTypCd(String payeeAcctTypCd) {
        this.payeeAcctTypCd = payeeAcctTypCd;
    }
    public String getPayeeAcctTypCd() {
        return payeeAcctTypCd;
    }

    public void setPayeeAcctKind(String payeeAcctKind) {
        this.payeeAcctKind = payeeAcctKind;
    }
    public String getPayeeAcctKind() {
        return payeeAcctKind;
    }


}
