package com.csii.test.base;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.csii.pe.service.comm.Transport;

/**
 * icif测试的基类
 * 
 * @author xujin
 * 
 */
public abstract class UPPTest extends BaseTest {

	@Override
	public Transport loadTransport(String testMode) throws Exception {
		if("tcp".equalsIgnoreCase(testMode)){
			return (Transport) new ClassPathXmlApplicationContext(
				"META-INF/config/icif/transport.xml").getBean("client");
		}else if("ws".equalsIgnoreCase(testMode)){
			
		}else if("dubbo".equalsIgnoreCase(testMode)){
			Transport transport=(Transport) new ClassPathXmlApplicationContext(
					"/META-INF/config/dubbo/dubbo.xml").getBean("client");
			return transport;
		}
		return null;
	}
	
	@Override
	public void setTestHeader(Map<String, Object> requestMap) throws Exception {
		// TODO Auto-generated method stub
		requestMap.put("ChnlId", "99");
		//requestMap.put("termnId", "");
		requestMap.put("BranchId", "1234567890");
		requestMap.put("OperId", "123654");
		requestMap.put("ReqTime", "20170613175050");
		requestMap.put("BizTrackNo", String.valueOf(System.currentTimeMillis()));
		requestMap.put("ReqSeqNo", String.valueOf(System.currentTimeMillis()));
		requestMap.put("ClientIp", "127.0.0.1");
		requestMap.put("Summery", "备注");
	}
	


	private String getLocalMac() throws Exception {
		// TODO Auto-generated method stub
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(
				InetAddress.getLocalHost()).getHardwareAddress();
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}

		return sb.toString().toUpperCase();
	}

}