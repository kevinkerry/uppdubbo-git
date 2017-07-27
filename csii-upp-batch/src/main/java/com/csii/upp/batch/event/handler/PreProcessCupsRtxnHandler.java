package com.csii.upp.batch.event.handler;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.constant.ConstCups;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ProcessState;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.PreprocessfundtransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Preprocessfundtrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.event.EventHandler;
import com.csii.upp.service.fundprocess.EAccountService;
import com.csii.upp.supportor.DefaultSupportor;

public class PreProcessCupsRtxnHandler implements
	EventHandler<PreProcessCupsRtxnEvent> {
	
	protected final static Log log = LogFactory
			.getLog(PreProcessCupsRtxnHandler.class);
	
	@Override
	public void handler(PreProcessCupsRtxnEvent event) {
		// TODO Auto-generated method stub
		Preprocessfundtrans rtxn = event.getPreprocessfundtrans();
		if(ConstCups.IERR.equals(rtxn.getDealtypcd())){
			return;
		}
		String fundChannelCd = event.getFundChannelCd();
		InputFundData inputDate = new InputFundData();
		inputDate.setTransamt(rtxn.getTransamt());
		inputDate.setInnerfundtransnbr(rtxn.getProcesstransnbr());
		inputDate.setFundchannelcode(fundChannelCd);
		inputDate.setTransdate(SysinfoExtendDAO.getSysInfo().getPostdate());
		inputDate.setCheckdataflag(rtxn.getFundchannelcode());
		
		if (FundChannelCode.EACCOUNT.equals(fundChannelCd)) {
			if(rtxn.getPayerfeeamt().compareTo(rtxn.getPayeefeeamt())!=0){
				boolean flag=false;
				if(rtxn.getPayerfeeamt().compareTo(rtxn.getPayeefeeamt())<0){
					flag=true;
				}
				EAccountService eaccount = (EAccountService) DefaultSupportor
						.getService(fundChannelCd.toLowerCase());
				try {
					RespSysHead output = eaccount.PreprocessCupsfundment(inputDate,flag);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
			rtxn.setDealstatus(ProcessState.PROCED);
			try {
				PreprocessfundtransDAO.updateByPrimaryKeySelective(rtxn);
			} catch (SQLException e) {
				log.error("error",e);
			}
		}
	}

	@Override
	public Class<PreProcessCupsRtxnEvent> getAcceptedEventType() {
		// TODO Auto-generated method stub
		return PreProcessCupsRtxnEvent.class;
	}

}
