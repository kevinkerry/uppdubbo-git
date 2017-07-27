package com.csii.upp.fundprocess.action.common;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 输出交易通用处理
 * 
 * @author 徐锦
 *
 */
public class OutputAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		if (!StringUtil.isStringEmpty(context.getString(Dict.OVERALL_TRANS_NBR))) {
			updateTransData(context);
		}
	}

	/**
	 * 更新总交易资金流水表交易状态
	 * 
	 * @param context
	 * @throws PeException
	 */
	private void updateTransData(Context context) throws PeException {
		String respCode = context.getString(Dict.RESP_CODE);
		String transStatus = context.getString(Dict.TRANS_STATUS);
		transStatus=this.getOveralltransstatus(respCode,transStatus);
		context.setData(Dict.TRANS_STATUS,transStatus);
		Overalltrans record = new Overalltrans();
		record.setOveralltransnbr(context.getString(Dict.OVERALL_TRANS_NBR));// 总交易流水号
		record.setCheckdataflag(context.getString(Dict.CHECK_DATA_FLAG));// 对账数据标志
		record.setOveralltransstatus(transStatus);// 总流水交易状态:0-交易成功1-交易失败2-撤销成功3-部分退货4-全部退货5-已受理状态(银联与二代不是实时返回处理结果的)
		record.setReturncode(context.getString(Dict.RESP_CODE));// 交易返回码
		record.setReturnmsg(context.getString(Dict.RESP_MESSAGE));// 返回信息
		try {
			if (OveralltransDAO.updateByPrimaryKeySelective(record) == 0) {
				throw new PeException(DictErrors.REQ_LOST);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION, e);
		}
	}

	/**
	 * 判断交易最终状态
	 * 
	 * @param context
	 * @return
	 */
	private String getOveralltransstatus(String respCode,String  transStatus) {
		if (ResponseCode.SUCCESS.equals(respCode)) {
			return StringUtil.isStringEmpty(transStatus) ? TransStatus.SUCCESS : transStatus;
		} else if (ResponseCode.TIMEOUT.equals(respCode)) {
			return TransStatus.TIMEOUT;
		} else {
			return TransStatus.FAILURE;
		}
	}

}
