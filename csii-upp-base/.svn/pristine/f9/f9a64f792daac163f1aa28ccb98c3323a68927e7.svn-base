package com.csii.upp.encrypt.hsm;



public class HsmApp {
	private HsmSession hSession ;

    public HsmSession gethSession() {
		return hSession;
	}

	public void sethSession(HsmSession hSession) {
		this.hSession = hSession;
	}

	// 常用函数：数据打印：HEX格式
    static public void outputDataHex(String sInfo, byte[] byteIn, int nDataLen) {
        int i, j, n, prev;

        System.out.println("[" + sInfo + "]" + "length" + "[" + nDataLen + "]");
        prev = n = 0;
        for (i = 0; i < nDataLen; i++) {
            if (i == (prev + 16)) {
                System.out.print("    ;");
                for (j = prev; j < prev + 16; j++) {
                    if (Character.isLetter((char) (byteIn[j] & 0xff)) == true) {
                        System.out.print((char) byteIn[j]);
                    }
                    else {
                        System.out.print(" ");
                    }
                }
                prev += 16;
                System.out.println();
            }
            if (Integer.toHexString(byteIn[i] & 0xff).length() == 1) {
                System.out.print("0" + Integer.toHexString(byteIn[i] & 0xff) + " ");
            }
            else {
                System.out.print(Integer.toHexString(byteIn[i] & 0xff) + " ");
            }
        }
        if (i != prev) {
            n = i;
            for (; i < prev + 16; i++) {
                System.out.print("   ");
            }
        }
        System.out.print("    ;");
        for (i = prev; i < n; i++) {
            if (Character.isLetter((char) byteIn[i]) == true) {
                System.out.print((char) byteIn[i]);
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static public boolean str2Hex(byte[] in, byte[] out, int len) {
        byte[] asciiCode = { 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };

        if (len > in.length) {
            return false;
        }

        if (len % 2 != 0) {
            return false;
        }

        byte[] temp = new byte[len];

        for (int i = 0; i < len; i++) {
            if (in[i] >= 0x30 && in[i] <= 0x39) {
                temp[i] = (byte) (in[i] - 0x30);
            }
            else if (in[i] >= 0x41 && in[i] <= 0x46) {
                temp[i] = asciiCode[in[i] - 0x41];
            }
            else if (in[i] >= 0x61 && in[i] <= 0x66) {
                temp[i] = asciiCode[in[i] - 0x61];
            }
            else {
                return false;
            }
        }

        for (int i = 0; i < len / 2; i++) {
            out[i] = (byte) (temp[2 * i] * 16 + temp[2 * i + 1]);
        }

        return true;
    }

    static public boolean hex2Str(byte[] in, byte[] out, int len) {
        byte[] asciiCode = { 0x41, 0x42, 0x43, 0x44, 0x45, 0x46 };

        if (len > in.length) {
            return false;
        }

        byte[] temp = new byte[2 * len];

        for (int i = 0; i < len; i++) {
            temp[2 * i] = (byte) ((in[i] & 0xf0) / 16);
            temp[2 * i + 1] = (byte) (in[i] & 0x0f);
        }

        for (int i = 0; i < 2 * len; i++) {
            if (temp[i] <= 9 && temp[i] >= 0) {
                out[i] = (byte) (temp[i] + 0x30);
            }
            else {
                out[i] = asciiCode[temp[i] - 0x0a];
            }
        }

        return true;
    }

    public static String byte2hex(byte[] b) { // 二行制转字符
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;

            }
            if (n < b.length - 1) {
                hs = hs + " ";
            }
        }

        return hs.toUpperCase(); 
        
        
    }


	private int HsmLink(byte[] bSecBufferIn, int iSndLen, byte[] bSecBufferOut)
	{
		int iSessionID, nResult;

		//Get a session
		iSessionID = hSession.getSessionID();
		if(iSessionID == -1){
			return HsmConst.ERR_HANDLE_FAULT;
		}
		nResult = hSession.sendData(iSessionID,bSecBufferIn, iSndLen);
		if (nResult != HsmConst.T_SUCCESS) {
			return nResult;
		}
		//receive message
		nResult = hSession.recvData(iSessionID,bSecBufferOut);
		//OutputDataHex("Data Receive From HSM:",bSecBufferOut,nResult);		
		if(nResult < 0) {
			return HsmConst.ERR_RECVFORM_HSM;
		}
		
		hSession.releaseSession(iSessionID);
		if (bSecBufferOut[2] != 'A') {
			return (bSecBufferOut[3] & 0xFF);
		}
		//System.out.println("Realse session ID = " + iSessionID);
		return HsmConst.T_SUCCESS;
	}
	
	//转加密PIN
	public int convertPIN(byte[] pinIn, byte[] pinOut)
	{
		byte[] bSecBufferIn  = new byte[HsmConst.SECBUF_MAX_SIZE];
		byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

		int nResult, iSndLen;
		
		bSecBufferIn[0] = (byte)0x00;
		bSecBufferIn[1] = (byte)26;
		bSecBufferIn[2] = (byte)'0';
		bSecBufferIn[3] = (byte)'1';
 	
		System.arraycopy(pinIn, 0, bSecBufferIn, 4, 24);
		iSndLen = 2 + 2 + 24;

		nResult = HsmLink(bSecBufferIn,iSndLen,bSecBufferOut);		
		if (nResult != HsmConst.T_SUCCESS) {
			return nResult;
		}

		System.arraycopy(bSecBufferOut, 3, pinOut, 0, 24);

		return HsmConst.T_SUCCESS;
	}

	//加密明文PIN
	public int encryptPIN(byte[] strPin, byte[] pinOut)
	{
		byte[] bSecBufferIn  = new byte[HsmConst.SECBUF_MAX_SIZE];
		byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

		int nResult, iSndLen;
		int i, pinlen;
		
		for(i = 0; i <= 12; i++)
			if(strPin[i] < '0' || strPin[i] > '9')
				break;
		if(i < 4 || i == 12)
			return HsmConst.EPIN_LENGTH;
		pinlen = i;

		bSecBufferIn[0] = (byte)0x00;
		bSecBufferIn[1] = (byte)(pinlen + 4);
		bSecBufferIn[2] = (byte)'0';
		bSecBufferIn[3] = (byte)'2';
		bSecBufferIn[4] = (byte)0;
		bSecBufferIn[5] = (byte)pinlen;
 	
		System.arraycopy(strPin, 0, bSecBufferIn, 6, pinlen);
		iSndLen = 2 + 2 + 2 + pinlen;

		nResult = HsmLink(bSecBufferIn,iSndLen,bSecBufferOut);		
		if (nResult != HsmConst.T_SUCCESS) {
			return nResult;
		}

		System.arraycopy(bSecBufferOut, 3, pinOut, 0, 24);

		return HsmConst.T_SUCCESS;
	}

	//计算MAC
	public int generateMAC(int dataLen, byte[] data, byte[] mac)
	{
		byte[] bSecBufferIn  = new byte[HsmConst.SECBUF_MAX_SIZE];
		byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

		int nResult, iSndLen;

		if(dataLen > HsmConst.SECBUF_MAX_SIZE - 4)
			return HsmConst.EMES_TOO_LONG;

		bSecBufferIn[0] = (byte)((dataLen + 4) >> 8 & 0xFF);
		bSecBufferIn[1] = (byte)((dataLen + 4) & 0xFF);

		bSecBufferIn[2] = (byte)'0';
		bSecBufferIn[3] = (byte)'3';

		bSecBufferIn[4] = (byte)(dataLen >> 8 & 0xFF);
		bSecBufferIn[5] = (byte)(dataLen & 0xFF);
			
		System.arraycopy(data, 0, bSecBufferIn, 6, dataLen);
		iSndLen = 6 + dataLen;

		nResult = HsmLink(bSecBufferIn,iSndLen,bSecBufferOut);		
		if (nResult != HsmConst.T_SUCCESS) {
			return nResult;
		}

		System.arraycopy(bSecBufferOut, 3, mac, 0, 16);

		return HsmConst.T_SUCCESS;
	}
	
	//加密网银密码
	public byte[] encryptPassword(String strAcco, String strPassword)
	{
		byte[] bSecBufferIn  = new byte[HsmConst.SECBUF_MAX_SIZE];
		byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

		int nResult, iSndLen;
		int accLen = strAcco.length();
		int pwdLen = strPassword.length();

		//bSecBufferIn[0] = (byte)((accLen + pwdLen + 3) >> 8 & 0xFF);
		//bSecBufferIn[1] = (byte)((accLen + pwdLen + 3) & 0xFF);
		bSecBufferIn[0] = (byte)((accLen + pwdLen + 4) >> 8 & 0xFF);
		bSecBufferIn[1] = (byte)((accLen + pwdLen + 4) & 0xFF);

		bSecBufferIn[2] = (byte)'0';
		bSecBufferIn[3] = (byte)'4';

		bSecBufferIn[4] = (byte)(accLen & 0xFF);
		System.arraycopy(strAcco.getBytes(), 0, bSecBufferIn, 5, accLen);
		bSecBufferIn[5+accLen] = (byte)(pwdLen & 0xFF);
		System.arraycopy(strPassword.getBytes(), 0, bSecBufferIn, 6 + accLen, pwdLen);
		
		iSndLen = accLen + pwdLen + 6;

		nResult = HsmLink(bSecBufferIn,iSndLen,bSecBufferOut);		
		if (nResult == HsmConst.T_SUCCESS) {
			byte[] cipherPwd=new byte[bSecBufferOut[1] - 1];
			System.arraycopy(bSecBufferOut, 3, cipherPwd, 0, bSecBufferOut[1] - 1);
			return cipherPwd;
		}
		return null;
	}

	//校验网银密码
	public int verifyPassword(String strAcco, String strPassword, String strCipherPwd)
	{
		byte[] bSecBufferIn  = new byte[HsmConst.SECBUF_MAX_SIZE];
		byte[] bSecBufferOut = new byte[HsmConst.SECBUF_MAX_SIZE];

		int nResult, iSndLen;
		int accLen = strAcco.length();
		int pwdLen = strPassword.length();
		int ciphLen = strCipherPwd.length();

		//bSecBufferIn[0] = (byte)((accLen + pwdLen + ciphLen + 4) >> 8 & 0xFF);
		//bSecBufferIn[1] = (byte)((accLen + pwdLen + ciphLen + 4) & 0xFF);
		bSecBufferIn[0] = (byte)((accLen + pwdLen + ciphLen + 5) >> 8 & 0xFF);
		bSecBufferIn[1] = (byte)((accLen + pwdLen + ciphLen + 5) & 0xFF);

		bSecBufferIn[2] = (byte)'0';
		bSecBufferIn[3] = (byte)'5';
		
		bSecBufferIn[4] = (byte)(accLen & 0xFF);
		System.arraycopy(strAcco.getBytes(), 0, bSecBufferIn, 5, accLen);
		bSecBufferIn[5+accLen] = (byte)(pwdLen & 0xFF);
		System.arraycopy(strPassword.getBytes(), 0, bSecBufferIn, 6 + accLen, pwdLen);
		bSecBufferIn[6+accLen+pwdLen] = (byte)(ciphLen & 0xFF);
		System.arraycopy(strCipherPwd.getBytes(), 0, bSecBufferIn, 7 + accLen + pwdLen, ciphLen);
		
		iSndLen = accLen + pwdLen + ciphLen + 7;

		nResult = HsmLink(bSecBufferIn,iSndLen,bSecBufferOut);		
		return nResult;
	}
	
}
