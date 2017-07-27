package com.csii.upp.batch.event.handler;

import com.csii.upp.dto.generate.Preprocessfundtrans;
import com.csii.upp.event.Event;

public class PreprocessfundtransEvent  extends Event {
	private String fundChannelCd;
	private  Preprocessfundtrans Preprocessfundtrans;
	public String getFundChannelCd() {
		return fundChannelCd;
	}

	public void setFundchannelcode(String fundChannelCd) {
		this.fundChannelCd = fundChannelCd;
	}
	public Preprocessfundtrans getPreprocessfundtrans() {
		return Preprocessfundtrans;
	}
	public void setPreprocessfundtrans(Preprocessfundtrans Preprocessfundtrans) {
		this.Preprocessfundtrans = Preprocessfundtrans;
	}
}
