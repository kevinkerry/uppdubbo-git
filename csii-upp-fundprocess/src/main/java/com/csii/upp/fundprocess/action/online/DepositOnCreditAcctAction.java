package com.csii.upp.fundprocess.action.online;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.fundprocess.action.PayOnlineAction;
import com.csii.upp.service.fundprocess.HvpsService;
import com.csii.upp.service.fundprocess.IbpsService;

/**
 * 来账挂账处理 做挂账成功后直接发二代退汇并做电子账户解挂
 * 
 * @author xujin
 * 
 */
public class DepositOnCreditAcctAction extends PayOnlineAction {

	@Override
	public void execute(Context arg0) throws PeException {
		InputFundData input = new InputFundData(arg0.getDataMap());
		OveralltransExample example = new OveralltransExample();
		example.createCriteria()
				.andUppertransnbrEqualTo(input.getOriguppertransnbr())
				.andUppertransdateEqualTo(input.getOriguppertransdate())
				.andUppersysnbrEqualTo(input.getOriguppersysnbr());
		try {
			List<Overalltrans> Overalltranss = OveralltransDAO
					.selectByExample(example);
			if (Overalltranss == null || Overalltranss.size() < 1) {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}
			Overalltrans origOveralltrans = Overalltranss.get(0);
			// 如果原交易处理成功,无法处理该交易
			if (TransStatus.SUCCESS.equals(origOveralltrans
					.getOveralltransstatus())) {
				throw new PeException(DictErrors.ORIG_TRANS_SUCCESS);
			}
			// 获得内部原交易信息
			List<Innerfundtrans> origfundRtxns = InnerfundtransExtendDAO
					.getInnerfundtransByOveralltransNbr(origOveralltrans
							.getOveralltransnbr());
			Innerfundtrans origfundRtxn = origfundRtxns
					.get(origfundRtxns.size() > 1 ? origfundRtxns.size() - 1
							: 0);
			
			//查询二代
			input.getInputDataByOrigInnerfundtrans(origfundRtxn, false);
			input.setCheckdataflag(origfundRtxn.getFundchannelcode());
			arg0.setData(Dict.CHECK_CARD_PWD_FLAG, input.getCheckdataflag());
			RespSysHead deposit = getDBankService(input)
					.dodepositOnCreditAcctForCheck(input);
			if (deposit.isSuccess()) {
				// 退汇
				RespSysHead retrave = null;
				if (FundChannelCode.HVPS.equals(origfundRtxn
						.getFundchannelcode())) {
					HvpsService hvspService = (HvpsService) this
							.getRouterService(FundChannelCode.HVPS
									.toLowerCase());
					retrave = hvspService.STHPDrReTraveForCheck(input);
				} else if (FundChannelCode.BEPS.equals(origfundRtxn
						.getFundchannelcode())) {
					IbpsService ibpsService = (IbpsService) this
							.getRouterService(FundChannelCode.BEPS
									.toLowerCase());
					retrave = ibpsService.STIBDrReTraveForCheck(input);
				}
				// 解挂
				if (retrave.isSuccess()) {
					RespSysHead retRemit = getDBankService(input)
							.doReturnRemittance(input);
					if (retRemit.isTimeout()) {
						throw new PeException(DictErrors.QUERY_TIME_OUT);
					} else if (retRemit.isFailure()) {
						throw new PeException(DictErrors.TRANS_EXCEPTION);
					}
				} else if (retrave.isTimeout()) {
					throw new PeException(DictErrors.QUERY_TIME_OUT);
				} else if (retrave.isFailure()) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
			}
			if (deposit.isTimeout()) {
				throw new PeException(DictErrors.QUERY_TIME_OUT);
			} else if (deposit.isFailure()) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
