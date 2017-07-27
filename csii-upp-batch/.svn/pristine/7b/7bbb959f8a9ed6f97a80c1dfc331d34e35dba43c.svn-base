package com.csii.upp.batch.action;


import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.generate.QueDAO;
import com.csii.upp.dto.generate.Que;
import com.csii.upp.dto.generate.QueExample;

public class EditQueAction extends BaseAction {

	@Override
	public void execute(Context ctx) throws PeException {		
		try {
			QueExample example = new QueExample();
			example.createCriteria()
			.andQuenbrEqualTo(ctx.getLong("quenbr"));
			Que record = new Que();
			record.setQuenbr(ctx.getLong("quenbr"));
			record.setQuedesc(ctx.getString("quedesc"));
			record.setAutorun(ctx.getString("autorun"));
			record.setCornexperess(ctx.getString("cornexperess"));
			QueDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
//		EditQueRequest eqReq = new EditQueRequest();
//
//		eqReq.setQuenbr(ctx.getLong("quenbr"));
//		eqReq.setQuetypcd(ctx.getString("quetypcd"));
//		eqReq.setQuedesc(ctx.getString("quedesc"));
//		if(ctx.getString("datelastrun") != null){
//			eqReq.setDatelastrun(DateUtil.DateTimeFormat_To_Date(ctx.getString("datelastrun")));
//		}else{
//			eqReq.setDatelastrun(null);
//		}
//		if(ctx.getString("startdatetime") != null){
//			eqReq.setStartdatetime(DateUtil.DateTimeFormat_To_Date(ctx.getString("startdatetime")));
//		}else{
//			eqReq.setStartdatetime(null);
//		}
//		if(ctx.getString("stopdatetime") != null){
//			eqReq.setStopdatetime(DateUtil.DateTimeFormat_To_Date(ctx.getString("stopdatetime")));
//		}else{
//			eqReq.setStopdatetime(null);
//		}
//		eqReq.setSeqnbr(ctx.getLong("seqnbr"));
//		eqReq.setAutorun(ctx.getString("autorun"));
//		eqReq.setCornexperess(ctx.getString("cornexperess"));
//		
//		callService(ServiceDict.EditQueService, eqReq);
	}

}
