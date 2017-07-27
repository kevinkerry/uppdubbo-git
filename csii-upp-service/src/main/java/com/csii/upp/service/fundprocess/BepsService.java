package com.csii.upp.service.fundprocess;
import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.ConstBeps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.beps.ReqBepsCheck;
import com.csii.upp.dto.router.beps.ReqBepsQueryAcctWithdrawl;
import com.csii.upp.dto.router.beps.ReqBepsQueryRtxn;
import com.csii.upp.dto.router.beps.ReqBepsReTraveRtxn;
import com.csii.upp.dto.router.beps.ReqBepsVirAcctWithdrawl;
import com.csii.upp.dto.router.beps.RespBepsCheck;
import com.csii.upp.dto.router.beps.RespBepsQueryAcctWithdrawl;
import com.csii.upp.dto.router.beps.RespBepsQueryRtxn;
import com.csii.upp.dto.router.beps.RespBepsReTraveRtxn;
import com.csii.upp.dto.router.beps.RespBepsVirAcctWithdrawl;
import com.csii.upp.dto.router.beps.RespBepsWithdrawal;
import com.csii.upp.dto.router.hvps.ReqHvpsCreditRtxn;
import com.csii.upp.dto.router.hvps.ReqHvpsQueryRtxn;
import com.csii.upp.dto.router.hvps.RespHvpsCreditRtxn;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.StringUtil;

/**
 * 小额支付服务类
 * 
 * @author gaoyu
 * 
 */
public class BepsService extends RouterService implements CreditRouter,QueryRouter {

	/*
	 * 
	 * 小额来帐失败时退回来帐交易 //TODO gaoyu 目前统一由大额service
	 * reqhvpscreditrtxn.setPriority("HIGH");
	 */
	public RespSysHead STBPDrRetrave(InputFundData input) throws PeException {
		// 1. 小额系统贷记业务
		ReqBepsReTraveRtxn reqBepsReTraveRtxn = new ReqBepsReTraveRtxn(input);
		RespBepsReTraveRtxn output = this.handleFundRtxn(InnerRtxnTyp.STBPDrRetrave, input, reqBepsReTraveRtxn,
				RespBepsReTraveRtxn.class);
		if (output.isFailure()) {
			// 失败时直接返回
			EAccountService eaccount = (EAccountService) DefaultSupportor
					.getService(FundChannelCode.EACCOUNT.toLowerCase());
			RespSysHead outdata = eaccount.dodepositOnCreditAcctForCheck(input);
		} else if (output.isTimeout()) {
			RespBepsQueryRtxn query = this.handleNonFundRtxn(new ReqHvpsQueryRtxn(input), RespBepsQueryRtxn.class);
			if (query.isSuccess()) {
				this.updateInnerfundtrans(input, output);
			} else if (query.isFailure()) {
				EAccountService eaccount = (EAccountService) DefaultSupportor
						.getService(FundChannelCode.EACCOUNT.toLowerCase());
				RespSysHead outdata = eaccount.dodepositOnCreditAcctForCheck(input);
			}
		}
		return output;
	}

	private String BuildspctcrExceptionRtxnSnap(InputFundData input) {
		InputFundData tempInput = input;
		tempInput.exchangePaperAndPayeeAcctNbr();
		// return tempInput.formatInputData();
		return BeanUtils.beanToXmlString(input);

	}

	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespBepsQueryRtxn output = this.handleNonFundRtxn(new ReqBepsQueryRtxn(input), RespBepsQueryRtxn.class);
		if (output.isSuccess()) {
			// 更新总交易流水状态
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.SUCCESS);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		} else if (output.isFailure()) {
			// 执行电子账户冲回
			insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
		} else if (output.isTimeout()) {
			formatException(output);
		}
	}

	@Override
	public RespSysHead queryPayeeAcctInfo(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryPayerAcctInfo(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	public RespBepsCheck bepsCheck(InputFundData input) throws PeException {
		RespBepsCheck output = this.handleNonFundRtxn(new ReqBepsCheck(input), RespBepsCheck.class);
		return output;
	}

	/**
	 * 日终差错处理小额来账退回交易
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead STBPDrRetraveForCheck(InputFundData input) throws PeException {
		RespHvpsCreditRtxn output = null;
		if (true) {
			ReqBepsReTraveRtxn reqReTraveRtxn = new ReqBepsReTraveRtxn(input);
			output = this.handleFundRtxn(InnerRtxnTyp.STBPDrRetrave, input, reqReTraveRtxn, RespHvpsCreditRtxn.class);
		} else {
			ReqHvpsCreditRtxn reqhvpscreditrtxn = new ReqHvpsCreditRtxn(input);
			reqhvpscreditrtxn.setPayeebankcode(input.getPayeebanknbr());
			reqhvpscreditrtxn.setPayeercvbank(input.getPayerbanknbr());
			reqhvpscreditrtxn.setPriority("HIGH");
			output = this.handleFundRtxn(InnerRtxnTyp.STBPDrRetrave, input, reqhvpscreditrtxn,
					RespHvpsCreditRtxn.class);
		}

		return output;
	}

	@Override
	public RespSysHead rtdtcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead refoundTrans(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead virAcctWithdrawl(InputFundData input) throws PeException {
		RespBepsVirAcctWithdrawl output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.BepsVirAcctWithdrawl, input, new ReqBepsVirAcctWithdrawl(input),
				RespBepsVirAcctWithdrawl.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}                                                                                                                                                                                                                            
	
	public RespBepsQueryAcctWithdrawl queryBatchTransferState(InputFundData input) throws PeException {
		RespBepsQueryAcctWithdrawl output = this.handleNonFundRtxn(new ReqBepsQueryAcctWithdrawl(input), RespBepsQueryAcctWithdrawl.class);
		return output;
	}

	
	public void queryTransStateForPay(InputFundData input) throws PeException {
		RespBepsQueryAcctWithdrawl output = this.handleNonFundRtxn(new ReqBepsQueryAcctWithdrawl(input),RespBepsQueryAcctWithdrawl.class);
		String overallTransState = null;
		if (output.isSuccess()) {
			if (ConstBeps.RETSTATUS_SUCCESS.equals(output.getTransStatus())) {
				overallTransState = TransStatus.SUCCESS;
				input.setTransstatus(TransStatus.SUCCESS);
			} else if(ConstBeps.RETSTATUS_FAILE.equals(output.getTransStatus())){
				overallTransState = TransStatus.FAILURE;
				output.setRtxnstate(TransStatus.FAILURE);
				input.setTransstatus(TransStatus.FAILURE);
			}else{
				return ;
			}
		} else if (output.isFailure()) {
			overallTransState = TransStatus.FAILURE;
			input.setTransstatus(TransStatus.FAILURE);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		if (!StringUtil.isStringEmpty(input.getInnerfundtransnbr())) {
			// 更新总交易流水状态
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(overallTransState);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			this.updateInnerfundtrans(input, output);
		}
		// 发给payment
		((PaymService) DefaultSupportor.getService(FundChannelCode.PAYM.toLowerCase())).notifyPayResult(input);
	}

	@Override
	public RespSysHead withdrawal(InputFundData input) throws PeException {
		RespBepsWithdrawal output = new RespBepsWithdrawal();
		input.setTranscode("beps");
		insertInnerfundtrans(input);
		output.setRtxnstate("0");
		output.setReturnmsg("ok");
		output.setResult(RouterResult.SUCCESS);
		updateInnerfundtrans(input, output);	
		return output;
	}
	
}
