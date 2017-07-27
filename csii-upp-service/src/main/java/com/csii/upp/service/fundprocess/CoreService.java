package com.csii.upp.service.fundprocess;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.ExWipeoutStatus;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.BatchmersettleDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchmersettle;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.ReqCardToInnerAcct;
import com.csii.upp.dto.router.core.ReqCheckCreditCardPwd;
import com.csii.upp.dto.router.core.ReqCheckDeditCardPwd;
import com.csii.upp.dto.router.core.ReqCheckOffDateAppl;
import com.csii.upp.dto.router.core.ReqConfirmedVirtualAcctPay;
import com.csii.upp.dto.router.core.ReqCoreCheckApply;
import com.csii.upp.dto.router.core.ReqCoreCreditPayment;
import com.csii.upp.dto.router.core.ReqCoreCreditRefound;
import com.csii.upp.dto.router.core.ReqCoreCreditVirtualRefoundTrans;
import com.csii.upp.dto.router.core.ReqCoreDebitVirtualRefoundTrans;
import com.csii.upp.dto.router.core.ReqCoreDeditPaymentAndRefound;
import com.csii.upp.dto.router.core.ReqCoreExWipeout;
import com.csii.upp.dto.router.core.ReqCoreInnerTransfer;
import com.csii.upp.dto.router.core.ReqCoreIntegalPay;
import com.csii.upp.dto.router.core.ReqCoreQueryRtxn;
import com.csii.upp.dto.router.core.ReqCoreWipeout;
import com.csii.upp.dto.router.core.ReqCreditVirAcctTransfer;
import com.csii.upp.dto.router.core.ReqFundRedemption;
import com.csii.upp.dto.router.core.ReqInnerAcctToCard;
import com.csii.upp.dto.router.core.ReqPaymIssueFile;
import com.csii.upp.dto.router.core.ReqQueryCreditCardInfo;
import com.csii.upp.dto.router.core.ReqQueryDeditCardInfo;
import com.csii.upp.dto.router.core.ReqQueryInnerAcctInfo;
import com.csii.upp.dto.router.core.ReqQueryVirtualAcctBalance;
import com.csii.upp.dto.router.core.ReqVirAcctTransfer;
import com.csii.upp.dto.router.core.RespCardToInnerAcct;
import com.csii.upp.dto.router.core.RespCheckCreditCardPwd;
import com.csii.upp.dto.router.core.RespCheckDeditCardPwd;
import com.csii.upp.dto.router.core.RespCheckOffDateAppl;
import com.csii.upp.dto.router.core.RespConfirmedVirtualAcctPay;
import com.csii.upp.dto.router.core.RespCoreCheckApply;
import com.csii.upp.dto.router.core.RespCoreCreditVirtualRefoundTrans;
import com.csii.upp.dto.router.core.RespCoreDebitVirtualRefoundTrans;
import com.csii.upp.dto.router.core.RespCoreDeditPaymentAndRefound;
import com.csii.upp.dto.router.core.RespCoreHead;
import com.csii.upp.dto.router.core.RespCoreIntegalPay;
import com.csii.upp.dto.router.core.RespCoreQueryRtxn;
import com.csii.upp.dto.router.core.RespCoreWipeout;
import com.csii.upp.dto.router.core.RespCreditVirAcctTransfer;
import com.csii.upp.dto.router.core.RespDbjz;
import com.csii.upp.dto.router.core.RespInnerAcctToCard;
import com.csii.upp.dto.router.core.RespPaymIssueFile;
import com.csii.upp.dto.router.core.RespQueryCreditCardInfo;
import com.csii.upp.dto.router.core.RespQueryDeditCardInfo;
import com.csii.upp.dto.router.core.RespQueryInnerAcctInfo;
import com.csii.upp.dto.router.core.RespQueryVirtualAcctBalance;
import com.csii.upp.dto.router.core.RespVirAcctTransfer;
import com.csii.upp.dto.router.hvps.ReqHvpsWithdrawal;
import com.csii.upp.dto.router.hvps.RespHvpsWithdrawal;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 老核心服务类
 * 
 */
