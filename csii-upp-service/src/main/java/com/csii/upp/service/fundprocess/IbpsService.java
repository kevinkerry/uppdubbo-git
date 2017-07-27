package com.csii.upp.service.fundprocess;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.ibps.ReqIbpsCheckApply;
import com.csii.upp.dto.router.ibps.ReqIbpsQueryRtxn;
import com.csii.upp.dto.router.ibps.ReqRealTimeCreditRtxn;
import com.csii.upp.dto.router.ibps.RespIbpsCheckApply;
import com.csii.upp.dto.router.ibps.RespIbpsQueryRtxn;
import com.csii.upp.dto.router.ibps.RespIbpsWithdrawal;
import com.csii.upp.dto.router.ibps.RespRealTimeCreditRtxn;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.StringUtil;

/**
 * 超级网银服务类
 * 
 * @author 姜星
 * 
 */
public class IbpsService extends RouterService implements CreditRouter, QueryRouter {

	/**
	 * 实时借记贷方
	 */
	@Override
	public RespSysHead rtdtcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 实时贷记贷方
	 * 
	 * @author 姜星
	 */
	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		ReqRealTimeCreditRtxn creditInput = new ReqRealTimeCreditRtxn(input);
		RespRealTimeCreditRtxn output = this.handleFundRtxn(InnerRtxnTyp.IbpsCreditRtxn, input, creditInput,
				RespRealTimeCreditRtxn.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			if (YN.N.equals(output.getIsTimeoutStatus())) {
				// 超时没有响应时插入异常处理表
				this.insertTransexceptionreg(input, RtxnExceptionFunction.IbpsQueryRtxnState);
				// 实时贷记贷方交易超时
				this.formatException(output);
			} else {
				this.formatException(output);
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

	/**
	 * 超网来帐失败时退回来帐交易
	 * 
	 * @author 陈彦鹏
	 */

	// public RespSysHead STIBDrReTrave(InputData input) throws PeException {
	//
	// ReqRealTimeCreditRtxn creditInput = new ReqRealTimeCreditRtxn(input);
	// creditInput.setRcvbankcode(input.getSendbankcode());
	// creditInput.setPayeracctcode("100");
	// RespRealTimeCreditRtxn output = this.handleFundRtxn(
	// InnerRtxnTyp.STIBDrReTrave, input, creditInput,
	// RespRealTimeCreditRtxn.class);
	// // if (output.isFailure()) {
	// // // 失败时插入异常处理表
	// // throw new PayException(output,
	// // BusinessCode.IBPSRTCT_CR_FAILED);
	// // } else if (output.isTimeout()) {
	// //
	// // throw new PayException(output,
	// // SystemCode.RTCT_CREDIT_TIMEOUT);
	// // } else if (output.isSuccess()) {
	// updateTransexceptionregState(ExpHandleState.SUCCESS,
	// input.getTransexcepseqnbr());
	// // }
	// this.updateInnerfundtrans(input, output);
	// return output;
	// }

	/**
	 * 普通贷记贷方
	 */
	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 实时借记贷方撤销
	 */
	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 网银按渠道流水号查询往账状态
	 * 
	 * @author 姜星
	 */
	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespIbpsQueryRtxn output = this.handleNonFundRtxn(new ReqIbpsQueryRtxn(input), RespIbpsQueryRtxn.class);
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
			// 执行电子账户冲回
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
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

	public RespIbpsCheckApply applyCheckFile(InputFundData input) throws PeException {
		RespIbpsCheckApply output = this.handleNonFundRtxn(new ReqIbpsCheckApply(input), RespIbpsCheckApply.class);
		return output;
	}

	public RespSysHead STIBDrReTraveForCheck(InputFundData input) throws PeException {
		ReqRealTimeCreditRtxn creditInput = new ReqRealTimeCreditRtxn(input);
		creditInput.setRcvbankcode(input.getPayeebanknbr());
		if (StringUtil.isStringEmpty(input.getPayeebanknbr())) {
			creditInput.setRcvbankcode(input.getPayerbanknbr());
		}
		creditInput.setPayeracctcode("100");
		RespRealTimeCreditRtxn output = this.handleFundRtxn(InnerRtxnTyp.STIBDrReTrave, input, creditInput,
				RespRealTimeCreditRtxn.class);
		return output;
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
		
		RespIbpsWithdrawal output = new RespIbpsWithdrawal();
		input.setTranscode("ibps");
		insertInnerfundtrans(input);
//		T output = send(inputObj, outputClazz);
		output.setRtxnstate("0");
		output.setReturnmsg("ok");
		output.setResult(RouterResult.SUCCESS);
		updateInnerfundtrans(input, output);	
//		if(output.isFailure()){
//			this.formatException(output);
//		}else if(output.isTimeout()){
//			this.formatException(output);
//		}
		return output;
	}
}
