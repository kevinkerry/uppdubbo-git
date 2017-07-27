package com.csii.upp.payment.action.query;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.BankOptionCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 查询签约卡交易限额信息
 * 
 * @author heshishuai
 *
 */
public class QueryCtrlTransAction extends PaymentOnlineAction {
	@Override
	public void execute(Context context) throws PeException {
		Custtransctrl custCtrl = null;
		if (StringUtil.isStringEmpty(context.getString(Dict.SIGN_NBR))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.SIGN_NBR });
		}
		if (StringUtil.isStringEmpty(context.getString(Dict.SIGN_TYP_CD))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.SIGN_TYP_CD });
		}
		// 执行查询操作
		try {
			custCtrl = CusttransctrlDAO.selectByPrimaryKey(context.getString(Dict.SIGN_NBR),
					context.getString(Dict.SIGN_TYP_CD));
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		context.setData(Dict.PER_DAY_LIMIT, custCtrl.getPerdaylimit());
		context.setData(Dict.PER_TRANS_LIMIT, custCtrl.getPertranslimit());
		String perDayMax = null;
		String perTransMax = null;
		if (PayTypCd.FOSION.equals(context.getString(Dict.PAY_TYP_CD))) {
			perDayMax = BankOptionCd.FSDL;
			perTransMax = BankOptionCd.FSTL;
		} else {
			perDayMax = BankOptionCd.USDL;
			perTransMax = BankOptionCd.USTL;
		}
		try {
			context.setData(Dict.PER_DAY_MAX, BankoptionDAO.selectByPrimaryKey(perDayMax, 1L).getBankoptionvalue());
			context.setData(Dict.PER_TRANS_MAX, BankoptionDAO.selectByPrimaryKey(perTransMax, 1L).getBankoptionvalue());
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
