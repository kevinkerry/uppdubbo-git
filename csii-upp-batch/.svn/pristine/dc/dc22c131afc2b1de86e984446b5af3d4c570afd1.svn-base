package com.csii.upp.batch.action;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.batch.event.handler.RunQueEvent;
import com.csii.upp.dict.Dict;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;
/**
 * 手动运行队列
 * @author xujin
 *
 */
public class RunQueAction extends BaseAction{
	
	@Override
	public void execute(Context ctx) throws PeException {
		try {
			RunQueEvent event=new RunQueEvent();
			event.setQueNbr(StringUtil.parseLong(ctx.getString(Dict.QUE_NBR)));
			event.setRunSeqNbr(StringUtil.parseLong(StringUtil.parseObjectToString(ctx
							.getData(Dict.RUN_SEQ_NBR))));
			event.setRunDate(ctx.getString(Dict.RUN_DATE));
			// 异步线程处理
			DefaultSupportor.getEventManager().doService(event);
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
	}
}
