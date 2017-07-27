/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.util.StringUtil;

/**
 * @author xujin
 * 发往fundprocess的退货交易请求类 
 *
 */
public class ReqRefound extends ReqFundProHead {

	private String origSeqNbr;
	private String checkNumber;   //对账分类编号
	private String refoundMode;   //退款方式 
	private String note; //备用字段
	private String realAmt;
	private String deductAmt;
	private String interalFlag;
	private String pointReacctdeptNbr;
	private String payTypCd;
	//扫码支付相关信息
	private String refundReason;//退款原因 
	
	private String operatorId;//操作员编号
	
	private String storeId;//门店编号 
	
	private String termId;//终端编号
	
	private String codeTypCd;//二维码类别
	
	public ReqRefound(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.Refound);
		this.setRefoundMode(data.getRefundmode());
		this.setCyberTypCd(data.getCybertypcd());
		this.setOrigSeqNbr(data.getOrigseqnbr());
		this.setInteralFlag(data.getInteralflag());
		this.setDeductAmt(StringUtil.parseObjectToString(data.getDeductamt()));
		this.setRealAmt(StringUtil.parseObjectToString(data.getRealamt()));
		this.setPointReacctdeptNbr(data.getPointreacctdeptnbr());
		this.setPayTypCd(data.getPaytypcd());
		this.setRefundReason(data.getRefundReason());
		this.setOperatorId(data.getOperatorId());
		this.setStoreId(data.getStoreId());
		this.setTermId(data.getTermId());
		this.setCodeTypCd(data.getCodetypcd());
	}
	
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRefoundMode() {
		return refoundMode;
	}

	public void setRefoundMode(String refoundMode) {
		this.refoundMode = refoundMode;
	}

	public String getOrigSeqNbr() {
		return origSeqNbr;
	}

	public void setOrigSeqNbr(String origSeqNbr) {
		this.origSeqNbr = origSeqNbr;
	}

	public String getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(String deductAmt) {
		this.deductAmt = deductAmt;
	}

	public String getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(String realAmt) {
		this.realAmt = realAmt;
	}

	public String getInteralFlag() {
		return interalFlag;
	}

	public void setInteralFlag(String interalFlag) {
		this.interalFlag = interalFlag;
	}

	public String getPointReacctdeptNbr() {
		return pointReacctdeptNbr;
	}

	public void setPointReacctdeptNbr(String pointReacctdeptNbr) {
		this.pointReacctdeptNbr = pointReacctdeptNbr;
	}

	public String getPayTypCd() {
		return payTypCd;
	}

	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
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

	public String getCodeTypCd() {
		return codeTypCd;
	}

	public void setCodeTypCd(String codeTypCd) {
		this.codeTypCd = codeTypCd;
	}
	
}
