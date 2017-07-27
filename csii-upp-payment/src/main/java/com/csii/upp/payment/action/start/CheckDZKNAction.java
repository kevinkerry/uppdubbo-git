package com.csii.upp.payment.action.start;

import java.util.regex.Pattern;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ElecPortFlag;
import com.csii.upp.constant.PatternCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
/**
 *  电子口岸验证
 * @author xujin
 *
 */
public class CheckDZKNAction extends PaymentOnlineAction{

	@Override
	public void execute(Context context) throws PeException {

		InputPaymentData inputData = (InputPaymentData) context.getData(Dict.INPUT_PAYMENT_DATA);
	    //flase:直接做电子口岸通知;true:等待异步回调进行电子口岸
		if (this.isCallBackPayResult(inputData.getPaytypcd())) {
			return;
		}
		for (SubTransData subTrans : inputData.getOnlineSubTransList()) {
			if (ElecPortFlag.ElecPortNotify.equals(subTrans.getElecportflag())){
				/** 
				 *  查询核心接口返回身份证和姓名：
				 *  1.如果是跨境支付，需要传身份证，校验身份证不为空，带英文字母的大写
					2.真实姓名必输项，且真实姓名必须是中文字符
				**/
				RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService()
						.queryCardInfo(inputData);
				String idCardNumber = hostInfo.getPayerIdNbr();
				String payerAcctName =hostInfo.getPayerAcctName();
				if("01".equals(hostInfo.getPayerIdTypCd())){
					if(idCardNumber.endsWith("x"))
						idCardNumber=idCardNumber.replace('x', 'X');	
				}
				Pattern partten = Pattern.compile(PatternCd.CNNAME);
				if(!partten.matcher(payerAcctName).matches())
					throw new PeException(DictErrors.CNNAMERROR,new Object[]{payerAcctName});
				inputData.setPayeridnbr(idCardNumber);
				inputData.setPayeracctname(payerAcctName);
				break;
			}
		}
	}

}
