/**
 * 
 */
package com.csii.upp.http;

import java.util.Map;

import com.csii.upp.idto.IDto;

/**
 * @author lixinyou
 *	Http请求实现类，封装http请求的报文
 */
public class HttpRequest implements IDto{
	private String contentType;
	private byte[] data;
	private String url;
	private Map<String, Object> requestMap;	//新增requestMap用于放财政网请求数据
	
	public HttpRequest(String contentType, byte[] data, String url, Map<String, Object> requestMap) {
		this.contentType = contentType;
		this.data = data;
		this.url = url;
		this.requestMap = requestMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte [] getData() {
		return data;
	}

	public void setData(byte [] data) {
		this.data = data;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}
}
