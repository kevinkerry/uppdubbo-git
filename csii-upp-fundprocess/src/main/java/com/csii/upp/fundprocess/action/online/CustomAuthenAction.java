package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.unionpay.RespCustomAuthen;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.UnionPayService;

/**
 * 客户鉴权(POC测试使用)
 * 
 * @author 颜祎名
 *
 */
public class CustomAuthenAction extends PayOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputFundData input = new InputFundData(ctx.getDataMap());

		// 客户姓名
		String customName = ctx.getString(Dict.CUSTOM_NAME);
		// 证件类型
		String certTyp = ctx.getString(Dict.CERT_TYP);
		// 证件编号
		String certNo = ctx.getString(Dict.CERT_NO);
		// 卡类型
		String cardTyp = ctx.getString(Dict.CARD_TYP);
		// 卡号
		String cardNo = ctx.getString(Dict.CARD_NO);
		// 客户类型
		String customTyp = ctx.getString(Dict.CUSTOM_TYP);
		// 客户手机号
		String phoneNo = ctx.getString(Dict.PHONE_NO);

		// TODO 具体业务逻辑

		RespCustomAuthen resp = (RespCustomAuthen) getUnionPayService().customAuthen(input);
	}

	protected UnionPayService getUnionPayService() {
		return (UnionPayService) getRouterService(FundChannelCode.UNIONPAY);
	}
}
