package com.weiniu.antproxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.weiniu.utils.IPUtil;

public class MaYiProxy {

	// 定义申请获得的appKey和appSecret
	private static final String APPKEY = "77754893";
	private static final String SECRET = "bf2177d6a45dd1d067f4fe474e41181f";
	private static final String PROXY_IP = "s3.proxy.mayidaili.com";
	private static final int PROXY_PORT = 8123;
	
	private static final String IP138 = "http://1212.ip138.com/ic.asp";

	private static int total = 0;
	private static int error = 0;
	private static int transparency = 0;
	
	public static void main(String[] args) {
		int i = 1000000;
		while(i-->0) {
			total++;
			getFromIP138();
		}
		
		System.out.println("total:" +total);
		System.out.println("error:" +error);
		System.out.println("transparency:" +transparency);
		
	}
    
	private static void getFromIP138(){
		
		try {
            Document doc = Jsoup.connect(IP138)
            		.proxy(PROXY_IP, PROXY_PORT, null)
            		.header("Proxy-Authorization", MaYiAuthUtil.getAuthHeader(APPKEY, SECRET))
                    .timeout(5*1000)
                    .get();
            String myIP = IPUtil.myIP();
            if(!StringUtils.isEmpty(myIP) && doc.text().contains(myIP)) {
            	transparency++;
            	return;
            }
            Elements eles = doc.getElementsByTag("center");
            String text = eles.first().text();
            System.out.println(text);
            
        } catch (Exception e) {
        	error++;
        	e.printStackTrace();
        }
	}
	
}
