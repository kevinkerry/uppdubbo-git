package com.csii.upp.fundprocess.action.common;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 第三方支付签名验证处理
 * 
 * @author 徐锦
 * 
 */
public class SignCheckAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		log.debug("CallBackServlet接收后台通知开始");
		log.info("receive:" + context.getDataMap());

//		// 获取请求参数中所有的信息
//		Map<String, String> reqParam = context.getDataMap();
//		// 打印请求报文
//		LogUtil.printRequestLog(reqParam);
//
//		Map<String, String> valideData = null;
//		String encoding = (String) context.getData("encoding");
//		if (null != reqParam && !reqParam.isEmpty()) {
//			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
//			valideData = new HashMap<String, String>(reqParam.size());
//			while (it.hasNext()) {
//				Entry<String, String> e = it.next();
//				String key = (String) e.getKey();
//				String value = (String) e.getValue();
//				try {
//					value = new String(value.getBytes("ISO-8859-1"), encoding);
//				} catch (UnsupportedEncodingException e1) {
//					// TODO Auto-generated catch block
//					log.error("UnsupportedEncodingException", e1);
//				}
//				valideData.put(key, value);
//			}
//		}
//
//		try {
//			// 验证签名
//			if (!SDKUtil.validate(valideData, encoding)) {
//				log.debug("验证签名结果[失败].");
//			} else {
//				log.debug("验证签名结果[成功].");
//			}
//		} catch (Throwable e) {
//			log.error("校验失败", e);
//		}

		log.debug("CallBackServlet接收后台通知结束");
	}

}