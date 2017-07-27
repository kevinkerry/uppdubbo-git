/**
 * 
 */
package com.csii.upp.signature;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.csii.pe.core.PeException;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.util.StringUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 用于一般情况下签名验签
 */
public class CommonSignature implements InitializingBean, UppSignature {

	private Log log = LogFactory.getLog(CommonSignature.class);

	public static final String KEYSTORE_TYPE = "PKCS12"; // 私钥类型
	private String keyPath = "F:\\app\\epay\\jks\\ecp_test.pfx"; // 私钥容器路径
	private String cardPwdAlias = "zjrcu"; // 私钥证书别名
	private static final String SHA1WITHRSA_ALGORITHM = "SHA1WithRSA"; // 安全哈希算法
	private static final String MD5WITHRSA_ALGORITHM = "MD5withRSA"; // MD5算法
	private String charset = "UTF-8";

	public void setCardPwdAlias(String cardPwdAlias) {
		this.cardPwdAlias = cardPwdAlias;
	}

	private PrivateKey privateKey; // 私钥

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	@Override
	public String sign(String plain) throws PeException {
		// TODO Auto-generated method stub
		try {
			byte[] signature = this.sign(privateKey, SHA1WITHRSA_ALGORITHM, plain.getBytes("utf-8"));
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(signature);
		} catch (Exception e) {
			log.error(e.getMessage() + e.getStackTrace() + e.toString());
			throw new PeException(DictErrors.SIGN_ERROR);
		}
	}

	@Override
	public void verify(String plain, String signature, X509Certificate certificate) throws PeException {
		// TODO Auto-generated method stub
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			if (!this.verify(certificate.getPublicKey(), SHA1WITHRSA_ALGORITHM, plain.getBytes(),
					decoder.decodeBuffer(signature))) {
				throw new PeException(DictErrors.VERIFY_ERROR);
			}
		} catch (Exception e) {
			throw new PeException(DictErrors.VERIFY_ERROR);
		}
	}

	private byte[] sign(PrivateKey privateKey, String signatureAlgorithm, byte[] dataPart)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature signature = Signature.getInstance(signatureAlgorithm);

		signature.initSign(privateKey);

		signature.update(dataPart);

		return signature.sign();
	}

	private boolean verify(PublicKey publicKey, String signatureAlgorithm, byte[] dataPart, byte[] signaturePart)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature signature = Signature.getInstance(signatureAlgorithm);

		signature.initVerify(publicKey);

		signature.update(dataPart);

		return signature.verify(signaturePart);
	}

	@Override
	public String md5Sign(String plain) throws PeException {
		try {
			return DigestUtils.md5Hex((plain).getBytes(charset));
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("加载私钥容器路径：" + keyPath);
		InputStream key = new FileInputStream(keyPath);
		KeyStore ks = KeyStore.getInstance(KEYSTORE_TYPE);
		log.info("私钥密码别名：" + cardPwdAlias);
		ks.load(key, cardPwdAlias.toCharArray());
		Enumeration<String> keyAlias = ks.aliases();
		while (keyAlias.hasMoreElements()) {
			String nextElement = keyAlias.nextElement();
			log.info("keyAlias.nextElement()*****************" + nextElement);
			boolean keyEntryFlag = ks.isKeyEntry(nextElement);
			log.info("keyAlias.nextElement().isKeyEntry*****************" + keyEntryFlag);
			if (keyEntryFlag) {
				privateKey = (PrivateKey) ks.getKey(nextElement, cardPwdAlias.toCharArray());
				break;
			}
		}
	}

	public static void main(String[] args) {
		String password="0923456781";
     	char[] keyArray=password.toCharArray();
     	String pwd ="N1,N3,N0,N4,N5,N6";
     	String[] pwdArray = pwd.split(",");
     	StringBuilder passwordSb = new StringBuilder();
		for (String s : pwdArray) {
			char c = keyArray[StringUtil.parseInteger(String.valueOf(s.charAt(1)))];
			passwordSb.append(c);
		}
		System.err.println(passwordSb.toString());
		CommonSignature sign = new CommonSignature();
		try {
			sign.afterPropertiesSet();
			String plain = "<Finance><Message><TransId>SMSR</TransId><MerchantId>201506110000000292</MerchantId><MerType>1A</MerType><SycOprtType>0</SycOprtType><MerStatus>0</MerStatus><MerCifNO>20331224386-1</MerCifNO><MerName>上海大誉国际物流有限公司</MerName><MerMgmtDeptId>13</MerMgmtDeptId><MerDevelopDeptId>807000</MerDevelopDeptId><MerServiceType>11</MerServiceType><MerSettAcctNO>201000143926491</MerSettAcctNO><MerSettAcctName>上海大誉国际物流有限公司</MerSettAcctName><MerSettAcctBankNo></MerSettAcctBankNo><MerFeeAcctNO>80700001511181000173</MerFeeAcctNO><MerFeeAcctName>合作商城手续费收入</MerFeeAcctName><MerSettMode>2</MerSettMode><MerSettPeriod>1</MerSettPeriod><MerFeeSettPeriod>1</MerFeeSettPeriod><MerFeeMode>1</MerFeeMode><MerFeeAmt>0</MerFeeAmt><MerFeeMinAmt>0</MerFeeMinAmt><MerFeeMaxAmt>0</MerFeeMaxAmt><MerFeeReturnFlag>1</MerFeeReturnFlag><MerOpenDate>20151027</MerOpenDate><MerOpenUser>奚海忠</MerOpenUser><MerActiveFlag>0</MerActiveFlag></Message></Finance>";
			System.err.print(sign.sign(plain));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.print(e.getMessage());
		}

	}

}
