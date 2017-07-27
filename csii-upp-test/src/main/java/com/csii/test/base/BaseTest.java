package com.csii.test.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.csii.pe.service.comm.Transport;

/**
 * 测试的基类
 * 
 * @author xujin
 * 
 */
public abstract class BaseTest {

	/**
	 * @fields log 日志变量
	 */
	public static Logger log;

	/**
	 * @throws Exception
	 * @description 单元测试初始化
	 */
    @BeforeClass
	public void setUp() {
		log = LoggerFactory.getLogger(getClass());
	}

	/**
	 * @description 执行测试
	 * @throws Exception
	 */
    @Test
	public abstract void test();

	public abstract Transport loadTransport(String testMode) throws Exception;

	public abstract void setTestHeader(Map<String, Object> requestMap) throws Exception;

	/**
	 * @description 发送请求运行测试交易
	 */
	@SuppressWarnings("unchecked")
	public void callServer(Map<String, Object> requestMap) {
		try {
			String testMode=this.getTestMode();
			Transport transport=((Transport)this.loadTransport(testMode));
			// 组装报文头
			setTestHeader(requestMap);
			print("Request DataMap", requestMap);
			Map<String, Object> responseMap ;
			// 发送请求
			responseMap=(Map<String, Object>) transport.submit(requestMap);
			// 处理请求结果
			print("Response DataMap", responseMap);	

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	public String getTestMode(){
		String path = "META-INF/config/cfg.properties";
		Properties config = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				path);
		try {
			config.load(input);
			return config.getProperty("testMode");
		} catch (IOException e) {
			throw new RuntimeException(
					"An error occurred while reading classpath property '"
							+ path + "', see nested exceptions", e);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// close quietly
			}
		}
	}

	/**
	 * @description 对未赋值的字段设定默认值
	 * @param requestMap
	 *            报文数据
	 * @param nodeName
	 *            节点名称
	 * @param defaultValue
	 *            默认值
	 */
	protected void setNodeInfo(Map<String, Object> requestMap, String nodeName,
			Object defaultValue) {
		Object origValue = requestMap.get(nodeName);
		if (origValue == null) {
			requestMap.put(nodeName, defaultValue);
		}
	}

	/**
	 * @description 打印测试信息
	 * @param title
	 *            标题信息
	 * @param dataMap
	 *            打印参数
	 */
	private void print(String title, Object obj) {
		log.debug(getPrintSeperator() + title + "-Begin-" + getPrintSeperator());
		log.debug(obj.toString());
		log.debug(getPrintSeperator() + title + "--End--" + getPrintSeperator());
	}

	/**
	 * @description 设置打印信息格式
	 * @return 打印格式信息
	 */
	private static String getPrintSeperator() {
		return "************************************";
	}

}