package com.csii.upp.batch.event.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.event.EventHandler;

public class QueryCodePayStatusHandler implements EventHandler<QueryCodePayStatusEvent> {
	protected static Log log = LogFactory.getLog(QueryCodePayStatusHandler.class);

	@Override
	public void handler(QueryCodePayStatusEvent event) {
		Innerfundtrans innerfundtrans = event.getInnerfundtrans();
		if(innerfundtrans!=null){
			InputFundData input=buildInputDate(innerfundtrans);
			input.setTimeout(event.isTimeout());
			RespSysHead output=null;
			try{
				if(FundChannelCode.WECHATCODE.equals(innerfundtrans.getFundchannelcode())){
					output=event.getWechatService().weChatQrcodeTransTimeOut(input);
				}else if(FundChannelCode.ALIPAYCODE.equals(innerfundtrans.getFundchannelcode())){
					output=event.getAlipayService().alipayQrcodeTransTimeOut(input);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public Class<QueryCodePayStatusEvent> getAcceptedEventType() {
		return QueryCodePayStatusEvent.class;
	}
	private static InputFundData buildInputDate(Innerfundtrans innerfundtrans) {
		InputFundData inputData = new InputFundData();
		inputData.setInnerfundtransnbr(innerfundtrans.getInnerfundtransnbr());
		inputData.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
		inputData.setTranstime(innerfundtrans.getTranstime());
		inputData.setMerId(innerfundtrans.getPayeeacctnbr());
		inputData.setUppertransnbr(innerfundtrans.getUppertransnbr());
		return inputData;
	}

}
