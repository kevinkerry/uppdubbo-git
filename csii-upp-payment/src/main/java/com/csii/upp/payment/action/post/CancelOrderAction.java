package com.csii.upp.payment.action.post;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.OnlineorderinfoExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.OnlinetranshistExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

public class CancelOrderAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		List<Onlineorderinfo> order = null;
		OnlineorderinfoExample example = new OnlineorderinfoExample();
		example.createCriteria().andMerseqnbrEqualTo(input.getOrigmerseqnbr())
			.andMernbrEqualTo(input.getMernbr());  
		
		try {
			order = OnlineorderinfoDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if(order.isEmpty()){
			// 订单信息为空，报错
			log.info("****************OrderInfo 订单不存在**********");
			throw new PeException(DictErrors.ORDER_NOT_EXIST);	 
		}else if(order.size()!=1){
			log.info("****************OrderInfo 订单错误**********");
			throw new PeException(DictErrors.ORDER_ERROR);	
		}else{
			List<Onlinetrans> trans = null;
			List<Onlinetranshist> transhist = null;
			OnlinetransExample example1 = new OnlinetransExample();
			example1.createCriteria().andMerseqnbrEqualTo(input.getOrigmerseqnbr()); 
			try {
				trans = OnlinetransDAO.selectByExample(example1);
			} catch (SQLException e1) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if(trans.isEmpty()){
				OnlinetranshistExample example2 = new OnlinetranshistExample();
				example2.createCriteria().andMerseqnbrEqualTo(input.getOrigmerseqnbr()); 
					try {
						transhist = OnlinetranshistDAO.selectByExample(example2);
					} catch (SQLException e) {
						throw new PeException(DictErrors.TRANS_EXCEPTION);
					}	
			}
			Onlineorderinfo orderInfo = order.get(0);
			String payStatus=orderInfo.getPaystatus();
			if(PayStatus.PAY_STATUS_OK.equals(payStatus)){
				throw new PeException(DictErrors.ORDER_INFO_OK);
			}
			if(PayStatus.PAY_STATUS_HAND.equals(payStatus)){
				if(trans.isEmpty()&&transhist.isEmpty()){					
				}else{
					throw new PeException(DictErrors.ORDER_INFO_HANDLE);	
				} 
			}
			if(PayStatus.PAY_STATUS_NO.equals(payStatus) || PayStatus.PAY_STATUS_FAIL.equals(payStatus) || PayStatus.PAY_STATUS_CANCEL.equals(payStatus)||(trans.isEmpty()&&transhist.isEmpty())){
				if(0!=orderInfo.getTransamt().compareTo(StringUtil.parseBigDecimal(context.getString(Dict.ORIG_TRANS_AMT)))){
					throw new PeException(DictErrors.TRANSAMT_DISCORD);
				}else{
					Onlineorderinfo orderinfo = new Onlineorderinfo();
					orderinfo.setPaystatus( PayStatus.PAY_STATUS_CANCEL);
					orderinfo.setMemo1(context.getString(Dict.MSG_EXT));
					orderinfo.setMemo2(context.getString(Dict.REASON_DESC));
					try {
						OnlineorderinfoDAO.updateByExampleSelective(orderinfo, example);
					} catch (SQLException e) {
						throw new PeException(DictErrors.TRANS_EXCEPTION);
					}
				
				}
			}
		}
	}

}
