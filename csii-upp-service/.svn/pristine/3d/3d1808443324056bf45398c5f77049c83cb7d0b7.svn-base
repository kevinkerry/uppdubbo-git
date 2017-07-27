package com.csii.upp.service.fundprocess.router;

import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;


/**
 * 贷记交易接口
 * 
 * @author 徐锦
 *
 */
public interface CreditRouter {
	RespSysHead rtdtcr(InputFundData input) throws PeException;
	RespSysHead rtctcr(InputFundData input) throws PeException;
	RespSysHead spctcr(InputFundData input) throws PeException;
	RespSysHead rtdtcrReTrave(InputFundData input) throws PeException;
    RespSysHead revoke(InputFundData input) throws PeException;
	RespSysHead refoundTrans(InputFundData input) throws PeException;
	RespSysHead virAcctWithdrawl(InputFundData input) throws PeException ;
	RespSysHead withdrawal(InputFundData input) throws PeException ;
}
