package com.csii.upp.batch.appl.qrcodepay;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.event.handler.RunQueEvent;
import com.csii.upp.constant.BatchType;
import com.csii.upp.constant.CheckFileDealFlag;
import com.csii.upp.constant.CheckFlag;
import com.csii.upp.constant.DateFormatCode;
import com.csii.upp.constant.FundChannelCode;
import com.csii.upp.dao.generate.ChecknoticeDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dao.generate.SysinfoDAO;
import com.csii.upp.dto.generate.Checknotice;
import com.csii.upp.dto.generate.ChecknoticeExample;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.supportor.DefaultSupportor;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.DateUtil;
import com.csii.upp.util.FileHandler;
import com.csii.upp.util.StringUtil;

/**
 * 二维码对账文件申请
 * 
 * @author wangtao
 * 
 */
public class CheckFileApplyQRCodeAppl extends BaseAppl {
	/**
	 * 资金渠道代码
	 * 
	 * @return
	 */
	protected String getFundChannelCd(Map<String, Object> argMaps){
		return FundChannelCode.QRCODE;
	}
	/**
	 * 申请对账文件,只判断对账文件是否 存在
	 * 
	 * @return
	 * @throws PeException 
	 * @throws  
	 */
	protected void applyCheckFile(Innercheckapply innercheckapply, Map<String, Object> maps) throws PeException{
		Date checkDate = null;
		String fundChannelCd = innercheckapply.getDownsysnbr();
		 Date checkFDPSDate = this.getCheckDate(maps);
		//对账文件日期
		try {
			checkDate = SysinfoDAO.selectByPrimaryKey(fundChannelCd).getPrevpostdate();
		} catch (SQLException e1) {
			throw new PeRuntimeException("select sysinfo Table Failed.", e1);
		}
		innercheckapply.setCheckstartdate(checkFDPSDate);
		innercheckapply.setCheckenddate(checkFDPSDate);
		//对账文件路径
		String filePath = StringUtil.parseObjectToString(
				getApplBean().getCheckFileParserLocalPathMap().get(fundChannelCd.toLowerCase()));
		//对账文件名称
		String fileNamePre=DateUtil.Date_To_DateTimeFormat(checkDate, DateFormatCode.DATE_FORMATTER3);
		String alipayfileName = fileNamePre+"_2PAYZFB";
		String wechatfileName = fileNamePre+"_2PAYCFT";	
		
		//判断支付宝微信对账文件是否存在，不存在直接退出
		boolean isAlipayFileExist =FileHandler.isExistsFile(filePath, alipayfileName);
		boolean isWechatFileExist = FileHandler.isExistsFile(filePath, wechatfileName);
		if(!isAlipayFileExist){
			log.info(new StringBuilder().append("二维码前置支付宝对账文件在[").append(alipayfileName).append("]").append("文件路径[").append(filePath)
					.append("]").append("不存在 "));
			return;
		}
		if(!isWechatFileExist){
			log.info(new StringBuilder().append("二维码前置微信对账文件[").append(wechatfileName).append("]").append("文件路径[").append(filePath)
					.append("]").append("无文件"));
			return;
		}
		//判断文件是否保存在表checknotice中，如果存在说明已经执行过对账队列，退出执行,此队列自动出发，定时执行
		ChecknoticeExample example = new ChecknoticeExample();
		example.createCriteria().andFundchannelcodeEqualTo(fundChannelCd).andCheckdateEqualTo(checkDate);
		try {
			List<Checknotice> checknoticeList = ChecknoticeDAO.selectByExample(example);
			if(checknoticeList.size()>=2){//支付宝和微信两条记录
				log.info("二维码前置支付宝和微信对账文件已处理！");
				return ;
			}
		} catch (SQLException e1) {
			throw new PeRuntimeException("select checknotice Table Failed.", e1);
		}
		
		//对账文件号 支付宝：00+数列号 微信:11加序列号
		String checkApplyNbr=IDGenerateFactory.generateSeqId();
		innercheckapply.setCheckapplynbr("00"+checkApplyNbr);
		//插入支付宝
		this.handleCheckApply(innercheckapply);		

		innercheckapply.setCheckapplynbr("00"+checkApplyNbr);
 		innercheckapply.setDealmsg("申请已处理");
 		innercheckapply.setFilename(alipayfileName);
 		try {
 			InnercheckapplyDAO.updateByPrimaryKeySelective(innercheckapply);
 			Checknotice checknotice = new Checknotice();
	 		checknotice.setCheckdate(checkDate);
	 		checknotice.setBatchtypcd(BatchType.CHECK);
	 		checknotice.setFilename(alipayfileName);
	 		checknotice.setFundchannelcode(fundChannelCd);
	 		checknotice.setChecknoticenbr(IDGenerateFactory.generateSeqId());
	 		ChecknoticeDAO.insertSelective(checknotice);
 		} catch (SQLException e) {
			throw new PeRuntimeException("Insert/Update innercheckapply/checknotice Table Failed.", e);
		}
	
		//插入微信
		innercheckapply.setFilename(null);
		innercheckapply.setCheckapplynbr("11"+checkApplyNbr);
		this.handleCheckApply(innercheckapply);

		innercheckapply.setCheckapplynbr("11"+checkApplyNbr);
 		innercheckapply.setDealmsg("申请已处理");
 		innercheckapply.setFilename(wechatfileName);
 		try {
 			InnercheckapplyDAO.updateByPrimaryKeySelective(innercheckapply);
 			Checknotice checknotice = new Checknotice();
	 		checknotice.setCheckdate(checkDate);
	 		checknotice.setBatchtypcd(BatchType.CHECK);
	 		checknotice.setFilename(wechatfileName);
	 		checknotice.setFundchannelcode(fundChannelCd);
	 		checknotice.setChecknoticenbr(IDGenerateFactory.generateSeqId());
	 		ChecknoticeDAO.insertSelective(checknotice);
 		} catch (SQLException e) {
			throw new PeRuntimeException("Insert/Update innercheckapply/checknotice Table Failed.", e);
		}
 		// 异步线程处理
 		RunQueEvent event=new RunQueEvent();
		event.setQueNbr(24L);
		event.setRunDate(DateUtil.Date_To_DateTimeFormat(checkDate, DateFormatCode.DATE_FORMATTER1));
		DefaultSupportor.getEventManager().doService(event); 		
	
	}
	
