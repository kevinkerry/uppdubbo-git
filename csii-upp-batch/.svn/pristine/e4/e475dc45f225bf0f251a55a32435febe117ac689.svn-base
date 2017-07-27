package com.csii.upp.batch.event.handler;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.ProcessState;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dao.generate.PreprocessfundtransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.Preprocessfundtrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.event.EventHandler;
import com.csii.upp.service.fundprocess.EAccountService;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * 待处理交易
 * 
 * @author 徐锦
 * 
 */
public class PreprocessfundtransHandler implements
		EventHandler<PreprocessfundtransEvent> {

	protected final static Log log = LogFactory
			.getLog(RtxnExcepEventHandler.class);
	
	public void handler(PreprocessfundtransEvent event) {
		Preprocessfundtrans rtxn = event.getPreprocessfundtrans();
		String fundChannelCd = event.getFundChannelCd();
		Overalltrans record = new Overalltrans();
		 
		if(FundChannelCode.BEPS.equals(rtxn.getFundchannelcode()))//小额来帐
		{ 
			 record.setOveralltranstypcd( OveralltransTyp.STXNBEPS);
		}
		if(FundChannelCode.HVPS.equals(rtxn.getFundchannelcode()))//大额来帐
		{
			 record.setOveralltranstypcd( OveralltransTyp.STXNHVPS);
		}
		if(FundChannelCode.IBPS.equals(rtxn.getFundchannelcode()))//超网来帐
		{
			record.setOveralltranstypcd(OveralltransTyp.STXNIBPS);
		}
		if (FundChannelCode.DPC.equals(rtxn.getFundchannelcode())) {// 大同城来帐
			record.setOveralltranstypcd(OveralltransTyp.STXNDPC);
		}
		record.setUppertransnbr(rtxn.getDowntransnbr());
		record.setUppersysnbr(rtxn.getFundchannelcode());
		record.setUppertransdate(rtxn.getDowntransdate());
		record.setTransdate(SysinfoExtendDAO.getSysInfo().getPostdate());
		record.setOveralltransstatus(TransStatus.INIT);
		try {
	    	record.setOveralltransnbr(IDGenerateFactory.generateRtxnNbr());
			OveralltransDAO.insertSelective(record);
		} catch (SQLException e1) {
			log.error(e1.getMessage());
		}
		InputFundData inputDate = new InputFundData(rtxn, record);
		inputDate.setFundchannelcode(fundChannelCd);
		inputDate.setTransdate(SysinfoExtendDAO.getSysInfo().getPostdate());
		inputDate.setCheckdataflag(rtxn.getFundchannelcode());
		RespSysHead output = null;
		if (FundChannelCode.EACCOUNT.equals(fundChannelCd)) {
			EAccountService eaccount = (EAccountService) DefaultSupportor
					.getService(fundChannelCd.toLowerCase());
			try {
					output = eaccount.PreprocessAcctRecharge(inputDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		try {
			rtxn.setDealstatus(ProcessState.PROCED);
			PreprocessfundtransDAO.updateByPrimaryKeySelective(rtxn);
			if (output == null) {
				record.setOveralltransstatus(TransStatus.FAILURE);
//				record.setCheckstate(CheckState.UNCHECKED);
				//退汇交易更新原交易流水状态为失败
				if(rtxn.getOrigdowntransnbr() != null){
					Innerfundtrans Innerfundtrans = new Innerfundtrans();
					Innerfundtrans.setInnerfundtransnbr(rtxn.getOrigdowntransnbr());
					Innerfundtrans.setTransstatus(TransStatus.FAILURE);
					Innerfundtrans.setCheckstatus(CheckStatus.UNCHECKED);
					InnerfundtransDAO.updateByPrimaryKeySelective(Innerfundtrans);
					
					
				}
			} else {
				record.setOveralltransstatus(output.isSuccess() ? TransStatus.SUCCESS
						: (output.isFailure() ? TransStatus.FAILURE
								: TransStatus.TIMEOUT));
				//退汇交易更新原交易流水状态为成功
				if(rtxn.getOrigdowntransnbr() != null){
					Innerfundtrans Innerfundtrans = new Innerfundtrans();
					Innerfundtrans.setInnerfundtransnbr(rtxn.getOrigdowntransnbr());
					Innerfundtrans.setTransstatus(TransStatus.FAILURE);
					Innerfundtrans.setCheckstatus(CheckStatus.UNCHECKED);
					InnerfundtransDAO.updateByPrimaryKeySelective(Innerfundtrans);
				}
				
			}
			OveralltransDAO.updateByPrimaryKeySelective(record);
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public Class<PreprocessfundtransEvent> getAcceptedEventType() {
		return PreprocessfundtransEvent.class;
	}

}
