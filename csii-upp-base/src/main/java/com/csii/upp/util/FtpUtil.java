package com.csii.upp.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Ftp工具类
 * @author Thinkpad
 *
 */
public class FtpUtil {
	private static Log log = LogFactory.getLog(FtpUtil.class);
	private static Properties props = new Properties();

	public static final String FPT_CONFIG_FILENAME = "META-INF/config/ftputil.properties";
	public static final String FTP_HOST = "com.csii.bank.core.ftp.host";
	public static final String FTP_PORT = "com.csii.bank.core.ftp.port";
	public static final String FTP_USERNAME = "com.csii.bank.core.ftp.username";
	public static final String FTP_PASSWORD = "com.csii.bank.core.ftp.password";
	public static final String FTP_WORKING_DIRECTORY = "com.csii.bank.core.ftp.workingDirectory";
	public static final String FTP_FILE_CHARSET = "com.csii.bank.core.ftp.fileCharset";
	public static final String FTP_CONNECT_TIMEOUT = "com.csii.bank.core.ftp.connectTimeout";
	public static final String FTP_SERVER_IP = "com.csii.bank.core.ftp.serverip";
	public static final String FTP_SERVER_PORT = "com.csii.bank.core.ftp.serverport";
	public static final String FTP_STRESS_TEST = "com.csii.bank.core.ftp.stressTest";


	static {
		InputStream is = null;
		is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(FPT_CONFIG_FILENAME);

		try {
			if (is != null) {
				is = new BufferedInputStream(is);
			} else {
				is = new BufferedInputStream(new FileInputStream(
						FPT_CONFIG_FILENAME));
			}
			props.load(is);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException ignore) {
				}
		}
	}

	/**
	 * 执行文件上传
	 * 
	 * @param fileName
	 *            上传到ftp服务器的文件名，带后缀
	 * @param fileContent
	 *            文件内容
	 * @return true-上传成功；false-上传失败
	 */
	public static boolean uploadFile(String fileName, String fileContent) {
		if (props == null || props.isEmpty()) {
			return false;
		}

		if (Boolean.parseBoolean(props.getProperty(FTP_STRESS_TEST))) {
			return true;
		}

		boolean result = false;
		FTPClient ftp = new FTPClient();
		ftp.setConnectTimeout(Integer.parseInt(props
				.getProperty(FTP_CONNECT_TIMEOUT)));

		try {
			// 构建文件流
			ByteArrayInputStream input = new ByteArrayInputStream(
					fileContent.getBytes(props.getProperty(FTP_FILE_CHARSET)));

			// 连接并登录
			ftp.connect(props.getProperty(FTP_HOST),
					Integer.parseInt(props.getProperty(FTP_PORT)));
			ftp.login(props.getProperty(FTP_USERNAME),
					props.getProperty(FTP_PASSWORD));
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}

			// 进入指定目录，若目录不存在则创建，若创建不成功则直接退出
			String workingDirectory = props.getProperty(FTP_WORKING_DIRECTORY);
			if (!ftp.changeWorkingDirectory(workingDirectory)) {
				if (ftp.makeDirectory(workingDirectory)) {
					ftp.changeWorkingDirectory(workingDirectory);
				} else {
					return result;
				}
			}

			// 执行文件上传
			if (ftp.storeFile(fileName, input)) {
				result = true;
			}
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				ftp.disconnect();
			} catch (IOException ex) {
				log.error(ex);
			}
		}
		return result;
	}

	/**
	 * 执行文件下载
	 * 
	 * @param serverFileName
	 *            服务器上要下载的文件名称
	 * @param localPath
	 *            下载到本地路径
	 */
    public static boolean downloadFile(String serverFileName,String localPath) {

		if (props == null || props.isEmpty()) {
			return false;
		}

//		if (Boolean.parseBoolean(props.getProperty(FTP_STRESS_TEST))) {
//			return true;
//		}
		
		FTPClient ftpClient = new FTPClient();
        boolean result = false;
        
        try {

            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.connect(props.getProperty(FTP_HOST), Integer.parseInt(props.getProperty(FTP_PORT)));
            
            // 登录
            ftpClient.login(props.getProperty(FTP_USERNAME),props.getProperty(FTP_PASSWORD));
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            
            // 获取ftp登录应答代码

            int reply = ftpClient.getReplyCode();
            
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return result;
            }
            
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(props.getProperty(FTP_WORKING_DIRECTORY));
            
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(serverFileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftpClient.logout();
            result = true;
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException ex) {
				log.error(ex);
			}
		}
		return result;
    }

    
    /**
	 * 执行文件上传
	 * 
	 * @param upload
	 *            上传到ftp服务器的文件名，带后缀
	 * @param fileName
	 *            本地文件
	 * @return true-上传成功；false-上传失败
	 */
	public static boolean upload(String serverfileName, String fileName) {
		if (props == null || props.isEmpty()) {
			return false;
		}
		if (Boolean.parseBoolean(props.getProperty(FTP_STRESS_TEST))) {
			return true;
		}

		boolean result = false;
		FTPClient ftp = new FTPClient();
		ftp.setConnectTimeout(Integer.parseInt(props
				.getProperty(FTP_CONNECT_TIMEOUT)));
		FileInputStream inStream =null;
		try {
			// 构建文件流
			
            File fileIn = new File(fileName);
            inStream = new FileInputStream(fileIn);

			// 连接并登录
			ftp.connect(props.getProperty(FTP_HOST),
					Integer.parseInt(props.getProperty(FTP_PORT)));
			ftp.login(props.getProperty(FTP_USERNAME),
					props.getProperty(FTP_PASSWORD));
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}

			// 进入指定目录，若目录不存在则创建，若创建不成功则直接退出
			String workingDirectory = props.getProperty(FTP_WORKING_DIRECTORY);
			if (!ftp.changeWorkingDirectory(workingDirectory)) {
				if (ftp.makeDirectory(workingDirectory)) {
					ftp.changeWorkingDirectory(workingDirectory);
				} else {
					return result;
				}
			}

			// 执行文件上传
			if (ftp.storeFile(serverfileName, inStream)) {
				result = true;
			}
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				if(inStream!=null)
					inStream.close();
				ftp.disconnect();
			} catch (IOException ex) {
				log.error(ex);
			}
		}
		return result;
	}
	
	public static void main(String args[]) {
		// uploadFile("test.txt", "2343ewrewrdsdgd没有");
		// uploadLocalFile("D:\\", "test.TXT","testXXX.TXT");
//		downloadFile("test.txt","D:\\");
	}
}
