/**
 * 
 */
package com.csii.upp.fundprocess.action.payment;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespCheckOffDateAppl;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * @author gaoqi
 * @description 查询核心账务日期
 *
 */
public class QueryHostDateAction extends PayOnlineAction {

	/* (non-Javadoc)
	 * @see com.csii.pe.action.Executable#execute(com.csii.pe.core.Context)
	 */
	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		InputFundData input = new InputFundData(context.getDataMap());
		RespSysHead output = getCoreService().queryDownPostDate(input);
		RespCheckOffDateAppl deditoutput = (RespCheckOffDateAppl)output;
		context.setData(Dict.HOST_CLEAR_DATE, DateUtil.Date_To_DateTimeFormat(deditoutput.getDownrtxndate(), DateFormatCode.DATE_FORMATTER3));
	}
}