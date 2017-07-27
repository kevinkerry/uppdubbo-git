package com.csii.upp.constant;
/**
 * 订单状态
 * @author 徐锦
 *
 */
public interface OrderInfoStatus {
	/**
	 * 待支付
	 */
	String PAY_STATUS_NO="0";
	/**
	 * 处理成功
	 */
	String PAY_STATUS_OK="1";
	/**
	 * 失败
	 */
	String PAY_STATUS_FAIL="2";
	/**
	 * 订单处理中
	 */
	String PAY_STATUS_HAND="3";
	/**
	 * 订单取消
	 */
	String PAY_STATUS_CANCEL="4";
	
	
}
