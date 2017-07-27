package com.csii.upp.batch.appl.dpc;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckFlag;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.CheckapplyDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Checkapply;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.dto.router.dpc.RespDpcCheckFileApply;
import com.csii.upp.service.fundprocess.DpcService;



public class CheckFileApplyDpcAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String fundChannelCd = FundChannelCode.DPC;
		//对账日期
		Sysinfo dpcSysInfo = SysinfoExtendDAO.getSysInfo(fundChannelCd);
		Date checkDate =  this.getCheckDate(argMaps);
		long checkWorkRound = dpcSysInfo.getPreworkround();
		
		for(long i=1; i<=checkWorkRound; i++)
		{
			Checkapply checkapply = new Checkapply();
			checkapply.setFundchannelcode(fundChannelCd);
			checkapply.setCheckdate(checkDate);
			checkapply.setCheckflag(CheckFlag.CHECKFLAG_WORK);
			checkapply.setDealcode(CheckFileDealFlag.UnDeal);
			checkapply.setDealmsg("申请已提交");
			checkapply.setWorkround(i);
			try {
				CheckapplyDAO.insertSelective(checkapply);
			} catch (SQLException e) {
				throw new PeRuntimeException("Get Checkapply Table Failed.", e);
			}
			
			DpcService dpcService = (DpcService) this
					.getRouterService(fundChannelCd.toLowerCase());
			InputFundData data = new InputFundData();
			data.setTransdate(checkDate);
			data.setInnerfundtransnbr(checkapply.getCheckapplynbr());
			RespDpcCheckFileApply output = null;
			try {
				output = dpcService.DocheckFileApply(data);
			} catch (PeException e1) {
				log.error(e1.getMessage());
			}
			
			if (!output.isSuccess()) {
				throw new PeRuntimeException(output.getReturncode() + output.getReturnmsg());
			}
			
			Checkapply updcheckapply = new Checkapply();
			updcheckapply.setCheckapplynbr(checkapply.getCheckapplynbr());
			updcheckapply.setFilename(output.getFilepath().substring(
					output.getFilepath().lastIndexOf("/") + 1));
			updcheckapply.setReceivenum(output.getSxtcrcvtotale());
			updcheckapply.setSendnum(output.getSxtcsendtotal());
			updcheckapply.setTotalnum(output.getSxtctotalnum());
			updcheckapply.setWorkround(i);
			try {
				CheckapplyDAO.updateByPrimaryKeySelective(updcheckapply);
			} catch (SQLException e) {
				throw new PeRuntimeException("Get Checkapply Table Failed.",
						e);
			}

		}
		
	}
}
