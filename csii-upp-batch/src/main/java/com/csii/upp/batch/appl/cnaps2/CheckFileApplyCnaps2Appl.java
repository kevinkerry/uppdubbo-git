package com.csii.upp.batch.appl.cnaps2;

import java.util.Map;

import com.csii.upp.batch.appl.base.BaseApplyFileAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dto.generate.Innercheckapply;

public class CheckFileApplyCnaps2Appl extends BaseApplyFileAppl {

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return FundChannelCode.CNAPS2;
	}

	/* (non-Javadoc)
	 * @see com.csii.upp.batch.appl.base.BaseApplyFileAppl#applyCheckFile(com.csii.upp.dto.generate.Innercheckapply)
	 */
	@Override
	protected void applyCheckFile(Innercheckapply innercheckapply, Map<String, Object> argMaps) {
		// TODO 自动生成的方法存根
		
	}
}
