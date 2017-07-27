package com.csii.upp.batch.action;

import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.StandBookTypCD;
import com.csii.upp.dao.extend.FundchannelcleartransExtendDAO;

/**
 * 银联核账接口，后管使用
 * 
 * @author 姜星
 *
 */
public class CheckAmountAction extends BaseAction {

	@Override
	public void execute(Context arg0) throws PeException {
		List<Map> list = FundchannelcleartransExtendDAO
				.getTotalOffsetBalance(StandBookTypCD.UNIONPAY_TOTAL_OFFSET_BALANCE);
		arg0.setData("list", list);
	}
}
