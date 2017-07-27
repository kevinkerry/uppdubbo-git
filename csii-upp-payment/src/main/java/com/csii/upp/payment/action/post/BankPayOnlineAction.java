package com.csii.upp.payment.action.post;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.dao.generate.MerbaseinfoDAO;
import com.csii.upp.dao.generate.MerthirdpartacctDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Merbaseinfo;
import com.csii.upp.dto.generate.Merthirdpartacct;
import com.csii.upp.dto.generate.MerthirdpartacctExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.signature.UppSignature;
import com.csii.upp.util.StringUtil;

/**
 * 网银加签
 * 
 * @author zgb
 *
 */
public class BankPayOnlineAction extends PaymentOnlineAction {


	private UppSignature signature;

	@Override
	public void execute(Context context) throws PeException {
		String xmlData = (String) context.getData(Dict.PLAIN);
		String cyberTypeCd = (String) context.getData(Dict.CYBER_TYP_CD);
		String subMerNbr=(String) context.getData(Dict.SUB_MERCHANT_ID);
//		String payTypCd = (String) context.getData(Dict.PAY_TYP_CD);
		
		// online接收到的plan<Finance><Message><TransId>IPER</TransId><MerchantId>010020150521152412</MerchantId><MerSeqNo>wsl20160223132214</MerSeqNo><OrderId>DS2015052514</OrderId><ChannelId>05</ChannelId><TransAmt>5.00</TransAmt><MobileNo>13911111111</MobileNo><MerDateTime>20160223132214</MerDateTime><Currency>01</Currency><CustoMerName>柯南</CustoMerName><CustoMerEMail>kenan@163.com</CustoMerEMail><MerURL>http://158.222.25.107:8080/merchant/*.do?TransName=prePWDReq</MerURL><MerURL1></MerURL1><OpenPayType></OpenPayType><DefaultPayType></DefaultPayType><PayIp>127.0.0.1</PayIp><MsgExt>哈哈哈哈</MsgExt><AppointedPayType>2</AppointedPayType><AppointedPayAcctNo>6230910199001239157</AppointedPayAcctNo><AppointedMobileNo>13675821692</AppointedMobileNo><MerTransList><MerTransDetail><SubMerchantId>201506110000000292</SubMerchantId><SubMerDateTime>20160223132214</SubMerDateTime><SubMerSeqNo>wsla20160223132214</SubMerSeqNo><ProductInfo>哈哈哈哈</ProductInfo><SubTransAmt>2.00</SubTransAmt><SubMerImport>K1</SubMerImport></MerTransDetail><MerTransDetail><SubMerchantId>201506110000000299</SubMerchantId><SubMerDateTime>20160223132214</SubMerDateTime><SubMerSeqNo>wslb20160223132214</SubMerSeqNo><ProductInfo>呵呵呵呵</ProductInfo><SubTransAmt>3.00</SubTransAmt><SubMerImport>K0</SubMerImport></MerTransDetail></MerTransList></Message></Finance>
		xmlData=xmlData.replaceAll(" />", "/>");
		xmlData = xmlData.replaceAll("amp;", "");
		xmlData = xmlData.replaceFirst("<Finance>", "");
		xmlData = xmlData.replaceAll("</Finance>", "");
		xmlData = xmlData.replaceFirst("<Message>", "");
		xmlData = xmlData.replaceAll("</Message>", "");
		xmlData = xmlData.replaceAll("<TransId>IPER</TransId>", "<TransId>PP01</TransId>");
		/*xmlData = xmlData.replaceAll("<OpenPayType>","<OpenPayType>2");*/
		String merNbr = (String) context.getData(Dict.MER_NBR);
		Merbaseinfo merchant;
		try {
			merchant = MerbaseinfoDAO.selectByPrimaryKey(merNbr);
			if (merchant == null) {
				throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
			}
			String merchantName = merchant.getMername();
			
			StringBuilder sb=new StringBuilder();
			sb.append("<MerchantName>").append(merchantName).append("</MerchantName>")//.append("<OpenPayType>").append(payTypCd).append("</OpenPayType>")
			.append("<ChannelType>").append(cyberTypeCd).append("</ChannelType>");
			
			if(xmlData.contains("<MsgExt/>")){
				xmlData = xmlData.replaceAll("<MsgExt/>", "");
			}
			if(!xmlData.contains("<MsgExt>")){
				sb.append("<MsgExt>").append("UPP").append("</MsgExt>");
				sb.append(xmlData.trim());
			}else{
				int startIndex=xmlData.indexOf("<MsgExt>");
				int lastIndex=xmlData.indexOf("</MsgExt>");
				sb.append(xmlData.substring(0, startIndex));
				sb.append("<MsgExt>").append("UPP");
				sb.append(xmlData.substring(lastIndex));
			}
			xmlData=sb.toString();
			StringBuilder sb1=new StringBuilder();
			String channelId = context.getString(Dict.CHANNEL_NBR);
			 if(xmlData.contains("<ChannelId/>")){
				xmlData = xmlData.replaceAll("<ChannelId/>", "");
			 }
			if(!xmlData.contains("<ChannelId>")){
				sb1.append("<ChannelId>").append(channelId).append("</ChannelId>");
				sb1.append(xmlData.trim());
			}else{
				int startIndex=xmlData.indexOf("<ChannelId>");
				int lastIndex=xmlData.indexOf("</ChannelId>");
				sb1.append(xmlData.substring(0, startIndex));
				sb1.append("<ChannelId>").append(channelId);
				sb1.append(xmlData.substring(lastIndex));
			}

			if(xmlData.contains("<TransId>WHDW</TransId>")){
				Merthirdpartacct thirdAcct=null;
				if(!StringUtil.isStringEmpty(subMerNbr)){
					thirdAcct=MerthirdpartacctDAO.selectByPrimaryKey(context.getString(Dict.MER_NBR), subMerNbr); 
					// 判断虚账户是否存在
					if (thirdAcct == null) {
						throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
					}
				}else if(!StringUtil.isStringEmpty(context.getString(Dict.USER_NBR))){
					MerthirdpartacctExample example=new MerthirdpartacctExample();
					example.createCriteria().andUsernbrEqualTo(context.getString(Dict.USER_NBR));
					List<Merthirdpartacct> thirdAccts=MerthirdpartacctDAO.selectByExample(example);
					// 判断虚账户是否存在
					if (thirdAccts.isEmpty()) {
						throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
					}
					thirdAcct=thirdAccts.get(0);
				}
				sb1.append("<PayerAcctNbr>").append(thirdAcct.getVirtualacctnbr()).append("</PayerAcctNbr>");
				sb1.append("<PayeeAcctName>").append(thirdAcct.getAcctname()).append("</PayeeAcctName>");
				sb1.append("<PayeeAcctNbr>").append(thirdAcct.getAcctnbr()).append("</PayeeAcctNbr>");
			}
			
			String plain = sb1.toString();
			log.info("修改后的报文" + plain + "********");

			String Signature = signature.sign(plain);
			context.setData(Dict.PLAIN, plain);
			context.setData(Dict.SIGNATURE, Signature);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}

	

	public UppSignature getSignature() {
		return signature;
	}

	public void setSignature(UppSignature signature) {
		this.signature = signature;
	}

}
