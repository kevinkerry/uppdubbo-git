package com.csii.upp.payment.action.query;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.router.fundprocess.RespQueryOrderPayStatus;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 查询订单的支付状态
 * 
 * @author zhubenle
 *
 */
public class QueryOrderStatusPaymentAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		try {
			Onlineorderinfo order = OnlineorderinfoDAO.selectByPrimaryKey(context.getString(Dict.MER_NBR),
					context.getString(Dict.MER_SEQ_NBR));
			if (PayStatus.PAY_STATUS_HAND.equals(order.getPaystatus())) {
				// 如果订单是处理中状态，就发fundprocess去银联查询
				Onlinetrans onlinetrans = OnlinetransDAO.selectByPrimaryKey(order.getTransseqnbr());

				context.setData(Dict.TRANS_SEQ_NBR, onlinetrans.getTransseqnbr());
				context.setData(Dict.CYBER_TYP_CD, onlinetrans.getCybertypcd());

				InputPaymentData input = new InputPaymentData(context.getDataMap());

				RespQueryOrderPayStatus respQueryOrderPayStatus = getFDPSService().queryOrderPayStatus(input);
				String payStatus = respQueryOrderPayStatus.getPayStatus();
				if (payStatus != null) {
					if (PayStatus.PAY_STATUS_OK.equals(payStatus)) {
						input.setTransstatus(TransStatus.SUCCESS);
						OnlineTransExtendDAO.updateTransStatus(input);
					} else if (PayStatus.PAY_STATUS_FAIL.equals(payStatus)) {
						input.setTransstatus(TransStatus.FAILURE);
						OnlineTransExtendDAO.updateTransStatus(input);
					}
					context.setData(Dict.PAY_STATUS, payStatus);
				}else {
					context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_HAND);
				}
			} else {
				context.setData(Dict.PAY_STATUS, order.getPaystatus());
			}
		} catch (SQLException e) {
			context.setData(Dict.PAY_STATUS, PayStatus.PAY_STATUS_HAND);
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
