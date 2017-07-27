package com.csii.upp.service.fundprocess;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.dpc.ReqDpcCheckFileApply;
import com.csii.upp.dto.router.dpc.ReqDpcPPCreditRtxn;
import com.csii.upp.dto.router.dpc.ReqDpcQueryRtxn;
import com.csii.upp.dto.router.dpc.RespDpcCheckFileApply;
import com.csii.upp.dto.router.dpc.RespDpcPPCreditRtxn;
import com.csii.upp.dto.router.dpc.RespDpcQueryRtxn;
import com.csii.upp.dto.router.dpc.RespDpcWithdrawal;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.util.BeanUtils;

/**
 * 同城服务类
 * 
 * @author
 * 
 */
public class DpcService extends RouterService implements CreditRouter, QueryRouter {

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
		// 1. 大同城个人提出贷记业务
		RespDpcPPCreditRtxn output = this.handleFundRtxn(InnerRtxnTyp.DpcPPCreditRtxn, input,
				new ReqDpcPPCreditRtxn(input), RespDpcPPCreditRtxn.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时时处理
			// todo 同城超时处理 目前缺相应的接口
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.DpcQueryRtxnState);
			this.formatException(output);
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

	/**
	 * 大同城系统按渠道流水号查询往账状态
	 * 
	 */
	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespDpcQueryRtxn output = this.handleNonFundRtxn(new ReqDpcQueryRtxn(input), RespDpcQueryRtxn.class);
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

	public RespDpcCheckFileApply DocheckFileApply(InputFundData input) throws PeException {
		RespDpcCheckFileApply output = this.handleNonFundRtxn(new ReqDpcCheckFileApply(input),
				RespDpcCheckFileApply.class);
		return output;
	}

	public RespSysHead STIBDrReTraveForCheck(InputFundData input) throws PeException {
		ReqDpcPPCreditRtxn creditRtxn = new ReqDpcPPCreditRtxn(input);
		creditRtxn.setRcvbankcode(input.getPayeebanknbr());
		creditRtxn.setAccttype("300");
		RespDpcPPCreditRtxn out = this.handleFundRtxn(InnerRtxnTyp.DcpDrReTrave, input, creditRtxn,
				RespDpcPPCreditRtxn.class);
		return out;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead withdrawal(InputFundData input) throws PeException {
		RespDpcWithdrawal output = new RespDpcWithdrawal();
		input.setTranscode("dpc");
		insertInnerfundtrans(input);
		output.setRtxnstate("0");
		output.setReturnmsg("ok");
		output.setResult(RouterResult.SUCCESS);
		updateInnerfundtrans(input, output);	
		return output;
	}
}
