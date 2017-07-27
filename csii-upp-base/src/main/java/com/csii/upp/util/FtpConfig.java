package com.csii.upp.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FtpConfig {
	private String mode = "ftp";
	private String host = "localhost";
	private int port = 21;
	private String userName = "admin";
	private String password = "password";
	//private String workingDirectory = "/test";
	private String workingDirectory = "/testtemp/powerpay";
	private int connectTimeout = 10000;
	private boolean testing = false;

	public FtpConfig() {
	}
	
	FtpConfig(String configFilePath) {
		resolveFile(configFilePath);
	}

	private void resolveFile(String configFilePath) {
		InputStream is = null;
		Properties props = new Properties();
		String temp;

		is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(configFilePath);
		try {
			if (is != null) {
				is = new BufferedInputStream(is);
			} else {
				is = new BufferedInputStream(
						new FileInputStream(configFilePath));
			}
			props.load(is);

			temp = props.getProperty("com.csii.bank.core.ftp.mode");
			if (temp != null && !temp.isEmpty()) {
				this.mode = temp;
			}
			temp = props.getProperty("com.csii.bank.core.ftp.host");
			if (temp != null && !temp.isEmpty()) {
				this.host = temp;
			}
			temp = props.getProperty("com.csii.bank.core.ftp.port");
			if (temp != null && !temp.isEmpty()) {
				this.port = Integer.valueOf(temp);
			}
			temp = props.getProperty("com.csii.bank.core.ftp.username");
			if (temp != null && !temp.isEmpty()) {
				this.userName = temp;
			}
			temp = props.getProperty("com.csii.bank.core.ftp.password");
			if (temp != null && !temp.isEmpty()) {
				this.password = temp;
			}
			temp = props.getProperty("com.csii.bank.core.ftp.connectTimeout");
			if (temp != null && !temp.isEmpty()) {
				this.connectTimeout = Integer.valueOf(temp);
			}
			temp = props.getProperty("com.csii.bank.core.ftp.workingDirectory");
			if (temp != null && !temp.isEmpty()) {
				this.workingDirectory = temp;
			}
			temp = props.getProperty("com.csii.bank.core.ftp.testing");
			if (temp != null && !temp.isEmpty()) {
				this.testing = Boolean.parseBoolean(temp);
			}
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

	public String getMode() {
		return mode;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public boolean isTesting() {
		return testing;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

}
