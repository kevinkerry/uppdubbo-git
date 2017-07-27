/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-dto
 * create: 2016年2月16日 下午3:27:21
 * vc: $Id: $
 */
package com.csii.upp.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO 请填写注释.
 * @author chen chao 
 * @version $Revision:$
 */
public class PaymIssueFile {
	/**
	 * 交易代码
	 */
	private String transcode;
	/**
	 * 清算日期
	 */
	private Date cleardate;
	/**
	 * 商户时间戳
	 */
	private Date mertransdatetime;
	/**
	 * 商户交易流水号
	 */
	private String merseqnbr;
	/**
	 * 支付平台流水号
	 */
	private String transseqnbr;
	/**
	 * 商户号
	 */
	private String mernbr;
	/**
	 * 交易金额
	 */
	private BigDecimal transamt;
	/**
	 * 手续费
	 */
	private BigDecimal feeamt;
	/**
	 * 交易状态
	 */
	private String transstatus;
	/**
	 * 备注1
	 */
	private String memo1;
	/**
	 * 备注2
	 */
	private String memo2;
	/**
	 * @return the transcode
	 */
	public String getTranscode() {
		return transcode;
	}
	/**
	 * @param transcode the transcode to set
	 */
	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}
	/**
	 * @return the cleardate
	 */
	public Date getCleardate() {
		return cleardate;
	}
	/**
	 * @param cleardate the cleardate to set
	 */
	public void setCleardate(Date cleardate) {
		this.cleardate = cleardate;
	}
	/**
	 * @return the mertransdatetime
	 */
	public Date getMertransdatetime() {
		return mertransdatetime;
	}
	/**
	 * @param mertransdatetime the mertransdatetime to set
	 */
	public void setMertransdatetime(Date mertransdatetime) {
		this.mertransdatetime = mertransdatetime;
	}
	/**
	 * @return the merseqnbr
	 */
	public String getMerseqnbr() {
		return merseqnbr;
	}
	/**
	 * @param merseqnbr the merseqnbr to set
	 */
	public void setMerseqnbr(String merseqnbr) {
		this.merseqnbr = merseqnbr;
	}
	/**
	 * @return the transseqnbr
	 */
	public String getTransseqnbr() {
		return transseqnbr;
	}
	/**
	 * @param transseqnbr the transseqnbr to set
	 */
	public void setTransseqnbr(String transseqnbr) {
		this.transseqnbr = transseqnbr;
	}
	/**
	 * @return the mernbr
	 */
	public String getMernbr() {
		return mernbr;
	}
	/**
	 * @param mernbr the mernbr to set
	 */
	public void setMernbr(String mernbr) {
		this.mernbr = mernbr;
	}
	/**
	 * @return the transamt
	 */
	public BigDecimal getTransamt() {
		return transamt;
	}
	/**
	 * @param transamt the transamt to set
	 */
	public void setTransamt(BigDecimal transamt) {
		this.transamt = transamt;
	}
	/**
	 * @return the feeamt
	 */
	public BigDecimal getFeeamt() {
		return feeamt;
	}
	/**
	 * @param feeamt the feeamt to set
	 */
	public void setFeeamt(BigDecimal feeamt) {
		this.feeamt = feeamt;
	}
	/**
	 * @return the transstatus
	 */
	public String getTransstatus() {
		return transstatus;
	}
	/**
	 * @param transstatus the transstatus to set
	 */
	public void setTransstatus(String transstatus) {
		this.transstatus = transstatus;
	}
	/**
	 * @return the memo1
	 */
	public String getMemo1() {
		return memo1;
	}
	/**
	 * @param memo1 the memo1 to set
	 */
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	/**
	 * @return the memo2
	 */
	public String getMemo2() {
		return memo2;
	}
	/**
	 * @param memo2 the memo2 to set
	 */
	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}
	
}
