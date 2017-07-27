package com.csii.upp.service.fundprocess.router;

import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;

public interface QueryRouter {
	public void queryRtxnState(InputFundData input) throws PeException;
	// 通过收款人账号查询电子账户信息
	public RespSysHead queryPayeeAcctInfo(InputFundData input) throws PeException;
	// 通过付款人账号查询电子账户信息
	public RespSysHead queryPayerAcctInfo(InputFundData input) throws PeException;
}
