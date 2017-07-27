package com.csii.upp.payment.event.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.generate.OnlinesubtransferDAO;
import com.csii.upp.dao.generate.OnlinetransferDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesubtransfer;
import com.csii.upp.dto.generate.Onlinetransfer;
import com.csii.upp.dto.router.fundprocess.RespBatchTransfer;
import com.csii.upp.event.EventHandler;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

@SuppressWarnings("rawtypes")
public class BatchTransferHandler implements EventHandler<BatchTransferEvent> {
	protected static Log log = LogFactory.getLog(BatchTransferHandler.class);

	@SuppressWarnings("unchecked")
	@Override
	public void handler(BatchTransferEvent event) {
		try {
			Map paramMap = event.getParamMap();
			List<Map> merTransList = (List<Map>) paramMap.get(Dict.MER_TRANS_LIST);
			InputPaymentData paymentData = new InputPaymentData(paramMap);
			paymentData.setPayeracctname(StringUtil.parseObjectToString(paramMap.get(Dict.SETTLE_ACCT_NAME)));
			paymentData.setPayeracctnbr(StringUtil.parseObjectToString(paramMap.get(Dict.SETTLE_ACCT_NBR)));
			paymentData.setPayeraccttypcd(StringUtil.parseObjectToString(paramMap.get(Dict.MER_SETT_ACCT_TYPE)));
			paymentData.setTranserDeptNbr(StringUtil.parseObjectToString(paramMap.get(Dict.TRANSER_DEPT_NBR)));
			/*OnlinetransferExample example = new OnlinetransferExample();
			example.createCriteria().andBatchnbrEqualTo(StringUtil.parseObjectToString(paramMap.get(Dict.BATCH_NO)));
			int count = OnlinetransferDAO.countByExample(example);
			if (count > 0) {
				throw new PeException("批量转账批次号重复异常");
			}*/

			Onlinetransfer onlinetransfer = new Onlinetransfer();
			onlinetransfer.setTranscode(paymentData.getTranscode());
			onlinetransfer.setTranstypcd(TransTypCd.WTH);
			onlinetransfer.setBatchnbr(StringUtil.parseObjectToString(paramMap.get(Dict.BATCH_NO)));
			onlinetransfer.setTransseqnbr(IDGenerateFactory.generateRtxnNbr());
			onlinetransfer.setChannelnbr(paymentData.getChannelnbr());
			onlinetransfer.setCurrency(StringUtil.parseObjectToString(paramMap.get(Dict.CURRENCY_CD)));
			onlinetransfer.setMernbr(paymentData.getMernbr());
			onlinetransfer.setMerseqnbr(paymentData.getMerseqnbr());
			onlinetransfer.setMersettacctbanknbr(StringUtil.parseObjectToString(paramMap.get(Dict.MER_SETT_ACCT_BANK_NO)));
			onlinetransfer.setMertftype(StringUtil.parseObjectToString(paramMap.get(Dict.MER_TF_TYPE)));
			onlinetransfer.setMersettacctname(StringUtil.parseObjectToString(paramMap.get(Dict.SETTLE_ACCT_NAME)));
			onlinetransfer.setMersettacctnbr(StringUtil.parseObjectToString(paramMap.get(Dict.SETTLE_ACCT_NBR)));
			onlinetransfer.setMersettaccttype(StringUtil.parseObjectToString(paramMap.get(Dict.MER_SETT_ACCT_TYPE)));
			onlinetransfer.setMertransdatetime(DateUtil.DateTimeFormat_To_Date(paramMap.get(Dict.MER_TRANS_DATE_TIME)));
			//onlinetransfer.setTotalnum(Long.parseLong(StringUtil.parseObjectToString(paramMap.get(Dict.TOTAL_NUM))));
			onlinetransfer.setTotaltransamt(paymentData.getTransamt());
			onlinetransfer.setTransdate(DateUtil.DateFormat_To_Date(new Date()));
			onlinetransfer.setTranstime(DateUtil.DateTimeFormat_To_Date(new Date()));
			onlinetransfer.setTransstatus(TransStatus.SUCCESS);
			OnlinetransferDAO.insertSelective(onlinetransfer);
			
			for (Map map : merTransList) {
				List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
				Map<String, String> pm = new HashMap<String, String>();
				pm.put(Dict.PAYEE_BANK_NBR, StringUtil.parseObjectToString(map.get(Dict.ACCT_BANK_NO)));
				pm.put(Dict.PAYEE_ACCT_NBR, StringUtil.parseObjectToString(map.get(Dict.ACCTNO)));
				pm.put(Dict.PAYEE_ACCT_NAME, StringUtil.parseObjectToString(map.get(Dict.ACCT_NAME)));
				pm.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(map.get(Dict.SUB_TRANS_AMT)));
				payeeAcctList.add(pm);
				paymentData.setPayeeAcctList(payeeAcctList);
				paymentData.setTransamt(new BigDecimal(StringUtil.parseObjectToString(map.get(Dict.SUB_TRANS_AMT))));
				paymentData.setTransnbr(IDGenerateFactory.generateRtxnNbr());
				paymentData.setTransseqnbr(paymentData.getTransnbr());

				RespBatchTransfer respBt = (RespBatchTransfer) event.getFdpsService().batchTransfer(paymentData);

				Onlinesubtransfer onlinesubtransfer = new Onlinesubtransfer();
				onlinesubtransfer.setTranscode(onlinetransfer.getTranscode());
				onlinesubtransfer.setTranstypcd(TransTypCd.WTH);
				onlinesubtransfer.setTransamt(onlinesubtransfer.getTransamt());
				onlinesubtransfer.setTransseqnbr(onlinetransfer.getTransseqnbr());
				onlinesubtransfer.setSubtransseqnbr(paymentData.getTransseqnbr());
				onlinesubtransfer.setAcctbanknbr(StringUtil.parseObjectToString(map.get(Dict.ACCT_BANK_NO)));
				onlinesubtransfer.setAcctname(StringUtil.parseObjectToString(map.get(Dict.ACCT_NAME)));
				onlinesubtransfer.setAcctnbr(StringUtil.parseObjectToString(map.get(Dict.ACCTNO)));
				onlinesubtransfer.setAccttype(StringUtil.parseObjectToString(map.get(Dict.ACCT_TYPE)));
				onlinesubtransfer.setSubmernbr(StringUtil.parseObjectToString(map.get(Dict.SUB_MERCHANT_ID)));
				onlinesubtransfer.setSubmername(StringUtil.parseObjectToString(map.get(Dict.SUB_MERCHANT_NAME)));
				onlinesubtransfer.setTransferusage(StringUtil.parseObjectToString(map.get(Dict.TRANSFER_USAGE)));
				onlinesubtransfer.setSubmerseqnbr(StringUtil.parseObjectToString(map.get(Dict.SUB_MER_SEQ_NO)));
				onlinesubtransfer
						.setSubtransamt(new BigDecimal(StringUtil.parseObjectToString(map.get(Dict.SUB_TRANS_AMT))));
				onlinesubtransfer.setMerseqnbr(onlinetransfer.getMerseqnbr());
				onlinesubtransfer
						.setSubmerdatetime(DateUtil.DateTimeFormat_To_Date(paramMap.get(Dict.SUB_MER_DATE_TIME)));
				onlinesubtransfer.setTransstatus(TransStatus.PROCESSING);
				onlinesubtransfer.setTranstime(DateUtil.DateTimeFormat_To_Date(new Date()));
				onlinesubtransfer.setTranstime(DateUtil.DateFormat_To_Date(new Date()));
				OnlinesubtransferDAO.insertSelective(onlinesubtransfer);
			}
		} catch (Exception e) {
			log.error("BatchTransferHandler ERROR", e);
		}
	}

	@Override
	public Class<BatchTransferEvent> getAcceptedEventType() {
		return BatchTransferEvent.class;
	}

}
