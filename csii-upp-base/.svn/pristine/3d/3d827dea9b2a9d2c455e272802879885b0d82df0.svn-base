/**
 * 
 */
package com.csii.upp.signature;

import java.security.cert.X509Certificate;

import com.csii.pe.core.PeException;

/**
 * 用于签名验签接口
 */
public interface UppSignature {
	public abstract String md5Sign(String plain) throws PeException;
	public abstract String sign(String plain) throws PeException;
	public abstract void verify(String plain,String signature,X509Certificate certificate) throws PeException;
}
