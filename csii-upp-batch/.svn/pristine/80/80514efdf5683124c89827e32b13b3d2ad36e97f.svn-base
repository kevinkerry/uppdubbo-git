package com.csii.upp.batch.appl.bill99;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.FundchannelcleartransExtendDAO;
import com.csii.upp.dao.generate.FundchannelsettleDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Fundchannel;
import com.csii.upp.dto.generate.Fundchannelsettle;
import com.csii.upp.supportor.IDGenerateFactory;

public class SettleBill99Appl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws PeException {
		try {
			String channel = FundChannelCode.BILL99;
			Fundchannel thirdfundchannel = getFundchannel(channel);
			Fundchannel ownfundchannel = getFundchannel(FundChannelCode.FDPS);
			List<Map> results = FundchannelcleartransExtendDAO.calculateTotalByMerAcctType(channel,
					getCheckDate(argMaps));
			for (Map result : results) {

				Fundchannelsettle record = new Fundchannelsettle();
				// record.setSettlertxnnbr(settlertxnnbr); //not null
				record.setCleardate(getCheckDate(argMaps));
				record.setPayeeacctdeptnbr(ownfundchannel.getFundchannelcode());
				record.setPayerdeptnbr(thirdfundchannel.getFundchannelcode());
				record.setFundchannelcode(ownfundchannel.getFundchannelcode());
				record.setTransdate(this.getPostDate());
				// record.setClearrtxnnbr(clearrtxnnbr);

				// record.setClearrtxnnbr(clearrtxnnbr); //清算流水号
				// record.setDownrtxnnbr(downrtxnnbr); // 下游交易流水
				// record.setNote(note); // 备注
				record.setPayamount(new BigDecimal(result.get("totalamount").toString())); // 应付总金额
				record.setPaycount(Long.parseLong(result.get("totalnum").toString())); // 应付总笔数
				record.setPayeesettleacctnbr(ownfundchannel.getSettleacctnbr()); // 收款结算账户
				record.setPayeesettleacctnbr(thirdfundchannel.getSettleacctnbr()); // 付款结算机构
				// record.setReceivableamount(receivableamount); //应收总金额 商户专用
				// record.setReceviablecount(receviablecount); // 应收总笔数 //商户专用
				// record.setReturncode(returncode); // 自动结算专用
				// record.setSendstate(sendstate); // 自动结算专用
				record.setSettletyp(result.get("meracctyp").toString()); // 结算=台账类型
				// record.setTransamt(transamt); // 交易金额

				record.setSettleseqnbr(IDGenerateFactory.generateSeqId());

				// TODO 根据清算表具体数据内容决定插入信息格式
				FundchannelsettleDAO.insert(record);
			}
		} catch (Exception e) {
			if (e instanceof PeException) {
				throw (PeException) e;
			}
			throw new PeException(DictErrors.DATABASE_EXCEPTION);
		}
	}
}
