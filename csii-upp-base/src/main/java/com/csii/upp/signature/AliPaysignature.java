/**
 * 
 */
package com.csii.upp.signature;

import java.security.cert.X509Certificate;

import com.csii.pe.core.PeException;

/**
 * 由于支付宝签名验签特殊，这里需重写支付宝的签名验签
 */
public class AliPaysignature implements UppSignature {

	@Override
	public String sign(String plain) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verify(String plain, String signature,
			X509Certificate certificate) throws PeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String md5Sign(String plain) throws PeException {
		// TODO Auto-generated method stub
		return null;
	}


}
