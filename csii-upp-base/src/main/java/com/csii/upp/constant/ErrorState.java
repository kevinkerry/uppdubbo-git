package com.csii.upp.constant;

public interface ErrorState {
	
	/**
	 * 处理中
	 */
	String HANDLING = "0";
	/**
	 * 处理成功
	 */
	String SUCCESS = "1";
	/**
	 * 处理失败
	 */
	String FAILURE = "2";

	/**
	 * 待处理
	 */
	String PRECHECK = "3";
}
