package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.dto.router.fundprocess.RespQueryAcctOpenStatusPayment;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 他行快捷支付开通状态查询
 * 
 * @author zhubenle
 *
 */
public class QueryAcctOpenStatusPaymentAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		String isQueryOpenStatus = context.getString(Dict.IS_QUERY_OPEN_STATUS);
		if ("0".equals(isQueryOpenStatus)) {
			RespQueryAcctOpenStatusPayment resp = getFDPSService().queryAcctOpenStatus(input);
			context.setData(Dict.OPEN_STATUS, resp.getOpenStatus());
		} else if ("1".equals(isQueryOpenStatus)) {
			queryCardType(input);
			
			context.setData(Dict.OPEN_STATUS, "1");	//设置该卡开启，暂时设置
			
			context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
			context.setData(Dict.CARD_BIN_NAME, input.getCardBinName());
			context.setData(Dict.INNER_CARD_FLAG, input.getInnerCardFlag());
		} else if ("2".equals(isQueryOpenStatus)) {
			queryCardType(input);
			context.setData(Dict.INNER_CARD_FLAG, input.getInnerCardFlag());
			context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
			context.setData(Dict.CARD_BIN_NAME, input.getCardBinName());
			if("0".endsWith(input.getInnerCardFlag())){
				try {
					UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
					example.createCriteria().andPaytypcdEqualTo(PayTypCd.INTEL)
							.andSigncardnbrEqualTo(input.getPayeracctnbr());
					List<Userpaytypsigninfo> list = UserpaytypsigninfoDAO.selectByExample(example);
					if (list.size() > 0) {
						if(!input.getPayerphoneno().equals(list.get(0).getSignmobile())){
							throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
						}
						this.autoDeleteSignUser(input.getPayerphoneno(), input.getPayeracctnbr(), PayTypCd.INTEL);
					}
					// 将客户的手机号和卡号存放到签约表里
					this.autoSignUserPayTyp(input.getPayerphoneno(), input.getPayeracctnbr(),
							input.getPayeracctdeptnbr(), PayTypCd.INTEL);
				} catch (SQLException e) {
					throw new PeException(DictErrors.DATABASE_EXCEPTION);
				}
			}
		}
	}
}
