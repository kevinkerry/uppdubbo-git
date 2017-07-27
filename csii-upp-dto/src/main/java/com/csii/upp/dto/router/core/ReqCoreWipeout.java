package com.csii.upp.dto.router.core;

import java.math.BigDecimal;
import java.util.Date;

import com.csii.upp.annotation.AttributeProperties;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.StringUtil;

/**
 * 老核心抹帐交易入参
 * 
 * @author 陈彦鹏
 *
 */
public class ReqCoreWipeout extends ReqCoreHead {
	public ReqCoreWipeout(InputFundData data) {
		super(data);
		setTransCode(CoreTransCode.CoreWipeout);
		this.setOrigproductdate(data.getOrigtransdate());
		this.setOrigproductseqn(data.getOriginnertransnbr());
		this.setOrigtranamt(data.getTransamt());
		this.setStrorigtranamt(StringUtil.BigDel2Str(getOrigtranamt()));
		this.setRemakrs("交易冲正");
	}

	private String origproductid;// 原业务系统代码
	@AttributeProperties(dateFormat = DateFormatCode.DATE_FORMATTER3)
	private Date origproductdate;// 原业务系统日期
	private String origproductseqn;// 原业务系统流水号
	private BigDecimal origtranamt; // 原交易金额
	private String strorigtranamt; // 原交易金额
	private String remakrs;// 备注

	public String getOrigproductid() {
		return origproductid;
	}

	public void setOrigproductid(String origproductid) {
		this.origproductid = origproductid;
	}

	public Date getOrigproductdate() {
		return origproductdate;
	}

	public void setOrigproductdate(Date origproductdate) {
		this.origproductdate = origproductdate;
	}

	public String getOrigproductseqn() {
		return origproductseqn;
	}

	public void setOrigproductseqn(String origproductseqn) {
		this.origproductseqn = origproductseqn;
	}

	public String getRemakrs() {
		return remakrs;
	}

	public void setRemakrs(String remakrs) {
		this.remakrs = remakrs;
	}

	public BigDecimal getOrigtranamt() {
		return origtranamt;
	}

	public void setOrigtranamt(BigDecimal origtranamt) {
		this.origtranamt = origtranamt;
	}

	public String getStrorigtranamt() {
		return strorigtranamt;
	}

	public void setStrorigtranamt(String strorigtranamt) {
		this.strorigtranamt = strorigtranamt;
	}

}
