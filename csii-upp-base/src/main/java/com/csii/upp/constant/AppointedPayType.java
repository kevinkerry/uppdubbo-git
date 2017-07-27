package com.csii.upp.constant;
/**
 * 支付方式
 * @author fansonggu@csii.com.cn
 *
 */
public interface AppointedPayType {
	/**
	 * 以支付平台默认支付方式为准
	 */
	String appointedPayTypeM = "0";
	/**
	 * 丰收E支付为指定方式
	 */
	String appointedPayTypeF = "1";
	/**
	 * 银行卡支付为指定方式
	 */
	String appointedPayTypeC = "2";
}
