package com.csii.upp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.csii.upp.constant.DateFormatCode;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {
	private static Log log = LogFactory.getLog(FtpUtil.class);

	/**
	 * 执行文件上传
	 * 
	 * @param fileName
	 *            上传到ftp服务器的文件名，带后缀
	 * @param localFilePath
	 *            待上传的本地文件的路径
	 * @param config
	 *            上传相关的配置信息
	 * 
	 * @return true-上传成功；false-上传失败
	 */
	public static boolean uploadFile(String fileName, String localFilePath,
			FtpConfig config) {
		if (config == null || config.isTesting()) {
			return false;
		}

		boolean result = false;

		if ("sftp".equals(config.getMode())) {
			ChannelSftp sftp = new ChannelSftp();
			try {
				JSch jsch = new JSch();
				Session sshSession = jsch.getSession(config.getUserName(),
						config.getHost(), config.getPort());
				sshSession.setPassword(config.getPassword());
				Properties sshConfig = new Properties();
				sshConfig.put("StrictHostKeyChecking", "no");
				sshSession.setConfig(sshConfig);
				sshSession.connect();
				Channel channel = sshSession.openChannel("sftp");
				channel.connect();
				sftp = (ChannelSftp) channel;

				try{
					sftp.cd(config.getWorkingDirectory());
				}catch(SftpException sftpe){
					sftp.mkdir(config.getWorkingDirectory());
					sftp.cd(config.getWorkingDirectory());
				}
				File file = new File(localFilePath + fileName);
				sftp.put(new FileInputStream(file), file.getName());
				result = true;
			} catch (Exception e) {
				log.error("文件上传失败：",e);
			} finally {
				if (sftp.isConnected()) {
					try {
						sftp.disconnect();
						sftp.getSession().disconnect();
						sftp.exit();
					} catch (JSchException e) {
						log.error(e);
					}
				}
			}

		} else {
			FTPClient ftp = new FTPClient();
			ftp.setConnectTimeout(config.getConnectTimeout());
			try {
				ftp.connect(config.getHost(), config.getPort());
				ftp.login(config.getUserName(), config.getPassword());
				int reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return result;
				}

				// 进入指定目录，若目录不存在则创建，若创建不成功则直接退出
				String workingDirectory = config.getWorkingDirectory();
				if (!ftp.changeWorkingDirectory(workingDirectory)) {
					if (ftp.makeDirectory(workingDirectory)) {
						ftp.changeWorkingDirectory(workingDirectory);
					} else {
						return result;
					}
				}

				// 执行文件上传
				FileInputStream fs = null;
				try {
					fs = new FileInputStream(new File(localFilePath + fileName));
					if (ftp.storeFile(fileName, fs)) {
						result = true;
					}
				} finally {
					if (fs != null) {
						fs.close();
					}
				}
			} catch (IOException e) {
				log.error("文件上传失败", e);
			} finally {
				try {
					ftp.disconnect();
				} catch (IOException ex) {
					log.error(ex);
				}
			}
		}
		return result;
	}

	/**
	 * 执行文件下载
	 * 
	 * @param fileName
	 *            服务器端文件名
	 * @param localFilePath
	 *            本地文件保存路径
	 *@param date
	 *            下载日期
	 * @param config
	 *            相关配置信息
	 * @return
	 */
	public static boolean downloadFile(String fileName, String localFilePath,Date date,
			FtpConfig config) {
		if (config == null || config.isTesting()) {
			return false;
		}

		boolean result = false;

		if ("sftp".equals(config.getMode())) {
			ChannelSftp sftp = new ChannelSftp();
			try {
				JSch jsch = new JSch();
				Session sshSession = jsch.getSession(config.getUserName(),
						config.getHost(), config.getPort());
				sshSession.setPassword(config.getPassword());
				Properties sshConfig = new Properties();
				sshConfig.put("StrictHostKeyChecking", "no");
				sshSession.setConfig(sshConfig);
				sshSession.connect();
				Channel channel = sshSession.openChannel("sftp");
				channel.connect();
				sftp = (ChannelSftp) channel;

				try{
					String dir=config.getWorkingDirectory();
					if(dir.matches("$date"))
					{
						dir.replace("$date", DateUtil.Date_To_DateTimeFormat(DateUtil.getCurrentDateTime(),
								DateFormatCode.DATE_FORMATTER3));
					}
					sftp.cd(dir);
				}catch(SftpException sftpe){
					sftp.mkdir(config.getWorkingDirectory());
					sftp.cd(config.getWorkingDirectory());
				}
				File file = new File(localFilePath + fileName);
				sftp.get(fileName, new FileOutputStream(file));
				result = true;
			} catch (Exception e) {
				log.error("downloadFile error:"+e);
			} finally {
				if (sftp.isConnected()) {
					try {
						sftp.disconnect();
						sftp.getSession().disconnect();
						sftp.exit();
					} catch (JSchException e) {
						log.error(e, e);
					}
				}
			}

		} else {
			FTPClient ftp = new FTPClient();
			ftp.setConnectTimeout(config.getConnectTimeout());
			try {
				ftp.connect(config.getHost(), config.getPort());
				ftp.login(config.getUserName(), config.getPassword());
				int reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return result;
				}
				String dir=config.getWorkingDirectory();
				if(fileName.indexOf("/")!=-1){
					dir=fileName.substring(0,fileName.lastIndexOf("/"));
					fileName=fileName.substring(fileName.lastIndexOf("/")+1);
				}
				if(dir.endsWith("$date"))
				{
					dir=dir.replace("$date", DateUtil.Date_To_DateTimeFormat(date,
							DateFormatCode.DATE_FORMATTER3));
				}
				ftp.changeWorkingDirectory(dir);
				FTPFile[] fs = ftp.listFiles();
				for (FTPFile ff : fs) {
					if (ff.getName().equals(fileName)) {

						OutputStream is = null;
						try {
							File directory = new File(localFilePath);
							if (!directory.exists()) {
								directory.mkdirs();
							}
							File file = new File(localFilePath + ff.getName());
							if (!file.exists()) {
								file.createNewFile();
							}
							is = new FileOutputStream(file);
							ftp.retrieveFile(ff.getName(), is);
						} finally {
							if (is != null) {
								is.close();
							}
						}
					}
				}

				ftp.logout();
				result = true;
			} catch (IOException e) {
				log.error("文件下载失败", e);
			} finally {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
			}
		}
		return result;
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings("rawtypes")
	public Vector listFiles(String directory, ChannelSftp sftp)
			throws SftpException {
		return sftp.ls(directory);
	}
}
