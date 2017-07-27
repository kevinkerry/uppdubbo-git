package com.csii.upp.batch.appl.pbc;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckFlag;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.CheckapplyDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Checkapply;
import com.csii.upp.dto.router.pbc.RespPbcCheckFile;
import com.csii.upp.service.fundprocess.PbcService;

public class CheckFileApplyPbcAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) {
		String fundChannelCd = FundChannelCode.PBC;
		//对账日期
		Date checkDate =  this.getCheckDate(argMaps);
		//对账标识
		String checkFlag =  CheckFlag.CHECKFLAG_WORK;
		PbcService pbcService = (PbcService) this.getRouterService(FundChannelCode.PBC.toLowerCase());
		String dealcode = CheckFileDealFlag.UnDeal;
		String dealmsg = "申请已提交";
		InputFundData input = new InputFundData();
		input.setFundchannelcode(fundChannelCd);
		input.setTransdate(checkDate);
		RespPbcCheckFile checkfileoutput;
		try {
			checkfileoutput = pbcService.pbcCheckFile(input);
			String filename =checkfileoutput.getFilepath();
			Checkapply checkapply = new Checkapply();
			checkapply.setFundchannelcode(fundChannelCd);
			checkapply.setCheckdate(checkDate);
			checkapply.setCheckflag(checkFlag);
			
			checkapply.setDealcode(dealcode);
			checkapply.setDealmsg(dealmsg);
			checkapply.setFilename(filename);
			CheckapplyDAO.insertSelective(checkapply);
		} catch (PeException e1) {
			// TODO Auto-generated catch block
						log.error(e1.getMessage());
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Checkapply Table Failed.",
					e);
		}

	}

}
