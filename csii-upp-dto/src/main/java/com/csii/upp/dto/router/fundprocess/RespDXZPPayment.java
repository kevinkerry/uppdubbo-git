/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;


/**
 * @author tongzongbing
 * 发往电信诈骗的响应类
 *
 */
public class RespDXZPPayment extends RespFundProHead {
	private String inNumState;
	private String outNumState;
	
	public String getInNumState() {
		return inNumState;
	}
	public void setInNumState(String inNumState) {
		this.inNumState = inNumState;
	}
	public String getOutNumState() {
		return outNumState;
	}
	public void setOutNumState(String outNumState) {
		this.outNumState = outNumState;
	}



	
}
