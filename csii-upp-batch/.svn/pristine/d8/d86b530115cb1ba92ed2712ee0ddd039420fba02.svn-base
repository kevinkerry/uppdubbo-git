/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-batch
 * create: 2016年2月17日 上午11:00:33
 * vc: $Id: $
 */
package com.csii.upp.batch.appl.paym;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.UploadFlag;
import com.csii.upp.dao.extend.BatchFeeProfitTransExtendDAO;
import com.csii.upp.dao.extend.BatchMerTransExtendDAO;
import com.csii.upp.dao.extend.OnlinesubtranshistExtendDAO;
import com.csii.upp.dao.extend.OnlinetranshistExtendDAO;
import com.csii.upp.dao.generate.BatchclearsubtransDAO;
import com.csii.upp.dao.generate.BatchcleartransDAO;
import com.csii.upp.dao.generate.BatchconfirmsubcleartransDAO;
import com.csii.upp.dao.generate.BatchfeeprofittransDAO;
import com.csii.upp.dao.generate.BatchfeeprofittranshistDAO;
import com.csii.upp.dao.generate.BatchmertransDAO;
import com.csii.upp.dao.generate.BatchmertranshistDAO;
import com.csii.upp.dao.generate.MernotifiyregDAO;
import com.csii.upp.dao.generate.OnlinefileinfoDAO;
import com.csii.upp.dao.generate.OnlinejnlDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dto.generate.BatchclearsubtransExample;
import com.csii.upp.dto.generate.BatchcleartransExample;
import com.csii.upp.dto.generate.BatchconfirmsubcleartransExample;
import com.csii.upp.dto.generate.BatchfeeprofittransExample;
import com.csii.upp.dto.generate.BatchfeeprofittranshistExample;
import com.csii.upp.dto.generate.BatchmertransExample;
import com.csii.upp.dto.generate.BatchmertranshistExample;
import com.csii.upp.dto.generate.MernotifiyregExample;
import com.csii.upp.dto.generate.OnlinefileinfoExample;
import com.csii.upp.dto.generate.OnlinejnlExample;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.generate.OnlinetranshistExample;
import com.csii.upp.util.DateUtil;

/**
 * paym数据清除.
 * 
 * @author chen chao
 * @version $Revision:$
 */
