package com.csii.upp.qrcodeplatform.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @classname Main
 * @description 服务启动类，负责加载Servlet容器
 * @author xujin
 * @date 2017年6月6日
 * 
 */
public class Main {
	protected static Logger log = LoggerFactory.getLogger(Main.class);
    /**
     * @description 加载jetty容器
     * @param args
     *            输入参数
     */
    public static void main(String[] args) {
    	log.debug("===============开始启动jetty=======");
        try {
            org.eclipse.jetty.xml.XmlConfiguration.main(new String[] { "jetty.xml" });
        } catch (Exception e) {
			log.error("===============jetty启动失败=======",e);
        }
    }

}