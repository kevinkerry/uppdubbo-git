package com.csii.upp.batch.appl.unionpay;

import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.batch.event.handler.TimeOutTxnEvent;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.supportor.DefaultSupportor;

/**
 * 银联渠道超时订单订单状态修改
 * 
 * @author 颜祎名
 *
 */
public class UpdateTimeOutOrdersAppl extends BaseAction {

	private final static int GRID_SECONDS = 300;
	private long beforequeryseconds;
	private long timeoutseconds;

	public long getBeforequeryseconds() {
		return beforequeryseconds;
	}

	public void setBeforequeryseconds(long beforequeryseconds) {
		this.beforequeryseconds = beforequeryseconds;
	}

	public long getTimeoutseconds() {
		return timeoutseconds;
	}

	public void setTimeoutseconds(long timeoutseconds) {
		this.timeoutseconds = timeoutseconds;
	}

	@Override
	public void execute(Context context) throws PeException {

		/*
		 * 获取需要做查询的交易，此时距交易发到银联过了5分钟
		 */
		List<Innerfundtrans> list_needQuery = InnerfundtransExtendDAO.getNeed2QueryTxn(FundChannelCode.UNIONPAY,
				beforequeryseconds, GRID_SECONDS);

		if (list_needQuery != null && !list_needQuery.isEmpty()) {
			for (Innerfundtrans innerfundtrans : list_needQuery) {
				TimeOutTxnEvent event = new TimeOutTxnEvent();
				event.setTimeout(false);
				event.setInnerfundtrans(innerfundtrans);
				event.setRouterService(getRouterService(FundChannelCode.UNIONPAY));
				DefaultSupportor.getEventManager().doService(event);
			}
		}

		/*
		 * 获取需要做查询和更新交易状态的交易，此时距发到银联过了超时时间
		 */
		List<Innerfundtrans> list = InnerfundtransExtendDAO.queryTimeOutTxn(FundChannelCode.UNIONPAY, timeoutseconds,
				GRID_SECONDS);

		if (list != null && !list.isEmpty()) {
			for (Innerfundtrans innerfundtrans : list) {
				TimeOutTxnEvent event = new TimeOutTxnEvent();
				event.setTimeout(true);
				event.setInnerfundtrans(innerfundtrans);
				event.setRouterService(getRouterService(FundChannelCode.UNIONPAY));
				DefaultSupportor.getEventManager().doService(event);
			}
		}

	}

}
