package com.csii.upp.fundprocess.action.common;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.fundprocess.action.event.handler.RegOverallRequestEvent;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 请求信息登记处理
 * 
 * @author xujin
 * 
 */
public class RequestRegAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.TRANS_ID, context.getTransactionId());
		if(StringUtil.isStringEmpty(context.getString(Dict.POST_DATE))){
			context.setData(Dict.POST_DATE, DateUtil.Date_To_DateFormat(this.getPostDate()));
		}
		RegOverallRequestEvent event = new RegOverallRequestEvent();
		event.setParamMap(context.getDataMap());
		DefaultSupportor.getEventManager().doService(event);
	}

}