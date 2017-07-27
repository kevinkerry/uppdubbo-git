package com.csii.upp.batch.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.InnerRtxnTyp;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerpreclearfundtransExtendDAO;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.BatchmersettleDAO;
import com.csii.upp.dao.generate.DownsysfundtranshistDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.Batchmersettle;
import com.csii.upp.dto.generate.Downsysfundtranshist;
import com.csii.upp.dto.generate.DownsysfundtranshistExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.InnerfundtranshistExample;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespCoreQueryRtxn;
import com.csii.upp.dto.router.unionpay.RespUnionPayRefund;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.service.fundprocess.UnionPayService;
import com.csii.upp.util.StringUtil;

/**
 * @author 姜星
 * 
 */
public class PrebackProcessAction extends BaseAction {

	@Override
	public void execute(Context context) throws PeException {
		String errornbr = context.getString(Dict.ERROR_NBR);
		Batchcheckerror record = null;
		RespSysHead outdata = null;
		String downtransnbr = null;

		try {
			record = BatchcheckerrorDAO.selectByPrimaryKey(errornbr);
		} catch (SQLException e) {
			log.error("PrebackProcess Error:" + e.getMessage());
		}
		String errorState = ErrorState.HANDLING;
		if (record != null) {
			try {
				this.updateCheckErrorState(record,errorState);
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
			
			Map<String, Object> map = InnerpreclearfundtransExtendDAO
					.getInnerfundtransForError(errornbr,record.getInnerfundtransnbr());
			if (map != null) {
				map.put(Dict.POST_DATE, SysinfoExtendDAO.getSysInfo().getPostdate());
				String errortype = record.getCheckerrortyp();
				InputFundData inputdata = new InputFundData(map);
				if(ErrorTyp.UPFAILDOWNSUCCESS.equals(errortype)){
					//差错1-内部失败下游有
					String transStatus = TransStatus.SUCCESS;
					errorState = ErrorState.SUCCESS;
					try {
						//OverallTransHist表和InnerFundTransHist表交易状态由失败变更为成功
						this.updateOverallTransHistStatus(record,transStatus);
						this.updateInnerFundTransHistStatus(record,transStatus);
						//更新batchcheckerror表处理状态为处理成功
						this.updateCheckErrorState(record,errorState);
					} catch (SQLException e) {
						log.error("PrebackProcess Error:" + e.getMessage());
					}					
				}else if(ErrorTyp.UPSUCCESSDOWNFAIL.equals(errortype)){
					//差错2-内部成功下游失败
					String transStatus = TransStatus.FAILURE;
					errorState = ErrorState.SUCCESS;
					try {
						//OverallTransHist表和InnerFundTransHist表交易状态由成功变更为失败
						this.updateOverallTransHistStatus(record,transStatus);
						this.updateInnerFundTransHistStatus(record,transStatus);
						//更新batchcheckerror表处理状态为处理成功
						this.updateCheckErrorState(record,errorState);
					} catch (SQLException e) {
						log.error("PrebackProcess Error:" + e.getMessage());
					}					
				}else if(ErrorTyp.DOWNNOTEXIST.equals(errortype)){
					//差错3-内部成功下游无
					String transStatus = TransStatus.FAILURE;
					errorState = ErrorState.SUCCESS;
					try {
						//OverallTransHist表和InnerFundTransHist表交易状态由成功变更为失败
						this.updateOverallTransHistStatus(record,transStatus);
						this.updateInnerFundTransHistStatus(record,transStatus);
						//更新batchcheckerror表处理状态为处理成功
						this.updateCheckErrorState(record,errorState);
					} catch (SQLException e) {
						log.error("PrebackProcess Error:" + e.getMessage());
					}
				}else if(ErrorTyp.PRERVCOREPROCESS.equals(errortype)){
					try {
						String overalltransnbr = (String) map.get(Dict.OVERALL_TRANS_NBR.toLowerCase());
						InnerfundtranshistExample innerfundtranshistExample = new InnerfundtranshistExample();
						innerfundtranshistExample.createCriteria().andOveralltransnbrEqualTo(overalltransnbr).
							andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY).andTranscodeIn(Arrays.asList(InnerRtxnTyp.UnionPayInnerRefoundTrans
									,InnerRtxnTyp.UnionPayRefoundTrans, InnerRtxnTyp.UnionPayCancelTrans)).andTransstatusEqualTo(TransStatus.SUCCESS);
						List<Innerfundtranshist> innerfundtranshists = InnerfundtranshistDAO.selectByExample(innerfundtranshistExample);
						if(innerfundtranshists != null && innerfundtranshists.size() > 0){
							throw new PeException("该笔交易已经存在消费撤销或者退货，并且成功");
						}
					} catch (Exception e1) {
						throw new PeException(e1);
					}
					
					// 银联成功核心失败或未做，做银联退货
					UnionPayService unionpayservice = (UnionPayService) this
							.getRouterService(FundChannelCode.UNIONPAY
									.toLowerCase());
					if (FundChannelCode.CORE.equals(record.getFundchannelcode())) {
						try {
							Innerfundtranshist innerfundtranshist = InnerfundtranshistDAO
									.selectByPrimaryKey(record
											.getInnerfundtransnbr());
							if(innerfundtranshist!=null&&!"".equals(innerfundtranshist)){
								InnerfundtranshistExample example = new InnerfundtranshistExample();
								example.createCriteria().andOveralltransnbrEqualTo(innerfundtranshist.getOveralltransnbr())
										.andFundchannelcodeEqualTo(FundChannelCode.UNIONPAY).andTranscodeNotEqualTo(InnerRtxnTyp.UnionPayCancelTrans);
								List<Innerfundtranshist> innerfundtranshis = InnerfundtranshistDAO.selectByExample(example);
								if(innerfundtranshis!=null&&innerfundtranshis.size()>0){
									 downtransnbr=innerfundtranshis.get(0).getInnerfundtransnbr(); 
								}   
							}
							} catch (SQLException e) {
							log.equals(e.getMessage());
						}

					}else{
						try {
							String originnertransnbr = (String) map.get(Dict.ORIG_INNER_TRANS_NBR.toLowerCase());
							if(StringUtil.isStringEmpty(originnertransnbr)){
								downtransnbr = (String) map.get(Dict.DOWN_TRANS_NBR.toLowerCase());
								inputdata.setOriginnertransnbr((String) map.get(Dict.INNER_FUND_TRANS_NBR.toLowerCase()));
							}else {
								inputdata.setOriginnertransnbr(originnertransnbr);
								Innerfundtranshist innerfundtranshist = InnerfundtranshistDAO.selectByPrimaryKey(originnertransnbr);
								downtransnbr = innerfundtranshist.getDowntransnbr();
							}
							if(StringUtil.isStringEmpty(downtransnbr)){
								DownsysfundtranshistExample downsysfundtranshistExample = new DownsysfundtranshistExample();
								downsysfundtranshistExample.createCriteria().andInnerfundtransnbrEqualTo(inputdata.getOriginnertransnbr());
								List<Downsysfundtranshist> downsysfundtranshists = DownsysfundtranshistDAO.selectByExample(downsysfundtranshistExample);
								if(downsysfundtranshists != null && downsysfundtranshists.size() > 0){
									downtransnbr = downsysfundtranshists.get(0).getDowntransnbr();
								}
							}
							log.info("差错对应原交易流水号originnertransnbr：" + originnertransnbr);
							log.info("差错对应原交易下游流水号downtransnbr：" + downtransnbr);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
				    inputdata.setDowntransnbr(downtransnbr);
				    inputdata.setCheckdataflag(FundChannelCode.UNIONPAY);
					inputdata.setTranstime(new Date());
					String transCode = (String) map.get(Dict.TRANS_CODE
							.toLowerCase());
					if (InnerRtxnTyp.UnionPayQuickPay.equals(transCode)
							|| InnerRtxnTyp.UnionPayCancelTrans.equals(transCode)
							|| InnerRtxnTyp.UnionPayRefoundTrans.equals(transCode)
							|| InnerRtxnTyp.UnionPayInnerRefoundTrans.equals(transCode)) {
						inputdata.setBizType("000301");
					} else {
						if (InnerRtxnTyp.UnionPayOtherPerBankPay.equals(transCode)) {
							inputdata.setBizType("000201");
						} else if (InnerRtxnTyp.UnionPayOtherEBankPay.equals(transCode)) {
							inputdata.setBizType("000202");
						}
					}
					/*
					 * POC测试改动
					 */
					inputdata.setInnerfundtransnbr(null);
					outdata = new RespUnionPayRefund();
					outdata.setRtxnstate(TransStatus.SUCCESS);
//					outdata = unionpayservice.innerRefoundTrans(inputdata);
				}else if(ErrorTyp.PRERVMERCOREPROCESS.equals(errortype)){
					//查询核心商户结算状态
					CoreService coreService = (CoreService)this.getRouterService(FundChannelCode.CORE.toLowerCase());
					RespCoreQueryRtxn output = (RespCoreQueryRtxn) coreService.queryBatchTransferState(inputdata);
					if (output.isTimeout()) {
						throw new PeException(DictErrors.TRANS_TIMEOUT);
					} else if (output.isSuccess() && output.getOrigSeqNbr() != null) {
						//查询得到状态成功，则更新batchcheckerror表和batchmersettle表
						errorState = ErrorState.SUCCESS;
						String sendStatus = SendStatus.SUCCESS;
						try {
							this.updateCheckErrorState(record,errorState);
							this.updateBatchMerSettleSendStatus(record.getInnerfundtransnbr(),sendStatus);
						} catch (SQLException e) {
							log.error("PrebackProcess Error:" + e.getMessage());
						}						
					} else {
						// 组装502380接口的请求要素
						inputdata.setTransid(record.getTranscode());
						Map<String,String> payeeAcctMap=new HashMap<String,String>();
						payeeAcctMap.put(Dict.PAYEE_ACCT_NBR, inputdata.getPayeeacctnbr());
						payeeAcctMap.put(Dict.SUB_TRANS_AMT, StringUtil.parseObjectToString(inputdata.getTransamt()));
						List<Map<String,String>> payeeAcctList = new ArrayList<Map<String, String>>();
						payeeAcctList.add(payeeAcctMap);
						inputdata.setPayeeAcctList(payeeAcctList);
						//查询得到状态失败进行再结算操作
						outdata = coreService.settleMerchant(inputdata);
					}
				}else if(ErrorTyp.PRECOREPROCESS.equals(errortype)){
					//核心手工入账补账
					try {
						// OverallTransHist表和InnerFundTransHist表交易状态变更为成功
						this.updateOverallTransHistStatus(record, TransStatus.SUCCESS);
						this.updateInnerFundTransHistStatus(record, TransStatus.SUCCESS);
						// 更新batchcheckerror表处理状态为处理成功
						this.updateCheckErrorState(record, ErrorState.SUCCESS);
						
						/*
						 * POC测试改动
						 */
						inputdata.setInnerfundtransnbr(null);
						outdata = new RespUnionPayRefund();
						outdata.setRtxnstate(TransStatus.SUCCESS);
					} catch (SQLException e) {
						log.error("PrebackProcess Error:" + e.getMessage());
					}
				}else if(ErrorTyp.PREUNIONPAYPROCESS.equals(errortype)){
					//银联清算
					
				}

				//处理完毕，进行batchcheckerror表的状态更新
				try {
					updateCheckError(outdata, record);
				} catch (SQLException e) {
					log.error("PrebackProcess Error:" + e.getMessage());
				}
			} else {
				throw new PeException(DictErrors.CHECK_ERROR_FAILED);
			}
		}
	}

	private void updateBatchMerSettleSendStatus(String innerfundtransnbr, String sendStatus) throws SQLException {
		Batchmersettle batchMerSettle = new Batchmersettle();
		batchMerSettle.setSettleseqnbr(innerfundtransnbr);
		batchMerSettle.setSendstatus(sendStatus);
		BatchmersettleDAO.updateByPrimaryKeySelective(batchMerSettle);
	}

	private void updateOverallTransHistStatus(Batchcheckerror record, String transStatus) throws SQLException {
		Overalltranshist overallTransHist = new Overalltranshist();
		overallTransHist.setOveralltransstatus(transStatus);
		overallTransHist.setOveralltransnbr(record.getOveralltransnbr());
		OveralltranshistDAO.updateByPrimaryKeySelective(overallTransHist);
	}

	private void updateInnerFundTransHistStatus(Batchcheckerror record, String transStatus) throws SQLException {
		Innerfundtranshist innerFundTransHist = new Innerfundtranshist();
		innerFundTransHist.setTransstatus(transStatus);
		innerFundTransHist.setInnerfundtransnbr(record.getInnerfundtransnbr());
		InnerfundtranshistDAO.updateByPrimaryKeySelective(innerFundTransHist);
	}

	private void updateCheckError(RespSysHead outdata, Batchcheckerror checkerror) throws SQLException {
		// 如果交易成功
		if (outdata != null) {
			if (outdata.isSuccess()) {
				Batchcheckerror error = new Batchcheckerror();
				error.setErrorstatus(ErrorState.SUCCESS);
				error.setErrornbr(checkerror.getErrornbr());
				BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
			} else {
				Batchcheckerror error = new Batchcheckerror();
				error.setErrorstatus(ErrorState.FAILURE);
				error.setErrornbr(checkerror.getErrornbr());
				BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
			}
		}
	}

	/**
	 * 修改Batchcheckerror表的errorState状态
	 * 
	 * @param checerror
	 * @throws SQLException
	 */
	private void updateCheckErrorState(Batchcheckerror checerror, String errorState) throws SQLException {
		Batchcheckerror error = new Batchcheckerror();
		error.setErrornbr(checerror.getErrornbr());
		error.setErrorstatus(errorState);
		BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
	}

	/**
	 * 通过日终差错表获取日间差错信息
	 * 
	 * @param record
	 *            日终差错表
	 * @return
	 */
	// private InputFundData getInputData(Batchcheckerror record) {
	// Overalltrans rtxn = null;
	// try {
	// rtxn = OveralltransDAO.selectByPrimaryKey(record.getOveralltransnbr());
	// } catch (SQLException e1) {
	// log.error(e1.getMessage());
	// }
	// if (rtxn != null) {
	// OverallrequestregExample example = new OverallrequestregExample();
	// example.createCriteria().andUppertransnbrEqualTo(rtxn.getUppertransnbr())
	// .andUppertransdateEqualTo(rtxn.getUppertransdate()).andUppersysnbrEqualTo(rtxn.getUppersysnbr());
	// List<Overallrequestreg> list = null;
	// try {
	// list = OverallrequestregDAO.selectByExample(example);
	// } catch (SQLException e) {
	// log.error("PrebackProcess Error:" + e.getMessage());
	// }
	// if (list != null && list.size() > 0) {
	// return InputFundData.parseInputData(list.get(0));
	// }
	// }
	// return null;
	// }
}
