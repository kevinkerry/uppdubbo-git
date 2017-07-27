package com.csii.upp.paygate.action.foison;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaygateData;
import com.csii.upp.dto.router.paym.ReqQuerySignInfoList;
import com.csii.upp.paygate.action.PayGateAction;
import com.csii.upp.util.StringUtil;

public class QueryDetailConfimAction extends PayGateAction {

	@Override
	public void execute(Context context) throws PeException {
		log.info("***********交易明细查询**************");
		if (StringUtil.isStringEmpty(context.getString(Dict.PAYER_ACCT_NBR))) {
			context.setState(2);
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PAYER_ACCT_NBR});
		}

		if (!StringUtil.isStringEmpty(context.getString("newPageNum"))) {
			context.setData(Dict.PAGE_NUM, 1);
		} else {
			if (StringUtil.isStringEmpty(context.getString(Dict.PAGE_NUM))) {
				context.setData(Dict.PAGE_NUM, 1);
			}
		}

		context.setData(Dict.PAY_TYP_CD, 1);
		context.setData(Dict.PAGE_SIZE, 5);

		// 组装查询卡信息报文
		InputPaygateData inputData = new InputPaygateData(context.getDataMap());
		// 查询交易明细
		Map resultMap = this.sendPaymenTransport(new ReqQuerySignInfoList(inputData));
		// 后续处理
		String respCode = resultMap.get(Dict.RESP_CODE).toString();
		if (!ResponseCode.SUCCESS.equals(respCode)) {
			context.setState(2);
		}
		context.setDataMap(resultMap);
	}

}
