package com.csii.upp.service.fundprocess;

import java.sql.SQLException;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.unionpay.ReqUnionPayQuickPay;
import com.csii.upp.dto.router.unionpay.RespPayForAnothertb;
import com.csii.upp.dto.router.wechatcode.ReqReUndoToWechat;
import com.csii.upp.dto.router.wechatcode.ReqRefundToWechat;
import com.csii.upp.dto.router.wechatcode.ReqWeChatCodeActivePay;
import com.csii.upp.dto.router.wechatcode.ReqWeChatCodePayStatus;
import com.csii.upp.dto.router.wechatcode.ReqWeChatCodeSynInfo;
import com.csii.upp.dto.router.wechatcode.ReqWeChatCodeUpdInfo;
import com.csii.upp.dto.router.wechatcode.ReqWechatCodeRefStatus;
import com.csii.upp.dto.router.wechatcode.RespWeChatCodePayStatus;
import com.csii.upp.dto.router.wechatcode.RespWeChatCodePreHead;
import com.csii.upp.dto.router.wechatcode.RespWeChatCodeSynInfo;
import com.csii.upp.dto.router.wechatcode.RespWechatCodeRefStatus;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 微信渠道服务
 * 
 * @author WY
 * 
 */
public class WechatService extends RouterService implements DebitRouter {
	/**
	 * 统一支付
	 */
	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
//		RespWeChatCodePreHead output = new RespWeChatCodePreHead();
//		boolean flag = true;
//		if (flag) {//ScanCodeMode.ACTIVE.equals(input.getScancodemode()) 
//			output = this.handleFundRtxn(InnerRtxnTyp.WeChatQrCodeActivePay, input, new ReqWeChatCodeActivePay(input),
//					RespWeChatCodePreHead.class);
//		}
		/*} else if (ScanCodeMode.PASSIVE.equals(input.getScancodemode())) { 
			output = this.handleFundRtxn(InnerRtxnTyp.WeChatQrCodePassivePay, input, new ReqWeChatCodPassivePay(input),
					RespWeChatCodePreHead.class);
		}*/
//		if (output.isFailure()) {
//			if(ScanCodeMode.PASSIVE.equals(input.getScancodemode())){
//				this.insertTransexceptionreg(input, RtxnExceptionFunction.WeChatQrcodeTransTimeOut);
//			}
//			this.formatException(output);
//		} else if (output.isTimeout()) {
//			
//		}

		
		
		
		
