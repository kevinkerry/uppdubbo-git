package com.csii.upp.fundprocess.action.online;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.fundprocess.action.PayOnlineAction;

/**
 * 冲正交易
 * 
 * 
 * @author 李超
 *
 *
 */
public class CorrectAccountAction extends PayOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputFundData input = new InputFundData(context.getDataMap());
		
//		this.getCoreService().correctAccount(input);
	}

}
