package com.weiniu.goubanjiaproxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weiniu.utils.IPUtil;

public class GouBanJiaProxy {

	private static final Logger logger = LoggerFactory.getLogger(GouBanJiaProxy.class);
	
	private static final String FROM = "狗搬家代理";
	private static final String URL = "http://dynamic.goubanjia.com/dynamic/get/41485daa711f5d5faf7bc61fa415e8e5.html?random=yes";  // 国内高匿代理
	private static final int TOTAL_PAGE = 1000;
	
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
        				.timeout(30*1000)
        				.get();
            	logger.info(baseUrl + "==" + doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(":");
                    String[] chk = checkProxy(strs[0],Integer.parseInt(strs[1]), FROM);
                    if("true".equals(chk[0])){
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
		GouBanJiaProxy proxyCrawler = new GouBanJiaProxy();
          
        proxyCrawler.run();
        
        System.out.println("总数量：" + totleCount);
        System.out.println("透明数量：" + tmCount);
        System.out.println("不可用数量：" + errCount);
    }
	
	
}
