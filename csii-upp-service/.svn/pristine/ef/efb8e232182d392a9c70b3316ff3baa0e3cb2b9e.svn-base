package com.csii.upp.service.fundprocess;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespCheckOffDateAppl;
import com.csii.upp.dto.router.point.ReqCancelPayerIntegral;
import com.csii.upp.dto.router.point.ReqConsumePayerIntegral;
import com.csii.upp.dto.router.point.ReqQueryPayerIntegral;
import com.csii.upp.dto.router.point.RespCancelPayerIntegral;
import com.csii.upp.dto.router.point.RespConsumePayerIntegral;
import com.csii.upp.dto.router.point.RespQueryPayerIntegral;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;

/**
 * 积分支付服务类
 * 
 * @author wy
 * 
 */
public class PointService extends RouterService {
	
	/**
	 * 积分查询
	 * 
	 * @param input
	 * @throws PeException 
	 */
	public RespSysHead queryPayerIntegral(InputFundData input) throws PeException{
		RespQueryPayerIntegral output = (RespQueryPayerIntegral)this.handleNonFundRtxn(new ReqQueryPayerIntegral(input), RespQueryPayerIntegral.class);
		if (output.isSuccess()) {
			return output;
		} else if (output.isFailure()) {
			formatException(output);
		} else if (output.isTimeout()) {
			formatException(output);
		}
		return null;	
	}
	/**
	 * 积分消费
	 * 
	 * @param input
	 * @throws PeException 
	 */
	public RespConsumePayerIntegral consumePayerIntegral(InputFundData input) throws PeException{
		RespConsumePayerIntegral output = (RespConsumePayerIntegral)this.handleFundRtxn(InnerRtxnTyp.PointConsume,input, new ReqConsumePayerIntegral(input), RespConsumePayerIntegral.class);
		if (output.isSuccess()) {
			input.setOriginnertransnbr(input.getInnerfundtransnbr());
			RespCheckOffDateAppl  outputHost=	(RespCheckOffDateAppl) 
			((CoreService)DefaultSupportor.getService(FundChannelCode.CORE.toLowerCase())).queryDownPostDate(input);
			if(!StringUtil.isObjectEmpty(outputHost.getDownrtxndate())){
				this.updateIntegalClearDate(input, outputHost);
			}
			return output;
		} else if (output.isFailure()) {
			formatException(output);
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.JFWGConsumeTransTimeOut);
			formatException(output);
		}
		return null;	
	}
	/**  
	 * 积分撤销
	 * 
	 * @param input
	 * @throws PeException 
	 */
	public RespCancelPayerIntegral cancelPayerIntegral(InputFundData input) throws PeException{
		this.getOrigInnerfundtrans(input);
		if(!InteralFlag.YES.equals(input.getInteralFlag())){
			return null;
		}
		RespCancelPayerIntegral output = (RespCancelPayerIntegral)this.handleFundRtxn(InnerRtxnTyp.PointCancel,input, new ReqCancelPayerIntegral(input), RespCancelPayerIntegral.class);
		// 积分撤销成功更新原消费状态为已撤销
		if (output.isSuccess()) {
			input.setTransstatus(TransStatus.REVOKED);
			this.updateIntegalInnerfundtrans(input);
			return output;
		} else if (output.isFailure()) {
			if(ResponseCode.NOPOINTCONSUME.equals(output.getReturncode())){
				input.setTransstatus(TransStatus.FAILURE);
				this.updateIntegalInnerfundtrans(input);
			}else{
				this.insertTransexceptionreg(input, RtxnExceptionFunction.JFWGCancelTransTimeOut);
			}
		} else if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.JFWGCancelTransTimeOut);
		}
		return null;	
	}
	
	/**
	 * 积分消费超时，撤销超时失败统一账务处理
	 * 
	 * @param input
	 */
	public void JFWGConsumeTransTimeOut(InputFundData input) throws PeException {
		this.getPointOrigInnerfundtrans(input);
		RespCancelPayerIntegral output = (RespCancelPayerIntegral)this.handleNonFundRtxn(new ReqCancelPayerIntegral(input), RespCancelPayerIntegral.class);
		if (output.isSuccess()) {
			input.setTransstatus(TransStatus.REVOKED);
			RespCheckOffDateAppl  outputHost=	(RespCheckOffDateAppl) 
			((CoreService)DefaultSupportor.getService(FundChannelCode.CORE.toLowerCase())).queryDownPostDate(input);
			if(!StringUtil.isObjectEmpty(outputHost.getDownrtxndate())){
				this.updateIntegalClearDate(input, outputHost);
			}
		} else if (output.isFailure()) {
			if(ResponseCode.NOPOINTCONSUME.equals(output.getReturncode())){
				input.setTransstatus(TransStatus.FAILURE);
			}else{
				input.setTransstatus(TransStatus.REVOKFAIL);
			}
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		if (!StringUtil.isStringEmpty(input.getInnerfundtransnbr())) {
			this.updateIntegalInnerfundtrans(input);
			this.updateOveralltrans(input);
		}
		
	}
	public void JFWGCancelTransTimeOut(InputFundData input) throws PeException {
		this.getPointOrigInnerfundtrans(input);
		RespCancelPayerIntegral output = (RespCancelPayerIntegral)this.handleNonFundRtxn(new ReqCancelPayerIntegral(input), RespCancelPayerIntegral.class);
		if (output.isSuccess()) {
			input.setTransstatus(TransStatus.REVOKED);
		} else if (output.isFailure()) {
			if(ResponseCode.CANCELREPEAT.equals(output.getReturncode())){
				input.setTransstatus(TransStatus.REVOKED);
				output.setRtxnstate(TransStatus.SUCCESS);
			}else{
				input.setTransstatus(null);
				output.setRtxnstate(TransStatus.REVOKFAIL);
			}
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		if (!StringUtil.isStringEmpty(input.getInnerfundtransnbr())) {
			this.updateIntegalCancelInnerfundtrans(input, output);
		}
		
	}

}
