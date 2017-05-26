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
 * XiCiProxyIPCralwer 
 * @Description: [xicidaili-西刺代理]网站代理服务器ip地址爬取
 * 
 */
@Component
public class XiCiProxyIPCralwer {
  
	private static final String FROM = "西刺代理";
	
	@Autowired
	private IPService iPService;
	
    public void run(){
    	kuaidailiCom("http://www.xicidaili.com/nn", 15);  // 国内高匿代理
    }  
      
    private void kuaidailiCom(String baseUrl, int totalPage){
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";
        Pattern ipPtn = Pattern.compile(ipReg);
          
        for (int i = 1; i < totalPage; i++) {
            try {
                Document doc = Jsoup.connect(baseUrl + "/" + i)
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("Accept-Language", "zh-CN,zh;q=0.8")
                        .header("Cache-Control", "max-age=0")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                        .header("Cookie", "_free_proxy_session=BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJTlkMjVmZjljMjU1MzkzMGViNTg1OTQ3N2JlODJlNDU1BjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMWxlV0J6TWN5b1BRRklZZlNjQzh6RDRTdjNUK0xXYjNEN1JKMDVEeGw4aVk9BjsARg%3D%3D--c206cc57d4bb1de14f0cb0ab5d1474593f96c3c7; Hm_lvt_0cf76c77469e965d2957f0553e6ecf59=1495770792,1495791653; Hm_lpvt_0cf76c77469e965d2957f0553e6ecf59=1495791688")
                        .header("Host", "www.xicidaili.com")
                        .header("Referer", "http://www.xicidaili.com/nn/2")
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
        XiCiProxyIPCralwer proxyCrawler = new XiCiProxyIPCralwer();
          
        proxyCrawler.run();
    }
}