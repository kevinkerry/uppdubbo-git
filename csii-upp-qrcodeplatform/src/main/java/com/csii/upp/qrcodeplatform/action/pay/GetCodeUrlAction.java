package com.csii.upp.qrcodeplatform.action.pay;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.Constants;
import com.csii.upp.qrcodeplatform.action.constant.Dict;
import com.csii.upp.qrcodeplatform.action.util.MiscUtil;
import com.csii.upp.qrcodeplatform.action.util.WxUtil;
import com.csii.upp.qrcodeplatform.action.util.XmlUtil;
import com.csii.upp.qrcodeplatform.generate.dao.QrcodetimectrlMapper;
import com.csii.upp.qrcodeplatform.generate.dao.model.Qrcodetimectrl;
import com.csii.upp.qrcodeplatform.sequence.DefaultSupportor;

public class GetCodeUrlAction extends QrCodeAction {


	@Autowired
	private QrcodetimectrlMapper qrcodetimectrlMapper;
	
	@Override
	public void execute(Context ctx) throws PeException {
		
		String QrCodeNbr = DefaultSupportor.generateSeqNbr();
		String MerName =  ctx.getString("MerName");
		String TransAmt =  ctx.getString("TransAmt");
		
		Date date = MiscUtil.getCurrentDate();
		long timeExprie = 300000;		//二维码有效时间使用毫秒数计
		
		//记录此次发放二维码 时间及编号
		Qrcodetimectrl record = new Qrcodetimectrl();
		record.setQrcodenbr(Long.parseLong(QrCodeNbr));
		record.setStarttime(date);
		record.setTimeexprie(timeExprie);
		qrcodetimectrlMapper.insert(record);
		
		String url = "http://150.221.75.175:8003/paygate2/qrCode?TransName=check&QrCodeNbr="+QrCodeNbr+"&MerchantName="+MerName+"&TransAmt="+TransAmt;
		url = URLEncoder.encode(url);
		long time = System.currentTimeMillis();
		ctx.setData("codeUrl", url);
		ctx.setData("qrcodeNbr", QrCodeNbr);
		ctx.setData(Dict.RESP_STATUS, "S");
		ctx.setData("RespDesc", "交易成功");
		ctx.setData(Dict.RESP_CODE, "0000");
	}

}
