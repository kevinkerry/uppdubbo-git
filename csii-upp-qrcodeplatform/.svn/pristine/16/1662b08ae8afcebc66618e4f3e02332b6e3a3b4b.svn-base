package com.csii.upp.qrcodeplatform.action.base;

import com.csii.pe.service.comm.Server;

/**
 * @classname Main
 * @description 加载容器，服务启动类
 * @author 徐锦
 *
 */
public class WebServiceServer implements Server {

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		try {
			org.eclipse.jetty.xml.XmlConfiguration.main(new String[] { 
					"jetty.xml"
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
