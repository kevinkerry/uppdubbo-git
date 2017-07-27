package com.csii.upp.dto.router.unionpay;

import com.csii.upp.dto.extend.InputFundData;

public class ReqFileDownload extends ReqUnionPayHead {
	private String settleDate;
	private String fileType;
	
	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public ReqFileDownload(InputFundData data) {
		super(data);
		setTxnType("76");
		setTxnSubType("01");
		setBizType("000000");
		setAccessType("0");
		setSettleDate("0119");
		setFileType("00");
	}

}
