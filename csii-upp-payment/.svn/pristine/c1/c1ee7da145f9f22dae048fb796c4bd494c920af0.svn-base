package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.MerthirdpartacctDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Merthirdpartacct;
import com.csii.upp.dto.generate.MerthirdpartacctExample;
import com.csii.upp.dto.router.fundprocess.RespQueryVirtualBalance;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

public class QueryVirtualAcctBalanceAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		try {
			InputPaymentData input = new InputPaymentData(context.getDataMap());
			String userNbr = context.getString(Dict.USER_NBR);
			String subMerchantId = context.getString(Dict.SUB_MERCHANT_ID);
			String merThirdPartAcctno = context.getString(Dict.PAYEE_ACCT_NBR);
			if (StringUtil.isStringEmpty(subMerchantId) && !StringUtil.isStringEmpty(userNbr)
					&& !StringUtil.isStringEmpty(merThirdPartAcctno)) {
				MerthirdpartacctExample example = new MerthirdpartacctExample();
				example.createCriteria().andUsernbrEqualTo(userNbr);
				List<Merthirdpartacct> thirdAccts = MerthirdpartacctDAO.selectByExample(example);
				if (thirdAccts.isEmpty()) {
					throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
				}
				Merthirdpartacct merthirdpartacct = thirdAccts.get(0);
				if (merthirdpartacct.getVirtualacctnbr().equals(merThirdPartAcctno)) {
					RespQueryVirtualBalance fyBlance = this.getFDPSService().QueryVirtualAcctBalanceInfo(input);
					return2PagateData(context, fyBlance);
				} else {
					throw new PeException(DictErrors.USER_NBR_AND_VIR_ACC_ERROR);
				}
			} else if (StringUtil.isStringEmpty(userNbr) && !StringUtil.isStringEmpty(subMerchantId)
					&& !StringUtil.isStringEmpty(merThirdPartAcctno)) {
				Merthirdpartacct merthirdpartacct = MerthirdpartacctDAO.selectByPrimaryKey(input.getMernbr(), subMerchantId);
				if (merthirdpartacct != null) {
					if (merThirdPartAcctno.equals(merthirdpartacct.getVirtualacctnbr())) {
						RespQueryVirtualBalance fyBlance = this.getFDPSService().QueryVirtualAcctBalanceInfo(input);
						return2PagateData(context, fyBlance);
					} else {
						throw new PeException(DictErrors.USER_NBR_AND_VIR_ACC_ERROR);
					}

				} else {
					throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
				}
			} else {
				throw new PeException(DictErrors.SUBMIT_INFO_ERROR);
			}

		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}

	private void return2PagateData(Context context, RespQueryVirtualBalance fyBlance) {

		context.setData(Dict.BDOA_BAL, fyBlance.getBdoABal());
		context.setData(Dict.BDOB_BAL, fyBlance.getBdoBBal());
		context.setData(Dict.BDOG_BAL, fyBlance.getBdoGBal());
		context.setData(Dict.USER_NBR, fyBlance.getUserNbr());

	}
}
