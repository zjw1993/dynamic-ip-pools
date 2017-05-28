package com.weiniu.liuliuproxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weiniu.utils.IPUtil;
import com.weiniu.utils.UserAgentUtil;

public class LiuLiuProxy {

	private static final Logger logger = LoggerFactory.getLogger(LiuLiuProxy.class);
	
	private static final String FROM = "66代理";
	private static final String URL = "http://www.66ip.cn/getzh.php?getzh=2017052898370&getnum=50&isp=0&anonymoustype=4&start=&ports=&export=&ipaddress=&area=1&proxytype=2&api=https";  // 国内高匿代理
	private static final int TOTAL_PAGE = 10;
	
    public void run(){
    	cralwer(URL, TOTAL_PAGE);
    }  
      
    private void cralwer(String baseUrl, int totalPage){
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\:\\d{1,6}";
        Pattern ipPtn = Pattern.compile(ipReg);
          
        for (int i = 1; i < totalPage; i++) {
            try {
            	Document doc;
        		doc = Jsoup.connect(baseUrl)
        				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
        				.header("Accept-Encoding", "gzip, deflate, sdch")
        				.header("Accept-Language", "zh-CN,zh;q=0.8")
        				.header("Cache-Control", "max-age=0")
        				.header("User-Agent", UserAgentUtil.randomUserAgent())
        				.header("Cookie", "__cfduid=d83b371f0775c092ce05b94bcc7ecba001495793353; UM_distinctid=15c443b7d151cc-0a354f8e7f839c-474f0820-100200-15c443b7d1950; CNZZDATA1253901093=1835687482-1495788054-null%7C1495870544; Hm_lvt_1761fabf3c988e7f04bec51acd4073f4=1495862907,1495863010,1495863021,1495869849; Hm_lpvt_1761fabf3c988e7f04bec51acd4073f4=1495872656; cf_clearance=1c573fe2ce05e9d0b51b068b29b47b90ec3db6f1-1495878658-3600")
        				.header("Host", "www.66ip.cn")
        				.header("Referer", "http://www.66ip.cn/nm.html")
        				.timeout(30*1000)
        				.get();
            	logger.info(baseUrl + "==" + doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(":");
                    String[] chk = checkProxy(strs[0],Integer.parseInt(strs[1]), FROM);
                    if("true".equals(chk[0])){
                    	// 保存有效代理ip到数据库
                    	//proxyIPService.saveProxy(strs[0], Integer.parseInt(strs[1]), chk[1], FROM);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static int totleCount = 0;
    private static int errCount = 0;
    private static int tmCount = 0;
    
    public static String[] checkProxy(String ip, Integer port, String from){ 
    	totleCount++;
        try {  
        	// 随机取一个网站做连接测试
            Document doc = Jsoup.connect("http://1212.ip138.com/ic.asp")
                    .timeout(3*1000)  
                    .proxy(ip, port, null)  
                    .get();
            String myIP = IPUtil.myIP();
            if(!StringUtils.isEmpty(myIP) && doc.text().contains(myIP)) {
            	tmCount++;
            	logger.info(from +"--" + ip + ":" + port + "状态可用=====但是是透明的"); 
            	return new String[]{"false"};
            }
            Elements eles = doc.getElementsByTag("center");
            String text = eles.first().text();
            logger.info(from +"--" + ip + ":" + port + "状态可用, " + text);  
            return new String[]{"true", text.split("：")[2]};  
        } catch (Exception e) {
        	errCount++;
        	logger.info(from +"--" + ip + ":" + port + "状态不可用"); 
            return new String[]{"false"};  
        }
    }
    
	public static void main(String[] args) {
		LiuLiuProxy proxyCrawler = new LiuLiuProxy();
          
        proxyCrawler.run();
        
        System.out.println("总数量：" + totleCount);
        System.out.println("透明数量：" + tmCount);
        System.out.println("不可用数量：" + errCount);
    }
	
	
}
