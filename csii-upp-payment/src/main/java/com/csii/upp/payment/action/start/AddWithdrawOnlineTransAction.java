package com.csii.upp.payment.action.start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.DepartmentNbr;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.MerthirdpartacctDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.extend.SubTransData;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.MeracctinfoExample;
import com.csii.upp.dto.generate.Merthirdpartacct;
import com.csii.upp.dto.generate.MerthirdpartacctExample;
import com.csii.upp.util.StringUtil;
/**
 * 提现交易插入子交易明细和总交易明细
 * @author xujin
 *
 */
public class AddWithdrawOnlineTransAction  extends BaseOnlineTransAction {

	@Override
	protected void prepareOnlineTrans(Context ctx,InputPaymentData inputData) throws PeException {
		//验证商户流水号和交易金额
		this.checkCommon(inputData.getMerseqnbr(), inputData.getTransamt());
		//从虚账户获得收款账户和付款账户信息
		String subMerNbr=ctx.getString(Dict.SUB_MERCHANT_ID);
		String userNbr=ctx.getString(Dict.USER_NBR);
		String transerDeptNbr=null;
		if(!StringUtil.isStringEmpty(subMerNbr)&&!StringUtil.isStringEmpty(userNbr)){
			throw new PeException(DictErrors.ONLY_ONE);
		}
		//验证富阳付款账户名和提现账户名是否一致
		String payerName = ctx.getString(Dict.PAYER_ACCT_NAME);
		String payeeName = ctx.getString(Dict.PAYEE_ACCT_NAME);
		Merthirdpartacct thirdAcct=null;
		try {
			if(!StringUtil.isStringEmpty(subMerNbr)){
				thirdAcct=MerthirdpartacctDAO.selectByPrimaryKey(inputData.getMernbr(), subMerNbr); 
				// 判断虚账户是否存在
				if (thirdAcct == null) {
					throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
				}
				
				MeracctinfoExample examplemac=new MeracctinfoExample();
				examplemac.createCriteria().andMernbrEqualTo(subMerNbr);
				List<Meracctinfo> Meracctinfos=MeracctinfoDAO.selectByExample(examplemac);
				transerDeptNbr=Meracctinfos.get(0).getMerdevelopdeptnbr();
			
			}else if(!StringUtil.isStringEmpty(userNbr)){
				MerthirdpartacctExample example=new MerthirdpartacctExample();
				example.createCriteria().andUsernbrEqualTo(userNbr);
				List<Merthirdpartacct> thirdAccts=MerthirdpartacctDAO.selectByExample(example);
				
				// 判断虚账户是否存在
				if (thirdAccts.isEmpty()) {
					throw new PeException(DictErrors.THIRD_PART_ACCT_NOT_EXIST);
				}
				thirdAcct=thirdAccts.get(0);
			}
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		
		log.info("网银发来的付款账户名称" + payerName);
		log.info("网银发来的收款账户名称" + payeeName);
		log.info("数据库查到的付款账户名称" + thirdAcct.getVirtualacctname());
		log.info("数据库查到的收款款账户名称" + thirdAcct.getAcctname());
		
		/*if(StringUtil.isStringEmpty(payerName)){
			throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.PAYER_ACCT_NAME});
		}
		if(!payerName.equals(payeeName) || !payerName.equals(thirdAcct.getVirtualacctname()) || !payerName.equals(thirdAcct.getAcctname())){
			throw new PeException(DictErrors.ACCT_NAME_IS_NOT_MATCH);
		}*/
		
		inputData.setDepartmentnbr(DepartmentNbr.OUR);
		inputData.setPayeracctnbr(thirdAcct.getVirtualacctnbr());
		inputData.setPayeracctname(thirdAcct.getVirtualacctname());
		inputData.setPayeracctkind(thirdAcct.getVirtualacctkind());
		inputData.setPayeraccttypcd(thirdAcct.getVirtualaccttype());
		inputData.setPayeeacctnbr(thirdAcct.getAcctnbr());
		inputData.setPayeeacctname(thirdAcct.getAcctname());
		inputData.setPayeeacctkind(thirdAcct.getAcctkind());
		inputData.setPayeeaccttypcd(thirdAcct.getAccttype());
		inputData.setPayeebanknbr(thirdAcct.getAcctbanknbr());
		SubTransData subTrans = this
				.makeSubTrans(ctx.getDataMap(), inputData);
		List<SubTransData> onlineSubTransList = new ArrayList<SubTransData>();
		onlineSubTransList.add(subTrans);
		inputData.setOnlineSubTransList(onlineSubTransList);
		inputData.setTransmode(subTrans.getTransmode());
		inputData.setTranserDeptNbr(transerDeptNbr);
	}

	@Override
	protected void prepareOnlineSubTrans(InputPaymentData transData,SubTransData subTrans)
			throws PeException{
		subTrans.setTransamt(transData.getTransamt());
		subTrans.setPayeeacctdeptnbr(transData.getPayeeacctdeptnbr());
		subTrans.setPayeeacctnbr(transData.getPayeeacctnbr());
		subTrans.setPayeeacctname(transData.getPayeeacctname());
		subTrans.setPayeecardtypcd(transData.getPayeecardtypcd());
		subTrans.setPayeeaccttypcd(transData.getPayeeaccttypcd());
		subTrans.setPayeeacctkind(transData.getPayeeacctkind());
		// 子交易商户流水号:提现没有上传二级商户流水号，所以这里用一级商户流水号
		subTrans.setMerseqnbr(transData.getMerseqnbr());
	}
}
