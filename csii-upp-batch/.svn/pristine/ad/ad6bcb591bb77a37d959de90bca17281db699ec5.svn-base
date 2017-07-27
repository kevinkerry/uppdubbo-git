package com.csii.upp.batch.appl.cups;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCheckFileParseAppl;
import com.csii.upp.constant.ConstCups;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.ProcessState;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 银联对账文件下载、解析、入库
 * 
 * @author xujin
 * 
 */
public class CheckFileParseCupsAppl extends BaseCheckFileParseAppl {

	private String fundChannelCd;

	@Override
	protected String getFundChannelCd(Map<String, Object> argMaps) {
		return FundChannelCode.CUPS;
	}

	@Override
	protected void insertData(List<Map<String, Object>> rtxnMaps,Map<String, Object> argMaps,
			String fileName) throws PeException {
		BigDecimal payerAmount = BigDecimal.ZERO;
		BigDecimal payeeAmount = BigDecimal.ZERO;
		Map resultMap = new HashMap();
		resultMap.put("fundchanelcode", FundChannelCode.CUPS);
		if (fileName.endsWith(ConstCups.ILFEE)) {
			resultMap.put("processtypcd", ConstCups.ILFEE);
			for (Map<String, Object> map : rtxnMaps) {
				payerAmount = payerAmount.add(StringUtil.parseBigDecimal(map
						.get("transamt")));
				;
			}
			if (payerAmount.compareTo(BigDecimal.ZERO) != 0) {
				resultMap.put("payerfeeamt",
						StringUtil.parseObjectToString(payerAmount));
				this.insertPreprocessfundtrans(resultMap, ProcessState.PREPROC);
			}
		} else if (fileName.endsWith(ConstCups.FCP)) {
			resultMap.put("processtypcd", ConstCups.FCP);
			for (Map<String, Object> map : rtxnMaps) {

				String rtxnTypCd = StringUtil.parseObjectToString(map
						.get("rtxntypcd"));
				if ("E30".equals(rtxnTypCd)) {
					payerAmount = payerAmount.add(StringUtil
							.parseBigDecimal(map.get("transamt")));
				} else if ("E20".equals(rtxnTypCd)) {
					payeeAmount = payeeAmount.add(StringUtil
							.parseBigDecimal(map.get("transamt")));
				}
			}
			if (payerAmount.compareTo(BigDecimal.ZERO) != 0
					|| payeeAmount.compareTo(BigDecimal.ZERO) != 0) {
				resultMap.put("payerfeeamt",
						StringUtil.parseObjectToString(payerAmount));
				resultMap.put("payeefeeamt",
						StringUtil.parseObjectToString(payeeAmount));
				this.insertPreprocessfundtrans(resultMap, ProcessState.PREPROC);
			}
		} else if (fileName.endsWith(ConstCups.IPED)) {
			resultMap.put("processtypcd", ConstCups.IPED);
			for (Map<String, Object> map : rtxnMaps) {
				payerAmount = payerAmount.add(StringUtil.parseBigDecimal(map
						.get("payerfeeamt")));
				payerAmount = payerAmount.add(StringUtil.parseBigDecimal(map
						.get("throConnFeeAmt")));
				payeeAmount = payeeAmount.add(StringUtil.parseBigDecimal(map
						.get("payeefeeamt")));
			}
			if (payerAmount.compareTo(BigDecimal.ZERO) != 0
					|| payeeAmount.compareTo(BigDecimal.ZERO) != 0) {
				resultMap.put("payerfeeamt",
						StringUtil.parseObjectToString(payerAmount));
				resultMap.put("payeefeeamt",
						StringUtil.parseObjectToString(payeeAmount));
				this.insertPreprocessfundtrans(resultMap, ProcessState.PREPROC);
			}
		} else if (fileName.endsWith(ConstCups.IERRPED)) {
			resultMap.put("processtypcd", ConstCups.IERRPED);
			for (Map<String, Object> map : rtxnMaps) {
				payerAmount = payerAmount.add(StringUtil.parseBigDecimal(map
						.get("payerfeeamt")));
				payerAmount = payerAmount.add(StringUtil.parseBigDecimal(map
						.get("sndInsPayerFeeAmt")));
				payeeAmount = payeeAmount.add(StringUtil.parseBigDecimal(map
						.get("payeefeeamt")));
				payeeAmount = payeeAmount.add(StringUtil.parseBigDecimal(map
						.get("sndInsPayeeFeeAmt")));
			}
			if (payerAmount.compareTo(BigDecimal.ZERO) != 0
					|| payeeAmount.compareTo(BigDecimal.ZERO) != 0) {
				resultMap.put("payerfeeamt",
						StringUtil.parseObjectToString(payerAmount));
				resultMap.put("payeefeeamt",
						StringUtil.parseObjectToString(payeeAmount));
				this.insertPreprocessfundtrans(resultMap, ProcessState.PREPROC);
			}
		} else {
			for (Map<String, Object> map : rtxnMaps) {
				if (fileName.endsWith(ConstCups.ICOM)) {
					String procCode = StringUtil.parseObjectToString(map
							.get("rtxntypecode"));
					String rtxnTypCd = StringUtil.buildString(
							StringUtil.parseObjectToString(map.get("msgtype")),
							procCode.subSequence(0, 2));
					//账户关系验证与查询
					if(ConstCups.VDAT.equals(rtxnTypCd)){
						continue;
					}
					// 银联对账文件流水的交易状态都是成功
					map.put(Dict.TRANSSTATUS, TransStatus.SUCCESS);
					String innerRtxnNbr = StringUtil
							.buildString(StringUtil.parseObjectToString(map
									.get("msgtype")), StringUtil
									.parseObjectToString(map.get("traceNo")),
									StringUtil.parseObjectToString(map
											.get("transDate")), StringUtil
											.parseObjectToString(map
													.get("acqInsCode")),
									StringUtil.parseObjectToString(map
											.get("sndInsCode")));

					map.put(Dict.INNERFUNDTRANSNBR, innerRtxnNbr);
					map.put(Dict.DOWN_TRANS_NBR, innerRtxnNbr);
					map.put(Dict.FUND_CHANNEL_CD, FundChannelCode.CUPS);
					map.put(Dict.TRANSSTATUS,
							StringUtil.parseObjectToString(TransStatus.SUCCESS));
					String payeracctnbr = StringUtil.parseObjectToString(map
							.get(Dict.PAYERACCTNBR));
					if (ConstCups.CONC.equals(rtxnTypCd)
							|| ConstCups.CONSREVK.equals(rtxnTypCd)
							|| ConstCups.CONR.equals(rtxnTypCd)) {
						map.put(Dict.PAYEEACCTNBR, payeracctnbr);
						map.put(Dict.PAYERACCTNBR, null);
					}
					//组装原始交易流水号
					String origTraceNo=StringUtil.parseObjectToString(map.get("origTraceNo"));
					if (!StringUtil.isStringEmpty(origTraceNo)&&(!"000000".equals(origTraceNo))) {
						String origInnerRtxnNbr = StringUtil.buildString(
								"0200", StringUtil.parseObjectToString(map
										.get("origTraceNo")), StringUtil
										.parseObjectToString(map
												.get("origTxnTime")),
								StringUtil.parseObjectToString(map
										.get("acqInsCode")), StringUtil
										.parseObjectToString(map
												.get("sndInsCode")));
						map.put("originnerrtxnnbr", origInnerRtxnNbr);
					}
					map.put(Dict.TRANSCODE, rtxnTypCd);
					this.insertDownsysfundtrans(map);

					payerAmount = payerAmount.add(StringUtil
							.parseBigDecimal(map.get("payerfeeamt")));
					payerAmount = payerAmount.add(StringUtil
							.parseBigDecimal(map.get("throConnFeeAmt")));
					payeeAmount = payeeAmount.add(StringUtil
							.parseBigDecimal(map.get("payeefeeamt")));
				} else {
					if (fileName.endsWith(ConstCups.IERR)) {
						// 差错文件要显示到界面，由柜员检查后再处理，这样就改为已处理
						map.put(Dict.PROCESSTYPCD, ConstCups.IERR); // 处理类型
						map.put("fundchanelcode", FundChannelCode.CUPS);
						String downRtxnNbr = StringUtil.buildString(StringUtil
								.parseObjectToString(map.get("msgtype")), "|",
								StringUtil.parseObjectToString(map
										.get("traceNo")), "|", StringUtil
										.parseObjectToString(map
												.get("transDate")), "|",
								StringUtil.parseObjectToString(map
										.get("acqInsCode")), "|", StringUtil
										.parseObjectToString(map
												.get("sndInsCode")));
						map.put(Dict.DOWNTRANSNBR, downRtxnNbr);
						map.put(Dict.TRANSDATE,
								DateUtil.Date_To_DateFormat(DateUtil.getDate()));
						this.insertPreprocessfundtrans(map, ProcessState.PREPROC);
					}
				}
			}

			// ICOM手续费
			if (fileName.endsWith(ConstCups.ICOM)) {
				resultMap.put("processtypcd", ConstCups.ICOM);
				if (payerAmount.compareTo(BigDecimal.ZERO) != 0
						|| payeeAmount.compareTo(BigDecimal.ZERO) != 0) {
					resultMap.put("payerfeeamt",
							StringUtil.parseObjectToString(payerAmount));
					resultMap.put("payeefeeamt",
							StringUtil.parseObjectToString(payeeAmount));
					this.insertPreprocessfundtrans(resultMap,
							ProcessState.PREPROC);
				}
			}
		}

	}

	@Override
	protected String getFileType() {
		// TODO Auto-generated method stub
		return ConstCups.FILE_TYPE;
	}

	@Override
	protected boolean isDownloadFile() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected List<String> getCheckFileName(Map<String, Object> argMaps) {
		// TODO Auto-generated method stub
		return null;
	}

}
