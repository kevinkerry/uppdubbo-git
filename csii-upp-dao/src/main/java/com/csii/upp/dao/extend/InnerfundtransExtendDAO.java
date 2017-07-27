package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.constant.PayStatus;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.InnerfundtransDAO;
import com.csii.upp.dao.generate.InnerfundtranshistDAO;
import com.csii.upp.dao.generate.OnlineorderinfoDAO;
import com.csii.upp.dao.generate.OnlinesubtransDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dao.generate.OnlinetransDAO;
import com.csii.upp.dao.generate.OnlinetranshistDAO;
import com.csii.upp.dao.generate.OveralltransDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dto.generate.Innerfundtrans;
import com.csii.upp.dto.generate.InnerfundtransExample;
import com.csii.upp.dto.generate.Innerfundtranshist;
import com.csii.upp.dto.generate.Onlineorderinfo;
import com.csii.upp.dto.generate.OnlineorderinfoExample;
import com.csii.upp.dto.generate.Onlinesubtrans;
import com.csii.upp.dto.generate.OnlinesubtransExample;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;
import com.csii.upp.dto.generate.Onlinetrans;
import com.csii.upp.dto.generate.Onlinetranshist;
import com.csii.upp.dto.generate.Overalltrans;
import com.csii.upp.dto.generate.OveralltransExample;
import com.csii.upp.dto.generate.Overalltranshist;

public class InnerfundtransExtendDAO extends BasePayDAO {

	 

