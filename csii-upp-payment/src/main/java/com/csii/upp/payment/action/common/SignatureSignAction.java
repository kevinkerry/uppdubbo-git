package com.csii.upp.payment.action.common;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.SystemCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.payment.helper.XmlSignatureHelper;
/**
 * 加签
 * @author 徐锦
 *
 */
public class SignatureSignAction  extends BaseAction {
	private XmlSignatureHelper xmlSignature;
	public void setXmlSignature(XmlSignatureHelper xmlSignature) {
		this.xmlSignature = xmlSignature;
	}
	@Override
	public void execute(Context ctx) throws PeException {
		String systemCode=ctx.getString(Dict.SYSTEM_CODE);
		if(SystemCode.PAYGATE.equals(systemCode)){
			ctx.setData(Dict.CONTENT,this.xmlSignature.xmltransformsign(ctx.getDataMap(),ctx.getTransactionId()));
		}
	}
}
