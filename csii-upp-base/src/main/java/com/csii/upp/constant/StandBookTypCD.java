package com.csii.upp.constant;

/*
 *  台账类型
 */
public interface StandBookTypCD {
	/**
	 * 支付结算（银行->商户）
	 */
	String MERCHANT_TRANS_PAY = "00";
	/**
	 * 退货返还（商户->银行）
	 */
	String MERCHANT_TRANS_WITHDRAW = "01";
	/**
	 * 包月手续费收取（商户->银行）
	 */
	String MERCHANT_TRANS_FEE_RCV = "02";
	/**
	 * 手续费返还（银行->商户）
	 */
	String MERCHANT_TRANS_FEE_RETURN = "03";
	/**
	 * 提现结算（商户->银行）
	 */
	String MERCHANT_TRANS_CASH = "04";
	/**
	 * 差额手续费收取（内部账户->内部手续费账户）
	 */
	String MERCHANT_TRANS_BALANCE_FEE_RCV = "05";
	/**
	 * 未结算退货手续费退回（手续费账户->内部账户）
	 */
	String MERCHANT_TRANS_BALANCE_FEE_RETURN = "06";
	/**
	 * 全额结算手续费收取（商户->银行）
	 */
	String MERCHANT_TRANS_FEE_DIRT_RCV = "07";	
	/**
	 * 银联汇总轧差金额
	 */
	String UNIONPAY_TOTAL_OFFSET_BALANCE = "08";
	/**
	 * 快钱-手续费支出台账
	 */
	String Bill_Fee_Acct = "11";
	/**
	 * 快钱-支付台账
	 */
	String Bill_Pay_Acct = "12";
	/**
	 * 快钱-退款台账 
	 */
	String Bill_Retrave_acct = "13";
	
	/**
	 * 银联-手续费支出台账
	 */
	String UnionPay_Fee_Acct = "11";
}
