package com.weiniu.antproxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.weiniu.utils.IPUtil;
import com.weiniu.utils.MaYiUtil;

public class MaYiProxy {

	private static final String IP138 = "http://1212.ip138.com/ic.asp";

	private static int total = 0;  // 总代理数
	private static int error = 0;  // 无效代理数
	private static int transparency = 0;  // 透明代理数
	
	public static void main(String[] args) {
		int i = 100;
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
            		.proxy(MaYiUtil.PROXY_IP, MaYiUtil.PROXY_PORT, null)
            		.header("Proxy-Authorization", MaYiUtil.authHeader())
                    .timeout(5*1000)
                    .get();
            String myIP = IPUtil.myIP(); // 获取我的IP
            // 如果IP138检测到的IP和我的IP一样表示该代理为透明代理
            if(!StringUtils.isEmpty(myIP) && doc.text().contains(myIP)) {
            	transparency++;
            	return;
            }
            
            // 输出IP信息描述文本
            Elements eles = doc.getElementsByTag("center");
            String text = eles.first().text();
            System.out.println(text);
            
        } catch (Exception e) {
        	error++;
        	e.printStackTrace();
        }
	}
	
}
