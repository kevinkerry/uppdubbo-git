package com.csii.upp.payment.action.post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.MerPlatformNbr;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.ValidateCard;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.OnlinetranshistExample;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;

/**
 * 
 * 卡信息验证
 * @author zgb
 *
 */
public class ValidateCardInfoAction extends PaymentOnlineAction {

	private static final int CHECK_STATUS_LENGTH = 5;

	 public static final List<String> CARD_TYPE_LIST = new ArrayList<String>() {

			{
	            add(CardTypCd.DEBIT);
	            add(CardTypCd.CREDIT);
	        }
	    };
	    public static final String PHONE_MATCHER = "^1[3|5|7|8]\\d{9}$";
	   
	  //证件类型 一对多
		 Map<String,List> IdTypeMap = new HashMap<String, List>(); {
		 	List<String> list = new ArrayList<String>();
		 	list.add("101");
		 	list.add("01");
		 	IdTypeMap.put(Dict.PAYER_ID_TYP_CD,list);
		 }	
	   
	   
	@Override
	public void execute(Context context) throws PeException {

		InputPaymentData input = new InputPaymentData(context.getDataMap());
		
		String MerNbr       = input.getMernbr();
		Meracctinfo merinfo;
		try {
			merinfo = MeracctinfoDAO.selectByPrimaryKey(MerNbr);
			if (!MerPlatformNbr.FMRT.equals(merinfo.getMerplatformnbr())
					&&!MerPlatformNbr.RARB.equals(merinfo.getMerplatformnbr())) {
				
               throw new PeException(DictErrors.NOSUCHTRADINGRIGHTS);
               
			}
		} catch (SQLException e1) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

		if(StringUtil.isStringEmpty(input.getPayeracctnbr())){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAYERACCTNBR});
		}
		
		if(StringUtil.isStringEmpty(input.getPayerphoneno())){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAYER_PHONE_NO});
		}
		
		if(StringUtil.isStringEmpty(input.getPayeridnbr())){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAYER_ID_NBR});
		}
		
		if(StringUtil.isStringEmpty(input.getMernbr())){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.MER_NBR});
		}
		
		if(StringUtil.isStringEmpty(input.getPayeridtypcd())){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAYER_ID_TYP_CD});
		}
		
		if(StringUtil.isStringEmpty(input.getPayercardtypcd())){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PAYER_CARD_TYP_CD});
		}
		
		
		String MerSeqNbr    = input.getMerseqnbr(); 
		String AcctName     = (String) context.getData(Dict.ACCT_NAME);
		//用户输入
		String Payercardtypcd = null;
		if("C".equals(input.getPayercardtypcd())){
			Payercardtypcd=CardTypCd.CREDIT;	
		}else if("D".equals(input.getPayercardtypcd())){
			Payercardtypcd=CardTypCd.DEBIT;	
		}else{
			throw new PeException(DictErrors.CARD_TYPE_NOT_EXIST);
		}
		//交易流水号不为空
		if(!StringUtil.isStringEmpty(MerSeqNbr)){
			  log.info("CSVR**********" + "支付流水号：" + MerSeqNbr);
			  //根据mernbr和MerSeqNbr查询
			  OnlinetransExample example = new OnlinetransExample();
			  OnlinetranshistExample examplehist = new OnlinetranshistExample();
			  example.createCriteria().andMernbrEqualTo(MerNbr).andMerseqnbrEqualTo(MerSeqNbr);
			  examplehist.createCriteria().andMernbrEqualTo(MerNbr).andMerseqnbrEqualTo(MerSeqNbr);
			
			try {
				List<Onlinetrans> onlinetranslist = OnlinetransDAO.selectByExample(example);
				String acctnbr=null;
				if(onlinetranslist.size()<=0){
				List<Onlinetranshist> onlinetranslisthist = OnlinetranshistDAO.selectByExample(examplehist);
			
					if (onlinetranslisthist.size() <= 0) {
						throw new PeException(DictErrors.DATA_ERROR);
					}
					if(onlinetranslisthist.size()>0){
						Onlinetranshist onlinetranshist =onlinetranslisthist.get(0);
						acctnbr = onlinetranshist.getPayeracctnbr();
					}
					
				}else{
					Onlinetrans onlinetrans = onlinetranslist.get(0);
					acctnbr = onlinetrans.getPayeracctnbr();
				}
				//判断输入的卡号和记录中卡号是否匹配
				if(!input.getPayeracctnbr().equals(acctnbr)){
					throw new PeException(DictErrors.CARD_NUMBER_DOES_NOTMATCH);
				}
				
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		
		}

		// 判断卡类型 是否判断本行或其他行卡 卡宾已配置
		queryCardType(input);
		// 卡查询
		RespQueryCardInfo output = (RespQueryCardInfo) this.getFDPSService().cvsrQueryCardInfo(input);
		if(!ResponseCode.SUCCESS.equals(output.getReturncode())){
			  throw new PeException(String.valueOf(output.getReturncode()));
		}
		
		 char[] checkStatus = new char[CHECK_STATUS_LENGTH];
	        checkStatus[0] = checkCardType(output, Payercardtypcd,input);
	        checkStatus[1] = checkCardName(output, AcctName);
	        checkStatus[2] = checkPhone(output, input.getPayerphoneno());
	        checkStatus[3] = checkCertType(output, input.getPayercardtypcd());
	        checkStatus[4] = checkCertNo(output,input.getPayeridnbr());
	        context.setData(Dict.CHECK_STATUS, new String(checkStatus));
	        context.setData(Dict.ACCT_NAME, AcctName);
	        context.setData(Dict.TRANS_ID, context.getData(Dict.TRANS_ID));
	        context.setData(Dict.MER_NBR,MerNbr);
	        context.setData(Dict.PAYER_ACCT_NBR, input.getPayeracctnbr());
	        
	}

	private char checkCertNo(RespQueryCardInfo output, String PayerIdNbr){
		if(StringUtil.isStringEmpty(PayerIdNbr)) {
            return ValidateCard.c;
        }
        
        return PayerIdNbr.equals(output.getPayerIdNbr())?ValidateCard.a:ValidateCard.b;
	}

	private char checkCertType(RespQueryCardInfo output, String payerIdTypCd) {
		if("1".equals(payerIdTypCd)){
			payerIdTypCd = "01";
		}
		   if(!IdTypeMap.get(Dict.PAYER_ID_TYP_CD).contains(payerIdTypCd)) {
	            return ValidateCard.c;
	        }
	        return IdTypeMap.get(Dict.PAYER_ID_TYP_CD).contains(output.getPayerIdTypCd())?ValidateCard.a:ValidateCard.b;
	}

	private char checkPhone(RespQueryCardInfo output, String payerPhoneNo) {
		 if(!isPhoneNumber(payerPhoneNo)) {
	            return ValidateCard.c;
	        }
	     List<String> mobileNolist  = output.getPayerPhoneNoList();
	     return mobileNolist.contains(payerPhoneNo)?ValidateCard.a:ValidateCard.b;
	}

	private char checkCardName(RespQueryCardInfo output, String acctName) {
		  if(StringUtil.isStringEmpty(acctName)) {
	            return ValidateCard.c;
	        }
	        return acctName.equals(String.valueOf(output.getPayerAcctName()))?ValidateCard.a:ValidateCard.b;
	}
     //判断核心的卡类型与输入的类型
	private char checkCardType(RespQueryCardInfo output, String Payercardtypcd,InputPaymentData input) {
		String CardTypcd = input.getPayercardtypcd();
		if(!CARD_TYPE_LIST.contains(CardTypcd)){
			return ValidateCard.c;
		}
		 return Payercardtypcd.equals(CardTypcd) ?ValidateCard.a:ValidateCard.b;
	}
	  private boolean isPhoneNumber(String input){
	        Pattern p = Pattern.compile(PHONE_MATCHER);
	        Matcher matcher = p.matcher(input);
	        return matcher.matches();
	    }
}
