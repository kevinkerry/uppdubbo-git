package com.csii.upp.constant;

public interface PayStatus {
	/**
	 * 订单等待支付
	 */
    String PAY_STATUS_NO = "0";
    /**
     * 订单处理成功
     */
    String PAY_STATUS_OK = "1";
    /**
     * 订单处理失败
     */
    String PAY_STATUS_FAIL = "2";
    /**
     * 订单处理中
     */
    String PAY_STATUS_HAND = "3";
    /**
     * 订单取消
     */
    String PAY_STATUS_CANCEL = "4";
    
    /**
     * 订单已受理
     */
    String PAY_STATUS_RECEIVED = "5";
    /**
     * 订单待扫码
     */
    String PAY_STATUS_PENDING = "6";
}
