package com.csii.upp.batch.appl.bill99;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.constant.FeeFlag;
import com.csii.upp.constant.FeeSettlePeriod;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.SettleStatus;
import com.csii.upp.constant.StandBookTypCD;
import com.csii.upp.dao.extend.FundchannelcleartransExtendDAO;
import com.csii.upp.dao.generate.FundchannelDAO;
import com.csii.upp.dao.generate.FundchannelcleartransDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Feeparam;
import com.csii.upp.dto.generate.Fundchannel;
import com.csii.upp.dto.generate.Fundchannelcleartrans;
import com.csii.upp.dto.generate.FundchannelcleartransExample;
import com.csii.upp.service.FeeService;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.StringUtil;


/**
 * 批量手续费计算
 * 
 * 读取通道配置表FUNDCHANNEL中手续费相关配置FEESETTLEPERIOD，FEEFLAG 根据计费周期以及批量规则判断是否需要计费
 * 根据台账表FUNDCHANNELCLEARTRANS的台账类型MERACCTYP进行汇总 得到相应类型交易的总金额和总笔数
 * 根据通道配置表FUNDCHANNEL中配置的计费编号FEENBR获取通道计费规则 根据计费规则，匹配交易类型的总金额、总笔数，计算该交易类型的总手续费
 * 插入台账表FUNDCHANNELCLEARTRANS
 * 更新台账表FUNDCHANNELCLEARTRANS已汇总明细记录的手续费清算流水号FEECLEARRTXNNBR
 */
public class FeeCountingBill99Appl extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws PeException {
		try {
			String channel = FundChannelCode.BILL99;
			FeeService feeService = new FeeService();
			Fundchannel fundChannel = FundchannelDAO
					.selectByPrimaryKey(channel);
			Date checkDate=this.getCheckDate(argMaps);
			if (isBatchRun(StringUtil.parseLong(fundChannel.getFeeflag()).shortValue())
					&& timeToRun(fundChannel.getFeesettleperiod().shortValue(),checkDate)) {
				List<Feeparam> feeparams = feeService.getFeeParamList(fundChannel
						.getFeenbr());
				List<Map> results = FundchannelcleartransExtendDAO
						.calculateTotalByMerAcctType(channel, checkDate);
				for (Map result : results) {
//					List<BigDecimal> fees = feeService.calculateFee(
//							feeparams,
//							new BigDecimal(result.get("totalamount").toString()),
//							new BigDecimal(result.get("totalnum").toString()));
//					Fundchannelcleartrans record = insertFundchannelcleartrans(InitFundChannelClearTrans(channel, fees,checkDate));
//				     	updateFundchannelcleartrans(channel, record,checkDate);
				}
			}
		} catch (Exception e) {
			if (e instanceof PeException) {
				throw (PeException) e;
			}
			throw new PeException(DictErrors.DATABASE_EXCEPTION);
		}
	}

	private Fundchannelcleartrans InitFundChannelClearTrans(String channel ,List<BigDecimal> fees,Date checkDate) throws SQLException {
		Fundchannel thirdfundchannel = getFundchannel(channel);
		Fundchannel ownfundchannel = getFundchannel(FundChannelCode.FDPS);
		Fundchannelcleartrans record = new Fundchannelcleartrans();
		record.setCleardate(checkDate);// '清算日期';
		//record.setClearrtxnnbr(clearrtxnnbr);  //'支付平台清算流水号';
		record.setFeeamt(fees.get(0));//  '手续费金额';
   //	record.setFeeclearrtxnnbr(feeclearrtxnnbr);  不需要//  '手续费清算流水号';
		record.setFundchannelcode(channel); // '清算资金通道编号';
		//record.setInnerrtxnnbr(innerrtxnnbr);//内部交易流水号
		record.setStandbooktypcd(StandBookTypCD.Bill_Fee_Acct.toString());//  '台账类型-  1 快钱 手续费支出   2- 快钱支付款项 3-快钱退款款项';
		//record.setNote(note);// '备注';
		//record.setOveralltransnbr(Overalltransnbr);//总流水号
		record.setPayeeacctdeptnbr(ownfundchannel.getFundchannelcode()); //  '付款方账户机构';
		record.setPayeeacctnbr(ownfundchannel.getSettleacctnbr());//  '付款账户';
		record.setPayerdeptnbr(thirdfundchannel.getFundchannelcode());//  '收款账户机构';
		record.setPayeracctnbr(thirdfundchannel.getSettleacctnbr());//  '收款账户';
		record.setTransdate(this.getPostDate());// '交易日期';
		//record.setSettledate(settledate);// '结算日期';
		record.setTransamt(fees.get(1));// '交易金额';
		//record.setSettlenbr(settlenbr); // '支付平台结算流水号';
		record.setSettlestatus(SettleStatus.UNSETTLE);
		return record;
	}
	/**
	 * 插入台账表
	 * 
	 * @throws SQLException
	 */
	private Fundchannelcleartrans insertFundchannelcleartrans(Fundchannelcleartrans record) throws SQLException {
		FundchannelcleartransDAO.insert(record);
		return record;
	}

	private void updateFundchannelcleartrans(String channel,
			Fundchannelcleartrans record,Date checkDate) throws SQLException {
		FundchannelcleartransExample example = new FundchannelcleartransExample();
		example.createCriteria().andFundchannelcodeEqualTo(channel)
				.andTransdateEqualTo(checkDate);
		Fundchannelcleartrans fundchannelcleartrans = new Fundchannelcleartrans();
		fundchannelcleartrans.setCleardate(record.getCleardate());
		fundchannelcleartrans.setFeecleartransnbr(record.getCleartransnbr());
		FundchannelcleartransDAO.updateByExampleSelective(
				fundchannelcleartrans, example);
	}

	/**
	 * 判断该渠道是否设置为批量计费
	 */
	private boolean isBatchRun(short flag) {
		return FeeFlag.BATCH == flag;
	}
	/**
	 * 判断系统当前交易日期是否为该渠道的计费日期
	 */
	private boolean timeToRun(short period,Date date) throws Exception {
		switch (period) {
		case FeeSettlePeriod.PERIOD_DAY:
			return true;
		case FeeSettlePeriod.PERIOD_WEEK:
			return DateUtil.isWeekDay(date);
		case FeeSettlePeriod.PERIOD_HALFMONTH:
			return DateUtil.isHalfMonthEnd(date);
		case FeeSettlePeriod.PERIOD_MONTH:
			return DateUtil.isMonthEnd(date);
		case FeeSettlePeriod.PERIOD_SEASON:
			return DateUtil.isSeasonEnd(date);
		case FeeSettlePeriod.PERIOD_HALFYEAR:
			return DateUtil.isHalfYearEnd(date);
		case FeeSettlePeriod.PERIOD_YEAR:
			return DateUtil.isYearEnd(date);
		}
		return false;
	}
}
