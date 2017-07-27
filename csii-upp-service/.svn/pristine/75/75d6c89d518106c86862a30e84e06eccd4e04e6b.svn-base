/**
 * 
 */
package com.csii.upp.service.payment;

import com.csii.pe.core.PeException;
import com.csii.pe.service.comm.CommunicationException;
import com.csii.upp.http.HttpRequest;
import com.csii.upp.http.HttpResponse;
import com.csii.upp.service.BasePayService;

/**
 * @author lixinyou
 * 用于商户的服务
 *
 */
public class NotifyService extends BasePayService{
	/**
	 * http请求发送
	 * @param requset
	 * @return
	 * @throws PeException
	 */
	public HttpResponse httpsend(HttpRequest httpRequest) throws CommunicationException{
		HttpResponse httpResponse = (HttpResponse) this.getTransport().submit(httpRequest);
		return httpResponse;
	}
}
