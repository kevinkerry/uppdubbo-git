/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;


/**
 * @author wy
 * 发往fundprocess
 *
 */
public class RespQueryQrCodeUrl extends RespFundProHead {
	
	private String codeUrl;

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
}
