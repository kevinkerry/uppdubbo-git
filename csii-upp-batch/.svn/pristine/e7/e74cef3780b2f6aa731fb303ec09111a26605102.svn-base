package com.csii.upp.batch.action;

import com.csii.pe.core.Context;
import com.csii.pe.core.CoreController;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;

public class RunBatchAction extends BaseAction {

	private CoreController coreController;

	public void setCoreController(CoreController coreController) {
		this.coreController = coreController;
	}

	@Override
	public void execute(Context context) throws PeException {
		context.setTransactionId(context.getString("batchName"));
		coreController.execute(context);
	}

}
