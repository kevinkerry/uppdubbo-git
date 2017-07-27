package com.csii.upp.custom.common.api.data.fundprocess;

import com.csii.upp.custom.common.api.data.base.FundProcessRespHead;

public class UPAYResp extends FundProcessRespHead {
	private static final long serialVersionUID = -6252367095036031984L;
    private String returnForm; 
    private String hostClearDate; 
    private String transDate; 
    private String codeUrl; 
    private String qrcodeordernbr; 
    private String payerAcctNbr; 
    private String receiptAmount; 
    private String transStatus; 
    public void setReturnForm(String returnForm) {
        this.returnForm = returnForm;
    }
    public String getReturnForm() {
        return returnForm;
    }

    public void setHostClearDate(String hostClearDate) {
        this.hostClearDate = hostClearDate;
    }
    public String getHostClearDate() {
        return hostClearDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    public String getTransDate() {
        return transDate;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
    public String getCodeUrl() {
        return codeUrl;
    }

    public void setQrcodeordernbr(String qrcodeordernbr) {
        this.qrcodeordernbr = qrcodeordernbr;
    }
    public String getQrcodeordernbr() {
        return qrcodeordernbr;
    }

    public void setPayerAcctNbr(String payerAcctNbr) {
        this.payerAcctNbr = payerAcctNbr;
    }
    public String getPayerAcctNbr() {
        return payerAcctNbr;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }
    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }
    public String getTransStatus() {
        return transStatus;
    }


}
