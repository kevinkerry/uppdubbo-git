package com.csii.upp.fundprocess.action;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.unionpay.RespDsjyyb;
import com.csii.upp.service.fundprocess.AliPayService;
import com.csii.upp.service.fundprocess.AutoRouterService;
import com.csii.upp.service.fundprocess.BepsService;
import com.csii.upp.service.fundprocess.Bill99Service;
import com.csii.upp.service.fundprocess.Cnaps2Service;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.service.fundprocess.EAccountService;
import com.csii.upp.service.fundprocess.HvpsService;
import com.csii.upp.service.fundprocess.IbpsService;
import com.csii.upp.service.fundprocess.PaymService;
import com.csii.upp.service.fundprocess.PbcService;
import com.csii.upp.service.fundprocess.PointService;
import com.csii.upp.service.fundprocess.UnionPayService;
import com.csii.upp.service.fundprocess.WechatService;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.Helper;
import com.csii.upp.util.StringUtil;

/**
 * 交易处理基类
 * 
 * @author 徐锦
 * 
 */
public abstract class PayOnlineAction extends BaseAction {
	
	
	protected CreditRouter getCreditRouter(InputFundData input) throws PeException {
		/*
		 * 路由得到贷方service 规则如下： 实时贷记 本行、超级网银 （<5w） 普通贷记： 大额、本行、同城 ( >5w) 本行判断
		 * 通过卡bin bank表存放总行级别信息 前段应该有个表存放支行详细信息
		 */
		getAutoRouterService().autoChannelRouter(input,
				Helper.formatCardBin(input.getPayeeacctnbr()),input.getPayeeacctkind());
		//得到科目账户外部识别号
		input.setCheckdataflag(input.getFundchannelcode());
		CreditRouter routerService = (CreditRouter) getService(input
				.getFundchannelcode().toLowerCase());
		return routerService;
	}

	protected DebitRouter getDebitRouter(InputFundData input) throws PeException {
		getAutoRouterService().autoChannelRouter(input,
				Helper.formatCardBin(input.getPayeracctnbr()),input.getPayeracctkind());
		//得到科目账户外部识别号
//		if(input.getFundchannelcode().equals(FundChannelCode.ALIPAYCODE)
//				||input.getFundchannelcode().equals(FundChannelCode.WECHATCODE)){
//			input.setCheckdataflag(FundChannelCode.QRCODE);
//		}else{
			input.setCheckdataflag(input.getFundchannelcode());
//		}
		DebitRouter routerService = (DebitRouter) getService(input
				.getFundchannelcode().toLowerCase());
		return routerService;
	}

	protected QueryRouter getQueryRouter(InputFundData input) {
		// TODO 演示用，应该类似getCreditRouter、getDebitRouter，根据自动路由规则获取实际路由
		return (QueryRouter) getCoreService();
	}

	protected EAccountService getDBankService(InputFundData input) {
		if(StringUtil.isStringEmpty(input.getCheckdataflag())){
			//得到科目账户外部识别号
			input.setCheckdataflag(input.getUppersysnbr());
		}
		String fundChannelCd = FundChannelCode.EACCOUNT;
		input.setFundchannelcode(fundChannelCd);
		return (EAccountService) getService(fundChannelCd.toLowerCase());
	}


	protected AutoRouterService getAutoRouterService() {
		return (AutoRouterService) getService("router");
	}

	protected CoreService getCoreService() {
		return (CoreService) getService(FundChannelCode.CORE.toLowerCase());
	}

	protected Bill99Service getBill99Service() {
		return (Bill99Service) getService(FundChannelCode.BILL99.toLowerCase());
	}

	protected Cnaps2Service getCnaps2Service() {
		return (Cnaps2Service) getService(FundChannelCode.CNAPS2.toLowerCase());
	}

	protected EAccountService getEAccountService() {
		return (EAccountService) getService(FundChannelCode.EACCOUNT
				.toLowerCase());
	}

	protected PbcService getPbcService() {
		return (PbcService) getService(FundChannelCode.PBC.toLowerCase());
	}
	
	protected BepsService getBepsService() {
		return (BepsService) getService(FundChannelCode.BEPS.toLowerCase());
	}

	protected Date getPostDate() {
		return SysinfoExtendDAO.getSysInfo().getPostdate();
	}

	protected IbpsService getIbpsService() {
		return (IbpsService) getService(FundChannelCode.IBPS.toLowerCase());
	}

	protected HvpsService getHvpsService() {
		return (HvpsService) getService(FundChannelCode.HVPS.toLowerCase());
	}

	protected UnionPayService getUnionPayService() {
		return (UnionPayService) getService(FundChannelCode.UNIONPAY
				.toLowerCase());
	}
	protected PointService getPointService() {
		return (PointService) getService(FundChannelCode.JFWG
				.toLowerCase());
	}
	protected PaymService getPaymService(){
		return (PaymService) getService(FundChannelCode.PAYM.toLowerCase());
	}
	protected AliPayService getAliPayService() {
		return (AliPayService) getService(FundChannelCode.ALIPAYCODE
				.toLowerCase());
	}
	protected WechatService getWechatService() {
		return (WechatService) getService(FundChannelCode.WECHATCODE
				.toLowerCase());
	}
	protected RespDsjyyb buildRespOjb(Context ctx) {
		RespDsjyyb obj = new RespDsjyyb();
		obj.setVersion(ctx.getString("version"));
		obj.setEncoding(ctx.getString("encoding"));
		obj.setCertId(ctx.getString("certId"));
		obj.setSignMethod(ctx.getString("signMethod"));
		obj.setTxnType(ctx.getString("txnType"));
		obj.setTxnSubType(ctx.getString("txnSubType"));
		obj.setBizType(ctx.getString("bizType"));
		obj.setAccessType(ctx.getString("accessType"));
		obj.setMerId(ctx.getString("merId"));
		obj.setOrderId(ctx.getString("orderId"));
		obj.setCurrencyCode(ctx.getString("currencyCode"));
		obj.setTxnAmt(new BigDecimal(ctx.getString("txnAmt")));
		obj.setTxnTime(ctx.getString("txnTime"));
		obj.setPayType(ctx.getString("payType"));
		obj.setAccNo(ctx.getString("accNo"));
		obj.setPayCardType(ctx.getString("payCardType"));
		obj.setQueryId(ctx.getString("queryId"));
		obj.setTraceNo(ctx.getString("traceNo"));
		obj.setTraceTime(ctx.getString("traceTime"));
		obj.setSettleDate(DateUtil.getYear() + ctx.getString("settleDate"));
		obj.setSettleCurrencyCode(ctx.getString("settleCurrencyCode"));
		obj.setSettleAmt(ctx.getString("settleAmt"));
		obj.setRespCode(ctx.getString("respCode"));
		obj.setRespMsg(ctx.getString("respMsg"));
		obj.setOrigQryId(ctx.getString("origQryId"));
		return obj;
	}
}
