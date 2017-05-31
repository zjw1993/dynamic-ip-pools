package com.weiniu.task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiniu.entity.ProxyIP;
import com.weiniu.service.IProxyIPService;

@Component
public class DownloadByIpPoolTask {

	@Autowired
	private IProxyIPService proxyIPService;
	
	private static final String IMG_URL = "http://118.89.150.235:9999/a.jpg";
	
	public void run(){
		for(int i=0; i< 1000; i++) {
			System.out.print(i + " : ");
			download(IMG_URL);
		}
	}
	
	private void download(String imgurl){
		
		ProxyIP proxyIP = proxyIPService.getAProxy();
		if(null != proxyIP) {
			
			InputStream in = null;
			FileOutputStream fos = null;
			try {
				URL url = new URL(imgurl);
				
				InetSocketAddress socket = new InetSocketAddress(
						InetAddress.getByName(proxyIP.getHost()), 
						proxyIP.getPort());
				Proxy proxy = new Proxy(Proxy.Type.HTTP, socket);
				URLConnection con = url.openConnection(proxy);
				//con.setRequestProperty("User-Agent", UserAgentUtil.randomUserAgent());
				in = con.getInputStream();
				
		        byte[] getData = readInputStream(in);      
		  
		        //文件保存位置  
		        File saveDir = new File("G:\\download");  
		        if(!saveDir.exists()){  
		            saveDir.mkdir();  
		        }
		        String fileName = System.currentTimeMillis() + ".jpg";
		        File file = new File(saveDir + File.separator + fileName);      
		        fos = new FileOutputStream(file);       
		        fos.write(getData);
		        fos.flush();
		        
		        System.out.println("success");
				
			} catch (MalformedURLException e) {
				System.err.println("error");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("error");
				e.printStackTrace();
			} finally {
				if(null != in) {
					try {
							in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(null != fos) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }
	
}
