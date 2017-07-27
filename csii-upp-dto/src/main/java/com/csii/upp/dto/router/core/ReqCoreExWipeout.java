/**
 * 
 */
package com.csii.upp.dto.router.core;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.TransKind;
import com.csii.upp.dto.extend.InputFundData;

/**
 * @author WY
 * 核心异常账务处理
 */
public class ReqCoreExWipeout extends ReqCoreHead {
	private String transKind;
	private String origTransnbr;
	private String downTransNbr;
	public ReqCoreExWipeout(InputFundData data) {
		super(data);
		this.setReceiver(ConstCore.RECEIVER_CORE);
		StringBuilder checkNumberSb=new StringBuilder();
		checkNumberSb.append(ConstCore.CHANNELNO).append(FundChannelCode.FDPS);
		checkNumberSb.append("D");
		this.setCheckNumber(checkNumberSb.append(this.getReqDate()).toString());
		this.setTransCode(CoreTransCode.CoreExWipeout);
		this.setDownTransNbr(data.getDowntransnbr());
		this.setOrigTransnbr(data.getOriginnertransnbr());
		data.setPointdataflag(InteralFlag.YES);
		data.setCheckdataflag(FundChannelCode.CORE);
		data.setInnerfundtransnbr(this.getReqSeqNo());
		this.setTransKind(TransKind.COREEXWIPEOUT);
		if(OveralltransTyp.UPAY.equals(data.getTransid())){
			data.setPayeracctnbr(null);
			data.setPayeeacctnbr(data.getPointReacctdeptNbr());
		}else{
			data.setPayeracctnbr(data.getPointReacctdeptNbr());
			data.setPayeeacctnbr(data.getPayeracctnbr());
		}
	}
	public String getTransKind() {
		return transKind;
	}
	public void setTransKind(String transKind) {
		this.transKind = transKind;
	}
	public String getDownTransNbr() {
		return downTransNbr;
	}
	public void setDownTransNbr(String downTransNbr) {
		this.downTransNbr = downTransNbr;
	}
	public String getOrigTransnbr() {
		return origTransnbr;
	}
	public void setOrigTransnbr(String origTransnbr) {
		this.origTransnbr = origTransnbr;
	}
}
