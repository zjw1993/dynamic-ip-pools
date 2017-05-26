package com.weiniu.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiniu.entity.IP;
import com.weiniu.service.IPService;
import com.weiniu.utils.ProxyUtil;

@Component
public class CheckProxyTask {
	
	@Autowired
	private IPService iPService;
	
	/**
	 * 遍历测试数据库中的代理ip，删除失效的
	 */
	public void run(){
		List<IP> ipList = iPService.selectAll();
		System.out.println("数据库记录过滤开始.....本次共扫描到" + ipList.size() + "条数据");
		for(IP ip : ipList) {
			boolean isAble = ProxyUtil.checkProxy(ip.getHost(), ip.getPort(), "[后台检测]");
			if(!isAble) {
				//System.out.println("代理 " + ip.getHost() + ":" + ip.getPort() + "已经不可用...删除...");
				iPService.delete(ip.getId());
			}
		}
	}
	
}
