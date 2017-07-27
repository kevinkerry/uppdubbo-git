package com.csii.upp.batch.event.handler;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.service.Service;
import com.csii.upp.constant.ExpHandleState;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.TransexceptionregExtendDAO;
import com.csii.upp.dto.InputData;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.event.EventHandler;
import com.csii.upp.util.StringUtil;

/**
 * 日间异常交易处理
 * @author xujin 
 */
public class RtxnExcepEventHandler implements EventHandler<RtxnExcepEvent> {
	
	protected final static Log log = LogFactory
			.getLog(RtxnExcepEventHandler.class);
	
	private int timespace;

	public int getTimespace() {
		return timespace;
	}

	public void setTimespace(int timespace) {
		this.timespace = timespace;
	}

	public void handler(RtxnExcepEvent event) {
		Service routerService = event.getRouterService();
		Transexceptionreg transexceptionreg = event.getTransexceptionreg();
		int times = StringUtil.parseInteger(transexceptionreg
				.getMaxhandletimes());
		String methodcd =  transexceptionreg.getExphandletranscode();
		InputData inputData = null;
		if(FundChannelCode.FDPS.equals(transexceptionreg.getFundchannelcode())){
			inputData = InputPaymentData.parseInputData(transexceptionreg);
		}else{
			inputData = InputFundData.parseInputData(transexceptionreg);
		}

		int i = 0;
		while (i < times) {
			// 调用服务
			try {
				Method m = routerService.getClass().getMethod(methodcd,
						inputData.getClass());
				m.invoke(routerService, inputData);
				transexceptionreg.setExphandlestatus(ExpHandleState.SUCCESS);
				break;
			} catch (Exception e) {
			}
			//等待timespace秒后再发
			if(times>1){
				try {
					Thread.sleep(this.timespace*1000);
				} catch (InterruptedException e) {
				}
			}
			i++;
		}

		if (i >= times) {
			// 更新日间异常表
			transexceptionreg.setRetrytimes(Long.valueOf(i));
			transexceptionreg.setExphandlestatus(ExpHandleState.TIMESOVER);
		}
		TransexceptionregExtendDAO.updateTransexceptionreg(transexceptionreg);
	}

	public Class<RtxnExcepEvent> getAcceptedEventType() {
		return RtxnExcepEvent.class;
	}

}
