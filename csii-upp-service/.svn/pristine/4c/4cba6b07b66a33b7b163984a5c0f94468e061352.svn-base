package com.csii.upp.service.exception.fundction;

import com.csii.upp.constant.FundChannelCode;



public enum RtxnExceptionFunction {
	EAccountQueryRtxnState(FundChannelCode.EACCOUNT,"queryRtxnState","贷记交易电子账户出金超时做查询"),
	CoreQueryRtxnState(FundChannelCode.CORE,"queryRtxnState","贷记交易老核心入金超时做查询"),
	DpcQueryRtxnState(FundChannelCode.DPC,"queryRtxnState","大同城交易失败时查询"),
	HvpsQueryRtxnState(FundChannelCode.HVPS,"queryRtxnState","大额交易失败时查询"),
	IbpsQueryRtxnState(FundChannelCode.IBPS, "queryRtxnState","贷记交易超级网银入金超时做查询"),
	CoreQueryTransStateForPay(FundChannelCode.CORE,"queryTransStateForPay","统一转账超时查询"),//统一转账超时查询
	CoreQueryTransStateForUnionPay(FundChannelCode.CORE,"queryTransStateForUnionPay","统一转账超时查询"),//统一转账超时查询
	HvpsQueryTransStateForPay(FundChannelCode.HVPS,"queryTransStateForPay","统一转账超时查询"),
	BepsQueryTransStateForPay(FundChannelCode.BEPS,"queryTransStateForPay","统一转账超时查询"),
	Cnaps2QueryRtxnState(FundChannelCode.CNAPS2,"queryRtxnState","贷记交易二代入金超时做查询"),
	EAccounteAccountRecharge(FundChannelCode.EACCOUNT,"eAccountRecharge","冲正电子账户"),
    CorequeryRtxnStateForDebit(FundChannelCode.CORE,"queryRtxnStateForDebit","借记老核心渠道交易超时"), //借记交易老核心渠道交易超时调用方法  
    Bill99queryRtxnStateForDebit(FundChannelCode.BILL99,"queryRtxnStateForDebit","借记快钱渠道交易超时"),//借记交易快钱渠道交易超时调用方法  
    EAccountqueryRtxnForDebit(FundChannelCode.EACCOUNT,"queryRtxnForDebit","借记电子账户核心超时"),//借记交易电子账户核心超时调用方法  EAccountService
    EAccountdodepositOnCreditAcct(FundChannelCode.EACCOUNT,"dodepositOnCreditAcct","借记电子账户核心失败"),//借记交易电子账户核心失败调用方法 
    EAccountdopostSuspendRtxn(FundChannelCode.EACCOUNT,"dopostSuspendRtxn","挂账成功后挂入"),//挂账成功后挂入
	Bill99RtdtdrReTrave(FundChannelCode.BILL99,"rtdtdrReTrave","快钱退回"),//dr快钱退回
	CoreRtdtdrReTrave(FundChannelCode.CORE,"rtdtdrReTrave","核心退回"),//dr快钱退回
	EAccountRtdtdrReTrave(FundChannelCode.EACCOUNT,"rtdtdrReTrave","快钱退回"),//dr快钱退回
	EAccountQueryRtxnSateForReTrave(FundChannelCode.EACCOUNT,"queryRtxnSateForReTrave","cr电子账户撤销超时"),//做电子账户撤销交易超时(电子账户渠道)
	Bill99queryRtxnStateForReTrave(FundChannelCode.BILL99,"queryRtxnStateForReTrave","快钱渠道做dr超时"),
	CorequeryRtxnStateForReTrave(FundChannelCode.CORE,"queryRtxnStateForReTrave","老核心渠道做dr超时"),
	EAccountqueryRtxnStateForReTrave(FundChannelCode.EACCOUNT,"queryRtxnStateForReTrave","电子账户渠道做dr超时"),
	CnapS2ReturnRemittance(FundChannelCode.CNAPS2,"cnaps2ReturnRemittance","挂入失败后退汇"),
	UnionPayPayForAnother(FundChannelCode.UNIONPAY,"payForAnother","人行代收入账失败"),
	EAccountrtdtcrForPbcRtxn(FundChannelCode.EACCOUNT,"rtdtcrForPbc","人行代收入账超时"),
	UnionPayQueryStateForRefoundTrans(FundChannelCode.UNIONPAY,"queryStateForRefoundTrans","退货交易失败查询"),
	UnionPayqueryRtxnStateForDebit(FundChannelCode.UNIONPAY,"queryRtxnStateForDebit","银联代收交易失败时查询"),
	UnionPayqueryRtxnStateForCredit(FundChannelCode.UNIONPAY,"queryRtxnStateForForCredit","银联代付交易失败时查询"),
	HvpsReTrave(FundChannelCode.HVPS, "STHPDrReTrave", "单笔记账失败大额退回"),
	BepsReTrave(FundChannelCode.BEPS, "STHPDrReTrave", "单笔记账失败小额退回"),
	IbpsReTrave(FundChannelCode.IBPS, "STIBDrReTrave", "单笔记账失败超网退回"),
	DpcReTrave(FundChannelCode.DPC, "STDPDrReTrave", "单笔记账失败同城退回"),
	EAccountdoSuspendRVandReTrave(FundChannelCode.EACCOUNT, "doSuspendRVandReTrave", "挂入失败后电子账户解挂二代退汇(二代来账)"),
	FDPSHandleTransTimeOut(FundChannelCode.FDPS, "handleTransTimeOut", "PAYMENT交易超时做查询并修改交易状态"),
	SettleMerchant(FundChannelCode.CORE, "settleMerchant", "商户结算"),
    CoreExWipeoutTimeOut(FundChannelCode.CORE,"CoreExWipeoutTimeOut", "核心异常账务处理超时"),
	JFWGConsumeTransTimeOut(FundChannelCode.JFWG,"JFWGConsumeTransTimeOut", "积分网关消费处理超时"),
	JFWGCancelTransTimeOut(FundChannelCode.JFWG,"JFWGCancelTransTimeOut", "积分网关撤销处理超时"),
	CoreIntegalTransTimeOut(FundChannelCode.CORE,"CoreIntegalTransTimeOut", "核心积分抵扣账务处理超时"),
	AlipayQrcodeTransTimeOut(FundChannelCode.ALIPAYCODE,"alipayQrcodeTransTimeOut", "二维码前置支付宝账务处理超时"),
	WeChatQrcodeTransTimeOut(FundChannelCode.WECHATCODE,"weChatQrcodeTransTimeOut", "二维码前置微信账务处理超时"),
	AlipayQrcodeRefoundTimeOut(FundChannelCode.ALIPAYCODE,"alipayQrcodeRefoundTimeOut", "二维码前置支付宝账务处理超时"),
	WeChatQrcodeRefoundTimeOut(FundChannelCode.WECHATCODE,"weChatQrcodeRefoundTimeOut", "二维码前置微信账务处理超时");
	private final String fundChanelCd;
	private final String rtxnCode;
	private final String rtxnDesc;
	
	private RtxnExceptionFunction(String fundChanelCd,String rtxnCode,String rtxnDesc){ 
		this.fundChanelCd=fundChanelCd;
		this.rtxnCode = rtxnCode;
		this.rtxnDesc = rtxnDesc;
	}

	public String getFundChanelCd() {
		return fundChanelCd;
	}

	public String getRtxnCode() {
		return rtxnCode;
	}

	public String getRtxnDesc() {
		return rtxnDesc;
	}
}
