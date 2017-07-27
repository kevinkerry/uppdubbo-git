package com.csii.upp.transport.restful.ws;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.csii.pe.service.comm.CommunicationException;
import com.csii.pe.service.comm.Transport;
import com.sun.jersey.api.client.WebResource;

public class RestWebServiceTransport implements Transport {
	
	private Log log = LogFactory.getLog(RestWebServiceTransport.class);
	private String prefixUrl;
	private RestClientImpl restClient;

	@Override
	public Object submit(Object obj) throws CommunicationException {
		Map inputData = (Map) obj;
		String restUrl = prefixUrl + inputData.get("suffixUrl").toString();
		log.info("请求积分系统,Restful URL:" + restUrl);
		inputData.put("restUrl", restUrl);
		return send(inputData);
	}
	
	private Object send(Map paramMap) throws CommunicationException{
		String restUrl = paramMap.get("restUrl").toString();
		WebResource resource = restClient.getClient().resource(restUrl);
		String respJson = resource.accept(MediaType.APPLICATION_JSON).get(String.class);
		log.debug("响应的json报文:"  + respJson);
		if(respJson == null){
			throw new CommunicationException("response is null, server not Unarrivable!");
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> respMap = (Map<String, Object>) JSON.parse(respJson);
		Integer result = (Integer) respMap.get("responseResult");
		respMap.put("responseResult", result.toString());
		return respMap;
	}
	
	public String getPrefixUrl() {
		return prefixUrl;
	}

	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
	}

	public RestClientImpl getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClientImpl restClient) {
		this.restClient = restClient;
	}
	
}
