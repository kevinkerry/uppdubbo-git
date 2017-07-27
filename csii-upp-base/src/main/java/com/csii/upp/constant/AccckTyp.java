package com.csii.upp.constant;

/**
 * 调账方式
 */
public interface AccckTyp {

	/*
	 * 0-支付平台处理差错
	 */
	final String HandByZF = "0";
	
	/*
	 * 1-冲电子账户账
	 */
	final String ReverseEAccount = "1";
	
	/*
	 * 2-补电子账户账 
	 */
	final String AddEAccount = "2";
	
	/*
	 * 3-仅调整差错状态
	 */
	final String ModifyStatus = "3";
	
	/*
	 * 4-抹柜面核心账 
	 */
	final String ReverseCore = "4";
	
	/*
	 * 5-补柜面核心账
	 */
	final String AddCore = "5";
	
	/*
	 * 6-未归类差错
	 */
	final String UnCatError = "99";
}
