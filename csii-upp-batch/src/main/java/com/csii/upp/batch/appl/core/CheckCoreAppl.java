package com.csii.upp.batch.appl.core;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.upp.batch.appl.base.BaseCheckAppl;
import com.csii.upp.constant.CheckDataFlag;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.InteralFlag;
import com.csii.upp.constant.OveralltransTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.InnerpreclearfundtransDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.CheckUpdateStatus;
import com.csii.upp.dto.extend.PreClearCheckTrans;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.InnerfundtranshistExample;
import com.csii.upp.dto.generate.Innerpreclearfundtrans;
import com.csii.upp.dto.generate.InnerpreclearfundtransExample;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 数据核对 姜星
 */
public class CheckCoreAppl extends BaseCheckAppl {
	
	@Override
	protected List<Innercheckapply> getInnerCheckApply(Map<String, Object> argMaps) {
		Date checkDate = this.getCheckDate(argMaps);
		if (!this.isCoreCheckData(argMaps)) {
			checkDate = DateUtil.addDate(checkDate, 1);
		}
		String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		return this.getCheckApplyForCheck(fundChannelCd, checkDate);
	}

	/*
	 * 下游没有相关交易流水时情况 如果在intervalDate 内 则 按照设置
	 * 
	 * @see
	 * com.csii.bank.pay.batch.appl.base.BaseCheckAppl#checkDownRtxnNotExist
	 * (com.csii.bank.pay.dto.generate.Innerpreclearfundtrans, java.util.Date)
	 */
	protected CheckUpdateStatus checkDownRtxnNotExist(PreClearCheckTrans preClearCheckTrans, Date intervalDate,String checkdataflag) {
		Overalltranshist overalltranshist = null;
		try {
			overalltranshist = OveralltranshistDAO.selectByPrimaryKey(preClearCheckTrans.getOveralltransnbr());
		} catch (SQLException e) {
			log.error(e);
		}
		CheckUpdateStatus updState = new CheckUpdateStatus();
		if (InnerRtxnTyp.QrcodeCoreInnerTransfer.equals(preClearCheckTrans.getTranscode())) {
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
		}else if (InnerRtxnTyp.CoreInnerTransfer.equals(preClearCheckTrans.getTranscode())) {
			if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
//				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
//				}
			} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			} else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
			}
		} else if (InnerRtxnTyp.CoreRefoundTrans4UnionPay.equals(preClearCheckTrans.getTranscode())) {
			if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
				}
			} else if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			} else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setOverallTransStatus(TransStatus.FAILURE);
			}
			//积分交易
		} else if((InnerRtxnTyp.CoreDeditPayment.equals(preClearCheckTrans.getTranscode())||InnerRtxnTyp.CoreCreditPayment.equals(preClearCheckTrans.getTranscode()))
				&& InteralFlag.YES.equals(overalltranshist.getPointdataflag())){//积分客户资金扣账交易
			if(TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())){//失败
				Innerfundtranshist innerfundtransHistPointCancel = getInnerfundtransByTransCode(InnerRtxnTyp.PointCancel, overalltranshist.getOveralltransnbr());//积分撤销交易
				//String PointCancelTransStatus = innerfundtransHistPointCancel!=null?innerfundtransHistPointCancel.getTransstatus():"";
				/**if(!TransStatus.SUCCESS.equals(PointCancelTransStatus)){//积分冲正失败或超时**/
					/**查询积分消费交易获取清算日期**/
					Innerfundtranshist innerfundtransHistPointConsume = getInnerfundtransByTransCode(InnerRtxnTyp.PointConsume, overalltranshist.getOveralltransnbr());//积分撤销交易
					innerfundtransHistPointCancel.setCleardate(innerfundtransHistPointConsume.getCleardate());
					//积分冲正交易状态改成成功
					updateInnerfundtransStatus(TransStatus.SUCCESS,innerfundtransHistPointCancel);
				//}
				updState.setOverallCheckStatus(CheckStatus.CHECKED);	
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);				
			}else if(TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())){
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);
				}
			}else if(TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())){//日终对账失败
				//将积分交易状态修改成失败，提供给积分网关对账
				Innerfundtranshist innerfundtransHistPointConsume = getInnerfundtransByTransCode(InnerRtxnTyp.PointConsume,overalltranshist.getOveralltransnbr());
				//积分消费交易状态改称失败
				if(innerfundtransHistPointConsume.getCleardate().compareTo(preClearCheckTrans.getTransdate())<=0){
				//核心日期<=支付平台日期，说明当前交易核心已提供对账文件
					updateInnerfundtransStatus(TransStatus.FAILURE, innerfundtransHistPointConsume);
				}
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerTransStatus(TransStatus.FAILURE);
					//记入差错表，手工发起营销资金冲正
					updState.setCheckErrorType(ErrorTyp.POINTCUSTOMERPAYETIMEOUT);//扣客户账超时导致营销资金冲正
					//将积分交易状态修改成失败，提供给积分网关对账
					innerfundtransHistPointConsume = getInnerfundtransByTransCode(InnerRtxnTyp.PointConsume,overalltranshist.getOveralltransnbr());
					//积分消费交易状态改称失败
					updateInnerfundtransStatus(TransStatus.FAILURE, innerfundtransHistPointConsume);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);	
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.FAILURE);					
				}
			}
			
		} else if(InnerRtxnTyp.CoreIntegalPay.equals(preClearCheckTrans.getTranscode())){//营销资金扣减 && OveralltransTyp.UPAY.equals(overalltranshist.getOveralltranstypcd())
			if(TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())){//营销资金扣减失败
				if(OveralltransTyp.UPAY.equals(overalltranshist.getOveralltranstypcd())){//支付交易更新积分撤销交易状态
					Innerfundtranshist innerfundtransHistPointCancel= getInnerfundtransByTransCode(InnerRtxnTyp.PointCancel, overalltranshist.getOveralltransnbr());//积分撤销交易
					//if(!TransStatus.SUCCESS.equals(innerfundtransHistPointCancel.getTransstatus())){
						//根据积分消费获取积分撤销交易清算日期
						Innerfundtranshist innerfundtransHistPointConsume= getInnerfundtransByTransCode(InnerRtxnTyp.PointConsume, overalltranshist.getOveralltransnbr());//积分撤销交易
						innerfundtransHistPointCancel.setCleardate(innerfundtransHistPointConsume.getCleardate());
						//更新积分撤销交易状态
						updateInnerfundtransStatus(TransStatus.SUCCESS, innerfundtransHistPointCancel);		
					//}
				}
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);	
				updState.setOverallTransStatus(TransStatus.FAILURE);
			}else if(TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())){//超时
				//营销资金扣减失败
				if(OveralltransTyp.UPAY.equals(overalltranshist.getOveralltranstypcd())){//支付交易更新积分撤销交易状态
					Innerfundtranshist innerfundtransHistPointCancel= getInnerfundtransByTransCode(InnerRtxnTyp.PointCancel, overalltranshist.getOveralltransnbr());//积分撤销交易
					//if(!TransStatus.SUCCESS.equals(innerfundtransHistPointCancel.getTransstatus())){
						//根据积分消费获取积分撤销交易清算日期
						Innerfundtranshist innerfundtransHistPointConsume= getInnerfundtransByTransCode(InnerRtxnTyp.PointConsume, overalltranshist.getOveralltransnbr());//积分撤销交易
						innerfundtransHistPointCancel.setCleardate(innerfundtransHistPointConsume.getCleardate());
						//更新积分撤销交易状态							
						updateInnerfundtransStatus(TransStatus.SUCCESS, innerfundtransHistPointCancel);		
					//}
				}	
				
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);	
					updState.setOverallTransStatus(TransStatus.FAILURE);					
				}
							
			}  else
			if(TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())){
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					//判断是否存在营销资金冲正交易，如果存在，说明营销资金冲正成功
					Innerfundtranshist innerfundtransHistCoreExWipeout= getInnerfundtransByTransCode(InnerRtxnTyp.CoreExWipeout, overalltranshist.getOveralltransnbr());//营销资金撤销交易
					if(innerfundtransHistCoreExWipeout!=null){//存在营销资金冲正
						//修改对账状态为已对账
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						//修改交易状态为已撤销
						updState.setInnerTransStatus(TransStatus.REVOKED);
						//营销资金冲正为超时，更新为冲正成功
						if(TransStatus.TIMEOUT.equals(innerfundtransHistCoreExWipeout.getTransstatus())){
							//更新清算日期
							innerfundtransHistCoreExWipeout.setCleardate(preClearCheckTrans.getCleardate());
							updateInnerfundtransStatus(TransStatus.SUCCESS, innerfundtransHistCoreExWipeout);
						}					
					}else {
						//营销资金冲正不存在，记入差错
						updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
						updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
						updState.setCheckErrorType(ErrorTyp.DOWNNOTEXIST);				
					}					
				}
				

			}else if(TransStatus.REVOKED.equals(preClearCheckTrans.getTransstatus())){//撤销成功
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
			}
			
		}else if(InnerRtxnTyp.CoreExWipeout.equals(preClearCheckTrans.getTranscode())){//营销资金冲正
			if(TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus()) ){
				//只修改对账状态，超时情况在营销资金扣减时处理
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
			}else if( TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus()) 
					||  TransStatus.REVOKFAIL.equals(preClearCheckTrans.getTransstatus())  ){//冲正失败，记入差错
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setCheckErrorType(ErrorTyp.POINTCOREEXWIPEOUTFAIL);
			}
			
		} else if((InnerRtxnTyp.CoreDeditRefoundTrans.equals(preClearCheckTrans.getTranscode())||InnerRtxnTyp.CoreCreditRefoundTrans.equals(preClearCheckTrans.getTranscode()))
				&& InteralFlag.YES.equals(overalltranshist.getPointdataflag())){//积分客户资金退款
			if(TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())){//客户资金退款失败
				updState.setOverallTransStatus(TransStatus.FAILURE);
				updState.setOverallCheckStatus(CheckStatus.CHECKED);
				updState.setInnerTransStatus(TransStatus.FAILURE);
				updState.setInnerCheckStatus(CheckStatus.CHECKED);				
			}
			else if(TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())){//客户资金退款超时
				//记入差错表，手工调账
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setCheckErrorType(ErrorTyp.POINTCUSTOMERWITHDRAWTIMEOUT);//客户资金扣减超时导致营销资金冲正
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.FAILURE);
					updState.setOverallCheckStatus(CheckStatus.CHECKED);						
				}				

			}else if(TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())){
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
				}				
			}
			
		} else {
			if ((TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus()) || TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus()))) {
				// 如果内部待清算流水交是intervalDate前(包括)的数据，设置内部带清算交易流水交易状态为失败，总交易为失败，对账状态为已经对平
				if (preClearCheckTrans.getTransdate().before(intervalDate)) {
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.FAILURE);
					updState.setOverallTransStatus(TransStatus.FAILURE);
					if (CheckDataFlag.CORE.equals(checkdataflag)) {
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
					}
				}
			} else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
				updState.setInnerCheckStatus(CheckStatus.CHECKED);
				updState.setOverallTransStatus(TransStatus.FAILURE);
				if (CheckDataFlag.CORE.equals(checkdataflag)) {
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
				}
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
			if (InnerRtxnTyp.QrcodeCoreInnerTransfer.equals(preClearCheckTrans.getTranscode())) {
				if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
					if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
					} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())
							||TransStatus.PROCESSING.equals(preClearCheckTrans.getTransstatus())) {
						updState.setInnerTransStatus(TransStatus.FAILURE);
						updState.setOverallTransStatus(TransStatus.FAILURE);
						updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
						updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
						updState.setCheckErrorType(ErrorTyp.QRCODECOREREVERSING);
					}else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
						updState.setInnerTransStatus(TransStatus.FAILURE);
						updState.setOverallTransStatus(TransStatus.FAILURE);
						updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
						updState.setOverallCheckStatus(CheckStatus.UNCHECKED);
						updState.setCheckErrorType(ErrorTyp.QRCODECOREREVERSING);
					}
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					
				}
			}else if (InnerRtxnTyp.CoreInnerTransfer.equals(preClearCheckTrans.getTranscode())) {
				if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
					updState.setOverallTransStatus(TransStatus.SUCCESS);
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
				} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setOverallTransStatus(TransStatus.SUCCESS);
				}else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setDownCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.UPFAILDOWNSUCCESS);
				}
			} else if (InnerRtxnTyp.CoreRefoundTrans4UnionPay.equals(preClearCheckTrans.getTranscode())) {
				if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {
					try {
						InnerfundtranshistExample example = new InnerfundtranshistExample();
						example.createCriteria().andOveralltransnbrEqualTo(overalltranshist.getOveralltransnbr())
								.andTranscodeEqualTo(InnerRtxnTyp.UnionPayRefoundTrans);
						List<Innerfundtranshist> innerfundtranshists = InnerfundtranshistDAO.selectByExample(example);
						if (innerfundtranshists != null && innerfundtranshists.size() < 1) {
							updState.setCheckErrorType(ErrorTyp.PRERVCOREPROCESS);
						}
						updState.setOverallTransStatus(TransStatus.SUCCESS);
						updState.setDownCheckStatus(CheckStatus.CHECKED);
						updState.setInnerCheckStatus(CheckStatus.CHECKED);
					} catch (Exception e) {
						log.error(e);
					}
				} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setOverallTransStatus(TransStatus.SUCCESS);
					updState.setCheckErrorType(ErrorTyp.PRERVCOREPROCESS);
				}else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {
					updState.setInnerCheckStatus(CheckStatus.UNCHECKED);
					updState.setCheckErrorType(ErrorTyp.UPFAILDOWNSUCCESS);
				}
			} else if(!InteralFlag.YES.equals(preClearCheckTrans.getPointdataflag()) 
					&& InteralFlag.YES.equals(overalltranshist.getPointdataflag())){//积分客户资金扣账交易
				if(TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())){//超时
					//修改交易状态为成功
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setOverallTransStatus(TransStatus.SUCCESS);	
				}else if(TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())){
					// 差错类型，对账状态——不平，交易状态人工处理
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setOverallTransStatus(TransStatus.SUCCESS);									
				}else if(TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())){
					// 平台记成功，只更新对账状态
					updState.setOverallCheckStatus(CheckStatus.CHECKED);
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
				}

			} else if(InnerRtxnTyp.CoreIntegalPay.equals(preClearCheckTrans.getTranscode())){//营销资金扣减成功且未发生撤销
				if(TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())){//
					updState.setDownCheckStatus(CheckStatus.CHECKED);//
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setCheckErrorType(ErrorTyp.POINTCOREINTEGALPAYTIMEOUT);
					//根据支付、退货判断
					if(OveralltransTyp.UPAY.equals(overalltranshist.getOveralltranstypcd())){//
						//支付更新积分撤销交易状态为成功
						Innerfundtranshist innerfundtransHistPointCancel= getInnerfundtransByTransCode(InnerRtxnTyp.PointCancel, overalltranshist.getOveralltransnbr());//营销资金撤销
						//if(!TransStatus.SUCCESS.equals(innerfundtransHistPointCancel.getTransstatus())){
							//根据积分消费交易查询清算日期
							Innerfundtranshist innerfundtransHistPointConsume= getInnerfundtransByTransCode(InnerRtxnTyp.PointConsume, overalltranshist.getOveralltransnbr());//营销资金撤销
							innerfundtransHistPointCancel.setCleardate(innerfundtransHistPointConsume.getCleardate());
							updateInnerfundtransStatus(TransStatus.SUCCESS, innerfundtransHistPointCancel);
						//}
					}
				}else if(TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())){
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);	
					//判断是否存在营销资金冲正交易，如果存在，且为超时，设置成失败
					Innerfundtranshist innerfundtransHistCoreExWipeout= getInnerfundtransByTransCode(InnerRtxnTyp.CoreExWipeout, overalltranshist.getOveralltransnbr());//营销资金撤销
					
					if(innerfundtransHistCoreExWipeout!=null){//存在营销资金冲正
						//营销资金冲正为超时，更新为冲正失败
						if(TransStatus.TIMEOUT.equals(innerfundtransHistCoreExWipeout.getTransstatus())){
							innerfundtransHistCoreExWipeout.setCleardate(preClearCheckTrans.getCleardate());
							updateInnerfundtransStatus(TransStatus.FAILURE, innerfundtransHistCoreExWipeout);
							//记差错,手工处理
							updState.setCheckErrorType(ErrorTyp.POINTCOREINTEGALPAYTIMEOUT);
						}					
					}					
				}else if(TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())){
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
				}
				
			} else {
				if (TransStatus.SUCCESS.equals(preClearCheckTrans.getTransstatus())) {// 平台记成功，只更新对账状态
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setOverallTransStatus(TransStatus.SUCCESS);
					if (CheckDataFlag.CORE.equals(checkdataflag)) {
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
					}
				} else if (TransStatus.FAILURE.equals(preClearCheckTrans.getTransstatus())) {// 差错类型，对账状态——不平，交易状态人工处理
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setOverallTransStatus(TransStatus.SUCCESS);
					if (CheckDataFlag.CORE.equals(checkdataflag)) {
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
					}
				} else if (TransStatus.TIMEOUT.equals(preClearCheckTrans.getTransstatus())) {
					// 交易状态成功，对账状态——平
					updState.setDownCheckStatus(CheckStatus.CHECKED);
					updState.setInnerCheckStatus(CheckStatus.CHECKED);
					updState.setInnerTransStatus(TransStatus.SUCCESS);
					updState.setOverallTransStatus(TransStatus.SUCCESS);
					if (CheckDataFlag.CORE.equals(checkdataflag)) {
						updState.setOverallCheckStatus(CheckStatus.CHECKED);
					}
				}
			}
		} else {
			// 关键域不匹配
			updState.setInnerCheckStatus(CheckStatus.UNCHECKEDKEY);
			updState.setDownCheckStatus(CheckStatus.UNCHECKEDKEY);
			updState.setCheckErrorType(ErrorTyp.ARTPROCESS);
			updState.setOverallCheckStatus(CheckStatus.UNCHECKEDKEY);
		}

		return updState;
	}
	
	
	/**
	 * 查询资金交易
	 */
	private Innerfundtranshist getInnerfundtransByTransCode(String transCode,String overalltransNbr){
		InnerfundtranshistExample example = new InnerfundtranshistExample();
		example.createCriteria().andOveralltransnbrEqualTo(overalltransNbr);				
		List<Innerfundtranshist> innerfundtranshists = null;
		try {
			innerfundtranshists = InnerfundtranshistDAO.selectByExample(example);
		} catch (SQLException e) {
			log.error("查询资金交易失败，overalltransnbr="+overalltransNbr);
			e.printStackTrace();
		}
		Innerfundtranshist resultinnerfundtransHist= null;
		for(Innerfundtranshist innerfundtransHist :innerfundtranshists){
			if(transCode.equals(innerfundtransHist.getTranscode())){
				resultinnerfundtransHist = innerfundtransHist;
				break;
			}
		}			
		return resultinnerfundtransHist;
		
	}
	
	private void updateInnerfundtransStatus(String transStatus,Innerfundtranshist innerfundtrans){
		Innerfundtranshist innerfundtransHist = new Innerfundtranshist();
		innerfundtransHist.setTransstatus(transStatus);
		innerfundtransHist.setCheckstatus(CheckStatus.CHECKED);
		//更新清算日期
		innerfundtransHist.setCleardate(innerfundtrans.getCleardate());
		InnerfundtranshistExample example = new InnerfundtranshistExample();
		example.createCriteria().andInnerfundtransnbrEqualTo(innerfundtrans.getInnerfundtransnbr());
		try {
			InnerfundtranshistDAO.updateByExampleSelective(innerfundtransHist, example);
		} catch (SQLException e) {
			log.error("修改资金交易状态失败，innerfundtransnbr="+innerfundtrans.getInnerfundtransnbr());
			e.printStackTrace();
		}		
		//更新innerpreclearfundtrans表,主要针对积分抵扣资金
		if(!FundChannelCode.JFWG.equals(innerfundtrans.getCheckdataflag()) ){
			Innerpreclearfundtrans innerpreclearfundtrans = new Innerpreclearfundtrans(); 
			innerpreclearfundtrans.setTransstatus(transStatus);
			innerpreclearfundtrans.setCheckstatus(CheckStatus.CHECKED);
			InnerpreclearfundtransExample example1 = new InnerpreclearfundtransExample();
			example1.createCriteria().andInnerfundtransnbrEqualTo(innerfundtrans.getInnerfundtransnbr());
			try {
				InnerpreclearfundtransDAO.updateByExampleSelective(innerpreclearfundtrans, example1);
			} catch (SQLException e) {
				log.error("修改资金交易预清算状态失败，innerfundtransnbr="+innerfundtrans.getInnerfundtransnbr());
				e.printStackTrace();
			}				
		}
	}
}
