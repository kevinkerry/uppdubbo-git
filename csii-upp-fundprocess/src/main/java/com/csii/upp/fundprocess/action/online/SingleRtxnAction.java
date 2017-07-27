/**
 * 
 */
package com.csii.upp.fundprocess.action.online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CurrencyCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.EAccountService;

/**
 * 单笔记账:电子账户入金
 * 
 * @author 姜星
 * 
 */
public class SingleRtxnAction extends PayOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {
		InputFundData input = new InputFundData(arg0.getDataMap());
		// 设置默认值
		if (arg0.getString(Dict.CURRENCY_CD) == null) {
			input.setCurrencycd(CurrencyCode.CNY);
		}
		// 判断验证收款账户是否存在（调电子账户查询接口)
//		Boolean isSuccess = false;
//		try {
//			isSuccess = this.getDBankService(input).queryPayeeAcctInfo(input)
//					.isSuccess();
//		} catch (PayException p) {
//			if (SystemCode.QUERY_TIMEOUT.getCode().equals(p.getExceptionCode())) {
//
//			} else {
//				this.getDBankService(input)
//						.insertTransexceptionregForSingleRtxn(
//								input,
//								input.getRtxnTypCd().equals("STIB") ? RtxnExceptionFunction.IbpsReTrave
//										: input.getRtxnTypCd().equals("STHP") ? RtxnExceptionFunction.HvpsReTrave
//												: RtxnExceptionFunction.BepsReTrave);
//			}
//			arg0.setData("resultStatus", "F");
//			arg0.setData("MESSAGE_TYPE", "2010");
//			List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
//			Map<String, String> retMap = new HashMap<String, String>();
//			retMap.put("RET_CODE", "310524");
//			retMap.put("RET_MSG", "操作数据库异常");
//			resultList.add(retMap);
//			arg0.setData("resultList", resultList);
//			throw p;
//		} catch (RuntimeException e) {
//			this.getDBankService(input)
//					.insertTransexceptionregForSingleRtxn(
//							input,
//							input.getRtxnTypCd().equals("STIB") ? RtxnExceptionFunction.IbpsReTrave
//									: input.getRtxnTypCd().equals("STHP") ? RtxnExceptionFunction.HvpsReTrave
//											: RtxnExceptionFunction.BepsReTrave);
//			arg0.setData("resultStatus", "F");
//			arg0.setData("MESSAGE_TYPE", "2010");
//			List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
//			Map<String, String> retMap = new HashMap<String, String>();
//			retMap.put("RET_CODE", "310524");
//			retMap.put("RET_MSG", "操作数据库异常");
//			resultList.add(retMap);
//			arg0.setData("resultList", resultList);
//			throw e;
//		}
//		if (isSuccess) {
			// 单笔记账：电子账户入金
			EAccountService service=getDBankService(input);
			arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
			RespSysHead output = service.stxndr(input);
			if (output.isSuccess()) {
				arg0.setData("resultStatus", "S");
				arg0.setData("MESSAGE_TYPE", "2010");
				List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
				Map<String, String> retMap = new HashMap<String, String>();
				retMap.put("RET_CODE", "000000");
				retMap.put("RET_MSG", "success");
				resultList.add(retMap);
				arg0.setData("resultList", resultList);
			} else {
				arg0.setData("resultStatus", "F");
				arg0.setData("MESSAGE_TYPE", "2010");
				List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
				Map<String, String> retMap = new HashMap<String, String>();
				retMap.put("RET_CODE", "310524");
				retMap.put("RET_MSG", "操作数据库异常");
				resultList.add(retMap);
				arg0.setData("resultList", resultList);
			}
//		}

	}
}
