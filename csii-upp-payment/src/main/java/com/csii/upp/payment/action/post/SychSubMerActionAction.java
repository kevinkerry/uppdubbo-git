


package com.csii.upp.payment.action.post;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.AcctKind;
import com.csii.upp.constant.AcctStatCode;
import com.csii.upp.constant.AcctTypCd;
import com.csii.upp.constant.BoundFlag;
import com.csii.upp.constant.CalculateType;
import com.csii.upp.constant.CardTypCd;
import com.csii.upp.constant.ClearClassModeCd;
import com.csii.upp.constant.DeductFlag;
import com.csii.upp.constant.FeeMode;
import com.csii.upp.constant.FloatFlag;
import com.csii.upp.constant.IsParentMer;
import com.csii.upp.constant.MerPlatformNbr;
import com.csii.upp.constant.MerStatus;
import com.csii.upp.constant.PayModeCd;
import com.csii.upp.constant.ProgressFlag;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.SettPeriod;
import com.csii.upp.constant.SystemCode;
import com.csii.upp.dao.generate.FeeparamDAO;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.MerbaseinfoDAO;
import com.csii.upp.dao.generate.MerthirdpartacctDAO;
import com.csii.upp.dao.generate.ProfitctrlDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputMerchantSyncData;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Feeparam;
import com.csii.upp.dto.generate.FeeparamExample;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.MeracctinfoExample;
import com.csii.upp.dto.generate.Merbaseinfo;
import com.csii.upp.dto.generate.MerbaseinfoExample;
import com.csii.upp.dto.generate.Merthirdpartacct;
import com.csii.upp.dto.generate.MerthirdpartacctExample;
import com.csii.upp.dto.generate.Profitctrl;
import com.csii.upp.dto.generate.ProfitctrlExample;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.dto.router.fundprocess.RespQueryInnerAcctInfo;
import com.csii.upp.dto.router.fundprocess.RespSyhMerDate;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

/**
 * 二级商户信息同步
 * 
 * @author qgs
 *
 */
