package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.OveralltranshistDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Overalltranshist;
import com.csii.upp.dto.generate.OveralltranshistExample;

public class OveralltranshistExtendDAO extends BasePayDAO {
	/**
	 * 通过总交易号更新总交易流水的交易状态
	 * 
	 * @param innerRtxnNbr
	 * @param rtxnState
	 * @throws PeException 
	 */
	public static void updateOveralltranshist(String OveralltransNbr,
			String OveralltransState, String checkState) throws PeException {
		OveralltranshistExample example = new OveralltranshistExample();
		example.createCriteria().andOveralltransnbrEqualTo(OveralltransNbr);
		Overalltranshist Overalltrans = new Overalltranshist();
		Overalltrans.setOveralltransstatus(OveralltransState);
		Overalltrans.setCheckstatus(checkState);
		try {
			if (OveralltranshistDAO.updateByExampleSelective(Overalltrans, example) == 0) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
	}

	public static Date getRtxnDateByOveralltransbr(String OveralltransNbr) throws PeException {
		OveralltranshistExample example = new OveralltranshistExample();
		example.createCriteria().andOveralltransnbrEqualTo(OveralltransNbr);
		try {
			List<Overalltranshist> list = OveralltranshistDAO
					.selectByExample(example);
			if (list != null && list.size() > 0) {
				return list.get(0).getTransdate();
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		return null;
	}
	
	public static List<Map> getRtxnOveralltransStat(Map map) {
			return (List<Map>)getSqlMap().queryForList("Overalltrans.getRtxnOveralltransStat",map);
		
	}
	
	public static List<Map<String, Object>> QueryOveralltransHistList(Map map, int pageNum, int pageSize) {
		return (List<Map<String, Object>>) queryForList("Overalltrans.getOveralltransHist", map,
				pageSize * (pageNum - 1), pageSize * pageNum);
	}
	
	/**
	 * 将OverallTransHist (总交易流水历史表)360天之前的非待对账的数据移植到总交易流水历史All表OverallTransHistAll表里
	 * @author chen chao
	 */
	public static void insertOveralltranshistToAll(Date transDate) {
		getSqlMap().insert("OVERALLTRANSHISTEXTEND.insertOveralltranshistToAll", transDate);
	}
	
	public static List<Overalltranshist> getOveralltranshistList(String fundChannelTypCd,String checkDataFlag) {
        Map<String, Object> para = new HashMap<String,Object>();
        para.put("fundchanneltype", fundChannelTypCd);
        para.put("checkdataflag", checkDataFlag);
		return getSqlMap().queryForList("OVERALLTRANSHISTEXTEND.getOveralltranshistList", para);
	}
}
