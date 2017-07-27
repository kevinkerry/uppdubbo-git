package com.csii.upp.fundprocess.action.demo;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;

public class DemoAction extends BaseAction{

	@Override
	public void execute(Context ctx) throws PeException {
		log.debug("rev request object is "+ctx.getDataMap());
	}

}
