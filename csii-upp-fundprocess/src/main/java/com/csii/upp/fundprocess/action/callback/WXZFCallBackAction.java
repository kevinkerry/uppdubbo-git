package com.csii.upp.fundprocess.action.callback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.fundprocess.action.online.QRCodeCreateOrderAction;

public class WXZFCallBackAction extends PayOnlineAction{

	@Override
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		String innerfundtransnbr = ctx.getString("orderId");
		log.debug("innerFundTrans is "+innerfundtransnbr);
		
		try {
			Innerfundtrans innerfundtrans = InnerfundtransDAO.selectByPrimaryKey(innerfundtransnbr);
			String overalltransnbr = innerfundtrans.getOveralltransnbr();
			String transnbr = innerfundtrans.getUppertransnbr();
			
			Overalltrans overalltrans = OveralltransDAO.selectByPrimaryKey(overalltransnbr);
			overalltrans.setOveralltransstatus("0");
			OveralltransDAO.updateByPrimaryKey(overalltrans);
			
			QRCodeCreateOrderAction.updateTrans(overalltransnbr, transnbr, "0");
			log.debug("微信回调成功  交易状态改变~");
			
			ctx.setData(Dict.CONTENT, "回调成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
