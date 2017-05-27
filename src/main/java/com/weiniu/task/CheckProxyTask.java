package com.weiniu.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiniu.entity.ProxyIP;
import com.weiniu.service.IPService;
import com.weiniu.utils.ProxyUtil;

/**
 * 检测数据库中记录是否有效的定时任务
 * @author zhangjw
 *
 */
@Component
public class CheckProxyTask {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckProxyTask.class);
	
	@Autowired
	private IPService iPService;
	
	/**
	 * 遍历测试数据库中的代理ip，删除失效的
	 */
	public void run(){
		List<ProxyIP> ipList = iPService.selectAll();
		logger.info("数据库记录过滤开始.....本次共扫描到" + ipList.size() + "条数据");
		for(ProxyIP ip : ipList) {
			boolean isAble = ProxyUtil.checkProxy(ip.getHost(), ip.getPort(), "[后台检测]");
			if(!isAble) {
				iPService.delete(ip.getId());
			}
		}
	}
	
}
