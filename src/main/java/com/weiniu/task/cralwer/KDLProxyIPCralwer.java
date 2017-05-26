package com.weiniu.task.cralwer;  
  
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiniu.service.IPService;
import com.weiniu.utils.ProxyUtil;
  
/** 
 *  
 * ProxyCralwerUnusedVPN 
 * @Description: [快代理]网站代理服务器ip地址爬取
 * 
 */
@Component
public class KDLProxyIPCralwer {
  
	private static final String FROM = "快代理";
	
	@Autowired
	private IPService iPService;
	
    public void run(){
    	kuaidailiCom("http://www.kuaidaili.com/free/inha/", 15);  // 国内高匿代理
    }  
    
    private void kuaidailiCom(String baseUrl, int totalPage){
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";
        Pattern ipPtn = Pattern.compile(ipReg);
          
        for (int i = 1; i < totalPage; i++) {
            try {
                Document doc = Jsoup.connect(baseUrl + i + "/")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("Accept-Language", "zh-CN,zh;q=0.8")
                        .header("Cache-Control", "max-age=0")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                        .header("Cookie", "channelid=0; sid=1495777796497660; _ga=GA1.2.7788636.1495776618; _gid=GA1.2.377650158.1495778243; Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1495776618; Hm_lpvt_7ed65b1cc4b810e9fd37959c9bb51b31=1495778243")
                        .header("Host", "www.kuaidaili.com")
                        .header("Referer", "http://www.kuaidaili.com/free/outha/")
                        .timeout(30*1000)
                        .get();
                System.out.println(doc.text());
                Matcher m = ipPtn.matcher(doc.text());
                  
                while(m.find()){
                    String[] strs = m.group().split(" ");
                    if(ProxyUtil.checkProxy(strs[0],Integer.parseInt(strs[1]), FROM)){
                    	// 保存有效代理ip到数据库
                    	iPService.saveProxy(strs[0], Integer.parseInt(strs[1]), FROM);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

	public static void main(String[] args) {
        KDLProxyIPCralwer proxyCrawler = new KDLProxyIPCralwer();
          
        proxyCrawler.run();
    }
}