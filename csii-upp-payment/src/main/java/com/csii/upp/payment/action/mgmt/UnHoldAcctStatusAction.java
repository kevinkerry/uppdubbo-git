package com.csii.upp.payment.action.mgmt;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 解冻
 * 
 * @author zgb
 *
 */
public class UnHoldAcctStatusAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());

		Userpaytypsigninfo signInfo = new Userpaytypsigninfo();
		signInfo.setSignstatus(SignStatus.NORMA);
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr()).andPaytypcdEqualTo(input.getPaytypcd());
		try {
			UserpaytypsigninfoDAO.updateByExampleSelective(signInfo, example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
