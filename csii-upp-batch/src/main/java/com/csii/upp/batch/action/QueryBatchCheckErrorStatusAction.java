package com.csii.upp.batch.action;

import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.extend.BatchcheckerrorExtendDAO;

public class QueryBatchCheckErrorStatusAction extends BaseAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		List list = BatchcheckerrorExtendDAO.getCheckerrors();
		context.setData("checkerrors", list);
	}

}
