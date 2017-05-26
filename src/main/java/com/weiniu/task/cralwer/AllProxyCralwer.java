package com.weiniu.task.cralwer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllProxyCralwer {

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
