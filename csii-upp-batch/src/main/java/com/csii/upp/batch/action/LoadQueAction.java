package com.csii.upp.batch.action;


import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.generate.QueDAO;
import com.csii.upp.dto.generate.Que;
import com.csii.upp.dto.generate.QueExample;

public class LoadQueAction extends BaseAction {

	@Override
	public void execute(Context ctx) throws PeException {
		QueExample example = new QueExample();
		example.createCriteria().andQuenbrEqualTo(ctx.getLong("quenbr"));
		example.setOrderByClause("quenbr");
		try {
			List<Que> queList = QueDAO.selectByExample(example);
			for (Que queDetail : queList) {
				ctx.setData("quenbr", queDetail.getQuenbr());
				ctx.setData("quetypcd", queDetail.getQuetypcd());
				ctx.setData("quedesc", queDetail.getQuedesc());
				ctx.setData("datelastmaint", queDetail.getDatelastmaint());
				ctx.setData("datelastrun", queDetail.getDatelastrun());
				ctx.setData("startdatetime", queDetail.getStartdatetime());
				ctx.setData("stopdatetime", queDetail.getStopdatetime());
				ctx.setData("seqnbr", queDetail.getSeqnbr());
				ctx.setData("autorun", queDetail.getAutorun());
				ctx.setData("cornexperess", queDetail.getCornexperess());
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

	}

}
