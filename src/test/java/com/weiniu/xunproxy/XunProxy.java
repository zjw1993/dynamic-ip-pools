package com.weiniu.xunproxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weiniu.utils.IPUtil;

public class XunProxy {

	private static final Logger logger = LoggerFactory.getLogger(XunProxy.class);
	
	private static final String FROM = "讯代理";
	private static final String URL = "http://www.xdaili.cn/ipagent//privateProxy/getDynamicIP/MF20175316635PdoQue/9f7f53f4160211e79ff07cd30abda612?returnType=1";  // 国内高匿代理
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
        				.timeout(30*1000)
        				.get();
            	logger.info(baseUrl + "==" + doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(":");
                    String[] chk = checkProxy(strs[0],Integer.parseInt(strs[1]), FROM);
                    if("true".equals(chk[0])){
                    	// 保存有效代理ip到数据库
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
				Thread.sleep(1000*15);
			} catch (InterruptedException e) {
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
		XunProxy proxyCrawler = new XunProxy();
          
        proxyCrawler.run();
        
        System.out.println("总数量：" + totleCount);
        System.out.println("透明数量：" + tmCount);
        System.out.println("不可用数量：" + errCount);
    }
	
	
}
