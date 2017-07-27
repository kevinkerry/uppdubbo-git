package com.csii.upp.batch.appl;

import java.util.Map;

import com.csii.upp.batch.appl.base.BaseAppl;



public class SimpleTestAppl extends BaseAppl {
	static int count = 0;

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps)
			throws Exception {
		try {
			Thread.sleep(1000);
			System.out.println("executed object:"+entry.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		throw new RuntimeException("hellokitty");
//		 if (i == 10) {
//		 CoreExceptionThrower.tryThrow(1001);
//		 }
//		 if (i == 20 && count == 0) {
//		 count++;
//		 throw new ConflictingModificationException("hello world");
//		 }
	}
}
