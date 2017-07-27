package com.csii.upp.batch.appl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.DealFlag;
import com.csii.upp.constant.ErrorState;
import com.csii.upp.constant.ErrorTyp;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.InnerpreclearfundtransDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Batchcheckerror;
import com.csii.upp.dto.generate.BatchcheckerrorExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.Innerpreclearfundtrans;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.util.StringUtil;

/**
 * 
 * 差错处理
 * 
 * @author 姜星
 */
public class CheckErrorAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String fundChannelCd = StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		String dealflag = StringUtil.parseObjectToString(argMaps.get(Dict.CLBZ));
		if(DealFlag.AUTOMATIC.equals(dealflag)){
			prcessCheckError(argMaps, fundChannelCd);
		}else{
			
		}	
	}

	private void prcessCheckError(Map<String, Object> argMaps, String fundChannelCode) {
		BatchcheckerrorExample batchcheckerrorExample = new BatchcheckerrorExample();
		List<String> list = new ArrayList<String>(Arrays.asList(ErrorTyp.DOWNNOTEXIST, ErrorTyp.UPFAILDOWNSUCCESS)); // 银联代扣
		List<String> eslist = new ArrayList<String>(Arrays.asList(ErrorState.PRECHECK, ErrorState.HANDLING));
		batchcheckerrorExample.createCriteria().andCheckerrortypIn(list).andErrorstatusIn(eslist)
				.andFundchannelcodeEqualTo(fundChannelCode);
		try {
			List<Batchcheckerror> errorlist = BatchcheckerrorDAO.selectByExample(batchcheckerrorExample);
			if (errorlist != null && errorlist.size() > 0) {
				for (final Batchcheckerror error : errorlist) {
					// String code = error.getFundchannelcode();
					final String errortype = error.getCheckerrortyp();
					try {
						// Map<String, Object> map =
						// InnerpreclearfundtransExtendDAO.getInnerfundtransForError(errorlist.get(i).getInnerfundtransnbr());
						// RespSysHead outdata = null;
						// if (map != null) {
						// InputFundData regData =
						// this.getInputData(errorlist.get(i));
						// InputFundData inputdata = new InputFundData(map);
						// // 将innerrtxnnbr放置在originnerrtxn中 以便做撤销交易
						// inputdata.setOriginnertransnbr(inputdata.getInnerfundtransnbr());
						// inputdata.setFundchannelcode(code);
						// inputdata.setRecognitionid(regData.getRecognitionid());
						// // 待处理 - 待冲电子账户
						// EAccountService eaService = (EAccountService)
						// this.getRouterService(FundChannelCode.EACCOUNT.toLowerCase());
						// if (ErrorTyp.PREEACCTPROCESS.equals(errortype)) {
						// InputFundData input = new InputFundData();
						// if
						// (InnerRtxnTyp.RVAcctInnerWithdrawal.equals(map.get("rtxntypcd").toString()))
						// {
						// // 付款和收款无需置换
						// input = inputdata;
						// input.setTransdate(this.getPostDate());
						// } else if
						// (InnerRtxnTyp.HvpsCreditRtxn.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.IbpsCreditRtxn.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.DpcPPCreditRtxn.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.CoreInnerAcctToCard.equals(map.get("rtxntypcd").toString()))
						// {
						// // 付款和收款置换
						// input = initInputData(map);
						// }
						// // 转换账户类型
						// input.setPayeeaccttypcd(regData.getPayeraccttypcd());
						// input.setFundchannelcode(FundChannelCode.EACCOUNT);
						// input.setRecognitionid(regData
						// .getRecognitionid());
						// outdata = eaService
						// .eAccountRechargeForcheck(input);
						// }
						// else if
						// (ErrorTyp.PREOTHPROCESS.equals(errortype)) {
						// InputData input = new InputData();
						// input = initInputData(map, input);
						// // 待处理 -代充非电子账户
						// if (FundChannelCode.BILL99.equals(code)) {
						// // 快钱系统 退货交易
						// input.setFundchannelcode(FundChannelCode.BILL99);
						// bill99.MASRFD(input);
						// } else if (FundChannelCode.CORE.equals(code)) {
						// // 借记失败 退回他行卡
						// input.setFundchannelcode(FundChannelCode.CORE);
						// outdata = core.rtdtdrReTraveForCheck(input);
						// } else if (FundChannelCode.HVPS.equals(code)) {
						// // 借记失败 退回他行卡
						// input.setFundchannelcode(FundChannelCode.HVPS);
						// outdata = hvps.STHPDrReTraveForCheck(input);
						// } else if (FundChannelCode.BEPS.equals(code)) {
						// // 借记失败 退回他行卡
						// input.setFundchannelcode(FundChannelCode.BEPS);
						// outdata = beps.STHPDrReTraveForCheck(input);
						// } else if (FundChannelCode.IBPS.equals(code)) {
						// // 借记失败 退回他行卡
						// input.setFundchannelcode(FundChannelCode.IBPS);
						// outdata = Ibps.STIBDrReTraveForCheck(input);
						// }
						// }
						// else if (ErrorTyp.PREBACKPROCESS.equals(errortype)) {
						// if (!FundChannelCode.HVPS.equals(code)) {
						// InputFundData input = new InputFundData();
						// if
						// (InnerRtxnTyp.AcctReChargeSuspend.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.AcctReChargeSuspendIn.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.AcctRecharge.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.AcctReChargeSuspendRV.equals(map.get("rtxntypcd").toString()))
						// {
						// input = initInputData(map);
						// } else if
						// (InnerRtxnTyp.STHPDrReTrave.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.STBPDrRetrave.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.STIBDrReTrave.equals(map.get("rtxntypcd").toString())
						// ||
						// InnerRtxnTyp.CoreWipeout.equals(map.get("rtxntypcd").toString()))
						// {
						// input = inputdata;
						// input.setTransdate(this.getPostDate());
						// }
						// input.setFundchannelcode(FundChannelCode.EACCOUNT);
						// input.setRecognitionid(regData.getRecognitionid());
						// RespEAccountHead output =
						// eaService.doReturnRemittance(input);
						// if (output.isSuccess()) {
						// // 待处理 -代充非电子账户
						// if (FundChannelCode.BILL99.equals(code)) {
						// // 快钱系统 退货交易
						// input.setFundchannelcode(FundChannelCode.BILL99);
						// Bill99Service bill99 = (Bill99Service)
						// this.getRouterService(FundChannelCode.BILL99.toLowerCase());
						// bill99.MASRFD(input);
						// } else if (FundChannelCode.CORE.equals(code)) {
						// input.setFundchannelcode(FundChannelCode.CORE);
						// CoreService core = (CoreService)
						// this.getRouterService(FundChannelCode.CORE.toLowerCase());
						// outdata = core.rtdtdrReTraveForCheck(input);
						// } else if (FundChannelCode.IBPS.equals(code)) {
						// input.setFundchannelcode(FundChannelCode.IBPS);
						// input.setPayeracctdeptnbr(regData.getPayeracctdeptnbr());
						// input.setPayerbanknbr(regData.getPayerbanknbr());
						// IbpsService Ibps = (IbpsService)
						// this.getRouterService(FundChannelCode.IBPS.toLowerCase());
						// outdata = Ibps.STIBDrReTraveForCheck(input);
						// } else if (FundChannelCode.DPC.equals(code)) {
						// } else if (FundChannelCode.BEPS.equals(code)) {
						// input.setPayeracctdeptnbr(regData.getPayeracctdeptnbr());
						// input.setPayerbanknbr(regData.getPayerbanknbr());
						// input.setFundchannelcode(FundChannelCode.BEPS);
						// input.setMsgid(regData.getMsgid());
						// BepsService beps = (BepsService)
						// this.getRouterService(FundChannelCode.BEPS.toLowerCase());
						// outdata = beps.STBPDrRetraveForCheck(input);
						// }
						// } else {
						// this.updateCheckError(output, errorlist.get(i));
						// }
						// }
						// } else if (ErrorTyp.PREUNIONPAY.equals(errortype)) {
						// UnionPayService unionPay = (UnionPayService)
						// this.getRouterService(FundChannelCode.UNIONPAY.toLowerCase());
						// inputdata.setFundchannelcode(FundChannelCode.UNIONPAY);
						// outdata = unionPay.payForAnother(inputdata);
						// } else if
						// (ErrorTyp.PRERVEACCTPROCESS.equals(errortype)) {
						// // 来账、借记单边账需要补账
						// inputdata.setFundchannelcode(FundChannelCode.EACCOUNT);
						// inputdata.setUppertransdate((Date)
						// map.get("upperrtxndate"));
						// inputdata.setUppertransnbr(map.get("upperrtxnnbr").toString());
						// inputdata.setUppersysnbr(FundChannelCode.FDPS);
						// if (FundChannelCode.UNIONPAY.equals(code)) {
						// inputdata.setRecognitionid(FundChannelCode.CUPS);
						// outdata=eaService.dsForCheck(inputdata);
						// }else{
						// outdata = eaService.BZForCheck(inputdata);
						// }
						// } else
						getTransactionTemplate().execute(new TransactionCallback() {

							@Override
							public Object doInTransaction(TransactionStatus arg0) {
								if (ErrorTyp.DOWNNOTEXIST.equals(errortype)) {
									// 单边账差错：平台成功，下游无
									Overalltranshist record = new Overalltranshist();
									record.setOveralltransnbr(error.getOveralltransnbr());
									record.setOveralltransstatus(TransStatus.FAILURE);
									try {
										OveralltranshistDAO.updateByPrimaryKeySelective(record);
									} catch (SQLException e1) {
										log.error(e1.getMessage());
									}

									Innerfundtranshist inner = new Innerfundtranshist();
									inner.setInnerfundtransnbr(error.getInnerfundtransnbr());
									inner.setTransstatus(TransStatus.FAILURE);
									try {
										InnerfundtranshistDAO.updateByPrimaryKeySelective(inner);
									} catch (SQLException e) {
										log.error(e.getMessage());
									}

									Innerpreclearfundtrans clear = new Innerpreclearfundtrans();
									clear.setInnerfundtransnbr(error.getInnerfundtransnbr());
									clear.setTransstatus(TransStatus.FAILURE);
									try {
										InnerpreclearfundtransDAO.updateByPrimaryKeySelective(clear);
									} catch (SQLException e) {
										log.error(e.getMessage());
									}
								} else if (ErrorTyp.UPFAILDOWNSUCCESS.equals(errortype)) {
									// 单边账差错：平台失败下游成功
									Overalltranshist record = new Overalltranshist();
									record.setOveralltransnbr(error.getOveralltransnbr());
									record.setOveralltransstatus(TransStatus.SUCCESS);
									try {
										OveralltranshistDAO.updateByPrimaryKeySelective(record);
									} catch (SQLException e) {
										log.error(e.getMessage());
									}

									Innerfundtranshist inner = new Innerfundtranshist();
									inner.setInnerfundtransnbr(error.getInnerfundtransnbr());
									inner.setTransstatus(TransStatus.SUCCESS);
									try {
										InnerfundtranshistDAO.updateByPrimaryKeySelective(inner);
									} catch (SQLException e) {
										log.error(e.getMessage());
									}

									Innerpreclearfundtrans clear = new Innerpreclearfundtrans();
									clear.setInnerfundtransnbr(error.getInnerfundtransnbr());
									clear.setTransstatus(TransStatus.SUCCESS);
									try {
										InnerpreclearfundtransDAO.updateByPrimaryKeySelective(clear);
									} catch (SQLException e) {
										log.error(e.getMessage());
									}
								}

								// } else {
								// throw new
								// PeException(DictErrors.CHECK_ERROR_FAILED);
								// }
								error.setErrorstatus(ErrorState.SUCCESS);
								try {
									BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
								} catch (SQLException e) {
									log.error(e.getMessage());
								}
								return null;

							}
						});
					} catch (Exception e) {

					}
				}
			}
		} catch (Exception e) {
			log.error("CheckErrorAppl Error:" + e.getMessage());
		}
	}

	// private void updateCheckError(RespSysHead outdata, Batchcheckerror
	// checerror)
	// throws SQLException {
	// // 如果交易成功
	// if (outdata != null) {
	// if (outdata.isSuccess()) {
	// Batchcheckerror error = new Batchcheckerror();
	// error.setErrorstatus(ErrorState.SUCCESS);
	// error.setErrornbr(checerror.getErrornbr());
	// // Overalltranshist hist = new Overalltranshist();
	// // hist.setOveralltransnbr(checerror.getOveralltransnbr());
	// // hist.setOveralltransstate(TransStatus.SUCCESS);
	// BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
	// // OveralltranshistDAO.updateByPrimaryKeySelective(hist);
	// } else {
	// // 如果交易失败
	// Batchcheckerror error = new Batchcheckerror();
	// error.setErrorstatus(ErrorState.FAILURE);
	// error.setErrornbr(checerror.getErrornbr());
	// BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
	// }
	// }
	// }

	/**
	 * 正在处理中状态
	 * 
	 * @param checerror
	 * @throws SQLException
	 */
	// private void updateCheckErrorState(Batchcheckerror checerror)
	// throws SQLException {
	// Batchcheckerror error = new Batchcheckerror();
	// error.setInnerfundtransnbr(checerror.getInnerfundtransnbr());
	// error.setErrorstatus(ErrorState.HANDLING);
	// error.setErrornbr(checerror.getErrornbr());
	// BatchcheckerrorDAO.updateByPrimaryKeySelective(error);
	// }

	/**
	 * 
	 * @param map
	 * @param inputdata
	 * @return
	 */
	// private InputFundData initInputData(Map<String, Object> map) {
	// Date postDate = SysinfoExtendDAO.getSysInfo().getPostdate();
	// InputFundData data = new InputFundData(map);
	// data.exchangePaperAndPayeeAcctNbr();
	// data.setTransdate(postDate);
	// return data;
	// }

	// private void insertBatchErorr(InputFundData inputdata, Map<String,
	// Object> map) {
	// // 人工处理
	// Batchcheckerror checkError = new Batchcheckerror();
	// checkError.setCheckerrortyp(ErrorTyp.PREBACKPROCESS);
	// checkError.setTransstatus(RtxnState.FAILURE);
	// checkError.setInnerfundtransnbr(inputdata.getInnerfundtransnbr());
	// checkError.setTransdate(SysinfoExtendDAO.getSysInfo().getPostdate());
	// checkError.setOveralltransnbr(inputdata.getOveralltransnbr());
	// checkError.setTranscode(map.get("rtxntypcd").toString());
	// checkError.setPayeeacctnbr(inputdata.getPayeeacctnbr());
	// checkError.setPayeename(inputdata.getPayeename());
	// checkError.setPayeracctnbr(inputdata.getPayeracctnbr());
	// checkError.setPayername(inputdata.getPayername());
	// checkError.setTransamt(StringUtil.parseBigDecimal(map.get("transAmt")));
	// checkError.setCurrencycd(inputdata.getCurrencycd());
	// checkError.setFundchannelcode(inputdata.getFundchannelcode());
	// checkError.setErrorstatus(ErrorState.PRECHECK);
	// BatchcheckerrorExtendDAO.insertBatchcheckerror(checkError);
	// }

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
	// rtxn = OveralltransDAO
	// .selectByPrimaryKey(record.getOveralltransnbr());
	// } catch (SQLException e1) {
	// log.error(e1.getMessage());
	// }
	// if (rtxn != null) {
	// OverallrequestregExample example = new OverallrequestregExample();
	// example.createCriteria()
	// .andUppertransnbrEqualTo(rtxn.getUppertransnbr())
	// .andUppertransdateEqualTo(rtxn.getUppertransdate())
	// .andUppersysnbrEqualTo(rtxn.getUppersysnbr());
	// List<Overallrequestreg> list = null;
	// try {
	// list = OverallrequestregDAO.selectByExample(example);
	// } catch (SQLException e) {
	// log.error("CheckErrorAppl Error:" + e.getMessage());
	// }
	// if (list != null && list.size() > 0) {
	// return InputFundData.parseInputData(list.get(0));
	// }
	// }
	// return null;
	// }
}
