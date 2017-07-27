package com.csii.upp.payment.action.mgmt;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.BankOptionCd;
import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.CusttransctrlExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 查询签约卡交易限额信息
 * 
 * @author heshishuai
 *
 */
public class UpdateCtrlTransAction extends PaymentOnlineAction {
	@Override
	public void execute(Context context) throws PeException {
		if (StringUtil.isStringEmpty(context.getString(Dict.SIGN_NBR))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.SIGN_NBR });
		}
		if (StringUtil.isStringEmpty(context.getString(Dict.SIGN_TYP_CD))) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.SIGN_TYP_CD });
		}
		String perDayMax = BankOptionCd.FSDL;
		String perTransMax = BankOptionCd.FSTL;
	    BigDecimal PerDayMax=null;
	    BigDecimal PerTransMax=null;
	    try{
	    	PerDayMax=new BigDecimal(BankoptionDAO.selectByPrimaryKey(perDayMax, 1L).getBankoptionvalue());
	    	PerTransMax=new BigDecimal(BankoptionDAO.selectByPrimaryKey(perTransMax, 1L).getBankoptionvalue());
	    }catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (!StringUtil.isStringEmpty(context.getString(Dict.PER_DAY_LIMIT))
				&& !StringUtil.isStringEmpty(context.getString(Dict.PER_TRANS_LIMIT))) {
			if(PerDayMax.compareTo(new BigDecimal(context.getString(Dict.PER_DAY_LIMIT)))<0
					||PerTransMax.compareTo(new BigDecimal(context.getString(Dict.PER_TRANS_LIMIT)))<0){
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			
			CusttransctrlExample example = new CusttransctrlExample();
			Custtransctrl record = new Custtransctrl();
			example.createCriteria().andSignnbrEqualTo(context.getString(Dict.SIGN_NBR))
					.andSigntypcdEqualTo(context.getString(Dict.SIGN_TYP_CD));
			try {
				List<Custtransctrl> list = CusttransctrlDAO.selectByExample(example);
				Custtransctrl orirecord = list.get(0);
				record.setSignnbr(context.getString(Dict.SIGN_NBR));
				record.setSigntypcd(context.getString(Dict.SIGN_TYP_CD));
				record.setPerdaylimit(new BigDecimal(context.getString(Dict.PER_DAY_LIMIT)));
				record.setPertranslimit(new BigDecimal(context.getString(Dict.PER_TRANS_LIMIT)));
				record.setDayamt(orirecord.getDayamt());
				record.setDayamtdate(orirecord.getDayamtdate());
				CusttransctrlDAO.updateByExampleSelective(record, example);
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}
	}
}
