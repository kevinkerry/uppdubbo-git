package com.csii.upp.fundprocess.action.payment;

import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.dto.generate.OveralltranshistExample;
import com.csii.upp.dto.router.unionpay.RespQueryRtxnState;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * @author zhubenle
 * @description 查询订单对应流水支付情况
 *
 */
public class QueryOrderPayStatusAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		try {
			OveralltransExample overalltransExample = new OveralltransExample();
			overalltransExample.createCriteria().andUppertransnbrEqualTo(input.getUppertransnbr());
			List<Overalltrans> Overalltranss = OveralltransDAO.selectByExample(overalltransExample);
			if (Overalltranss != null && Overalltranss.size() > 0) {
				Overalltrans overalltrans = Overalltranss.get(0);
				if (TransStatus.SUCCESS.equals(overalltrans.getOveralltransstatus())) {
					context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_OK);
				} else if (TransStatus.FAILURE.equals(overalltrans.getOveralltransstatus())) {
					context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_FAIL);
				} else {
					InnerfundtransExample innerfundtransExample = new InnerfundtransExample();
					innerfundtransExample.createCriteria().andOveralltransnbrEqualTo(overalltrans.getOveralltransnbr());
					Innerfundtrans innerfundtrans = InnerfundtransDAO.selectByExample(innerfundtransExample).get(0);

					input.setInnerfundtransnbr(innerfundtrans.getInnerfundtransnbr());
					input.setTransnbr(innerfundtrans.getInnerfundtransnbr());
					input.setOveralltransnbr(innerfundtrans.getOveralltransnbr());
					input.setTranstime(innerfundtrans.getTranstime());
					input.setTranscode(innerfundtrans.getTranscode());

					RespQueryRtxnState respQueryRtxnState = (RespQueryRtxnState) getUnionPayService()
							.queryRtxnForOtherEBank(input);
					if (TransStatus.SUCCESS.equals(respQueryRtxnState.getRtxnstate())) {
						if ("A6".equals(respQueryRtxnState.getOrigRespCode())
								|| "00".equals(respQueryRtxnState
										.getOrigRespCode()))
							context.setData(Dict.PAY_STATUS,
									PayStatus.PAY_STATUS_RECEIVED);
					} else if (TransStatus.FAILURE.equals(respQueryRtxnState.getRtxnstate())) {
						overalltrans.setOveralltransstatus(TransStatus.FAILURE);
						OveralltransDAO.updateByPrimaryKey(overalltrans);
						context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_FAIL);
					} else {
						context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_HAND);
					}
				}
			} else {
				OveralltranshistExample overalltranshistExample = new OveralltranshistExample();
				overalltranshistExample.createCriteria().andUppertransnbrEqualTo(input.getUppertransnbr());
				List<Overalltranshist> overalltranshists = OveralltranshistDAO.selectByExample(overalltranshistExample);
				if (overalltranshists != null && overalltranshists.size() > 0) {
					Overalltranshist overalltranshist = overalltranshists.get(0);
					if (TransStatus.SUCCESS.equals(overalltranshist.getOveralltransstatus())) {
						
					} else if (TransStatus.FAILURE.equals(overalltranshist.getOveralltransstatus())) {
						context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_FAIL);
					} else {
						context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_HAND);
					}
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_HAND);
		}
	}
}