package com.csii.upp.constant;

public interface ProcStep {
	/**
	 * 未对账
	 */
	String Init = "0";
	/**
	 * 对账成功
	 */
	String CHECKED = "1";
	/**
	 * 对账失败
	 */
	String UNCHECKED = "2";
	/**
	 * 清算成功
	 */
	String CLEARED = "3";
	/**
	 * 清算失败
	 */
	String UNCLEARED = "4";
	/**
	 * 结算成功
	 */
	String SETTLED = "5";
	/**
	 * 结算失败
	 */
	String UNSETTLED = "6";
	/**
	 * 待对账
	 */
	String PRECHECK = "9";
}
