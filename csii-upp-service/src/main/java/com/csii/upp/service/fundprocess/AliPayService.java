package com.csii.upp.service.fundprocess;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.ScanCodeMode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.alipacode.ReqAlipayCodeActivePay;
import com.csii.upp.dto.router.alipacode.ReqAlipayCodePassivePay;
import com.csii.upp.dto.router.alipacode.ReqAlipayCodePayStatus;
import com.csii.upp.dto.router.alipacode.ReqAlipayCodeRefStatus;
import com.csii.upp.dto.router.alipacode.ReqAlipayCodeSynInfo;
import com.csii.upp.dto.router.alipacode.ReqAlipayCodeUpdInfo;
import com.csii.upp.dto.router.alipacode.ReqRefundToAli;
import com.csii.upp.dto.router.alipacode.ReqUndoToAli;
import com.csii.upp.dto.router.alipacode.RespAlipayCodePayStatus;
import com.csii.upp.dto.router.alipacode.RespAlipayCodePreHead;
import com.csii.upp.dto.router.alipacode.RespAlipayCodeRefStatus;
import com.csii.upp.dto.router.alipacode.RespAlipayCodeSynInfo;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;

/**
 * 支付宝渠道服务
 * 
 * @author WY
 * 
 */
public class AliPayService extends RouterService implements DebitRouter {
	/**
	 * 统一支付
	 */
	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
		RespAlipayCodePreHead output = null;
		boolean flag = true;
		if (flag) { //ScanCodeMode.ACTIVE.equals(input.getScancodemode())
			output = this.handleFundRtxn(InnerRtxnTyp.AlipayQrCodeActivePay, input, new ReqAlipayCodeActivePay(input),
					RespAlipayCodePreHead.class);
		} else if (ScanCodeMode.PASSIVE.equals(input.getScancodemode())) { 
			output = this.handleFundRtxn(InnerRtxnTyp.AlipayQrCodePassivePay, input, new ReqAlipayCodePassivePay(input),
					RespAlipayCodePreHead.class);
		}if(output.isSuccess()&&ScanCodeMode.PASSIVE.equals(input.getScancodemode())){
			input.setPayeracctnbr(output.getBuyerLogonId());
			input.setReceiptAmount(output.getReceiptAmount());
		}
		else if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			if(ScanCodeMode.PASSIVE.equals(input.getScancodemode())){
				this.insertTransexceptionreg(input, RtxnExceptionFunction.AlipayQrcodeTransTimeOut);
			}
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 查询原二维码信息
	 */
	public RespSysHead queryQrCodeUrl(InputFundData input) throws PeException {
		RespAlipayCodePreHead output = null;
		output = this.handleNonFundRtxn(new ReqAlipayCodeActivePay(input), RespAlipayCodePreHead.class);
		 if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 查询原二维码支付状态
	 */
	public RespAlipayCodePayStatus alipayQrcodeTransTimeOut(InputFundData input) throws PeException {
		RespAlipayCodePayStatus output = null;
		String overallTransState = null;
		output = this.handleNonFundRtxn(new ReqAlipayCodePayStatus(input), RespAlipayCodePayStatus.class);
		if (output.isSuccess()){
			overallTransState = TransStatus.SUCCESS;
			input.setTransstatus(TransStatus.SUCCESS);
		}
		if (output.isFailure()) {
			if(!input.isTimeout()){
				return output;
			}
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
		return output;
	}
	/**
	 * 查询原二维码退货状态
	 */
	public RespSysHead alipayQrcodeRefoundTimeOut(InputFundData input) throws PeException {
		RespSysHead output = null;
		String overallTransState = null;
		output = this.handleNonFundRtxn(new ReqAlipayCodeRefStatus(input), RespAlipayCodeRefStatus.class);
		if (output.isSuccess()){
			overallTransState = TransStatus.SUCCESS;
			input.setTransstatus(TransStatus.SUCCESS);
		}
		if (output.isFailure()) {
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
		return output;
	}
  /**
   * 
   * 功能：支付宝二维码退款
   * 作者：liru
   * @mender:
   * @param:InputFundData input
   * @return:RespSysHead
   * @remack:(认为有必要的描述)
   */
	public RespSysHead refoundTrans(InputFundData input) throws PeException {
		RespAlipayCodePreHead output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.AlipayRefoundTrans, input, new ReqRefundToAli(input),RespAlipayCodePreHead.class);
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.AlipayQrcodeRefoundTimeOut);
			this.formatException(output);
		}
		return output;
	}
	
 /**
   * 
   * 功能：支付宝二维码撤消
   * 作者：liru
   * @mender:
   * @param:InputFundData input
   * @return:RespSysHead
   * @remack:(认为有必要的描述)
   */
	public RespSysHead redoTrans(InputFundData input) throws PeException {
		RespAlipayCodePreHead output = null;
		output = this.handleFundRtxn(InnerRtxnTyp.AlipayRedoTrans, input, new ReqUndoToAli(input),RespAlipayCodePreHead.class);
		return output;
	}

	@Override
	public RespSysHead rtdtdr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input)
			throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryDownPostDate(InputFundData input)
			throws PeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 商户信息同步（新增）
	 */
	public RespAlipayCodeSynInfo synAlipayMerchantInfo(InputFundData input)
			throws PeException {
		
		RespAlipayCodeSynInfo output = this.handleNonFundRtxn(new ReqAlipayCodeSynInfo(input), RespAlipayCodeSynInfo.class);
		
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	 * 商户信息同步（修改）
	 */
	public RespAlipayCodeSynInfo updAlipayMerchantInfo(InputFundData input)
			throws PeException {
		
		RespAlipayCodeSynInfo output = this.handleNonFundRtxn(new ReqAlipayCodeUpdInfo(input), RespAlipayCodeSynInfo.class);
		
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
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
