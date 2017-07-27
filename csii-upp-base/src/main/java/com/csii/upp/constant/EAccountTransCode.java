package com.csii.upp.constant;

public interface EAccountTransCode {
	/**
	 * 单笔借记
	 */
	
	String AcctInnerWithdrawal="AcctInnerWithdrawal";
    String AcctRecharge = "AcctRecharge";
    String DepositOnCreditAcct = "DepositOnCreditAcct";
    String DoPostSuspendRtxn="PostSuspendRtxn";
    String QueryEcAcctTxn="QueryEcAcctTxn";
	// 电子账户信息查询
	String QueryDirectAcctInfo = "queryDirectAcctInfo";
	String ReturnRemittance="ReturnRemittance"; //挂退
	String AcctFundPurchases = "AcctFundPurchases";
	String acctFundRedemption = "acctFundRedemption";
	String QueryPerson="QueryPerson";
	String CreditLoanQueryLimitAmt="CreditLoanQueryLimitAmt";
	String AcctCCQueryLimitAmt="AcctCCQueryLimitAmt";
	String PwdValid="PwdValid";
	
	String DepRtxnReversal="DepRtxnReversal";
	
	/**
	 * 电子账户抹账
	 */
	String RtxnErrorCorrect = "RtxnErrorCorrect";
	/**
	 * 内部户转出
	 */
	String AcctItlWithdraw="AcctItlWithdraw";
	/**
	 * 内部户转入
	 */
	String AcctItlDep="AcctItlDep";
	/**
	 * 电子账户出金——消费
	 */
	String AcctOuterWithdrawal="AcctOuterWithdrawal";
	/**
	 * 电子账户出金——缴费
	 */
	String AcctPayment="acctPayment";
	/**
	 * 小米付
	 */
	String CreditLoanConsume="CreditLoanConsume";
	/**
	 * 小米付
	 */
	String CCConsume="CCConsume";
	/**
	 * 电子账户入金——缴费退款
	 */
	String AcctRefundment="acctRefundment";
	/**
	 * 银联应收
	 */
	String ItlRefundment="itlRefundment";
	/**
	 * 银联应付
	 */
	String ItlPayment="itlPayment";
}
