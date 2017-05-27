package com.weiniu.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiniu.cralwer.KDLProxyIPCralwer;
import com.weiniu.cralwer.LiuLiuProxyIPCralwer;
import com.weiniu.cralwer.XiCiProxyIPCralwer;
import com.weiniu.cralwer.YunProxyIPCralwer;

/**
 * 爬取代理网站免费ip的定时任务
 * @author zhangjw
 *
 */
@Component
public class ProxyCralwerTask {

	@Autowired
	private KDLProxyIPCralwer kDLProxyIPCralwer;
	@Autowired
	private LiuLiuProxyIPCralwer liuLiuProxyIPCralwer;
	@Autowired
	private XiCiProxyIPCralwer xiCiProxyIPCralwer;
	@Autowired
	private YunProxyIPCralwer yunProxyIPCralwer;
	
	public void run(){
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				kDLProxyIPCralwer.run();
			}
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				liuLiuProxyIPCralwer.run();
			}
		});
		t2.start();
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				xiCiProxyIPCralwer.run();
			}
		});
		t3.start();
		
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				yunProxyIPCralwer.run();
			}
		});
		t4.start();
	}
	
}
