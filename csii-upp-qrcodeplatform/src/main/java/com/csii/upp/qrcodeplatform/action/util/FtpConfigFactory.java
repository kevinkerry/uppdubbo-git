package com.csii.upp.qrcodeplatform.action.util;

import java.util.concurrent.ConcurrentHashMap;

public class FtpConfigFactory {
	private static ConcurrentHashMap<String, FtpConfig> cache = new ConcurrentHashMap<String, FtpConfig>();

	public static FtpConfig getConfig(String fileType) {
		String key = fileType + "-ftp.properties";
		FtpConfig config = cache.get(key);
		if (config == null) {
			config = new FtpConfig("config/ftp/" + key);
			cache.put(key, config);
		}
		return config;
	}
}
