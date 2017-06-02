package com.weiniu.task;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import com.weiniu.utils.BandwidthLimiter;
import com.weiniu.utils.CommonUtil;
import com.weiniu.utils.DownloadLimiter;
import com.weiniu.utils.MaYiUtil;
import com.weiniu.utils.ProxyUtil;


public class LimitDownloadTask {
	
	public static String URLS = "http://118.89.150.235:9999/a.jpg";
//	public static String URLS = "http://118.89.150.235:9999/ccc.mp4";
	
	
	public static void main(String[] args) {
		LimitDownloadTask task = new LimitDownloadTask();
		
		task.run();
		
	}
	
	public void run(){
		long start = System.currentTimeMillis();
		for(int i = 0; i< 100; i++) {
			System.out.print(i +" : ");
			uploadPic(i + "", URLS);
		}
		long end = System.currentTimeMillis();
		long all = end - start;
		System.out.println(all/1000);
	}
	
	public void uploadPic(String i, String picUrl){
		if(CommonUtil.isEmpty(picUrl)){
		    return;
		}
	    InputStream is = null;
	    FileOutputStream fos = null;
		try {
		    URL url  = new URL(picUrl);
			if(!CommonUtil.isEmpty(url)){
//				String[] ip = ProxyUtil.getAProxy().split(":");
//				InetSocketAddress socket = new InetSocketAddress(MaYiUtil.PROXY_IP, MaYiUtil.PROXY_PORT);
//				InetSocketAddress socket = new InetSocketAddress(ip[0], Integer.parseInt(ip[1]));
//				Proxy proxy = new Proxy(Proxy.Type.HTTP, socket);
//				URLConnection conn = url.openConnection(proxy);
				URLConnection conn = ProxyUtil.getConnection(url);
				//conn.setRequestProperty("Proxy-Authorization", MaYiUtil.authHeader());
				InputStream inputStream = conn.getInputStream();
				
				DownloadLimiter dl = new DownloadLimiter(inputStream, new BandwidthLimiter(4096));
				DataInputStream iss = new DataInputStream(dl);
				String name = "E:\\test10\\" + System.currentTimeMillis() + ".jpg";
				BufferedImage bi = ImageIO.read(iss);
				File file1 = new File(name);
				ImageIO.write(bi, "jpg", file1);
				iss.close();
				
				
//				byte[] getData = readInputStream(dl);
//				//文件保存位置  
//		        File saveDir = new File("E:\\test10");  
//		        if(!saveDir.exists()){  
//		            saveDir.mkdir();  
//		        }
//		        String fileName = System.currentTimeMillis() + ".mp4";
//		        File file = new File(saveDir + File.separator + fileName);      
//		        fos = new FileOutputStream(file);       
//		        fos.write(getData);
//		        fos.flush();
//				fos.close();
				
				dl.close();
				inputStream.close();
				System.out.println("success");
			}
		} catch (Exception e) {
			System.out.println("err");
			ProxyUtil.removeProxy();
			e.printStackTrace();
		} finally {
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
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
