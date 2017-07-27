package com.csii.upp.constant;

public interface ConstCups {
	
	/**
	 * 账户关系验证与查询
	 */
	String VDAT="010033";
	/**
	 * 消费
	 */
	String CONS="020000";
	/**
	 * 消费撤消
	 */
	String CONC="020020";
	/**
	 * 消费退货
	 */
	String CONR="022020";
	/**
	 * 消费冲正
	 */
	String CONSREVK="042000";
	/**
	 * 消费撤消冲正
	 */
	String CONCREVK="042020";
	
	String FILE_TYPE = "cupsCheckFile";
	/**
	 * 收付费文件
	 */
	String FCP="FCP";
	/**
	 * 一般交易流水
	 */
	String ICOM="ICOM";
	/**
	 * 差错交易发卡方流水文件
	 */
	String IERR="IERR";
	/**
	 * 发卡方周期计费文件
	 */
	String IPED="IPED";
	/**
	 * 发卡方周期计费差错流水文件
	 */
	String IERRPED="IERRPED";
	/**
	 * 品牌服务费文件
	 */
	String ILFEE="ILFEE";
}