	@Override
	protected void runAppl(Object entry, Map<String, Object> maps) {		
		String fundChannelCd = getFundChannelCd(maps);
		Innercheckapply innercheckapply = new Innercheckapply();
		innercheckapply.setUppersysnbr(FundChannelCode.FDPS);
		innercheckapply.setDownsysnbr(fundChannelCd);
		innercheckapply.setCheckflag(CheckFlag.CHECKFLAG_WORK);
		innercheckapply.setDealcode(CheckFileDealFlag.UnDeal);
		innercheckapply.setDealmsg("申请已提交");
		try {
			//申请对账文件
			this.applyCheckFile(innercheckapply, maps);
			
		}catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		}
	}
	
	protected void handleCheckApply(final Innercheckapply innercheckapply){
		try {
			if(StringUtil.isStringEmpty(innercheckapply.getFilename())){
				innercheckapply.setFilename("无文件");
			}
			InnercheckapplyExample example = new InnercheckapplyExample();
			example.createCriteria().andDownsysnbrEqualTo(innercheckapply.getDownsysnbr())
			.andCheckstartdateEqualTo(innercheckapply.getCheckstartdate())
					.andFilenameEqualTo(innercheckapply.getFilename());
			InnercheckapplyDAO.deleteByExample(example);
			if(StringUtil.isStringEmpty(innercheckapply.getCheckapplynbr())){
				innercheckapply.setCheckapplynbr(IDGenerateFactory.generateSeqId());
			}
			InnercheckapplyDAO.insertSelective(innercheckapply);
		} catch (SQLException e) {
			throw new PeRuntimeException("Insert/Update innercheckapply Table Failed.", e);
		}
	}
	
	
}
