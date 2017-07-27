package com.csii.upp.paygate.action.cyber;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqBankPay;
import com.csii.upp.paygate.action.PayGateAction;

/**
 * 本行个人网银支付-->发给网银 （修改后的明文和签名）
 * 
 * @author zgb
 * 
 */
public class PbankpayAction extends PayGateAction {
	private String oneIp;
	private String pweb;

	public void execute(Context context) throws PeException {

		context.setData(Dict.CYBER_TYP_CD,pweb);
		context.setData(Dict.PAY_TYP_CD, PayTypCd.OURCYBER);
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		Map resultMap = this.sendPaymenTransport(new ReqBankPay(inputData));

		String plain = (String) resultMap.get(Dict.PLAIN);
		String signature = (String) resultMap.get(Dict.SIGNATURE);
		context.setData(Dict.PLAIN, plain);
		context.setData(Dict.SIGNATURE, signature);
		context.setData(Dict.PAY_IP, oneIp);
	}

	public String getOneIp() {
		return oneIp;
	}

	public void setOneIp(String oneIp) {
		this.oneIp = oneIp;
	}

	public String getPweb() {
		return pweb;
	}

	public void setPweb(String pweb) {
		this.pweb = pweb;
	}

}
