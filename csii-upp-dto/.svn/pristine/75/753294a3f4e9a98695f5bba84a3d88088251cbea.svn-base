package com.csii.upp.dto.router.unionpay;

import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.DateUtil;

/**
 *  银联对账文件申请需求 
 * 
 *  @author Chenyanpeng
 */
public class ReqCheckFileApply extends ReqUnionPayHead {

	private String settleDate; // 渠道类型
	private String fileType; // 渠道类型
	 
	public ReqCheckFileApply(InputFundData data) {
		super(data);
		this.setTxnType("76");
		this.setTxnSubType("01");
		this.setBizType("000000");
		this.setFileType("00");
		this.setSettleDate(DateUtil.Date_To_DateTimeFormat(data.getTransdate(), "MMdd"));
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

 
}
