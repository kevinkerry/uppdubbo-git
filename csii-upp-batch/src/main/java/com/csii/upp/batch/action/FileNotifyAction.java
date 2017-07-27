package com.csii.upp.batch.action;

import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.dao.extend.SysinfoExtendDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.supportor.IDGenerateFactory;
/** 
 * 文件对账通知
 * 
 * @author Chen Chao
 *
 */
public class FileNotifyAction extends BaseAction {

	@SuppressWarnings("rawtypes")
	private Map queMap;

	@SuppressWarnings("rawtypes")
	public void setQueMap(Map queMap) {
		this.queMap = queMap;
	}

	@Override
	public void execute(Context ctx) throws PeException {
 	try {
 			
 				String checkDataFlag=ctx.getString(Dict.CHECK_DATA_FLAG);
 				String filename = ctx.getString(Dict.FILE_NAME);
 				
 				Innercheckapply innercheckapply = new Innercheckapply();
			    innercheckapply.setDealmsg("初始化");
			    innercheckapply.setDealcode("UnDeal");
				innercheckapply.setFilename(filename);
				innercheckapply.setDownsysnbr("AUTH");
				innercheckapply.setCheckflag("");
				innercheckapply.setFilename("IPP_1.txt");
			    innercheckapply.setCheckstartdate(SysinfoExtendDAO.getSysInfo().getPostdate());
				innercheckapply.setCheckenddate(SysinfoExtendDAO.getSysInfo().getPostdate());
				innercheckapply.setCheckapplynbr(IDGenerateFactory.generateSeqId());
				InnercheckapplyDAO.insertSelective(innercheckapply);
			    // 调起相应对账队列
 	 			ctx.setData(Dict.QUE_NBR,this.queMap.get("AUTH"));
		
 		
 			
 			
// 			//同步记账日期
// 			Sysinfo sysinfo = SysinfoExtendDAO.getSysInfo();
// 			Date eaccountRtxnDate = DateUtil.DateFormat_To_Date(ctx.getString("upperRtxnDate"));
// 			if (!sysinfo.getPostdate().equals(eaccountRtxnDate)) {
// 				SysinfoExtendDAO.updatePowerpayDate(eaccountRtxnDate);
// 			}
// 		
//			Checknotice record=new Checknotice();
//			record.setFundchannelcode(FundChannelCode.EACCOUNT);
//			record.setFilenbr(ctx.getString("FileNo"));
//			String sAccountDate = ctx.getString("AccountDate");
//			
//			if(sAccountDate!=null){
//				
//				record.setCheckdate(DateUtil.DateFormat_To_Date(sAccountDate));
//				
//			}
//			record.setBatchtypcd(ctx.getString("BatchType"));
//			record.setFilename(ctx.getString("FileName"));
//			String sNum = ctx.getString("Num");
//			if(sNum!=null&&!"".equals(sNum)){
//				
//				record.setTotalnum(Long.parseLong(sNum));
//				
//			}
//			
//			String sAmount = ctx.getString("Amount");
//			if(sAmount!=null&&!"".equals(sAmount)){
//				
//				record.setTotalamount(new BigDecimal(sAmount));
//				
//			}
//			record.setFilepath(ctx.getString("FilePath"));
////			record.setExtfld1(ctx.getString("ExtFld1"));
////			record.setExtfld2(ctx.getString("ExtFld2"));
////			record.setExtfld3(ctx.getString("ExtFld3"));
//			
//			ChecknoticeDAO.insertSelective(record);
//			
//			//补一条申请结果便于电子账户开始对账 
//			
//			String dealcode =CheckFileDealFlag.UnDeal ;
//			String dealmsg = "申请已提交";
//			//PT_HVPS_对账日期_时间戳.txt
//		/*	String filename = "PT_"+fundChannelCd.toLowerCase()+DateUtil.Date_To_DateTimeFormat(SysinfoExtendDAO.getSysInfo()
//					.getPrevpostdate(), DateFormatCode.DATE_FORMATTER3)+".txt";*/
//			 
//			
//			/*String temp[] = filename.split("\\\\"); 
//			if (temp.length > 1) { 
//				filename = temp[temp.length - 1]; 
//			} 截取文件名 
//			*/
//			
//			Checkapply checkapply = new Checkapply();
//			checkapply.setFundchannelcode(record.getFundchannelcode());
//			checkapply.setCheckdate(record.getCheckdate());
//			checkapply.setCheckflag(CheckFlag.CHECKFLAG_WORK);
//			
//			checkapply.setDealcode(dealcode);
//			checkapply.setDealmsg(dealmsg);
//			checkapply.setFilename(ctx.getString("FileName"));
//		//	checkapply.setReceivenum(output.getHvpsrcvtotal());
//		//	checkapply.setSendnum(output.getHvpssendtotal());
//		//	checkapply.setTotalnum(output.getHvpstotalnum());
//			
//			try {
//				CheckapplyExample example = new CheckapplyExample();
//				example.createCriteria().andCheckdateEqualTo(checkapply.getCheckdate())
//						.andFundchannelcodeEqualTo(checkapply.getFundchannelcode())
//						.andFilenameEqualTo(checkapply.getFilename());
//				List<Checkapply> checkapplys=CheckapplyDAO.selectByExample(example);
//				if (checkapplys==null||checkapplys.isEmpty()) {
//					CheckapplyDAO.insertSelective(checkapply);
//				}else{
//					CheckapplyDAO.updateByPrimaryKeySelective(checkapply);
//				}
//			} catch (SQLException e) {
//				throw new PeRuntimeException("Inser/Update Checkapply Table Failed.", e);
//			}
		} catch (Exception e) {
			throw new PeException(e);
		} 
		
	}

}
