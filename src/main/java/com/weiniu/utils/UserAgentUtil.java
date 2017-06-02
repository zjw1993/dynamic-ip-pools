package com.weiniu.utils;

public class UserAgentUtil {

	/**
	 * 返回一个随机PC端的UserAgent
	 * @return
	 */
	public static String randomPcUA(){
		return PC_USER_AGENTS[RandomUtil.randomInt(PC_USER_AGENTS.length)];
	}
	
	/**
	 * 返回一个随机手机端的UserAgent
	 * @return
	 */
	public static String randomMobileUA(){
		return MOBILE_USER_AGENTS[RandomUtil.randomInt(MOBILE_USER_AGENTS.length)];
	}
	
	// 浏览器类型集合
	private static final String[] PC_USER_AGENTS = {
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)",
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
		"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
		"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0",
		"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b13pre) Gecko/20110307 Firefox/4.0b13pre",
		"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.15) Gecko/20110303 Firefox/3.6.15",
		"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
	};
	
	private static final String[] MOBILE_USER_AGENTS = {
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; PE-TL20 Build/HuaweiPE-TL20) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/37.0.0.0 MQQBrowser/6.3 Mobile Safari/537.36",
		"Mozilla/5.0 (Linux; Android 4.4.4; SM-N910P Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36",
		"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; X909 Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30",
		"Mozilla/5.0 (Linux; Android 5.0.1; GT-I9500 Build/LRX22C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36 MicroMessenger/6.3.8.65_r47ff115.661 NetType/WIFI Language/id" ,
		"Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; R831S Build/JLS36C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 LieBaoFast/3.25.5" ,
		"Mozilla/5.0 (Linux; U; Android 5.1; zh-CN; m2 Build/LMY47D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.8.5.689 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; Android 4.4.2; Lenovo A320t Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 baiduboxapp/5.0 (Baidu; P1 4.4.2)" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; HM 2A Build/KTU84Q) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025477 Mobile Safari/533.1 V1_AND_SQ_5.9.5_288_YYB_D QQ/5.9.5.2575 NetType/WIFI WebP/0.3.0 Pixel/720" ,
		"Mozilla/5.0 (Linux; Android 4.4.4; 2014813 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-CN; ZTE Q509T Build/KTU84P) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/9.9.2.467 U3/0.8.0 Mobile Safari/533.1" ,
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; {D9D54F49-E51C-445e-92F2-1EE3C2313240}; Tablet PC 2.0)" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; m1 note Build/KTU84P) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.7.51_rbb7fa12.660 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; Che2-TL00M Build/HonorChe2-TL00M) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.8.56_re6b2553.680 NetType/cmnet Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.0.4; zh-CN; Coolpad 8720L Build/JRO03L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/9.9.7.500 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; MI NOTE LTE Build/KTU84P) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025489 Mobile Safari/533.1 MicroMessenger/6.3.8.56_re6b2553.680 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-CN; 6 Plus Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.7.5.655 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 5.1.1; zh-CN; ONE E1001 Build/LMY47V) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.9.2.712 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; Lenovo A320T Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.6.2.626 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; Android 4.4.4; HUAWEI C8817E Build/HuaweiC8817E) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; Coolpad 8675 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/6.1 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 5.0.2; zh-cn; MI 4LTE Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/37.0.0.0 MQQBrowser/6.3 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; L50u Build/17.1.1.F.0.43) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.8.5.689 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 5.1.1; zh-cn; HUAWEI P7-L07 Build/HuaweiP7-L07) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.8.50_r251a77a.680 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 5.0.2; zh-cn; MI 2SC Build/LRX22G) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.7.51_rbb7fa12.660 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; 2013023 Build/HM2013023) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.9.0.703 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; P301 Build/KTU84P) AppleWebKit/534.24 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.24 T5/2.0 baiduboxapp/6.8 (Baidu; P1 4.4.4)" ,
		"Mozilla/5.0 (Linux; U; Android 2.3; en-us; SAMSUNG-SGH-I717 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1" ,
		"Mozilla/5.0 (Linux; U; Android 5.1.1; zh-cn; A51 Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/37.0.0.0 MQQBrowser/6.3 Mobile Safari/537.36" ,
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; CMDTDF; Tablet PC 2.0; .NET4.0C)" ,
		"Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; HS-X8C Build/JLS36C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/6.3.8.56_re6b2553.680 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (iPhone 5; CPU iPhone OS 8_4_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/6.0 MQQBrowser/6.1.1 Mobile/12H321 Safari/8536.25" ,
		"Mozilla/5.0 (Linux; Android 4.4.4; zh-cn; SAMSUNG-SM-A7009 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/2.0 Chrome/34.0.1847.76 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; 1100 Build/KTU84P) AppleWebKit/534.24 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.24 T5/2.0 search/1.0 baiduboxapp/7.0 (Baidu; P1 4.4.4)" ,
		"Mozilla/5.0 (Linux; U; Android 5.1; zh-cn; m2 Build/LMY47D) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025487 Mobile Safari/533.1 MicroMessenger/6.3.8.50_r251a77a.680 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (iphone; U; CPU iPhone OS 4_3_5 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5" ,
		"Mozilla/5.0 (iPhone; CPU iPhone OS 9_2 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/7.0 Mobile/13C75 Safari/9537.53" ,
		"Mozilla/5.0 (Linux; U; Android 4.2.1; zh-cn; R809T Build/JOP40D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 OppoBrowser/3.6.3 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; G621-TL00 Build/HonorG621-TL00) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.8.56_re6b2553.680 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-CN; Lenovo S650 Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.6.2.626 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 5.1.1; zh-CN; HUAWEI P7-L07 Build/HuaweiP7-L07) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.9.0.703 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 5.1; zh-CN; MX5 Build/LMY47I) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.8.5.689 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; MI 3 Build/KTU84P) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.7.51_rbb7fa12.660 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.2.1; zh-cn; 2013022 Build/HM2013022) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025463 Mobile Safari/533.1 MicroMessenger/6.2.4.51_rdf8da56.600 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; PE-CL00 Build/HuaweiPE-CL00) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.7.51_rbb7fa12.660 NetType/ctlte Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-CN; U3 Build/KTU84P) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.9.0.703 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 2.3.4; zh-cn; Z6 Build/GRJ22) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1)" ,
		"Mozilla/5.0 (Linux; U; Android 5.1.1; zh-cn; SCL-AL00 Build/HonorSCL-AL00) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.3 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; Android 5.1; m2 Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.114 Mobile Safari/537.36 baiduboxapp/5.0 (Baidu; P1 5.1)" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; HM NOTE 1S Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 XiaoMi/MiuiBrowser/2.1.1" ,
		"Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; GT-N7108 Build/JSS15J) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.7.51_rbb7fa12.660 NetType/cmnet Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 5.0.1; zh-cn; GT-I9508V Build/LRX22C) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/6.1 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; Android 4.4.4; SM-W2015 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-CN; N918St Build/KTU84P) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.4.0.558 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 5.1.1; zh-cn; SM-N9100 Build/LMY47X) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.8.56_re6b2553.680 NetType/cmnet Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; GN9000 Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.9.1.711 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-CN; Coolpad 7295C Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser-CMCC/10.0.0.488 U3/0.8.0 Mobile Safari/534.30" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; G621-TL00 Build/HonorG621-TL00) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.8.56_re6b2553.680 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 5.1; zh-cn; MX5 Build/LMY47I) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025478 Mobile Safari/533.1 MicroMessenger/6.3.7.51_rbb7fa12.660 NetType/WIFI Language/zh_CN" ,
		"Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn;lephone-td8208 Build/04.30.59) AppleWebKit/534.24 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.24 360 Aphone Browser (4.1.2)" ,
		"Mozilla/5.0 (Linux; U; Android 5.0.2; zh-cn; MI 2S Build/LRX22G) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 V1_AND_SQ_6.1.0_312_YYB_D QQ/6.1.0.2635 NetType/WIFI WebP/0.3.0 Pixel/720" ,
		"Mozilla/5.0 (Linux; U; Android 5.0.2; zh-CN; Redmi Note 2 Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/10.5.0.94402 Mobile Safari/537.36" ,
		"Mozilla/5.0 (Linux; U; Android 4.0.4; zh-cn; LT29i Build/7.0.A.3.197) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.3.8.56_re6b2553.680 NetType/WIFI Language/zh_CN"	
	};
	
	
}
