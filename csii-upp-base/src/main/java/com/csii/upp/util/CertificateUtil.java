package com.csii.upp.util;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.upp.dict.DictErrors;

import sun.misc.BASE64Decoder;

public class CertificateUtil {
	private static Log log = LogFactory.getLog(CertificateUtil.class);
    public static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public static final String END_CERT = "-----END CERTIFICATE-----";
	public static X509Certificate generateCertificate(String merCert) throws PeException{
		if (!StringUtil.isStringEmpty(merCert)) {
			merCert=merCert.trim().replaceAll(BEGIN_CERT, "").replaceAll(END_CERT, "") ;
			try {
				BASE64Decoder decoder=new BASE64Decoder();
				byte[] byteCert=decoder.decodeBuffer(merCert);
				ByteArrayInputStream bain=new ByteArrayInputStream(byteCert);
				CertificateFactory cf= CertificateFactory.getInstance("X.509");
				return (X509Certificate) cf.generateCertificate(bain);
			} catch (Exception e) {
				log.error("商户证书格式错误",e);
				throw new PeException(DictErrors.MER_CERT_FORMAT_ERROR);
			}
		} else {
			throw new PeException(DictErrors.MER_CERT_NOT_EXIST);
		}
	}
}