public class SychSubMerActionAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		
		if(!StringUtil.isStringEmpty(context.getString(Dict.PROXY_SYN_TYPE))){
			Merbaseinfo merBase = null;
			try {
				merBase = MerbaseinfoDAO.selectByPrimaryKey(context.getString(Dict.SUB_MERCHANT_ID));
				if(null!=merBase){
					SychQRCodeSubMerInfo(context,merBase);
				}else{
					SychSubMerInfo(context);
					SychQRCodeSubMerInfo(context,merBase);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			SychSubMerInfo(context);
		}
	}
		
		protected void SychQRCodeSubMerInfo(Context context,Merbaseinfo merBase) throws PeException{
			InputPaymentData inputData = new InputPaymentData(context.getDataMap());
			if (StringUtil.isStringEmpty(inputData.getSubMerchantId())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.SUB_MERCHANT_ID});
			}
			if (StringUtil.isStringEmpty(inputData.getMerShortName())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.MER_SHORT_NAME});
			}
			if (StringUtil.isStringEmpty(inputData.getProxySynStatus())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PROXY_SYN_STATUS});
			}
			if (StringUtil.isStringEmpty(inputData.getServicePhone())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.SERVICE_PHONE});
			}
			if (StringUtil.isStringEmpty(inputData.getMertName())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.MER_NAME});
			}
			if (StringUtil.isStringEmpty(inputData.getBusiness())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.BUSINESS});
			}
			if (StringUtil.isStringEmpty(inputData.getMerRemark())) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.MER_REMARK});
			}
			Merbaseinfo merbase = new Merbaseinfo();
			int operationStatus = 0;
			RespSyhMerDate output = null;
			//如果无简称 则没有同步过 执行同步
			if(null == merBase.getMershortname()){
				output = (RespSyhMerDate) this.getFDPSService().symerinfo(inputData);
				if("01".equals(context.getString(Dict.PROXY_SYN_TYPE))){
					merbase.setAlipayproxystatus("0");
				}else if("02".equals(context.getString(Dict.PROXY_SYN_TYPE))){
					merbase.setWechatproxystatus("0");
				}else if("00".equals(context.getString(Dict.PROXY_SYN_TYPE))){
					merbase.setAlipayproxystatus("0");
					merbase.setWechatproxystatus("0");
				}
				
			}else if(null != merBase.getMershortname()){
				//如果有简称 但要同步的方式未开通过 则执行同步
				if(("01".equals(context.getString(Dict.PROXY_SYN_TYPE))&&"0".equals(merBase.getWechatsubmerchantid()))||
					("02".equals(context.getString(Dict.PROXY_SYN_TYPE))&&"0".equals(merBase.getAlipaymerchantid()))){
						output = (RespSyhMerDate) this.getFDPSService().symerinfo(inputData);
						if("01".equals(context.getString(Dict.PROXY_SYN_TYPE))){
							merbase.setAlipayproxystatus("0");
						}else if("02".equals(context.getString(Dict.PROXY_SYN_TYPE))){
							merbase.setWechatproxystatus("0");
						}else if("00".equals(context.getString(Dict.PROXY_SYN_TYPE))){
							merbase.setAlipayproxystatus("0");
							merbase.setWechatproxystatus("0");
						}
				}else{
					//执行更新
					inputData.setAlipayMerchantId(merBase.getAlipaymerchantid());
					inputData.setWeChatSubMerchatId(merBase.getWechatsubmerchantid());
					output = (RespSyhMerDate) this.getFDPSService().updmerinfo(inputData);
					if("1".equals(inputData.getProxySynStatus())){
						if("01".equals(context.getString(Dict.PROXY_SYN_TYPE))){
							merbase.setAlipayproxystatus("1");
						}else if("02".equals(context.getString(Dict.PROXY_SYN_TYPE))){
							merbase.setWechatproxystatus("1");
						}else if("00".equals(context.getString(Dict.PROXY_SYN_TYPE))){
							merbase.setAlipayproxystatus("1");
							merbase.setWechatproxystatus("1");
						}
					}
				}
			}else{
				throw new PeException(DictErrors.SUB_MER_NOT_THIRD_SYCH);
			}
			
			//执行更新或同步
			merbase.setAlipaymerchantid(output.getAlipayMerchantId());
			merbase.setWechatsubmerchantid(output.getWeChatSubMerchatId());
			merbase.setProxymernbr(output.getProxyMerNbr());
			
			merbase.setMername(inputData.getMertName());
			merbase.setMershortname(inputData.getMerShortName());
			merbase.setServicephone(inputData.getServicePhone());
			merbase.setLinkmanname(inputData.getContactName());
			merbase.setLinkmantel(inputData.getContactMobile());
			merbase.setLinkmanphone(inputData.getContactPhone());
			merbase.setLinkmanemail(inputData.getContactEmail());
			merbase.setBusiness(context.getString(Dict.BUSINESS));
			merbase.setMerremark(context.getString(Dict.MER_REMARK));
			
			MerbaseinfoExample merbaseexample = new MerbaseinfoExample();
			merbaseexample.createCriteria().andMernbrEqualTo(context.getString(Dict.SUB_MERCHANT_ID));
			//merbaseexample.createCriteria().andMernbrEqualTo(context.getString(Dict.MER_NBR));
			try {
				operationStatus = MerbaseinfoDAO.updateByExampleSelective(merbase, merbaseexample);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			if (0 == operationStatus) {
				//更新不成功的操作
			} else if (1 == operationStatus) {
				// 空，更新1条，表示执行更新
			} else {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			context.setData(Dict.PROXY_SYN_TYPE,inputData.getProxySynType());
			context.setData(Dict.PROXY_SYN_STATUS,inputData.getProxySynStatus());
					
		} 
					
		
			
		protected void SychSubMerInfo(Context context) throws PeException{
			if (SystemCode.ESB.equals(context.getString(Dict.SYSTEM_CODE))) {
			context.setData(Dict.SUB_MERCHANT_ID,context.getString(Dict.MER_NBR)); 
			context.setData(Dict.MER_NBR, context.getString(Dict.SUPER_MER_NBR));
			if(!StringUtil.isStringEmpty(context.getString(Dict.MER_NBR))){
				Meracctinfo merAcct = null;
				try {
					merAcct = MeracctinfoDAO.selectByPrimaryKey(context.getString(Dict.MER_NBR));
					if (merAcct == null) {
						throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
					}
				} catch (SQLException e) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
				context.setData(Dict.MER_PLATFORM_NBR, merAcct.getMerplatformnbr());
			}else{
				if("11".equals(context.getString(Dict.MER_TYPE))){
					context.setData(Dict.MER_PLATFORM_NBR, MerPlatformNbr.FSEG);
				}else if("12".equals(context.getString(Dict.MER_TYPE))){
					context.setData(Dict.MER_PLATFORM_NBR, MerPlatformNbr.FSJ);
				}else{
					throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.MER_TYPE});
				}
				List<Meracctinfo> merAcctInfo = null;
				MeracctinfoExample example = new MeracctinfoExample();
				example.createCriteria().andMerplatformnbrEqualTo(context.getString(Dict.MER_PLATFORM_NBR))
						.andIsparentmerEqualTo(IsParentMer.Y);
				try {
					merAcctInfo = MeracctinfoDAO.selectByExample(example);
				} catch (SQLException e) {
					throw new PeException(DictErrors.TRANS_EXCEPTION);
				}
				if (merAcctInfo.isEmpty()) {
					throw new PeException(DictErrors.MERNBR_NOT_EXST);
				}

				if (StringUtil.isObjectEmpty(merAcctInfo.get(0).getMernbr())) {
					throw new PeException(DictErrors.NO_SUPER_MER_NBR);
				} else {
					context.setData(Dict.MER_NBR, merAcctInfo.get(0).getMernbr());
				}
			}
			}
			
				
				
				

		final InputMerchantSyncData input = new InputMerchantSyncData(context.getDataMap());
		
		if (StringUtil.isStringEmpty(input.getMerNbr())) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.SUPER_MER_NBR});
		}
		if (StringUtil.isObjectEmpty(input.getUpperLimitVal())) {
			input.setUpperLimitVal(new BigDecimal(999999999));
		}
		
		if (StringUtil.isObjectEmpty(input.getLowerLimitVal())) {
			input.setLowerLimitVal(new BigDecimal(0));
		}
		
		if(input.getUpperLimitVal().compareTo(input.getLowerLimitVal()) <0){
			throw new PeException(DictErrors.MAX_FEE_LT_MIN_FEE);
		}
		
