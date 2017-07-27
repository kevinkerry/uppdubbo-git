package com.csii.upp.qrcodeplatform.action.before;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.QrCodeType;
import com.csii.upp.qrcodeplatform.action.constant.TransStatus;
import com.csii.upp.qrcodeplatform.generate.dao.ChanneltransMapper;
import com.csii.upp.qrcodeplatform.generate.dao.model.Channeltrans;
import com.ibm.db2.jcc.a.c;

public class AddOrderAction extends QrCodeAction {

	@Autowired
	private ChanneltransMapper channeltransMapper;
	
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		log.debug("开始插入订单~");
		
		Channeltrans channeltrans = new Channeltrans();
		channeltrans.setTransnbr(ctx.getString("UpperTransNbr"));
		BigDecimal transamt = ctx.getBigDecimal("TransAmt");
		channeltrans.setTransamt(transamt);
		channeltrans.setStatus(TransStatus.INIT);
		channeltrans.setQrcodetype(QrCodeType.WECHAT);
		
		channeltransMapper.insert(channeltrans);
		
		ctx.setData("auth_code","130094644701059832");
		ctx.setData("paysignkey","xudong12345678xudong12345678csii");
		log.debug("插入订单完成");
		
	}

}
