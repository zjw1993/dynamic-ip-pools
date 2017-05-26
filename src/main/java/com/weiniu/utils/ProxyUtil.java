package com.weiniu.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ProxyUtil {

	private static final String[] TEST_SITES = {
			"http://www.json.cn/",
			"http://1212.ip138.com/ic.asp",
			"http://www.bejson.com/jsoneditoronline/",
			"http://www.baidu.com",
			"http://zhangjw.cn/"
	};
//	private static final String[] TEST_SITES = {
//		"http://zhangjw.cn/"
//	};
//	
    public static boolean checkProxy(String ip, Integer port, String from){  
        try {  
            // http://1212.ip138.com/ic.asp 可以换成任何比较快的网页 
        	// 随机取一个网站做连接测试
        	String url = TEST_SITES[(int) Math.floor(Math.random()*TEST_SITES.length)];
            Document doc = Jsoup.connect(url)
                    .timeout(3*1000)  
                    .proxy(ip, port, null)  
                    .get();  
            //System.out.println(doc);
            System.out.println(from +"--" + ip + ":" + port + "状态可用");  
            return true;  
        } catch (Exception e) {
        	//System.err.println(from +"--" + ip + ":" + port + "状态不可用");
            return false;  
        }
    }
    
    public static void main(String[] args) {
		checkProxy("183.153.30.73", 808, "");
	}
	
}
