package com.csii.upp.dao.extend;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.BasePayDAO;
import com.csii.upp.dao.generate.SysinfoDAO;
import com.csii.upp.dto.generate.Sysinfo;
import com.csii.upp.util.StringUtil;

public class SysinfoExtendDAO extends BasePayDAO {

	/**
	 * 获取资金处理平台信息
	 * 
	 * @return 获取资金处理平台信息
	 * @throws SQLException
	 */
	public static Sysinfo getSysInfo() {
		return getSysInfo(FundChannelCode.FDPS);
	}


	
	/**
	 * 根据电子账户系统时间更新资金处理平台时间
	 * 
	 * @param effectDate
	 */
	public static synchronized void updateUppDate(Date effectDate) {
		
		Sysinfo info = getSysInfo(FundChannelCode.FDPS);
		Map<String, Object> updateMap = new HashMap<String, Object>();
		
		Date nowDate = info.getPostdate();//当前账务日期
		Date nextDate = info.getNextpostdate();//下一账务日期
		updateMap.put("postdate", nowDate);
		updateMap.put("nextDate", nextDate);
		updateMap.put("nexttwoDate", getNextDate(nextDate));
		//更新确认收货时间
		getSqlMap().update("SYSINFO.updateConfirmDate",updateMap);
		//日切
		updateSysInfo(FundChannelCode.FDPS, effectDate,null);
		updateSysInfo(FundChannelCode.UNIONPAY, effectDate,null);
		updateSysInfo(FundChannelCode.ALIPAY, effectDate,null);
		updateSysInfo("WXZF", effectDate,null);		
		//对账申请表更新
		
		updateMap.put("CHECKAPPLYNBR1", "CORE"+getRandomNbr());
		updateMap.put("CHECKAPPLYNBR2", "CORE"+getRandomNbr());
		updateMap.put("CHECKAPPLYNBR3", "UNIONPAY"+getRandomNbr());
		getSqlMap().insert("SYSINFO.insertCheckFIle",updateMap);
		//插入下游流水表
	//	getSqlMap().insert("SYSINFO.insertDownSys");
		//updateSysInfo(FundChannelCode.QRCODE, effectDate,null);
	}

	/**
	 * 更新系统信息
	 * 
	 * @param fundchannelCode
	 * @param effectDate
	 */
	public static void updateSysInfo(String fundchannelCode, Date effectDate,String checkResult) {
		Sysinfo info = getSysInfo(fundchannelCode);
		Date nowDate = info.getPostdate();//当前账务日期
		Date nextDate =getNextDate(nowDate);
		info.setPrevpostdate(nowDate);
		info.setPostdate(nextDate);
		info.setNextpostdate(getNextDate(nextDate));
//		if(effectDate != null)
//		{
//			info.setPrevpostdate(info.getPostdate());
//		    info.setPostdate(effectDate);
//		}
		info.setCheckresult(checkResult);
		try {
			SysinfoDAO.updateByPrimaryKeySelective(info);
		} catch (SQLException e) {
			throw new PeRuntimeException(StringUtil.buildString(
					"Update channel[", fundchannelCode, "] postdate failed."));
		}
	}
	/**
	 * poc使用
	 * @param date
	 * @return
	 */
	private static Date getNextDate(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, +1); //今天的时间加一天  
        date = calendar.getTime();  
        return date;          
    }
	
	public static String getRandomNbr(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMMssSSS");
		String str= dateFormat.format(new Date());
		return str.substring(10, str.length());
	}
	
	
	/**
	 * 更新系统信息(适应新的通道日切通知 )
	 * 
	 * @param fundchannelCode
	 * @param effectDate
	 */
	 public static void updateSysInfo(String fundchannelCode,Date prevPostDate, Date effectDate,
			 Date NextPostDate,String checkResult,String clearResult,String sysState,
			 String holidayFlag,String reMark,Long prevWorkRound, Long currWorkRound) {
		Sysinfo info = getSysInfo(fundchannelCode);
		info.setCheckresult(checkResult);
		info.setClearresult(clearResult);
//		info.setExfld1(sysState);
//		info.setExfld2(holidayFlag);
		//info.setExfld3(exfld3);
		info.setSysstate(sysState);
		info.setHolidayflag(holidayFlag);
		info.setNextpostdate(NextPostDate);
		info.setPrevpostdate(prevPostDate);
		info.setPostdate(effectDate);
		info.setCheckresult(checkResult);
		info.setPreworkround(prevWorkRound);
		info.setWorkgroup(currWorkRound);
		try {
			SysinfoDAO.updateByPrimaryKeySelective(info);
		} catch (SQLException e) {
			throw new PeRuntimeException(StringUtil.buildString(
					"Update channel[", fundchannelCode, "] postdate failed."));
		}
	} 

	/**
	 * 获取特定系统信息
	 * 
	 * @param fundchannelCode
	 * @return
	 */
	public static Sysinfo getSysInfo(String fundchannelCode) {
		Sysinfo sysinfo;
		try {
			sysinfo = SysinfoDAO.selectByPrimaryKey(fundchannelCode);
		} catch (SQLException e) {
			throw new PeRuntimeException(StringUtil.buildString("Get channel[",
					fundchannelCode, "] info failed."));
		}
		if (sysinfo==null) {
			throw new PeRuntimeException(StringUtil.buildString("Channel[",
					fundchannelCode, "] info can not be found."));
		} else {
			return sysinfo;
		}
	}
	
	public static Sysinfo getMinSysInfo() {
		return (Sysinfo)getSqlMap().queryForObject("SYSINFO.getMinSysInfo");
	}
	


}
