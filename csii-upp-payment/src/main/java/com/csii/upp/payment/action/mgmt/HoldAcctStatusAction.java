package com.csii.upp.payment.action.mgmt;

import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 丰收E支付 冻结
 * @author qgs
 *
 */
public class HoldAcctStatusAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		
		@SuppressWarnings("unused")
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		
		Userpaytypsigninfo signInfo = new Userpaytypsigninfo();
		signInfo.setSignstatus(SignStatus.FROZEN);
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr()).andPaytypcdEqualTo(input.getPaytypcd());
		try {
			UserpaytypsigninfoDAO.updateByExampleSelective(signInfo, example);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		
		/*RespCheckCardPwd output=(RespCheckCardPwd) getFDPSService().CheckCardPwd(input);
		
		if(CardTypCd.CREDIT.equals(input.getPayercardtypcd())){			
		//	String Hostcode = output.getReturncode();
			String HostPayerIdTypCd = output.getPayerIdTypCd().trim();
			String HostPayerIdNbr = output.getPayerIdNbr().trim();
			String HostPayerCardExpiredDate = output.getPayerCardExpiredDate();
			
			if(!input.getPayeridtypcd().equals(HostPayerIdTypCd)){
				throw new PeException(DictErrors.CARDTYPENOTMATCH);
			}
			if(!input.getPayeridnbr().equals(HostPayerIdNbr)){
				throw new PeException(DictErrors.CARDIDNOTMATCH);
			}
		}
		*/
	
	}

}
