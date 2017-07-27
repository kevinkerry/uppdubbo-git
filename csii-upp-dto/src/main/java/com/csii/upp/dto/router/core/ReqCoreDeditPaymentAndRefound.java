/**
 * 
 */
package com.csii.upp.dto.router.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.upp.constant.ConstCore;
import com.csii.upp.constant.CoreTransCode;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.util.StringUtil;

/**
 * 转账请求类
 */
public class ReqCoreDeditPaymentAndRefound extends ReqCoreHead {


	public ReqCoreDeditPaymentAndRefound(InputFundData data) {
		super(data);
		if (OveralltransTyp.MERSETTLE.equals(data.getTransid())) {
			this.setTransCode(CoreTransCode.NewCoreDeditPayment);
			this.setPayerAcctnbr(data.getPayeracctnbr());
			this.setPayeeAcctnbr(data.getPayeeacctnbr());
			this.setCurrencyCd(data.getCurrencycd());
			this.setTotalAmt(data.getTransamt());
			this.setMernbr(data.getMernbr());
			this.setNote(data.getNote());
		} else {
			if(OveralltransTyp.UPAY.equals(data.getTransid()) || OveralltransTyp.BTTF.equals(data.getTransid())){
				this.setTransTypCd(ConstCore.TRANSTYP_PAY);
				if(InteralFlag.YES.equals(data.getInteralFlag())){
					this.setTotalAmt(data.getRealAmt());
					data.setTransamt(data.getRealAmt());
					List<Map<String, String>> srcList = data.getPayeeAcctList();
					List<Map<String, String>> destList = new ArrayList<Map<String, String>>(srcList);
					for(int i=destList.size()-1;i>=0;i--){
						BigDecimal transAmt= StringUtil.parseBigDecimal(destList.get(i).get(Dict.SUB_REAL_AMT));
						if (StringUtil.isObjectEmpty(transAmt)
								|| (transAmt.compareTo(BigDecimal.ZERO) <= 0)) {
							destList.remove(i);
						}
						else{
							Map<String, String> destMap = new HashMap<String, String>();
							destMap.putAll(destList.get(i));
							destMap.put(Dict.SUB_TRANS_AMT, transAmt.toString());
							destList.remove(i);
							destList.add(destMap);
						}
					}
					this.setPayeeAcctList(destList);
				}else{
					this.setPayeeAcctList(data.getPayeeAcctList());
					this.setTotalAmt(data.getTransamt());
				}
			}
			else{
				this.setTransTypCd(ConstCore.TRANSTYP_RETN);
				Map<String,String> payeeAcctMap=new HashMap<String,String>();
				payeeAcctMap.put(Dict.PAYEE_ACCT_NBR, data.getPayeeacctnbr());
				if(InteralFlag.YES.equals(data.getInteralFlag())){
					this.setTotalAmt(data.getRealAmt());
					data.setTransamt(data.getRealAmt());
					payeeAcctMap.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(data.getRealAmt()));
				}else{
					this.setTotalAmt(data.getTransamt());
					payeeAcctMap.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(data.getTransamt()));
				}
				payeeAcctList=new ArrayList<Map<String, String>>();
				payeeAcctList.add(payeeAcctMap);
			}
			this.setTransCode(CoreTransCode.CoreDeditPayment);
			this.setPayerAcctnbr(data.getPayeracctnbr());
			this.setCurrencyCd(data.getCurrencycd());
			this.setMernbr(data.getMernbr());
			this.setPayeeAcctnbr(data.getPayeeacctnbr());
			this.setPayeeOrgnbr(data.getPayeeacctdeptnbr());
			this.setPayerCardPwd(data.getPayercardpwd());
			this.setNote(data.getNote());
			if(StringUtil.isStringEmpty(this.payerCardPwd)){
				this.setCheckPwdFlag(ConstCore.CHECKPWDFLAG_N);
			}else{
				this.setCheckPwdFlag(ConstCore.CHECKPWDFLAG_Y);
			}
		}

		
	}
	
	private String payerAcctnbr;  //付款人账号
	private String currencyCd;  //币种
	private String note; //备用字段
	private String mernbr;      //商户编号
	private String payeeAcctnbr;  //收款账号
	private String payeeOrgnbr;   //商户归属机构号
	private String transTypCd;//交易类型
	private String payerCardPwd;     //卡密码
	private BigDecimal totalAmt; //汇总交易金额
	private List<Map<String,String>> payeeAcctList; //同一机构子订单汇总
	private String checkPwdFlag;//验密标志
	
	
	
	public String getCheckPwdFlag() {
		return checkPwdFlag;
	}
	public void setCheckPwdFlag(String checkPwdFlag) {
		this.checkPwdFlag = checkPwdFlag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getMernbr() {
		return mernbr;
	}
	public void setMernbr(String mernbr) {
		this.mernbr = mernbr;
	}
	
	
	public String getPayerAcctnbr() {
		return payerAcctnbr;
	}
	public void setPayerAcctnbr(String payerAcctnbr) {
		this.payerAcctnbr = payerAcctnbr;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getPayeeAcctnbr() {
		return payeeAcctnbr;
	}
	public void setPayeeAcctnbr(String payeeAcctnbr) {
		this.payeeAcctnbr = payeeAcctnbr;
	}
	public String getPayeeOrgnbr() {
		return payeeOrgnbr;
	}
	public void setPayeeOrgnbr(String payeeOrgnbr) {
		this.payeeOrgnbr = payeeOrgnbr;
	}
	public String getTransTypCd() {
		return transTypCd;
	}
	public void setTransTypCd(String transTypCd) {
		this.transTypCd = transTypCd;
	}
	public String getPayerCardPwd() {
		return payerCardPwd;
	}
	public void setPayerCardPwd(String payerCardPwd) {
		this.payerCardPwd = payerCardPwd;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public List<Map<String, String>> getPayeeAcctList() {
		return payeeAcctList;
	}
	public void setPayeeAcctList(List<Map<String, String>> payeeAcctList) {
		this.payeeAcctList = payeeAcctList;
	}
}

