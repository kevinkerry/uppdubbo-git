package com.csii.upp.payment.action.query;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.extend.CardphonelimitExtendDAO;
import com.csii.upp.dao.generate.CardphonelimitDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Cardphonelimit;
import com.csii.upp.dto.generate.CardphonelimitExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;
/**
 * 验证手机号卡号错误次数
 * @author qgs
 *
 */
public class QueryCardPhoneAction extends PaymentOnlineAction{

	@Override
	public void execute(Context context) throws PeException {
		String cardNbr = context.getString(Dict.PAYER_ACCT_NBR);
		String phone = context.getString(Dict.PAYER_PHONE_NO);
		Date date = this.getPostDate();
		
		if("0".equals(context.getString(Dict.QUERY_FLAG))){
			Map<String, Object> cardParams = new HashMap<String, Object>();
			cardParams.put("cardnbr", cardNbr);
			cardParams.put("dealDate", date);
			BigDecimal cardSum = CardphonelimitExtendDAO.sumErrorTimes(cardParams);
			if(!StringUtil.isObjectEmpty(cardSum) && cardSum.compareTo(maxTimes) >= 0){
				throw new PeException(DictErrors.CARD_MAX_ERROR);
			}
			Map<String, Object> phoneParams = new HashMap<String, Object>();
			phoneParams.put("mobile", phone);
			phoneParams.put("dealDate", this.getPostDate());
			BigDecimal phoneSum = CardphonelimitExtendDAO.sumErrorTimes(phoneParams);
			if(!StringUtil.isObjectEmpty(phoneSum) && phoneSum.compareTo(maxTimes) >= 0){
				throw new PeException(DictErrors.PHONE_MAX_ERROR);
			}
		}else if("1".equals(context.getString(Dict.QUERY_FLAG))){
			Cardphonelimit cdl = null;
			try {
				cdl = CardphonelimitDAO.selectByPrimaryKey(cardNbr, phone, date);
			} catch (SQLException e) {
				log.error("卡号手机号错误插入数据库异常："+e.getMessage());
			}
			if(cdl != null){
				long errortimes =  cdl.getErrortime()+1;
				cdl.setErrortime(errortimes);
				try {
					CardphonelimitDAO.updateByPrimaryKey(cdl);
				} catch (SQLException e) {
					log.error("卡号手机号错误插入数据库异常："+e.getMessage());
				}
			} else {
				Cardphonelimit record = new Cardphonelimit();
				record.setCardnbr(cardNbr);
				record.setMobile(phone);
				record.setErrortime((long) 1);
				record.setDealdate(date);
				try {
					CardphonelimitDAO.insertSelective(record);
				} catch (SQLException e) {
					log.error("卡号手机号错误插入数据库异常："+e.getMessage());
				}
			}
		} else if("2".equals(context.getString(Dict.QUERY_FLAG))){
			CardphonelimitExample example = new CardphonelimitExample();
			example.createCriteria().andCardnbrEqualTo(cardNbr).andDealdateEqualTo(date);
			try {
				CardphonelimitDAO.deleteByExample(example);
			} catch (SQLException e) {
				log.error("卡号手机号输入正确清空卡号数据库异常："+e.getMessage());
			}
			example.createCriteria().andMobileEqualTo(phone).andDealdateEqualTo(date);
			try {
				CardphonelimitDAO.deleteByExample(example);
			} catch (SQLException e) {
				log.error("卡号手机号输入正确清空手机号数据库异常："+e.getMessage());
			}
		}
	
	}

	private BigDecimal maxTimes;

	public BigDecimal getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(BigDecimal maxTimes) {
		this.maxTimes = maxTimes;
	}
}