/**
 * @author Administrator
 *
 */

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class CoreService extends RouterService implements CreditRouter, DebitRouter, QueryRouter {

	@Override
	public RespSysHead rtdtcr(InputFundData input) {

		return null;
	}

	/**
	 * 实时贷记贷方
	 * 
	 * @author JIANGXING
	 */
	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		// 发送内部转卡请求
		RespInnerAcctToCard output = this.handleFundRtxn(InnerRtxnTyp.CoreInnerAcctToCard, input,
				new ReqInnerAcctToCard(input), RespInnerAcctToCard.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.CoreQueryRtxnState);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 普通贷记交易贷方流程: 1.插入资金流水 2.内部转卡 3.更新资金流水
	 * 
	 * @throws PeException
	 */
	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		// 发送内部转卡请求
		RespInnerAcctToCard output = this.handleFundRtxn(InnerRtxnTyp.CoreInnerAcctToCard, input,
				new ReqInnerAcctToCard(input), RespInnerAcctToCard.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.CoreQueryRtxnState);
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

	/**
	 * 核心卡转内部交易
	 */
	@Override
	public RespSysHead rtdtdr(InputFundData input) throws PeException {
		RespCardToInnerAcct output = this.handleFundRtxn(InnerRtxnTyp.CoreCardToInnerAcct, input,
				new ReqCardToInnerAcct(input), RespCardToInnerAcct.class);
		String s = input.getPayeeacctnbr();
     	if("2".equals(s.substring(s.length()-1, s.length()))){
     		Innerfundtrans fundrtxn = new Innerfundtrans();
			fundrtxn.setInnerfundtransnbr(input.getInnerfundtransnbr());
			fundrtxn.setTransstatus(TransStatus.TIMEOUT);
			try {
				InnerfundtransDAO.updateByPrimaryKeySelective(fundrtxn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			output.setResult(RouterResult.TIMEOUT);
			output.setRtxnstate(TransStatus.TIMEOUT);
		}else if("3".equals(s.substring(s.length()-1, s.length()))){
			
			output.setResult(RouterResult.FAILURE);
			output.setRtxnstate(TransStatus.FAILURE);
		}
     	
		if (output.isTimeout()) {
			// TODO 需要修改插入异常表的异常
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CorequeryRtxnStateForDebit);
			this.formatException(output);
		} else if (output.isFailure()) {
			// 失败时直接返回
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
			output.setReturnmsg("交易失败");
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 核心卡转内部交易
	 */
	
	public RespSysHead rtdtdr1(InputFundData input) throws PeException {
		RespCardToInnerAcct output = this.handleFundRtxn(InnerRtxnTyp.CoreCardToInnerAcct1, input,
				new ReqCardToInnerAcct(input), RespCardToInnerAcct.class);
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
		if (output.isTimeout()) {
			// TODO 需要修改插入异常表的异常
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreRtdtdrReTrave);
			this.formatException(output);
		} else if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead rtctdr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead spctdr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTrave(InputFundData input) throws PeException {
		RespInnerAcctToCard output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.CoreWipeout, input, new ReqInnerAcctToCard(input),
				RespInnerAcctToCard.class);
		
		String orioverallrtxn= input.getOveralltransnbr();
		Overalltrans overalltrans = new Overalltrans();
		overalltrans.setOveralltransnbr(orioverallrtxn);
		overalltrans.setOveralltransstatus(TransStatus.FAILURE);
		try {
			OveralltransDAO.updateByPrimaryKeySelective(overalltrans);
		} catch (SQLException e1) {
		}
		InnerfundtransExample  example = new InnerfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo("CORE").andOveralltransnbrEqualTo(orioverallrtxn);
		String oriinnerfundtrans = null;
		try {
			List<Innerfundtrans> innerfundtrans = InnerfundtransDAO.selectByExample(example);
			oriinnerfundtrans = innerfundtrans.get(0).getInnerfundtransnbr();
		} catch (SQLException e1) {
		
			e1.printStackTrace();
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
		example1.createCriteria().andFundchannelcodeEqualTo("EACCOUNT").andOveralltransnbrEqualTo(orioverallrtxn);
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
		
		
		
		
		
		String s = input.getPayeeacctnbr();
     	if("3".equals(s.substring(s.length()-1, s.length()))){
		InnerfundtransExample  example2 = new InnerfundtransExample();
		example2.createCriteria().andFundchannelcodeEqualTo("CORE").andOveralltransnbrEqualTo(orioverallrtxn);
		String oriinnerfundtrans2 = null;
		try {
			List<Innerfundtrans> innerfundtrans2 = InnerfundtransDAO.selectByExample(example2);
			oriinnerfundtrans2 = innerfundtrans2.get(0).getInnerfundtransnbr();
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}
		
		Innerfundtrans fundrtxn2 = new Innerfundtrans();
		fundrtxn2.setInnerfundtransnbr(oriinnerfundtrans2);
		fundrtxn2.setTransstatus(TransStatus.FAILURE);
		
		try {
			InnerfundtransDAO.updateByPrimaryKeySelective(fundrtxn2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	}
		
		if (output.isFailure()) {
			// this.insertTransexceptionreg(input,
			// RtxnExceptionFunction.CoreRtdtdrReTrave);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			// this.insertTransexceptionreg(input,
			// RtxnExceptionFunction.EAccountqueryRtxnStateForReTrave);// Query
			this.formatException(output);
		} 
		return output;

	}

	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		return null;
	}

	/**
	 * (贷记交易)老核心入账交易超时调用方法
	 * 
	 * @throws SQLException
	 */
	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		RespCoreQueryRtxn output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreQueryRtxn.class);
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
			this.updateInnerfundtrans(input, output);
		} else if (output.isFailure()) {
			// 执行电子账户冲回
			insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
	}

	/*
	 * @param input
	 * 
	 * @throws PeException 借记交易老核心渠道交易超时调用方法
	 */
	public void queryRtxnStateForDebit(InputFundData input) throws PeException {
		RespCoreHead output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreHead.class);
		String s = input.getPayeeacctnbr();
		if("2".equals(s.substring(s.length()-1, s.length()))){
			
			output.setResult(RouterResult.FAILURE);
			output.setRtxnstate(TransStatus.FAILURE);
			
		}
		String orioverallrtxn= input.getOveralltransnbr();
		InnerfundtransExample  example = new InnerfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo("CORE").andOveralltransnbrEqualTo(orioverallrtxn);
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
		} else if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
		
			
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccounteAccountRecharge);
			this.updateInnerfundtrans(input, output);
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.FAILURE);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			this.formatException(output);
		}
	}

	// 三个(老核心，快钱，电子账户)渠道做撤销(dr)超时方法
	public void queryRtxnStateForReTrave(InputFundData input) {
		// 查询老核心状态
		RespCoreHead output = null;
		try {
			output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreHead.class);
			if (output.isSuccess()) {
				Overalltrans OrigOveralltrans = OveralltransDAO.selectByPrimaryKey(input.getOrigoverralltransnbr());
				OrigOveralltrans.setOveralltransstatus(TransStatus.REVOKED);
				OveralltransDAO.updateByPrimaryKeySelective(OrigOveralltrans);
				this.updateInnerfundtrans(input, output);
			} else if (output.isFailure()) {
				this.updateInnerfundtrans(input, output);
				this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreRtdtdrReTrave);
			}
		} catch (PeException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
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

	public RespSysHead applyCheckFile(InputFundData input) throws PeException {
		RespCoreHead output = null;
		output = this.handleNonFundRtxn(new ReqCoreCheckApply(input), RespCoreCheckApply.class);
		return output;
	}

	/**
	 * 基金赎回借方交易
	 * 
	 * @author JIANGXING
	 */
	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		RespDbjz output = this.handleFundRtxn(input.getTranscode(), input, new ReqFundRedemption(input),
				RespDbjz.class);
		if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CorequeryRtxnStateForDebit);
			this.formatException(output);
		} else if (output.isFailure()) {
			// 失败时直接返回
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.FAILURE);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			this.updateInnerfundtrans(input, output);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 核心记账
	 * 
	 * 哈尔滨银行无转账状态查询，借记交易走记账接口
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public void coreSingleRtxn(InputFundData input) throws PeException {
		// TODO

		RespCoreHead output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreHead.class);
		if (output.isSuccess()) {
			insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
		} else if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			// TODO 修改交易状态
			this.updateInnerfundtrans(input, output);
			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());
			record.setOveralltransstatus(TransStatus.FAILURE);
			try {
				OveralltransDAO.updateByPrimaryKeySelective(record);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			this.formatException(output);
		}
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input) throws PeException {
		RespInnerAcctToCard output = null;
		/*
		 * String OrigOverrallRtxnnbr = input.getOveralltransNbr();
		 * List<Innerfundtrans> origfundRtxns = InnerfundtransExtendDAO
		 * .getInnerfundtrans(input.getFundchannelcode(), OrigOverrallRtxnnbr);
		 * input.getInputDataByOrigInnerfundtrans(origfundRtxns.get(
		 * origfundRtxns .size() > 1 ? origfundRtxns.size() - 1 : 0),true);
		 */

		output = this.handleFundRtxn(InnerRtxnTyp.CoreWipeout, input, new ReqInnerAcctToCard(input),
				RespInnerAcctToCard.class);
		return output;

		/**
		 * 
		 * 
		 * RespInnerAcctToCard output = null;
		 * 
		 * InputData tmpInputData = null; String OrigOverrallRtxnnbr =
		 * input.getOveralltransNbr(); List<Innerfundtrans> origfundRtxns =
		 * InnerfundtransExtendDAO
		 * .getInnerfundtrans(input.getFundchannelcode(), OrigOverrallRtxnnbr);
		 * Overalltrans Overalltrans = null; try { Overalltrans =
		 * OveralltransDAO.selectByPrimaryKey(OrigOverrallRtxnnbr);
		 * if(Overalltrans != null) { OverallrequestregExample example = new
		 * OverallrequestregExample();
		 * example.createCriteria().andUpperrtxnnbrEqualTo(Overalltrans.
		 * getUpperrtxnnbr()); List<Overallrequestreg> reglist =
		 * OverallrequestregDAO.selectByExample(example ) ;
		 * if(reglist!=null&&reglist.size()>0) { tmpInputData =
		 * InputData.parseInputData(reglist.get(0)); }
		 * input.getInputDataByOrigInnerfundtrans(origfundRtxns.get(
		 * origfundRtxns .size() > 1 ? origfundRtxns.size() - 1 : 0),true);
		 * 
		 * output = this.handleFundRtxn(InnerRtxnTyp.CoreWipeout, input, new
		 * ReqInnerAcctToCard(input), RespInnerAcctToCard.class); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage()); }
		 * 
		 */

	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		RespCoreWipeout output = this.handleFundRtxn(InnerRtxnTyp.CoreWipeout, input, new ReqCoreWipeout(input),
				RespCoreWipeout.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 用于本行卡统一支付
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
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
				this.coreIntegalPayExWipeout(input);
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

	/**
	 * 用于本行卡统一退货
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	@Override
	public RespSysHead refoundTrans(InputFundData input) throws PeException {
		RespCoreHead output = null;
		if (input.getPayeecardtypcd().equals(CardTypCd.CREDIT)) {
			output = this.handleFundRtxn(InnerRtxnTyp.CoreCreditRefoundTrans, input, new ReqCoreCreditRefound(input),
					RespCoreHead.class);
		} else if (input.getPayeecardtypcd().equals(CardTypCd.DEBIT)) {
			output = this.handleFundRtxn(InnerRtxnTyp.CoreDeditRefoundTrans, input,
					new ReqCoreDeditPaymentAndRefound(input), RespCoreDeditPaymentAndRefound.class);
		}
		if (output.isFailure()) {
			if(InteralFlag.YES.equals(input.getInteralFlag())){
				this.coreIntegalPayExWipeout(input);
			}
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * 用于本行卡统一退货
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead refoundTrans4UnionPay(InputFundData input) throws PeException {
		RespCoreHead output = this.handleFundRtxn(InnerRtxnTyp.CoreRefoundTrans4UnionPay, input,
					new ReqCoreDeditPaymentAndRefound(input), RespCoreDeditPaymentAndRefound.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 用于核心虚账户退货
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead virtualRefoundTrans(InputFundData input) throws PeException {
		RespCoreHead output = null;
		if (input.getPayeecardtypcd().equals(CardTypCd.DEBIT)) {
			//核心虚账户借记卡退货交易
			output = this.handleFundRtxn(InnerRtxnTyp.CoreDebitVirtualRefoundTrans, input,
					new ReqCoreDebitVirtualRefoundTrans(input), RespCoreDebitVirtualRefoundTrans.class);
		}else if(input.getPayeecardtypcd().equals(CardTypCd.CREDIT)){
			//核心虚账户贷记卡退货交易
			output = this.handleFundRtxn(InnerRtxnTyp.CoreCreditVirtualRefoundTrans, input,
					new ReqCoreCreditVirtualRefoundTrans(input), RespCoreCreditVirtualRefoundTrans.class);
		}
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             查询借记卡信息
	 */
	public RespSysHead queryDebitCardInfo(InputFundData input) throws PeException {
		RespQueryDeditCardInfo output = this.handleNonFundRtxn(new ReqQueryDeditCardInfo(input),
				RespQueryDeditCardInfo.class);
		if (output.isFailure()) {
			if (ConstCore.RETCODE_CARD_EMPTY.contains(output.getReturncode()))
				throw new PeException(DictErrors.DEBIT_CARD_NBR_NOT_EXIST);
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		} else if (output.isTimeout()) {
			throw new PeException(DictErrors.TRANS_TIMEOUT);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             查贷记卡信息
	 */
	public RespSysHead queryCreditCardInfo(InputFundData input) throws PeException {
		RespQueryCreditCardInfo output = this.handleNonFundRtxn(new ReqQueryCreditCardInfo(input),
				RespQueryCreditCardInfo.class);
		if (output.isFailure()) {
			if (ConstCore.RETCODE_CARD_EMPTY.contains(output.getReturncode()))
				throw new PeException(DictErrors.CREDIT_CARD_NBR_NOT_EXIST);
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             检验借记卡密码
	 */
	public RespSysHead checkDebitCardPwd(InputFundData input) throws PeException {
		RespCheckDeditCardPwd output = this.handleNonFundRtxn(new ReqCheckDeditCardPwd(input),
				RespCheckDeditCardPwd.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             检验贷记卡密码
	 */
	public RespSysHead checkCreditCardPwd(InputFundData input) throws PeException {
		RespCheckCreditCardPwd creditCheck = this.handleNonFundRtxn(new ReqCheckCreditCardPwd(input),
				RespCheckCreditCardPwd.class);
		if (creditCheck.isFailure()) {
			if (ConstCore.RETCODE_CARD_EMPTY.contains(creditCheck.getReturncode())) {
				throw new PeException(DictErrors.CREDIT_CARD_NBR_NOT_EXIST);
			} else if (ConstCore.QUERYCARDFLAG.equals(creditCheck.getReturncode())) {
				throw new PeException(DictErrors.QUERYPASSWORDINPUTERROR); // 查询密码输入错误，请核实后重新输入
			} else if (ConstCore.CMERQUERYFLAG.equals(creditCheck.getReturncode())) {
				throw new PeException(DictErrors.CMERQUERYPWDNOTSET);// 客户级查询密码未设置，请先设置查询密码
			} else if (ConstCore.PWDNUMFLAG.equals(creditCheck.getReturncode())) {
				throw new PeException(DictErrors.QUERYPWDNUMBEROFERRORS);// 客户级查询密码累计错误次数超限
			}
			throw new PeException(DictErrors.CARDINFORMATIONERROR);// 卡信息有误，详情打电话到客服中心处理
		} else if (creditCheck.isTimeout()) {
			this.formatException(creditCheck);
		} else if (creditCheck.isSuccess()) {
			if (!StringUtil.isStringEmpty(input.getPayeridtypcd())
					&& !input.getPayeridtypcd().equals(creditCheck.getPayerIdTypCd())) {
				throw new PeException(DictErrors.CARDTYPENOTMATCH);// 证件类型不匹配
			} else if (!StringUtil.isStringEmpty(input.getPayeridnbr())
					&& !input.getPayeridnbr().equals(creditCheck.getPayerIdNbr())) {
				throw new PeException(DictErrors.CARDIDNOTMATCH);// 证件号码不匹配
			} else if (!StringUtil.isStringEmpty(input.getPayercardexpireddate())
					&& !input.getPayercardexpireddate().equals(creditCheck.getPayerCardExpiredDate())) {
				throw new PeException(DictErrors.VALIDITY_ERROR);// 有效期错误
			}
		}
		return creditCheck;
	}

	/**
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryDownPostDate(InputFundData input) throws PeException {
		RespCoreHead output = null;
		output = this.handleNonFundRtxn(new ReqCheckOffDateAppl(input), RespCheckOffDateAppl.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @return
	 * @throws PeException
	 */
	public RespSysHead confirmedVirtualAcctPay(InputFundData input) throws PeException {
		RespCoreHead output = null;
		output = this.handleNonFundRtxn(new ReqConfirmedVirtualAcctPay(input), RespConfirmedVirtualAcctPay.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryInnerAcctInfo(InputFundData input) throws PeException {
		RespCoreHead output = null;
		output = this.handleNonFundRtxn(new ReqQueryInnerAcctInfo(input), RespQueryInnerAcctInfo.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @return
	 * @throws PeException
	 */
	public RespSysHead virAcctTransfer(InputFundData input) throws PeException {
		RespCoreHead output = null;
		if(CardTypCd.CREDIT.equals(input.getPayercardtypcd())){
			//富阳贷记卡支付交易处理
			output = this.handleFundRtxn(InnerRtxnTyp.CoreVirAcctTransfer, input, new ReqCreditVirAcctTransfer(input),
					RespCreditVirAcctTransfer.class);
		}else{
			output = this.handleFundRtxn(InnerRtxnTyp.CoreVirAcctTransfer, input, new ReqVirAcctTransfer(input),
					RespVirAcctTransfer.class);
		}
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * 虚账户提现
	 */
	@Override
	public RespSysHead virAcctWithdrawl(InputFundData input) throws PeException {
		RespCoreHead output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.CoreVirAcctWithdrawl, input, new ReqVirAcctTransfer(input),
				RespVirAcctTransfer.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
			this.formatException(output);
		}
		return output;
	}
	
	public void queryTransStateForPay(InputFundData input) throws PeException {
		RespCoreQueryRtxn output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreQueryRtxn.class);
		String overallTransState = null;
		if (output.isSuccess()) {
			if (StringUtil.isStringEmpty(input.getInnerfundtransnbr())
					|| !StringUtil.isStringEmpty(output.getOrigSeqNbr())) {
				overallTransState = TransStatus.SUCCESS;
				input.setTransstatus(TransStatus.SUCCESS);
			} else {
				overallTransState = TransStatus.FAILURE;
				output.setRtxnstate(TransStatus.FAILURE);
				input.setTransstatus(TransStatus.FAILURE);
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
		//撤销积分
		if(overallTransState.equals(TransStatus.FAILURE)&&!StringUtil.isStringEmpty(input.getInnerfundtransnbr())){
			if(OveralltransTyp.UPAY.equals(input.getTransid())){
				this.coreIntegalPayExWipeout(input);
				((PointService)DefaultSupportor.getService(FundChannelCode.JFWG.toLowerCase())).cancelPayerIntegral(input);
			}else{
				this.coreIntegalPayExWipeout(input);
			}
		}
		
		
		
	}
	

	/**
	 * 生成下发文件，通知互联网前置
	 */
	public RespSysHead sendPaymIssueFile(InputFundData data) throws PeException {
		ReqPaymIssueFile input = new ReqPaymIssueFile(data);
		RespCoreHead output = this.handleNonFundRtxn(input, RespPaymIssueFile.class);
		
		return output;
	}

	/**
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryViratualBalace(InputFundData input) throws PeException {
		RespQueryVirtualAcctBalance output = null;
		output = this.handleNonFundRtxn(new ReqQueryVirtualAcctBalance(input), RespQueryVirtualAcctBalance.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 商户结算
	 * 
	 * @author 姜星
	 */
	public RespSysHead settleMerchant(InputFundData input) throws PeException {
		RespCoreHead output = this.handleNonFundRtxn(new ReqCoreDeditPaymentAndRefound(input),
				RespCoreDeditPaymentAndRefound.class);
		return output;
	}

	public void queryMerchantSettlement(InputFundData input) throws PeException {
		RespCoreQueryRtxn output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreQueryRtxn.class);
		Batchmersettle record = new Batchmersettle();
		record.setSettleseqnbr(input.getSettleseqnbr());
		if (output.isSuccess()) {
			record.setSendstatus(SendStatus.SUCCESS);
		} else if (output.isFailure()) {
			record.setSendstatus(SendStatus.FAILURE);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		try {
			BatchmersettleDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 批量转账
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead batchTransfer(InputFundData input) throws PeException {
		RespCoreHead output = this.handleFundRtxn(InnerRtxnTyp.BatchTransferTrans, input, new ReqCoreDeditPaymentAndRefound(input),
					RespCoreDeditPaymentAndRefound.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForPay);
		}
		return output;
	}
	
	public RespCoreQueryRtxn queryBatchTransferState(InputFundData input) throws PeException {
		RespCoreQueryRtxn output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreQueryRtxn.class);
		return output;
	}
	
	/**
	 * 双边账，核心边记账
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead coreInnerTransfer(InputFundData input) throws PeException {
		RespCoreHead output = this.handleFundRtxn(InnerRtxnTyp.CoreInnerTransfer, input, new ReqCoreInnerTransfer(input),
				RespCoreHead.class);
		if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreQueryTransStateForUnionPay);
		}
		return output;
	}
	
	/**
	 *	coreInnerTransfer失败，查询交易
	 * @param input
	 * @throws PeException
	 */
	public void queryTransStateForUnionPay(InputFundData input) throws PeException {
		RespCoreQueryRtxn output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreQueryRtxn.class);
		String overallTransState = null;
		Innerfundtrans innerfundtrans = null;
		
		InnerfundtransExample innerfundtransExample = new InnerfundtransExample();
		innerfundtransExample.createCriteria().andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY).andOveralltransnbrEqualTo(input.getOveralltransnbr());
		try {
			innerfundtrans = InnerfundtransDAO.selectByExample(innerfundtransExample).get(0);
		} catch (SQLException e1) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION);
		}
		
		Map<String, Object> map = this.getObjectMapMarshaller().marshall(innerfundtrans);
		InputFundData inputCancelData = new InputFundData(map);
		inputCancelData.setInnerfundtransnbr(null);
		inputCancelData.setTranstime(DateUtil.getCurrentDateTime());
		inputCancelData.setTransdate(SysinfoExtendDAO.getSysInfo().getPostdate());
		inputCancelData.setOriginnertransnbr(innerfundtrans.getInnerfundtransnbr());
		inputCancelData.setDowntransnbr(innerfundtrans.getDowntransnbr());
		inputCancelData.setBizType(input.getBizType());
		inputCancelData.setPayeeacctnbr(innerfundtrans.getPayeracctnbr());
		UnionPayService unionPayService = (UnionPayService) DefaultSupportor.getService(FundChannelCode.UNIONPAY.toLowerCase());
		if (output.isSuccess()) {
			if (StringUtil.isStringEmpty(input.getInnerfundtransnbr())
					|| !StringUtil.isStringEmpty(output.getOrigSeqNbr())) {
				overallTransState = TransStatus.SUCCESS;
				input.setTransstatus(TransStatus.SUCCESS);
			} else {
				unionPayService.cancelTrans(inputCancelData);
				
				overallTransState = TransStatus.FAILURE;
				output.setRtxnstate(TransStatus.FAILURE);
				input.setTransstatus(TransStatus.FAILURE);
			}
		} else if (output.isFailure()) {
			unionPayService.cancelTrans(inputCancelData);
			
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
	
	/**
	 * 核心异常抹账
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead coreExWipeout(InputFundData input) throws PeException {
		RespCoreWipeout output = this.handleFundRtxn(InnerRtxnTyp.CoreExWipeout, input, new ReqCoreExWipeout(input),
				RespCoreWipeout.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreExWipeoutTimeOut);
		}
		return output;
	}
	/**
	 * 用于本行卡积分抵扣
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead coreIntegalPay(InputFundData input) throws PeException {
		RespCoreHead output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.CoreIntegalPay, input, new ReqCoreIntegalPay(input),
					RespCoreIntegalPay.class);
		if(output.isSuccess()&&OveralltransTyp.UPAY.equals(input.getTransid())){
			this.getOrigInnerfundtrans(input);
			this.updateIntegalClearDate(input, output);
		}else if (output.isFailure()) {
			if(InteralFlag.YES.equals(input.getInteralFlag())){
				((PointService)DefaultSupportor.getService(FundChannelCode.JFWG.toLowerCase())).cancelPayerIntegral(input);
			}
			output.setReturnmsg("营销资金扣款失败");
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreIntegalTransTimeOut);
			if(InteralFlag.YES.equals(input.getInteralFlag())&&OveralltransTyp.UPAY.equals(input.getTransid())){
				((PointService)DefaultSupportor.getService(FundChannelCode.JFWG.toLowerCase())).cancelPayerIntegral(input);
			}
			output.setReturnmsg("营销资金扣款超时");
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 用于本行卡积分异常抹账
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public void coreIntegalPayExWipeout(InputFundData input) throws PeException {
		this.getOrigIntegalfundtrans(input);
		if(InteralFlag.YES.equals(input.getInteralFlag())){
			RespCoreWipeout output = this.handleFundRtxn(InnerRtxnTyp.CoreExWipeout, input, new ReqCoreExWipeout(input),
					RespCoreWipeout.class);
			if(output.isSuccess()&&output.getRecStatus().equals(PayStatus.PAY_STATUS_OK)){
				input.setTransstatus(TransStatus.REVOKED);
				this.updateIntegalInnerfundtrans(input);
			}else{
				this.insertTransexceptionreg(input, RtxnExceptionFunction.CoreExWipeoutTimeOut);
			}
			
		}
	}
	/**
	 * 用于本行卡积分抵扣超时
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public void CoreIntegalTransTimeOut(InputFundData input) throws PeException {
		RespCoreQueryRtxn output = this.handleNonFundRtxn(new ReqCoreQueryRtxn(input), RespCoreQueryRtxn.class);
		if (output.isSuccess()) {
			if (StringUtil.isStringEmpty(input.getInnerfundtransnbr())
					|| !StringUtil.isStringEmpty(output.getOrigSeqNbr())) {
				input.setTransstatus(TransStatus.SUCCESS);
				output.setDownrtxnnbr(output.getOrigSeqNbr());
				this.getOrigInnerfundtrans(input);
				this.updateIntegalClearDate(input, output);
				input.setOriginnertransnbr(null);
			}else{
				output.setRtxnstate(TransStatus.FAILURE);
			}
		} else if (output.isFailure()) {
			input.setTransstatus(TransStatus.FAILURE);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		if (!StringUtil.isStringEmpty(input.getInnerfundtransnbr())) {
			this.updateInnerfundtrans(input, output);
			this.updateOveralltrans(input);
		}
		if (!StringUtil.isStringEmpty(input.getInnerfundtransnbr())
				&& !StringUtil.isStringEmpty(output.getOrigSeqNbr())) {
			input.setDowntransnbr(output.getOrigSeqNbr());	
			input.setOriginnertransnbr(input.getInnerfundtransnbr());
			this.coreIntegalPayExWipeout(input);
		}
		
	}
	
	/**
	 * 用于本行冲积分的钱超时处理
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public void CoreExWipeoutTimeOut(InputFundData input) throws PeException {
		this.getPointOrigInnerfundtrans(input);
		if(InteralFlag.YES.equals(input.getInteralFlag())){
			RespCoreWipeout output = this.handleNonFundRtxn(new ReqCoreExWipeout(input),
					RespCoreWipeout.class);
			if(output.isSuccess()){
				if(output.getRecStatus().equals(ExWipeoutStatus.WIPOUT_OK)||output.getRecStatus().equals(ExWipeoutStatus.WIPOUT_OK_AG)){
					input.setTransstatus(TransStatus.REVOKED);
				}else{
					input.setTransstatus(null);
					output.setRtxnstate(TransStatus.REVOKFAIL);
				}
			}else if(output.isFailure()){
				input.setTransstatus(null);
				output.setRtxnstate(TransStatus.REVOKFAIL);
			}else{
				this.formatException(output);
			}
			if (!StringUtil.isStringEmpty(input.getInnerfundtransnbr())) {
				this.updateIntegalCancelInnerfundtrans(input, output);
			}
		}
	}

	@Override
	public RespSysHead withdrawal(InputFundData input) throws PeException {
		RespHvpsWithdrawal output = this.handleNonFundRtxn(new ReqHvpsWithdrawal(input),RespHvpsWithdrawal.class);
		if(output.isFailure()){
			this.formatException(output);
		}else if(output.isTimeout()){
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead recharge(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	} 
}
