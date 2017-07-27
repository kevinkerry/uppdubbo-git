package com.csii.upp.paygate.action.foison;

import java.math.BigDecimal;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class UpdateCtrlConfirmAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		context.setData(Dict.PAY_TYP_CD, PayTypCd.FOSION);
		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		// 查询本地卡 签约关系
	
		if(new BigDecimal(StringUtil.toStringAndTrim(inputData.getPerTransLimit())).
	        		compareTo(new BigDecimal(StringUtil.toStringAndTrim(inputData.getPerDayLimit())))>0)  {
	        			context.setState(1);
	        			context.setData("RespMessage2", "单笔限额大于单日限额，请更改");
	        			return ;
	        		}
	}
}
