package com.weiniu.cralwer;  
  
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiniu.entity.ProxyIP;
import com.weiniu.service.IProxyIPService;
import com.weiniu.utils.ProxyUtil;
import com.weiniu.utils.UserAgentUtil;
  
/** 
 *  
 * LiuLiuProxyIPCralwer
 * @Description: [66ip代理]网站代理服务器ip地址爬取
 * 
 */
@Component
public class LiuLiuProxyIPCralwer {
  
	private static final Logger logger = LoggerFactory.getLogger(LiuLiuProxyIPCralwer.class);
	
	private static final String FROM = "66代理";
	private static final String URL = "http://www.66ip.cn/nmtq.php"
			+ "?getnum=100&isp=0&anonymoustype=4&start=&ports=&export=&ipaddress=&area=1&proxytype=0&api=66ip";  // 国内高匿代理
	private static final int TOTAL_PAGE = 10;
	
	@Autowired
	private IProxyIPService proxyIPService;
	
    public void run(){
    	cralwer(URL, TOTAL_PAGE);
    }  
      
    private void cralwer(String baseUrl, int totalPage){
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\:\\d{1,6}";
        Pattern ipPtn = Pattern.compile(ipReg);
        ProxyIP proxy;
          
        for (int i = 1; i < totalPage; i++) {
        	proxy = proxyIPService.getAProxy();
            try {
            	Document doc;
            	if(null != proxy) {
            		doc = Jsoup.connect(baseUrl)
            				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            				.header("Accept-Encoding", "gzip, deflate, sdch")
            				.header("Accept-Language", "zh-CN,zh;q=0.8")
            				.header("Cache-Control", "max-age=0")
            				.header("User-Agent", UserAgentUtil.randomUserAgent())
            				.header("Cookie", "__cfduid=dd63149c5ec5f80ab1cbf56230f455bac1495803437; UM_distinctid=15c44d3a197f6-01b7304b27b953-3c654009-1fa400-15c44d3a19817e; CNZZDATA1253901093=143815242-1495798868-null%7C1495798868; Hm_lvt_1761fabf3c988e7f04bec51acd4073f4=1495803339,1495803469; Hm_lpvt_1761fabf3c988e7f04bec51acd4073f4=1495803469")
            				.header("Host", "www.66ip.cn")
            				.header("Referer", "http://www.66ip.cn/nm.html")
            				.timeout(30*1000)
            				.proxy(proxy.getHost(), proxy.getPort(), null)
            				.get();
            	}else {
            		doc = Jsoup.connect(baseUrl)
            				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            				.header("Accept-Encoding", "gzip, deflate, sdch")
            				.header("Accept-Language", "zh-CN,zh;q=0.8")
            				.header("Cache-Control", "max-age=0")
            				.header("User-Agent", UserAgentUtil.randomUserAgent())
            				.header("Cookie", "__cfduid=dd63149c5ec5f80ab1cbf56230f455bac1495803437; UM_distinctid=15c44d3a197f6-01b7304b27b953-3c654009-1fa400-15c44d3a19817e; CNZZDATA1253901093=143815242-1495798868-null%7C1495798868; Hm_lvt_1761fabf3c988e7f04bec51acd4073f4=1495803339,1495803469; Hm_lpvt_1761fabf3c988e7f04bec51acd4073f4=1495803469")
            				.header("Host", "www.66ip.cn")
            				.header("Referer", "http://www.66ip.cn/nm.html")
            				.timeout(30*1000)
            				.get();
            	}
            	logger.info(baseUrl + "==" + doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(":");
                    if(ProxyUtil.checkProxy(strs[0],Integer.parseInt(strs[1]), FROM)){
                    	// 保存有效代理IP到数据库
                    	proxyIPService.saveProxy(strs[0], Integer.parseInt(strs[1]), FROM);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                proxy = proxyIPService.getAProxy();
            }

        }
    }
    
	public static void main(String[] args) {
        LiuLiuProxyIPCralwer proxyCrawler = new LiuLiuProxyIPCralwer();
          
        proxyCrawler.run();
    }
}