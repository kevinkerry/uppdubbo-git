package com.csii.upp.service.fundprocess;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.BizType;
import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.CyberTypCd;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TxnType;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.unionpay.CustomerInfoObj;
import com.csii.upp.dto.router.unionpay.ReqAuthenticateName;
import com.csii.upp.dto.router.unionpay.ReqCheckFileApply;
import com.csii.upp.dto.router.unionpay.ReqCustomAuthen;
import com.csii.upp.dto.router.unionpay.ReqDsjy;
import com.csii.upp.dto.router.unionpay.ReqDsjyCx;
import com.csii.upp.dto.router.unionpay.ReqFileDownload;
import com.csii.upp.dto.router.unionpay.ReqPayForAnother;
import com.csii.upp.dto.router.unionpay.ReqQueryAcctOpenStatus;
import com.csii.upp.dto.router.unionpay.ReqQueryRtxnForOtherEBank;
import com.csii.upp.dto.router.unionpay.ReqQueryStateForQuickPay;
import com.csii.upp.dto.router.unionpay.ReqQueryStateForRefoundTrans;
import com.csii.upp.dto.router.unionpay.ReqSendMessage;
import com.csii.upp.dto.router.unionpay.ReqUnionPayCancel;
import com.csii.upp.dto.router.unionpay.ReqUnionPayHead;
import com.csii.upp.dto.router.unionpay.ReqUnionPayQuickPay;
import com.csii.upp.dto.router.unionpay.ReqUnionPayRefund;
import com.csii.upp.dto.router.unionpay.RespAuthenticateName;
import com.csii.upp.dto.router.unionpay.RespCheckFileApply;
import com.csii.upp.dto.router.unionpay.RespCustomAuthen;
import com.csii.upp.dto.router.unionpay.RespDsjyCx;
import com.csii.upp.dto.router.unionpay.RespDsjytb;
import com.csii.upp.dto.router.unionpay.RespFileDownload;
import com.csii.upp.dto.router.unionpay.RespPayForAnothertb;
import com.csii.upp.dto.router.unionpay.RespQueryRtxnState;
import com.csii.upp.dto.router.unionpay.RespRecharge;
import com.csii.upp.dto.router.unionpay.RespSendMessage;
import com.csii.upp.dto.router.unionpay.RespUnionPayCancel;
import com.csii.upp.dto.router.unionpay.RespUnionPayRefund;
import com.csii.upp.service.exception.fundction.RtxnExceptionFunction;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.transport.UnionPayTransport;
import com.csii.upp.util.BeanUtils;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 银联渠道服务
 * 
 * @author WHD
 * 
 */
public class UnionPayService extends RouterService implements CreditRouter, QueryRouter, DebitRouter {

