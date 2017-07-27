package com.csii.upp.constant;

public interface ConstUnionPay {
	String STATUS_SUCCESS = "00";
	/**
	 * 代收商户号
	 */
//	String PAY_MER_ID="449161060120001";
	String PAY_MER_ID="821420160120006";
	/**
	 * 代付商户号
	 */
	String COLLECTION_MER_ID="449161060120002";
	
	String FILE_TYPE = "unionpayCheckFile";
	
	/**
	 * 消费撤消
	 */
	String CONC="31";
	/**
	 * 消费退货
	 */
	String CONR="04";
	/**
	 * 代收商户号
	 */
	String PAY_IN="PayIn";
	/**
	 * 代付商户号
	 */
	String PAY_OUT="PayOut";

}
