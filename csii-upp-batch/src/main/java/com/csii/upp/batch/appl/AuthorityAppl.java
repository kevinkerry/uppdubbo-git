package com.csii.upp.batch.appl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.generate.AuthorityDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Authority;
import com.csii.upp.dto.generate.AuthorityExample;
import com.csii.upp.dto.generate.Cardbininfo;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.marshaller.ObjectMapMarshaller;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.service.fundprocess.EAccountService;
import com.csii.upp.service.fundprocess.UnionPayService;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * @author fgw
 * 
 */
public class AuthorityAppl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps)
			throws Exception {

		CoreService coreService = (CoreService) this
				.getRouterService(FundChannelCode.CORE.toLowerCase());
		EAccountService eaccountService = (EAccountService) this
				.getRouterService(FundChannelCode.EACCOUNT.toLowerCase());
		UnionPayService unionpayService = (UnionPayService) this
				.getRouterService(FundChannelCode.UNIONPAY.toLowerCase());
		
		Date dealdate = new java.sql.Date(new Date().getTime());

		// 获得资金归集数据
		AuthorityExample cashsweepexample = new AuthorityExample();
		cashsweepexample.createCriteria().andTranstatusEqualTo(TransStatus.INIT)
				.andTransdateEqualTo(this.getPostDate());
		List<Authority>authorityList = null;
		Cardbininfo cardBinInfo = null;
		try {
			authorityList = AuthorityDAO.selectByExample(cashsweepexample);
		} catch (SQLException e) {
               throw new PeRuntimeException("get Cashsweep Table Failed.",e);
		}
		int i=0;
		for (Authority authority : authorityList) {
			i++;
			Date checkDate = this.getCheckDate(argMaps);
			ObjectMapMarshaller objecttomap = DefaultSupportor.getObjectMapMarshaller();
			Map<String, Object> map = objecttomap.marshall(authority);
			InputFundData input = new InputFundData(map);
			input.setCerttyp((String)map.get("certtyp"));
			input.setCustomname((String)map.get("customname"));
			input.setCertno((String)map.get("certno"));
			input.setCardtyp((String)map.get("cardtyp"));
			input.setCardno((String)map.get("cardno"));
			input.setCustomtyp((String)map.get("customtyp"));
			input.setRtxnseq((String)map.get("rtxnseq"));
			Overalltrans record = new Overalltrans();
			record.setTransdate(this.getPostDate());
			record.setPayeeacctnbr(input.getCardno());
			record.setPayeracctnbr(input.getCardno());
			record.setPayeeacctname(input.getCustomname());
			record.setCheckstatus("0");
			record.setTranstime(new Date());
			record.setTransamt(new BigDecimal(input.getCardtyp()));
			record.setCurrencycd("CNY");
			record.setReturncode("000000");
			record.setReturnmsg("交易成功");
			record.setOveralltransstatus("0");
			record.setCheckdataflag("CORE");
			
			
			if(i/2==0){
				record.setOveralltranstypcd("BACTHDS");
			}else{
				record.setOveralltranstypcd("BACTHDF");
			}
	    	record.setOveralltransnbr(IDGenerateFactory.generateRtxnNbr());
			OveralltransDAO.insertSelective(record);
			
			unionpayService.customAuthen(input);
			
			authority.setRtxnseq(input.getRtxnseq());
			authority.setTranstatus(TransStatus.SUCCESS);
			
			AuthorityDAO.updateByPrimaryKey(authority);
			
	}


	}
}