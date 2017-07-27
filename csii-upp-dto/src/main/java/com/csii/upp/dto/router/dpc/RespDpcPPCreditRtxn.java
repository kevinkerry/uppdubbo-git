/**
 * 
 */
package com.csii.upp.dto.router.dpc;

import com.csii.upp.dto.router.core.RespGeneralCharge;

/**
 * @author DreamsHunter
 *
 */
public class RespDpcPPCreditRtxn extends RespGeneralCharge {
	private String refno; // 主机流水号

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}
}
