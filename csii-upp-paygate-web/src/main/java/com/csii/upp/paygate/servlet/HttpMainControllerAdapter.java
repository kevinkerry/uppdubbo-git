/*
 * @(#)HttpMainControllerAdapter.java	1.0 2011-4-15
 *
 * Copyright 2004-2010 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.csii.upp.paygate.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.csii.pe.channel.http.servlet.Controller;

/**
 * HttpMainControllerAdapter.java
 * 
 * HttpMainController的适配器
 *
 * @author cuiyi
 * <p>
 *   Created on 2011-4-18
 *   Modification history
 * </p>
 * <p>
 *   IBS Product Expert Group, CSII
 *   Powered by CSII PowerEngine 6.0
 * </p>
 * @version 1.0
 * @since 1.0
 */
public class HttpMainControllerAdapter implements org.springframework.web.servlet.mvc.Controller  {
	
	private Controller controller;
	
	private static final String ATT_VIEWREFERER_KEY = "_viewReferer";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object obj = controller.process(request, response, response.getLocale());
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(this.transferResultMap((Map)obj));
		mav.setViewName((String) mav.getModel().get(ATT_VIEWREFERER_KEY));
		return mav;
	}
	
	private Map transferResultMap(Map<String, Object> resultMap) {
		Map<String, Object> tmpMap=new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			String key=entry.getKey();
			Object value=entry.getValue();
			if (Character.isLowerCase(key.charAt(0))) {
				String firstOneKey = (new StringBuilder()).append(Character.toUpperCase(key.charAt(0))).append(key.substring(1))
						.toString();
				if(value instanceof Map){
					tmpMap.put(firstOneKey, this.transferResultMap((Map)value));
				}if(value instanceof List){
					List<Map> list=(List<Map>)value;
					List<Object> result = new ArrayList<Object>();
					for (Map map : list) {
						result.add(this.transferResultMap(map));
					}
					tmpMap.put(firstOneKey, result);
				}else{
					tmpMap.put(firstOneKey, value);
				}
		
			}
		}
		resultMap.putAll(tmpMap);
		return resultMap;
	}
	
	

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
