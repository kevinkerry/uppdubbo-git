package com.csii.upp.encrypt.hsm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HsmSession {
	private static final Log logger = LogFactory.getLog(HsmSession.class);
	private SessionMonitor sessionMonitor;

	private ShareHandle[] sHandles;
	private int sPreIndex = -1;

	private int iLastErr = -1;
	private int sBalance;
	private String[] sIPs;
	private int sPort;
	private int sTimeOut;

	public int getiLastErr() {
		return iLastErr;
	}

	public void setiLastErr(int iLastErr) {
		this.iLastErr = iLastErr;
	}

	public int getsBalance() {
		return sBalance;
	}

	public void setsBalance(int sBalance) {
		this.sBalance = sBalance;
	}

	public String[] getsIPs() {
		return sIPs;
	}

	public void setsIPs(String[] sIPs) {
		this.sIPs = sIPs;
	}

	public int getsPort() {
		return sPort;
	}

	public void setsPort(int sPort) {
		this.sPort = sPort;
	}

	public int getsTimeOut() {
		return sTimeOut;
	}

	public void setsTimeOut(int sTimeOut) {
		this.sTimeOut = sTimeOut;
	}

	public void setSessionMonitor(SessionMonitor sessionMonitor) {
		this.sessionMonitor = sessionMonitor;
	}

	// 创建连接
	public void init() {
		iLastErr = 0;

		// 初始化加密机连接
		int i, j, nError;
		nError = 0;

		// 1、判断是否已经初始化
		if (sHandles != null)
			return;

		// 3、初始化连接池，每加密机依次建立一个连接，以实现负载均衡
		ShareHandle[] tHandle = new ShareHandle[this.sIPs.length * sBalance];
		for (i = 0; i < sBalance; i++) {
			for (j = 0; j < this.sIPs.length; j++) {
				tHandle[i * this.sIPs.length + j] = new ShareHandle(sIPs[j],
						sPort, sTimeOut);
				if (tHandle[i * this.sIPs.length + j].isFault())
					nError++;
			}
		}

		if (nError == this.sIPs.length * sBalance) {
			// 无法与加密机建立连接。
			iLastErr = HsmConst.ERR_CONNECT_HSM;
			logger.error(this.parseErrCode(iLastErr));
		} else {

			// 4、启动连接监控线程
			sHandles = tHandle;
			this.sessionMonitor.addHandle(sHandles);
			this.sessionMonitor.start();
		}
	}

	public int getSessionID() {
		int iHandleID = -1;
		if (HsmConst.ERR_CONNECT_HSM == iLastErr) {
			this.init();
			if (HsmConst.ERR_CONNECT_HSM == iLastErr) {
				return iHandleID;
			}
		}
		// 获取可用连接
		for (int loop = 0; loop < (sTimeOut / 20); loop++) {
			iHandleID = getSession();
			if (iHandleID != -1)
				break;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				break;
			}
		}
		return iHandleID;
	}

	private synchronized int getSession() {
		int i;
		int iHandleID = -1;
		ShareHandle tHandle;
		int tNumOfSession = this.sIPs.length * sBalance;
		for (i = sPreIndex + 1; i < tNumOfSession; i++) {
			tHandle = sHandles[i];
			if (tHandle.isUsable()) {
				tHandle.setUsed();
				sPreIndex = i;
				iHandleID = i;
				break;
			}
		}

		if (i == tNumOfSession) {
			for (i = 0; i <= sPreIndex; i++) {
				tHandle = sHandles[i];
				if (tHandle.isUsable()) {
					tHandle.setUsed();
					sPreIndex = i;
					iHandleID = i;
					break;
				}
			}
		}

		return iHandleID;
	}

	public String parseErrCode(int nErrCode) {
		String sMeaning;
		switch (nErrCode) {
		case 0:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":操作正确,状态正常";
			break;
		case HsmConst.ERR_CONFIG_FILE:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":配置文件异常";
			break;
		case HsmConst.ERR_CONNECT_HSM:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":连接密码机失败";
			break;
		case HsmConst.ERR_SENDTO_HSM:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":发送数据至密码机失败";
			break;
		case HsmConst.ERR_RECVFORM_HSM:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":接收密码机数据失败";
			break;
		case HsmConst.ERR_SESSION_END:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":连接已关闭";
			break;
		case HsmConst.ERR_HANDLE_FAULT:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":连接句柄状态异常";
			break;
		default:
			sMeaning = "0x" + Integer.toHexString(nErrCode) + ":异常操作,检查密码机日志";
			break;
		}
		return sMeaning;
	}

	// 发送数据到加密机,正确返回0,失败返回错误码
	public int sendData(int iHandleID, byte[] byteOut, int nLength) {
		ShareHandle tHandle = sHandles[iHandleID];

		if (tHandle.isFault()) {
			return HsmConst.ERR_HANDLE_FAULT;
		}

		try {
			tHandle.write(byteOut, nLength);
		} catch (Exception e) {
			tHandle.setFault();
			logger.error("HsmSession::SendData() - " + e.getMessage());
			return HsmConst.ERR_SENDTO_HSM;
		} catch (Error err) {
			tHandle.setFault();
			logger.error("HsmSession::SendData() - " + err.getMessage());
			return HsmConst.ERR_SENDTO_HSM;
		}

		return HsmConst.T_SUCCESS;
	}

	// 从加密机接收数据,正确返回收到的字节数,否则返回-1
	public int recvData(int iHandleID, byte[] byteIn) {
		ShareHandle tHandle = sHandles[iHandleID];
		int rcvLen;
		if (tHandle.isFault()) {
			return -1;
		}

		try {
			rcvLen = tHandle.read(byteIn, HsmConst.SECBUF_MAX_SIZE);
		} catch (Exception e) {
			tHandle.setFault();
			logger.error("HsmSession::RecvData() - " + e.getMessage());
			return -1;
		} catch (Error err) {
			tHandle.setFault();
			logger.error("HsmSession::RecvData() - " + err.getMessage());
			return -1;
		}

		if (rcvLen > 0) {
			return rcvLen;
		} else {
			tHandle.setFault();
			return -1;
		}
	}

	public void releaseSession(int iHandleID) {
		if (iHandleID >= 0) {
			ShareHandle tHandle = sHandles[iHandleID];
			if (tHandle.isUsed()) {
				tHandle.setNotused();
			}
		}
		return;
	}

	public synchronized void clearAllSessions() {
		int i;
		int iTotalSessions = this.sIPs.length * sBalance;

		if (sHandles == null)
			return;

		this.sessionMonitor.stopMonitor();
		for (i = 0; i < iTotalSessions; i++) {
			sHandles[i].releaseSocketHandle();
		}

		sHandles = null;
		return;
	}
}

