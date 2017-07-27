/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-batch
 * create: 2016年1月25日 下午2:49:18
 * vc: $Id: $
 */
package com.csii.upp.batch.appl.paym;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckStatus;
import com.csii.upp.constant.DealResult;
import com.csii.upp.constant.ProcStep;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.extend.OnlineTransExtendDAO;
import com.csii.upp.dao.extend.OnlinesubtransExtendDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.UpersyschecknoticeDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.OnlinetransExample;
import com.csii.upp.dto.generate.Upersyschecknotice;
import com.csii.upp.dto.generate.UpersyschecknoticeExample;
import com.csii.upp.util.StringUtil;

/**
 * paym数据准备.
 * 
 * @author chen chao
 * @version $Revision:$
 */
public class PaymDataPrepareAppl extends BaseAppl {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csii.upp.batch.appl.base.BaseAppl#runAppl(java.lang.Object,
	 * java.util.Map)
	 */
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		final String checkStatus = CheckStatus.PRECHECK;
		final String procStep = ProcStep.PRECHECK;
		final String transStatus = TransStatus.INIT;
		final Date checkDate = this.getCheckDate(argMaps);
		// STEP1 获取渠道系别
		final String fundchanneltypcd = StringUtil.parseObjectToString(argMaps.get(Dict.XBBZ));
		UpersyschecknoticeExample uperExample = new UpersyschecknoticeExample();
		uperExample.createCriteria().andDealresultEqualTo(DealResult.UnDeal).andCheckstartdateEqualTo(checkDate)
		            .andBatchtypcdEqualTo(fundchanneltypcd);
		List<Upersyschecknotice> UpersyschecknoticeList=UpersyschecknoticeDAO.selectByExample(uperExample);
		/*if(UpersyschecknoticeList.isEmpty()){
			throw new PeRuntimeException("未存在文件通知信息");
		}*/
		//final Upersyschecknotice uperSysCheckNotice=UpersyschecknoticeList.get(0);
		
		getTransactionTemplate().execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					// 总流水
					OnlineTransDataPrepare(checkStatus, transStatus, checkDate, fundchanneltypcd);					
					// STEP2 流水数据移植
					// 子流水
					OnlineSubTransDataPrepare(procStep, transStatus, checkDate, fundchanneltypcd);
					
					// step4:删除OnlineTrans（总交易明细表）中本系别待对账的数据
					OnlinetransExample onlinetransExample = new OnlinetransExample();
					onlinetransExample.createCriteria().andCheckstatusEqualTo(checkStatus);
					OnlinetransDAO.deleteByExample(onlinetransExample);					
					// STEP3将upersyschecknotice表dealresult置为dealed
					//uperSysCheckNotice.setDealresult(DealResult.Dealed);
					//UpersyschecknoticeDAO.updateByPrimaryKey(uperSysCheckNotice);
				} catch (Exception e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}

	private void OnlineTransDataPrepare(String checkStatus, String transStatus, Date checkDate,
			String fundchanneltypcd) throws Exception{
		// step1:更新初始状态的OnlineTrans（总交易明细表）的数据为待对账
		// 首先更新交易表提取的数据，锁定提取范围，保证总交易和子交易的一致性
		Onlinetrans record = new Onlinetrans();
		record.setCheckstatus(checkStatus);
		OnlinetransExample example = new OnlinetransExample();
		example.createCriteria().andTransstatusNotEqualTo(transStatus).andDepartmentnbrEqualTo(fundchanneltypcd);
		OnlinetransDAO.updateByExampleSelective(record, example);
		
		// step2:将本系别待对账的OnlineTrans（总交易明细表）数据插入OnlineTransHist（总交易明细历史表）
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("checkStatus", checkStatus);
		OnlineTransExtendDAO.insertOnlineTransToHist(map);

		// step3:将本系别待状态的OnlineTrans（总交易明细表）数据插入BatchClearTrans（清算交易明细表）
		OnlineTransExtendDAO.insertOnlineTransToBatchClear(map);

	}

	private void OnlineSubTransDataPrepare(String procStep, String transStatus, Date checkDate,String fundchanneltypcd) throws Exception{
		// step1:更新OnlineSubTrans（子交易明细表）的数据为待对账
		//Onlinesubtrans record = new Onlinesubtrans();
		//record.setProcstep(procStep);
		//OnlinesubtransExample example = new OnlinesubtransExample();
		//example.createCriteria().andTransstatusNotEqualTo(transStatus).andDepartmentnbrEqualTo(fundchanneltypcd);
		//OnlinesubtransDAO.updateByExampleSelective(record, example);
		Map<String, Object> mapCheck = new HashMap<String, Object>();
		mapCheck.put("departmentNbr", fundchanneltypcd);
		mapCheck.put("transStatus", transStatus);
		mapCheck.put("checkStatus", CheckStatus.PRECHECK );
		mapCheck.put("procStep", procStep);
		OnlinesubtransExtendDAO.updateOnlinesubtransForCheck(mapCheck);

		// step2:将本系别待对账的OnlineSubTrans（子交易明细表）数据插入OnlineSubTransHist（子交易明细历史表）
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("procStep", procStep);
		OnlinesubtransExtendDAO.insertOnlinesubtransToHist(map);

		// step3:将本系别待对账的OnlineSubTrans（子交易明细表）数据插入BatchClearSubTrans（子订单清算）
		OnlinesubtransExtendDAO.insertOlinesubtransToBatchClearSubTrans(map);

		// step4:删除OnlineSubTrans（子交易明细表）中本系别待对账的数据
		OnlinesubtransExample onlinesubtransExample = new OnlinesubtransExample();
		onlinesubtransExample.createCriteria().andProcstepEqualTo(procStep);
		OnlinesubtransDAO.deleteByExample(onlinesubtransExample);
	}
}
