package com.csii.upp.dto.extend;
/**
 * 计算商户手续费输出参数
 * @author xujin
 *
 */
public class FeeOutput {
	private String feeNbr;
	private String superFeeNbr;
	public String getFeeNbr() {
		return feeNbr;
	}
	public void setFeeNbr(String feeNbr) {
		this.feeNbr = feeNbr;
	}
	public String getSuperFeeNbr() {
		return superFeeNbr;
	}
	public void setSuperFeeNbr(String superFeeNbr) {
		this.superFeeNbr = superFeeNbr;
	}
}
