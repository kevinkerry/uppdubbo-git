package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.ConstFdps;
import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dto.extend.InputPaymentData;
/**
 * 虚账户转账交易入参
 * @author 徐锦
 *
 */
public class ReqVirtualAcctTransfer  extends ReqFundProHead {

	public ReqVirtualAcctTransfer(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.VirtualAcctTransfer);
		if(TransTypCd.PAY.equals(data.getTranstypcd())){
			this.setProcessMode(ConstFdps.PROCESSMODE_PAY);
		}else if(TransTypCd.WTH.equals(data.getTranstypcd())){
			this.setProcessMode(ConstFdps.PROCESSMODE_WTH);
		}
		this.setNote(data.getNote());
		this.setPayTypCd(data.getPaytypcd());
		this.setTranserDeptNbr(data.getTranserDeptNbr());
	}
	
	private String processMode; //处理类别
	private String checkNumber;   //对账分类编号
	private String note; //备用字段
	private String payTypCd;	//支付方式
	private String transerDeptNbr;//机构号
	
	public String getProcessMode() {
		return processMode;
	}
	public void setProcessMode(String processMode) {
		this.processMode = processMode;
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
	public String getPayTypCd() {
		return payTypCd;
	}
	public void setPayTypCd(String payTypCd) {
		this.payTypCd = payTypCd;
	}

	public String getTranserDeptNbr() {
		return transerDeptNbr;
	}
	public void setTranserDeptNbr(String transerDeptNbr) {
		this.transerDeptNbr = transerDeptNbr;
	}
	
}
