package com.csii.upp.qrcodeplatform.action.pay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.AlipayCode;
import com.csii.upp.qrcodeplatform.action.constant.AlipayParameter;
import com.csii.upp.qrcodeplatform.action.constant.Dict;

public class AlipayCodeAction extends QrCodeAction {

	private AlipayParameter alipayParameter;

	
	@Override
	public void execute(Context ctx) throws PeException {
		// TODO Auto-generated method stub
		
		//交易流水号（fundprocess传入）,交易金额，交易标题，商户门店号及交易超时时间
		String transSeqNbr = ctx.getString("");
		String transAmt = ctx.getString("");
		String subject = ctx.getString("");
		String store_id = ctx.getString("");
		String timeout_express = ctx.getString("");
		
		AlipayClient alipayClient = new DefaultAlipayClient(alipayParameter.getUrl(), alipayParameter.getAppid(),
															alipayParameter.getMerPrivateKey(), "json",
															alipayParameter.getCharset(), alipayParameter.getPublicKey(),
															"RSA2");
		
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		
		request.setBizContent("{"+
		" \"out_trade_no\":\""+ transSeqNbr+ "\"" +
		" \"total_amount\":\""+ transAmt+ "\"," +
		" \"subject\":\""+ subject+ "\"," +
		" \"store_id\":\""+ store_id+ "\"," +
		" \"timeout_express\":\""+ timeout_express+ "\"" +
		"}");
		
		AlipayTradePrecreateResponse response = null;
		
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			throw new PeException("F100239");
		}
		log.info("支付宝原生扫码支付发送完成，结果解析开始！");
		
		if(response.getCode().equals(AlipayCode.SUCESS)){
			ctx.setData(Dict.RESP_STATUS, "S");
			ctx.setData(Dict.RESP_CODE, "000000");
			ctx.setData("CodeUrl", response.getQrCode());
		}else{
			ctx.setData(Dict.RESP_STATUS, "F");
			ctx.setData(Dict.RESP_CODE, "F100000");
		}
	}


	public AlipayParameter getAlipayParameter() {
		return alipayParameter;
	}


	public void setAlipayParameter(AlipayParameter alipayParameter) {
		this.alipayParameter = alipayParameter;
	}

	
}
