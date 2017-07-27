package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.AuthorityDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Authority;
import com.csii.upp.dto.generate.AuthorityExample;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 查询二维码信息
 * 
 * @author wy
 * 
 */
public class QueryAuthAction extends PaymentOnlineAction {


	@SuppressWarnings("unchecked")
	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData inputData = new InputPaymentData(context.getDataMap());
		log.info(new StringBuilder().append("原二维码支付订单号[").append(inputData.getQrcodeordernbr()).append("]").append("支付平台流水[")
				.append("商户号[").append(inputData.getMernbr()).append("]")
				.append(inputData.getTranscode()).append("] 发送二维码查询!").toString());
		String transdate=  context.getString(Dict.ACCT_NAME);
		
		AuthorityExample authorityExample = new AuthorityExample();
		authorityExample.createCriteria().andTranstatusEqualTo(transdate);
	
		List<Authority> list = new ArrayList<Authority>();
		try {
			list = AuthorityDAO.selectByExample(authorityExample);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
			
		List<Map<String,String>> resultSmsList = new ArrayList<Map<String, String>>();
		for(int i = 0 ;i<list.size();i++) {
            Map<String,String> smsMap = new HashMap<String, String>();
            smsMap.put("customName",list.get(i).getCustomname());
            smsMap.put("certtyp",list.get(i).getCerttyp());
            smsMap.put("certno",list.get(i).getCertno());
            smsMap.put("cardtyp",list.get(i).getCardtyp());
            smsMap.put("cardno",list.get(i).getCardno());
            smsMap.put("customtyp",list.get(i).getCustomtyp());
            smsMap.put("phoneno",list.get(i).getPhoneno());
            smsMap.put("transtatus",list.get(i).getTranstatus());
            resultSmsList.add(smsMap);
        }
		context.setData(Dict.AUTHLIST,resultSmsList);
		
		
		
		
		
		
	}
	
}
