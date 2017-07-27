package com.csii.upp.constant;

public interface CardPwdFlag {
	
	/**
	 * 0-不检查密码
	 */
	String NOTCHECKPWD="0";
	
	/**
	 * 1-检查卡片级查询密码
	 */
	String CHECKCARDPWD="1";
	
	/**
	 * 检查取款密码
	 */
	String CHECKWITHDRAWALPWD="2";
	
	/**
	 * 检查客户级查询密码
	 */
	String CHECKCUSTOMERPWD="3";
}
