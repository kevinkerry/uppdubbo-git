package com.csii.upp.payment.action.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.TransTypCd;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

public class QuerySignInfoListAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		// 接收信息，打印日志
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		log.info("*******************查询交易明细Start********************");
		log.info("账户:" + input.getPayeracctnbr() + "开始日期：" + context.getString(Dict.BEGIN_DATE) + "结束日期："
				+ context.getString(Dict.END_DATE) + "PageSize：" + context.getString(Dict.PAGE_SIZE));
		// 查询前准备
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date beginDate = null;
		Date endDate = null;
		if(StringUtil.isStringEmpty(context.getString(Dict.BEGIN_DATE))){
			beginDate=new Date();
		}
		if(StringUtil.isStringEmpty(context.getString(Dict.END_DATE))){
			endDate=new Date();
		}
		try {

			beginDate = sdf.parse(context.getString(Dict.BEGIN_DATE));
			endDate = sdf.parse(context.getString(Dict.END_DATE));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		int PageNum = 0;
		int PageSize = 3;
		Map map = new HashMap();

		if (!(StringUtil.isStringEmpty(context.getString(Dict.PAGE_NUM))
				&& StringUtil.isStringEmpty(context.getString(Dict.PAGE_SIZE)))) {
			PageNum = StringUtil.toInt(context.getString(Dict.PAGE_NUM), 0);
			PageSize = Integer.parseInt(context.getString(Dict.PAGE_SIZE));
			
			map.put("startNo", PageNum-1);
			map.put("endNo", PageSize);
		}

		String transTypCd = context.getString(Dict.TRANS_TYP_CD);
		String payerAcctNbr = input.getPayeracctnbr();
		int RecordNumber = 0;
		List userQueryTransList = null;
		map.put(Dict.TRANS_TYP_CD, transTypCd);
		map.put(Dict.BEGIN_DATE, beginDate);
		map.put(Dict.END_DATE, endDate);
		map.put(Dict.PAYER_ACCT_NBR, payerAcctNbr);

		// 查询表
		if (TransTypCd.PAY.equals(transTypCd)) {
			userQueryTransList = OnlineTransExtendDAO.qryTrsDetailResult(map);
			RecordNumber = OnlineTransExtendDAO.countTrans(map);
		} else if (TransTypCd.RETURN.equals(transTypCd)) {
			userQueryTransList = OnlineTransExtendDAO.selectFoisonWithdrawList(map);
			RecordNumber = OnlineTransExtendDAO.selectFoisonWithdrawListCount(map);
		}
		int Totalpage;
		if (RecordNumber % PageSize == 0) {
			Totalpage = RecordNumber / PageSize;
		} else {
			Totalpage = RecordNumber / PageSize + 1;
		}
		context.setData(Dict.TOTAL_PAGE, Totalpage);
		context.setData(Dict.RECORD_NUMBER, RecordNumber);
		context.setData(Dict.PAGE_NUM, PageNum);
		context.setData(Dict.TRANS_LIST, userQueryTransList);
	}
}
