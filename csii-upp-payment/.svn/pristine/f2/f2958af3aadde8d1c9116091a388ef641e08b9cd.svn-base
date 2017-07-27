package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.PaymTransCode;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
* 查询签约信息
* @auther fgq email:f_xust@163.com
* @version 创建时间：2016年10月25日 上午11:15:42
* 
*/
public class QueryYinYiTongSignInfoAction extends PaymentOnlineAction{

	@Override
	public void execute(Context context) throws PeException {
		//判断签约信息是否存在
		boolean isExist = signInfoIsExist(context);
		if(!isExist){
			throw new PeException("签约信息不存在或状态异常");
		}
		context.setData(Dict.TRANS_TYP_CD, PaymTransCode.PayTrans);
		
	}
	/**
	 * 本地签约信息是否存在且是否正常
	 * @param context
	 * @throws PeException
	 */
	public boolean signInfoIsExist(Context context)throws PeException{
		
		String cardNo = context.getString(Dict.PAYER_ACCT_NBR);
		String mobile = context.getString(Dict.PAYER_PHONE_NO);
		if(StringUtil.isStringEmpty(cardNo)||StringUtil.isStringEmpty(mobile)){
			throw new PeException("卡号或手机号为空");
		}
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(cardNo).andSignmobileEqualTo(mobile).andPaytypcdEqualTo(PayTypCd.FOSION);
		List<Userpaytypsigninfo> userSignInfoList = null;
		try {
			userSignInfoList = UserpaytypsigninfoDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		// 本地签约信息为空
		if(userSignInfoList.isEmpty()){
			
			return false;
		}
		Userpaytypsigninfo signinfo = userSignInfoList.get(0);
		if(!signinfo.getSignstatus().equals(SignStatus.NORMA))
		{
			return false;
		}
		context.setData(Dict.PAYER_ACCT_DEPT_NBR, signinfo.getSigndeptnbr());
		return true;		
	}

}
