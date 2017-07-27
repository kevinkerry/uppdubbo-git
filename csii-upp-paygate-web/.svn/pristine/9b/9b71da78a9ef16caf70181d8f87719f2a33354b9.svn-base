package com.csii.upp.paygate.action.wap;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class WXSignUtil {
    
	public static String getSha1(String str){
	    if(str == null || str.length()==0){
	        return null;
	    }
	    System.out.println(str);
	    char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));

	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char buf[] = new char[j*2];
	        int k = 0;
	        for(int i=0;i<j;i++){
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	public static String sign(Map signMap) {
		signMap.put("jsapi_ticket", "kgt8ON7yVITDhtdwci0qea0cQXDpPlcPDyN_1bX85Kq8RRnT7_3vWrmPYv91VI1M6RIP-Uzfr85iOoLGP2P_vA");
		signMap.put("url", "http://150.221.75.27:8003/paygate/qrCode?Plain={MerchantName:ab,TransAmt:5.00,TransName:QREM}");
		SortedMap<String, String> sort = new TreeMap<String, String>(signMap);
		Set<Entry<String, String>> entry1 = sort.entrySet();
		Iterator<Entry<String, String>> it = entry1.iterator();
		StringBuilder sf = new StringBuilder();
		
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = entry.getKey();
			Object v = entry.getValue();
			if ((null != v) && !"".equals(v)) {
				sf.append(k + "=" + v + "&");
			}
		}
		String stringA = sf.toString();
		return WXSignUtil.getSha1(stringA.substring(0, stringA.length()-1));

	}
	
	public static void main(String[] args){
		Map signMap =new HashMap();
		long i=System.currentTimeMillis()/1000L;
		signMap.put("timestamp", i);
		signMap.put("noncestr", "7m37o14qDmo66mvl");
		String str1 =String.valueOf(System.currentTimeMillis());
		String str=WXSignUtil.sign(signMap);
		System.out.println(str);
	}
}