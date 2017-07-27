package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;
/**
 * 查询签约详情
 * @author qgs
 *
 */
public class QuerySignDetailAction extends PaymentOnlineAction{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Context context) throws PeException {
		String mobilePhone = StringUtil.toStringAndTrim(context.getString(Dict.PAYER_PHONE_NO));
		String payerCard=null;
		payerCard=StringUtil.toStringAndTrim(context.getString(Dict.PAYER_ACCT_NBR));
		if(StringUtil.isStringEmpty(mobilePhone)){
			throw new PeException(DictErrors.MOBILESIGN_STATUS_ERROR);
		} 
		String payTyCd=context.getString(Dict.PAY_TYP_CD);
		List<String>payTyCdsList=null;
		if(StringUtil.isStringEmpty(payTyCd)){
			throw new PeException(DictErrors.PAY_TYP_NOT_EXIST);
		}else{
			if(payTyCd.equals(PayTypCd.FOSION)||payTyCd.equals(PayTypCd.INTEL)){
				payTyCdsList = new ArrayList<String>(Arrays.asList(
						PayTypCd.FOSION, PayTypCd.INTEL));
			}
		}
		// 通过手机号查询签约信息
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		List<String> status = new ArrayList<String>(Arrays.asList(SignStatus.FROZEN, SignStatus.NORMA));
		example.createCriteria().andSignmobileEqualTo(mobilePhone).andSignstatusIn(status).andPaytypcdIn(payTyCdsList);
		List userSignInfoList = null;
		List <Map>cardList = new ArrayList();
		try {
			userSignInfoList = UserpaytypsigninfoDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if(userSignInfoList == null || userSignInfoList.isEmpty()){
			throw new PeException(DictErrors.MOBILESIGN_STATUS_ERROR);
		}else{
			log.info("该手机注册了"+userSignInfoList.size()+"个账户");
			
			for(Iterator it = userSignInfoList.iterator();it.hasNext();){
				Userpaytypsigninfo userSignInfo = (Userpaytypsigninfo) it.next();
				Map map = new HashMap();
				
				String payerAcctNbr = userSignInfo.getSigncardnbr();
				String preAcctNo = payerAcctNbr.substring(0, 4);
				String aftAcctNo = payerAcctNbr.substring(payerAcctNbr.length()-4);
				String acctno = preAcctNo + "*******" + aftAcctNo;
				String deptId = userSignInfo.getSigndeptnbr();
				String payTp=userSignInfo.getPaytypcd();
				map.put(Dict.PAYER_ACCT_NO, acctno);
			    map.put(Dict.PAYER_ACCT_NBR,payerAcctNbr);
				map.put(Dict.PAYER_ACCT_DEPT_NBR,deptId);
				map.put(Dict.PAY_TYP_CD,payTp);
				cardList.add(map);
				if(StringUtil.isStringEmpty(payerCard)){
					if(PayTypCd.FOSION.equals(payTp)&&!PayTypCd.FOSION.equals(cardList.get(0).get(Dict.PAY_TYP_CD))){
						cardList.set(cardList.size()-1, cardList.get(0));
						cardList.set(0, map);
					}
				}else{
					if(payerCard.equals(payerAcctNbr)){
						cardList.set(cardList.size()-1, cardList.get(0));
						cardList.set(0, map);
					}
				}
					
			}
			
		}
		Userpaytypsigninfo userSignInfo = (Userpaytypsigninfo)userSignInfoList.get(0);
		context.setData(Dict.PAYER_ACCT_NAME,userSignInfo.getSignname());
		context.setData(Dict.CARD_LIST, cardList);
			
	}


}
