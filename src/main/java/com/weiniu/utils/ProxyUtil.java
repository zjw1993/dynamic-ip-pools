package com.weiniu.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weiniu.entity.ProxyIP;

public class ProxyUtil {

	private static final Logger logger = LoggerFactory.getLogger(ProxyUtil.class);
	
	private static List<String> proxyList = new ArrayList<String>();
	
	private static final String API_URL = "http://www.66ip.cn/nmtq.php?"
			+ "getnum=5&isp=0&anonymoustype=4&start=&ports=&export=&ipaddress="
			+ "&area=1&proxytype=0&api=66ip";
	// API调用时间间隔   毫秒  -1为不限制
	private static long CALL_INTERVAL = 200;
	// 上一次API调用时间毫秒数
	private static long LAST_TIME_MILLIS = 0;
	
	private static String IP_REG = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\:\\d{1,6}";
	
	private static String current_ip = "";
	
	public static void removeProxy(){
		proxyList.remove(current_ip);
	}
	
	public static URLConnection getConnection(URL url) throws IOException{
		String[] ip = getAProxy().split(":");
		InetSocketAddress socket = 
				new InetSocketAddress(ip[0], Integer.parseInt(ip[1]));
		Proxy proxy = new Proxy(Proxy.Type.HTTP, socket);
		URLConnection conn = null;
		conn = url.openConnection(proxy);
		conn.setConnectTimeout(1000*3);
		return conn;
	}
	
	/**
	 * get a random proxy
	 * @return String Like "192.168.101.1:8888"
	 */
	public static String getAProxy(){
		checkProxyList();
		System.out.println("proxyList 中 当前可用ip数 = " + proxyList.size());
		String proxy = proxyList.get(RandomUtil.randomInt(proxyList.size()));
		while(!checkProxy(proxy)){
			proxyList.remove(proxy);
			checkProxyList();
			proxy = proxyList.get(RandomUtil.randomInt(proxyList.size()));
		}
		current_ip = proxy;
		return proxy;
	}
	
    private static void checkProxyList() {
    	while(proxyList.size() < 10) {
    		long current = System.currentTimeMillis();
    		if(-1 != CALL_INTERVAL && CALL_INTERVAL + LAST_TIME_MILLIS > current) {
    			try {
					Thread.sleep(CALL_INTERVAL - (current - LAST_TIME_MILLIS));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		getProxyFromAPI();
    	}
	}

	private static void getProxyFromAPI() {
		LAST_TIME_MILLIS = System.currentTimeMillis();
		
		Pattern ipPtn = Pattern.compile(IP_REG);
		try {
			Document doc = getDocument(API_URL, null, null);
			
	        Matcher m = ipPtn.matcher(doc.text());
            
            while(m.find()){
            	String proxy = m.group();
            	String[] arr = proxy.split(":");
            	String[] chk = checkProxy(arr[0], Integer.parseInt(arr[1]), "");
                if("true".equals(chk[0])){
                	// 保存有效代理ip到列表
                	proxyList.add(proxy);
                }
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] checkProxy(String ip, Integer port, String from){
    	logger.info(from +"--" + ip + ":" + port);
        try {
        	// 随机取一个网站做连接测试
            Document doc = getDocument(getUrl(), ip, port);
            
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
    		getDocument(getUrl(), ip.getHost(), ip.getPort());
    		logger.info(ip + ":" + ip.getPort() + "状态可用");  
    		return true;  
    	} catch (Exception e) {
    		return false;  
    	}
    }
    
    private static boolean checkProxy(String proxy){
    	String[] arr = proxy.split(":");
    	try {  
    		// 随机取一个网站做连接测试
    		getDocument(getUrl(), arr[0], Integer.parseInt(arr[1]));
    		//logger.info(arr[0] + ":" + arr[1] + "状态可用");  
    		return true;  
    	} catch (Exception e) {
    		return false;  
    	}
    }
    
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
	
	private static Document getDocument(String url, String proxyIP, Integer port) throws IOException{
		Connection conn = Jsoup.connect(url)
				.header("User-Agent", UserAgentUtil.randomPcUA())
				.timeout(3*1000);
		if(!StringUtils.isEmpty(proxyIP) || !(null == port)){
			conn = conn.proxy(proxyIP, port, null);
		} 
		return conn.get();
	}
    
    public static void main(String[] args) {
		checkProxy("183.153.30.73", 808, "");
	}
	
}
