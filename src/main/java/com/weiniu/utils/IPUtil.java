package com.weiniu.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

public class IPUtil {

	private static String MY_IP;
	
	public static String myIP(){
		if(StringUtils.isEmpty(MY_IP)) {
			getMyIP();
		}
		
		return MY_IP;
	}

	private static void getMyIP() {
		String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern ipPtn = Pattern.compile(ipReg);
		try {
			Document doc = Jsoup.connect("http://1212.ip138.com/ic.asp")
			        .timeout(3*1000)  
			        .get();
			
			Matcher m = ipPtn.matcher(doc.text());
			if(m.find()) {
				MY_IP = m.group();
				System.err.println("**********获取到无代理模式下我的IP为：" + MY_IP + "*************");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
