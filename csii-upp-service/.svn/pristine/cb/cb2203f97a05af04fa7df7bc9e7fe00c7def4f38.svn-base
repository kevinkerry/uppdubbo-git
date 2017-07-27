package com.csii.upp.service.payment;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.ExpHandleState;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dao.generate.TransexceptionregDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.fundprocess.ReqBatchTransfer;
import com.csii.upp.dto.router.fundprocess.ReqCheckCardPwd;
import com.csii.upp.dto.router.fundprocess.ReqConfirmedVirtualAcctPay;
import com.csii.upp.dto.router.fundprocess.ReqCustomAuthen;
import com.csii.upp.dto.router.fundprocess.ReqOtherEbankPayment;
import com.csii.upp.dto.router.fundprocess.ReqQueryAcctOpenStatusPayment;
import com.csii.upp.dto.router.fundprocess.ReqQueryBatchTransfer4TimeOut;
import com.csii.upp.dto.router.fundprocess.ReqQueryCardInfo;
import com.csii.upp.dto.router.fundprocess.ReqQueryHostDate;
import com.csii.upp.dto.router.fundprocess.ReqQueryInnerAcctInfo;
import com.csii.upp.dto.router.fundprocess.ReqQueryIntegral;
import com.csii.upp.dto.router.fundprocess.ReqQueryLimitInfo;
import com.csii.upp.dto.router.fundprocess.ReqQueryOrderPayStatus;
import com.csii.upp.dto.router.fundprocess.ReqQueryOverallRtxnStat;
import com.csii.upp.dto.router.fundprocess.ReqQueryQrCodeUrl;
import com.csii.upp.dto.router.fundprocess.ReqQueryVirtualAcctBalance;
import com.csii.upp.dto.router.fundprocess.ReqRefound;
import com.csii.upp.dto.router.fundprocess.ReqSendMessage;
import com.csii.upp.dto.router.fundprocess.ReqSyhMerDate;
import com.csii.upp.dto.router.fundprocess.ReqUnifiedPayment;
import com.csii.upp.dto.router.fundprocess.ReqUnifiedRegister;
import com.csii.upp.dto.router.fundprocess.ReqUpdMerDate;
import com.csii.upp.dto.router.fundprocess.ReqVirtualAcctTransfer;
import com.csii.upp.dto.router.fundprocess.ReqVirtualRefound;
import com.csii.upp.dto.router.fundprocess.RespBatchTransfer;
import com.csii.upp.dto.router.fundprocess.RespCheckCardPwd;
import com.csii.upp.dto.router.fundprocess.RespConfirmedVirtualAcctPay;
import com.csii.upp.dto.router.fundprocess.RespCustomAuthen;
import com.csii.upp.dto.router.fundprocess.RespFundProHead;
import com.csii.upp.dto.router.fundprocess.RespQueryAcctOpenStatusPayment;
import com.csii.upp.dto.router.fundprocess.RespQueryBatchTransfer4TimeOut;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.dto.router.fundprocess.RespQueryHostDate;
import com.csii.upp.dto.router.fundprocess.RespQueryInnerAcctInfo;
import com.csii.upp.dto.router.fundprocess.RespQueryIntegral;
import com.csii.upp.dto.router.fundprocess.RespQueryLimitInfo;
import com.csii.upp.dto.router.fundprocess.RespQueryOrderPayStatus;
import com.csii.upp.dto.router.fundprocess.RespQueryOverallRtxnStat;
import com.csii.upp.dto.router.fundprocess.RespQueryQrCodeUrl;
import com.csii.upp.dto.router.fundprocess.RespQueryVirtualBalance;
import com.csii.upp.dto.router.fundprocess.RespSendMessage;
import com.csii.upp.dto.router.fundprocess.RespSyhMerDate;
import com.csii.upp.dto.router.fundprocess.RespUnifiefPayment;
import com.csii.upp.service.BasePayService;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.StringUtil;

