package com.csii.upp.payment.action.syn;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.PayTypCd;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.constant.SignTypCd;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.CusttransctrlExample;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.DateUtil;

/**
 * 签约信息同步（迁移用），接收签约信息同步
 * 
 * @author gaoqi
 * 
 */
public class SynSignInfoAction extends PaymentOnlineAction {
	@Override
	public void execute(Context context) throws PeException {
		// context.setData(Dict.SYN_TYPE, "SynType");
		// context.setData(Dict.PAYER_ACCT_NBR, "AcctNo");
		// context.setData(Dict.USER_NBR, "CifNo");
		// context.setData(Dict.SIGN_NBR, "SignId");
		// context.setData(Dict.PER_DAY_LIMIT, "PerDayLimit");
		// context.setData(Dict.PER_TRANS_LIMIT, "PerTransLimit");
		// context.setData(Dict.TRANS_AMT, "TransAmt");
		// context.setData(Dict.TRANS_DATE, "PayDate");
		// context.setData(Dict.PAYER_PHONE_NO, "SignPhone");
		// context.setData(Dict.SIGN_RECOMMENDED_NO, "SignRecommendedNo");
		// context.setData(Dict.OPEN_DATE, "OpenDate");
		// context.setData(Dict.OPEN_USER, "OpenUser");
		// context.setData(Dict.MODIFY_DATE, "ModifyDate");
		// context.setData(Dict.MODIFY_USER, "ModifyUser");
		// context.setData(Dict.CLOSE_DATE, "CloseDate");
		// context.setData(Dict.CLOSE_USER, "CloseUser");
		if (context.getData("SynType") == null || context.getData("AcctNo") == null) {
			throw new PeException("必输项不能为空");
		}
		// 如果为注册交易，将签约信息插入签约表
		if (context.getData("SynType").equals("FS01")) {
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andSigncardnbrEqualTo(context.getString("AcctNo")).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP)
					.andPaytypcdEqualTo(PayTypCd.FOSION);
			List<Userpaytypsigninfo> signinfo = null;
			try {
				signinfo = UserpaytypsigninfoDAO.selectByExample(example);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			Userpaytypsigninfo record = new Userpaytypsigninfo();
			if (!signinfo.isEmpty()) {
				if(SignStatus.CANCEL.equals(signinfo.get(0).getSignstatus())){
					record.setSignstatus(SignStatus.NORMA);
					try {
						UserpaytypsigninfoDAO.updateByExampleSelective(record, example);
					} catch (SQLException e) {
						log.error("error",e);
					}
				}else{
					throw new PeException("签约信息已经存在");
				}
			}else {
				record.setSigncardnbr(context.getString("AcctNo"));
				record.setSignnbr(context.getString("SignId"));
				record.setSigntypcd(SignTypCd.USER_PAY_TYP);
				record.setSignstatus(SignStatus.NORMA);
				record.setSignname(context.getString("SignUserName"));
				record.setTeller(context.getString("SignRecommendedNo"));
				record.setSignmobile(context.getString("SignPhone"));
				record.setPaytypcd(PayTypCd.FOSION);
				record.setSigndeptnbr(context.getString("SignOpenDeptId"));
				record.setSigndate(DateUtil.DateFormat_To_Date(context.getString("OpenDate")));
			//	record.setUsernbr(context.getString("OpenUser"));
				try {
					UserpaytypsigninfoDAO.insert(record);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
				Custtransctrl record1 = new Custtransctrl();
				record1.setSignnbr(record.getSignnbr());
				record1.setSigntypcd(SignTypCd.USER_PAY_TYP);
				record1.setPerdaylimit(new BigDecimal(context.getString("PerDayLimit")));
				record1.setPertranslimit(new BigDecimal(context.getString("PerTransLimit")));
				record1.setDayamt(new BigDecimal("0.0000"));
				record1.setDayamtdate(DateUtil.DateFormat_To_Date(context.getString("OpenDate")));
				try {
					CusttransctrlDAO.insert(record1);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
		// 如果为调整交易限额交易，则根据卡号查询签约表得到签约号，更新客户控制表
		else if (context.getData("SynType").equals("FS03")) {
			// 查询签约表获取签约号
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andSigncardnbrEqualTo(context.getString("AcctNo")).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP)
					.andPaytypcdEqualTo(PayTypCd.FOSION);
			List<Userpaytypsigninfo> signinfo = null;
			try {
				signinfo = UserpaytypsigninfoDAO.selectByExample(example);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			if (signinfo.isEmpty()) {
				throw new PeException("签约信息为空");
			}
			if(!SignStatus.NORMA.equals(signinfo.get(0).getSignstatus())){
				throw new PeException("签约状态不为正常状态，无法调整交易限额！");
			}
			String signNbr = signinfo.get(0).getSignnbr();
			CusttransctrlExample example1 = new CusttransctrlExample();
			example1.createCriteria().andSignnbrEqualTo(signNbr).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP);
			Custtransctrl record = new Custtransctrl();
			// 更新每日限额和单笔限额
			record.setPerdaylimit(new BigDecimal(context.getString("PerDayLimit")));
			record.setPertranslimit(new BigDecimal(context.getString("PerTransLimit")));
			try {
				CusttransctrlDAO.updateByExampleSelective(record, example1);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		// 如果为支付交易，更新日累计限额
		else if (context.getData("SynType").equals("FS11")) {
			// 查询签约表获取签约号
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andSigncardnbrEqualTo(context.getString("AcctNo")).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP)
					.andPaytypcdEqualTo(PayTypCd.FOSION);
			List<Userpaytypsigninfo> signinfo = null;
			try {
				signinfo = UserpaytypsigninfoDAO.selectByExample(example);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			if (signinfo.isEmpty()) {
				throw new PeException("签约信息为空");
			}
			if(!SignStatus.NORMA.equals(signinfo.get(0).getSignstatus())){
				throw new PeException("签约状态不为正常状态，无法同步支付交易！");
			}
			String signNbr = signinfo.get(0).getSignnbr();
			// 查询客户交易控制表，获取日累计限额
			CusttransctrlExample example1 = new CusttransctrlExample();
			example1.createCriteria().andSignnbrEqualTo(signNbr).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP);
			List<Custtransctrl> custCtrl = null;
			try {
				custCtrl = CusttransctrlDAO.selectByExample(example1);
			} catch (SQLException e1) {
				log.error(e1.getMessage());
			}
			if (custCtrl.isEmpty()) {
				throw new PeException(DictErrors.CUST_TRANS_LIMIT_ERROR);
			}
			Custtransctrl record = new Custtransctrl();
			// 更新日累计限额和单日交易额日期
			record.setDayamt(custCtrl.get(0).getDayamt().add(new BigDecimal(context.getString("TransAmt"))));
		//	record.setDayamtdate(DateUtil.DateFormat_To_Date(context.getString("PayDate")));
			try {
				CusttransctrlDAO.updateByExampleSelective(record, example1);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		// 如果交易为冻结、解冻、注销。更新签约状态。
		else if (context.getData("SynType").equals("FS04") || context.getData("SynType").equals("FS05")
				|| context.getData("SynType").equals("FS07")) {
			// 查询签约信息表，判断该签约信息是否存在
			UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
			example.createCriteria().andSigncardnbrEqualTo(context.getString("AcctNo")).andSigntypcdEqualTo(SignTypCd.USER_PAY_TYP)
					.andPaytypcdEqualTo(PayTypCd.FOSION);
			List<Userpaytypsigninfo> signinfo = null;
			try {
				signinfo = UserpaytypsigninfoDAO.selectByExample(example);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			if (signinfo.isEmpty()) {
				throw new PeException(DictErrors.CUST_NOT_SIGN);
			}
			// 准备更新签约表信息
			Userpaytypsigninfo record = new Userpaytypsigninfo();
			if (context.getData("SynType").equals("FS04")) {
				if(!SignStatus.NORMA.equals(signinfo.get(0).getSignstatus())){
					throw new PeException("签约状态不为正常状态不能冻结操作！");
				}
				record.setSignstatus(SignStatus.FROZEN);
			} else if (context.getData("SynType").equals("FS05")) {
				if(!SignStatus.FROZEN.equals(signinfo.get(0).getSignstatus())){
					throw new PeException("签约状态不为冻结状态不能解冻操作！");
				}
				record.setSignstatus(SignStatus.NORMA);
			} else {
				if(SignStatus.CANCEL.equals(signinfo.get(0).getSignstatus())){
					throw new PeException("签约状态为注销状态不能注销操作！");
				}
				record.setSignstatus(SignStatus.CANCEL);
			}
			try {
				UserpaytypsigninfoDAO.updateByExampleSelective(record, example);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		} else {
			throw new PeException("同步内容类型传输错误");
		}
	}
	
}
