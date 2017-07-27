package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.TransStatus;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.InnerpreclearfundtransDAO;
import com.csii.upp.dto.extend.PreClearCheckTrans;
import com.csii.upp.dto.generate.Innerpreclearfundtrans;
import com.csii.upp.dto.generate.InnerpreclearfundtransExample;

public class InnerpreclearfundtransExtendDAO extends BasePayDAO {
	/**
	 * 获得对账核对数据
	 * @param fundChannelCd
	 * @param checkState
	 * @return
	 */
	public static List<PreClearCheckTrans> getPreClearCheckTrans(
			String fundChannelCd, String checkState){
		PreClearCheckTrans record = new PreClearCheckTrans();
		record.setFundchannelcode(fundChannelCd);
		record.setCheckstatus(checkState);
		List<PreClearCheckTrans> preClearCheckTrans;
		preClearCheckTrans = getSqlMap().queryForList("INNERPRECLEARFUNDTRANSEXTEND.getPreClearCheckTrans", record);
		return preClearCheckTrans;
	}
			
	/**
	 * 通过资金通道码和对账状态获得内部待清算资金流水列表
	 * @param fundChannelCd
	 * @param checkState
	 * @return
	 */
	public static List<Innerpreclearfundtrans> getInnerfundtransByCheckState(
			String fundChannelCd, String checkState) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd)
				.andCheckstatusEqualTo(checkState);
		example.setOrderByClause("innerfundtransnbr asc");
		List<Innerpreclearfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerpreclearfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
//			throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
//					+ fundChannelCd);
		}
		return Innerfundtranss;
	}
	
	/**
	 * 通过资金通道码、总交易流水号获得内部待清算资金流水
	 * @param fundChannelCd
	 * @param OveralltransNbr
	 * @param checkState
	 * @return
	 */
	public static List<Innerpreclearfundtrans> getInnerfundtransByOverallTransNbr(
			String fundChannelCd, String OveralltransNbr) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd)
				.andOveralltransnbrEqualTo(OveralltransNbr);
		example.setOrderByClause("INNERRTXNNBR desc");
		List<Innerpreclearfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerpreclearfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
			 //取不到资金流水很正常，放到具体场景中判断是否抛出异常
		//	throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
			//		+ fundChannelCd);
		}
		return Innerfundtranss;
	}
	
	public static List<Innerpreclearfundtrans> getInnerfundtrans(String upperSysnbr,
			String fundChannelCd, String checkState) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd)
				.andCheckstatusEqualTo(checkState).andUppersysnbrEqualTo(upperSysnbr);
		example.setOrderByClause("INNERRTXNNBR asc");
		List<Innerpreclearfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerpreclearfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
//			throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
//					+ fundChannelCd);
		}
		return Innerfundtranss;
	}
	
	/**
	 * 通过内部待清算流水号获取内部待清算流水信息和差错表信息
	 * @param innerrtxnnbr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> getInnerfundtransForError(String errornbr,String innerfundtransnbr){
		Map map = new HashMap();
		map.put("errornbr", errornbr);
		map.put("innerfundtransnbr",innerfundtransnbr);
		return  (Map<String, Object>) getSqlMap().queryForObject(
				"INNERPRECLEARFUNDTRANSEXTEND.getInnerfundtransForCheckError", map);
	}


	
	/**
	 * 通过资金通道码、总交易流水号获得内部待清算资金流水
	 * @param fundChannelCd
	 * @param OveralltransNbr
	 * @param checkState
	 * @return
	 */
	public static List<Innerpreclearfundtrans> getInnerfundtrans(
			  String OveralltransNbr) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria()
				.andOveralltransnbrEqualTo(OveralltransNbr);
		example.setOrderByClause("INNERRTXNNBR desc");
		List<Innerpreclearfundtrans> Innerfundtranss;
		try {
			Innerfundtranss = InnerpreclearfundtransDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		if (Innerfundtranss.isEmpty()) {
			 //取不到资金流水很正常，放到具体场景中判断是否抛出异常
		//	throw new PeRuntimeException("Get Innerpreclearfundtrans is null:"
			//		+ fundChannelCd);
		}
		return Innerfundtranss;
	}
	
	/**
	 * 通过内部交易流水号判断特定交易类型的流水是否存在
	 * 
	 * @param OveralltransNbr
	 *            内部交易流水号
	 * @param innerRtxnTypCd
	 *            交易类型代码
	 * @return Innerpreclearfundtrans
	 */
	public static Innerpreclearfundtrans getInnerpreclearfundtrans(String innerRtxnNbr,
			String innerRtxnTypCd) {
		InnerpreclearfundtransExample example = new InnerpreclearfundtransExample();
		example.createCriteria().andTranscodeEqualTo(innerRtxnTypCd)
			   .andOriginnertransnbrEqualTo(innerRtxnNbr);
		try {
			List<Innerpreclearfundtrans> Innerfundtranss = InnerpreclearfundtransDAO
					.selectByExample(example);
			if (Innerfundtranss != null && Innerfundtranss.size() > 0) {
				for(Innerpreclearfundtrans Innerfundtrans:Innerfundtranss){
					if(TransStatus.SUCCESS.equals(Innerfundtrans.getTransstatus())){
						return Innerfundtranss.get(0);
					}
				}

			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Innerpreclearfundtrans Failed.");
		}
		return null;
	}
	
	
	
	/**
	 * 通过内部交易号更新内部待清算资金流水的对账状态和交易状态
	 * @param innerRtxnNbr
	 * @param checkState
	 * @param rtxnState
	 */
	public static void updateInnerfundtrans(String innerRtxnNbr,String checkState,String rtxnState) {
		Innerpreclearfundtrans fundRtxn=new Innerpreclearfundtrans();
		fundRtxn.setInnerfundtransnbr(innerRtxnNbr);
		fundRtxn.setCheckstatus(checkState);
		fundRtxn.setTransstatus(rtxnState);
		try {
			if (InnerpreclearfundtransDAO.updateByPrimaryKeySelective(fundRtxn) == 0) {
				throw new PeRuntimeException(
						"Update Innerpreclearfundtrans Failed for unknown reason.");
			}
		} catch (SQLException e) {
			throw new PeRuntimeException("Update Innerpreclearfundtrans Table Failed.",
					e);
		}
	}

	/**
	 * 将表Innerpreclearfundtrans数据植入fundchannelcleartrans
	 * 
	 * @author Chen Chao
	 */
	public static void insertInnerpreclearToFundchannnelclear(Map<String, Object> map) {
		getSqlMap().insert("INNERPRECLEARFUNDRTXNEXTEND.insertInnerpreclearToFundchannnelclear", map);
	}
}
