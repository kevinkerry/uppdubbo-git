package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ConstUnionPay;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 银联代收后半部分(返回成功后入账)
 * 
 * @author WHD
 *
 */
public class UnionPayDSAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		String innerrtxnnbr = ctx.getString(Dict.ORDER_ID);
		String respCode = ctx.getString("respCode");

		/*
		 * 如果返回成功，接着做贷方交易 （电子账户入账，并且更新交易状态）
		 */
		Innerfundtrans Innerfundtrans = null;
		try {
			Innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerrtxnnbr);

		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

		if (Innerfundtrans == null) {
			throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
		}
		ctx.setData(Dict.OVERALL_TRANS_NBR, Innerfundtrans.getOveralltransnbr());
		if (TransStatus.PROCESSING.equals(Innerfundtrans.getTransstatus())||
				TransStatus.TIMEOUT.equals(Innerfundtrans.getTransstatus())) {
			if (ConstUnionPay.STATUS_SUCCESS.equals(respCode)) {
				Map<String, Object> map = this.getObjectMapMarshaller().marshall(Innerfundtrans);
				Date rtxnDate = this.getPostDate();
				map.put(Dict.TRANS_DATE, rtxnDate);
				map.put(Dict.CHNL_ID, FundChannelCode.UNIONPAY);
				// 账户分录用CUPS
				map.put(Dict.CHECK_CARD_PWD_FLAG, FundChannelCode.CUPS);
				InputFundData input = new InputFundData(map);

				input.setPayeracctnbr(Innerfundtrans.getPayeracctnbr());
				input.setPayeeacctnbr(Innerfundtrans.getPayeeacctnbr());
				input.setPayername(Innerfundtrans.getPayername());
				input.setCurrencycd(Innerfundtrans.getCurrencycd());
				ctx.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
				RespSysHead deposite = getDBankService(input).rtdtcrForUnionPay(input);
				if (deposite.isSuccess()){
					Innerfundtrans.setTransdate(rtxnDate);
					Innerfundtrans.setTransstatus(TransStatus.SUCCESS);
				}
				try {
					InnerfundtransDAO.updateByPrimaryKeySelective(Innerfundtrans);
				} catch (SQLException e) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
			} else {
				Innerfundtrans.setTransstatus(TransStatus.FAILURE);
				try {
					InnerfundtransDAO.updateByPrimaryKeySelective(Innerfundtrans);
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				} catch (SQLException e) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
			}
		}
	}
}
