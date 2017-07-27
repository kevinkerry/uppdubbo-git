package com.csii.upp.batch.action;

import java.util.Date;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dict.Dict;

/**
 * 资金平台日切交易 根据传入的postDate进行日切
 * 
 * @author 徐锦
 * 
 */
public class CutOffAction extends BaseAction {
	@Override
	public void execute(final Context context) throws PeException {
		SysinfoExtendDAO.updateUppDate((Date) context
				.getData(Dict.POST_DATE));

	}

}
