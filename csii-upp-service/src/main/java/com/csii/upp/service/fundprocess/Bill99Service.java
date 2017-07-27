package com.csii.upp.service.fundprocess;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dao.extend.OveralltransExtendDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.bill99.ReqCheckRight;
import com.csii.upp.dto.router.bill99.ReqMASqueryRtxn;
import com.csii.upp.dto.router.bill99.ReqPurchase;
import com.csii.upp.dto.router.bill99.ReqRFD;
import com.csii.upp.dto.router.bill99.RespBill99Head;
import com.csii.upp.dto.router.bill99.RespCheckRight;
import com.csii.upp.dto.router.bill99.RespPurchase;
import com.csii.upp.dto.router.bill99.RespRFD;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.DebitRouter;

/**
 * 快钱服务类
 * 
 * @author 徐锦
 * 
 */
public class Bill99Service extends RouterService implements DebitRouter {

	@Override
	public RespSysHead rtdtdr(InputFundData input) throws PeException {
		// 签权
		RespCheckRight checkRightOutput = this.handleNonFundRtxn(new ReqCheckRight(input), RespCheckRight.class);
		if (!checkRightOutput.isSuccess()) {
			this.formatException(checkRightOutput);
		}
		ReqPurchase purchase = new ReqPurchase(input);
		purchase.setCheckright(checkRightOutput);
		RespPurchase output = this.handleFundRtxn(InnerRtxnTyp.Bill99MASpurchase, input, purchase, RespPurchase.class);
		if (output.isFailure()) {
			// 失败时直接返回 更新交易为失败
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
		} else if (output.isTimeout()) {
			// 状态置为超时，插入日间异常流水表，查询-挂账

			// TODO 需要修改插入异常表的异常
			this.insertTransexceptionreg(input, RtxnExceptionFunction.Bill99queryRtxnStateForDebit);
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

	/**
	 * 退货交易
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespRFD MASRFD(InputFundData input) throws PeException {
		String OrigOverrallRtxnnbr = input.getOveralltransnbr();
		List<Innerfundtrans> origfundRtxns = InnerfundtransExtendDAO.getInnerfundtrans(input.getFundchannelcode(),
				OrigOverrallRtxnnbr);
		input.getInputDataByOrigInnerfundtrans(
				origfundRtxns.get(origfundRtxns.size() > 1 ? origfundRtxns.size() - 1 : 0), true);
		// 块钱退货交易
		RespRFD output = this.handleFundRtxn(InnerRtxnTyp.Bill99MASRFD, input, new ReqRFD(input), RespRFD.class);
		if (output.isFailure()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.Bill99RtdtdrReTrave);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnStateForReTrave);// Query
			this.formatException(output);
		}
		return output;

	}

	/*
	 * 消费交易撤销交易 add by chenyp
	 * 
	 * @see
	 * com.csii.bank.pay.router.DebitRouter#rtdtdrReTrave(com.csii.bank.pay.
	 * dto.BaseInputData)
	 */
	@Override
	public RespSysHead rtdtdrReTrave(InputFundData input) throws PeException {
		String OrigOverrallRtxnnbr = input.getOrigoverralltransnbr();
		List<Innerfundtrans> origfundRtxns = InnerfundtransExtendDAO.getInnerfundtrans(input.getFundchannelcode(),
				OrigOverrallRtxnnbr);
		input.getInputDataByOrigInnerfundtrans(
				origfundRtxns.get(origfundRtxns.size() > 1 ? origfundRtxns.size() - 1 : 0), true);
		// 块钱退货交易
		RespRFD output = this.handleFundRtxn(InnerRtxnTyp.Bill99MASRFD, input, new ReqRFD(input), RespRFD.class);
		if (output.isFailure()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.Bill99RtdtdrReTrave);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, RtxnExceptionFunction.EAccountqueryRtxnStateForReTrave);// Query
			this.formatException(output);
		}
		return output;

	}

	/*
	 * 快钱消费后超时调用方法
	 */
	public void queryRtxnStateForDebit(InputFundData input) {
		RespBill99Head output = null;
		try {
			// 查询状态
			output = this.handleNonFundRtxn(new ReqMASqueryRtxn(input), RespBill99Head.class);
			// 如果失败则交易失败，总流水和内部流水更新
			if (output.isFailure()) {
				this.updateInnerfundtrans(input, output);
				OveralltransExtendDAO.updateOveralltrans(input.getInnerfundtransnbr(), TransStatus.FAILURE, null);
			} else if (output.isSuccess()) {
				// 成功则挂账
				insertTransexceptionreg(input, RtxnExceptionFunction.EAccountdodepositOnCreditAcct);
			} else if (output.isTimeout()) {
				// 如果超时继续查询
				this.insertTransexceptionreg(input, RtxnExceptionFunction.Bill99queryRtxnStateForDebit);// Query
				this.formatException(output);
			}
		} catch (PeException e) {
			log.error(e.getMessage());
		}

	}

	// 三个(老核心，块钱，电子账户)渠道做撤销(dr)超时方法
	public void queryRtxnStateForReTrave(InputFundData input) {
		// 查询bill99状态
		RespBill99Head output = null;
		try {
			output = this.handleNonFundRtxn(new ReqMASqueryRtxn(input), RespBill99Head.class);
			if (output.isSuccess()) {
				Overalltrans OrigOveralltrans = OveralltransDAO.selectByPrimaryKey(input.getOrigoverralltransnbr());
				OrigOveralltrans.setOveralltransstatus(TransStatus.REVOKED);
				OveralltransDAO.updateByPrimaryKeySelective(OrigOveralltrans);
			} else if (output.isFailure()) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.Bill99RtdtdrReTrave);
				this.formatException(output);
			}
		} catch (PeException e) {
			log.error(e.getMessage());
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

	}

	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryDownPostDate(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead recharge(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

}
