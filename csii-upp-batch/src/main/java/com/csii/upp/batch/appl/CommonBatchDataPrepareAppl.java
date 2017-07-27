package com.csii.upp.batch.appl;

import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.InnerfundtransExtendDAO;
import com.csii.upp.dao.extend.OveralltransExtendDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.util.StringUtil;

/**
 * 对账数据准备
 * 
 * @author chen chao
 *
 */
public class CommonBatchDataPrepareAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		// String fundChannelCd =
		// StringUtil.parseObjectToString(argMaps.get(Dict.FCNM));
		final String checkdataFlag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
		final String transStatus = TransStatus.INIT;// 初始化
		final String checkStatus = CheckStatus.PRECHECK;// 待对账
		getTransactionTemplate().execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// step1:将总交易资金流水表Overalltrans的数据更新为待对账
					//为什么 银联对账取5个小时前的数据：异步通知会查询当前表不能把对账时间点的当期表记录移动历史表和待清算流水表，银联T日24点前生成文件，T+1日10点对3小时前的数据是没问题的 
					OveralltransExtendDAO.updateOverrallTransForCheck(checkdataFlag, transStatus, checkStatus);					
					// 子流水资金对账准备
					innerfundtransDataPrepare(transStatus, checkdataFlag, checkStatus);
					//积分网关数据准备
					innerfundtransPointDataPrepare(transStatus, FundChannelCode.JFWG, checkStatus);
					// 总流水资金对账准备
					overallDatePrepare(transStatus, checkdataFlag, checkStatus);
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	private void overallDatePrepare(String transStatus, String checkdataFlag, String checkStatus) throws Exception {
		// step2 : 将待对账的总交易流水表OverallTrans移植到历史表OverallTransHist
		OveralltransExtendDAO.insertOverralltransToHist(checkStatus, checkdataFlag);
		// step3 : 删除待对账的总交易流水表OverallTrans
		OveralltransExample example = new OveralltransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus).andCheckdataflagEqualTo(checkdataFlag);
		OveralltransDAO.deleteByExample(example);
	}

	private void innerfundtransDataPrepare(String transStatus, String checkdataFlag, String checkStatus)
			throws Exception {
		// step1:将子交易资金流水表InnerFundTrans的数据更新为待对账
		//为什么 银联对账取5个小时前的数据：异步通知会查询当前表不能把对账时间点的当期表记录移动历史表和待清算流水表，银联T日24点前生成文件，T+1日10点对3小时前的数据是没问题的 
		InnerfundtransExtendDAO.updateInnerFundTransForCheck(checkdataFlag, transStatus, checkStatus);
		// step2：将待对账的子交易资金流水表InnerFundTrans移植到平台资金历史表InnerFundTransHist
		InnerfundtransExtendDAO.InsertInnerfundtransToHist(checkdataFlag, checkStatus);
		// step3：将待对账的子交易资金流水表InnerFundTrans移植到内部待清算资金流水表InnerPreClearFundTrans
		InnerfundtransExtendDAO.InsertInnerfundtransToPreClear(checkdataFlag, checkStatus);
		// step4：删除子交易资金流水表InnerFundTrans
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus).andCheckdataflagEqualTo(checkdataFlag);
		InnerfundtransDAO.deleteByExample(example);
	}
	
	/**
	 * 将积分网关交易转移
	 * @param transStatus
	 * @param checkdataFlag
	 * @param checkStatus
	 * @throws Exception
	 * @author wt
	 */
	private void innerfundtransPointDataPrepare(String transStatus, String checkdataFlag, String checkStatus)
			throws Exception {
		// step1:将子交易资金流水表InnerFundTrans的数据更新为待对账
		//为什么 银联对账取5个小时前的数据：异步通知会查询当前表不能把对账时间点的当期表记录移动历史表和待清算流水表，银联T日24点前生成文件，T+1日10点对3小时前的数据是没问题的 
		InnerfundtransExtendDAO.updateInnerFundTransForCheck(checkdataFlag, transStatus, checkStatus);
		// step2：将待对账的子交易资金流水表InnerFundTrans移植到平台资金历史表InnerFundTransHist
		InnerfundtransExtendDAO.InsertInnerfundtransToHist(checkdataFlag, checkStatus);
		// step4：删除子交易资金流水表InnerFundTrans
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andCheckstatusEqualTo(checkStatus).andCheckdataflagEqualTo(checkdataFlag);
		InnerfundtransDAO.deleteByExample(example);
	}	
}
