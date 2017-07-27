package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.unionpay.RespAuthenticateName;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.UnionPayService;

/**
 * 实名认证
 * 
 * @author LICHAO
 * 
 */
public class AuthenticateNameAction extends PayOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {

		InputFundData input = new InputFundData(arg0.getDataMap());
		arg0.setData(Dict.CHECK_CARD_PWD_FLAG, FundChannelCode.UNIONPAY);
		RespAuthenticateName resAuthenticateName = ((UnionPayService) getService(FundChannelCode.UNIONPAY
				.toLowerCase())).authenticateName(input);

		// 认证成功返回成功标志+手机号，失败的话返回失败结果
		if ("00".equals(resAuthenticateName.getRespCode())) {
			arg0.setData("result", "success");
			arg0.setData("phoneNo", arg0.getData("payerPhoneNo"));
		} else {
			arg0.setData("result", "failure");
		}

	}
}