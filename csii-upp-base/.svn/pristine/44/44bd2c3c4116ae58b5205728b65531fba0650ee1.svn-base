package com.csii.upp.encrypt;

import com.csii.pe.core.PeException;
import com.csii.pe.enter.CSIIPinConvertor;
import com.csii.upp.constant.ChannelNbr;
import com.csii.upp.dict.Dict;
import com.csii.upp.dict.DictErrors;
import com.csii.upp.encrypt.hsm.HsmApp;
import com.csii.upp.util.StringUtil;
import com.csii.vx.webpe.WebViewPEConvertor;

/**
 * 密码解密加密
 * 
 * @author xujin
 *
 */
public class CommonEncrypt implements Encryption {
	private boolean pinSwitch;

	public void setPinSwitch(boolean pinSwitch) {
		this.pinSwitch = pinSwitch;
	}

	private HsmApp hsmApp;

	public void setHsmApp(HsmApp hsmApp) {
		this.hsmApp = hsmApp;
	}

	private CSIIPinConvertor pcPinConvertor;
	private WebViewPEConvertor wapPinConvert;

	public void setPcPinConvertor(CSIIPinConvertor pcPinConvertor) {
		this.pcPinConvertor = pcPinConvertor;
	}

	public void setWapPinConvert(WebViewPEConvertor wapPinConvert) {
		this.wapPinConvert = wapPinConvert;
	}

	@Override
	public String encrypt(String password, String channelNbr) throws PeException {
		if (StringUtil.isStringEmpty(password)) {
			throw new PeException(DictErrors.VALUE_NOT_EMPTY, new Object[] { Dict.PASSWORD });
		}
		if (pinSwitch) {
			// 调用密码控件解密
			if(ChannelNbr.PC.equals(channelNbr)){
				password = this.convertPCPassword(password);
			}else if(ChannelNbr.WAP.equals(channelNbr)){
				password = this.convertWapPassword(password);
			}
			byte[] pinblock = new byte[24];

			byte[] pin = new byte[12];

			System.arraycopy(password.getBytes(), 0, pin, 0, 6);

			int nRet = hsmApp.encryptPIN(pin, pinblock);

			if (nRet != 0) {
				// 加密机异常
				throw new PeException(DictErrors.ENCRYPT_PIN_FAILED);
			}
			// 提供接口在处理
			return StringUtil.byteToHex(pinblock);
		}
		return password;
	}

	/**
	 * 调用PC端密码控件解密
	 * 
	 * @param password
	 * @return
	 * @throws PeException
	 */
	public String convertPCPassword(String password) throws PeException {
		if (pinSwitch) {
			// 调用密码控件解密
			try {
				return pcPinConvertor.convert(password);
			} catch (Exception ex) {
				String msg = ex.getMessage();
				if ("uibs.security_input_timeout.".equals(msg)) {
					throw new PeException(DictErrors.PIN_INPUT_TIMEOUT);
				}
				if ("uibs.security_input_invalid_format.".equals(msg)) {
					throw new PeException(DictErrors.PIN_FORMAT_INVALID);
				}
				throw new PeException(DictErrors.ENCRYPT_PIN_FAILED);
			}
		}
		return password;
	}

	/**
	 * 调用手机WAP端密码控件解密
	 * 
	 * @param password
	 * @return
	 * @throws PeException
	 */
	private String convertWapPassword(String password) throws PeException {
		try {
			StringBuilder passwordSb = new StringBuilder();
			char[] keyArray = password.substring(0, 10).toCharArray();
			String pwd = password.substring(10, password.length());
			pwd = wapPinConvert.convert(pwd);
			String[] pwdArray = pwd.split(",");
			for (String s : pwdArray) {
				char c = keyArray[StringUtil.parseInteger(String.valueOf(s.charAt(1)))];
				passwordSb.append(c);
			}
			return passwordSb.toString();
		} catch (Exception ex) {
			throw new PeException(DictErrors.PIN_DIGIT_ERROR);
		}
	}

}
