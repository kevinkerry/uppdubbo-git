/**
 * 
 */
package com.csii.upp.dto.router.fundprocess;

import com.csii.upp.constant.CardPwdFlag;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.FundProcessTransCode;
import com.csii.upp.dto.extend.InputPaymentData;

/**
 *  @author lixinyou
 * 卡密码校验请求类
 */
public class ReqCheckCardPwd extends ReqFundProHead {

	private String checkCardPwdFlag;
	public ReqCheckCardPwd(InputPaymentData data) {
		super(data);
		this.setTransCode(FundProcessTransCode.CheckCardPwd);
		if(CardTypCd.DEBIT.equals(data.getPayercardtypcd())){
			this.setCheckCardPwdFlag(CardPwdFlag.CHECKCARDPWD);
			this.setPayerIdNbr(data.getPayeridnbr());
			this.setPayerIdTypCd(data.getPayeridtypcd());
		}
		else if(CardTypCd.CREDIT.equals(data.getPayercardtypcd())){
			this.setCheckCardPwdFlag(CardPwdFlag.CHECKCUSTOMERPWD);
		}
	}
	
	public String getCheckCardPwdFlag() {
		return checkCardPwdFlag;
	}
	public void setCheckCardPwdFlag(String checkCardPwdFlag) {
		this.checkCardPwdFlag = checkCardPwdFlag;
	}
}
