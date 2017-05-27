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
 * @Description: [云代理]网站代理服务器ip地址爬取
 * http://www.ip3366.net/
 * 
 */
@Component
public class YunProxyIPCralwer {
  
	private static final Logger logger = LoggerFactory.getLogger(YunProxyIPCralwer.class);
	
	private static final String FROM = "云代理";
	private static final String URL = "http://www.ip3366.net/free/?stype=1&page=";  // 国内高匿代理
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
            		doc = Jsoup.connect(baseUrl + i)
                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                            .header("Accept-Encoding", "gzip, deflate, sdch")
                            .header("Accept-Language", "zh-CN,zh;q=0.8")
                            .header("Cache-Control", "max-age=0")
                            .header("User-Agent", UserAgentUtil.randomUserAgent())
                            .header("Cookie", "ASPSESSIONIDASSDTTBB=GCGAPDODMCFDLJMOHKPHMPKK; safedog-flow-item=A788CDD0EBD25DE33E0238B3E52EAFD7; UM_distinctid=15c44e0d2c80-070b24824c7c2a-3c654009-1fa400-15c44e0d2c925f; CNZZDATA1256284042=1219135109-1495804302-%7C1495804302")
                            .header("Host", "www.ip3366.net")
                            .header("Referer", "http://www.ip3366.net/free")
                            .timeout(30*1000)
                            .proxy(proxy.getHost(), proxy.getPort(), null)
                            .get();
            	}else {
            		doc = Jsoup.connect(baseUrl + i)
                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                            .header("Accept-Encoding", "gzip, deflate, sdch")
                            .header("Accept-Language", "zh-CN,zh;q=0.8")
                            .header("Cache-Control", "max-age=0")
                            .header("User-Agent", UserAgentUtil.randomUserAgent())
                            .header("Cookie", "ASPSESSIONIDASSDTTBB=GCGAPDODMCFDLJMOHKPHMPKK; safedog-flow-item=A788CDD0EBD25DE33E0238B3E52EAFD7; UM_distinctid=15c44e0d2c80-070b24824c7c2a-3c654009-1fa400-15c44e0d2c925f; CNZZDATA1256284042=1219135109-1495804302-%7C1495804302")
                            .header("Host", "www.ip3366.net")
                            .header("Referer", "http://www.ip3366.net/free")
                            .timeout(30*1000)
                            .get();
            	}
                logger.info(baseUrl + i + "==" +doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(" ");
                    if(ProxyUtil.checkProxy(strs[0],Integer.parseInt(strs[1]), FROM)){
                    	// 保存有效代理ip到数据库
                    	proxyIPService.saveProxy(strs[0], Integer.parseInt(strs[1]), FROM);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                proxy = proxyIPService.getAProxy();
            }
            try {
				Thread.sleep((long)Math.random() * 10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

	public static void main(String[] args) {
        YunProxyIPCralwer proxyCrawler = new YunProxyIPCralwer();
          
        proxyCrawler.run();
    }
}