public class FDPSService extends BasePayService {
	protected void insertTransexceptionreg(InputPaymentData input, RtxnExceptionFunction ex) {
		Transexceptionreg record = new Transexceptionreg();
		record.setTransdate(input.getTransdate());
		record.setTranstime(input.getTranstime());
		record.setExphandlestatus(ExpHandleState.PREHANDLE);
		record.setRetrytimes(0L);
		record.setMaxhandletimes(this.getExpHandleMaxTimes());
		InputPaymentData regInput =new InputPaymentData();
		regInput.setTransseqnbr(input.getTransseqnbr());
		regInput.setMernbr(input.getMernbr());
		regInput.setMerseqnbr(input.getMerseqnbr());
		regInput.setTransdate(input.getTransdate());
		regInput.setTranstime(input.getTranstime());
		regInput.setTranstypcd(input.getTranstypcd());
		regInput.setTransamt(input.getTransamt());
		regInput.setTransId(input.getTransId());
		regInput.setQueryHistFlag(input.isQueryHistFlag());
		regInput.setPayModeCd(input.getPayModeCd());
		Onlinesubtrans origSubTrans = input.getOrigSubTrans();
		if(origSubTrans!=null){
			Onlinesubtrans origSubTransReg = new Onlinesubtrans();
			origSubTransReg.setTransseqnbr(origSubTrans.getTransseqnbr());
			origSubTransReg.setSubtransseqnbr(origSubTrans.getSubtransseqnbr());
			origSubTransReg.setTransamt(origSubTrans.getTransamt());
			origSubTransReg.setRefundedamt(origSubTrans.getRefundedamt());
			regInput.setOrigSubTrans(origSubTransReg);
		}
		Onlinetrans origTrans=input.getOrigTrans();
		if(origTrans!=null){
			Onlinetrans origTransReg = new Onlinetrans();
			origTransReg.setTransseqnbr(origTrans.getTransseqnbr());
			origTransReg.setTransamt(origTrans.getTransamt());
			origTransReg.setRefundedamt(origTrans.getRefundedamt());
			regInput.setOrigTrans(origTransReg);
		}
		record.setExptransdatasnap(BeanUtils.beanToXmlString(regInput));
		record.setFundchannelcode(ex.getFundChanelCd());
		record.setExphandletranscode(ex.getRtxnCode());
		record.setExphandlertransdesc(ex.getRtxnDesc());
		try {
			record.setExpseqnbr(IDGenerateFactory.generateSeqId());
			TransexceptionregDAO.insert(record);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert Transexceptionreg Table Failed.", e);
		}
	}

