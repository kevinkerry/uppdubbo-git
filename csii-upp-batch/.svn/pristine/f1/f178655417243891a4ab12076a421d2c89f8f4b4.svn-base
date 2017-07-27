package com.csii.upp.batch.appl;

import java.util.Date;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.dao.extend.SysinfoExtendDAO;

public class CutOffAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		SysinfoExtendDAO.updateUppDate(new Date());
	}

}
