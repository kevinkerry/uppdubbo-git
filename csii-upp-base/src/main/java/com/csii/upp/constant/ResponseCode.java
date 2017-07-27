package com.csii.upp.constant;

public interface ResponseCode {
	String SUCCESS = "0000000";
	/**
	 * 超时
	 */
	String TIMEOUT = "PAY8001";
	/**
	 * 失败
	 */
	String FAILURE = "PAY9999";
	/**
	 * 
	 */
	String EXITDATA ="PAY0053";
	/**
	 * 客户未签约
	 */
	String NOTEXIT ="PAY0046";
	/**
	 * 手机号和核心不匹配
	 */
	String PHONEANDCARDNOTMATCH="PAY0131";
	/**
	 * 核心无该借记卡
	 */
	String DEBITCARDNOTEXIST="PAY0123";
	/**
	 * 核心无该信用卡
	 */
	String CREDITCARDNOTEXIST="PAY0124";
	/**
	 * 该手机号没有签约为正常的银行卡
	 */
	String MOBILENOSIGN="PAY0084";
	/**
	 * 客户积分不足
	 */
	String POINTLESS="-10";
	/**
	 * 客户内码有误
	 */
	String CLIENTNOERROR="-7";
	/**
	 * 订单不存在
	 */
	String NOPOINTCONSUME="-8";
	/**
	 * 重复撤销
	 */
	String CANCELREPEAT="-122";
}

