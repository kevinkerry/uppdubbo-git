package com.csii.upp.batch.appl.base;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.support.TransactionTemplate;

import com.csii.pe.core.PeRuntimeException;
import com.csii.pe.service.Service;
import com.csii.upp.batch.base.BatchSupportor;
import com.csii.upp.batch.base.IJavaBatchWorker;
import com.csii.upp.batch.supportor.ApplBean;
import com.csii.upp.batch.supportor.BatchApplSupportor;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.DealResult;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.FundchannelDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dao.generate.UpersyschecknoticeDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.extend.InputFundData;
import com.csii.upp.dto.generate.Fundchannel;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.dto.generate.Upersyschecknotice;
import com.csii.upp.dto.generate.UpersyschecknoticeExample;
import com.csii.upp.dto.router.RespSysHead;
import com.csii.upp.dto.router.core.RespCheckOffDateAppl;
import com.csii.upp.event.EventManager;
import com.csii.upp.marshaller.ObjectMapMarshaller;
import com.csii.upp.service.fundprocess.CoreService;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.util.StringUtil;

/**
 * 批量应用基类
 * 
 * @author 徐锦
 * 
 */
public abstract class BaseAppl extends IJavaBatchWorker {
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 运行应用
	 * 
	 * @param entry
	 * @param argMaps
	 * @throws Exception
	 */
	protected abstract void runAppl(Object entry, Map<String, Object> argMaps) throws Exception;

	@Override
	public void run(Object entry, Map<String, Object> argMaps) {
		try {
			this.runAppl(entry, argMaps);
		} catch (Exception e) {
			log.error("run appl error", e);
			throw new PeRuntimeException(e.getMessage());
		}
	}

	protected Service getRouterService(String fundChannelCd) {
		return (Service) DefaultSupportor.getService(fundChannelCd.toLowerCase());
	}

	protected TransactionTemplate getTransactionTemplate() {
		return BatchSupportor.getTransactionTemplate();
	}

	protected EventManager getEventManager() {
		return DefaultSupportor.getEventManager();
	}

	protected ObjectMapMarshaller getObjectMapMarshaller() {
		return DefaultSupportor.getObjectMapMarshaller();
	}

	public ApplBean getApplBean() {
		return BatchApplSupportor.getApplBean();
	}

	protected Date getPostDate() {
		return SysinfoExtendDAO.getSysInfo().getPostdate();
	}

	/**
	 * 获得对账日期
	 * 
	 * @param fundChannelCd
	 * @return
	 */
	protected Date getCheckDate(Map<String, Object> argMaps) {
		return (Date) argMaps.get("checkDate");
	}

	/**
	 * 获得对账文件名
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	protected String getCheckFileName(String filePath, String fileName) {
		return StringUtil.isStringEmpty(filePath) ? null : filePath.substring(filePath.lastIndexOf("/") + 1);
	}

	/**
	 * 验证是否是核心对账数据
	 * @param argMaps
	 * @return
	 */
	protected boolean isCoreCheckData(Map<String, Object> argMaps) {
		String checkdataFlag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
		if(StringUtil.isStringEmpty(checkdataFlag)){
			throw new PeRuntimeException("checkdataFlag is not empty.");
		}
		if (FundChannelCode.CORE.equals(checkdataFlag)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取资金通道信息
	 * 
	 * @throws SQLException
	 */
	protected Fundchannel getFundchannel(String fundchannel) throws SQLException {
		return FundchannelDAO.selectByPrimaryKey(fundchannel);
	}

	protected List<String> getUperSysCheckNotice(String upperSysNbr, Date checkDate,String batchTypCd) {
		List<String> list = new ArrayList<String>();
		UpersyschecknoticeExample example = new UpersyschecknoticeExample();
		example.createCriteria().andCheckstartdateEqualTo(checkDate).andUppersysnbrEqualTo(upperSysNbr)
				.andDealresultEqualTo(DealResult.UnDeal).andBatchtypcdEqualTo(batchTypCd);
		List<Upersyschecknotice> uperSysCheckNotices;
		try {
			uperSysCheckNotices = UpersyschecknoticeDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Upersyschecknotice Failed.");
		}
		if (!uperSysCheckNotices.isEmpty()) {
			for (int i = 0; i < uperSysCheckNotices.size(); i++) {
				list.add(uperSysCheckNotices.get(i).getFilename());
			}
		}
		return list;
	}
	
	/**
	 * 查找对账申请表获得对账文件名
	 * 
	 * @return
	 */
	protected List<String> getInnerCheckApply(String fundChannelCd) {
		List<String> list = new ArrayList<String>();
		InnercheckapplyExample example = new InnercheckapplyExample();
		example.createCriteria().andDownsysnbrEqualTo(fundChannelCd).andDealcodeEqualTo(CheckFileDealFlag.UnDeal);
		List<Innercheckapply> checkapplys;
		try {
			checkapplys = InnercheckapplyDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Checkapply Failed.");
		}
		if (!checkapplys.isEmpty()) {
			for (int i = 0; i < checkapplys.size(); i++) {
				list.add(checkapplys.get(i).getFilename());
			}
		}
		return list;
	}

	protected List<Innercheckapply> getCheckApplyForCheck(String fundChannelCd, Date checkDate) {
		InnercheckapplyExample example = new InnercheckapplyExample();
		example.createCriteria().andCheckstartdateEqualTo(checkDate).andDownsysnbrEqualTo(fundChannelCd)
				.andDealcodeEqualTo(CheckFileDealFlag.Dealed);
		List<Innercheckapply> checkapplys;
		try {
			checkapplys = InnercheckapplyDAO.selectByExample(example);
		} catch (SQLException e) {
			throw new PeRuntimeException("Get Checkapply Failed.");
		}
		return checkapplys;
	}
	
	/**
	 * 
	 * @param maps
	 * @return
	 * @author wangtao
	 * 查询核心账务日期
	 */
	protected Date getCoreAccountDate(Map<String, Object> maps){
		try{
			String fundChannelCd =FundChannelCode.CORE;
			InputFundData input = new InputFundData(maps);
			CoreService coreService = (CoreService) this.getRouterService(fundChannelCd.toLowerCase());
			RespSysHead output = coreService.queryDownPostDate(input);
			RespCheckOffDateAppl deditoutput = (RespCheckOffDateAppl)output;
			return deditoutput.getDownrtxndate();
		}catch(Exception e){
			log.error("获取核心日期出错！");
			e.printStackTrace();
			return null;
		}
	}	
	
}
