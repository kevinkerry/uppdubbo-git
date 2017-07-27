package com.csii.upp.payment.action.syn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.service.comm.Transport;
import com.csii.upp.payment.action.PaymentOnlineAction;
import com.csii.upp.util.FileHandler;

public class FileSynSignInfoAction extends PaymentOnlineAction {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context) throws PeException {
		log.debug("文件同步");

		Map<String, Object> merResult = new HashMap<String, Object>();
		File file = new File(localPath);
		String[] filelist = file.list();
		if(filelist==null || filelist.length==0){
			return;
		}
		BufferedReader reader = null;
		for (int j = 0; j < filelist.length; j++) {
			File readfile = new File(FileHandler.checkPathEnd(localPath) + filelist[j]);
			FileHandler.createFile(sysSignHist, filelist[j]);
			copyFile(FileHandler.checkPathEnd(localPath) + filelist[j],
					FileHandler.checkPathEnd(sysSignHist) + filelist[j]);
			try {
				String lineSeparator = "\\|";
				String stringSeparator = "=";
				reader = new BufferedReader(new FileReader(readfile));
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					if (!tempString.startsWith("#")) {
						String[] stringArray = tempString.split(lineSeparator);
						for (int i = 0; i < stringArray.length; i++) {
							String[] array = stringArray[i].split(stringSeparator);
							merResult.put(array[0], array[1]);
						}
					}
				}

			} catch (IOException e) {
				log.error("同步读取文件失败" + e.getMessage());
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						log.error("读入流关闭错误" + e.getMessage());
					}
				}
			}

			Map resultMap = null;
			try {
				resultMap = (Map) this.getTransport().submit(merResult);
			} catch (Exception e) {
				log.error("同步1.0失败", e);
			}

			readfile.delete();
		}
	}

	private void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while((byteread=inStream.read(buffer))!=-1){
					bytesum += byteread;	//字节数，文件大小
					fs.write(buffer,0,byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} 

	}

	private String localPath;
	private Transport transport;
	private String sysSignHist;

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public String getSysSignHist() {
		return sysSignHist;
	}

	public void setSysSignHist(String sysSignHist) {
		this.sysSignHist = sysSignHist;
	}

}
