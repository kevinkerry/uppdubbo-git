/*
 * Copyright 2005-2016 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * project: csii-upp-batch
 * create: 2016年1月20日 下午5:50:00
 * vc: $Id: $
 */
package com.csii.upp.batch.appl.core;

import java.sql.SQLException;
import java.util.Date;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseCutOffAppl;
import com.csii.upp.constant.CheckResult;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.SysinfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.service.fundprocess.CoreService;

/**
 * 日切处理
 * 
 * @author chen chao
 * @version $Revision:$
 */
public class CheckOffDateCoreAppl extends BaseCutOffAppl {

	@Override
	protected String getFundchannelCode() {
		return FundChannelCode.CORE;
	}

	@Override
	protected RespSysHead queryDownPostDate() throws Exception{
		InputFundData data = new InputFundData();
		CoreService coreService = (CoreService) this.getRouterService(FundChannelCode.CORE.toLowerCase());
		RespSysHead output = coreService.queryDownPostDate(data);
		return output;
	}

	@Override
	protected void stepAfterQueryDownSysDate() throws Exception {

		// step1:查询下游工作日期
		RespSysHead output = queryDownPostDate();
		if (output != null && output.isSuccess()) {
			// step2:判断是否查询成功且切日
			Date postDate = output.getDownrtxndate();
			if (SysinfoExtendDAO.getSysInfo(getFundchannelCode()).getPostdate().compareTo(postDate) < 0) {
				// step3:更新通道系统维护表sysinfo
				Date prevpostdate = getPostDate();
				// 更新核心通道
				Sysinfo record1 = new Sysinfo();
				record1.setFundchannelcode(FundChannelCode.CORE);
				record1.setPostdate(postDate);
				record1.setNextpostdate(prevpostdate);
				record1.setPrevpostdate(prevpostdate);
				record1.setCheckresult(CheckResult.PRECHECKED);
				try {
					SysinfoDAO.updateByPrimaryKeySelective(record1);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
				// 更新FDPS通道
				Sysinfo record2 = new Sysinfo();
				record2.setFundchannelcode(FundChannelCode.FDPS);
				record2.setPostdate(postDate);
				record2.setNextpostdate(prevpostdate);
				record2.setPrevpostdate(prevpostdate);
				SysinfoDAO.updateByPrimaryKeySelective(record2);
				// 更新PAYM通道
				Sysinfo record3 = new Sysinfo();
				record3.setFundchannelcode(FundChannelCode.PAYM);
				record3.setPostdate(postDate);
				record3.setNextpostdate(prevpostdate);
				record3.setPrevpostdate(prevpostdate);
				SysinfoDAO.updateByPrimaryKeySelective(record3);
				// 更新银联通道
				Sysinfo record4 = new Sysinfo();
				record4.setFundchannelcode(FundChannelCode.UNIONPAY);
				record4.setPostdate(postDate);
				record4.setNextpostdate(prevpostdate);
				record4.setPrevpostdate(prevpostdate);
				SysinfoDAO.updateByPrimaryKeySelective(record4);
			}
		} else {
			throw new PeException(DictErrors.CHECK_OFF_DATE_ERROR);
		}
	}

}
