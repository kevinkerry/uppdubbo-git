package com.csii.upp.batch.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.batch.event.handler.RtxnExcepEvent;
import com.csii.upp.constant.ExpHandleState;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.extend.TransexceptionregExtendDAO;
import com.csii.upp.dto.generate.Transexceptionreg;
import com.csii.upp.supportor.DefaultSupportor;

/**
 * 日间异常交易调度
 * 
 * @author 徐锦
 * 
 */
public class RunTransExcepAction extends BaseAction {
	@Override
	public void execute(Context ctx) throws PeException {
		// 获得未处理异常处理信息
		Date postdate = SysinfoExtendDAO.getSysInfo().getPostdate();
		// List<Long> list = new
		// ArrayList<Long>(Arrays.asList(ExpHandleState.PREHANDLE,ExpHandleState.HANDLING));
		List<String> list = new ArrayList<String>(
				Arrays.asList(ExpHandleState.PREHANDLE));
		List<Transexceptionreg> rtxnExps = TransexceptionregExtendDAO
				.getTransexceptionreg(list, postdate);
		
		if (rtxnExps.isEmpty()) {
			return;
		}
		Transexceptionreg rtxnState = new Transexceptionreg();
		for (Transexceptionreg rtxnExcep : rtxnExps) {
			RtxnExcepEvent event = new RtxnExcepEvent();
			event.setTransexceptionreg(rtxnExcep);
			event.setRouterService(this.getRouterService(rtxnExcep.getFundchannelcode()));
			// 更新日间异常为处理中
			rtxnState.setExpseqnbr(rtxnExcep.getExpseqnbr());
			rtxnState.setExphandlestatus(ExpHandleState.HANDLING);
			if (rtxnExcep.getMaxhandletimes().equals(rtxnExcep.getRetrytimes()))
				rtxnState.setRetrytimes(0L);
			TransexceptionregExtendDAO.updateTransexceptionreg(rtxnState);
			
			//如果已日切就不进行日间异常处理，放在日终对账处理
			if(rtxnExcep.getTransdate().before(SysinfoExtendDAO.getSysInfo().getPostdate())){
				continue;
			}
			// 异步线程处理日间异常交易
			DefaultSupportor.getEventManager().doService(event);
		}
	}
}
