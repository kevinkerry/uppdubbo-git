package com.csii.upp.service.fundprocess;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.AcctKind;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.LocalBankNbr;
import com.csii.upp.dao.generate.CardbininfoDAO;
import com.csii.upp.dao.generate.ChannellimitroutDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Cardbininfo;
import com.csii.upp.dto.generate.Channellimitrout;
import com.csii.upp.dto.generate.ChannellimitroutExample;
import com.csii.upp.service.BasePayService;

/**
 * 自动计算路由通道服务
 * 
 * @author 徐锦
 * 
 */
public class AutoRouterService extends BasePayService {
	

	/**
	 * 获得满足的资金通道路由
	 * 
	 * @param input
	 * @throws PeException
	 */
	public void autoChannelRouter(InputFundData input, String cardBin,String accountType)
			throws PeException {
             try{

			//如果是本行卡和内部账号都走老核心通道,否则计算路由通道
			if(AcctKind.CORE.equals(accountType)){
				input.setFundchannelcode(FundChannelCode.CORE);
			}else {
				//查询Cardbininfo，判断是否是本行卡,本行卡走老核心通道
				Cardbininfo cardbin = CardbininfoDAO.selectByPrimaryKey(cardBin);
				if(LocalBankNbr.LocalEACCTBankNbr.equals(cardbin.getCardbinnbr())){
					input.setFundchannelcode(FundChannelCode.EACCOUNT);
				}else if( cardbin!=null && LocalBankNbr.LocalBankNbr.equals(cardbin.getBankcode())){
					input.setFundchannelcode(FundChannelCode.CORE);
				}else{
					if("ETWA".equals(input.getTransid())){
						input.setTranstypcd("02");
					}else if("PAYR".equals(input.getTransid())){
						input.setTranstypcd("01");
					}else if("ETRA".equals(input.getTransid())){
						input.setTranstypcd("03");
					}else{
						input.setTranstypcd("00");
					}
					// 他行卡通过银行行号、交易类型、金额和优先级获得满足的资金通道路由
					ChannellimitroutExample example = new ChannellimitroutExample();
					example.createCriteria().andBanknbrEqualTo(cardbin.getBankcode()).andTranstypcdEqualTo(input.getTranstypcd())
					.andLimitamtGreaterThanOrEqualTo(input.getTransamt());
					example.setOrderByClause("priority desc");
					List<Channellimitrout> fundchannellist = ChannellimitroutDAO.selectByExample(example);
					if(fundchannellist != null && fundchannellist.size()>0){
						input.setFundchannelcode(fundchannellist.get(0).getFundchannelcode());
						return;
					}else{
						throw new PeException(DictErrors.SYSTEMABNORMAL);
					}
				}
				
			}
		} catch (SQLException e) {		
			throw new PeRuntimeException(e.getMessage());
			}
		
			// 获得资金通道码
			input.setFundchannelcode(input.getFundchannelcode());
		
	}
}