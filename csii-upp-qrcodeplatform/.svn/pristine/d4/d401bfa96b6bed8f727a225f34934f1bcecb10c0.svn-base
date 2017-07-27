package com.csii.upp.qrcodeplatform.action.after;

import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.TransStatus;
import com.csii.upp.qrcodeplatform.generate.dao.ChanneltransMapper;
import com.csii.upp.qrcodeplatform.generate.dao.model.Channeltrans;

public class AfterHandleAction extends QrCodeAction {

	@Autowired
	private ChanneltransMapper channeltransMapper;
	
	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		log.debug("交易完成~ 更新订单状态！");
		
		String transNbr = context.getString("UpperTransNbr");
		
		Channeltrans record = channeltransMapper.selectByPrimaryKey(transNbr);
		
		String status = context.getString("RespStatus");
		if(status.equals("R")){
			record.setStatus(TransStatus.DEAL);
		}else{
			record.setStatus(TransStatus.FAIL);
		}
		
		try {
			channeltransMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PeException(e);
		}
		
		log.info("订单更新完成！");
	}

}
