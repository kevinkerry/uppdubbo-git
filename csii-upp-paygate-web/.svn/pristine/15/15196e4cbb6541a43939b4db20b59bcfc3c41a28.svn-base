package com.csii.upp.paygate.servlet;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.csii.pe.core.PeException;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.util.StringUtil;

public class MainContextResolverExtend extends MainContextResolver {

	@Override
	public String handerSpecialPlain(HttpServletRequest request) throws PeException {
		if (!StringUtil.isStringEmpty(request.getParameter("merId"))
				&& request.getParameter("merId").startsWith("88")) {
			System.out.println("特殊商户组装验签明文");
			// 财政平台 用请求类型来判断交易类型
			String requestModel = request.getParameter("requestModel");
			if (StringUtil.isStringEmpty(requestModel)) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Const.REQUEST_MODEL });
			}
			String plain = null;
			if ("01".equals(requestModel)) {
				plain = packPayPlain(request);
			} else if ("02".equals(requestModel)) {
				plain = packQueryPlain(request);
			} else if ("03".equals(requestModel) || "04".equals(requestModel)) {
				plain = packReturnPlain(request);
			} else {
				throw new PeException(DictErrors.REQUEST_MODEL_ERROR);
			}
			return plain;
		} else {
			return null;
		}
	}

	@Override
	public Map resolverData(HttpServletRequest request) throws PeException {
		if (!StringUtil.isStringEmpty(request.getParameter("merId"))
				&& request.getParameter("merId").startsWith("88")) {
			System.out.println("特殊商户解析到context");
			// 财政平台 用请求类型来判断交易类型
			String requestModel = request.getParameter("requestModel");
			if (StringUtil.isStringEmpty(requestModel)) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Const.REQUEST_MODEL });
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("requestModel", requestModel);
			if ("01".equals(requestModel)) {
				map = resolverPayMap(request);
			} else if ("02".equals(requestModel)) {
				map = resolverQueryMap(request);
			} else if ("03".equals(requestModel) || "04".equals(requestModel)) {
				map = resolverReturnMap(request);
			} else {
				throw new PeException(DictErrors.REQUEST_MODEL_ERROR);
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 将必要字段放到map，解析到context中 支付交易
	 * @throws PeException 
	 */
	private Map<String, Object> resolverPayMap(HttpServletRequest request) throws PeException {
		Map<String, Object> contextMap = new HashMap<String, Object>();
		if("01".equals(request.getParameter("payType"))){
			contextMap.put("TransId", "IPER");
		}else if("02".equals(request.getParameter("payType"))){
			contextMap.put("TransId", "IPEM");
		}else {
			throw new PeException(DictErrors.CZPAY_TYPE);
		}
		
		try {
			contextMap.put("TransAmt", new BigDecimal(URLDecoder.decode(request.getParameter("orderAmt"),"UTF-8")).divide(new BigDecimal(100)));
			contextMap.put("MerDateTime", URLDecoder.decode(request.getParameter("txnTime"),"UTF-8"));
			contextMap.put("OrderId", URLDecoder.decode(request.getParameter("orderId"),"UTF-8"));
			contextMap.put("MerSeqNo", URLDecoder.decode(request.getParameter("orderId"),"UTF-8"));
			contextMap.put("MerDateTime", URLDecoder.decode(request.getParameter("txnTime"),"UTF-8"));
			contextMap.put("MerURL", URLDecoder.decode(request.getParameter("notifyUrl"),"UTF-8"));
			contextMap.put("MerchantId", URLDecoder.decode(request.getParameter("merId"),"UTF-8"));
			contextMap.put("MerURL1", URLDecoder.decode(request.getParameter("returnUrl"),"UTF-8"));
			contextMap.put("BizType", URLDecoder.decode(request.getParameter("bizType"),"UTF-8"));	//业务类型
			contextMap.put("RequestModel", URLDecoder.decode(request.getParameter("requestModel"),"UTF-8"));	//请求类型
			contextMap.put("TradeCode", URLDecoder.decode(request.getParameter("tradeCode"),"UTF-8"));	//代收机构编码
		} catch (UnsupportedEncodingException e) {
			throw new PeException(DictErrors.CZ_ENCODE_ERROR);
		}
		
		
		List<Map<String, Object>> merTransList = new ArrayList<Map<String, Object>>();
		//财政网的交易只有一条。
		Map<String, Object> transMap = new HashMap<String, Object>();
		transMap.put("SubMerchantId", "GG"+contextMap.get("MerchantId"));
		transMap.put("SubMerSeqNo", "G"+contextMap.get("OrderId"));
		transMap.put("SubTransAmt", contextMap.get("TransAmt"));
		transMap.put("SubMerDateTime", (String) contextMap.get("MerDateTime"));
		
		merTransList.add(transMap);
		contextMap.put("MerTransList", merTransList);
		//支付的额外字段
		String plainContext =  
                "<Finance><Message>" +
                "<TransId>" +
                contextMap.get("TransId") +
                "</TransId><MerchantId>" +
                contextMap.get("MerchantId") +
                "</MerchantId><MerSeqNo>" +
                contextMap.get("MerSeqNo") +
                "</MerSeqNo><OrderId>" +
                contextMap.get("OrderId") +
                "</OrderId><TransAmt>" +
                contextMap.get("TransAmt") +
                "</TransAmt><MerDateTime>" +
                contextMap.get("MerDateTime") +
                "</MerDateTime><MerURL>" +
                contextMap.get("MerURL") +
                "</MerURL><MerURL1>" +
                contextMap.get("MerURL1") +
                "</MerURL1><BizType>" +
                contextMap.get("BizType") +
                "</BizType><RequestModel>" +
                contextMap.get("RequestModel") +
                "</RequestModel><TradeCode>" +
                contextMap.get("TradeCode") +
                "</TradeCode><MerTransList>" +
                "<MerTransDetail><SubMerchantId>" + transMap.get("SubMerchantId") + "</SubMerchantId>" +
                "<SubMerDateTime>" + transMap.get("SubMerDateTime") + "</SubMerDateTime>" +
                "<SubMerSeqNo>" + transMap.get("SubMerSeqNo") + "</SubMerSeqNo>" +
                "<SubTransAmt>" + transMap.get("SubTransAmt") + "</SubTransAmt></MerTransDetail>" +
                "</MerTransList></Message></Finance>";
		logger.info("财政网支付重组明文context："+plainContext);
		contextMap.put("PlainContext", plainContext);
		return contextMap;
	}

	/**
	 * 将必要字段放到map，解析到context中 退货交易
	 * @throws PeException 
	 */
	private Map<String, Object> resolverReturnMap(HttpServletRequest request) throws PeException {
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("TransId", "IPSR");
		try {
			contextMap.put("MerchantId", URLDecoder.decode(request.getParameter("merId"),"UTF-8"));
			contextMap.put("MerSeqNo", "R"+URLDecoder.decode(request.getParameter("orderId"),"UTF-8"));
			contextMap.put("SubMerchantId", "GG"+URLDecoder.decode(request.getParameter("merId"),"UTF-8"));
			contextMap.put("SubMerchantId", "RG"+URLDecoder.decode(request.getParameter("orderId"),"UTF-8"));
			contextMap.put("SubTransAmt", new BigDecimal(URLDecoder.decode(request.getParameter("orderAmt"),"UTF-8")).divide(new BigDecimal(100)));
			contextMap.put("MerDateTime", URLDecoder.decode(request.getParameter("txnTime"),"UTF-8"));
			contextMap.put("SubMerDateTime", URLDecoder.decode(request.getParameter("txnTime"),"UTF-8"));
			contextMap.put("OrgMerSeqNo", URLDecoder.decode(request.getParameter("orderId"),"UTF-8"));
			contextMap.put("OrgSubMerSeqNo", "G"+URLDecoder.decode(request.getParameter("orderId"),"UTF-8"));
			
			contextMap.put("RequestModel", URLDecoder.decode(request.getParameter("requestModel"),"UTF-8"));	//请求类型
			contextMap.put("TradeCode", URLDecoder.decode(request.getParameter("tradeCode"),"UTF-8"));	//代收机构编码
		} catch (UnsupportedEncodingException e) {
			throw new PeException(DictErrors.CZ_ENCODE_ERROR);
		}
		
		return contextMap;
	}

	private Map<String, Object> resolverQueryMap(HttpServletRequest request) {

		return null;
	}
	
	/**
	 * 组装支付明文，验签用
	 */
	private String packPayPlain(HttpServletRequest request) {
		Map<String, String> plainMap = new HashMap<String, String>();
		if(!StringUtil.isStringEmpty(request.getParameter("encoding"))){
			plainMap.put("encoding", request.getParameter("encoding"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("merId"))){
			plainMap.put("merId", request.getParameter("merId"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("orderId"))){
			plainMap.put("orderId", request.getParameter("orderId"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("orderSubject"))){
//			try {
//				plainMap.put("orderSubject", new String(request.getParameter("orderSubject").getBytes("UTF-8"),"UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			plainMap.put("orderSubject", request.getParameter("orderSubject"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("orderAmt"))){
			plainMap.put("orderAmt", request.getParameter("orderAmt"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("payType"))){
			plainMap.put("payType", request.getParameter("payType"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("bizType"))){
			plainMap.put("bizType", request.getParameter("bizType"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("requestModel"))){
			plainMap.put("requestModel", request.getParameter("requestModel"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("notifyUrl"))){
			plainMap.put("notifyUrl", request.getParameter("notifyUrl"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("returnUrl"))){
			plainMap.put("returnUrl", request.getParameter("returnUrl"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("txnTime"))){
			plainMap.put("txnTime", request.getParameter("txnTime"));
		}
		
		if(!StringUtil.isStringEmpty(request.getParameter("isOverTime"))){
			plainMap.put("isOverTime", request.getParameter("isOverTime"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("isTrueName"))){
			plainMap.put("isTrueName", request.getParameter("isTrueName"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("payerSfz"))){
			plainMap.put("payerSfz", request.getParameter("payerSfz"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("tradeCode"))){
			plainMap.put("tradeCode", request.getParameter("tradeCode"));
		}
		
		String plain = packPlain(plainMap);
		return plain;
	}
	
	private String packReturnPlain(HttpServletRequest request) {

		Map<String, String> plainMap = new HashMap<String, String>();
		if(!StringUtil.isStringEmpty(request.getParameter("encoding"))){
			plainMap.put("encoding", request.getParameter("encoding"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("merId"))){
			plainMap.put("merId", request.getParameter("merId"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("orderId"))){
			plainMap.put("orderId", request.getParameter("orderId"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("orderAmt"))){
			plainMap.put("orderAmt", request.getParameter("orderAmt"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("requestModel"))){
			plainMap.put("requestModel", request.getParameter("requestModel"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("txnTime"))){
			plainMap.put("txnTime", request.getParameter("txnTime"));
		}
		if(!StringUtil.isStringEmpty(request.getParameter("tradeCode"))){
			plainMap.put("tradeCode", request.getParameter("tradeCode"));
		}
		
		
		String plain = packPlain(plainMap);
		return plain;
	}

	private String packQueryPlain(HttpServletRequest request) {

		return null;
	}

	/**
	 * 验签规则，按非空字段升序
	 */
	private String packPlain(Map<String, String> plainMap) {
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String,String>>(plainMap.entrySet()); 
		//排序  可能有问题
		Collections.sort(list,new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> entry : list){
			sb.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
		}
		logger.info("特殊商户组装的明文："+sb.toString());
		return sb.toString();
	}

}
