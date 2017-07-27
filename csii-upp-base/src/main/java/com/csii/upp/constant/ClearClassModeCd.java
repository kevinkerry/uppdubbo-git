package com.csii.upp.constant;
/**
 * 清分方式
 * @author XUJIN
 *
 */
public interface ClearClassModeCd {
	/**
	 * 直接清分（清分一级商户）
	 */
	String DIRT="0";
	/**
	 * 一口清分（清分二级商户）
	 */
	String BITE="1";
	/**
	 * 递进清分（先清分一级再清分二级商户）
	 */
	String STEP="2";
}
