package com.csii.upp.service.fundprocess;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.pbc.ReqPbcCheckFile;
import com.csii.upp.dto.router.pbc.ReqPbcCollectionRtxn;
import com.csii.upp.dto.router.pbc.RespPbcCheckFile;
import com.csii.upp.dto.router.pbc.RespPbcCollectionRtxn;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;
/**
 * 人行渠道服务
 * @author WHD
 *
 */
public class PbcService extends RouterService implements DebitRouter, QueryRouter{

	@Override
	public RespSysHead rtdtdr(InputFundData input) throws PeException {
		//代收账户协议查询-行内集中代收付系统查询	QueryCollectionAgreement
		RespSysHead output = null;
//		RespQueryCollectionAgreement queryoutput = this.handleNonFundRtxn(
//				new ReqQueryCollectionAgreement(input), RespQueryCollectionAgreement.class);
//		output = queryoutput;
		RespPbcCollectionRtxn collectionoutput = null;
//	 	if(queryoutput.isSuccess()){
			  collectionoutput = this.handleFundRtxn(InnerRtxnTyp.PbcCollectionRtxn,
					input, new ReqPbcCollectionRtxn(input), RespPbcCollectionRtxn.class);
			  output = collectionoutput;
			if(collectionoutput.isFailure()){
				this.formatException(collectionoutput);
			}
//		 }
//	 	else
//	 	{
//	 		throw new PayException(queryoutput,
//					BusinessCode.PBCRTDT_DR_FAILEDNEEDREGIS);
//	 		
//	 	}
		return output;
	}

	@Override
	public RespSysHead rtctdr(InputFundData input) throws PeException {
	
		return null;
	}

	@Override
	public RespSysHead spctdr(InputFundData input) throws PeException {
		
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RespPbcCheckFile pbcCheckFile(InputFundData input) throws PeException{
		RespPbcCheckFile output = this.handleNonFundRtxn(new ReqPbcCheckFile(input),
				RespPbcCheckFile.class);
		if (output.isFailure()) {
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		return null;
	}

	@Override
	public void queryRtxnState(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RespSysHead queryPayeeAcctInfo(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryPayerAcctInfo(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input)
			throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryDownPostDate(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead recharge(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}


}
