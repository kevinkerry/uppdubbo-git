package com.csii.upp.payment.action.common;

import java.security.cert.X509Certificate;
import java.sql.SQLException;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.upp.action.BaseAction;
import com.csii.upp.constant.PlatformStatus;
import com.csii.upp.dao.generate.MeracctinfoDAO;
import com.csii.upp.dao.generate.MerplatformDAO;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.dto.generate.Meracctinfo;
import com.csii.upp.dto.generate.Merplatform;
import com.csii.upp.signature.UppSignature;
import com.csii.upp.util.CertificateUtil;
import com.csii.upp.util.StringUtil;

/**
 * 验签交易处理
 * 
 * @author 徐锦
 * 
 */
public class SignatureValidateAction extends BaseAction {

	private UppSignature signature;

	@Override
	public void execute(Context context) throws PeException {
		String merPlatformNbr = context.getString(Dict.MER_PLATFORM_NBR);
		String merNbr = context.getString(Dict.MER_NBR);
		String plain = (String) context.getData(Dict.PLAIN);
		String signatureStr = (String) context.getData(Dict.SIGNATURE);
		if (!StringUtil.isStringEmpty(plain)) {
			if (StringUtil.isStringEmpty(signatureStr)) {
				throw new PeException(DictErrors.VALUE_NOT_EMPTY,new Object[]{Dict.SIGNATURE});
			}
		}
	
		if (StringUtil.isStringEmpty(signatureStr)) {
			return;
		}
		if (StringUtil.isStringEmpty(merPlatformNbr)) {
			Meracctinfo merAcct = null;
			try {
				merAcct = MeracctinfoDAO.selectByPrimaryKey(merNbr);
				if (merAcct == null) {
					throw new PeException(DictErrors.MERCHANT_NOT_EXIST);
				}
			} catch (SQLException e) {
				throw new PeException(DictErrors.TRANS_EXCEPTION);
			}
			merPlatformNbr = merAcct.getMerplatformnbr();
			context.setData(Dict.MER_PLATFORM_NBR, merPlatformNbr);
		}

		Merplatform merPlatform =null;
		try {
			merPlatform = MerplatformDAO.selectByPrimaryKey(merPlatformNbr);
		} catch (SQLException e) {
			throw new PeException(DictErrors.TRANS_EXCEPTION);
		}
		if (merPlatform == null||!PlatformStatus.OPEN.equals(merPlatform.getPlatformstatus())) {
			throw new PeException(DictErrors.MER_PLATFORM_ERROR);
		}
		if (StringUtil.isStringEmpty(merPlatform.getMercert())) {
			throw new PeException(DictErrors.MER_CERT_NOT_EXIST);
		}
		log.info("商户[" + merNbr + "]验签开始");
		X509Certificate certificate = CertificateUtil.generateCertificate(merPlatform.getMercert());

		log.info("支付的签名数据***********" + signatureStr);

		try {
			signature.verify(plain, signatureStr, certificate);
			log.info("商户[" + merNbr + "]验签成功");
		} catch (Exception ex) {
			log.error("商户[" + merNbr + "]验签异常", ex);
			throw (PeException) ex;
		}
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(UppSignature signature) {
		this.signature = signature;
	}
}