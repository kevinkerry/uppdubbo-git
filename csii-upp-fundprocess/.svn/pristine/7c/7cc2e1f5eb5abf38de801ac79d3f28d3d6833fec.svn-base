package com.csii.upp.fundprocess.action.payment;


import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.point.RespQueryPayerIntegral;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 积分查询
 * @author wangkl
 *
 */
public class QueryIntegralFundAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {

		InputFundData input = new InputFundData(context.getDataMap());
		// 发往积分网关，查询积分
		RespQueryPayerIntegral output = (RespQueryPayerIntegral) getPointService().queryPayerIntegral(input);
		context.setData(Dict.OCCU_DATE, output.getOccuDate());
		context.setData(Dict.POINT_RECORDS, output.getPointRecords());
 	}

}