	/**
	 * 通过资金通道码、总交易流水号获得内部资金流水
	 * @param fundChannelCd
	 * @param overalltransNbr
	 * @param checkState
	 * @return
	 */
	public static List<Innerfundtrans> getInnerfundtrans(
			String fundChannelCd, String overalltransNbr) {
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd)
				.andOveralltransnbrEqualTo(overalltransNbr);
		example.setOrderByClause("INNERRTXNNBR asc");
		List<Innerfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
					+ fundChannelCd);
		}
		return Innerfundtranss;
	}
	
	public static Innerfundtrans getAnotherFundRtxnByOverall(String overalltransNbr){
		return (Innerfundtrans) getSqlMap().queryForObject("Innerfundtrans.getAnotherFundRtxnByOverall", overalltransNbr);
	}
	 
	/**
	 * 通过资金通道码获得内部资金流水
	 * @param fundChannelCd
	 * @param overalltransNbr
	 * @param checkState
	 * @return
	 */
	public static List<Innerfundtrans> getInnerfundtrans(
			String fundChannelCd) {
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd);
		example.setOrderByClause("innerfundtransnbr asc");
		List<Innerfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
					+ fundChannelCd);
		}
		return Innerfundtranss;
	}
	
	/**
	 * 查询内部交易流水表某渠道超时交易的交易流水
	 * 
	 * @param seconds
	 *            超时秒数
	 * @return List<innerfundtrans>
	 */
	public static List<Innerfundtrans> queryTimeOutTxn(String channelCode, long seconds, long gridseconds) {
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("fundchannelcode", channelCode);
		paramsMap.put("timeoutsecs", seconds);
		paramsMap.put("gridseconds", seconds + gridseconds);
		if(channelCode.equals(FundChannelCode.ALIPAYCODE)||
				channelCode.equals(FundChannelCode.WECHATCODE)){
			return (List<Innerfundtrans>) getSqlMap().queryForList("INNERFUNDTRANSEXTEND.getQrcodeInnerfundtrans", paramsMap);
		}
		return (List<Innerfundtrans>) getSqlMap().queryForList("INNERFUNDTRANSEXTEND.getInnerfundtrans", paramsMap);
	}
	
	/**
	 * 获取交易流水创建过后5分钟需要做查询的交易
	 * @param channelCode
	 * @param beforequeryseconds
	 * @return
	 */
	public static List<Innerfundtrans> getNeed2QueryTxn(String channelCode, long beforequeryseconds, long gridseconds) {
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("fundchannelcode", channelCode);
		paramsMap.put("beforequeryseconds", beforequeryseconds);
		paramsMap.put("gridseconds", beforequeryseconds + gridseconds);
		if(channelCode.equals(FundChannelCode.ALIPAYCODE)||
				channelCode.equals(FundChannelCode.WECHATCODE)){
			return (List<Innerfundtrans>) getSqlMap().queryForList("INNERFUNDTRANSEXTEND.getQrcodeInnertrans2query", paramsMap);
		}
		return (List<Innerfundtrans>) getSqlMap().queryForList("INNERFUNDTRANSEXTEND.getInnertrans2query", paramsMap);
	}
	
	
	public static void modifyTimeOutTrans2Fail(final Innerfundtrans _innerfundtrans){
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					Innerfundtrans innerfundtrans = new Innerfundtrans();
					innerfundtrans.setInnerfundtransnbr(_innerfundtrans.getInnerfundtransnbr());
					innerfundtrans.setTransstatus(TransStatus.FAILURE);
					if(InnerfundtransDAO.updateByPrimaryKeySelective(innerfundtrans) == 0){
						Innerfundtranshist innerfundtranshist = new Innerfundtranshist();
						innerfundtranshist.setInnerfundtransnbr(_innerfundtrans.getInnerfundtransnbr());
						innerfundtranshist.setTransstatus(TransStatus.FAILURE);
						InnerfundtranshistDAO.updateByPrimaryKeySelective(innerfundtranshist);
					};
					
					Overalltrans overalltrans = new Overalltrans();
					overalltrans.setOveralltransnbr(_innerfundtrans.getOveralltransnbr());
					overalltrans.setOveralltransstatus(TransStatus.FAILURE);
					if (OveralltransDAO.updateByPrimaryKeySelective(overalltrans) == 0) {
						Overalltranshist overalltranshist = new Overalltranshist();
						overalltranshist.setOveralltransnbr(_innerfundtrans.getOveralltransnbr());
						overalltranshist.setOveralltransstatus(TransStatus.FAILURE);
						OveralltranshistDAO.updateByPrimaryKeySelective(overalltranshist);
					};

					Onlinetrans onlinetrans = new Onlinetrans();
					onlinetrans.setTransseqnbr(_innerfundtrans.getUppertransnbr());
					onlinetrans.setTransstatus(TransStatus.FAILURE);
					if (OnlinetransDAO.updateByPrimaryKeySelective(onlinetrans) == 0) {
						Onlinetranshist onlinetranshist = new Onlinetranshist();
						onlinetranshist.setTransseqnbr(_innerfundtrans.getUppertransnbr());
						onlinetranshist.setTransstatus(TransStatus.FAILURE);
						OnlinetranshistDAO.updateByPrimaryKeySelective(onlinetranshist);
					};

					OnlineorderinfoExample infoexample = new OnlineorderinfoExample();
					infoexample.createCriteria().andTransseqnbrEqualTo(_innerfundtrans.getUppertransnbr());
					Onlineorderinfo onlineorderinfo = new Onlineorderinfo();
					onlineorderinfo.setPaystatus(PayStatus.PAY_STATUS_FAIL);
					OnlineorderinfoDAO.updateByExampleSelective(onlineorderinfo, infoexample);

					OnlinesubtransExample transexample = new OnlinesubtransExample();
					transexample.createCriteria().andTransseqnbrEqualTo(_innerfundtrans.getUppertransnbr());
					Onlinesubtrans onlinesubtrans = new Onlinesubtrans();
					onlinesubtrans.setTransstatus(TransStatus.FAILURE);
					if (OnlinesubtransDAO.updateByExampleSelective(onlinesubtrans, transexample) == 0) {
						OnlinesubtranshistExample transexhistample = new OnlinesubtranshistExample();
						transexhistample.createCriteria().andTransseqnbrEqualTo(_innerfundtrans.getUppertransnbr());
						Onlinesubtranshist onlinesubtranshist = new Onlinesubtranshist();
						onlinesubtranshist.setTransstatus(TransStatus.FAILURE);
						OnlinesubtranshistDAO.updateByExampleSelective(onlinesubtranshist, transexhistample);
					};
				} catch (SQLException e) {
					throw new PeRuntimeException(e);
				}
				return null;
			}
		});
	}
	
	public static List<Innerfundtrans> getInnerfundtransByOveralltransNbr( String overalltransNbr) {
		InnerfundtransExample example = new InnerfundtransExample();
		example.createCriteria().andOveralltransnbrEqualTo(overalltransNbr);
		example.setOrderByClause("INNERRTXNNBR asc");
		List<Innerfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans is null:overalltransNbr="
					+ overalltransNbr);
		}
		return Innerfundtranss;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Object>> getPayTransList(Map map,
			int pageNum, int pageSize) {
		return (List<Map<String, Object>>) queryForList(
				"Innerfundtrans.getPayTransInfo", map, pageSize*(pageNum-1), pageSize*pageNum);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, Object>> getInnerfundtrans(Map map) {
		return (List<Map<String, Object>>) getSqlMap().queryForList("Innerfundtrans.getInnerfundtrans", map);
	}
	
	/**
	 * 将待对账的子交易资金流水表InnerFundTrans待对账的移植到平台资金历史表InnerFundTransHist
	 * @author Chen Chao
	 */
	public static void InsertInnerfundtransToHist(String checkdataFlag,String checkStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("checkdataFlag", checkdataFlag);
		map.put("checkStatus", checkStatus);
		getSqlMap().insert("INNERFUNDTRANSEXTEND.InsertInnerfundtransToHist", map);
	}
	
	/**
	 * 将待对账的子交易资金流水表InnerFundTrans移植到内部待清算资金流水表InnerPreClearFundTrans
	 * @author Chen Chao
	 */
	public static void InsertInnerfundtransToPreClear(String checkdataFlag,String checkStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("checkdataFlag", checkdataFlag);
		map.put("checkStatus", checkStatus);
		getSqlMap().insert("INNERFUNDTRANSEXTEND.InsertInnerfundtransToPreClear",map);
	}
	public static void updateInnerFundTransForCheck(String checkDataFlag,String transStatus,String checkStatus) {
		
		try {
			OveralltransExample overallTransExample = new OveralltransExample();
			overallTransExample.createCriteria().andCheckdataflagEqualTo(checkDataFlag);
			List<Overalltrans> overalltransList = OveralltransDAO.selectByExample(overallTransExample);
			for(Overalltrans overalltrans : overalltransList) {
				String overalltransnbr = overalltrans.getOveralltransnbr();
				Innerfundtrans record=new Innerfundtrans();
				record.setTransstatus(transStatus);
				record.setCheckstatus(checkStatus);
				record.setOveralltransnbr(overalltransnbr);
				record.setCheckdataflag(checkDataFlag);
				getSqlMap().update("INNERFUNDTRANSEXTEND.updateInnerFundTransForCheck",record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
