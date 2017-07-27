package com.csii.upp.service.fundprocess;

import com.csii.pe.core.PeException;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.RouterResult;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.ReqInnerAcctToCard;
import com.csii.upp.dto.router.core.RespInnerAcctToCard;
import com.csii.upp.dto.router.unionpay.RespRecharge;
import com.csii.upp.service.fundprocess.router.CreditRouter;
import com.csii.upp.service.fundprocess.router.DebitRouter;
import com.csii.upp.service.fundprocess.router.QueryRouter;

/**
 * 中金支付服务类
 * 
 * @author lcy
 * 
 */
public class CiccService extends RouterService implements DebitRouter,CreditRouter, QueryRouter{
	
	public RespSysHead ciccpay(InputFundData input) throws PeException {
		// 发送充值请求
		RespInnerAcctToCard output = this.handleFundRtxn(InnerRtxnTyp.CoreInnerAcctToCard, input,
				new ReqInnerAcctToCard(input), RespInnerAcctToCard.class);
		if (output.isFailure()) {
			// 失败时直接返回
			this.formatException(output);
		} else if (output.isTimeout()) {
			this.formatException(output);
		}
		return output;
	}

	@Override
	public RespSysHead rtdtcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtctcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead spctcr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtcrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead refoundTrans(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead virAcctWithdrawl(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead withdrawal(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtdr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtctdr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead spctdr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTrave(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead freddr(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead rtdtdrReTraveForCheck(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead revoke(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespSysHead queryDownPostDate(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
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
	public RespSysHead recharge(InputFundData input) throws PeException {
		RespRecharge output = new RespRecharge();
		input.setTranscode("cicc");
		insertInnerfundtrans(input);
		output.setRtxnstate("0");
		output.setReturnmsg("ok");
		output.setResult(RouterResult.SUCCESS);
		updateInnerfundtrans(input, output);	
		return output;
	}

	@Override
	public RespSysHead unifiedPayment(InputFundData input) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}
}
