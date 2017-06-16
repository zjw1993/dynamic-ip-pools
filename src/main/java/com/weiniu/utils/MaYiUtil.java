package com.weiniu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.google.common.base.Joiner;

public class MaYiUtil {

	// 定义申请获得的appKey和appSecret
	public static final String PROXY_IP = "";
	public static final int PROXY_PORT = 8123;
	
	private static final String APPKEY = "";
	private static final String SECRET = "";
	
	public static String authHeader() {
		
		// 创建参数表
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("app_key", APPKEY);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));  // 使用中国时间，以免时区不同导致认证错误
		paramMap.put("timestamp", format.format(new Date()));
		paramMap.put("timeout", "100000");
		paramMap.put("enable-simulate", "true");     // true | false 是否只能模拟请求头信息
		paramMap.put("random-useragent", "mobile");  // disabled | pc | mobile , 在enable-simulate为true时生效 
		
		// 对参数名进行排序
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		
		// 拼接有序的参数名-值串
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SECRET);
		for (String key : keyArray) {
			stringBuilder.append(key).append(paramMap.get(key));
		}
		
		stringBuilder.append(SECRET);
		String codes = stringBuilder.toString();
		
		// MD5编码并转为大写， 这里使用的是Apache codec
		String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(codes).toUpperCase();
		
		paramMap.put("sign", sign);
		
		// 拼装请求头Proxy-Authorization的值，这里使用 guava 进行map的拼接
		String authHeader = "MYH-AUTH-MD5 " + Joiner.on('&').withKeyValueSeparator("=").join(paramMap);
		
		//System.out.println(authHeader);
		
		return authHeader;
	}
	
}
