package com.csii.test.fundprocess;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.csii.test.base.UPPTest;


public class DemoTest  extends UPPTest{

	@SuppressWarnings("unchecked")
	@Override
	public void test() {
		// TODO Auto-generated method stub
		Map requestMap=new HashMap();
		requestMap.put("servcId", "IS_PAY_DEMO_DEMO");
		try{
			this.callServer(requestMap);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		DemoTest de = new DemoTest();
		de.log = LoggerFactory.getLogger(de.getClass());
		de.test();
	}

}
