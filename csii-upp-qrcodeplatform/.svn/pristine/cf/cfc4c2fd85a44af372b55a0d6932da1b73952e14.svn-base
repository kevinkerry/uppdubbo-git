package com.csii.upp.qrcodeplatform.action.pay;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.Dict;
import com.csii.upp.qrcodeplatform.generate.dao.QrcodetimectrlMapper;
import com.csii.upp.qrcodeplatform.generate.dao.model.Qrcodetimectrl;

public class CodeCheckTimeOutAction extends QrCodeAction {

	@Autowired
	private QrcodetimectrlMapper qrcodetimectrlMapper;
	
	@Override
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		long qrcodeNbr = Long.parseLong(ctx.getString("QrcodeNbr"));
		
		log.info("检查二维码序列为："+qrcodeNbr+"的时效性开始！");
		
		Qrcodetimectrl qrcodetimectrl = qrcodetimectrlMapper.selectByPrimaryKey(qrcodeNbr);
		if(qrcodetimectrl==null){
			log.info("二维码序列:"+qrcodeNbr+"不存在！");
			throw new PeException("InvalidCode");
		}else{
			Date time = qrcodetimectrl.getStarttime();
			long time1 = time.getTime();
			long tiemexprie = qrcodetimectrl.getTimeexprie();
			long currenttime = System.currentTimeMillis();
			boolean timeout = currenttime-time1>tiemexprie?true:false;
			ctx.setData(Dict.RESP_STATUS, "S");
			ctx.setData("RespDesc", "交易成功");
			ctx.setData(Dict.RESP_CODE, "0000");
			if(timeout){
				ctx.setData("status", "0");
			}else{
				ctx.setData("status", "1");
			}
		}
	}

}
