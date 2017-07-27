package com.csii.upp.qrcodeplatform.action.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.qrcodeplatform.action.base.QrCodeAction;
import com.csii.upp.qrcodeplatform.action.constant.DictErrors;
import com.csii.upp.qrcodeplatform.generate.dao.UpperchannelMapper;
import com.csii.upp.qrcodeplatform.generate.dao.model.Upperchannel;

public class CommonCheckAction extends QrCodeAction {

	@Autowired
	private UpperchannelMapper upperchannelMapper; 
	
	@Override
	public void execute(Context context) throws PeException {
		// TODO Auto-generated method stub
		log.debug("交易前检查开始~————————————————————————————");
		
		//检查上游系统的合法性
		checkUpperSys(context);
	}

	private void checkUpperSys(Context context) throws PeException {
		// TODO Auto-generated method stub
		String upperSysNbr = context.getString("UpperSysNbr");
		Upperchannel upperchannel = upperchannelMapper.selectByPrimaryKey(upperSysNbr);
		
		if(upperchannel==null){
			throw new PeException(DictErrors.UPPER_SYS_NBR_INVALID);
		}
	}

}
