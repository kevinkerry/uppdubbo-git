package com.csii.upp.constant;

/**
 * 对账差错表BATCHCHECKERROR中processmode字段的含义
 * 
 * @author
 *
 */
public interface ProcessMode {

	public final String DEFAULT_VALUE = "1";
	/*
	 * 自动处理
	 */
	public final String PROCESS_BY_AUTO = "0";
	/*
	 * 支付平台人工处理
	 */
	public final String PROCESS_BY_MAN = "1";
	/*
	 * 清算平台手动处理
	 */
	public final String PROCESS_BY_QSPT = "2";

}
