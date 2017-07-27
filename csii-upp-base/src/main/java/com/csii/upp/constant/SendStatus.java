package com.csii.upp.constant;
/**
 * 短信发送状态
 * @author FGQ
 *
 */
public interface SendStatus {
	/**
	 * 发送成功
	 */
	String SUCCESS = "0";
	/**
	 * 发送失败
	 */
	String FAILURE = "1";
	/**
	 * 过期已失效
	 */
	String DISABLED = "2";
	/**
	 * 未发送
	 */
	String UNSEND = "9";
}
