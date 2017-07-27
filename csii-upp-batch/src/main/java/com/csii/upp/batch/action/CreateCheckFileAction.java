package com.csii.upp.batch.action;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;

/**
 * 生成对账文件用于测试
 * 
 * @author 徐锦
 * 
 */
public class CreateCheckFileAction extends BaseAction {

	@Override
	public void execute(Context ctx) throws PeException {
/*		String fundChannelCd = ctx.getString(Dictionary.FundChannelCd);
		List<Innerfundtrans> Innerfundtranss =null;
		try {
			Innerfundtranss = InnerfundtransExtendDAO
					.getInnerfundtrans(fundChannelCd);
		} catch (Exception e) {
		}
		FileFormat fileFormat = BatchApplSupportor.getApplBean()
				.getCheckFileParser().getFileFormat(fundChannelCd.toLowerCase());
		String localPath = StringUtil.parseObjectToString(BatchApplSupportor
				.getApplBean().getCheckFileParserLocalPathMap()
				.get(fundChannelCd.toLowerCase()));
		String fileName = fileFormat.getPrefix()
				+ DateUtil.Date_To_DateTimeFormat(SysinfoExtendDAO.getSysInfo(fundChannelCd)
						.getPrevpostdate(), DateFormatCode.DATE_FORMATTER3)
				+ fileFormat.getSuffix();
		// 生成下发文件
		this.writeIssueFile(Innerfundtranss, fileFormat, localPath, fileName,fundChannelCd);
		// 下发文件上传到服务器
		if (!FtpUtil.upload(fileName, localPath+fileName)) {
			throw new PeRuntimeException(StringUtil.buildString(
					"upload issue file:", fileName, " error"));
		}*/
	}

/*	private void writeIssueFile(List<Innerfundtrans> Innerfundtranss, FileFormat format,
			String localPath, String fileName,String fundChannelCd) {
		FileOutputStream out = null;
		try {
			String encoding = format.getEncoding();
			String lineSeparator = format.getLineSeparator();
			FileHandler.createFile(localPath, fileName);
			if(Innerfundtranss==null){
				return;
			}
			out = new FileOutputStream(localPath + fileName, true);
			for (Innerfundtrans Innerfundtrans : Innerfundtranss) {
				if(FundChannelCode.CNAPS2.equals(fundChannelCd)){
					Map<String, Object> map=DefaultSupportor.getObjectMapMarshaller().marshall(
							Innerfundtrans);
					//交易状态和人行处理状态转换
					String rtxnState=StringUtil.parseObjectToString(map.get(Dictionary.RtxnState));
					if(RtxnState.SUCCESS.equals(rtxnState)||RtxnState.SEND.equals(rtxnState)){
						map.put(Dictionary.RtxnState,"PR03");
					}else{
						map.put(Dictionary.RtxnState,"PR08");
					}
					String standardString = BatchUtil.getFormatString(map
							, format);
					FileHandler.writeRecorde(standardString + lineSeparator, out,
							encoding);
				}else{
					if(RtxnState.SUCCESS.equals(Innerfundtrans.getTransstatus())||RtxnState.SEND.equals(Innerfundtrans.getTransstatus())){
						String standardString = BatchUtil.getFormatString(
								DefaultSupportor.getObjectMapMarshaller().marshall(
										Innerfundtrans), format);
						FileHandler.writeRecorde(standardString + lineSeparator, out,
								encoding);
					}
				}
	

			}
		} catch (Exception e) {
			throw new PeRuntimeException(e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}*/
}
