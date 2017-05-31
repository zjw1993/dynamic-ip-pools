package com.weiniu.download;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
import com.weiniu.utils.MaYiAuthUtil;


public class Download {
	public static String URLS = "http://mmsns.qpic.cn/mmsns/1hwaRs2Nia1FibRoYLm3My6MpPRvbOhobJnDUDJzgD5fxw12vQ0OnYZib4TRCax4I3drtQmKWJXdKI/,http://mmsns.qpic.cn/mmsns/1hwaRs2Nia1FibRoYLm3My6MpPRvbOhobJl2iahB5E8XPDrGYaPOQVb2fPUHtPMWqJ9nQDuicUL7uZ8/,http://mmsns.qpic.cn/mmsns/1hwaRs2Nia1FibRoYLm3My6IwmMeNxic3jrOw2vCm5L4wpntlC4eHXwGLgevCia1KsV5s7bSVEzX5lo/,http://mmsns.qpic.cn/mmsns/1hwaRs2Nia1FibRoYLm3My6IwmMeNxic3jrcSsbDKx4qUN8nvD2XRuEXI1x870B9rKjUH8borXR0T4/,http://mmsns.qpic.cn/mmsns/1hwaRs2Nia1FibRoYLm3My6IwmMeNxic3jrfeMxRfCiaoZkuSdxV3yXQus47qacXobyLVfpuBYk38N4/,http://mmsns.qpic.cn/mmsns/1hwaRs2Nia1FibRoYLm3My6IwmMeNxic3jrnUynBJb4ZduepHPB2icx9FK9Kz1ib6NzgLkh15m29R2BQ/";
	
	public static void main(String[] args){
		
		String[] images = URLS.split(",");
		int i = 0;
		for(String url : images) {
			System.out.print(i +" : ");
			uploadPic(i,url);
			i++;
		}
		
	}
	
	
	public static String  uploadPic(int i, String urlPath){
		
		String backStr = "";
	    InputStream is = null;
		try {
		    URL url  = new URL(urlPath);
			if(!CommonUtil.isEmpty(url)){
				InetSocketAddress socket = new InetSocketAddress(MaYiAuthUtil.PROXY_IP, MaYiAuthUtil.PROXY_PORT);
				Proxy proxy = new Proxy(Proxy.Type.HTTP, socket);
				URLConnection conn = url.openConnection(proxy);
				conn.setRequestProperty("Proxy-Authorization", MaYiAuthUtil.authHeader());
				InputStream inputStream = conn.getInputStream();
				
				DownloadLimiter dl = new DownloadLimiter(inputStream, new BandwidthLimiter(512));
				DataInputStream  iss = new DataInputStream(dl);
				File saveDir = new File("E:\\test6");  
				if(!saveDir.exists()){  
					saveDir.mkdir();  
				}
				BufferedImage bi = ImageIO.read(iss);
				File file1 = new File(saveDir + File.separator + System.currentTimeMillis() + ".jpg");
				ImageIO.write(bi, "jpg", file1);
				iss.close();
				System.out.println("success");
			}
		} catch (Exception e) {
			System.err.println("err");
			e.printStackTrace();
		    return backStr;
		} finally {
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(!CommonUtil.isEmpty(backStr)){
			return backStr.substring(0, backStr.length() - 1);
		}
		return backStr;
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
