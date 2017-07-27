package com.csii.upp.service.fundprocess;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.AcctHoldMeth;
import com.csii.upp.constant.AcctStatCode;
import com.csii.upp.constant.AcctTypCd;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.YN;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.ReqCoreCreditPayment;
import com.csii.upp.dto.router.core.ReqCoreDeditPaymentAndRefound;
import com.csii.upp.dto.router.core.RespCoreDeditPaymentAndRefound;
import com.csii.upp.dto.router.core.RespCoreHead;
import com.csii.upp.dto.router.eaccount.AcctInfo;
import com.csii.upp.dto.router.eaccount.PersInfo;
import com.csii.upp.dto.router.eaccount.ReqAcctCCQueryLimitAmt;
import com.csii.upp.dto.router.eaccount.ReqAcctInnerWithdrawal;
import com.csii.upp.dto.router.eaccount.ReqAcctItlDep;
import com.csii.upp.dto.router.eaccount.ReqAcctItlWithdraw;
import com.csii.upp.dto.router.eaccount.ReqAcctPayment;
import com.csii.upp.dto.router.eaccount.ReqAcctRecharge;
import com.csii.upp.dto.router.eaccount.ReqAcctRefundment;
import com.csii.upp.dto.router.eaccount.ReqCccPayment;
import com.csii.upp.dto.router.eaccount.ReqDepRtxnReversal;
import com.csii.upp.dto.router.eaccount.ReqDepositOnCreditAcct;
import com.csii.upp.dto.router.eaccount.ReqEAccountHead;
import com.csii.upp.dto.router.eaccount.ReqEaccountInPut;
import com.csii.upp.dto.router.eaccount.ReqEaccountOutPut;
import com.csii.upp.dto.router.eaccount.ReqEcQueryAcctTxn;
import com.csii.upp.dto.router.eaccount.ReqItlPayment;
import com.csii.upp.dto.router.eaccount.ReqItlRefundment;
import com.csii.upp.dto.router.eaccount.ReqPostSuspendRtxn;
import com.csii.upp.dto.router.eaccount.ReqPurchaseFund;
import com.csii.upp.dto.router.eaccount.ReqPwdValid;
import com.csii.upp.dto.router.eaccount.ReqQueryPayeeAcctInfo;
import com.csii.upp.dto.router.eaccount.ReqQueryPayerAcctInfo;
import com.csii.upp.dto.router.eaccount.ReqQueryPerson;
import com.csii.upp.dto.router.eaccount.ReqQueryXmTransLimit;
import com.csii.upp.dto.router.eaccount.ReqRedeemFund;
import com.csii.upp.dto.router.eaccount.ReqReturnRemittance;
import com.csii.upp.dto.router.eaccount.ReqXmPayment;
import com.csii.upp.dto.router.eaccount.RespAcctCCQueryLimitAmt;
import com.csii.upp.dto.router.eaccount.RespAcctInnerWithdrawal;
import com.csii.upp.dto.router.eaccount.RespAcctRecharge;
import com.csii.upp.dto.router.eaccount.RespCccPayMent;
import com.csii.upp.dto.router.eaccount.RespDepRtxnReversal;
import com.csii.upp.dto.router.eaccount.RespDepositOnCreditAcct;
import com.csii.upp.dto.router.eaccount.RespEAccountHead;
import com.csii.upp.dto.router.eaccount.RespEaccountOutPut;
import com.csii.upp.dto.router.eaccount.RespItlPayment;
import com.csii.upp.dto.router.eaccount.RespItlRefundment;
import com.csii.upp.dto.router.eaccount.RespPostSuspendRtxn;
import com.csii.upp.dto.router.eaccount.RespPurchaseFund;
import com.csii.upp.dto.router.eaccount.RespPwdValid;
import com.csii.upp.dto.router.eaccount.RespQueryDirectAcctInfo;
import com.csii.upp.dto.router.eaccount.RespQueryPerson;
import com.csii.upp.dto.router.eaccount.RespQueryXmTransLimit;
import com.csii.upp.dto.router.eaccount.RespRedeemFund;
import com.csii.upp.dto.router.eaccount.RespXmPayMent;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.StringUtil;

/**
 * 电子账户服务类
 * 
 * @author 徐锦
 * 
 */
public class EAccountService extends RouterService implements CreditRouter, DebitRouter, QueryRouter {

	@Override
	public RespSysHead rtdtdr(InputFundData input) {
		return null;
	}

