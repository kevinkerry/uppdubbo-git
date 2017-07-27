package com.csii.upp.fundprocess.action.online;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 查询交易流水状态
 * @author qgs
 *
 */
public class QueryOverallRtxnStatAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		
		OveralltransExample alltrans = new OveralltransExample();
		alltrans.createCriteria().andUppertransnbrEqualTo(context.getString(Dict.ORIG_UPPER_TRANS_NBR));
		
		List<Overalltrans> list  = null;
		try {
			list  = OveralltransDAO
					.selectByExample(alltrans);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if(list.isEmpty()){
			context.setData(Dict.OVER_ALL_TRANS_STATUS, TransStatus.FAILURE);
		}else{
			context.setData(Dict.OVER_ALL_TRANS_STATUS, list.get(0).getOveralltransstatus());
		}	
	}
}
