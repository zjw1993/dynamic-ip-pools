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
 * ProxyCralwerUnusedVPN 
 * @Description: [快代理]网站代理服务器ip地址爬取
 * 
 */
@Component
public class KDLProxyIPCralwer {
  
	private static final Logger logger = LoggerFactory.getLogger(KDLProxyIPCralwer.class);
	
	private static final String FROM = "快代理";
	private static final String URL = "http://www.kuaidaili.com/free/inha/";  // 国内高匿代理
	private static final int TOTAL_PAGE = 15;
	
	@Autowired
	private IProxyIPService proxyIPService;
	
    public void run(){
    	cralwer(URL, TOTAL_PAGE);  
    }  
    
    private void cralwer(String baseUrl, int totalPage){
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";
        Pattern ipPtn = Pattern.compile(ipReg);
        ProxyIP proxy;
        
        for (int i = 1; i < totalPage; i++) {
        	proxy = proxyIPService.getAProxy();
            try {
            	Document doc;
            	if(null != proxy) {
            		doc = Jsoup.connect(baseUrl + i + "/")
                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                            .header("Accept-Encoding", "gzip, deflate, sdch")
                            .header("Accept-Language", "zh-CN,zh;q=0.8")
                            .header("Cache-Control", "max-age=0")
                            .header("User-Agent", UserAgentUtil.randomUserAgent())
                            .header("Cookie", "channelid=0; sid=1495777796497660; _ga=GA1.2.7788636.1495776618; _gid=GA1.2.377650158.1495778243; Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1495776618; Hm_lpvt_7ed65b1cc4b810e9fd37959c9bb51b31=1495778243")
                            .header("Host", "www.kuaidaili.com")
                            .header("Referer", baseUrl + (i-1) + "/")
                            .timeout(30*1000)
                            .proxy(proxy.getHost(), proxy.getPort(), null)
                            .get();
            	} else {
            		doc = Jsoup.connect(baseUrl + i + "/")
            				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            				.header("Accept-Encoding", "gzip, deflate, sdch")
            				.header("Accept-Language", "zh-CN,zh;q=0.8")
            				.header("Cache-Control", "max-age=0")
            				.header("User-Agent", UserAgentUtil.randomUserAgent())
            				.header("Cookie", "channelid=0; sid=1495777796497660; _ga=GA1.2.7788636.1495776618; _gid=GA1.2.377650158.1495778243; Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1495776618; Hm_lpvt_7ed65b1cc4b810e9fd37959c9bb51b31=1495778243")
            				.header("Host", "www.kuaidaili.com")
            				.header("Referer", baseUrl + (i-1) + "/")
            				.timeout(30*1000)
            				.get();
            	}
                logger.info(baseUrl + i + "/ ==" + doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(" ");
                    String[] chk = ProxyUtil.checkProxy(strs[0],Integer.parseInt(strs[1]), FROM);
                    if("true".equals(chk[0])){
                    	// 保存有效代理ip到数据库
                    	proxyIPService.saveProxy(strs[0], Integer.parseInt(strs[1]), chk[1], FROM);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                proxy = proxyIPService.getAProxy();
            }
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

	public static void main(String[] args) {
        KDLProxyIPCralwer proxyCrawler = new KDLProxyIPCralwer();
          
        proxyCrawler.run();
    }
}