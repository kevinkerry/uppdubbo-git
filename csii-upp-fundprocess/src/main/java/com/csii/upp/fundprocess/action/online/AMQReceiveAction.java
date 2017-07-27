package com.csii.upp.fundprocess.action.online;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 接收MQ消息
 */
public class AMQReceiveAction extends PayOnlineAction {

	private static final Log logger = LogFactory.getLog(AMQReceiveAction.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		Map queueData = context.getDataMap();
		logger.info("接收到MQ消息内容：" + queueData);
		Object object = queueData.get("mQMessageContent"); // 队列消息内容
		logger.info("消息内容：" + object); // 其他业务处理(消息内容可为 json、xml等文本化内容)
	}
	
}
