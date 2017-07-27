package com.csii.upp.fundprocess.action.payment;


import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.alipacode.RespAlipayCodeSynInfo;
import com.csii.upp.dto.router.wechatcode.RespWeChatCodeSynInfo;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 商户信息同步(新增)
 * @author shell
 *
 */
public class SynMerchantInfoAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {

		InputFundData input = new InputFundData(context.getDataMap());
		RespAlipayCodeSynInfo output = null;
		RespWeChatCodeSynInfo output2 = null;
		 
		if("01".equals(context.getString(Dict.PROXY_SYN_TYPE))){
			 output = getAliPayService().synAlipayMerchantInfo(input);
			 context.setData(Dict.ALIPAY_MERCHANT_ID, output.getAlipayMerchantId());
		}else if("02".equals(context.getString(Dict.PROXY_SYN_TYPE))){
			 output2 = getWechatService().synWCMerchantInfo(input);
			 context.setData(Dict.WE_CHAT_SUB_MERCHAT_ID, output2.getSubMchId());
			 context.setData(Dict.PROXY_MER_NBR, output2.getMchId());
		}else if("00".equals(context.getString(Dict.PROXY_SYN_TYPE))){
			 output = getAliPayService().synAlipayMerchantInfo(input);
			 output2 = getWechatService().synWCMerchantInfo(input);
			 context.setData(Dict.ALIPAY_MERCHANT_ID, output.getAlipayMerchantId());
			 context.setData(Dict.WE_CHAT_SUB_MERCHAT_ID, output2.getSubMchId());
			 context.setData(Dict.PROXY_MER_NBR, output2.getMchId());
		}
 	}

}
