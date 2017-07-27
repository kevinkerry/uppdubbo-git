package com.csii.upp.payment.action.mgmt;

import java.util.Date;
import java.util.List;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.constant.SignStatus;
import com.csii.upp.dao.generate.UserpaytypsigninfoDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.extend.InputPaymentData;
import com.csii.upp.dto.generate.Userpaytypsigninfo;
import com.csii.upp.dto.generate.UserpaytypsigninfoExample;
import com.csii.upp.dto.router.fundprocess.RespQueryCardInfo;
import com.csii.upp.payment.action.PaymentOnlineAction;

/**
 * 丰收e注册
 * 
 * @author zgb
 *
 */
public class ConfirmCancelSignInfoAction extends PaymentOnlineAction {

	@Override
	public void execute(Context context) throws PeException {
		InputPaymentData input = new InputPaymentData(context.getDataMap());

		// 判断卡类型 是否判断本行或其他行卡 卡宾已配置
		queryCardType(input);

		// 卡查询
		RespQueryCardInfo output = (RespQueryCardInfo) this.getFDPSService()
				.queryCardInfo(input);
		context.setData(Dict.PAYER_CARD_TYP_CD, input.getPayercardtypcd());
		context.setData(Dict.CUST_CIF_NBR, output.getCustCifNbr()); // 客户号
		context.setData(Dict.PAYER_PHONE_NO, input.getPayerphoneno());// 手机号
		context.setData(Dict.PAYER_ACCT_NBR, output.getPayerAcctNbr());// 此处拿核心返回的付款帐号
		context.setData(Dict.PAYER_ACCT_DEPT_NBR, output.getPayerAcctDeptNbr());
		context.setData(Dict.PAYER_ACCT_NAME, output.getPayerAcctName());
		context.setData(Dict.ACCT_NATURE, output.getAcctNature());
		context.setData(Dict.PAYER_ACCT_STATUS,output.getPayerAcctStatus());
		context.setData(Dict.ACCT_LOSS_STATUS,output.getAcctLossStatus());
		context.setData(Dict.ACCT_STATUS_WORD,output.getAcctStatusWord());
		context.setData(Dict.CARD_STATUS_WORD,output.getCardStatusWord());
		context.setData(Dict.CARD_STATUS,output.getCardStatus());
		// 查本地数据库 判断签约状态
		UserpaytypsigninfoExample example = new UserpaytypsigninfoExample();
		// 使用付款帐号和支付方式查询
		example.createCriteria().andSigncardnbrEqualTo(input.getPayeracctnbr())
				.andPaytypcdEqualTo(input.getPaytypcd())
				.andSignstatusNotEqualTo(SignStatus.CANCEL);
		List<Userpaytypsigninfo> userlist = null;
		try {
			userlist = UserpaytypsigninfoDAO.selectByExample(example);
		} catch (Exception e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (null == userlist || userlist.size() == 0) {
			// 无记录 可注册
			return;
		}
		if (userlist.size() > 1) {
			throw new PeException(DictErrors.DATA_ERROR);
		}
		// 保证一条记录
		Userpaytypsigninfo user = userlist.get(0);

		// 再判断核心数据与本地数据 此时判断本地与核心关系 进行强制注销
		if (!output.getPayerPhoneNoList().contains(user.getSignmobile())) {
			// 强制注销本地关系 此处是核心手机号和本地手机号不匹配 
			// 如果是非注销状态改变签约状态为注销 到下一步注册 修改手机号和注销状态
			// 此处强制注销不改变手机号 确定注册后修改本地签约手机号为核心与用户输入匹配的手机号
			try {
				cancelSignstatus(user, example);
				// 注销完成不执行以下部分
			} catch (Exception e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
		}
		// 如果核心与本地数据库匹配，且用户输入与本地匹配 还得查看是否是注销状态
		else if (input.getPayerphoneno().equals(user.getSignmobile())) {
			if (SignStatus.FROZEN.equals(user.getSignstatus())) {
				// 冻结状态，不可注册
				throw new PeException(DictErrors.SIGN_STATUS_ERROR,
						new Object[] { Dict.FROZEN });
			} else {
				// 签约状态已存在
				throw new PeException(DictErrors.CONT_RELA_ALREADY_EXISTS);
			}
		} else {
			// 回显到页面 进行选择强制注销
			context.setData(Dict.LOCAL_MOBILE_PHONE, user.getSignmobile());
			throw new PeException(DictErrors.SIGN_AGAIN_ERROR);
		}
	}

	/**
	 * 强制注销本地签约状态
	 * 
	 * @param user
	 * @param example
	 * @throws Exception
	 */
	public void cancelSignstatus(Userpaytypsigninfo user,
			UserpaytypsigninfoExample example) throws Exception {
		user.setSignstatus(SignStatus.CANCEL);
		user.setSigninactivedate(new Date());
		UserpaytypsigninfoDAO.updateByExampleSelective(user, example);

	}
}
/**
 * 1.核心 本地 输入 if（核心！=本地）
 * 
 * 
 * 
 * 
 */
