/**
 * 
 */
package com.csii.upp.http;

import com.csii.upp.idto.IDto;

/**
 * @author lixinyou
 * http请求相应类
 */
public class HttpResponse implements IDto {
	private int resultStatus;
	private byte[] data;
	
	public HttpResponse(int resultStatus, byte[] data){
		this.resultStatus = resultStatus;
		this.data = data;
	}
	
	public int getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
