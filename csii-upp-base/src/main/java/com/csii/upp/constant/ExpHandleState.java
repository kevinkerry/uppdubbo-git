package com.csii.upp.constant;

public interface ExpHandleState {
	/**
	 * 未处理
	 */
	String PREHANDLE = "0";
	/**
	 * 处理成功
	 */
	String SUCCESS = "1";
	/**
	 * 处理失败
	 */
	String FAILURE = "2";
	/**
	 * 处理重复次数超限
	 */
	String TIMESOVER = "3";
	/**
	 * 处理中
	 */
	String HANDLING = "4";
}
