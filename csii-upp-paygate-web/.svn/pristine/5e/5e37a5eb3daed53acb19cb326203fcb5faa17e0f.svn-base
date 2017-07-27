package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQueryCardDeptInfo;
import com.csii.upp.paygate.action.PayGateAction;

public class QueryDeptNbrAction extends PayGateAction{

	@Override
	public void execute(Context context) throws PeException {
		if(!"11112222".equals(context.getString("passWord"))){
			context.setState(99999);
			return;
		}
		
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		/*
		 * 查询卡信息
		 */
		Map resultMap=this.sendPaymenTransport(new ReqQueryCardDeptInfo(inputData));
		
		if(ResponseCode.SUCCESS.equals(resultMap.get(Dict.RESP_CODE)))
		{
			context.setState(0);
		}
		else
		{	
			context.setState(99999);
			return;
		}
	}

}
