package com.csii.upp.service.fundprocess;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.cnaps2.ReqReturnRemittance;
import com.csii.upp.dto.router.cnaps2.ReqUpbsAutoRout;
import com.csii.upp.dto.router.cnaps2.ReqUpbsQueryRtxn;
import com.csii.upp.dto.router.cnaps2.ReqUpbsSimpleCreditRtxn;
import com.csii.upp.dto.router.cnaps2.RespCnaps2Head;
import com.csii.upp.dto.router.cnaps2.RespReturnRemittance;
import com.csii.upp.dto.router.cnaps2.RespUpbsAutoRout;
import com.csii.upp.dto.router.cnaps2.RespUpbsSimpleCreditRtxn;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.util.BeanUtils;

/**
 * 二代支付服务类
 * 
 * @author 徐锦
 * 
 */
public class Cnaps2Service extends RouterService implements CreditRouter, QueryRouter {

	@Override
	public RespSysHead rtdtcr(InputFundData input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		return null;

	}

	/**
	 * 普通贷记交易贷方流程： 1.自动汇路 2.插入资金流水 3.普通贷记往帐 4.更新资金流水
	 * 
	 * @throws PeException
	 */
	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		// 发送自动汇路并计算手续费请求
		RespUpbsAutoRout upbsAutoOutput = this.handleNonFundRtxn(new ReqUpbsAutoRout(input), RespUpbsAutoRout.class);
		if (!upbsAutoOutput.isSuccess()) {
			// 不成功时直接返回
			this.formatException(upbsAutoOutput);
		}
		ReqUpbsSimpleCreditRtxn creditInput = new ReqUpbsSimpleCreditRtxn(input);
		creditInput.setUpbsAutoOutput(upbsAutoOutput);
		// 发送普通贷记往帐请求
		RespUpbsSimpleCreditRtxn output = this.handleFundRtxn(InnerRtxnTyp.Cnaps2UpbsSimpleCreditRtxn, input,
				creditInput, RespUpbsSimpleCreditRtxn.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.Cnaps2QueryRtxnState);
			this.formatException(output);
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
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 贷记时二代入账超时
	 */
	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespCnaps2Head output = this.handleNonFundRtxn(new ReqUpbsQueryRtxn(input), RespCnaps2Head.class);
		if (output.isSuccess()) {
			// 更新总交易流水状态
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus("1");
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			this.updateInnerfundtrans(input, output);
		} else if (output.isFailure()) {
			// 执行电子账户冲回
			insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
	}

	/**
	 * 调用退汇
	 */
	public void cnaps2ReturnRemittance(InputFundData input) {
		try {
			ReqReturnRemittance returninput = new ReqReturnRemittance(input);
			RespReturnRemittance output = this.handleFundRtxn(InnerRtxnTyp.Canps2ReturnRemittance, input, returninput,
					RespReturnRemittance.class);
			if (output.isFailure()) {
				this.formatException(output);
			} else if (output.isTimeout()) {
				this.formatException(output);
			}
		} catch (PeException e) {
			log.error(e.getMessage());
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
		// TODO Auto-generated method stub
		return null;
	}
}