class ShareHandle {
	final int FLAG_NOTUSE = 0;
	final int FLAG_USED = 1;
	final int FLAG_FAULT = 2;

	private Socket iSocketHandle = null;

	private int iStatus;

	private InputStream iInputStream = null;
	private OutputStream iOutputStream = null;

	private String iIP = null;
	private int iPort = -1;
	private int iTimeout = -1;

	/**
	 * @param aString
	 * @param aPort
	 * @param aTimeOut
	 */
	public ShareHandle(String aIP, int aPort, int aTimeout) {
		iIP = aIP;
		iPort = aPort;
		iTimeout = aTimeout;
		connect();
	}

	public void connect() {
		try {
			iSocketHandle = new Socket();
			InetSocketAddress hsmAddr = new InetSocketAddress(iIP, iPort);
			// System.out.println("IP: "+iIP+" Port: "+iPort);
			iSocketHandle.connect(hsmAddr, iTimeout);
			iSocketHandle.setSoTimeout(iTimeout);
			iSocketHandle.setKeepAlive(true);
			iSocketHandle.setReceiveBufferSize(2048);
			iSocketHandle.setTcpNoDelay(true);
			iInputStream = iSocketHandle.getInputStream();
			iOutputStream = iSocketHandle.getOutputStream();
			setNotused();
		} catch (IOException e) {
			releaseSocketHandle();
		}
	}

	public void releaseSocketHandle() {
		setFault();

		if (iInputStream != null) {
			try {
				iInputStream.close();
			} catch (Exception e) {
			}
			iInputStream = null;
		}

		if (iOutputStream != null) {
			try {
				iOutputStream.close();
			} catch (Exception e) {
			}
			iOutputStream = null;
		}

		if (iSocketHandle != null) {
			try {
				iSocketHandle.close();
			} catch (Exception e) {
			}
			iSocketHandle = null;
		}
	}

	public void setUsed() {
		iStatus = FLAG_USED;
	}

	public void setNotused() {
		iStatus = FLAG_NOTUSE;
	}

	public void setFault() {
		iStatus = FLAG_FAULT;
	}

	public int getStatus() {
		return iStatus;
	}

	public boolean isUsed() {
		return (iStatus == FLAG_USED);
	}

	public boolean isUsable() {
		return (iStatus == FLAG_NOTUSE);
	}

	public boolean isFault() {
		return (iStatus == FLAG_FAULT);
	}

	/**
	 * @param aByteOut
	 * @param aI
	 * @param aLength
	 * @throws IOException
	 */
	public void write(byte[] aByteOut, int aLength) throws IOException {
		iOutputStream.write(aByteOut, 0, aLength);
		iOutputStream.flush();
	}

	/**
	 * @param aByteIn
	 * @param aI
	 * @throws IOException
	 */
	public int read(byte[] aByteIn, int aBufferSize) throws IOException {
		return iInputStream.read(aByteIn, 0, aBufferSize);
	}
}
