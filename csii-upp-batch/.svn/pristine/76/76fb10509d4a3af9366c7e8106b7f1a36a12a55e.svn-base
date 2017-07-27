package com.csii.upp.batch.action;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.generate.QueDAO;
import com.csii.upp.dto.generate.Que;
import com.csii.upp.dto.generate.QueExample;
import com.csii.upp.supportor.DefaultSupportor;

public class QueListAction extends BaseAction{

	@Override
	public void execute(Context ctx) throws PeException {

		QueExample example = new QueExample();
		example.createCriteria();
		example.setOrderByClause("quenbr");
		try {
			
			List<Que> queList = QueDAO.selectByExample(example);
			ctx.setData("queAllList", DefaultSupportor.getObjectMapMarshaller().marshall(queList));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("error",e);
		}
	}

}
