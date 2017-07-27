package com.csii.upp.fundprocess.action.callback;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.dto.router.unionpay.RespDsjyyb;
import com.csii.upp.fundprocess.action.PayOnlineAction;

public class UnionPayOtherBankQuickPayRegisyterAfterAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		log.info("UnionPayOtherBankQuickPayRegisyterAfterAction--------UnionNotifation Success");
		RespDsjyyb respobj = this.buildRespOjb(ctx);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Userpaytypsigninfo signInfo = new Userpaytypsigninfo();
		signInfo.setSignstatus(SignStatus.NORMA);
		try {
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andPaytypcdEqualTo(PayTypCd.INTEL)
					.andSigncardnbrEqualTo(respobj.getAccNo());			
			UserpaytypsigninfoDAO.updateByExampleSelective(signInfo, example);			
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}

}
