package com.csii.upp.batch.appl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.event.handler.PreprocessfundtransEvent;
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
import com.csii.upp.dto.generate.PreprocessfundtransExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.fundprocess.EAccountService;

/**
 * 异步处理二代来账和退款交易
 * 
 * @author 徐锦
 * 
 */
public class PreProcessFundRtxnAppl extends BaseAppl {
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		// 获得未处理交易
		List<Preprocessfundtrans> preRtxn = null;
		try {
			PreprocessfundtransExample example = new PreprocessfundtransExample();
			example.createCriteria().andTransstatusEqualTo(
					ProcessState.PREPROC);
			preRtxn = PreprocessfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException(
					"Get Preprocessfundtrans Table Failed.", e);
		}
		if (preRtxn.isEmpty()) {
			return;
		}
		for (Preprocessfundtrans rtxn : preRtxn) {
			if (rtxn.getFundchannelcode().equals(FundChannelCode.CUPS)) {
				// PreProcessCupsRtxnEvent rtxnEvent = new
				// PreProcessCupsRtxnEvent();
				// rtxnEvent.setPreprocessfundtrans(rtxn);
				// rtxnEvent.setFundchannelcode(FundChannelCode.EACCOUNT);
				// this.getEventManager().doService(rtxnEvent);

//				if (ConstCups.IERR.equals(rtxn.getProcesstypcd())) {
//					continue;
//				}
//				
//				String fundChannelCd = FundChannelCode.EACCOUNT;
//				InputData inputDate = new InputData();
//				inputDate.setTransAmt(rtxn.getPayeefeeamt().add(rtxn.getPayerfeeamt().negate()).abs());
//				inputDate.setInnerRtxnNbr(rtxn.getProcessrtxnnbr());
//				inputDate.setFundchannelcode(fundChannelCd);
//				inputDate.setRtxnDate(SysinfoExtendDAO.getSysInfo()
//						.getPostdate());
//				inputDate.setRecognitionid(rtxn.getFundchanelcode());
//				if (FundChannelCode.EACCOUNT.equals(fundChannelCd)) {
//					if (rtxn.getPayerfeeamt().compareTo(rtxn.getPayeefeeamt()) != 0) {
//						boolean flag = false;
//						if (rtxn.getPayerfeeamt().compareTo(
//								rtxn.getPayeefeeamt()) < 0) {
//							flag = true;
//						}
//						EAccountService eaccount = (EAccountService) DefaultSupportor
//								.getService(fundChannelCd.toLowerCase());
//						try {
//							RespSysHead output = eaccount
//									.PreprocessCupsfundment(inputDate, flag);
//							rtxn.setProcessstate(ProcessState.PROCED);
//						} catch (Exception e) {
//							log.error(e.getMessage());
//						}
//					}else{
//						rtxn.setProcessstate(ProcessState.PROCED);
//					}
//					try {
//						PreprocessfundtransDAO.updateByPrimaryKeySelective(rtxn);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			} else {
				PreprocessfundtransEvent rtxnEvent = new PreprocessfundtransEvent();
				rtxnEvent.setPreprocessfundtrans(rtxn);
				rtxnEvent.setFundchannelcode(FundChannelCode.EACCOUNT);
				// 异步处理二代来账和退款交易
				// this.getEventManager().doService(rtxnEvent);
				this.handler(rtxnEvent);
			}
		}
	}

	public void handler(PreprocessfundtransEvent event) {
		Preprocessfundtrans rtxn = event.getPreprocessfundtrans();
		String fundChannelCd = event.getFundChannelCd();
		Overalltrans record = new Overalltrans();

		if (FundChannelCode.BEPS.equals(rtxn.getFundchannelcode()))// 小额来帐
		{
			record.setOveralltranstypcd(OveralltransTyp.STXNBEPS);
		}else if (FundChannelCode.HVPS.equals(rtxn.getFundchannelcode()))// 大额来帐
		{
			record.setOveralltranstypcd(OveralltransTyp.STXNHVPS);
		}else if (FundChannelCode.IBPS.equals(rtxn.getFundchannelcode()))// 超网来帐
		{
			record.setOveralltranstypcd(OveralltransTyp.STXNIBPS);
		}else if (FundChannelCode.DPC.equals(rtxn.getFundchannelcode())) {// 大同城来帐
			record.setOveralltranstypcd(OveralltransTyp.STXNDPC);
		}
		record.setUppertransnbr(rtxn.getDowntransnbr());
		record.setUppersysnbr(rtxn.getFundchannelcode());
		record.setUppertransdate(rtxn.getDowntransdate());
		record.setTransdate(SysinfoExtendDAO.getSysInfo().getPostdate());
		record.setOveralltransstatus(TransStatus.INIT);
		try {
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
			EAccountService eaccount = (EAccountService) this.getRouterService(fundChannelCd.toLowerCase());
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
				// record.setCheckstate(CheckState.UNCHECKED);
				// 退汇交易更新原交易流水状态为失败
				if (rtxn.getOrigdowntransnbr() != null) {
					Innerfundtrans innerfundtrans = new Innerfundtrans();
					innerfundtrans.setInnerfundtransnbr(rtxn.getOrigdowntransnbr());
					innerfundtrans.setTransstatus(TransStatus.FAILURE);
					innerfundtrans.setCheckstatus(CheckStatus.UNCHECKED);
					InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);

				}
			} else {
				record.setOveralltransstatus(output.isSuccess() ? TransStatus.SUCCESS
						: (output.isFailure() ? TransStatus.FAILURE
								: TransStatus.TIMEOUT));
				// 退汇交易更新原交易流水状态为成功
				if (rtxn.getOrigdowntransnbr() != null) {
					Innerfundtrans innerfundtrans = new Innerfundtrans();
					innerfundtrans.setInnerfundtransnbr(rtxn.getOrigdowntransnbr());
					innerfundtrans.setTransstatus(TransStatus.FAILURE);
					innerfundtrans.setCheckstatus(CheckStatus.UNCHECKED);
					InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans);
				}

			}
			OveralltransDAO.updateByPrimaryKeySelective(record);

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
}