public class PaymDataClearAppl extends BaseAppl {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csii.upp.batch.appl.base.BaseAppl#runAppl(java.lang.Object,
	 * java.util.Map)
	 */
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		final Date date = this.getCheckDate(argMaps);
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				Date clearDate = DateUtil.rolMonth(date, 13);
				// 删除当前结算日期BatchMerTransHist(商户台帐表历史)数据
				BatchmertranshistExample bmthExample = new BatchmertranshistExample();
				bmthExample.createCriteria().andSettledateEqualTo(clearDate);
				try {
					BatchmertranshistDAO.deleteByExample(bmthExample);
				} catch (SQLException e) {
					log.error(e);
				}
				// 当前结算日期BatchMerTrans(商户台帐表) 数据移植到BatchMerTransHist(商户台帐表历史)
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("date", date);
				BatchMerTransExtendDAO.insertBatchMerTransToHist(map);
				// 删除当前结算日期BatchMerTrans(商户台帐表) 数据
				BatchmertransExample bmtExample = new BatchmertransExample();
				bmtExample.createCriteria().andSettledateEqualTo(date);
				try {
					BatchmertransDAO.deleteByExample(bmtExample);
				} catch (SQLException e) {
					log.error(e);
				}
				
				// 删除当前结算日期BatchFeeProfitTransHist(商户台帐表历史)数据
				BatchfeeprofittranshistExample bmth1Example = new BatchfeeprofittranshistExample();
				bmth1Example.createCriteria().andSettledateEqualTo(clearDate);
				try {
					BatchfeeprofittranshistDAO.deleteByExample(bmth1Example);
				} catch (SQLException e) {
					log.error(e);
				}
				// 当前结算日期Batchfeeprofittrans(商户台帐表) 数据移植到BatchfeeprofittransHist(商户台帐表历史)
				Map<String, Object> map1 = new HashMap<String, Object>();
				map.put("date", date);
				BatchFeeProfitTransExtendDAO.insertBatchFeeProfitTransToHist(map);
				// 删除当前结算日期Batchfeeprofittrans(商户台帐表) 数据
				BatchfeeprofittransExample bmtExample1 = new BatchfeeprofittransExample();
				bmtExample1.createCriteria().andSettledateEqualTo(date);
				try {
					BatchfeeprofittransDAO.deleteByExample(bmtExample1);
				} catch (SQLException e) {
					log.error(e);
				}				
			
				// 删除当前清算日期BatchClearTrans（清算交易明细表）数据
				BatchcleartransExample bctExample = new BatchcleartransExample();
				bctExample.createCriteria().andCheckstatusNotEqualTo(CheckStatus.PRECHECK);
				try {
					BatchcleartransDAO.deleteByExample(bctExample);
				} catch (SQLException e) {
					log.error(e);
				}
				// 删除当前清算日期BatchClearSubTrans（子订单清算）数据
				BatchclearsubtransExample bcstExample = new BatchclearsubtransExample();
				bcstExample.createCriteria().andProcstepNotEqualTo(ProcStep.PRECHECK);
				try {
					BatchclearsubtransDAO.deleteByExample(bcstExample);
				} catch (SQLException e) {
					log.error(e);
				}

				// 删除当前清算日期BatchConfirmSubClearTrans（确认支付子交易清算表）数据
				BatchconfirmsubcleartransExample bcsctExample = new BatchconfirmsubcleartransExample();
				bcsctExample.createCriteria().andCleardateEqualTo(date);
				try {
					BatchconfirmsubcleartransDAO.deleteByExample(bcsctExample);
				} catch (SQLException e) {
					log.error(e);
				}
				
				// 将13个月以前清算日期的OnlineTransHist(总交易明细历史表)数据移植到
				// OnlineTransHistAll(总交易明细历史表All) 中
				Map<String, Object> clearMap = new HashMap<String, Object>();
				clearMap.put("clearDate", clearDate);
				OnlinetranshistExtendDAO.insertOnlinetranshistToAll(clearMap);
				// 删除13个月以前清算日期OnlineTransHist(总交易明细历史表) 数据
				OnlinetranshistExample othExample = new OnlinetranshistExample();
				othExample.createCriteria().andCleardateLessThan(clearDate);
				try {
					OnlinetranshistDAO.deleteByExample(othExample);
				} catch (SQLException e) {
					log.error(e);
				}
				// 将13个月以前清算日期的OnlineSubTransHist(子交易明细历史表)数据移植到
				// OnlineSubTransHistAll(子交易明细历史表All) 中
				OnlinesubtranshistExtendDAO.insertOnlinesubtranshistToAll(clearMap);
				// 删除13个月以前清算日期OnlineSubTransHist(子交易明细历史表) 数据
				OnlinesubtranshistExample osthExample = new OnlinesubtranshistExample();
				osthExample.createCriteria().andCleardateLessThan(clearDate);
				try {
					OnlinesubtranshistDAO.deleteByExample(osthExample);
				} catch (SQLException e) {
					log.error(e);
				}
				
				// 清理交易日志流水表
				OnlinejnlExample onlineJnlExample = new OnlinejnlExample();
				onlineJnlExample.createCriteria().andTransdateLessThan(
						DateUtil.rolMonth(date, 12));
				try {
					OnlinejnlDAO.deleteByExample(onlineJnlExample);
				} catch (SQLException e) {
					log.error(e);
				}
				
				// 清理商户通知登记数据
				MernotifiyregExample merNotifyExample = new MernotifiyregExample();
				merNotifyExample.createCriteria().andTransdateLessThan(
						DateUtil.rolMonth(date, 1));
				try {
					MernotifiyregDAO.deleteByExample(merNotifyExample);
				} catch (SQLException e) {
					log.error(e);
				}
				
				//删除一个月前的文件上传记录
				OnlinefileinfoExample ofExample = new OnlinefileinfoExample();
				ofExample.createCriteria().andUploadflagEqualTo(UploadFlag.Success).
					andCleardateLessThanOrEqualTo(DateUtil.rolMonth(date, 1));
				try {
					OnlinefileinfoDAO.deleteByExample(ofExample);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

	}

}
