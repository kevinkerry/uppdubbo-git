package com.csii.upp.payment.action.query;

import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.StringUtil;
/**
 * 查询签约表中卡号付款机构号
 * @author qgs
 *
 */
public class QueryCardDeptAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		final InputPaymentData input = new InputPaymentData();
	//	final List<String> list = new ArrayList<String>(Arrays.asList(PayTypCd.FOSION, PayTypCd.CARDPWD));

				try{
					// Userpaytypsigninfo
					UserpaytypsigninfoExample userSignInfoExample = new UserpaytypsigninfoExample();
					userSignInfoExample.createCriteria().andSignnameIsNull().andPaytypcdEqualTo("1");
					List<Userpaytypsigninfo> userSignInfolist = null;
					try {
						userSignInfolist = UserpaytypsigninfoDAO.selectByExample(userSignInfoExample);
					} catch (SQLException e1) {
						throw new PeException(DictErrors.TRANS_EXCEPTION);
					}
					//查出姓名是字符串null的数据
					UserpaytypsigninfoExample userSignInfoExamplenull= new UserpaytypsigninfoExample();
					userSignInfoExamplenull.createCriteria().andSignnameEqualTo("null").andPaytypcdEqualTo("1");
					List<Userpaytypsigninfo> userSignInfolistnull = null;
					try {
						userSignInfolistnull = UserpaytypsigninfoDAO.selectByExample(userSignInfoExamplenull);
					} catch (SQLException e1) {
						throw new PeException(DictErrors.TRANS_EXCEPTION);
					}
					userSignInfolist.addAll(userSignInfolistnull);
					for (Userpaytypsigninfo usi : userSignInfolist) {
						input.setPayeracctnbr(usi.getSigncardnbr());
						// 判断卡类型
						try{
							queryCardType(input);
						}catch(Exception e){
							log.error("该卡bin未找到："+ usi.getSigncardnbr());
						}
						RespQueryCardInfo hostInfo = null;
						try{
							hostInfo = (RespQueryCardInfo) getFDPSService().queryCardDeptInfo(input);
						}catch (Exception e){
							log.error("该卡未找到："+ usi.getSigncardnbr());
						}
						if(hostInfo!=null){
							usi.setSignname(hostInfo.getPayerAcctName());
							if(!StringUtil.isStringEmpty(hostInfo.getCustCifNbr())){
								usi.setUsernbr(hostInfo.getCustCifNbr());
							}
							try {
								UserpaytypsigninfoDAO.updateByPrimaryKeySelective(usi);
							} catch (SQLException e) {
								log.error("数据库更新失败"+ usi.getSignnbr());
							}
						}
						
					}
					
//					// Onlinesubtrans
//					OnlinesubtransExample OnlinesubtransExample = new OnlinesubtransExample();
//					OnlinesubtransExample.createCriteria().andPayeracctdeptnbrIsNull().andTransstatusEqualTo(TransStatus.SUCCESS)
//							.andTranstypcdEqualTo(TransTypCd.PAY).andPaytypcdIn(list);
//					List<Onlinesubtrans> Onlinesubtranslist = null;
//					try {
//						Onlinesubtranslist = OnlinesubtransDAO.selectByExample(OnlinesubtransExample);
//					} catch (SQLException e1) {
//						throw new PeException(DictErrors.TRANS_EXCEPTION);
//					}
//					for (Onlinesubtrans ost : Onlinesubtranslist) {
//						input.setPayeracctnbr(ost.getPayeracctnbr());
//						// 判断卡类型
//						queryCardType(input);
//						RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService().queryCardDeptInfo(input);
//						ost.setPayeracctdeptnbr(hostInfo.getPayerAcctDeptNbr());
//						try {
//							OnlinesubtransDAO.updateByPrimaryKeySelective(ost);
//						} catch (SQLException e) {
//							throw new PeException(DictErrors.TRANS_EXCEPTION);
//						}
//					}
//					// Onlinetranshist
//					OnlinetranshistExample OnlinetranshistExample = new OnlinetranshistExample();
//					OnlinetranshistExample.createCriteria().andPayeracctdeptnbrIsNull().andTransstatusEqualTo(TransStatus.SUCCESS)
//							.andTranstypcdEqualTo(TransTypCd.PAY).andPaytypcdIn(list);
//					List<Onlinetranshist> Onlinetranshistlist = null;
//					try {
//						Onlinetranshistlist = OnlinetranshistDAO.selectByExample(OnlinetranshistExample);
//					} catch (SQLException e1) {
//						throw new PeException(DictErrors.TRANS_EXCEPTION);
//					}
//					for (Onlinetranshist oth : Onlinetranshistlist) {
//						input.setPayeracctnbr(oth.getPayeracctnbr());
//						// 判断卡类型
//  						queryCardType(input);
//  						RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService().queryCardDeptInfo(input);
// 						oth.setPayeracctdeptnbr(hostInfo.getPayerAcctDeptNbr());
//						try {
//							OnlinetranshistDAO.updateByPrimaryKeySelective(oth);
//						} catch (SQLException e) {
//							throw new PeException(DictErrors.TRANS_EXCEPTION);
//						}
//					}
//					// Onlinesubtranshist
//					OnlinesubtranshistExample OnlinesubtranshistExample = new OnlinesubtranshistExample();
// 					OnlinesubtranshistExample.createCriteria().andPayeracctdeptnbrIsNull().andTransstatusEqualTo(TransStatus.SUCCESS)
//							.andTranstypcdEqualTo(TransTypCd.PAY).andPaytypcdIn(list);
//					List<Onlinesubtranshist> Onlinesubtranshistlist = null;
//					try {
//						Onlinesubtranshistlist = OnlinesubtranshistDAO.selectByExample(OnlinesubtranshistExample);
//					} catch (SQLException e1) {
//						throw new PeException(DictErrors.TRANS_EXCEPTION);
//					}
//					for (Onlinesubtranshist osth : Onlinesubtranshistlist) {
//						input.setPayeracctnbr(osth.getPayeracctnbr());
//						// 判断卡类型
//						queryCardType(input);
//						RespQueryCardInfo hostInfo = (RespQueryCardInfo) getFDPSService().queryCardDeptInfo(input);
//						osth.setPayeracctdeptnbr(hostInfo.getPayerAcctDeptNbr());
//						try {
//							OnlinesubtranshistDAO.updateByPrimaryKeySelective(osth);
//						} catch (SQLException e) {
//							throw new PeException(DictErrors.TRANS_EXCEPTION);
//						}
//					}
				}catch  (Exception e) {
					throw new PeRuntimeException(e);
				}
	}	

}
