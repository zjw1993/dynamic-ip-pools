package com.weiniu.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weiniu.entity.ProxyIP;

public class ProxyUtil {

	private static final Logger logger = LoggerFactory.getLogger(ProxyUtil.class);
	
	// 选取一些响应速度比较快的网址用来测试代理IP的有效性
	private static final String[] TEST_SITES = {
			/*"http://www.json.cn",*/
			"http://1212.ip138.com/ic.asp"
			/*"http://www.bejson.com",
			"http://www.baidu.com"*/
	};
	
	private static String getUrl(){
		return TEST_SITES[RandomUtil.randomInt(TEST_SITES.length)];
	}
	
    public static String[] checkProxy(String ip, Integer port, String from){
    	logger.info(from +"--" + ip + ":" + port);
        try {
        	// 随机取一个网站做连接测试
            Document doc = Jsoup.connect(getUrl())
            		.proxy(ip, port, null)
            		.header("User-Agent", UserAgentUtil.randomUserAgent())
                    .timeout(3*1000)
                    .get();
            
            String myIP = IPUtil.myIP();
            if(!StringUtils.isEmpty(myIP) && doc.text().contains(myIP)) {
            	logger.info(from +"--" + ip + ":" + port + "状态可用=====但是是透明的"); 
            	return new String[]{"false"};
            }
            
            Elements eles = doc.getElementsByTag("center");
            String text = eles.first().text();
            
            logger.info(from +"--" + ip + ":" + port + "状态可用, " + text);  
            return new String[]{"true", text.split("：")[2]};  
        } catch (Exception e) {
            return new String[]{"false"};  
        }
    }
    
    public static boolean checkProxy(ProxyIP ip){  
    	try {  
    		// 随机取一个网站做连接测试
    		Jsoup.connect(getUrl())
    		.proxy(ip.getHost(), ip.getPort(), null)
    		.timeout(3*1000)  
    		.get();
    		logger.info(ip + ":" + ip.getPort() + "状态可用");  
    		return true;  
    	} catch (Exception e) {
    		return false;  
    	}
    }
    
    
    
    public static void main(String[] args) {
		checkProxy("183.153.30.73", 808, "");
	}
	
}
