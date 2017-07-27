package com.csii.upp.constant;

/**
 * @author lixinyou
 *
 */
public interface OveralltransTyp {
	/**
	 * 实时借记
	 */
	String RTDT = "RTDT";
	/**
	 * 实时贷记
	 */
	String RTCT = "RTCT";
	/**
	 * 普通贷记
	 */
	String SPCT = "SPCT";
	/**
	 * 普通单笔记账
	 */
	String STXN = "STXN";
	
	/**
	 * 撤销
	 */
	String REVK = "REVK";
	
	/**
	 * 超级网银来帐
	 */
	String STXNIBPS = "STIB";
	/**
	 *大额来账入账
	 */
	String STXNHVPS = "STHP";
	/**
	 * 小额来账入账
	 */
	String STXNBEPS = "STBE";
	/**
	 * 同城来账入账
	 */
	String STXNDPC = "STDP";
	
	/**
	 * 人行代收前半部分
	 */
	String PbcCollectionBefore = "PbcCollectionBefore";
	
	/**
	 * 人行代收后半部分
	 */
	String PbcCollectionAfter = "PbcCollectionAfter";
	/**
	 * 基金申购
	 */
	String FPUR = "FPUR";
	/**
	 * 基金赎回
	 */
	String FRED = "FRED";
	/**
	 * 消费
	 */
	String CONS = "CONS";
	/**
	 * 消费撤销
	 */
	String CONC = "CONC";
	/**
	 * 消费退货
	 */
	String CONR = "CONR";
	
	
	
	//支付类总交易类型
	/**
	 * 统一支付
	 */
	String UPAY = "UPAY";
	/**
	 * 退货交易
	 */
	String PAYR = "PAYR";
	/**
	 * 虚账户转账
	 */
	String VATF = "VATF";
	/**
	 * 他行网银支付交易
	 */
	String OEBP = "OEBP";
	/**
	 * 确认虚账户支付
	 */
	String CVAP="CVAP";
	
	/**
	 * 批量转账
	 */
	String BTTF="BTTF";
	/**
	 * 虚账户退货
	 */
	String VTRT ="VTRT";
	/**
	 * 商户结算
	 */
	String MERSETTLE = "MERSETTLE";
}