//		if (StringUtil.isStringEmpty(input.getCalculateParam())) {
//			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.CALCULATE_PARAM});
//		}
		
		if (StringUtil.isStringEmpty(input.getMerDevelopDeptNbr())) {
			throw new PeException(DictErrors.MER_DEVELOPDEPT_ID_IS_NULL);
		}

		if (StringUtil.isObjectEmpty(input.getSettleAcctNbr())) {
			throw new PeException(DictErrors.SETT_ACCT_NBR_IS_NULL);
		}

		if (StringUtil.isObjectEmpty(input.getFeeAcctNbr())) {
			input.setFeeAcctNbr(input.getSettleAcctNbr());
		}

		if (StringUtil.isObjectEmpty(input.getFeeAcctName())) {
			input.setFeeAcctNbr(input.getSettleAcctName());
		}

		if (MerPlatformNbr.FY.equals(input.getMerPlatformNbr())) {
			if(StringUtil.isStringEmpty(input.getAcctKind())){
				throw new PeException(DictErrors.ACCT_KIND_IS_NULL);
			}
			// 虚账户类型商户
			log.info("虚账户类型商户：虚账号结算账号-merSettNo=" + input.getSettleAcctNbr());
			// 暂时不校验虚账户
			input.setPayModeCd(PayModeCd.HOLD);
		} else {
			if ("01".equals(input.getPayModeCd())) {
				input.setPayModeCd(PayModeCd.DIRECT);
			} else {
				input.setPayModeCd(PayModeCd.PROTECT);
			}
			if (input.getSettleAcctNbr().length() == 20) {
				InputPaymentData inputPayment = new InputPaymentData();
				inputPayment.setPayeracctnbr(input.getSettleAcctNbr());
				RespQueryInnerAcctInfo hostInfo = (RespQueryInnerAcctInfo) getFDPSService()
						.queryInnerAcctInfo(inputPayment);
				if (!AcctStatCode.NORMAL.equals(hostInfo.getInnerAcctStatus())) {
					throw new PeException(DictErrors.SETT_ACCT_NBR_STATUS_ERROR);
				}
				input.setMerOpenDeptNbr(hostInfo.getMerOpenDeptNbr());

			} else {
				String acctType = "";
				if (19 == StringUtil.toStringAndTrim(input.getSettleAcctNbr()).length()) {
					acctType = "Card";
				} else if (15 == StringUtil.toStringAndTrim(input.getSettleAcctNbr()).length()) {
					acctType = "Passbook";
				} else {
					throw new PeException(DictErrors.SETT_ACCT_NBR_LEN_ERROR);
				}

				InputPaymentData inputPayment = new InputPaymentData();
				inputPayment.setPayeracctnbr(input.getSettleAcctNbr());
				inputPayment.setPayercardtypcd(CardTypCd.DEBIT);
				RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService().queryAcctInfo(inputPayment);
				input.setMerOpenDeptNbr(hostInfo.getPayerAcctDeptNbr());
				input.setMerCifNbr(hostInfo.getCustCifNbr());
			//	checkSettAcctNo(hostInfo, acctType);
			}
		}

		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {

				try {
					int operationStatus = 0;
					// 更新商户基本信息表
					if (!StringUtil.isStringEmpty(input.getMerName())) {
						Merbaseinfo merbase = new Merbaseinfo();
						merbase.setMername(input.getMerName());

						MerbaseinfoExample merbaseexample = new MerbaseinfoExample();
						merbaseexample.createCriteria().andMernbrEqualTo(input.getSubMerchantId());
						operationStatus = MerbaseinfoDAO.updateByExampleSelective(merbase, merbaseexample);
						if (0 == operationStatus) {
							merbase.setMernbr(input.getSubMerchantId());
							MerbaseinfoDAO.insertSelective(merbase);
						} else if (1 == operationStatus) {
							// 空，更新1条，表示执行更新
						} else {
							throw new PeException(DictErrors.TRANS_EXCEPTION);
						}
					}

					Meracctinfo merAcctInfo = MeracctinfoDAO.selectByPrimaryKey(input.getSubMerchantId());
					Feeparam fee = new Feeparam();
					if (!StringUtil.isObjectEmpty(merAcctInfo)) {
						if (!StringUtil.isStringEmpty(merAcctInfo.getFeenbr())) {
							if (!StringUtil.isObjectEmpty(input.getUpperLimitVal())
									|| !StringUtil.isObjectEmpty(input.getLowerLimitVal())
									|| !StringUtil.isObjectEmpty(input.getCalculateParam())) {
								FeeparamExample feeExample = new FeeparamExample();
								feeExample.createCriteria().andFeenbrEqualTo(merAcctInfo.getFeenbr())
										.andSeqnbrEqualTo("1");
								if (!StringUtil.isStringEmpty(input.getFeeMode())) {
									if (FeeMode.SEC.equals(input.getFeeMode())) {
										fee.setFloatflag(String.valueOf(FloatFlag.FLOAT_BY_AMOUNT));
									} else {
										fee.setFloatflag(String.valueOf(FloatFlag.STATIC));
									}
									if (FeeMode.FIX.equals(input.getFeeMode()) || FeeMode.MON.equals(input.getFeeMode())
											|| FeeMode.SEC.equals(input.getFeeMode())) {
										fee.setCalculatetyp(String.valueOf(CalculateType.STATIC));
									} else {
										fee.setCalculatetyp(String.valueOf(CalculateType.PERCENT));
									}
								}
								fee.setDeductflag(DeductFlag.OUT_PAY);
								if(!StringUtil.isObjectEmpty(input.getUpperLimitVal()) && !StringUtil.isObjectEmpty(input.getLowerLimitVal())){
									if(input.getUpperLimitVal().compareTo(input.getLowerLimitVal()) == 0){
										fee.setBoundflag(BoundFlag.NO);
									}else{
										fee.setBoundflag(BoundFlag.YES);
									}
									
								}
								fee.setProgressflag(ProgressFlag.NO);

								fee.setUpperlimitval(input.getUpperLimitVal());
								fee.setLowerlimitval(input.getLowerLimitVal());
								fee.setCalculateparam(input.getCalculateParam());
								operationStatus = FeeparamDAO.updateByExampleSelective(fee, feeExample);
								if (0 == operationStatus) {
									fee.setFeenbr(IDGenerateFactory.generateSeqId());
									fee.setSeqnbr("1");
									FeeparamDAO.insertSelective(fee);
								} else if (1 == operationStatus) {
									// 空，更新1条，表示执行更新
								} else {
									throw new PeException(DictErrors.TRANS_EXCEPTION);
								}
							}
						} else {
							if (!StringUtil.isObjectEmpty(input.getUpperLimitVal())
									|| !StringUtil.isObjectEmpty(input.getLowerLimitVal())
									|| !StringUtil.isObjectEmpty(input.getCalculateParam())) {
								
								if (!StringUtil.isStringEmpty(input.getFeeMode())) {
									if (FeeMode.SEC.equals(input.getFeeMode())) {
										fee.setFloatflag(String.valueOf(FloatFlag.FLOAT_BY_AMOUNT));
									} else {
										fee.setFloatflag(String.valueOf(FloatFlag.STATIC));
									}
									if (FeeMode.FIX.equals(input.getFeeMode()) || FeeMode.MON.equals(input.getFeeMode())
											|| FeeMode.SEC.equals(input.getFeeMode())) {
										fee.setCalculatetyp(String.valueOf(CalculateType.STATIC));
									} else {
										fee.setCalculatetyp(String.valueOf(CalculateType.PERCENT));
									}
								}
								fee.setDeductflag(DeductFlag.OUT_PAY);
								if(!StringUtil.isObjectEmpty(input.getUpperLimitVal()) && !StringUtil.isObjectEmpty(input.getLowerLimitVal())){
									if(input.getUpperLimitVal().compareTo(input.getLowerLimitVal()) == 0){
										fee.setBoundflag(BoundFlag.NO);
									}else{
										fee.setBoundflag(BoundFlag.YES);
									}
									
								}
								fee.setProgressflag(ProgressFlag.NO);
								
								fee.setUpperlimitval(input.getUpperLimitVal());
								fee.setLowerlimitval(input.getLowerLimitVal());
								fee.setCalculateparam(input.getCalculateParam());
								fee.setFeenbr(IDGenerateFactory.generateSeqId());
								fee.setSeqnbr("1");
								FeeparamDAO.insertSelective(fee);
							}
						}
						// 更新商户账务信息表
						Meracctinfo meracct = new Meracctinfo();
						meracct.setSettleaccttyp(AcctTypCd.PRIV);
						meracct.setSettleacctkind(AcctKind.CORE);
						meracct.setClearclassmodecd(ClearClassModeCd.BITE);
						meracct.setFeesettperiod(SettPeriod.DAY);
						meracct.setIsparentmer(IsParentMer.N);
						meracct.setFeeacctkind(AcctKind.CORE);
						meracct.setFeeaccttyp(AcctTypCd.PRIV);
						meracct.setCardtypecd(input.getCardTypeCd());
						meracct.setSupermernbr(input.getMerNbr());
						meracct.setMerplatformnbr(input.getMerPlatformNbr());
						meracct.setMerstatus(MerStatus.CHECK);
						meracct.setMercifnbr(input.getMerCifNbr());
						meracct.setMername(input.getMerName());
						meracct.setPaymodecd(input.getPayModeCd());
						meracct.setSettleacctnbr(input.getSettleAcctNbr());
						meracct.setSettleacctname(input.getSettleAcctName());
						meracct.setFeemode(input.getFeeMode());
						meracct.setFeeacctnbr(input.getFeeAcctNbr());
						meracct.setFeeacctname(input.getFeeAcctName());
						meracct.setBailacctnbr(input.getBailAcctNbr());
						meracct.setBailamt(input.getBailAmt());
						meracct.setSettmode(input.getSettMode());
						meracct.setSettperiod(input.getSettPeriod());
						meracct.setProfitseqnbr(input.getProfitSeqNbr());
						meracct.setFeereturnflag(input.getFeeReturnFlag());
						meracct.setMeropendate(input.getMerOpenDate());
						meracct.setOpenusernbr(input.getOpenUserNbr());
						meracct.setMermodifydate(input.getMerModifyDate());
						meracct.setModifyusernbr(input.getModifyUserNbr());
						meracct.setMerclosedate(input.getMerCloseDate());
						meracct.setCloseuser(input.getCloseUser());
						meracct.setMermngdeptnbr(input.getMerMngDeptNbr());
						meracct.setMerdevelopdeptnbr(input.getMerDevelopDeptNbr());
						meracct.setMeropendeptnbr(input.getMerOpenDeptNbr());
						meracct.setFeesettperiod(input.getFeeSettperiod());
						meracct.setInneracctcfmmode(input.getInnerAcctCfmMode());
						if (!StringUtil.isStringEmpty(fee.getFeenbr())) {
							meracct.setFeenbr(fee.getFeenbr());
						}
						MeracctinfoExample meracctexample = new MeracctinfoExample();
						meracctexample.createCriteria().andMernbrEqualTo(input.getSubMerchantId());
						operationStatus = MeracctinfoDAO.updateByExampleSelective(meracct, meracctexample);
					} else {
						if (!StringUtil.isObjectEmpty(input.getUpperLimitVal())
								|| !StringUtil.isObjectEmpty(input.getLowerLimitVal())
								|| !StringUtil.isObjectEmpty(input.getCalculateParam())) {
							if (!StringUtil.isStringEmpty(input.getFeeMode())) {
								if (FeeMode.SEC.equals(input.getFeeMode())) {
									fee.setFloatflag(String.valueOf(FloatFlag.FLOAT_BY_AMOUNT));
								} else {
									fee.setFloatflag(String.valueOf(FloatFlag.STATIC));
								}
								if (FeeMode.FIX.equals(input.getFeeMode()) || FeeMode.MON.equals(input.getFeeMode())
										|| FeeMode.SEC.equals(input.getFeeMode())) {
									fee.setCalculatetyp(String.valueOf(CalculateType.STATIC));
								} else {
									fee.setCalculatetyp(String.valueOf(CalculateType.PERCENT));
								}
							}
							fee.setDeductflag(DeductFlag.OUT_PAY);
							if(!StringUtil.isObjectEmpty(input.getUpperLimitVal()) && !StringUtil.isObjectEmpty(input.getLowerLimitVal())){
								if(input.getUpperLimitVal().compareTo(input.getLowerLimitVal()) == 0){
									fee.setBoundflag(BoundFlag.NO);
								}else{
									fee.setBoundflag(BoundFlag.YES);
								}
								
							}
							fee.setProgressflag(ProgressFlag.NO);
							
							fee.setUpperlimitval(input.getUpperLimitVal());
							fee.setLowerlimitval(input.getLowerLimitVal());
							fee.setCalculateparam(input.getCalculateParam());
							fee.setFeenbr(IDGenerateFactory.generateSeqId());
							fee.setSeqnbr("1");
							FeeparamDAO.insertSelective(fee);
						}
						// 更新商户账务信息表
						Meracctinfo meracct = new Meracctinfo();
						meracct.setSettleaccttyp(AcctTypCd.PRIV);
						meracct.setSettleacctkind(AcctKind.CORE);
						meracct.setClearclassmodecd(ClearClassModeCd.BITE);
						meracct.setFeesettperiod(SettPeriod.DAY);
						meracct.setIsparentmer(IsParentMer.N);
						meracct.setFeeacctkind(AcctKind.CORE);
						meracct.setFeeaccttyp(AcctTypCd.PRIV);
						meracct.setCardtypecd(input.getCardTypeCd());
						meracct.setSupermernbr(input.getMerNbr());
						meracct.setMerplatformnbr(input.getMerPlatformNbr());
						meracct.setMerstatus(MerStatus.CHECK);
						meracct.setMercifnbr(input.getMerCifNbr());
						meracct.setMername(input.getMerName());
						meracct.setPaymodecd(input.getPayModeCd());
						meracct.setSettleacctnbr(input.getSettleAcctNbr());
						meracct.setSettleacctname(input.getSettleAcctName());
						meracct.setFeemode(input.getFeeMode());
						meracct.setFeeacctnbr(input.getFeeAcctNbr());
						meracct.setFeeacctname(input.getFeeAcctName());
						meracct.setBailacctnbr(input.getBailAcctNbr());
						meracct.setBailamt(input.getBailAmt());
						meracct.setSettmode(input.getSettMode());
						meracct.setSettperiod(input.getSettPeriod());
						meracct.setProfitseqnbr(input.getProfitSeqNbr());
						meracct.setFeereturnflag(input.getFeeReturnFlag());
						meracct.setMeropendate(input.getMerOpenDate());
						meracct.setOpenusernbr(input.getOpenUserNbr());
						meracct.setMermodifydate(input.getMerModifyDate());
						meracct.setModifyusernbr(input.getModifyUserNbr());
						meracct.setMerclosedate(input.getMerCloseDate());
						meracct.setCloseuser(input.getCloseUser());
						meracct.setMermngdeptnbr(input.getMerMngDeptNbr());
						meracct.setMerdevelopdeptnbr(input.getMerDevelopDeptNbr());
						meracct.setMeropendeptnbr(input.getMerOpenDeptNbr());
						meracct.setMernbr(input.getSubMerchantId());
						meracct.setFeesettperiod(input.getFeeSettperiod());
						meracct.setInneracctcfmmode(input.getInnerAcctCfmMode());
						if (!StringUtil.isStringEmpty(fee.getFeenbr())) {
							meracct.setFeenbr(fee.getFeenbr());
						}
						MeracctinfoDAO.insertSelective(meracct);
					}

					if (!StringUtil.isStringEmpty(input.getProfitSeqNbr())) {
						// 更新费率控制表
						Profitctrl profit = new Profitctrl();
						profit.setMernbr(input.getSubMerchantId());
						profit.setProfitmode(input.getProfitMode());
						profit.setProfitpayeropenamt(input.getProfitPayEropenAmt());
						profit.setProfitmeropenamt(input.getProfitMerOpenAmt());
						profit.setProfitmerdevelopamt(input.getProfitMerDevelopAmt());
						profit.setProfitheadbankamt(input.getProfitHeadBankAmt());
						profit.setProfitthirdpartamt(input.getProfitThirdPartAmt());

						ProfitctrlExample profitexample = new ProfitctrlExample();
						profitexample.createCriteria().andProfitseqnbrEqualTo(input.getProfitSeqNbr());
						operationStatus = ProfitctrlDAO.updateByExampleSelective(profit, profitexample);
						if (0 == operationStatus) {
							profit.setProfitseqnbr(input.getProfitSeqNbr());
							ProfitctrlDAO.insertSelective(profit);
						} else if (1 == operationStatus) {
							// 空，更新1条，表示执行更新
						} else {
							throw new PeException(DictErrors.TRANS_EXCEPTION);
						}
					}
					if (!StringUtil.isStringEmpty(input.getVirtualAcctNbr())) {
						//验证虚账户名称和提现账户名称是否一致
						if(!StringUtil.isStringEmpty(input.getSettleAcctName())){
							if(input.getSettleAcctName().equals(input.getPayeeAcctName())){
								throw new PeException(DictErrors.ACCT_NAME_IS_NOT_MATCH);
							}
						}
						// 更新第三方账户表
						Merthirdpartacct merthird = new Merthirdpartacct();
						merthird.setVirtualacctnbr(input.getVirtualAcctNbr());
						merthird.setVirtualacctname(input.getSettleAcctName());
						merthird.setAcctnbr(input.getPayeeAcctNbr());
						merthird.setAcctname(input.getPayeeAcctName());
						merthird.setAcctbanknbr(input.getPayeeBankNbr());
						// if(StringUtil.isStringEmpty(input.getPayeeBankNbr())
						// ||
						// (BankoptionDAO.selectByPrimaryKey(BankOptionCd.BANK,
						// 1L).getBankoptionvalue()).equals(input.getPayeeBankNbr())){
						// merthird.setAcctkind(AcctKind.CORE);
						// }else{
						// merthird.setAcctkind(AcctKind.OTHB);
						// }
						merthird.setAcctkind(input.getAcctKind());
						merthird.setVirtualaccttype(AcctTypCd.INNER);
						merthird.setVirtualacctkind(AcctKind.VIRTUAL);

						MerthirdpartacctExample merthirdexample = new MerthirdpartacctExample();
						merthirdexample.createCriteria().andMernbrEqualTo(input.getMerNbr())
								.andSubmernbrEqualTo(input.getSubMerchantId());
						operationStatus = MerthirdpartacctDAO.updateByExampleSelective(merthird, merthirdexample);
						if (0 == operationStatus) {
							merthird.setMernbr(input.getMerNbr());
							merthird.setSubmernbr(input.getSubMerchantId());
							MerthirdpartacctDAO.insertSelective(merthird);
						} else if (1 == operationStatus) {
							// 空，更新1条，表示执行更新
						} else {
							throw new PeException(DictErrors.TRANS_EXCEPTION);
						}
					}

				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}

				return null;
			}
		});

	}

	

	protected void checkSettAcctNo(RespQueryCardInfo hostInfo, String acctType) throws PeException {
		// TODO Auto-generated method stub
		// 校验返回状态
		String status = String.valueOf(hostInfo.getReturncode());
		if (StringUtil.isStringEmpty(status)) {
			throw new PeException(DictErrors.SETT_ACCT_NBR_IS_NULL);
		}
		if (!ResponseCode.SUCCESS.equals(status)) {
			throw new PeException(DictErrors.SETT_ACCT_NBR_ERROR);
		}

		// 校验户名 (不验证)

		// 校验帐户状态
		String payerAcctStatus = String.valueOf(hostInfo.getPayerAcctStatus());
		if (StringUtil.isStringEmpty(payerAcctStatus)) {
			throw new PeException(DictErrors.SETT_ACCT_NBR_STATUS_ERROR);
		}
		if (!AcctStatCode.NORMAL.equals(payerAcctStatus)) {
			throw new PeException(DictErrors.SETT_ACCT_NBR_STATUS_ERROR);
		}

		// 校验帐户挂失状态
		String acctLossStatus = String.valueOf(hostInfo.getAcctLossStatus());
		if (StringUtil.isStringEmpty(acctLossStatus)) {
			throw new PeException(DictErrors.ACCT_LOSS_STATUS_ERROR);
		}
		if (!"1".equals(acctLossStatus)) {
			throw new PeException(DictErrors.ACCT_LOSS_STATUS_ERROR);
		}

		// 校验帐户状态字
		String acctStatusWord = String.valueOf(hostInfo.getAcctStatusWord());
		if (StringUtil.isStringEmpty(acctStatusWord) || 7 > acctStatusWord.length()) {
			throw new PeException(DictErrors.ACCT_STATUS_WORD_ERROR);
		}
		if (!"0000000".equals(acctStatusWord.substring(0, 7))) {
			throw new PeException(DictErrors.ACCT_STATUS_WORD_ERROR);
		}

		// 校验部分冻结，解冻
		String acctFreezeFlag = String.valueOf(hostInfo.getAcctFreezeFlag());
		if (StringUtil.isStringEmpty(acctFreezeFlag) || 2 > acctFreezeFlag.length()) {
			throw new PeException(DictErrors.ACCT_FREEZE_FLAG_ERROR);
		}
		if (!"00".equals(acctFreezeFlag.substring(0, 2))) {
			throw new PeException(DictErrors.ACCT_FREEZE_FLAG_ERROR);
		}

		// 校验帐户性质
		String acctNature = String.valueOf(hostInfo.getAcctNature());
		if (StringUtil.isStringEmpty(acctNature) || 4 > acctNature.length()) {
			throw new PeException(DictErrors.ACCT_NATURE_ERROR);
		}
		if (!"0011".equals(acctNature) && !"0021".equals(acctNature) && !"0022".equals(acctNature)
				&& !"0023".equals(acctNature) && !"0024".equals(acctNature)) {
			throw new PeException(DictErrors.ACCT_NATURE_ERROR);
		}

		if (acctType.equals("Card")) {
			// 校验卡片状态
			String cardStatus = String.valueOf(hostInfo.getCardStatus());
			if (StringUtil.isStringEmpty(cardStatus)) {
				throw new PeException(DictErrors.CARD_STATUS_ERROR);
			}
			if (!"1".equals(cardStatus)) {
				throw new PeException(DictErrors.CARD_STATUS_ERROR);
			}

			// 校验卡状态字
			String cardStatusWord = String.valueOf(hostInfo.getCardStatusWord());
			if (StringUtil.isStringEmpty(cardStatusWord) || 4 > cardStatusWord.length()) {
				throw new PeException(DictErrors.CARD_STATUS_WORD_ERROR);
			}
			if (!"0011".equals(cardStatusWord.substring(0, 4))) {
				throw new PeException(DictErrors.CARD_STATUS_WORD_ERROR);
			}
		}

	}
}


