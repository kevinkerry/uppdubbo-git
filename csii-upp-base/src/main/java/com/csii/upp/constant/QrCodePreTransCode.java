package com.csii.upp.constant;

public interface QrCodePreTransCode {
	/**
	 * 支付宝创建下单支付
	 */
	String AlipayCodeActivePay="8012";
	/**
	 * 支付宝被扫支付
	 */
	String AlipayCodePassivePay = "8002";
	/**
	 * 微信创建下单支付
	 */
	String WeChatCodeActivePay ="1008"; 
	/**
	 * 微信被扫支付
	 */
	String WeChatCodePassivePay  = "1002";
	/**
	 * 支付宝商户信息同步新增
	 */
	String AlipayCodeSynInfo  = "8003";
	/**
	 * 支付宝商户信息同步修改
	 */
	String AlipayCodeUpdInfo  = "8005";
	/**
	 * 微信商户信息同步新增
	 */
	String WeChatCodeSynInfo  = "5001";
	/**
	 * 微信商户信息同步修改
	 */
	String WeChatCodeUpdInfo  = "5002";
	/**
	 * 支付状态查询
	 */
	String QueryCodePayStatus  = "3001";
	/**
	 * 支付宝退货状态查询
	 */
	String QueryAlipayCodeRefStatus  = "8010";
	/**
	 * 微信退货状态查询
	 */
	String QueryWechatCodeRefStatus  = "2004";
	
	/**
	 * 支付宝退款
	 */
	String AlipayCodeActivePayRefund ="8009";
	
	/**
	 * 支付宝扫码支付撤消
	 */
	String AlipayCodeActivePayUndo = "8008";
	
	/**
	 * 微信退款
	 */
	String WeChatCodeActivePayRefund = "2003";
	
	/**
	 * 微信扫码支付撤消
	 */
	String WeChatCodeActivePayUndo = "2001";

}
