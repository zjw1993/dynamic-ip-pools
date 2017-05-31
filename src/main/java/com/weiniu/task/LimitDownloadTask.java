package com.weiniu.task;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
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


public class LimitDownloadTask {
	
//	@Autowired
//	private IProxyIPService proxyIPService;	
	
	public static String URLS = 
			   "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,"
			+ "http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg,http://118.89.150.235:9999/a.jpg";
	
	
	public static void main(String[] args) {
		LimitDownloadTask task = new LimitDownloadTask();
		
		task.run();
		
	}
	
	public void run(){
		String[] images = URLS.split(",");
		int i = 0;
		for(String url : images) {
			System.out.print(i +" : ");
			uploadPic(i+"", url);
			i++;
		}
		
	}
	
	public void uploadPic(String i, String picUrl){
		if(CommonUtil.isEmpty(picUrl)){
		    return;
		}
	    InputStream is = null;
		try {
		    URL url  = new URL(picUrl);
			if(!CommonUtil.isEmpty(url)){
				
				InetSocketAddress socket = new InetSocketAddress(MaYiUtil.PROXY_IP, MaYiUtil.PROXY_PORT);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, socket);
				URLConnection conn = url.openConnection(proxy);
				conn.setRequestProperty("Proxy-Authorization", MaYiUtil.authHeader());
				InputStream inputStream = conn.getInputStream();
				
				DownloadLimiter dl = new DownloadLimiter(inputStream, new BandwidthLimiter(512));
				DataInputStream iss = new DataInputStream(dl);
				String name = "E:\\test\\" + System.currentTimeMillis() + ".jpg";
				BufferedImage bi = ImageIO.read(iss);
				File file1 = new File(name);
				ImageIO.write(bi, "jpg", file1);
				iss.close();
				System.out.println("success");
			}
		} catch (Exception e) {
			System.out.println("err");
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

}
