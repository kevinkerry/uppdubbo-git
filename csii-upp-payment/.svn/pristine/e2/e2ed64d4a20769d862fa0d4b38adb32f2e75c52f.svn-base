package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.OnlinesubtransferDAO;
import com.csii.upp.dao.generate.OnlinetransferDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesubtransfer;
import com.csii.upp.dto.generate.OnlinesubtransferExample;
import com.csii.upp.dto.generate.Onlinetransfer;
import com.csii.upp.dto.generate.OnlinetransferExample;
import com.csii.upp.dto.router.fundprocess.RespQueryBatchTransfer4TimeOut;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * 批量转账交易结果查询
 * 
 * @author zhubenle
 * 
 */
public class QueryBatchTransferPaymentAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		String batchNbr = ctx.getString(Dict.BATCH_NO); // 批次号
		String merNbr = ctx.getString(Dict.MER_NBR); // 商户编号
		/*Date merTransDateTime = DateUtil.DateTimeFormat_To_Date(ctx.getString(Dict.MER_TRANS_DATE_TIME),
				DateFormatCode.DATETIME_FORMATTER3);*/
		
		ctx.setData(Dict.TRANS_ID, "BTFR");
		try {
			OnlinetransferExample example = new OnlinetransferExample();
			example.createCriteria().andBatchnbrEqualTo(batchNbr).andMernbrEqualTo(merNbr);
			List<Onlinetransfer> onlinetransfers = OnlinetransferDAO.selectByExample(example);
			if (onlinetransfers.size() > 0 && onlinetransfers.get(0) != null) {
				Onlinetransfer onlinetransfer = onlinetransfers.get(0);
				List<Map<String, Object>> merTransList = new ArrayList<Map<String, Object>>();

				OnlinesubtransferExample subexample = new OnlinesubtransferExample();
				subexample.createCriteria().andTransseqnbrEqualTo(onlinetransfer.getTransseqnbr());
				List<Onlinesubtransfer> onlinesubtransfers = OnlinesubtransferDAO.selectByExample(subexample);

				for (Onlinesubtransfer onlinesubtransfer : onlinesubtransfers) {
					Map<String, Object> map = new HashMap<String, Object>();
					if(TransStatus.PROCESSING.equals(onlinesubtransfer.getTransstatus())){
						InputPaymentData input = new InputPaymentData();
						input.setTransseqnbr(onlinesubtransfer.getSubtransseqnbr());
						RespQueryBatchTransfer4TimeOut respQBTT = (RespQueryBatchTransfer4TimeOut) this.getFDPSService()
								.queryBatchTransfer4TimeOut(input);
						if(TransStatus.SUCCESS.equals(respQBTT.getTransStatus())){
							onlinesubtransfer.setTransstatus(TransStatus.SUCCESS);
						}else if (TransStatus.FAILURE.equals(respQBTT.getTransStatus())) {
							onlinesubtransfer.setTransstatus(TransStatus.FAILURE);
						}
						OnlinesubtransferDAO.updateByPrimaryKey(onlinesubtransfer);
					}
					map.put(Dict.SUB_MERCHANT_ID, onlinesubtransfer.getsubMernbr());
					map.put(Dict.SUB_MERCHANT_NAME, onlinesubtransfer.getSubmername());
					map.put(Dict.SUB_MER_SEQ_NO, onlinesubtransfer.getSubmerseqnbr());
					map.put(Dict.TRANSFER_USAGE, onlinesubtransfer.getTransferusage());
					map.put(Dict.SUB_TRANS_AMT, onlinesubtransfer.getSubtransamt());
					map.put(Dict.SUB_MER_DATE_TIME, DateUtil.Date_To_DateTimeFormat(
							onlinesubtransfer.getSubmerdatetime(), DateFormatCode.DATETIME_FORMATTER3));
					map.put(Dict.ACCT_BANK_NO, onlinesubtransfer.getAcctbanknbr());
					map.put(Dict.ACCT_NAME, onlinesubtransfer.getAcctname());
					map.put(Dict.ACCT_BANK_NO, onlinesubtransfer.getAcctbanknbr());
					map.put(Dict.ACCTNO, onlinesubtransfer.getAcctnbr());
					map.put(Dict.ACCT_TYPE, onlinesubtransfer.getAccttype());
					map.put(Dict.TRANS_SEQ_NO, onlinesubtransfer.getSubtransseqnbr());
					map.put(Dict.TRANS_DATE, DateUtil.Date_To_DateTimeFormat(onlinesubtransfer.getTransdate(),
							DateFormatCode.DATE_FORMATTER3));
					map.put(Dict.TRANS_STATUS, TransStatus.SUCCESS.equals(onlinesubtransfer.getTransstatus()) ? "0001"
							: (TransStatus.FAILURE.equals(onlinesubtransfer.getTransstatus()) ? "0000" : "0002"));
					merTransList.add(map);
				}
				ctx.setData(Dict.MERCHANT_ID, onlinetransfer.getMernbr());
				ctx.setData(Dict.BATCH_NO, onlinetransfer.getBatchnbr());
				ctx.setData(Dict.MER_TF_TYPE, onlinetransfer.getMertftype());
				ctx.setData(Dict.MER_SETT_ACCT_BANK_NO, onlinetransfer.getMersettacctbanknbr());
				ctx.setData(Dict.MER_SETT_ACCT_NAME, onlinetransfer.getMersettacctname());
				ctx.setData(Dict.MER_SETT_ACCTNO, onlinetransfer.getMersettacctnbr());
				ctx.setData(Dict.MER_SETT_ACCT_TYPE, onlinetransfer.getMersettaccttype());
				ctx.setData(Dict.TOTAL_NUM, onlinetransfer.getTotalnum());
				ctx.setData(Dict.CURRENCY_CD, onlinetransfer.getCurrency());
				ctx.setData(Dict.MER_TRANS_LIST, merTransList);
			}else {
				throw new PeException(DictErrors.QUERY_ORIG_NOT_EXISTS);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}
}
