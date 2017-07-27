package com.csii.upp.batch.appl.qrcodepay;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseCheckAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.CheckUpdateStatus;
import com.csii.upp.dto.extend.PreClearCheckTrans;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.InnerfundtranshistExample;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.util.StringUtil;

public class CheckQrcodePayAppl extends BaseCheckAppl {

	@Override
	protected List<Innercheckapply> getInnerCheckApply(Map<String, Object> argMaps) {
		Date checkDate = this.getCheckDate(argMaps);
		String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		return this.getCheckApplyForCheck(fundChannelCd, checkDate);
	}

	protected CheckUpdateStatus checkDownRtxnNotExist(PreClearCheckTrans preClearCheckTrans, Date intervalDate,String checkdataflag) {
		CheckUpdateStatus updState = new CheckUpdateStatus();
		Overalltranshist overalltranshist = null;
		try {
			overalltranshist = OveralltranshistDAO.selectByPrimaryKey(preClearCheckTrans.getOveralltransnbr());
		} catch (SQLException e) {
			log.error(e);
		}
		
		if (InnerRtxnTyp.AlipayQrCodePassivePay.equals(preClearCheckTrans.getTranscode())
				|| InnerRtxnTyp.WeChatQrCodePassivePay.equals(preClearCheckTrans.getTranscode())) {
			//被扫 支付交易  交易状态 0：成功 1：失败 5：处理中 9：超时
			if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);
			} else if (preClearCheckTrans.getTransdate().before(intervalDate)) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.FAILURE);
				} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			}
		} else if (InnerRtxnTyp.AlipayQrCodeActivePay.equals(preClearCheckTrans.getTranscode())
				|| InnerRtxnTyp.WeChatQrCodeActivePay.equals(preClearCheckTrans.getTranscode())) {
			// 主扫交易   交易状态 0：成功 1：失败 8：待扫码 9：超时(超时未生成二维码为交易失败计差错)
			if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);
			} else if (preClearCheckTrans.getTransdate().before(intervalDate)) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (TransStatus.PENDING.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.FAILURE);
				} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			}
		}else if (InnerRtxnTyp.AlipayRefoundTrans.equals(preClearCheckTrans.getTranscode())
				|| InnerRtxnTyp.WeChatRefoundTrans.equals(preClearCheckTrans.getTranscode())) {
			// 支付宝微信隔日退货
			InnerfundtranshistExample example = new InnerfundtranshistExample();
			example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
					.andTranscodeEqualTo(InnerRtxnTyp.CoreInnerTransfer);
			InnerfundtranshistExample reexample = new InnerfundtranshistExample();
			example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
					.andTranscodeEqualTo(InnerRtxnTyp.QrcodeCoreReInnerTransfer);
			List<Innerfundtranshist> innerfundtranshists=null;
			List<Innerfundtranshist> reinnerfundtranshists=null;
			try {
				innerfundtranshists = InnerfundtranshistDAO.selectByExample(example);
				reinnerfundtranshists = InnerfundtranshistDAO.selectByExample(reexample);
				
			} catch (Exception e) {
				log.error(e);
			}
			if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallTransStatus(TransStatus.FAILURE);
				if(CheckStatus.CHECKED.equals(reinnerfundtranshists.get(0).getCheckstatus())){
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					
				}else{
					//二维码失败，核心冲正失败
					log.debug("二维码交易失败核心冲正失败,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.QRCODECOREREVERSING);
				} 
			} else if (preClearCheckTrans.getTransdate().before(intervalDate)) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
					if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
						//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
						if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
							//二维码失败，核心对账成功，记入差错进行冲正
							log.debug("二维码交易失败核心入账成功,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
							updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
							updState.setCheckErrorType(ErrorTyp.QRCODECOREREVERSING);
						}else{
							updState.setOverallCheckStatus(CheckStatus.CHECKED);
						} 
							
					}
				} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
						//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
						if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
							//二维码失败，核心对账成功，记入差错进行冲正
							log.debug("二维码交易失败核心入账成功,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
							updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
						}else{
							updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
						} 
							
					}
					
				}
			}
		}
		else if (InnerRtxnTyp.AlipayRedoTrans.equals(preClearCheckTrans.getTranscode())
				|| InnerRtxnTyp.WeChatRedoTrans.equals(preClearCheckTrans.getTranscode())) {
			// 支付宝微信当日撤销
			InnerfundtranshistExample example = new InnerfundtranshistExample();
			example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
					.andTranscodeEqualTo(InnerRtxnTyp.CoreInnerTransfer);
			InnerfundtranshistExample reexample = new InnerfundtranshistExample();
			example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
					.andTranscodeEqualTo(InnerRtxnTyp.QrcodeCoreReInnerTransfer);
			List<Innerfundtranshist> innerfundtranshists=null;
			List<Innerfundtranshist> reinnerfundtranshists=null;
			try {
				innerfundtranshists = InnerfundtranshistDAO.selectByExample(example);
				reinnerfundtranshists = InnerfundtranshistDAO.selectByExample(reexample);
				
			} catch (Exception e) {
				log.error(e);
			}
			if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallTransStatus(TransStatus.FAILURE);
				if(CheckStatus.CHECKED.equals(reinnerfundtranshists.get(0).getCheckstatus())){
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					
				}else{
					//二维码失败，核心冲正失败
					log.debug("二维码交易失败核心冲正失败,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.QRCODECOREREVERSING);
				} 
			} else if (preClearCheckTrans.getTransdate().before(intervalDate)) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
						|| TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
					if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
						//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
						if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
							//二维码失败，核心对账成功，记入差错进行冲正
							log.debug("二维码交易失败核心入账成功,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
							updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
							updState.setCheckErrorType(ErrorTyp.QRCODECOREREVERSING);
						}else{
							updState.setOverallCheckStatus(CheckStatus.CHECKED);
						} 
							
					}
				} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					
					if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
						//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
						if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
							//二维码失败，核心对账成功，记入差错进行冲正
							log.debug("二维码交易失败核心入账成功,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
							updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
							updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
						}else{
							updState.setOverallCheckStatus(CheckStatus.CHECKED);
						} 
							
					}
				}
			}
		}else {
			if ((TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus()) || TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus()))) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
				}
			} else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				}
		}
		return updState;
	}

	@Override
	protected CheckUpdateStatus checkDownRtxnExist(PreClearCheckTrans preClearCheckTrans,String checkdataflag) {
		CheckUpdateStatus updState = new CheckUpdateStatus();
		Overalltranshist overalltranshist = null;
		try {
			overalltranshist = OveralltranshistDAO.selectByPrimaryKey(preClearCheckTrans.getOveralltransnbr());
		} catch (SQLException e) {
			log.error(e);
		}
		// 检查金额和账号关键字是否一致
			if (this.isKeyFieldEqual(preClearCheckTrans)) {
				if (InnerRtxnTyp.AlipayQrCodePassivePay.equals(preClearCheckTrans.getTranscode())
						|| InnerRtxnTyp.WeChatQrCodePassivePay.equals(preClearCheckTrans.getTranscode())) {
					//被扫 支付交易  交易状态 0：成功 1：失败 5：处理中 9：超时
					if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
							||TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())) {
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					}else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					}
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
				} else if (InnerRtxnTyp.AlipayQrCodeActivePay.equals(preClearCheckTrans.getTranscode())
						|| InnerRtxnTyp.WeChatQrCodeActivePay.equals(preClearCheckTrans.getTranscode())) {
					// 主扫交易   交易状态 0：成功 1：失败 8：待扫码 9：超时(超时未生成二维码为交易失败计差错)
					if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
						// 平台记成功，只更新对账状态
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					} else if (TransStatus.PENDING.equals(preClearCheckTrans.getTransstatus())) {
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					}else if(TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())){
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					}else if(TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())){
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
					}
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					
				} else if (InnerRtxnTyp.WeChatRefoundTrans.equals(preClearCheckTrans.getTranscode())
						|| InnerRtxnTyp.AlipayRefoundTrans.equals(preClearCheckTrans.getTranscode())) {
					// 支付宝微信隔日退货
					InnerfundtranshistExample example = new InnerfundtranshistExample();
					example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
							.andTranscodeEqualTo(InnerRtxnTyp.CoreInnerTransfer);
					List<Innerfundtranshist> innerfundtranshists=null;
					try {
						innerfundtranshists = InnerfundtranshistDAO.selectByExample(example);
						
					} catch (Exception e) {
						log.error(e);
					}
					if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
						if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
							//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
							if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
								updState.setOverallCheckStatus(CheckStatus.CHECKED);					
							}else{
								//二维码成功，核心交易成功但未对账，记入差错
								log.debug("二维码交易核心未对账,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
								updState.setOverallCheckStatus(CheckStatus.UNCHECKED);									
								updState.setCheckErrorType(ErrorTyp.PRECOREPROCESS);
							} 
								
						}
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						
					} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
							||TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())
							||TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
						if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
							//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
							if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
								updState.setOverallCheckStatus(CheckStatus.CHECKED);					
							}else{
								//二维码成功，核心交易成功但未对账，记入差错
								log.debug("二维码交易核心未对账,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
								updState.setOverallCheckStatus(CheckStatus.UNCHECKED);									
								updState.setCheckErrorType(ErrorTyp.PRECOREPROCESS);
							} 
								
						}
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
					}
				} else if (InnerRtxnTyp.AlipayRedoTrans.equals(preClearCheckTrans.getTranscode())
						|| InnerRtxnTyp.WeChatRedoTrans.equals(preClearCheckTrans.getTranscode())) {
					// 当日退货是撤销交易
					InnerfundtranshistExample example = new InnerfundtranshistExample();
					example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
							.andTranscodeEqualTo(InnerRtxnTyp.CoreInnerTransfer);
					List<Innerfundtranshist> innerfundtranshists=null;
					try {
						innerfundtranshists = InnerfundtranshistDAO.selectByExample(example);
						
					} catch (Exception e) {
						log.error(e);
					}
					if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
						if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
							//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
							if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
								updState.setOverallCheckStatus(CheckStatus.CHECKED);					
							}else{
								//二维码成功，核心交易成功但未对账，记入差错
								log.debug("二维码交易核心未对账,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
								updState.setOverallCheckStatus(CheckStatus.UNCHECKED);									
								updState.setCheckErrorType(ErrorTyp.PRECOREPROCESS);
							} 
								
						}
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
					} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
							||TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())
							||TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
						if (innerfundtranshists != null && innerfundtranshists.size() > 0) {
							//核心状态成功后才会发起二维码前置退货， OverallTransStatus状态为成功
							if(CheckStatus.CHECKED.equals(innerfundtranshists.get(0).getCheckstatus())){
								updState.setOverallCheckStatus(CheckStatus.CHECKED);					
							}else{
								//二维码成功，核心交易成功但未对账，记入差错
								log.debug("二维码交易核心未对账,innerfundtransnbr="+innerfundtranshists.get(0).getInnerfundtransnbr());
								updState.setOverallCheckStatus(CheckStatus.UNCHECKED);									
								updState.setCheckErrorType(ErrorTyp.PRECOREPROCESS);
							} 
								
						}
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
					}
				} else {
					if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
						
					} else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {// 差错类型，对账状态——不平，交易状态人工处理
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
						
					} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
						// 交易状态成功，对账状态——平
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setInnerTransStatus(TransStatus.SUCCESS);
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
						
					}
				}
		}
		else {
			// 关键域不匹配
			updState.setInnerCheckStatus(CheckStatus.UNCHECKEDKEY);
			updState.setDownCheckStatus(CheckStatus.UNCHECKEDKEY);
			updState.setCheckErrorType(ErrorTyp.ARTPROCESS);
			updState.setOverallCheckStatus(CheckStatus.UNCHECKEDKEY);		
		}
		return updState;
		
	}
}