	@Override
	public RespSysHead rtdtcr(InputFundData input) throws PeException {
		
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.AcctRecharge, input, new ReqAcctRecharge(input),
				RespAcctRecharge.class);
		String s = input.getPayeeacctnbr();
		if("1".equals(s.substring(s.length()-1, s.length()))){
			
			output.setResult(RouterResult.TIMEOUT);
			output.setRtxnstate(TransStatus.TIMEOUT);
			Innerfundtrans fundrtxn = new Innerfundtrans();
			fundrtxn.setInnerfundtransnbr(input.getInnerfundtransnbr());
			fundrtxn.setTransstatus(TransStatus.TIMEOUT);
			try {
				InnerfundtransDAO.updateByPrimaryKeySelective(fundrtxn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (output.isFailure()) {
			
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * 电子账户出金
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead withcash(InputFundData input) throws PeException {
		RespEaccountOutPut output = this.handleFundRtxn(InnerRtxnTyp.EaccountAccessToGoldOutput, input, new ReqEaccountOutPut(input),
				RespEaccountOutPut.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * 电子账户入金
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead eaccountInput(InputFundData input) throws PeException {
		RespEaccountOutPut output = this.handleFundRtxn(InnerRtxnTyp.EaccountAccessToGoldInput, input, new ReqEaccountInPut(input),
				RespEaccountOutPut.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 银联代收入账
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead rtdtcrForUnionPay(InputFundData input) throws PeException {
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.AcctRecharge, input, new ReqAcctRefundment(input),
				RespAcctRecharge.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			// this.insertTransexceptionreg(input,
			// RtxnExceptionFunction.EAccountqueryRtxnForDebit);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 人行代收入账
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead rtdtcrForPbc(InputFundData input) throws PeException {
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.AcctRecharge, input, new ReqAcctRecharge(input),
				RespAcctRecharge.class);
		if (output.isFailure()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.UnionPayPayForAnother);
			Innerfundtrans fundRtxn = new Innerfundtrans();
			fundRtxn.setInnerfundtransnbr(input.getInnerfundtransnbr());
			fundRtxn.setTransstatus("3");
			this.updateInnerfundtrans(fundRtxn);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountrtdtcrForPbcRtxn);
		}
		return output;
	}

	/**
	 * 实时贷记借方
	 * 
	 * @author 姜星
	 */
	@Override
	public RespSysHead rtctdr(InputFundData input) throws PeException {
		ReqEAccountHead reqEAccountHead = null;
		
	if (AcctTypCd.INNER.equals(input.getPayeeaccttypcd())) {
			// 往他行卡转一分钱
			reqEAccountHead = new ReqAcctItlWithdraw(input);
		} else {
			reqEAccountHead = new ReqAcctInnerWithdrawal(input);
		}
		// 单笔借记
		RespAcctInnerWithdrawal output = this.handleFundRtxn(InnerRtxnTyp.AcctInnerWithdrawal, input, reqEAccountHead,
				RespAcctInnerWithdrawal.class);
		String s = input.getPayeeacctnbr();
     	if("2".equals(s.substring(s.length()-1, s.length()))){
			
			output.setResult(RouterResult.TIMEOUT);
			output.setRtxnstate(TransStatus.TIMEOUT);
			Innerfundtrans fundrtxn = new Innerfundtrans();
			fundrtxn.setInnerfundtransnbr(input.getInnerfundtransnbr());
			fundrtxn.setTransstatus(TransStatus.TIMEOUT);
			try {
				InnerfundtransDAO.updateByPrimaryKeySelective(fundrtxn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("3".equals(s.substring(s.length()-1, s.length()))){
			
			output.setResult(RouterResult.FAILURE);
			output.setRtxnstate(TransStatus.FAILURE);
		}
		if (output.isFailure()) {// 失败时直接返回
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreRtdtdrReTrave);
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreRtdtdrReTrave);
			
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		return null;
	}

	/**
	 * 普通贷记交易借方流程： 1.插入资金流水 2.单笔借记 3.更新资金流水
	 * 
	 * @param input
	 * @throws PeException
	 * @throws Exception
	 */
	@Override
	public RespSysHead spctdr(InputFundData input) throws PeException {
		// 单笔借记
		RespAcctInnerWithdrawal output = this.handleFundRtxn(InnerRtxnTyp.AcctInnerWithdrawal, input,
				new ReqAcctInnerWithdrawal(input), RespAcctInnerWithdrawal.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountQueryRtxnState);
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {

		return null;
	}

	/*
	 * @param input
	 * 
	 * @return 日终冲回电子账户
	 * 
	 * @throws PeException
	 */
	public RespSysHead eAccountRechargeForcheck(InputFundData input) throws PeException {
		RespAcctRecharge output = null;
		if (AcctTypCd.INNER.equals(input.getPayeeaccttypcd())) {
			// 往他行卡转一分钱
			ReqAcctItlDep req = new ReqAcctItlDep(input);
			req.setSummary("冲回");
			output = this.handleFundRtxn(InnerRtxnTyp.RVAcctInnerWithdrawal, input, req, RespAcctRecharge.class);
		} else {
			ReqAcctRecharge req = new ReqAcctRecharge(input);
			req.setSummary("冲回");
			output = this.handleFundRtxn(InnerRtxnTyp.RVAcctInnerWithdrawal, input, req, RespAcctRecharge.class);
			
		}

		// if (output.isFailure()) {
		// this.insertTransexceptionreg(input,
		// RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
		// Innerfundtrans fundRtxn = new Innerfundtrans();
		// fundRtxn.setInnerrtxnnbr(input.getInnerfundtransnbr());
		// fundRtxn.setRtxnstate(3L);
		// this.updateInnerfundtrans(fundRtxn);
		// Overalltrans record = new Overalltrans();
		// record.setOveralltransnbr(input.getOveralltransNbr());
		// record.setOveralltransstate(RtxnState.SEND);
		// try {
		// OveralltransDAO.updateByPrimaryKeySelective(record);
		// } catch (SQLException e) {
		// log.error(e.getMessage());
		// }
		// throw new PayException(output,
		// SystemCode.RTXN_EXCEPTION);
		// } else if (output.isTimeout()) {
		// this.insertTransexceptionreg(input,
		// RtxnExceptionFunction.EAccountqueryRtxnForDebit);
		// throw new PayException(output,
		// SystemCode.RTDT_CREDIT_TIMEOUT);
		// }
		return output;
	}

	/*
	 * @param input
	 * 
	 * @return 日间交易使用(冲回电子账户) 更改异常表状态及内部流水表
	 * 
	 * @throws PeException
	 */
	public void eAccountRecharge(InputFundData input) {
		RespAcctRecharge output;
		try {
			if (AcctTypCd.INNER.equals(input.getPayeeaccttypcd())) {
				// 往他行卡转一分钱
				ReqAcctItlDep req = new ReqAcctItlDep(input);
				req.setSummary("冲回");
				output = this.handleFundRtxn(InnerRtxnTyp.RVAcctInnerWithdrawal, input, req, RespAcctRecharge.class);
			} else {
				ReqAcctRecharge req = new ReqAcctRecharge(input);
				req.setSummary("冲回");
				output = this.handleFundRtxn(InnerRtxnTyp.RVAcctInnerWithdrawal, input, req, RespAcctRecharge.class);
				String orioverallrtxn= input.getOveralltransnbr();
				InnerfundtransExample  example = new InnerfundtransExample();
				example.createCriteria().andFundchannelcodeEqualTo("EACCOUNT").andOveralltransnbrEqualTo(orioverallrtxn);
				String oriinnerfundtrans = null;
				try {
					List<Innerfundtrans> innerfundtrans = InnerfundtransDAO.selectByExample(example);
					oriinnerfundtrans = innerfundtrans.get(0).getInnerfundtransnbr();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				Overalltrans overalltrans = new Overalltrans();
				overalltrans.setOveralltransnbr(orioverallrtxn);
				overalltrans.setOveralltransstatus(TransStatus.FAILURE);
				try {
					OveralltransDAO.updateByPrimaryKeySelective(overalltrans);
				} catch (SQLException e1) {
				}
				Innerfundtrans fundrtxn = new Innerfundtrans();
				fundrtxn.setInnerfundtransnbr(oriinnerfundtrans);
				fundrtxn.setTransstatus(TransStatus.REVOKED);
				
				try {
					InnerfundtransDAO.updateByPrimaryKeySelective(fundrtxn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				InnerfundtransExample  example1 = new InnerfundtransExample();
				example1.createCriteria().andFundchannelcodeEqualTo("CORE").andOveralltransnbrEqualTo(orioverallrtxn);
				String oriinnerfundtrans1 = null;
				try {
					List<Innerfundtrans> innerfundtrans1 = InnerfundtransDAO.selectByExample(example1);
					oriinnerfundtrans1 = innerfundtrans1.get(0).getInnerfundtransnbr();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
				Innerfundtrans fundrtxn1 = new Innerfundtrans();
				fundrtxn1.setInnerfundtransnbr(oriinnerfundtrans1);
				fundrtxn1.setTransstatus(TransStatus.FAILURE);
				
				try {
					InnerfundtransDAO.updateByPrimaryKeySelective(fundrtxn1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (output.isFailure()) {
				this.formatException(output);
			}
			this.updateInnerfundtrans(input, output);
		} catch (PeException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public RespSysHead rtdtdrReTrave(InputFundData input) throws PeException {
		String OrigOverrallRtxnnbr = input.getOrigoverralltransnbr();
		List<Innerfundtrans> origfundRtxns = InnerfundtransExtendDAO.getInnerfundtrans(input.getFundchannelcode(),
				OrigOverrallRtxnnbr);
		input.getInputDataByOrigInnerfundtrans(origfundRtxns.get(origfundRtxns.size() - 1), true);
		// 存款冲正交易
		ReqAcctRecharge req = new ReqAcctRecharge(input);
		req.setSummary("冲回");
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.RVAcctInnerWithdrawal, input, req,
				RespAcctRecharge.class);
		if (output.isFailure()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountRtdtdrReTrave);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnStateForReTrave);// Query
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 反交易 add by 陈彦鹏
	 * 
	 * @param input
	 * @return
	 */
	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		String OrigOverrallRtxnnbr = input.getOrigoverralltransnbr();
		List<Innerfundtrans> origfundRtxns = InnerfundtransExtendDAO.getInnerfundtrans(input.getFundchannelcode(),
				OrigOverrallRtxnnbr);
		input.getInputDataByOrigInnerfundtrans(
				origfundRtxns.get(origfundRtxns.size() > 1 ? origfundRtxns.size() - 1 : 0), true);
		// 电子账户冲回（取出）
		RespAcctInnerWithdrawal output = this.handleFundRtxn(InnerRtxnTyp.RVAcctRecharge, input,
				new ReqAcctInnerWithdrawal(input), RespAcctInnerWithdrawal.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表 EAccountQueryRtxnSateForReTrave

			// to do 需添加查询方法，具体逻辑： 如果电子账户存款撤销成功，则 继续撤销贷方交易 ； 如果电子账户存款撤销失败， 则
			// 设置交易失败
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountQueryRtxnSateForReTrave);// queryRtxnSateForReTrave
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 单笔记账（借方）：1、插平台交易流水；2、电子账户入账；3、更新流水
	 * 
	 * @author 姜星
	 * @param
	 * 
	 */
	public RespSysHead stxndr(InputFundData input) throws PeException {
		// 单笔记账流程
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.AcctRecharge, input, new ReqAcctRecharge(input),
				RespAcctRecharge.class);
		// output.setResult(RouterResult.FAILURE);
		if (output.isFailure()) {
			// 挂账处理
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.TIMEOUT);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnForDebit);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 消费
	 * 
	 * @author 徐锦
	 */
	public RespSysHead consume(InputFundData input) throws PeException {
		ReqEAccountHead reqEAccountHead = new ReqAcctPayment(input);
		;
		// 消费
		RespAcctInnerWithdrawal output = this.handleFundRtxn(InnerRtxnTyp.AcctPayment, input, reqEAccountHead,
				RespAcctInnerWithdrawal.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountQueryRtxnState);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 消费撤销
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead consumeCancel(InputFundData input) throws PeException {
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.AcctRefundment, input, new ReqAcctRefundment(input),
				RespAcctRecharge.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnForDebit);
			this.formatException(output);
		}
		return output;
	}

	/*
	 * @param input
	 * 
	 * @return 缴费冲回电子账户
	 * 
	 * @throws PeException
	 */
	public RespSysHead revokeAcctPayment(InputFundData input) throws PeException {
		ReqAcctRefundment req = new ReqAcctRefundment(input);
		req.setSummary("缴费冲回");
		req.setRtxnDesc("缴费冲回");
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.RVAcctPayment, input, req, RespAcctRecharge.class);
		return output;
	}

	/*
	 * @param input
	 * 
	 * @return 消费撤销冲回电子账户
	 * 
	 * @throws PeException
	 */
	public RespSysHead revokeAcctRefundment(InputFundData input) throws PeException {
		ReqAcctPayment req = new ReqAcctPayment(input);
		req.setSummary("缴费撤销冲回");
		req.setRtxnDesc("缴费撤销冲回");
		RespAcctInnerWithdrawal output = this.handleFundRtxn(InnerRtxnTyp.RVAcctRefundment, input, req,
				RespAcctInnerWithdrawal.class);
		return output;
	}

	/*
	 * 借记交易超时调用方法
	 */
	public void queryRtxnForDebit(InputFundData input) throws PeException, SQLException {
		RespEAccountHead output = this.handleNonFundRtxn(new ReqEcQueryAcctTxn(input), RespEAccountHead.class);
		if (output.isSuccess()) {
			// 更新总流水和资金流水状态
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.SUCCESS);
			OveralltransDAO.updateByPrimaryKeySelective(record);
			this.updateInnerfundtrans(input, output);
		} else if (output.isFailure()) {
			if ("1401".equals(output.getBaseResponse().getRetCode())) {
				if (OveralltransTyp.CONC.equals(input.getTransid())) {
					this.revokeAcctRefundment(input);
				} else {
					dodepositOnCreditAcct(input);
				}
			}
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
	}

	/*
	 * 挂账交易
	 */
	public void dodepositOnCreditAcct(InputFundData input) throws PeException {
		RespDepositOnCreditAcct output = this.handleFundRtxn(InnerRtxnTyp.AcctReChargeSuspend, input,
				new ReqDepositOnCreditAcct(input), RespDepositOnCreditAcct.class);
		
		
		if (output.isFailure()) {
			// 失败时继续挂账
			// input.setIsfund(YN.N);// 不插入资金流水
			// this.insertTransexceptionreg(input,
			// RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
		} else if (output.isTimeout()) {
			// 捕获超时
			RespEAccountHead gzoutput = this.handleNonFundRtxn(new ReqEcQueryAcctTxn(input), RespEAccountHead.class);
			if (gzoutput.isSuccess()) {
				// 插入新异常 挂入
				this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdopostSuspendRtxn);
			} else {
				this.formatException(gzoutput);
			}
		} else if (output.isSuccess()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdopostSuspendRtxn);
		}
	}

	/*
	 * 挂入
	 */
	public void dopostSuspendRtxn(InputFundData input) throws PeException {
		RespPostSuspendRtxn output = this.handleFundRtxn(InnerRtxnTyp.AcctReChargeSuspendIn, input,
				new ReqPostSuspendRtxn(input), RespPostSuspendRtxn.class);
		// output.setResult(RouterResult.FAILURE);
		if (output.isTimeout()) {
			this.formatException(output);
		}
		// 挂入完成后资金流水成功
		if (output.isSuccess()) {
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.SUCCESS);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		} else if (output.isFailure()) {
			if (true) {
				if (FundChannelCode.HVPS.equals(input.getUppersysnbr())
						|| FundChannelCode.BEPS.equals(input.getUppersysnbr())) {
					// 挂入失败后执行解挂并退汇
					this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdoSuspendRVandReTrave);
				}
			} else {
				this.formatException(output);
			}
		}
	}

	public void doSuspendRVandReTrave(InputFundData input) throws PeException {
		String code = input.getUppersysnbr();
		input.setFundchannelcode(FundChannelCode.EACCOUNT);
		RespEAccountHead output = this.doReturnRemittance(input);
		if (output.isSuccess()) {
			if (FundChannelCode.HVPS.equals(code)) {
				input.setFundchannelcode(FundChannelCode.HVPS);
				HvpsService hvps = (HvpsService) DefaultSupportor.getService(FundChannelCode.HVPS.toLowerCase());
				RespSysHead outdata = hvps.STHPDrReTrave(input);
			} else if (FundChannelCode.BEPS.equals(code)) {
				input.setFundchannelcode(FundChannelCode.BEPS);
				BepsService beps = (BepsService) DefaultSupportor.getService(FundChannelCode.BEPS.toLowerCase());
				RespSysHead outdata = beps.STBPDrRetrave(input);
			}
		}
	}

	/*
	 * 电子账户出金超时
	 */
	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespEAccountHead output = this.handleNonFundRtxn(new ReqEcQueryAcctTxn(input), RespEAccountHead.class);
		if (output.isSuccess()) {
			this.updateInnerfundtrans(input, output);
			// 执行电子账户冲回
			input.exchangePaperAndPayeeAcctNbr();
			RespSysHead eaccountoutput = null;
			if (OveralltransTyp.CONS.equals(input.getTransid())) {
				eaccountoutput = this.revokeAcctPayment(input);
			} else {
				eaccountoutput = eAccountRechargeForcheck(input);
			}
			if (eaccountoutput.isSuccess()) {
				Overalltrans record = new Overalltrans();
				record.setOveralltransnbr(input.getOveralltransnbr());
				record.setOveralltransstatus(TransStatus.FAILURE);
				try {
					OveralltransDAO.updateByPrimaryKeySelective(record);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else if (output.isFailure()) {
			if ("1401".equals(output.getBaseResponse().getRetCode())) {
				// 更新原交易状态为失败
				this.updateInnerfundtrans(input, output);
				Overalltrans record = new Overalltrans();
				record.setOveralltransnbr(input.getOveralltransnbr());
				record.setOveralltransstatus(TransStatus.FAILURE);
				try {
					OveralltransDAO.updateByPrimaryKeySelective(record);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
			/*
			 * updateTransexceptionregState(ExpHandleState.SUCCESS,
			 * input.getTransexcepseqnbr());
			 */
			// 先注释掉更改日间异常表状态，如果失败需要支持多次循环功能
		} else if (output.isTimeout()) {
			/*
			 * updateTransexceptionregState(ExpHandleState.SUCCESS,
			 * input.getTransexcepseqnbr());
			 */
			this.formatException(output);
		}

	}

	/**
	 * 通过收款人账号查询电子账户信息
	 * 
	 * @author 姜星
	 */
	@Override
	public RespSysHead queryPayeeAcctInfo(InputFundData input) throws PeException {
		RespQueryDirectAcctInfo output = this.handleNonFundRtxn(new ReqQueryPayeeAcctInfo(input),
				RespQueryDirectAcctInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 通过付款人账号查询电子账户信息
	 * 
	 * @author 徐锦
	 */
	@Override
	public RespSysHead queryPayerAcctInfo(InputFundData input) throws PeException {
		RespQueryDirectAcctInfo output = this.handleNonFundRtxn(new ReqQueryPayerAcctInfo(input),
				RespQueryDirectAcctInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 验证收款人电子账户信息
	 * 
	 * @author 徐锦
	 */
	public RespSysHead validatePayeeAcctInfo(InputFundData input) throws PeException {
		RespSysHead query = this.queryPayeeAcctInfo(input);
		AcctInfo acctInfo = ((RespQueryDirectAcctInfo) query).getAcctinfo();
		if (!acctInfo.getAcctname().equals(input.getPayeename()) && !StringUtil.isStringEmpty(input.getPayeename())) {
			throw new PeException(DictErrors.EACCOUNT_NAME_ERROR);
		}
		if (!AcctStatCode.ACT.equals(acctInfo.getCurracctstatcd())) {
			throw new PeException(DictErrors.EACCOUNT_STATE_ERROR);
		}
		if (AcctHoldMeth.BSBF.equals(acctInfo.getAcctHoldMethCd())) {
			throw new PeException(DictErrors.EACCOUNT_LIMIT);
		}
		return query;
	}

	/**
	 * 验证付款人电子账户信息
	 * 
	 * @author 徐锦
	 * @throws Exception
	 */
	public RespSysHead validatePayerAcctInfo(InputFundData input) throws PeException {
		RespSysHead query = this.queryPayerAcctInfo(input);
		AcctInfo acctInfo = ((RespQueryDirectAcctInfo) query).getAcctinfo();
		// 湖北银行要求提现不判断激活状态
		if (!AcctStatCode.ACT.equals(acctInfo.getCurracctstatcd())
		/* || !MediStatCode.ACT.equals(acctInfo.getMedistatcd()) */) {
			throw new PeException(DictErrors.EACCOUNT_STATE_ERROR);
		}
		if (AcctHoldMeth.BSBF.equals(acctInfo.getAcctHoldMethCd())
				|| AcctHoldMeth.ZSBF.equals(acctInfo.getAcctHoldMethCd())) {
			throw new PeException(DictErrors.EACCOUNT_LIMIT);
		}
		if (!StringUtil.isStringEmpty(input.getPayerphoneno())) {
			if (!input.getPayerphoneno().equals(acctInfo.getPhoneNbr())) {
				throw new PeException(DictErrors.EACCOUNT_PHONE_ERROR);
			}
		}
		if (!StringUtil.isStringEmpty(input.getPayername())) {
			if (!input.getPayername().equals(acctInfo.getAcctname())) {
				throw new PeException(DictErrors.EACCOUNT_NAME_ERROR);
			}
		}

		// 身份证
		if (!StringUtil.isStringEmpty(input.getPayeridnbr())) {
			input.setPersnbr(acctInfo.getOwnerNbr());
			RespSysHead pers = this.queryPerson(input);
			if (pers.isSuccess()) {
				List<PersInfo> persInfo = ((RespQueryPerson) pers).getPersinfo();
				String idTyp = input.getPayeridnbr().substring(0, 2);
				// 电子账户只支持身份证
				if (!"01".equals(idTyp)) {
					throw new PeException(DictErrors.EACCOUNT_ONLY_ID_EN_VALID);
				}
				// 身份证后6位
				String idNum = persInfo.get(0).getIdnbr();
				String outIdNum = idNum.substring(idNum.length() - 6, idNum.length());
				String inputIdNum = input.getPayeridnbr().substring(input.getPayeridnbr().length() - 6,
						input.getPayeridnbr().length());
				if (!inputIdNum.equals(outIdNum)) {
					throw new PeException(DictErrors.EACCOUNT_ID_EN_ERROR);
				}
			}
		}

		if (FundChannelCode.CUPS.equals(input.getUppersysnbr())) {
			if (StringUtil.isStringEmpty(input.getPayercardpwd()) || "FFFFFF".equals(input.getPayercardpwd())) {
				throw new PeException(DictErrors.INVALID_PWD);
			}
			RespPwdValid pwd = (RespPwdValid) this.validPwd(input);
		}
		return query;
	}

	/**
	 * 查询客户信息
	 * 
	 * @author 徐锦
	 */
	public RespSysHead queryPerson(InputFundData input) throws PeException {
		RespQueryPerson output = this.handleNonFundRtxn(new ReqQueryPerson(input), RespQueryPerson.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 白条额度  
	 * 
	 * @author CreditLoanQueryLimitAmt
	 */
	public RespSysHead queryXmTransLimit(InputFundData input) throws PeException {
		RespQueryXmTransLimit output = this.handleNonFundRtxn(new ReqQueryXmTransLimit(input), RespQueryXmTransLimit.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * baitiao edu 
	 * 
	 * @author 类信用卡
	 */
	public RespSysHead queryAcctCCQueryLimitAmt(InputFundData input) throws PeException {
		RespAcctCCQueryLimitAmt output = this.handleNonFundRtxn(new ReqAcctCCQueryLimitAmt(input), RespAcctCCQueryLimitAmt.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 交易密码验证
	 * 
	 * @author 徐锦
	 */
	public RespSysHead validPwd(InputFundData input) throws PeException {
		RespPwdValid output = this.handleNonFundRtxn(new ReqPwdValid(input), RespPwdValid.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		if (YN.N.equals(output.getPwdValidYN())) {
			throw new PeException(DictErrors.INVALID_PWD);
		}
		return output;
	}

	/*
	 * 挂退
	 */
	public RespEAccountHead doReturnRemittance(InputFundData input) throws PeException {
		RespEAccountHead output = this.handleFundRtxn(InnerRtxnTyp.AcctReChargeSuspendRV, input,
				new ReqReturnRemittance(input), RespEAccountHead.class);
		return output;
	}

	/**
	 * 撤销交易中做电子账户cr反交易超时方法
	 * 
	 * @param input
	 */
	public void queryRtxnSateForReTrave(InputFundData input) {
		RespEAccountHead output = null;
		try {
			output = this.handleNonFundRtxn(new ReqEcQueryAcctTxn(input), RespEAccountHead.class);
			if (output.isSuccess()) {
				Innerfundtrans fundrtxn = InnerfundtransExtendDAO
						.getAnotherFundRtxnByOverall(input.getOveralltransnbr());
				if (fundrtxn != null) {
					if (fundrtxn.getFundchannelcode().equals(FundChannelCode.BILL99))
						this.insertTransexceptionreg(input, RtxnExceptionFunction.Bill99RtdtdrReTrave);
					else if (fundrtxn.getFundchannelcode().equals(FundChannelCode.CORE))
						this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreRtdtdrReTrave);
					else
						this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountRtdtdrReTrave);
				}
			} else if (output.isFailure()) {
				if ("1401".equals(output.getBaseResponse().getRetCode())) {
					Overalltrans Overalltrans = OveralltransDAO.selectByPrimaryKey(input.getOveralltransnbr());
					Overalltrans.setOveralltransstatus(TransStatus.FAILURE);
					OveralltransDAO.updateByPrimaryKeySelective(Overalltrans);
					this.updateInnerfundtrans(input, output);
				}
			}
		} catch (PeException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

	}

	// 三个(老核心，快钱，电子账户)渠道做撤销(dr)超时方法
	public void queryRtxnStateForReTrave(InputFundData input) {
		// 查询电子账户状态
		RespEAccountHead output = null;
		try {
			output = this.handleNonFundRtxn(new ReqEcQueryAcctTxn(input), RespEAccountHead.class);
			String s = input.getPayeeacctnbr();
			if("2".equals(s.substring(s.length()-1, s.length()))){
				
				output.setResult(RouterResult.FAILURE);
				output.setRtxnstate(TransStatus.FAILURE);
			}
			String orioverallrtxn= input.getOveralltransnbr();
			InnerfundtransExample  example = new InnerfundtransExample();
			example.createCriteria().andFundchannelcodeEqualTo("EACCOUNT").andOveralltransnbrEqualTo(orioverallrtxn);
			String oriinnerfundtrans = null;
			try {
				List<Innerfundtrans> innerfundtrans = InnerfundtransDAO.selectByExample(example);
				oriinnerfundtrans = innerfundtrans.get(0).getInnerfundtransnbr();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			
			if (output.isSuccess()) {
				
			Innerfundtrans fundrtxn = new Innerfundtrans();
			fundrtxn.setInnerfundtransnbr(oriinnerfundtrans);
			fundrtxn.setTransstatus(TransStatus.SUCCESS);
			this.updateInnerfundtrans(input, output);
			
			} else if (output.isFailure()) {
				
				this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreRtdtdrReTrave);
			}
		} catch (PeException e) {
			log.error(e.getMessage());
		}

	}

	/**
	 * 二代来账/退款交易进行电子账户入金交易
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead PreprocessAcctRecharge(InputFundData input) throws PeException {
		/*
		 * RespAcctRecharge output = this.handleFundRtxn(
		 * InnerRtxnTyp.AcctRecharge, input, new ReqAcctRecharge(input),
		 * RespAcctRecharge.class); if (output.isSuccess()) { // 修改资金流水对账状态为对账平
		 * input.setCheckstatus(CheckState.CHECKED);
		 * this.updateInnerfundtrans(input, output); } else { // 修改资金流水对账状态为对账平
		 * input.setCheckstatus(CheckState.UNCHECKED);
		 * this.updateInnerfundtrans(input, output); // 直接插入日终表转人工处理
		 * this.insertBatchcheckerror(input, output); }
		 */

		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.StxnBZ, input, new ReqAcctRecharge(input),
				RespAcctRecharge.class);
		if (output.isFailure()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
			// Innerfundtrans fundRtxn = new Innerfundtrans();
			// fundRtxn.setInnerrtxnnbr(input.getInnerfundtransnbr());
			// fundRtxn.setRtxnstate(3L);
			// this.updateInnerfundtrans(fundRtxn);
			// Overalltrans record = new Overalltrans();
			// record.setOveralltransnbr(input.getOveralltransNbr());
			// record.setOveralltransstate(RtxnState.SEND);
			// try {
			// OveralltransDAO.updateByPrimaryKeySelective(record);
			// } catch (SQLException e) {
			// log.error(e.getMessage());
			// }
			// throw new PayException(output,
			// SystemCode.RTXN_EXCEPTION);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnForDebit);
			// throw new PayException(output,
			// SystemCode.RTDT_CREDIT_TIMEOUT);
		}
		return output;
	}

	protected <T extends RespEAccountHead> T handleFundRtxn(String innerRtxnTypCd, InputFundData input,
			ReqEAccountHead inputObj, Class<T> outputClazz) throws PeException {
		T result = super.handleFundRtxn(innerRtxnTypCd, input, inputObj, outputClazz);
		/*afterCall(result);*/
		return result;
	}

	protected <T extends RespEAccountHead> T handleNonFundRtxn(ReqEAccountHead inputObj, Class<T> outputClazz)
			throws PeException {
		T result = super.handleNonFundRtxn(inputObj, outputClazz);
		afterCall(result);
		return result;
	}

	private <T extends RespEAccountHead> void afterCall(T result) {
		Sysinfo sysinfo = SysinfoExtendDAO.getSysInfo();
		if (!sysinfo.getPostdate().equals(result.getDownrtxndate())) {
			SysinfoExtendDAO.updateUppDate(result.getDownrtxndate());
		}
	}

	/**
	 * 来账交易日终差错做撤销
	 * 
	 * @param input
	 */
	/*
	 * public RespSysHead stxnRevoke(InputData input){ String
	 * OrigOverrallRtxnnbr = input.getOrigoverralltransnbr(); RespSysHead
	 * depositeReTrave = null; try { Overalltrans OrigOveralltrans
	 * =OveralltransDAO.selectByPrimaryKey(OrigOverrallRtxnnbr); depositeReTrave
	 * = rtdtcrReTrave(input); if(depositeReTrave.isSuccess()){
	 * OrigOveralltrans.setOveralltransstate(TransStatus.REVOKED);
	 * OveralltransDAO.updateByPrimaryKeySelective(OrigOveralltrans); }else
	 * if(depositeReTrave.isFailure()){ throw new
	 * PayException(depositeReTrave.getReturnmsg(),
	 * BusinessCode.STXNREVOKE_FAILED); } } catch(PeException e) {
	 * log.error(e.getMessage()); } catch (SQLException e) {
	 * log.error(e.getMessage()); } return depositeReTrave; }
	 */

	/**
	 * 基金申购借方交易
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead fpurdr(InputFundData input) throws PeException {
		RespPurchaseFund output = this.handleFundRtxn(InnerRtxnTyp.AcctFundPurchases, input, new ReqPurchaseFund(input),
				RespPurchaseFund.class);
		if (output.isFailure()) {
			// 失败时直接返回
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.FAILURE);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountQueryRtxnState);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 基金赎回贷方交易
	 * 
	 * @author JIANGXING
	 */
	public RespSysHead fredcr(InputFundData input) throws PeException {
		RespRedeemFund output = this.handleFundRtxn(InnerRtxnTyp.AcctFundRedemption, input, new ReqRedeemFund(input),
				RespRedeemFund.class);
		if (output.isFailure()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
			Innerfundtrans fundRtxn = new Innerfundtrans();
			fundRtxn.setInnerfundtransnbr(input.getInnerfundtransnbr());
			fundRtxn.setTransstatus(TransStatus.TIMEOUT);
			this.updateInnerfundtrans(fundRtxn);
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnForDebit);
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		return null;
	}

	/**
	 * 银联对账交易进行电子账户入金交易
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead checkUnionPayApplAcctRecharge(InputFundData input) throws PeException {
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.AcctRecharge, input, new ReqAcctRecharge(input),
				RespAcctRecharge.class);
		if (output.isSuccess()) {
			// 修改资金流水对账状态为对账平
			input.setCheckstatus(CheckStatus.CHECKED);
			this.updateInnerfundtrans(input, output);
		} else {
			// 修改资金流水对账状态为对账平
			input.setCheckstatus(CheckStatus.UNCHECKED);
			this.updateInnerfundtrans(input, output);
			// 直接插入日终表转人工处理
			this.insertBatchcheckerror(input, output);
		}
		return output;
	}

	public void insertTransexceptionregForSingleRtxn(InputFundData input, RtxnExceptionFunction ex) {
		this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input), ex);
	}

	private String BuildspctcrExceptionRtxnSnap(InputFundData input) {
		InputFundData tempInput = input;
		tempInput.exchangePaperAndPayeeAcctNbr();
		// return tempInput.formatInputData();
		return BeanUtils.beanToXmlString(input);
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input) throws PeException {
		return null;
	}

	/*
	 * 挂账交易
	 */
	public RespSysHead dodepositOnCreditAcctForCheck(InputFundData input) throws PeException {
		input.setFundchannelcode(FundChannelCode.EACCOUNT);
		RespDepositOnCreditAcct output = this.handleFundRtxn(InnerRtxnTyp.AcctReChargeSuspend, input,
				new ReqDepositOnCreditAcct(input), RespDepositOnCreditAcct.class);
		return output;
	}

	public RespSysHead BZForCheck(InputFundData input) throws PeException {
		ReqAcctRecharge req = new ReqAcctRecharge(input);
		req.setSummary("补账");
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.StxnBZ, input, req, RespAcctRecharge.class);
		return output;
	}

	/**
	 * 代收补账
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead dsForCheck(InputFundData input) throws PeException {
		ReqAcctRefundment req = new ReqAcctRefundment(input);
		req.setSummary("代收补账");
		RespAcctRecharge output = this.handleFundRtxn(InnerRtxnTyp.StxnBZ, input, req, RespAcctRecharge.class);
		return output;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		RespDepRtxnReversal output = this.handleFundRtxn(InnerRtxnTyp.AcctRtxnErrorCorrect, input,
				new ReqDepRtxnReversal(input), RespDepRtxnReversal.class);
		if (output.isFailure()) {
			if ("1420".equals(output.getReturncode())) {
				// 情况2——返成功
				output.setResult(RouterResult.SUCCESS);
			} else {
				this.formatException(output);
			}
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 小米付
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead xiaoMiPayment(InputFundData input) throws PeException {
		ReqXmPayment reqXmPayment = new ReqXmPayment(input);
		// 小米付
		RespXmPayMent output = this.handleFundRtxn(InnerRtxnTyp.XmPayment, input, reqXmPayment,
				RespXmPayMent.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountQueryRtxnState);
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 类信用卡付
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead cCConsumePayment(InputFundData input) throws PeException {
		ReqCccPayment reqCccPayment = new ReqCccPayment(input);
		// 类信用卡付
		RespCccPayMent output = this.handleFundRtxn(InnerRtxnTyp.CCConsumePayment, input, reqCccPayment,
				RespCccPayMent.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountQueryRtxnState);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 银联手续费
	 */
	public RespSysHead PreprocessCupsfundment(InputFundData input, boolean flag) throws PeException {
		RespEAccountHead output = null;
		if (flag) {
			RespItlPayment output1 = this.handleNonFundRtxn(new ReqItlPayment(input), RespItlPayment.class);
			output = output1;
		} else if (!flag) {
			RespItlRefundment output2 = this.handleNonFundRtxn(new ReqItlRefundment(input), RespItlRefundment.class);
			output = output2;
		}
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
		RespCoreHead output = null;
		if (CardTypCd.CREDIT.equals(input.getPayercardtypcd())) { 
			output = this.handleFundRtxn(InnerRtxnTyp.CoreCreditPayment, input, new ReqCoreCreditPayment(input),
					RespCoreHead.class);
		} else if (CardTypCd.DEBIT.equals(input.getPayercardtypcd())) { 
			output = this.handleFundRtxn(InnerRtxnTyp.CoreDeditPayment, input, new ReqCoreDeditPaymentAndRefound(input),
					RespCoreDeditPaymentAndRefound.class);
		}
		if (output.isFailure()) {
			if(InteralFlag.YES.equals(input.getInteralFlag())){
			((PointService)DefaultSupportor.getService(FundChannelCode.JFWG.toLowerCase())).cancelPayerIntegral(input);
			}
			output.setReturnmsg("客户资金扣款失败");
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			output.setReturnmsg("客户资金扣款超时");
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead refoundTrans(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryDownPostDate(InputFundData input) throws PeException {
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

	@Override
	public RespSysHead recharge(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

}
