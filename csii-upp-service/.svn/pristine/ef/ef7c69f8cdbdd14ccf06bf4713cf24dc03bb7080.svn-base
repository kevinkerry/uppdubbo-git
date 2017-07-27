package com.csii.upp.service.fundprocess.router;

import com.csii.pe.core.PeException;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;

/**
 * 借记交易接口
 * 
 * @author 徐锦
 *
 */
public interface DebitRouter {
	RespSysHead rtdtdr(InputFundData input) throws PeException;
	RespSysHead rtctdr(InputFundData input) throws PeException;
	RespSysHead spctdr(InputFundData input) throws PeException;//普通借记
	RespSysHead rtdtdrReTrave(InputFundData input) throws PeException;
    RespSysHead freddr(InputFundData input) throws PeException;
    RespSysHead rtdtdrReTraveForCheck(InputFundData input) throws PeException;
    RespSysHead revoke(InputFundData input) throws PeException;
    RespSysHead unifiedPayment(InputFundData input) throws PeException;
	RespSysHead queryDownPostDate(InputFundData input) throws PeException;
	RespSysHead recharge(InputFundData input) throws PeException;
}
