package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.OnlinesmsinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Onlinesmsinfo;
import com.csii.upp.dto.generate.OnlinesmsinfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;
/**
 * 商户查询短信接口
 * @author gaoqi
 *
 */
public class QuerySmsCodeAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		if (StringUtil.isStringEmpty(input.getPayerphoneno())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[] { Dict.FS_PAYER_PHONE_NO});
		}
		OnlinesmsinfoExample example = new OnlinesmsinfoExample();
		example.setOrderByClause("transtime DESC");
		example.createCriteria().andPhoneEqualTo(input.getPayerphoneno());
		List<Onlinesmsinfo> OnlinesmsinfoList = null;
		try {
			OnlinesmsinfoList = OnlinesmsinfoDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (OnlinesmsinfoList.isEmpty()) {
			// 短信验证码为空
			throw new PeException("该手机对应的短信验证码为空");
		}
		int num = 10;
		List<Map<String,String>> resultSmsList = new ArrayList<Map<String, String>>();
		for(int i = 0 ;i<num&&i<OnlinesmsinfoList.size();i++) {
            Map<String,String> smsMap = new HashMap<String, String>();
            smsMap.put("transTime",OnlinesmsinfoList.get(i).getTranstime().toString());
            smsMap.put("code",OnlinesmsinfoList.get(i).getCode());
            smsMap.put("msg",OnlinesmsinfoList.get(i).getMsg());
            resultSmsList.add(smsMap);
        }
		context.setData(Dict.SMS_LIST,resultSmsList);
	}

}