	@Override
	public RespSysHead rtdtcr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		// 发送银联代付请求
		RespPayForAnothertb output = this.handleFundRtxn(InnerRtxnTyp.UnionPayAcctToCard, input,
				new ReqPayForAnother(input), RespPayForAnothertb.class);
		if (output.isFailure()) {
			// 失败时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.EAccounteAccountRecharge);
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 超时没有响应时插入异常处理表
			this.insertTransexceptionreg(input, BuildspctcrExceptionRtxnSnap(input),
					RtxnExceptionFunction.UnionPayqueryRtxnStateForCredit);
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

	public void queryRtxnStateForForCredit(InputFundData input) throws PeException {
		RespQueryRtxnState output = this.handleNonFundRtxn(new ReqQueryRtxnForOtherEBank(input),
				RespQueryRtxnState.class);
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

	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		return null;
	}

	/**
	 * 对账文件申请
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead applyCheckFile(InputFundData input) throws PeException {
		return this.handleNonFundRtxn(new ReqCheckFileApply(input), RespCheckFileApply.class);
	}

	@Override
	public RespSysHead queryPayeeAcctInfo(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead queryPayerAcctInfo(InputFundData input) throws PeException {
		return null;
	}

	/**
	 * 建立绑定关系（实名认证）
	 * 
	 * @return
	 * @throws PeException
	 */
	public RespAuthenticateName authenticateName(InputFundData input) throws PeException {
		RespAuthenticateName output = this.handleFundRtxn(InnerRtxnTyp.AuthenticateName, input,
				new ReqAuthenticateName(input), RespAuthenticateName.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			// 失败时直接返回
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead rtdtdr(InputFundData input) throws PeException {
		RespDsjytb output = this.handleFundRtxn(InnerRtxnTyp.UnionPayCardIntoAcct, input, new ReqDsjy(input),
				RespDsjytb.class);
		if (output.isTimeout()) {
			this.insertTransexceptionreg(input, RtxnExceptionFunction.UnionPayqueryRtxnStateForDebit);
			this.formatException(output);
		} else if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		}
		return output;
	}

	// 银联代收交易查询
	public RespDsjyCx dsjyCx(InputFundData input) throws PeException {
		ReqDsjyCx req = new ReqDsjyCx(input);
		RespDsjyCx output = this.handleNonFundRtxn(req, RespDsjyCx.class);
		if (output.isFailure())
			this.formatException(output);
		else if (output.isTimeout())
			this.formatException(output);
		return output;
	}

	/**
	 * 客户鉴权(POC测试使用)
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead customAuthen(InputFundData input) throws PeException {
		RespCustomAuthen output = this.handleNonFundRtxn(new ReqCustomAuthen(input), RespCustomAuthen.class);
		if (output.isFailure())
			this.formatException(output);
		else if (output.isTimeout())
			this.formatException(output);
		return output;
	}
	
	public void queryRtxnStateForDebit(InputFundData input) throws PeException {
		input.setMernbr(""); // 代收时商户号
		RespQueryRtxnState output = this.handleNonFundRtxn(new ReqQueryRtxnForOtherEBank(input),
				RespQueryRtxnState.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
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
	}

	@Override
	public RespSysHead rtctdr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead spctdr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTrave(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public void queryRtxnState(InputFundData input) throws PeException {

	}

	public RespFileDownload download(InputFundData input) throws PeException {
		return handleNonFundRtxn(new ReqFileDownload(input), RespFileDownload.class);
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		return null;
	}

	/**
	 * 他行企业网银网关支付B2B
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead otherbankPayment(InputFundData input) throws PeException {
		if (CyberTypCd.OUR.equals(input.getCyberTypCd())) {
			input.setTranscode(InnerRtxnTyp.UnionPayOtherPerBankPay);
		} else if (CyberTypCd.OTH.equals(input.getCyberTypCd())) {
			input.setTranscode(InnerRtxnTyp.UnionPayOtherEBankPay);
		} else {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.CYBER_TYP_CD});
		}
		input.setFundchannelcode(FundChannelCode.UNIONPAY);
		Innerfundtrans funRtxn = this.initInnerfundtrans(input);
		funRtxn.setTransstatus(TransStatus.PROCESSING);
		this.insertInnerfundtrans(funRtxn);
		// 银联网关交易，通过页面表单跳转

		Map<String, String> requestData = new HashMap<String, String>();
		/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
		requestData.put("version", "5.0.0");
		requestData.put("encoding", "UTF-8");
		requestData.put("signMethod", "01");
		requestData.put("txnType", TxnType.TXNTYPE_01);
		requestData.put("txnSubType", "01");
		if (CyberTypCd.OUR.equals(input.getCyberTypCd())) {
			requestData.put("bizType", BizType.BIZTYPE_000201);
			requestData.put("merId", "929331053110008");
		} else if (CyberTypCd.OTH.equals(input.getCyberTypCd())) {
			requestData.put("bizType", BizType.BIZTYPE_000202);
			requestData.put("merId", "929331053110009");
		}
		requestData.put("channelType", "02".equals(input.getChannelNbr()) ? "08" : "07");
		
		/*** 商户接入参数 ***/
		requestData.put("accessType", "0");
		requestData.put("orderId", funRtxn.getInnerfundtransnbr());
		requestData.put("txnTime", DateUtil.Date_To_DateTimeFormat(input.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
		requestData.put("currencyCode", "156");
		requestData.put("issInsCode", input.getPayerbanknbr()); // 发卡机构号
		requestData.put("frontUrl", ((UnionPayTransport) this.getTransport()).getBaseFrontUrl() + input.getFrontCallBackUrl());
		requestData.put("backUrl",
				((UnionPayTransport) this.getTransport()).getBaseBackUrl() + "UnionPayWGCallBack.do");
		String InttxnAmtx = (input.getTransamt().multiply(new BigDecimal(100))).toString();
		if (InttxnAmtx.indexOf(".") != -1) {
			requestData.put("txnAmt", InttxnAmtx.substring(0, InttxnAmtx.indexOf(".")));
		} else {
			requestData.put("txnAmt", InttxnAmtx);
		}

		String htmlStr = UnionPayTransport.createHtml(requestData);

		// 把值返回html放到返回dto里面
		RespPayForAnothertb anothertb = new RespPayForAnothertb();
		anothertb.setReturnHtml(htmlStr);
		return anothertb;
	}

	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
		RespPayForAnothertb output = new RespPayForAnothertb();
		if (StringUtil.isStringEmpty(input.getSmsCode())) {
			input.setTranscode(InnerRtxnTyp.UnionPayQuickPay);
			input.setFundchannelcode(FundChannelCode.UNIONPAY);
			Innerfundtrans funRtxn = this.initInnerfundtrans(input);
			funRtxn.setTransstatus(TransStatus.PROCESSING);
			this.insertInnerfundtrans(funRtxn);
			// 银联快捷支付开通支付交易，通过页面表单跳转

			Map<String, String> requestData = new HashMap<String, String>();
			/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
			requestData.put("version", "5.0.0");
			requestData.put("encoding", "UTF-8");
			requestData.put("signMethod", "01");
			requestData.put("txnType", TxnType.TXNTYPE_01);
			requestData.put("txnSubType", "01");
			requestData.put("bizType", BizType.BIZTYPE_000301);
			requestData.put("channelType", "02".equals(input.getChannelNbr()) ? "08" : "07");

			/*** 商户接入参数 ***/
			requestData.put("accNo", input.getPayeracctnbr());
			requestData.put("customerInfo", new ReqUnionPayHead(input).getCustomer("UTF-8", new CustomerInfoObj(input, ConstUnionPay.PAY_OUT)));
			requestData.put("merId", "929331053110008");
			requestData.put("accessType", "0");
			requestData.put("orderId", funRtxn.getInnerfundtransnbr());
			requestData.put("txnTime", DateUtil.Date_To_DateTimeFormat(input.getTranstime(), DateFormatCode.DATETIME_FORMATTER3));
			requestData.put("currencyCode", "156");
			requestData.put("frontUrl", ((UnionPayTransport) this.getTransport()).getBaseFrontUrl() + input.getFrontCallBackUrl());
			requestData.put("backUrl", ((UnionPayTransport) this.getTransport()).getBaseBackUrl()
					+ "UnionPayOtherBankQuickPayCallBack.do");
			String InttxnAmtx = (input.getTransamt().multiply(new BigDecimal(100))).toString();
			if (InttxnAmtx.indexOf(".") != -1) {
				requestData.put("txnAmt", InttxnAmtx.substring(0, InttxnAmtx.indexOf(".")));
			} else {
				requestData.put("txnAmt", InttxnAmtx);
			}
			String htmlStr = UnionPayTransport.createHtml(requestData);
			// 把值返回html放到返回dto里面
			output.setReturnHtml(htmlStr);
			return output;
		} else {
			input.setTransnbr(input.getSmsInnerFundTransNbr());
			input.setInnerfundtransnbr(input.getSmsInnerFundTransNbr());
			input.setPayerphoneno(null);
			input.setTranstime(
					DateUtil.DateTimeFormat_To_Date(input.getSendUnionPayTime(), DateFormatCode.DATETIME_FORMATTER3));
			output = this.handleFundRtxn(InnerRtxnTyp.UnionPayQuickPay, input, new ReqUnionPayQuickPay(input),
					RespPayForAnothertb.class);
			if (output.isTimeout()) {
				this.formatException(output);
			} else if (output.isFailure()) {
				// 失败时直接返回
				this.formatException(output);
			} else if (output.isReceived()) {
				// 这是为了让action里面返回处理中状态
				output.setReturnHtml("");
			}
			return output;
		}
	}
	
	/**
	 * 跨行智能支付开通
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	
	public RespSysHead unifiedRegister(InputFundData input) throws PeException {
		RespPayForAnothertb output = new RespPayForAnothertb();
			// 银联快捷支付开通交易，通过页面表单跳转
			Map<String, String> requestData = new HashMap<String, String>();
			/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
			requestData.put("version", "5.0.0");
			requestData.put("encoding", "UTF-8");
			requestData.put("signMethod", "01");
			requestData.put("txnType", TxnType.TXNTYPE_79);
			requestData.put("txnSubType", "00");
			requestData.put("bizType", BizType.BIZTYPE_000301);
			requestData.put("channelType", "07");

			/*** 商户接入参数 ***/
			requestData.put("accNo", input.getPayeracctnbr());
			requestData.put("customerInfo", new ReqUnionPayHead(input).getCustomer("UTF-8", new CustomerInfoObj(input, ConstUnionPay.PAY_IN)));
			requestData.put("merId", "929331053110008");
			requestData.put("accessType", "0");
			requestData.put("orderId", IDGenerateFactory.generateSeqId());
			requestData.put("txnTime", DateUtil.Date_To_DateTimeFormat(DateUtil.getCurrentDateTime(), DateFormatCode.DATETIME_FORMATTER3));
			requestData.put("currencyCode", "156");
			requestData.put("frontUrl", ((UnionPayTransport) this.getTransport()).getBaseFrontUrl() + input.getFrontCallBackUrl());
			requestData.put("backUrl", ((UnionPayTransport) this.getTransport()).getBaseBackUrl()
					+ "UnionPayOtherBankQuickPayRegisyterCallBack.do");
			String htmlStr = UnionPayTransport.createHtml(requestData);
			log.info("创建的htmlStr-->" + htmlStr);
			// 把值返回html放到返回dto里面
			output.setReturnHtml(htmlStr);
			return output;
	}
	
	
	

	public RespSysHead queryStateForQuickPay(InputFundData input, RespSysHead output) throws PeException {
		RespQueryRtxnState queryOutput = this.handleNonFundRtxn(new ReqQueryStateForQuickPay(input),
				RespQueryRtxnState.class);
		if (queryOutput.isSuccess()) {

		} else if (queryOutput.isFailure()) {
			this.formatException(queryOutput);
		} else if (queryOutput.isTimeout()) {
			this.formatException(queryOutput);
		} else if (queryOutput.isReceived()) {
			output.setReturncode(queryOutput.getOrigRespCode());
			output.setReturnmsg(queryOutput.getOrigRespMsg());
			output.setDownrtxnnbr(queryOutput.getQueryId());
			String a = queryOutput.getOrigRespCode();
			if ("00".equals(a) || "A6".equals(a)) {
				output.setRtxnstate(TransStatus.SUCCESS);
				this.updateInnerfundtrans(input, output);
			} else if ("03".equals(a) || "04".equals(a) || "05".equals(a) || "01".equals(a) || "12".equals(a)
					|| "60".equals(a)) {
				output.setRtxnstate(TransStatus.TIMEOUT);// 待发送
				this.updateInnerfundtrans(input, output);
				throw new PeException(DictErrors.TRANS_TIMEOUT);
			} else {
				output.setRtxnstate(TransStatus.FAILURE);
				this.updateInnerfundtrans(input, output);
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}
		return queryOutput;
	}

	/**
	 * 退货交易
	 */
	@Override
	public RespSysHead refoundTrans(InputFundData input) throws PeException {
		if (!StringUtil.isStringEmpty(input.getBizType())) {
			if (InnerRtxnTyp.UnionPayOtherPerBankPay.equals(input.getBizType())) {
				input.setBizType("000201");
			} else if (InnerRtxnTyp.UnionPayOtherEBankPay.equals(input.getBizType())) {
				input.setBizType("000202");
			} else if (InnerRtxnTyp.UnionPayQuickPay.equals(input.getBizType())) {
				input.setBizType("000301");
			}
		}
		RespUnionPayRefund output = this.handleFundRtxn(InnerRtxnTyp.UnionPayRefoundTrans, input,
				new ReqUnionPayRefund(input), RespUnionPayRefund.class);
		return output;
	}
	
	/**
	 * 内部发起的退货交易
	 */
	public RespSysHead innerRefoundTrans(InputFundData input) throws PeException {
		if (!StringUtil.isStringEmpty(input.getBizType())) {
			if (InnerRtxnTyp.UnionPayOtherPerBankPay.equals(input.getBizType())) {
				input.setBizType("000201");
			} else if (InnerRtxnTyp.UnionPayOtherEBankPay.equals(input.getBizType())) {
				input.setBizType("000202");
			} else if (InnerRtxnTyp.UnionPayQuickPay.equals(input.getBizType())) {
				input.setBizType("000301");
			}
		}
		RespUnionPayRefund output = this.handleFundRtxn(InnerRtxnTyp.UnionPayInnerRefoundTrans, input,
				new ReqUnionPayRefund(input), RespUnionPayRefund.class);
		if (output.isTimeout()) {
			this.formatException(output);
		} else if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isReceived()) {
		}
		return output;
	}

	/**
	 * 消费撤销交易
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead cancelTrans(InputFundData input) throws PeException {
		if (StringUtil.isStringEmpty(input.getBizType())) {
			if (CyberTypCd.OUR.equals(input.getCyberTypCd())) {
				input.setBizType("000201");
			} else if (CyberTypCd.OTH.equals(input.getCyberTypCd())) {
				input.setBizType("000202");
			} else {
				input.setBizType("000301");
			}
		}
		RespUnionPayCancel output = this.handleFundRtxn(InnerRtxnTyp.UnionPayCancelTrans, input,
				new ReqUnionPayCancel(input), RespUnionPayCancel.class);
		return output;
	}

	/**
	 * 他行快捷发短信
	 * 
	 * @param input
	 * @throws PeException
	 */
	public RespSysHead sendMessage(InputFundData input) throws PeException {
		return this.handleNonFundRtxn(new ReqSendMessage(input), RespSendMessage.class);
	}

	/**
	 * 退货交易状态查询
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryStateForRefoundTrans(InputFundData input, RespSysHead output) throws PeException {
		RespQueryRtxnState queryOutput = this.handleNonFundRtxn(new ReqQueryStateForRefoundTrans(input),
				RespQueryRtxnState.class);
		if (queryOutput.isSuccess()) {

		} else if (queryOutput.isFailure()) {
			this.formatException(queryOutput);
		} else if (queryOutput.isTimeout()) {
			this.formatException(queryOutput);
		} else if (queryOutput.isReceived()) {
			output.setReturncode(queryOutput.getOrigRespCode());
			output.setReturnmsg(queryOutput.getOrigRespMsg());
			output.setDownrtxnnbr(queryOutput.getQueryId());
			String a = queryOutput.getOrigRespCode();
			if ("00".equals(a) || "A6".equals(a)) {
				output.setRtxnstate(TransStatus.SUCCESS);
				this.updateInnerfundtrans(input, output);
			} else if ("03".equals(a) || "04".equals(a) || "05".equals(a) || "01".equals(a) || "12".equals(a)
					|| "60".equals(a)) {
				output.setRtxnstate(TransStatus.TIMEOUT);// 待发送
				this.updateInnerfundtrans(input, output);
				throw new PeException(DictErrors.TRANS_TIMEOUT);
			} else {
				output.setRtxnstate(TransStatus.FAILURE);
				this.updateInnerfundtrans(input, output);
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}
		return queryOutput;
	}

	/**
	 * 交易结果查询
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryRtxnForOtherEBank(InputFundData input) throws PeException {
		RespQueryRtxnState output = this.handleNonFundRtxn(new ReqQueryRtxnForOtherEBank(input),
				RespQueryRtxnState.class);
		if (output.isSuccess()) {

		} else if (output.isFailure()) {
			this.updateInnerfundtrans(input, output);
		} else if (output.isTimeout()) {

		} else if (output.isReceived()) {
			output.setReturncode(output.getOrigRespCode());
			output.setReturnmsg(output.getOrigRespMsg());
			output.setDownrtxnnbr(output.getQueryId());
			String a = output.getOrigRespCode();

			Overalltrans record = new Overalltrans();
			record.setOveralltransnbr(input.getOveralltransnbr());

			if ("00".equals(a) || "A6".equals(a)) {
				output.setRtxnstate(TransStatus.SUCCESS);
			} else if ("03".equals(a) || "04".equals(a) || "05".equals(a) || "01".equals(a) || "12".equals(a)
					|| "60".equals(a)) {
				output.setRtxnstate(TransStatus.TIMEOUT);// 待发送
				record.setOveralltransstatus(TransStatus.TIMEOUT);
			} else {
				output.setRtxnstate(TransStatus.FAILURE);// 待发送
				record.setOveralltransstatus(TransStatus.FAILURE);
				try {
					OveralltransDAO.updateByPrimaryKeySelective(record);
					this.updateInnerfundtrans(input, output);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
		return output;
	}

	/**
	 * 查询账户是否开通快捷支付
	 * 
	 * @param input
	 * @return
	 * @throws PeException
	 */
	public RespSysHead queryAcctOpenStatus(InputFundData input) throws PeException {
		RespQueryRtxnState output = this.handleNonFundRtxn(new ReqQueryAcctOpenStatus(input), RespQueryRtxnState.class);
		if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead queryDownPostDate(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead virAcctWithdrawl(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public RespSysHead withdrawal(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead recharge(InputFundData input) throws PeException {
		RespRecharge output = new RespRecharge();
		input.setTranscode("unionpay");
		insertInnerfundtrans(input);
		output.setRtxnstate("0");
		output.setReturnmsg("ok");
		output.setResult(RouterResult.SUCCESS);
		updateInnerfundtrans(input, output);	
		return output;
	}
}
