package com.csii.upp.encrypt.hsm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionMonitor extends Thread {
	private static final Log log = LogFactory.getLog(HsmSession.class);
	private static ShareHandle[] sHandle;
	private volatile boolean bStop = false;

	public void addHandle(ShareHandle[] aHandle) {
		sHandle = aHandle;
	}

	public void stopMonitor() {
		bStop = true;
	}

	private void testConnect(ShareHandle tHandle) {
		byte[] aBuffer = new byte[64];
		// aBuffer[1] = (byte)0x01;
		aBuffer[0] = (byte) 0x00;
		aBuffer[1] = (byte) 0x1a;
		System.arraycopy("01FFFFFFFF1234567800000000".getBytes(), 0, aBuffer,
				2, 26);

		if (tHandle.isUsable()) {
			tHandle.setUsed();
			try {
				// tHandle.write(aBuffer,3);
				tHandle.write(aBuffer, 28);
				tHandle.read(aBuffer, 64);
			} catch (Exception e) {
				tHandle.setFault();
				return;
			} catch (Error err) {
				tHandle.setFault();
				return;
			}
			tHandle.setNotused();
		}
	}

	public void run() {
		int iTest = 0;
		int i;

		while (!bStop) {
			// System.out.println("SessionMonitor::Check connection pool ! ");
			try {
				for (i = 0; i < sHandle.length; i++) {
					// System.out.println("SessionMonitor::Status of sHandle[" +
					// i + "] = " + sHandle[i].getStatus());
					if (sHandle[i].isFault()) {
						sHandle[i].releaseSocketHandle();
						sHandle[i].connect();
					}
				}
			} catch (Exception e) {
				log.error("SessionMonitor::run() - " + e.getMessage());
			} catch (Error err) {
				log.error("SessionMonitor::run() - " + err.getMessage());
			}

			try {
				sleep(HsmConst.MONI_INTERVAL);
			} catch (Exception e1) {
			}
			iTest++;
			if (iTest >= HsmConst.TEST_INTERVAL) {
				iTest = 0;
				for (i = 0; i < sHandle.length; i++) {
					testConnect(sHandle[i]);
				}
			}
		}
	}

}
