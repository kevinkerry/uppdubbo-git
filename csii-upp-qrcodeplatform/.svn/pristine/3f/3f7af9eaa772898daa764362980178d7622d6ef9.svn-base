package com.csii.upp.qrcodeplatform.template;

import java.util.Collection;
import java.util.Iterator;

import com.csii.ibs.workflow.AbstractTrsWorkflow;
import com.csii.pe.action.Action;
import com.csii.pe.action.Executable;
import com.csii.pe.action.PlaceholderAction;
import com.csii.pe.channel.ws.srv.InvokerExceptionHandler;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.qrcodeplatform.action.constant.Dict;
import com.csii.upp.qrcodeplatform.action.constant.ResponseCode;
import com.csii.upp.qrcodeplatform.action.constant.ResponseStatus;
import com.csii.upp.qrcodeplatform.action.util.StringUtil;

/**
 * 批量交易通用模板
 * 
 * @author xujin
 * 
 */
public class BaseUppTemplate extends AbstractTrsWorkflow {
	
	private InvokerExceptionHandler exceptionHandler;
	public void setExceptionHandler(InvokerExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	
	public void execute(Context context) throws PeException {
		Collection<Action> actions = getActions().values();

		boolean isCommonSuccess = true;
		for (Action action : actions) {
			if (action instanceof PlaceholderAction) {
				if (isCommonSuccess) {
					exeConfigAction(context);
				}
				isCommonSuccess = true;
			} else {
				try {
					if(isCommonSuccess){
						doInternal(context, action);
					}
				} catch (Exception e) {
					if(this.exceptionHandler!=null){
						isCommonSuccess = false;
						this.exceptionHandler.handleException(e, context);
					}else{
						if (e instanceof PeException) {
							throw (PeException) e;
						} else {
							throw new PeRuntimeException(e);
						}
					}
				}
			}
		}
	}

	private void exeConfigAction(Context context) throws PeException {
		Collection<Action> actions = context.getTransactionConfig()
				.getActions().values();
		Action action = null;
		try {
			for (Iterator<Action> iterator = actions.iterator(); iterator
					.hasNext();) {
				action = iterator.next();
				doInternal(context, action);
			}
			
			if (StringUtil.isObjectEmpty(context.getData(Dict.RESP_STATUS))
					&& StringUtil.isObjectEmpty(context.getData(Dict.RESP_CODE))
					&& StringUtil.isObjectEmpty(context.getData(Dict.RESP_MESSAGE))) {
				context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
				context.setData(Dict.RESP_STATUS, ResponseStatus.SUCCESS);
				context.setData(Dict.RESP_MESSAGE, "交易成功");
			}
		} catch (Exception e) {
			if(this.exceptionHandler!=null){
				this.exceptionHandler.handleException(e, context);
			}else{
				if (e instanceof PeException) {
					throw (PeException) e;
				} else {
					throw new PeRuntimeException(e);
				}
			}
		}
	}

	private void doInternal(final Context context, final Action action)
			throws PeException {
		log.info(StringUtil.buildString("***************Start Execute Action[", action
				.getClass().getName(), "]***************"));
		try {
			((Executable) action).execute(context);
		} finally {
			log.info(StringUtil.buildString("***************End Execute Action[", action
					.getClass().getName(), "]***************"));
		}
	}
}