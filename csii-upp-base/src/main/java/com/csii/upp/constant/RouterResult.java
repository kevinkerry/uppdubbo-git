package com.csii.upp.constant;

/**
 * 交易结果状态：成功，失败，收到（异步处理），超时
 * 
 * @author 徐锦
 *
 */
public enum RouterResult {
	SUCCESS, FAILURE, RECEIVED, TIMEOUT,PENDING
}
