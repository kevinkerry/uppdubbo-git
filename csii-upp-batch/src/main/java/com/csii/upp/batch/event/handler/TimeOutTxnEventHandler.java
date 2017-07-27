package com.csii.upp.batch.event.handler;

import java.lang.reflect.Method;

import com.csii.pe.service.Service;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dto.InputData;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.unionpay.RespQueryRtxnState;
import com.csii.upp.event.EventHandler;

/**
 * 银联支付超时交易更新处理器
 * 
 * @author 颜祎名
 *
 */
public class TimeOutTxnEventHandler implements EventHandler<TimeOutTxnEvent> {

	@Override
	public void handler(TimeOutTxnEvent event) {
		Innerfundtrans innerfundtrans = event.getInnerfundtrans();
		Service routerService = event.getRouterService();
		InputData inputData = buildInputDate(innerfundtrans);
		String method = "queryRtxnForOtherEBank";
		Method m;

		try {
			m = routerService.getClass().getMethod(method, inputData.getClass());
			RespQueryRtxnState resp = (RespQueryRtxnState) m.invoke(routerService, inputData);
			if (TransStatus.SUCCESS.equals(resp.getRtxnstate()))
				return;
			//对于超时5分钟的查询交易，查询结果不是失败则返回；对于超时30分钟的交易，查询结果不是成功则更新成失败
			if (!TransStatus.FAILURE.equals(resp.getRtxnstate()) && !event.isTimeout())
				return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		InnerfundtransExtendDAO.modifyTimeOutTrans2Fail(innerfundtrans);
	}

	@Override
	public Class<TimeOutTxnEvent> getAcceptedEventType() {
		return TimeOutTxnEvent.class;
	}

	private static InputData buildInputDate(Innerfundtrans innerfundtrans) {
		InputFundData inputData = new InputFundData();
		inputData.setTranscode(innerfundtrans.getTranscode());
		inputData.setInnerfundtransnbr(innerfundtrans.getInnerfundtransnbr());
		inputData.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
		inputData.setTranstime(innerfundtrans.getTranstime());
		return inputData;
	}

}
