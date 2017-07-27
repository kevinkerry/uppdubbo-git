package com.csii.upp.payment.action.post;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransferDAO;
import com.csii.upp.dao.generate.OnlinetransferDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinesubtransfer;
import com.csii.upp.dto.generate.Onlinetransfer;
import com.csii.upp.dto.generate.OnlinetransferExample;
import com.csii.upp.dto.router.fundprocess.RespBatchTransfer;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 单笔代发
 * 
 * @author qgs
 * 
 */
public class SGPMAction extends PaymentOnlineAction {

	@Override
	public void execute(Context ctx) throws PeException {
		InputPaymentData inputdata = new InputPaymentData(ctx.getDataMap());

		if (!StringUtil.isStringEmpty(inputdata.getSubMerchantId())) {
			Meracctinfo meracct;
			try {
				meracct = MeracctinfoDAO.selectByPrimaryKey(inputdata.getSubMerchantId());
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
			if (meracct != null) {
				String setAcct = meracct.getSettleacctnbr();
				ctx.setData(Dict.TRANSER_DEPT_NBR, meracct.getMerdevelopdeptnbr());
				ctx.setData("SetAcct", setAcct);
				ctx.setData("SetAcctName", meracct.getSettleacctname());
			} else {
				throw new PeException("商户账务信息不存在");
			}
		} else {
			throw new PeException("商户信息不存在");
		}

		try {
			InputPaymentData paymentData = new InputPaymentData(ctx.getDataMap());
			paymentData.setPayeracctname(StringUtil.parseObjectToString(ctx.getData("SetAcctName")));
			paymentData.setPayeracctnbr(StringUtil.parseObjectToString(ctx.getData("SetAcct")));
			paymentData.setTranserDeptNbr(StringUtil.parseObjectToString(ctx.getData(Dict.TRANSER_DEPT_NBR)));
			OnlinetransferExample example = new OnlinetransferExample();
			example.createCriteria().andBatchnbrEqualTo(StringUtil.parseObjectToString(ctx.getData(Dict.BATCH_NO)));
			int count = OnlinetransferDAO.countByExample(example);
			if (count > 0) {
				throw new PeException("批量转账批次号重复异常");
			}

			Onlinetransfer onlinetransfer = new Onlinetransfer();
			onlinetransfer.setTranscode(paymentData.getTranscode());
			onlinetransfer.setTranstypcd(TransTypCd.WTH);
			onlinetransfer.setBatchnbr(StringUtil.parseObjectToString(ctx.getData(Dict.BATCH_NO)));
			onlinetransfer.setTransseqnbr(IDGenerateFactory.generateRtxnNbr());
			onlinetransfer.setChannelnbr(paymentData.getChannelnbr());
			onlinetransfer.setCurrency(StringUtil.parseObjectToString(ctx.getData(Dict.CURRENCY_CD)));
			onlinetransfer.setMernbr(paymentData.getMernbr());
			onlinetransfer.setMerseqnbr(paymentData.getMerseqnbr());
			onlinetransfer
					.setMersettacctbanknbr(StringUtil.parseObjectToString(ctx.getData(Dict.MER_SETT_ACCT_BANK_NO)));
			onlinetransfer.setMertftype(StringUtil.parseObjectToString(ctx.getData(Dict.MER_TF_TYPE)));
			onlinetransfer.setMersettacctname(StringUtil.parseObjectToString(paymentData.getPayeracctname()));
			onlinetransfer.setMersettacctnbr(StringUtil.parseObjectToString(paymentData.getPayeracctnbr()));
			onlinetransfer.setMersettaccttype(StringUtil.parseObjectToString(ctx.getData(Dict.MER_SETT_ACCT_TYPE)));
			onlinetransfer.setMertransdatetime(DateUtil.DateTimeFormat_To_Date(ctx.getData(Dict.MER_TRANS_DATE_TIME)));
			onlinetransfer.setTotaltransamt(paymentData.getTransamt());
			onlinetransfer.setTransdate(DateUtil.DateFormat_To_Date(new Date()));
			onlinetransfer.setTranstime(DateUtil.DateTimeFormat_To_Date(new Date()));
			onlinetransfer.setTransstatus(TransStatus.SUCCESS);
			OnlinetransferDAO.insertSelective(onlinetransfer);

			List<Map<String, String>> payeeAcctList = new ArrayList<Map<String, String>>();
			Map<String, String> pm = new HashMap<String, String>();
			pm.put(Dict.PAYEE_BANK_NBR, StringUtil.parseObjectToString(ctx.getData(Dict.ACCT_BANK_NO)));
			pm.put(Dict.PAYEE_ACCT_NBR, StringUtil.parseObjectToString(ctx.getData(Dict.SETTLE_ACCT_NBR)));
			pm.put(Dict.PAYEE_ACCT_NAME, StringUtil.parseObjectToString(ctx.getData(Dict.SETTLE_ACCT_NAME)));
			pm.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(ctx.getData(Dict.SUB_TRANS_AMT)));
			payeeAcctList.add(pm);
			paymentData.setPayeeAcctList(payeeAcctList);
			paymentData.setTransamt(new BigDecimal(StringUtil.parseObjectToString(ctx.getData(Dict.TRANS_AMT))));
			paymentData.setTransnbr(IDGenerateFactory.generateRtxnNbr());
			paymentData.setTransseqnbr(paymentData.getTransnbr());

			RespBatchTransfer respBt = (RespBatchTransfer) getFDPSService().batchTransfer(paymentData);

			Onlinesubtransfer onlinesubtransfer = new Onlinesubtransfer();
			onlinesubtransfer.setTranscode(onlinetransfer.getTranscode());
			onlinesubtransfer.setTranstypcd(TransTypCd.WTH);
			onlinesubtransfer.setTransamt(onlinesubtransfer.getTransamt());
			onlinesubtransfer.setTransseqnbr(onlinetransfer.getTransseqnbr());
			onlinesubtransfer.setSubtransseqnbr(paymentData.getTransseqnbr());
			onlinesubtransfer.setAcctbanknbr(StringUtil.parseObjectToString(ctx.getData(Dict.ACCT_BANK_NO)));
			onlinesubtransfer.setAcctname(StringUtil.parseObjectToString(ctx.getData(Dict.SETTLE_ACCT_NAME)));
			onlinesubtransfer.setAcctnbr(StringUtil.parseObjectToString(ctx.getData(Dict.SETTLE_ACCT_NBR)));
			onlinesubtransfer.setAccttype(StringUtil.parseObjectToString(ctx.getData(Dict.ACCT_TYPE)));
			onlinesubtransfer.setSubmernbr(StringUtil.parseObjectToString(ctx.getData(Dict.SUB_MERCHANT_ID)));
			onlinesubtransfer.setSubmername(StringUtil.parseObjectToString(ctx.getData(Dict.SUB_MERCHANT_NAME)));
			onlinesubtransfer.setTransferusage(StringUtil.parseObjectToString(ctx.getData(Dict.TRANSFER_USAGE)));
			onlinesubtransfer.setSubmerseqnbr(StringUtil.parseObjectToString(ctx.getData(Dict.SUB_MER_SEQ_NO)));
			onlinesubtransfer
					.setSubtransamt(new BigDecimal(StringUtil.parseObjectToString(ctx.getData(Dict.TRANS_AMT))));
			onlinesubtransfer.setMerseqnbr(onlinetransfer.getMerseqnbr());
			onlinesubtransfer.setSubmerdatetime(DateUtil.DateTimeFormat_To_Date(inputdata.getMertransdatetime()));
			onlinesubtransfer.setTransstatus(TransStatus.SUCCESS.equals(respBt.getRtxnstate())?TransStatus.SUCCESS:TransStatus.FAILURE);
			onlinesubtransfer.setTranstime(DateUtil.DateTimeFormat_To_Date(new Date()));
			onlinesubtransfer.setTranstime(DateUtil.DateFormat_To_Date(new Date()));
			OnlinesubtransferDAO.insertSelective(onlinesubtransfer);

		} catch (Exception e) {
			log.error("BatchTransferHandler ERROR", e);
		}

	}
}
