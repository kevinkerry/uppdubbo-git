package com.csii.upp.batch.appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.event.handler.RunQueEvent;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ExpHandleState;
import com.csii.upp.constant.SendStatus;
import com.csii.upp.dao.extend.FundchannelcleartranshistExtendDAO;
import com.csii.upp.dao.extend.InnerfundtransHistExtendDAO;
import com.csii.upp.dao.extend.OveralltranshistExtendDAO;
import com.csii.upp.dao.generate.BatchcheckerrorDAO;
import com.csii.upp.dao.generate.FundchannelcleartranshistDAO;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.InnerpreclearfundtransDAO;
import com.csii.upp.dao.generate.OverallrequestregDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dao.generate.QueapplhistDAO;
import com.csii.upp.dao.generate.TransexceptionregDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.BatchcheckerrorExample;
import com.csii.upp.dto.generate.FundchannelcleartranshistExample;
import com.csii.upp.dto.generate.FundchannelsettleExample;
import com.csii.upp.dto.generate.InnerfundtranshistExample;
import com.csii.upp.dto.generate.InnerpreclearfundtransExample;
import com.csii.upp.dto.generate.OverallrequestregExample;
import com.csii.upp.dto.generate.OveralltranshistExample;
import com.csii.upp.dto.generate.QueapplhistExample;
import com.csii.upp.dto.generate.TransexceptionregExample;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 清除流水数据
 * 
 * @author 姜星
 * 
 */
public class CheckDataClearAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps)
			throws Exception {
		final Date checkDate = this.getCheckDate(argMaps);
		final String xbflag = StringUtil.parseObjectToString(argMaps.get(Dict.XBBZ));
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					Date periodDate = DateUtil.rolMonth(checkDate, 12);
					// 清理交易差错数据
					List<String> list = new ArrayList<String>(Arrays.asList(
							ExpHandleState.PREHANDLE, ExpHandleState.HANDLING));
					TransexceptionregExample excepExample = new TransexceptionregExample();
					excepExample.createCriteria().andTransdateLessThan(DateUtil.rolMonth(checkDate, 6))
							.andExphandlestatusNotIn(list);
					TransexceptionregDAO.deleteByExample(excepExample);

					// 清理交易登记数据
					OverallrequestregExample regExample = new OverallrequestregExample();
					regExample.createCriteria().andTransdateLessThan(
							periodDate);
					OverallrequestregDAO.deleteByExample(regExample);
					
					// 回调交易登记簿
//					CallbacktransregExample callBackExample = new CallbacktransregExample();
//					callBackExample.createCriteria().and(
//							periodDate);
//					CallbacktransregDAO.deleteByExample(example)
					
					//队列应用历史
					QueapplhistExample queExample = new QueapplhistExample();
					queExample.createCriteria().andRundateLessThan(periodDate);
					QueapplhistDAO.deleteByExample(queExample);
					
					//将InnerFundTransHist （平台资金流水历史表）的360天之前的非待对账的数据移植到平台资金流水历史All表InnerFundTransHistAll
					InnerfundtransHistExtendDAO.insertInnerfundtranshistToAll(periodDate);
					//删除
					InnerfundtranshistExample iftExample = new InnerfundtranshistExample();
					iftExample.createCriteria().andTransdateLessThan(periodDate);
					InnerfundtranshistDAO.deleteByExample(iftExample);
					
					//将OverallTransHist (总交易流水历史表)360天之前的非待对账的数据移植到总交易流水历史All表OverallTransHistAll表里
					OveralltranshistExtendDAO.insertOveralltranshistToAll(periodDate);
					//删除
					OveralltranshistExample othExample = new OveralltranshistExample();
					othExample.createCriteria().andTransdateLessThan(periodDate);
					OveralltranshistDAO.deleteByExample(othExample);
					
					// 日终差错
					BatchcheckerrorExample bceExample = new BatchcheckerrorExample();
					bceExample.createCriteria().andTransdateLessThan(periodDate).andDealstatusNotEqualTo(ExpHandleState.PREHANDLE).
					andDealstatusNotEqualTo(ExpHandleState.HANDLING);
					BatchcheckerrorDAO.deleteByExample(bceExample);
					
					//删除FundChannelSettle资金通道结算表
					FundchannelsettleExample fcsExample = new FundchannelsettleExample();
					fcsExample.createCriteria().andTransdateLessThan(periodDate).andSendstatusEqualTo(SendStatus.SUCCESS);
					FundchannelsettleDAO.deleteByExample(fcsExample);
					
					// 渠道台账历史表数据移植到All表并删除历史表数据
					FundchannelcleartranshistExtendDAO.insertFundchannelcleartranshistToAll(periodDate);
					FundchannelcleartranshistExample fcctExample = new FundchannelcleartranshistExample();
					fcctExample.createCriteria().andTransdateLessThan(periodDate);
					FundchannelcleartranshistDAO.deleteByExample(fcctExample);
					
					// 清算表
					InnerpreclearfundtransExample ipcftExample = new InnerpreclearfundtransExample(); 
					ipcftExample.createCriteria().andCheckstatusNotEqualTo(CheckStatus.PRECHECK).andTransdateLessThan(DateUtil.rolMonth(checkDate, 6));
					InnerpreclearfundtransDAO.deleteByExample(ipcftExample);
					
					
					RunQueEvent event=new RunQueEvent();
					if(xbflag.equals("0")){
						//本行系调用本行PAYM队列
						event.setQueNbr(13L);
					}else if(xbflag.equals("1")){
						//第三方调用第三方PAYM队列
						event.setQueNbr(18L);
					}
					// 异步线程处理
					DefaultSupportor.getEventManager().doService(event);
				} catch (Exception ex) {
					log.error(ex);
				}
				return null;
			}
		});
	}
}
