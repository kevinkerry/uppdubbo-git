package com.csii.upp.service.fundprocess;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.ConstHvps;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.hvps.ReqHvpsCheck;
import com.csii.upp.dto.router.hvps.ReqHvpsCreditRtxn;
import com.csii.upp.dto.router.hvps.ReqHvpsQueryAcctWithdrawl;
import com.csii.upp.dto.router.hvps.ReqHvpsQueryRtxn;
import com.csii.upp.dto.router.hvps.ReqHvpsReTraveRtxn;
import com.csii.upp.dto.router.hvps.ReqHvpsVirAcctWithdrawl;
import com.csii.upp.dto.router.hvps.RespHvpsCheck;
import com.csii.upp.dto.router.hvps.RespHvpsCreditRtxn;
import com.csii.upp.dto.router.hvps.RespHvpsQueryAcctWithdrawl;
import com.csii.upp.dto.router.hvps.RespHvpsQueryRtxn;
import com.csii.upp.dto.router.hvps.RespHvpsReTraveRtxn;
import com.csii.upp.dto.router.hvps.RespHvpsVirAcctWithdrawl;
import com.csii.upp.dto.router.hvps.RespHvpsWithdrawal;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.StringUtil;

/**
 * 大额支付服务类
 * 
 * @author 陈彦鹏
 * 
 */
public class HvpsService extends RouterService implements CreditRouter, QueryRouter {

	@Override
	public RespSysHead rtdtcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		// 1. 大额系统贷记业务
		RespHvpsCreditRtxn output = this.handleFundRtxn(InnerRtxnTyp.HvpsCreditRtxn, input,
				new ReqHvpsCreditRtxn(input), RespHvpsCreditRtxn.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
		}
		return output;
	}

	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		// 1. 大额系统贷记业务
		RespHvpsCreditRtxn output = this.handleFundRtxn(InnerRtxnTyp.HvpsCreditRtxn, input,
				new ReqHvpsCreditRtxn(input), RespHvpsCreditRtxn.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);

			// this.insertTransexceptionreg(input,
			// BuildspctcrExceptionRtxnSnap(input),
			// RtxnExceptionFunction.HvpsQueryRtxnState);

			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
		}
		return output;
	}

	/*
	 * 
	 * 大额来帐失败时退汇交易
	 */
	public RespSysHead STHPDrReTrave(InputFundData input) throws PeException {
		// 1. 大额系统贷记业务
		ReqHvpsReTraveRtxn reqHvpsReTraveRtxn = new ReqHvpsReTraveRtxn(input);
		RespHvpsReTraveRtxn output = this.handleFundRtxn(InnerRtxnTyp.STHPDrReTrave, input, reqHvpsReTraveRtxn,
				RespHvpsReTraveRtxn.class);
		if (output.isFailure()) {
			EAccountService eaccount = (EAccountService) DefaultSupportor
					.getService(FundChannelCode.EACCOUNT.toLowerCase());
			RespSysHead outdata = eaccount.dodepositOnCreditAcctForCheck(input);
		} else if (output.isTimeout()) {
			RespHvpsQueryRtxn query = this.handleNonFundRtxn(new ReqHvpsQueryRtxn(input), RespHvpsQueryRtxn.class);
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

	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	private String BuildspctcrExceptionRtxnSnap(InputFundData input) {
		InputFundData tempInput = input;
		tempInput.exchangePaperAndPayeeAcctNbr();
		// return tempInput.formatInputData();
		return BeanUtils.beanToXmlString(input);

	}

	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespHvpsQueryRtxn output = this.handleNonFundRtxn(new ReqHvpsQueryRtxn(input), RespHvpsQueryRtxn.class);
		if (output.isSuccess()) {
			this.updateInnerfundtrans(input, output);
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
			// if ("".equals(output.getReturncode())) { // TODO
			// 执行电子账户冲回
			insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
			// }
		} else if (output.isTimeout()) {
			this.formatException(output);
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

	public RespHvpsCheck hvpsCheck(InputFundData input) throws PeException {
		RespHvpsCheck output = this.handleNonFundRtxn(new ReqHvpsCheck(input), RespHvpsCheck.class);
		return output;
	}

	public RespSysHead STHPDrReTraveForCheck(InputFundData input) throws PeException {
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
		RespHvpsVirAcctWithdrawl output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.HvpsVirAcctWithdrawl, input, new ReqHvpsVirAcctWithdrawl(input),
				RespHvpsVirAcctWithdrawl.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.HvpsQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}
	
	public RespHvpsQueryAcctWithdrawl queryBatchTransferState(InputFundData input) throws PeException {
		RespHvpsQueryAcctWithdrawl output = this.handleNonFundRtxn(new ReqHvpsQueryAcctWithdrawl(input), RespHvpsQueryAcctWithdrawl.class);
		return output;
	}
	
	
	public void queryTransStateForPay(InputFundData input) throws PeException {
		RespHvpsQueryAcctWithdrawl output = this.handleNonFundRtxn(new ReqHvpsQueryAcctWithdrawl(input),RespHvpsQueryAcctWithdrawl.class);
		String overallTransState = null;
		if (output.isSuccess()) {
			if (ConstHvps.RETSTATUS_SUCCESS.equals(output.getResultStatus())) {
				overallTransState = TransStatus.SUCCESS;
				input.setTransstatus(TransStatus.SUCCESS);
			} else if(ConstHvps.RETSTATUS_FAILE.equals(output.getResultStatus())){
				overallTransState = TransStatus.FAILURE;
				output.setRtxnstate(TransStatus.FAILURE);
				input.setTransstatus(TransStatus.FAILURE);
			}else{
				return;
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
		RespHvpsWithdrawal output = new RespHvpsWithdrawal();
		input.setTranscode("hvps");
		insertInnerfundtrans(input);
		output.setRtxnstate("0");
		output.setReturnmsg("ok");
		output.setResult(RouterResult.SUCCESS);
		updateInnerfundtrans(input, output);	
		return output;
	}
	
}
