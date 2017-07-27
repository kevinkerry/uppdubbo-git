package com.csii.upp.payment.action.mgmt;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.BankOptionCd;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.constant.SignTypCd;
import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;

public class QueryReOtherPayStatusAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());
		queryCardType(input);
		String isQueryOpenStatus = context.getString(Dict.IS_QUERY_OPEN_STATUS);
		context.setData(Dict.INNER_CARD_FLAG, input.getInnerCardFlag());
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		context.setData(Dict.CARD_BIN_NAME, input.getCardBinName());
		if(!"0".equals(isQueryOpenStatus)){
			try {
				UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
				example.createCriteria().andPaytypcdEqualTo(PayTypCd.INTEL)
						.andSigncardnbrEqualTo(input.getPayeracctnbr());
				List<Userpaytypsigninfo> list = UserpaytypsigninfoDAO.selectByExample(example);
				//有记录，但未注册
				if (list.size() > 0 ) {
					if(!input.getPayerphoneno().equals(list.get(0).getSignmobile())){
						this.autoDeleteSignUser(input.getPayerphoneno(), input.getPayeracctnbr(), PayTypCd.INTEL);
						context.setData(Dict.OPEN_STATUS, "3");
					}else{
						if(SignStatus.CANCEL.equals(list.get(0).getSignstatus())){
							context.setData(Dict.OPEN_STATUS, "0");
						}else if(SignStatus.FROZEN.equals(list.get(0).getSignstatus())){
							context.setData(Dict.OPEN_STATUS, "1");
						}else{
							//有记录，注册
							context.setData(Dict.OPEN_STATUS, "2");
						}
						
					}
				}
				//无记录
				else if(list.size()==0){
				// 将客户的手机号和卡号存放到签约表里					
					this.autoSignUserPayTyp(input.getPayerphoneno(), input.getPayeracctnbr(),
							input.getPayeracctdeptnbr(), PayTypCd.INTEL);
					context.setData(Dict.OPEN_STATUS, "0");
				}
					
			} catch (SQLException e) {
				throw new PeException(DictErrors.DATABASE_EXCEPTION);
			}
		}else{
			this.UnionRegisyterStatusChange(input);
		}
	}
	
	protected Userpaytypsigninfo autoSignUserPayTyp(final String signPhoneNo, final String signCardNbr,final String signDeptNbr,
			final String payTypCd) throws PeException {
		final Userpaytypsigninfo signRecord = new Userpaytypsigninfo();
		this.getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				try {

					signRecord.setSigncardnbr(signCardNbr);
					signRecord.setSignmobile(signPhoneNo);
					signRecord.setSigntypcd(SignTypCd.USER_PAY_TYP);
					signRecord.setPaytypcd(payTypCd);
					signRecord.setSigndate(getPostDate());
					signRecord.setSignstatus(SignStatus.CANCEL);
					signRecord.setSigneffdate(signRecord.getSigndate());
					signRecord.setSigndeptnbr(signDeptNbr);
					signRecord.setSignnbr(IDGenerateFactory.generateSeqId());
					UserpaytypsigninfoDAO.insert(signRecord);

					Custtransctrl ctrlRecord = new Custtransctrl();
					ctrlRecord.setPerdaylimit(new BigDecimal(
							BankoptionDAO.selectByPrimaryKey(BankOptionCd.USTL, 1L).getBankoptionvalue()));
					ctrlRecord.setPertranslimit(new BigDecimal(
							BankoptionDAO.selectByPrimaryKey(BankOptionCd.USDL, 1L).getBankoptionvalue()));
					ctrlRecord.setSignnbr(signRecord.getSignnbr());
					ctrlRecord.setSigntypcd(signRecord.getSigntypcd());
					CusttransctrlDAO.insert(ctrlRecord);
				} catch (SQLException e) {
					throw new PeRuntimeException(e);

				}
				return null;
			}

		});
		return signRecord;
	}
	
	//更改本地签约状态未开通
	public void UnionRegisyterStatusChange(InputPaymentData input) throws PeException{
		Userpaytypsigninfo signInfo = new Userpaytypsigninfo();
		signInfo.setSignstatus(SignStatus.NORMA);
		try {
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andPaytypcdEqualTo(PayTypCd.INTEL)
					.andSigncardnbrEqualTo(input.getPayeracctnbr());			
			UserpaytypsigninfoDAO.updateByExampleSelective(signInfo, example);			
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}			
	}

}
