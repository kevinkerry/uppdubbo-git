package com.csii.upp.fundprocess.action.common;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.CallbacktransregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Callbacktransreg;
import com.csii.upp.dto.generate.CallbacktransregExample;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * 回调交易通用处理
 * 
 * @author 徐锦
 * 
 */
public class CallBackAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		insertTransData(context);
//		validateTransData(context);
	}

	/**
	 * 插入交易流水
	 * 
	 * @param context
	 * @throws PeException 
	 */
	private void insertTransData(Context context) throws PeException {
		try {
			Callbacktransreg record = new Callbacktransreg();
			record.setMernbr(context.getString("merId"));
			record.setOrdernbr(context.getString("orderId"));
	    	record.setCallbacktransnbr(IDGenerateFactory.generateSeqId());
			CallbacktransregDAO.insert(record);
			context.setData(Dict.CALL_BACK_TRANS_REG_ID, record.getCallbacktransnbr());
		} catch (Exception e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION,e);
		}
	}

	/**
	 * 流水信息校验，防止重复交易
	 * 
	 * @param context
	 * @throws PeException 
	 */
	private void validateTransData(Context context) throws PeException {
		CallbacktransregExample example = new CallbacktransregExample();

		example.createCriteria()
				.andMernbrEqualTo(context.getString("merId"))
				.andOrdernbrEqualTo(context.getString("orderId"));
		try {
			List<Callbacktransreg> callbackrtxns = CallbacktransregDAO
					.selectByExample(example);
			if (callbackrtxns.size() > 1) {
				throw new PeException(DictErrors.DUPLICATED_CALL_BACK_REQ);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.DATABASE_EXCEPTION ,e);
		}
	}
}