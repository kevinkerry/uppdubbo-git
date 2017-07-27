package com.csii.upp.payment.action.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.dao.generate.TestDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Test;
import com.csii.upp.dto.generate.TestExample;
import com.csii.upp.payment.action.PaymentOnlineAction;


/**
 * 
 * @author liguangyu生产测试
 *
 */
public class QueryPayernameAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException{	
		InputPaymentData transData = new InputPaymentData(context.getDataMap());
		String mernbr=transData.getMernbr();
		/*
		 * String mernbr=transData.getMernbr();
		 * Date MerDate = DateUtil.DateFormat_To_Date(context.getData(Dict.MER_TRANS_DATE));
		if(StringUtil.isStringEmpty(mernbr)){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.MER_NBR});
		}
		if(StringUtil.isObjectEmpty(MerDate)){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] {Dict.MER_TRANS_DATE});
		}*/
		BigDecimal transAmt=null;
		String payerName=null;
		TestExample onlinetransExample=new TestExample();
		onlinetransExample.createCriteria().andMernbrEqualTo(mernbr);
			List<Test> onlinetrans=null;
			String transSeqNbr=null;
			try {
				onlinetrans = TestDAO.selectByExample(onlinetransExample);				
				if(onlinetrans.isEmpty()){
					context.setData(Dict.RESP_MESSAGE,"实时查询，无交易信息！");
					context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
					return;
				} 
				else{
					/*transSeqNbr=onlinetrans.get(0).getTransseqnbr();
					InnerfundtransExample example=new InnerfundtransExample();
					example.createCriteria().andUppertransnbrEqualTo(transSeqNbr);
					List<Innerfundtrans> innerfundtrans=null;
					innerfundtrans=InnerfundtransDAO.selectByExample(example);
					if(innerfundtrans.size()>0){
						transAmt=innerfundtrans.get(0).getTransamt();
						payerName=innerfundtrans.get(0).getPayername();
					}else{
						context.setData(Dict.RESP_MESSAGE,"实时查询，无资金交易信息！");
						context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
						return;
					}*/
					transAmt=onlinetrans.get(0).getTransamt();
					payerName=onlinetrans.get(0).getPayeeacctname();
				}
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			
		
			context.setData(Dict.TRANS_AMT, transAmt);
			context.setData(Dict.PAYER_ACCT_NAME, payerName);
		}
		
		
	}

