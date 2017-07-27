package com.csii.upp.dto.router.eaccount;

import java.util.Date;

import com.csii.upp.constant.EAccountTransCode;
import com.csii.upp.dto.extend.InputFundData;
/**
 * 抹账
 * @author xujin
 *
 */
public class ReqRtxnErrorCorrect extends ReqEAccountHead{

	public ReqRtxnErrorCorrect(InputFundData data) {
		super(data);
		this.setTransCode(EAccountTransCode.RtxnErrorCorrect);
		this.rtxnSourceCd=this.getBaseRequest().getSourceId();
		this.extTxnDate=data.getOrigtransdate();
		this.extTxnId=data.getOriginnertransnbr();
	}
	
	private String extTxnId;//外部渠道流水号
	private String rtxnSourceCd;//渠道类型
	private Date extTxnDate;//外部渠道日期
	public String getExtTxnId() {
		return extTxnId;
	}
	public void setExtTxnId(String extTxnId) {
		this.extTxnId = extTxnId;
	}
	public String getRtxnSourceCd() {
		return rtxnSourceCd;
	}
	public void setRtxnSourceCd(String rtxnSourceCd) {
		this.rtxnSourceCd = rtxnSourceCd;
	}
	public Date getExtTxnDate() {
		return extTxnDate;
	}
	public void setExtTxnDate(Date extTxnDate) {
		this.extTxnDate = extTxnDate;
	}
}
