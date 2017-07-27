package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.OnlinesubtranshistDAO;
import com.csii.upp.dto.generate.Batchconfirmsubcleartrans;
import com.csii.upp.dto.generate.Onlinesubtranshist;
import com.csii.upp.dto.generate.OnlinesubtranshistExample;

public class OnlinesubtranshistExtendDAO extends BasePayDAO {
	/**
	 * 通过总交易流水号查询子订单信息
	 * 
	 * @param transSeqNbr
	 * @return
	 */
	public static List<Onlinesubtranshist> getOnlineSubTransHistBytransSeqNbr(String transSeqNbr) {
		OnlinesubtranshistExample example = new OnlinesubtranshistExample();
		example.createCriteria().andTransseqnbrEqualTo(transSeqNbr);
		example.setOrderByClause("SubTransSeqNbr asc");
		List<Onlinesubtranshist> Onlinesubtranshists;
		try {
			Onlinesubtranshists = OnlinesubtranshistDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Onlinesubtranshist Failed.");
		}
		if (Onlinesubtranshists.isEmpty()) {
			throw new PeRuntimeException("Get Onlinesubtranshist is null:");
		}
		return Onlinesubtranshists;
	}

	/**
	 * 通过支付子交易清算表的商户号、子订单号、订单日期查询订单信息
	 * 
	 * @return
	 */
	public static List<Onlinesubtranshist> getOnlineSubTransHist(Batchconfirmsubcleartrans subhist) {
		OnlinesubtranshistExample example = new OnlinesubtranshistExample();
		example.createCriteria().andMernbrEqualTo(subhist.getMernbr()).andSubtransseqnbrEqualTo(subhist.getOrigsubtransseqnbr());
		example.setOrderByClause("SubTransSeqNbr asc");
		List<Onlinesubtranshist> Onlinesubtranshists;
		try {
			Onlinesubtranshists = OnlinesubtranshistDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Onlinesubtranshist Failed.");
		}
		if (Onlinesubtranshists.isEmpty()) {
			throw new PeRuntimeException("Get Onlinesubtranshist is null:");
		}
		return Onlinesubtranshists;
	}

	/**
	 * move onlinesubtranshist to onlinesubtranshistall
	 */
	public static void insertOnlinesubtranshistToAll(Map<String, Object> map) {
		getSqlMap().insert("ONLINESUBTRANSHISTEXTEND.insertOnlinesubtranshistToAll", map);
	}

	/**
	 * 
	 */
	public static void updateSubHistTransStep(Map<String, Object> map) {
		getSqlMap().update("ONLINESUBTRANSHISTEXTEND.updateSubHistTransStep", map);
	}
}