		RespPayForAnothertb output = new RespPayForAnothertb();
		if (StringUtil.isStringEmpty(input.getSmsCode())) {
//			input.setTranscode(InnerRtxnTyp.UnionPayQuickPay);
//			input.setFundchannelcode(FundChannelCode.UNIONPAY);
//			Innerfundtrans funRtxn = this.initInnerfundtrans(input);
//			funRtxn.setTransstatus(TransStatus.PROCESSING);
//			this.insertInnerfundtrans(funRtxn);
//			// 银联快捷支付开通支付交易，通过页面表单跳转
//
//			Map<String, String> requestData = new HashMap<String, String>();
//			/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
//			requestData.put("version", "5.0.0");
//			requestData.put("encoding", "UTF-8");
//			requestData.put("signMethod", "01");
//			requestData.put("txnType", TxnType.TXNTYPE_01);
//			requestData.put("txnSubType", "01");
//			requestData.put("bizType", BizType.BIZTYPE_000301);
//			requestData.put("channelType", "02".equals(input.getChannelNbr()) ? "08" : "07");
//
//			/*** 商户接入参数 ***/
//			requestData.put("accNo", input.getPayeracctnbr());
//			requestData.put("customerInfo", new ReqUnionPayHead(input).getCustomer("UTF-8", new CustomerInfoObj(input, ConstUnionPay.PAY_OUT)));
//			requestData.put("merId", "929331053110008");
//			requestData.put("accessType", "0");
//			requestData.put("orderId", funRtxn.getInnerfundtransnbr());
//			requestData.put("txnTime", DateUtil.Date_To_DateTimeFormat(input.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
//			requestData.put("currencyCode", "156");
//			requestData.put("frontUrl", ((UnionPayTransport) this.getTransport()).getBaseFrontUrl() + input.getFrontCallBackUrl());
//			requestData.put("backUrl", ((UnionPayTransport) this.getTransport()).getBaseBackUrl()
//					+ "UnionPayOtherBankQuickPayCallBack.do");
//			String InttxnAmtx = (input.getTransamt().multiply(new BigDecimal(100))).toString();
//			if (InttxnAmtx.indexOf(".") != -1) {
//				requestData.put("txnAmt", InttxnAmtx.substring(0, InttxnAmtx.indexOf(".")));
//			} else {
//				requestData.put("txnAmt", InttxnAmtx);
//			}
//			String htmlStr = UnionPayTransport.createHtml(requestData);
//			// 把值返回html放到返回dto里面
//			output.setReturnHtml(htmlStr);
//			return output;
		} else {
			input.setTransnbr(input.getSmsInnerFundTransNbr());
			input.setInnerfundtransnbr(input.getSmsInnerFundTransNbr());
			input.setPayerphoneno(null);
			input.setTranstime(
					DateUtil.DateTimeFormat_To_Date(input.getSendUnionPayTime(), DateFormatCode.DATETIME_FORMATTER3));
			input.setTranscode(InnerRtxnTyp.WeChatQrCodeActivePay);
			insertInnerfundtrans(input);
			input.setTranscode(InnerRtxnTyp.UnionPayQuickPay);
			output = send(new ReqUnionPayQuickPay(input), RespPayForAnothertb.class);
			input.setTranscode(InnerRtxnTyp.WeChatQrCodeActivePay);
			updateInnerfundtrans(input, output);
			
			if (output.isTimeout()) {
				this.formatException(output);
			} else if (output.isFailure()) {
				// 失败时直接返回
				this.formatException(output);
			} else if (output.isReceived()) {
				// 这是为了让action里面返回处理中状态
				output.setReturnHtml("");
			}
		}
		return output;
	}
	
	


	
	
	
	
	/**
	 * 查询原二维码信息
	 */
	public RespSysHead queryQrCodeUrl(InputFundData input) throws PeException {
		RespWeChatCodePreHead output = null;
		output = this.handleNonFundRtxn(new ReqWeChatCodeActivePay(input), RespWeChatCodePreHead.class);
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
	public RespWeChatCodePayStatus weChatQrcodeTransTimeOut(InputFundData input) throws PeException {
		RespWeChatCodePayStatus output = null;
		String overallTransState = null;
		output = this.handleNonFundRtxn(new ReqWeChatCodePayStatus(input), RespWeChatCodePayStatus.class);
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
	public RespSysHead weChatQrcodeRefoundTimeOut(InputFundData input) throws PeException {
		RespSysHead output = null;
		String overallTransState = null;
		output = this.handleNonFundRtxn(new ReqWechatCodeRefStatus(input), RespWechatCodeRefStatus.class);
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
	public RespWeChatCodeSynInfo synWCMerchantInfo(InputFundData input)
			throws PeException {
		
		RespWeChatCodeSynInfo output = this.handleNonFundRtxn(new ReqWeChatCodeSynInfo(input), RespWeChatCodeSynInfo.class);
		
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
	public RespWeChatCodeSynInfo updWCMerchantInfo(InputFundData input)
			throws PeException {
		
		RespWeChatCodeSynInfo output = this.handleNonFundRtxn(new ReqWeChatCodeUpdInfo(input), RespWeChatCodeSynInfo.class);
		
		if (output.isFailure()) {
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}
	/**
	   * 
	   * 功能：微信退款
	   * 作者：liru
	   * @mender:
	   * @param:InputFundData input
	   * @return:RespSysHead
	   * @remack:(认为有必要的描述)
	   */
		public RespSysHead refoundTrans(InputFundData input) throws PeException {
			RespWeChatCodePreHead output = null;
			output = this.handleFundRtxn(InnerRtxnTyp.WeChatRefoundTrans, input, new ReqRefundToWechat(input),RespWeChatCodePreHead.class);
			if (output.isFailure()) {
				this.formatException(output);
			} else if (output.isTimeout()) {
				this.insertTransexceptionreg(input, RtxnExceptionFunction.WeChatQrcodeRefoundTimeOut);
				this.formatException(output);
			}
			return output;
		}
		
	 /**
	   * 
	   * 功能：微信撤消
	   * 作者：liru
	   * @mender:
	   * @param:InputFundData input
	   * @return:RespSysHead
	   * @remack:(认为有必要的描述)
	   */
		public RespSysHead redoTrans(InputFundData input) throws PeException {
			RespWeChatCodePreHead output = null;
			output = this.handleFundRtxn(InnerRtxnTyp.WeChatRedoTrans, input, new ReqReUndoToWechat(input),RespWeChatCodePreHead.class);
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
