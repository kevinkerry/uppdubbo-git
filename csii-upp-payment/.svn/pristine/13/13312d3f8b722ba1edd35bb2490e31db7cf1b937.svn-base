package com.csii.upp.payment.action.post;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 生成二级商户编号
 * @author 
 *
 */
public class GenerrateSubMerchantIdAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		try {
			String merNbr = StringUtil.toStringAndTrim(context.getString(Dict.MER_NBR));
			if(merNbr==null||"".equals(merNbr))
				throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
			Meracctinfo merchantAcctInfo = MeracctinfoDAO.selectByPrimaryKey(merNbr);
			
			String merPlatNbr = merchantAcctInfo.getMerplatformnbr();
		
			String subMerchantId=merPlatNbr+ StringUtil.toStringAndTrim(context.getString(Dict.OUT_SUB_MERCHANT_ID));
			context.setData(Dict.SUB_MERCHANT_ID, subMerchantId);
			context.setData(Dict.MER_PLATFORM_NBR, merPlatNbr);
			context.setDataMap(context.getDataMap());
		} catch (Exception e) {
		
			throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
		}
		
       
	}

}
