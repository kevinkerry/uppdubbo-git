package com.csii.upp.payment.action.mgmt;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.constant.BankOptionCd;
import com.csii.upp.constant.ResponseCode;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.constant.SignTypCd;
import com.csii.upp.dao.generate.BankoptionDAO;
import com.csii.upp.dao.generate.CusttransctrlDAO;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Custtransctrl;
import com.csii.upp.dto.generate.CusttransctrlExample;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.supportor.IDGenerateFactory;

/**
 * 丰收e注册
 * 
 * @author zgb
 *
 */
public class AddSignInfoAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		final InputPaymentData input = new InputPaymentData(context.getDataMap());
		final String tellerNo=context.getString(Dict.TELLER_NO);
		// 再次验证用户是否签约 直接查是否有注销状态下的
		final UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		try {
			// 判断该卡是否有签约关系
			// 根据卡号 支付方式 签约状态为注销的条件查询（强制注销情况）
			example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr())
					.andPaytypcdEqualTo(input.getPaytypcd()).andSignstatusEqualTo(SignStatus.CANCEL);
			List<Userpaytypsigninfo> userpaylist = UserpaytypsigninfoDAO.selectByExample(example);
			final String perDayMax = BankoptionDAO.selectByPrimaryKey(BankOptionCd.FSDL, 1L).getBankoptionvalue();
			final String perTransMax = BankoptionDAO.selectByPrimaryKey(BankOptionCd.FSTL, 1L).getBankoptionvalue();
			if (userpaylist.size() > 0) {
				// 强制注销情况下 下次注册改变本地数据库中手机号
				// 判断核心手机号与本地手机号是否匹配
				final Userpaytypsigninfo user = userpaylist.get(0);

				this.getTransactionTemplate().execute(new TransactionCallback() {
					@Override
					public Object doInTransaction(TransactionStatus arg0) {
						try {
							// 如果核心与本地不匹配 属于强制注销情况 再次注册改变状态与本地手机号为用户输入手机号
							user.setSignmobile(input.getPayerphoneno());
							user.setSignstatus(SignStatus.NORMA);
							user.setSigneffdate(getPostDate());
							user.setSignname(input.getPayeracctname());
							// 此时是否需要动限额表
							UserpaytypsigninfoDAO.updateByExampleSelective(user, example);

							Custtransctrl record = new Custtransctrl();
							record.setSigntypcd(user.getSigntypcd());
							record.setSignnbr(user.getSignnbr());
							record.setPertranslimit(new BigDecimal(perTransMax));
							record.setPerdaylimit(new BigDecimal(perDayMax)); // 根据签约编号
							
							CusttransctrlExample ctrlexample = new CusttransctrlExample();
							ctrlexample.createCriteria().andSignnbrEqualTo(user.getSignnbr())
									.andSigntypcdEqualTo(user.getSigntypcd());
							CusttransctrlDAO.updateByExampleSelective(record, ctrlexample);

						} catch (Exception e) {
							throw new PeRuntimeException(DictErrors.TRANS_EXCEPTION);
						}
						return null;
					}
				});

			} else {

				// 没有数据则插入签约表和限额控制表 采用事物
				this.getTransactionTemplate().execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus arg0) {
						try {
							Userpaytypsigninfo usert = new Userpaytypsigninfo();
							usert.setSignname(input.getPayeracctname());
							usert.setUsernbr(input.getCustcifnbr());
							usert.setSignstatus(SignStatus.NORMA);
							usert.setSigncardnbr(input.getPayeracctnbr());
							usert.setPaytypcd(input.getPaytypcd());
							usert.setSigndate(getPostDate());
							usert.setSigneffdate(getPostDate());
							usert.setSignmobile(input.getPayerphoneno());
							usert.setSigntypcd(SignTypCd.USER_PAY_TYP);
							usert.setSigndeptnbr(input.getPayeracctdeptnbr());
							usert.setTeller(tellerNo);
							usert.setSignnbr(IDGenerateFactory.generateSeqId());
							UserpaytypsigninfoDAO.insert(usert);
							// 插入限额控制表
							Custtransctrl custctrl = new Custtransctrl();
							custctrl.setSignnbr(usert.getSignnbr());
							custctrl.setSigntypcd(usert.getSigntypcd());
							custctrl.setPertranslimit(new BigDecimal(perTransMax));
							custctrl.setPerdaylimit(new BigDecimal(perDayMax));
							CusttransctrlDAO.insert(custctrl);
						} catch (Exception e) {
							throw new PeRuntimeException(DictErrors.TRANS_EXCEPTION);
						}
						return null;
					}

				});

			}
			context.setDataMap(context.getDataMap());
			context.setData(Dict.RESP_CODE, ResponseCode.SUCCESS);
			return;
		} catch (Exception e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}

	}
}