	/**
	 * @param input
	 *            贷记卡、借记卡卡信息查询
	 * @return
	 */
	public RespSysHead queryCardInfo(InputPaymentData input) throws PeException {
		RespQueryCardInfo output = this.send(new ReqQueryCardInfo(input), RespQueryCardInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		// 用户输入的和核心手机号判断
		if(input.getPayercardtypcd().equals(CardTypCd.DEBIT)){
			if (!output.getPayerPhoneNoList().contains(input.getPayerphoneno())) {
				throw new PeException(DictErrors.DEBIT_PHONE_AND_CARD_NOT_MATCH);
		}
		}else if(input.getPayercardtypcd().equals(CardTypCd.CREDIT)){
			if (!output.getPayerPhoneNoList().contains(input.getPayerphoneno())) {
				throw new PeException(DictErrors.CREDIT_PHONE_AND_CARD_NOT_MATCH);
			}
		}
		return output;
	}
	
	/**
	 * @param input
	 *            小米付额度信息查询
	 * @return
	 */
	public RespSysHead queryXiaoMiTransLimit(InputPaymentData input) throws PeException {
		RespQueryLimitInfo output = this.send(new ReqQueryLimitInfo(input), RespQueryLimitInfo.class);
		return output;
	}
	
	/**
	 * @param input
	 *            卡信息验证贷记卡、借记卡卡信息查询
	 * @return
	 */
	public RespSysHead cvsrQueryCardInfo(InputPaymentData input) throws PeException {
		RespQueryCardInfo output = this.send(new ReqQueryCardInfo(input), RespQueryCardInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * 
	 *            贷记卡、借记卡卡信息查询 机构号
	 * @return
	 */
	public RespSysHead queryCardDeptInfo(InputPaymentData input) throws PeException {
		RespQueryCardInfo output = this.send(new ReqQueryCardInfo(input), RespQueryCardInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 *            查询账户信息
	 * @return
	 */
	public RespSysHead queryAcctInfo(InputPaymentData input) throws PeException {
		RespQueryCardInfo output = this.send(new ReqQueryCardInfo(input), RespQueryCardInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}

		return output;
	}

	/**
	 * @param input
	 *            查询内部账户详细信息
	 * @return
	 */
	public RespSysHead queryInnerAcctInfo(InputPaymentData input) throws PeException {
		RespQueryInnerAcctInfo output = this.send(new ReqQueryInnerAcctInfo(input), RespQueryInnerAcctInfo.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}

		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             发往FundProcess进行支付交易
	 */
	public RespSysHead unifiedPayment(InputPaymentData input) throws PeException {
		RespUnifiefPayment output = this.send(new ReqUnifiedPayment(input), RespUnifiefPayment.class);
		input.setDownsystransnbr(output.getDownrtxnnbr());
		input.setDownsysdate(output.getDownrtxndate());
		input.setDownsysdatetime(output.getDownrtxntime());
		input.setDownsysrespcode(output.getReturncode());
		input.setTransstatus(output.getTransStatus());
		if (output.isTimeout()) {
			// 只有payment没有收到fundprocess应答时才做超时查询
			if (StringUtil.isStringEmpty(output.getReturncode())) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.FDPSHandleTransTimeOut);
			}
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             发往FundProcess进行他行网银支付交易
	 */
	public RespSysHead otherEbankPayment(InputPaymentData input) throws PeException {
		RespUnifiefPayment output = this.send(new ReqOtherEbankPayment(input), RespUnifiefPayment.class);
		input.setDownsystransnbr(output.getDownrtxnnbr());
		input.setDownsysdate(output.getDownrtxndate());
		input.setDownsysdatetime(output.getDownrtxntime());
		input.setDownsysrespcode(output.getReturncode());
		input.setTransstatus(output.getTransStatus());
		if (output.isTimeout()) {
			this.formatException(output);
			// 只有payment没有收到fundprocess应答时才做超时查询
			if (StringUtil.isStringEmpty(output.getReturncode())) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.FDPSHandleTransTimeOut);
			}
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             发往FundProcess进行虚账户转账交易
	 */
	public RespSysHead virtualAcctTransfer(InputPaymentData input) throws PeException {
		RespFundProHead output = this.send(new ReqVirtualAcctTransfer(input), RespFundProHead.class);
		input.setDownsystransnbr(output.getDownrtxnnbr());
		input.setDownsysdate(output.getDownrtxndate());
		input.setDownsysdatetime(output.getDownrtxntime());
		input.setDownsysrespcode(output.getReturncode());
		if (output.isTimeout()) {
			// 只有payment没有收到fundprocess应答时才做超时查询
			if (StringUtil.isStringEmpty(output.getReturncode())) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.FDPSHandleTransTimeOut);
			}
			this.formatException(output);

		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             发往FundProcess进行退货交易
	 */
	public RespSysHead refound(InputPaymentData input) throws PeException {		
		RespFundProHead output = null;
		if (PayModeCd.HOLD.equals(input.getPayModeCd())) {
			output = this.send(new ReqVirtualRefound(input), RespFundProHead.class);
		}else{
			output = this.send(new ReqRefound(input), RespFundProHead.class);
		}
		input.setDownsystransnbr(output.getDownrtxnnbr());
		input.setDownsysdate(output.getDownrtxndate());
		input.setDownsysdatetime(output.getDownrtxntime());
		input.setDownsysrespcode(output.getReturncode());
		input.setTransstatus(output.getTransStatus());
		if (output.isTimeout()) {
			// 只有payment没有收到fundprocess应答时才做超时查询
			if (StringUtil.isStringEmpty(output.getReturncode())) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.FDPSHandleTransTimeOut);
			}
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             虚账户资金解冻
	 */
	public RespSysHead confirmedVirtualAcctPay(InputPaymentData input) throws PeException {
		RespConfirmedVirtualAcctPay output = this.send(new ReqConfirmedVirtualAcctPay(input),
				RespConfirmedVirtualAcctPay.class);
		if (output.isTimeout()) {
			// 只有payment没有收到fundprocess应答时才做超时查询
			if (StringUtil.isStringEmpty(output.getReturncode())) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.FDPSHandleTransTimeOut);
			}
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	/**
	 * @param input
	 * @return
	 * @throws PeException
	 *             用于校验卡密码
	 */
	public RespSysHead checkCardPwd(InputPaymentData input) throws PeException {

		RespCheckCardPwd output = this.send(new ReqCheckCardPwd(input), RespCheckCardPwd.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	public RespQueryVirtualBalance QueryVirtualAcctBalanceInfo(InputPaymentData input) throws PeException {
		RespQueryVirtualBalance output = this.send(new ReqQueryVirtualAcctBalance(input),
				RespQueryVirtualBalance.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	
	public RespSysHead RegisterOtherQuickPay(InputPaymentData input) throws PeException {
		RespUnifiefPayment output = this.send(new ReqUnifiedRegister(input), RespUnifiefPayment.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 * 用于积分查询
	 * @param input InputPaymentData
	 * @return
	 * @throws PeException
	 */
	public RespQueryIntegral QueryIntegral(InputPaymentData input) throws PeException {
		RespQueryIntegral output = this.send(new ReqQueryIntegral(input), RespQueryIntegral.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	public RespQueryOrderPayStatus queryOrderPayStatus(InputPaymentData input) throws PeException {
		RespQueryOrderPayStatus output = this.send(new ReqQueryOrderPayStatus(input), RespQueryOrderPayStatus.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	public RespQueryAcctOpenStatusPayment queryAcctOpenStatus(InputPaymentData input) throws PeException {
		RespQueryAcctOpenStatusPayment output = this.send(new ReqQueryAcctOpenStatusPayment(input),
				RespQueryAcctOpenStatusPayment.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	public RespSysHead sendMessage(InputPaymentData input) throws PeException {
		RespSendMessage output = this.send(new ReqSendMessage(input), RespSendMessage.class);
		return output;
	}

	/**
	 * PAYMENT交易超时做查询并修改交易状态:支付，退货、富阳支付，富阳提现，富阳确认支付
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public void handleTransTimeOut(InputPaymentData input) throws PeException {
		RespQueryOverallRtxnStat output = this.send(new ReqQueryOverallRtxnStat(input), RespQueryOverallRtxnStat.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		if (TransStatus.SUCCESS.equals(output.getOverAllTransStatus())) {
			if (TransTypCd.PAY.equals(input.getTranstypcd()) || TransTypCd.WTH.equals(input.getTranstypcd())) {
				input.setTransstatus(TransStatus.SUCCESS);
				OnlineTransExtendDAO.updateTransStatus(input);
			} else if (TransTypCd.RETURN.equals(input.getTranstypcd())) {
				// 退货成功,更新原交易订单
				OnlineTransExtendDAO.updateOrigTrans(input);
				input.setTransstatus(TransStatus.SUCCESS);
				OnlineTransExtendDAO.updateTransStatus(input);
			} else if (PayModeCd.HOLD.equals(input.getPayModeCd())) {
				Onlinesubtrans origSubTrans = input.getOrigSubTrans();
				boolean isQueryHist = false;// 查询当前表还是历史表：true:历史表,false:当前表
				Onlinetrans onlineTrans = null;
				Onlinetranshist onlineTransHist = null;
				String origMerSeqNbr = null;
				try {
					onlineTrans = OnlinetransDAO.selectByPrimaryKey(origSubTrans.getTransseqnbr());
				} catch (SQLException e1) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
				if (onlineTrans == null) {
					try {
						onlineTransHist = OnlinetranshistDAO.selectByPrimaryKey(origSubTrans.getSubtransseqnbr());
					} catch (SQLException e) {
						throw new PeException(DictErrors.TRANS_EXCEPTION);
					}
					if (onlineTransHist != null) {
						origMerSeqNbr = onlineTransHist.getMerseqnbr();
						isQueryHist = true;
					} else {
						throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
					}
				} else {
					origMerSeqNbr = onlineTrans.getMerseqnbr();
				}
				OnlineTransExtendDAO.updateComfirmPay(isQueryHist, origSubTrans.getTransseqnbr(),
						origSubTrans.getSubtransseqnbr(), input.getTransamt(), origMerSeqNbr, input.getTranstime());
			}
		} else if (TransStatus.FAILURE.equals(output.getOverAllTransStatus())) {
			if (TransTypCd.PAY.equals(input.getTranstypcd()) || TransTypCd.WTH.equals(input.getTranstypcd())
					||TransTypCd.RETURN.equals(input.getTranstypcd())) {
				input.setTransstatus(TransStatus.FAILURE);
				OnlineTransExtendDAO.updateTransStatus(input);
			}
		}
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead batchTransfer(InputPaymentData input) throws PeException {
		return this.send(new ReqBatchTransfer(input), RespBatchTransfer.class);
	}
	
	/**
	 * 批量转账查询
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryBatchTransfer4TimeOut(InputPaymentData input) throws PeException {
		RespQueryBatchTransfer4TimeOut output = this.send(new ReqQueryBatchTransfer4TimeOut(input), RespQueryBatchTransfer4TimeOut.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 *查询核心日期
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryHostDate(InputPaymentData input) throws PeException {
		RespQueryHostDate output = this.send(new ReqQueryHostDate(input), RespQueryHostDate.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 *客户鉴权(POC测试使用)	
	 *	
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead customAuthen(InputPaymentData input) throws PeException {
		RespCustomAuthen output = this.send(new ReqCustomAuthen(input), RespCustomAuthen.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	
	/**
	 *商户信息同步(新增)		
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead symerinfo(InputPaymentData input) throws PeException {
		RespSyhMerDate output = this.send(new ReqSyhMerDate(input), RespSyhMerDate.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 *商户信息同步(更新)		
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead updmerinfo(InputPaymentData input) throws PeException {
		RespSyhMerDate output = this.send(new ReqUpdMerDate(input), RespSyhMerDate.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 *查询二维码信息
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryQrCodeUrl(InputPaymentData input) throws PeException {
		RespQueryQrCodeUrl output = this.send(new ReqQueryQrCodeUrl(input), RespQueryQrCodeUrl.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}
}
