package com.csii.upp.payment.action.mgmt;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.MerStatus;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.router.fundprocess.RespCustomAuthen;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 客户鉴权(POC测试使用)
 * 
 * @author 颜祎名
 *
 */
public class CustomAuthenAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		String merId = ctx.getString("merId");
		Meracctinfo merAcct = null;
		try {
			// 判断一级商户下是否有该二级商户
			merAcct = MeracctinfoDAO.selectByPrimaryKey(merId);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		// 判断该商户是否存在
		if (merAcct == null)
			throw new PeException(DictErrors.MERCHANT_NOT_EXIST);

		// 商户已冻结或注销
		if (!MerStatus.NORMAL.equals(merAcct.getMerstatus()))
			throw new PeException(DictErrors.MER_STATUS_INVALID);

		RespCustomAuthen resp = (RespCustomAuthen) getFDPSService()
				.customAuthen(new InputPaymentData(ctx.getDataMap()));

	}
}
